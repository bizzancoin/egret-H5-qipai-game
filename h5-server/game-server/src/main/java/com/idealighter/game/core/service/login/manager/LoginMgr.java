
package com.idealighter.game.core.service.login.manager;

import com.alibaba.fastjson.JSON;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.constant.PlayerType;
import com.idealighter.game.core.constant.SysConfigId;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.SmsMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.CancelPlayerExitEvent;
import com.idealighter.game.core.service.event.struct.ChannelCloseEvent;
import com.idealighter.game.core.service.event.struct.GameClearExitEvent;
import com.idealighter.game.core.service.event.struct.PlayerExitEvent;
import com.idealighter.game.core.service.event.struct.PlayerLoginEvent;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.log.struct.login.LoginLog;
import com.idealighter.game.core.service.log.struct.login.LogoutLog;
import com.idealighter.game.core.service.log.struct.login.RegisterLog;
import com.idealighter.game.core.service.login.constant.LoginPlatform;
import com.idealighter.game.core.service.login.constant.LoginType;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.IPlayerService;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.whitelist.manager.WhiteListMgr;
import com.idealighter.game.core.util.PwdEncode;
import com.idealighter.game.core.util.RandomKeyGenerator;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.SysconfigDic;
import com.idealighter.game.dictionary.dic.WordsDic;
import com.idealighter.game.login.dto.ReLoginCodeDto;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.core.code.SessionType;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.utils.check.CheckUtil;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;
import com.idealighter.utils.crypto.MD5Utils;
import com.idealighter.utils.json.JsonUtil;
import com.idealighter.utils.time.TimeUtil;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.mybatis.guice.transactional.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

/**
 * 登陆逻辑 .
 *
 */
@Singleton
public class LoginMgr {
  private static final Logger LOG = LoggerFactory.getLogger(LoginMgr.class);
  private static final AtomicLong touristIdSeed = new AtomicLong(System.currentTimeMillis());
  private static final AtomicLong weChatIdSeed = new AtomicLong(System.currentTimeMillis());
  // public static final Long INIT_GOLD = 10000000L;
  public static final Long INIT_GOLD = 0L;

  // 服务器最大承载人数
  private static final int MAX_PLAYERS = 50000;
  // 刷新手机登录验证码间隔时间(毫秒)
  private static final int REFRESH_PHONE_LOGIN_KEY_INTERVAL = 60000;
  // 加密ip的附加值
  private static final String ENCODE_IP_EXT = "weFrt15@74";

  private final EventMgr eventMgr;

  @Inject
  @Named("default.channel.id")
  String channelId;

  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private ExecutorMgr executorMgr;
  @Inject
  private IPlayerService playerService;
  @Inject
  private LoginMsgMgr loginMsgMgr;

  // @Inject
  // private CommonMsgMgr commonMsgMgr;
  // @Inject
  // private PlayerHeadSrcDic playerIconDic;
  @Inject
  private SysconfigDic sysconfigDic;
  @Inject
  private WhiteListMgr whiteListMgr;
  // @Inject
  // private BlackListMgr blackListMgr;
  @Inject
  private SmsMgr smsMgr;
  @Inject
  private WordsDic wordsDic;

  private final String defaultSmsCode;

  /**
   * 构造函数.
   * 
   * @param eventMgr 时间管理.
   * @param defaultSmsCode 默认smscode。
   */
  @Inject
  public LoginMgr(EventMgr eventMgr, @Named("smscode.default") String defaultSmsCode,
      @Named("default.channel.id") String channelId) {
    this.defaultSmsCode = defaultSmsCode;
    eventMgr.register(this);
    this.eventMgr = eventMgr;
  }

  /**
   * 订阅玩家退出游戏事件, . 出错极端情况： 1.A登录游戏,禁用网络，客户端知道掉线，服务器不知道(认为客户端还在线)； .
   * 2.A开启网络，断线重连，重新登录，服务器一般先收到当前网络连接的登录，后收到1步骤建立的连接的掉线；会导致A在当前连接重新登录成功后( . 在线玩家列表注册当前玩家)，
   * 立马处理1步骤建立的连接掉线(在线玩家列表注册移除当前玩家)， . 结果就是在线玩家列表中没有当前登录的玩家， 修正方法:玩家掉线时，掉线的玩家和当前在线的玩家是否为同一个(player
   * == playerMgr.getPlayer(player.getId())) .
   * 
   * @param event .
   */
  @Subscribe
  public void onChannelClose(ChannelCloseEvent event) {
    if (event.player != null && event.channelHashCode != null) {

      executorMgr.getLoginExecutor(event.channelHashCode).execute(() -> {
        Player player = event.player;
        if (player != null && !player.logouted && player.playerBo() != null
            && player == playerMgr.getPlayer(player.getId())) {
          logoutForChannelClose(event.player);
        }
      });
    }
  }

  /**
   * 断线，游戏出发 . 让用户退出，不通知.
   * 
   * @param event 事件.
   */
  @Subscribe
  public void onGameClearExitEvent(GameClearExitEvent event) {
    if (event.player == null) {
      LOG.info("游戏清空用户错误，player为null");
    }
    executorMgr.getLoginExecutor(event.player.getChannelHashCode()).execute(() -> {
      Player player = event.player;
      LOG.info("捕获到用户[{}]被游戏[{}]清空，执行真正退出", player.getId(), event.gameName);

      if (!player.logouted && player == playerMgr.getPlayer(player.getId())) {
        clearForLogout(player);
      }
    });
  }

  /**
   * 游客登录.
   * 
   * @Title: touristLogin.
   * 
   * @author HouDongSheng
   * @date 2017年11月26日 下午4:56:18
   * @param player 玩家编号
   * @param code 游客唯一标识,第一次登录传""
   * @param platform 平台(0:ios,1:android,2:pc)
   */
  @Transactional
  public ResMessage touristLogin(Player player, String code, int platform) {
    // logOutOldPlayer(player);
    HuohuaAssert.isTrue(LoginPlatform.isClient(platform));
    if (playerMgr.onLinePlayersNum() >= MAX_PLAYERS) {
      LOG.error("游客[{}]登录失败超过服务器承载人数[{}]", code, MAX_PLAYERS);
      HuohuaAssert.isTrue(false, ErrorCode.SERVICE_UNAVAILABLE);
    }

    player.fromPhone = true;
    // 根据用户名查找玩家，先在活跃账号缓存找，找不到再去数据库找
    PlayerBo playerDom = null;
    if (code != null && code.trim().length() > 0) {
      playerDom = playerMgr.selectPlayer(code);
    }

    // 非游客进行游客登录，创建新的账户
    HuohuaAssert.isTrue(playerDom == null || playerDom.isTourist(), ErrorCode.BAD_REQUEST);

    // PlayerDomain playerDom = null;
    if (playerDom == null) {
      code = TimeUtil.format("yyMMddHHmmss", TimeUtil.now())
          + UUID.randomUUID().toString().replace("-", "");
      String userName = code; // 游客的账号为code
      String playerName = "游客" + touristIdSeed.incrementAndGet();
      String sex = RandCodeUtil.randomBoolean() ? "女" : "男";
      // 必须设置PlayerDomain，PlayerDomain才是数据
      player.setPlayerBo(new PlayerBo());
      // 游客登录手机注册
      player.setRegisterType((byte) 3);
      player.setUserName(userName);
      player.setPlayerName(playerName);
      player.setPassword(""); // 游客没有密码
      player.setIcon(1);
      player.setSex(sex);
      player.setRegisterIp(player.ip);
      player.setRegisterTime(new Date());
      player.setLoginTime(new Date());
      player.setLoginIp(player.ip);
      player.setLoginCount(1);
      player.setOnline(true);
      player.setTourist(true);
      player.setUpdateTime(new Date());
      player.initGold(INIT_GOLD);
      player.setType(PlayerType.USER);
      player.setTransferReward(0);
      player.setSuperId(0);
      player.setChannelId(channelId);

      playerMgr.insertPlayer(player);
      player.setSuperId(player.getId()); // 默认靓号为用户编号
      playerMgr.asyncUpdateSuperId(player); // 更新玩家的靓号


      // 注册日志
      DbLogService.log(new RegisterLog(player, platform));

      String reLoginCode = generateReLoginCode(player.ip, code);
      doLogin(player, player.playerBo(), platform, LoginType.TOURIST.getType(), reLoginCode);
    } else {

      String reLoginCode = generateReLoginCode(player.ip, code);
      doLogin(player, playerDom, platform, LoginType.TOURIST.getType(), reLoginCode);
    }

    return ResMessage.DEFAULT;
  }

  /**
   * 微信登录.
   * 
   * @param player 玩家.
   * @param unionId 微信unionid.
   * @param nickName 昵称.
   * @param platform 平台(0:ios, 1:android, 2:pc).
   * @return 结果.
   */
  public ResMessage weChatLogin(Player player, String unionId, String nickName, int platform) {
    HuohuaAssert.isTrue(LoginPlatform.isClient(platform));
    // logOutOldPlayer(player);
    if (playerMgr.onLinePlayersNum() >= MAX_PLAYERS) {
      LOG.error("玩家[{}]登录失败超过服务器承载人数[{}]", player.getPhone(), MAX_PLAYERS);
      HuohuaAssert.isTrue(false, ErrorCode.SERVICE_UNAVAILABLE);
    }

    // String oldUnionId = unionId;

    if (!checkUnionId(unionId)) {
      LOG.error("玩家微信登录失败, unionId:[{}]有误", unionId);
      HuohuaAssert.isTrue(false);
    }
    unionId = unionId.split("\\.")[0];
    player.fromPhone = true; // 来自手机端
    PlayerBo playerDom = playerMgr.selectPlayerByUnionId(unionId);
    if (playerDom == null) {
      // 新的号码，执行注册
      String userName = "微信玩家" + weChatIdSeed.incrementAndGet();
      String playerName = nickName;
      String sex = RandCodeUtil.randomBoolean() ? "女" : "男";
      // 必须设置PlayerDomain，PlayerDomain才是数据
      player.setPlayerBo(new PlayerBo());
      // 客户端手机注册
      player.setRegisterType((byte) 1);
      player.setUserName(userName);
      player.setPlayerName(playerName);
      player.setPassword(PwdEncode.encodeWithTail(userName));
      player.setIcon(1);
      player.setSex(sex);
      player.setRegisterIp(player.ip);
      player.setRegisterTime(new Date());
      player.setLoginCount(0);
      player.setTourist(false);
      player.setUpdateTime(new Date());
      player.setUnionId(unionId);
      player.initGold(INIT_GOLD);
      player.setType(PlayerType.USER);
      player.setTransferReward(0);
      player.setSuperId(0);

      playerMgr.insertPlayer(player);
      player.setSuperId(player.getId()); // 默认靓号为用户编号
      playerMgr.asyncUpdateSuperId(player); // 更新玩家的靓号

      // 注册日志
      DbLogService.log(new RegisterLog(player, platform));

      // 设置playerDom
      playerDom = player.playerBo();
    }

    // 执行真正的登录
    String reLoginCode = generateReLoginCode(player.ip, unionId);
    doLogin(player, playerDom, platform, LoginType.WECHAT.getType(), reLoginCode);

    return ResMessage.DEFAULT;
  }

  private boolean checkUnionId(String unionId) {
    boolean result = false;
    if (unionId != null && unionId.trim().length() > 0) {
      String[] arrs = unionId.split("\\.");
      if (arrs != null && arrs.length == 3) {
        if (arrs[2].equals(MD5Utils.encode(arrs[0] + "." + arrs[1] + "huohuagame!@#"))) {
          // 时间
          Long sec = Long.parseLong(arrs[1]);
          Long nowSec = System.currentTimeMillis() / 1000;
          if ((nowSec - sec) < 60 * 30) {
            result = true;
          }
        }
      }
    }
    return result;
  }

  private void checkLoginOrRegisterSmsCode(String phone, String smsCode, Player player) {
    // 如果是白名单, 允许默认验证码登录
    if (whiteListMgr.isInWhiteList(phone)) {
      if (!defaultSmsCode.equals(smsCode)) {
        HuohuaAssert.isTrue((phone.trim().equals(player.loginOrRegisterPhone)
            && smsCode.trim().equals(player.loginOrRegisterSmsCode)), ErrorCode.SMS_FAIL);
        // 短信码30分钟内有效
        HuohuaAssert.isTrue(
            (System.currentTimeMillis() - player.getloginOrRegisterSmsTime) <= 1000 * 60 * 30,
            ErrorCode.SMS_FAIL);
      }
    } else { // 不是白名单，只能使用短信验证码登录
      HuohuaAssert.isTrue((phone.trim().equals(player.loginOrRegisterPhone)
          && smsCode.trim().equals(player.loginOrRegisterSmsCode)), ErrorCode.SMS_FAIL);
      // 短信码30分钟内有效
      HuohuaAssert.isTrue(
          (System.currentTimeMillis() - player.getloginOrRegisterSmsTime) <= 1000 * 60 * 30,
          ErrorCode.SMS_FAIL);
    }
  }

  /**
   * 手机号码+短信注册/登录.
   * 
   * @Title: phoneLoginOrRegister.
   * @author HouDongSheng
   * @date 2017年11月26日 下午4:55:45
   * @param player 玩家
   * @param smsCode 登录短信验证码
   * @param platform 平台(0:ios,1:android,2:pc)
   */
  @Transactional
  public ResMessage phoneLoginOrRegister(Player player, String phone, String smsCode,
      int platform) {

    // logOutOldPlayer(player);
    HuohuaAssert.isTrue(LoginPlatform.isClient(platform));

    HuohuaAssert.isTrue(player.loginOrRegisterSmsCode != null, ErrorCode.SMS_FAIL);
    HuohuaAssert.isTrue(CheckUtil.checkTelephone(phone));
    HuohuaAssert.isTrue(CheckUtil.checkLength(smsCode, 6));

    // 校验手机号码是否正确
    checkLoginOrRegisterSmsCode(phone, smsCode, player);

    long startTime = System.nanoTime();
    PlayerBo playerDom = playerMgr.selectPlayerByPhone(phone);

    LOG.info("获取用户时间={}", System.nanoTime() - startTime);



    if (playerDom == null) {
      // String phone = player.loginOrRegisterPhone; // 注册手机号码
      // 新的号码，执行注册
      String userName = phone;
      String playerName = userName;
      String sex = RandCodeUtil.randomBoolean() ? "女" : "男";
      // 必须设置PlayerDomain，PlayerDomain才是数据
      player.setPlayerBo(new PlayerBo());
      // 客户端手机注册
      player.setRegisterType((byte) 1);
      player.setUserName(userName);
      player.setPlayerName(playerName);
      player.setPassword(PwdEncode.encodeWithTail(userName));
      player.setIcon(1);
      player.setSex(sex);
      player.setRegisterIp(player.ip);
      player.setRegisterTime(new Date());
      player.setLoginCount(0);
      player.setTourist(false);
      player.setUpdateTime(new Date());
      player.setPhone(phone);
      player.initGold(INIT_GOLD);
      player.setType(PlayerType.USER);
      player.setTransferReward(0);
      player.setSuperId(0);

      playerMgr.insertPlayer(player);
      player.setSuperId(player.getId()); // 默认靓号为用户编号
      playerMgr.asyncUpdateSuperId(player); // 更新玩家的靓号

      // 注册日志
      DbLogService.log(new RegisterLog(player, platform));

      // 设置playerDom
      playerDom = player.playerBo();
    }

    // if (playerMgr.onLinePlayersNum() >= MAX_PLAYERS) {
    // LOG.error("玩家[{}]登录失败超过服务器承载人数[{}]", player.getPhone(), MAX_PLAYERS);
    // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.SERVICE_UNAVAILABLE, Operator.SYSTEM);
    // return;
    // }

    if (playerMgr.onLinePlayersNum() >= MAX_PLAYERS) {
      LOG.error("玩家[{}]登录失败超过服务器承载人数[{}]", player.getPhone(), MAX_PLAYERS);
      HuohuaAssert.isTrue(false, ErrorCode.SERVICE_UNAVAILABLE);
    }

    String reLoginCode = generateReLoginCode(player.ip, phone);
    // 执行真正的登录
    doLogin(player, playerDom, platform, LoginType.PHONE.getType(), reLoginCode);

    return ResMessage.DEFAULT;
  }

  /**
   * 产生 relogincode .
   *
   * @author abin
   * @date 2018年6月21日 下午7:28:01
   * @param ip ip.
   * @param account 帐号.
   * @return code。
   */
  public static String generateReLoginCode(String ip, String account) {
    // 短线重连码
    ReLoginCodeDto loginCode = new ReLoginCodeDto(Base64Utils.encodeToString(account.getBytes()),
        encodeLoginIp(Base64Utils.encodeToString(ip.getBytes())));
    String reLoginCode = Base64Utils.encodeToString(JsonUtil.toJson(loginCode).getBytes()) + "."
        + System.currentTimeMillis();
    reLoginCode += "." + PwdEncode.encodeWithTail(reLoginCode);

    return reLoginCode;
  }


  /**
   * 断线重连.
   * 
   * @Title: reLogin.
   * @author HouDongSheng
   * @date 2017年11月26日 下午10:41:47
   * @param player 玩家
   * @param type 类型(游客,手机账号,机器人)
   * @param code reLoginCode
   */
  public ResMessage reLogin(Player player, int type, String code, boolean byHand, int platform) {
    HuohuaAssert.isTrue(LoginPlatform.isClient(platform));

    String[] arrs = code.split("\\.");
    // arrs[0] json数据({"account":"", randCode:"1fes", ip:"wefrfd"})
    // arrs[1] 上次登陆时间
    // arrs[2] PwdEncode.encodeWithTail(arrs[0] + "." + arrs[1])
    HuohuaAssert.isTrue(arrs != null && arrs.length == 3, ErrorCode.USER_LOGIN_FAIL);
    HuohuaAssert.isTrue(PwdEncode.encodeWithTail(arrs[0] + "." + arrs[1]).equals(arrs[2]),
        ErrorCode.USER_LOGIN_FAIL);


    String codeJsonContent = new String(Base64Utils.decodeFromString(arrs[0]));
    // 上一次登录时间
    Long preLoginTimestamp = Long.parseLong(arrs[1]);

    if (byHand) {
      // 时间必须是一天内
      HuohuaAssert.isTrue(System.currentTimeMillis() - preLoginTimestamp <= 24L * 60 * 60 * 1000,
          ErrorCode.USER_LOGIN_FAIL);
    } else {
      // 时间必须是两天内
      HuohuaAssert.isTrue(
          System.currentTimeMillis() - preLoginTimestamp <= 2 * 24L * 60 * 60 * 1000,
          ErrorCode.USER_LOGIN_FAIL);
    }

    ReLoginCodeDto reLoginCodeDto = JSON.parseObject(codeJsonContent, ReLoginCodeDto.class);
    HuohuaAssert.isTrue(reLoginCodeDto != null, ErrorCode.USER_LOGIN_FAIL);

    String account = new String(Base64Utils.decodeFromString(reLoginCodeDto.getAccount()));
    String encodeIp = reLoginCodeDto.getIp();

    // 根据用户名查找玩家，先在活跃账号缓存找，找不到再去数据库找
    PlayerBo playerDom = null;
    // LoginType loginType = null;
    if (type == LoginType.PHONE.getType()) {
      // 手机
      // loginType = LoginType.PHONE;
      playerDom = playerMgr.selectPlayerByPhone(account);
    } else if (type == LoginType.TOURIST.getType()) {
      // 游客
      // loginType = LoginType.TOURIST;
      playerDom = playerMgr.selectPlayer(account);
    } else if (type == LoginType.WECHAT.getType()) {
      // 微信
      // loginType = LoginType.WECHAT;
      playerDom = playerMgr.selectPlayer(account);
    } else if (type == LoginType.H5.getType()) {
      // loginType = LoginType.H5;
      playerDom = playerMgr.selectPlayer(account);

    } else {
      LOG.error("断线重连类型[{}]有误", type);
      // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.BAD_REQUEST, Operator.SYSTEM);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    HuohuaAssert.notNull(playerDom, ErrorCode.USER_NOT_EXIST);

    // 校验用户密码
    if (!playerDom.getReLoginCode().equals(PwdEncode.encodeWithTail(code))) {
      if (type == LoginType.ROBOT.getType()) {
        LOG.error("机器人[{}]登录失败,密码错误", account);
      } else if (type == LoginType.PHONE.getType()) {
        LOG.error("手机账号[{}]登录失败,密码错误", account);
      } else if (type == LoginType.TOURIST.getType()) {
        LOG.error("游客[{}]登录失败,密码错误", account);
      } else if (type == LoginType.WECHAT.getType()) {
        LOG.error("[{微信}]登录失败,密码错误", account);
      }
      // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.USER_LOGIN_FAIL, Operator.SYSTEM);
      HuohuaAssert.isTrue(false, ErrorCode.USER_LOGIN_FAIL);
    }

    // 代理账号重新登录，判断
    if (playerDom.getType() == (byte) 1) {
      if (!encodeIp.equals(encodeLoginIp(Base64Utils.encodeToString(player.ip.getBytes())))) {
        LOG.error("[{}]代理[{}]登录失败,密码错误", player.getChannel(), account);
        HuohuaAssert.isTrue(false, ErrorCode.USER_LOGIN_FAIL);
      }
    }

    // 生成重连码
    String reLoginCode = code;// generateReLoginCode(player.ip, account);
    doLogin(player, playerDom, platform, type, reLoginCode);

    return ResMessage.DEFAULT;
  }

  private static String encodeLoginIp(String ip) {
    return PwdEncode.encodeWithTail(ip + ENCODE_IP_EXT);
  }

  // =============================
  /**
   * 机器人登录 .
   * 
   * @param player 玩家.
   * @param userName .
   * @param password .
   * @param gold . 初始化金币
   */
  public ResMessage robotLogin(Player player, String userName, String password, long gold) {
    // 根据用户名查找玩家，先在活跃账号缓存找，找不到再去数据库找
    PlayerBo playerDom = playerMgr.selectPlayer(userName);

    if (playerDom == null) {
      LOG.error("机器人用户名[{}]不存在", userName);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    if (!playerDom.isRobot()) {
      LOG.error("玩家[{}]不是机器人,不能使用机器人登录方式", userName);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    if (EmptyUtil.stringIsEmpty(playerDom.getPassword())) {
      playerDom.setPassword(PwdEncode.encodeWithTail(playerDom.getUserName()));
      playerMgr.updatePlayer(playerDom);
    }

    // 校验用户密码
    if (!playerDom.getPassword().equals(PwdEncode.encodeWithTail(password))) {
      LOG.error(PwdEncode.encodeWithTail(password));
      LOG.error("机器人[{}]登录失败,密码错误", userName);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    if (playerMgr.onLinePlayersNum() >= MAX_PLAYERS) {
      LOG.error("机器人[{}]登录失败超过服务器承载人数[{}]", userName, MAX_PLAYERS);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    Player oldPlayer = playerMgr.getPlayer(playerDom.getId());
    if (oldPlayer != null) {
      LOG.error("机器人[{}][{}]不能重复登录", playerDom.getId(), playerDom.getUserName());
      HuohuaAssert.isTrue(false, ErrorCode.ROBOT_HAD_LOGED);
    }

    // 设置初始化金币
    // playerDom.getGold().set(gold);
    playerDom.getSafeGold().set(gold);

    String reLoginCode = generateReLoginCode(player.ip, userName);
    doLogin(player, playerDom, LoginPlatform.ROBOT.getId(), LoginType.ROBOT.getType(), reLoginCode);

    return ResMessage.DEFAULT;
  }

  /**
   * 登录 .
   * 
   * @param player 玩家.
   * @param playerDom .
   * @param platform 平台(0:ios,1:android,2:pc,3:robot)
   */
  private void doLogin(Player player, PlayerBo playerDom, int platform, int loginType,
      String reLoginCode) {
    // 内部测试阶段只有白名单中的人可以登录
    if (sysconfigDic.getIntVal(SysConfigId.PLATFORM_STATUS) == 2
        && !whiteListMgr.isInWhiteList(player.getUserName())) {
      HuohuaAssert.isTrue(false, ErrorCode.SYSTEM_MAINTAINING);
    }

    HuohuaAssert.isTrue(!playerDom.isLocked(), ErrorCode.USER_LOCK);

    // 重复登录问题，分两种情况考虑1: 断线重连, 2:剔除其他登录
    Player oldPlayer = playerMgr.getPlayer(playerDom.getId());
    if (oldPlayer != null) {
      if (oldPlayer.logoutTimeout != null && !oldPlayer.logoutTimeout.isCancelled()) {
        LOG.info("清空旧账号的游戏信息");
        oldPlayer.logoutTimeout.cancel();
      }
      // channel不为null,表明在其他客户端登录过,强制下线
      if (oldPlayer.getChannel() != null) {
        LOG.warn("玩家[{}]重复登录,之前登录的账号将会被强制下线", oldPlayer.getId());
        noticeLogoutForRepetition(oldPlayer);
        oldPlayer.getChannel().attr(Player.PLAYER_KEY).set(null); // channel解绑Player
        oldPlayer.getChannel().attr(SessionType.PLAYER_ID_KEY).set(null); // channel解绑playerId
        oldPlayer.clearChannel(); // 玩家解绑channel
      }
      oldPlayer.setChannelNotNull(player.getChannel()); // 新的channel替换旧的channel
      oldPlayer.getChannel().attr(Player.PLAYER_KEY).set(oldPlayer); // channel绑定新的用户和用户编号
      player = oldPlayer; // player指向旧的账号

      // 取消游戏中退出
      CancelPlayerExitEvent cancelPlayerExitEvent = new CancelPlayerExitEvent(player);
      eventMgr.post(cancelPlayerExitEvent);
    }

    // 设置玩家数据
    player.setPlayerBo(playerDom);
    player.setOnline(true);
    player.setLoginIp(player.ip);
    player.setLoginTime(new Date());
    player.setLoginCount(player.getLoginCount() + 1);

    player.setReLoginCode(reLoginCode);

    loginMsgMgr.sendPlayerInfo(player);
    // 更新
    player.setReLoginCode(PwdEncode.encodeWithTail(player.getReLoginCode()));
    player.setOnline(true);
    playerMgr.asyncUpdatePlayer(player);

    if (oldPlayer == null) {
      // 触发玩家登录事件
      eventMgr.post(new PlayerLoginEvent(player));
      // 登记玩家
      playerMgr.registerPlayer(player);
    }

    // channel设置playerId属性，一个网络连接不能登录2个账号
    player.getChannel().attr(SessionType.PLAYER_ID_KEY).set(player.getId());

    // 短信码登录清空
    player.loginOrRegisterPhone = "";
    player.loginOrRegisterSmsCode = "";
    player.getloginOrRegisterSmsTime = 0;

    // 游戏开始消息
    // loginMsgMgr.sendGameStartMsg(player);
    LOG.info("玩家[{}][{}]登陆成功,channel[{}]", player.getPlayerName(), player.getId(),
        player.getChannel());

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new LoginLog(player, platform, loginType));
    }
  }

  /**
   * 校验用户昵称 .
   * 
   * @param player 玩家.
   * @param playerName .
   */
  public boolean checkPlayerName(Player player, String playerName) {
    HuohuaAssert.isTrue(CheckUtil.checkLength(playerName, 1, 45));

    // if (!CheckUtil.checkLength(playerName, 1, 18)) {
    // LOG.error("用户昵称[{}]不合法", playerName);
    // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.BAD_REQUEST, Operator.SYSTEM);
    // return false;
    // }

    for (String word : wordsDic.words()) {
      if (playerName.indexOf(word) != -1) {
        LOG.warn("昵称[{}]包含敏感词汇[{}]", playerName, word);
        // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.USER_NICKNAME_INCLUDE_SENSITIVE_WORD,
        // Operator.SYSTEM);
        HuohuaAssert.isTrue(false, ErrorCode.USER_NICKNAME_INCLUDE_SENSITIVE_WORD);
        // return false;
      }
    }

    // 判断是否为自己的昵称
    PlayerBo playerDomain = player.playerBo();
    if (playerDomain != null) {
      String nickName = playerDomain.getPlayerName();
      if (nickName.equals(playerName)) {
        return true;
      }
    }

    // 校验用户存在否
    Long playerId = playerService.selectPlayerIdByPlayerName(playerName);
    if (playerId != null) {
      LOG.error("该昵称[{}]已经存在", playerName);
      HuohuaAssert.isTrue(false, ErrorCode.USER_NICKNAME_EXIST);
      // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.USER_NICKNAME_EXIST, Operator.SYSTEM);
      // return false;
    }
    return true;
  }

  /**
   * 获取手机登录时的验证码 .
   * 
   * @param player 玩家.
   * @param phone 手机号码.
   */
  public ResMessage resLoginOrRegisterSmsCode(Player player, String phone) {
    // 校验手机号码是否合法

    HuohuaAssert.isTrue(CheckUtil.checkTelephone(phone), ErrorCode.BAD_REQUEST);

    // if (!CheckUtil.checkTelephone(phone)) {
    // LOG.warn("手机号码[{}]不合法", phone);
    // return loginMsgMgr
    // .convertLoginOrRegisterSmsMsg(ResPhoneLoginOrRegisterGetSmsMsg.ResType.ERROR, 0);
    // }

    long now = System.currentTimeMillis();

    if (player.getloginOrRegisterSmsTime > 0L) {
      // 给客户端延时2秒
      HuohuaAssert.isTrue(
          now - player.getloginOrRegisterSmsTime + 2000 >= REFRESH_PHONE_LOGIN_KEY_INTERVAL,
          ErrorCode.SMS_FREQUENT);
    }

    player.loginOrRegisterPhone = phone;
    player.loginOrRegisterSmsCode = RandomKeyGenerator.generateNum(6);
    player.getloginOrRegisterSmsTime = now;

    smsMgr.sendSmsKey(phone, player.loginOrRegisterSmsCode);
    LOG.info("手机[{}]登录获取手机验证码[{}]", phone, player.loginOrRegisterSmsCode);

    return ResMessage.DEFAULT;
  }

  private void clearForLogout(Player player) {
    LOG.info("执行用户{} 真正退出 ", player.getId());

    player.logouted = true;
    player.setOnline(false);
    player.setLogoutTime(new Date());
    player.setOnlinetime(
        player.getOnlinetime() + System.currentTimeMillis() - player.getLoginTime().getTime());

    AbstractRoom curRoom = player.curRoom;
    if (curRoom != null) { // 清空房间中的玩家
      curRoom.getPlayers().remove(player.getId());
    }
    playerMgr.updatePlayer(player.playerBo());
    // 取消登记在线玩家
    playerMgr.unregisterPlayer(player);

    unbindPlayerChannel(player);
    player.reset();

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new LogoutLog(player,
          (System.currentTimeMillis() - player.getLoginTime().getTime()) / 1000));
    }
  }

  private void logout(Player player) {
    unbindPlayerChannel(player);
    HuohuaAssert.isTrue(player != null && player.playerBo() != null,
        ErrorCode.INTERNAL_SERVER_ERROR);
    if (player.curSeat != null) {
      LOG.info("用户{} 在游戏 {} 中,让游戏退出 ", player.getId(), player.curRoom.game());
      // 触发玩家退出事件
      eventMgr.post(new PlayerExitEvent(player));
    } else {
      clearForLogout(player);
    }
  }

  private void unbindPlayerChannel(Player player) {
    Channel channel = player.getChannel();
    if (channel != null) { // 执行退出后，Player和Channel双解绑
      channel.attr(SessionType.PLAYER_ID_KEY).set(null); // 清空channel中的玩家编号
      channel.attr(Player.PLAYER_KEY).set(null); // 清空channle中绑定的player信息
      player.clearChannel(); // 玩家的channel设为null,防止退出后再收到消息

      LOG.info("退出{}用户[{}][{}]执行退出 ", channel, player.getId(), player.getUserName());
    }
  }

  /**
   * 用户主动退出.
   * 
   * @param player 玩家.
   * @return 玩家主动退出 消息.
   */
  public ResMessage resLogout(Player player) {
    logout(player);
    return ResMessage.DEFAULT;
  }

  /**
   * 停服退出信息.
   * 
   * @param player 玩家.
   */
  public void noticeLogoutForApplicationClose(Player player) {
    loginMsgMgr.sendLogoutForApplicationCloseMsg(player);
    logout(player);
    LOG.info("玩家[{}][{}]退出游戏,原因是[{}]", player.getPlayerName(), player.getId(), "停服 退出 消息");

  }

  /**
   * 通知Channel . 关闭 退出信息.z
   * 
   * @param player 玩家.
   */
  public void logoutForChannelClose(Player player) {
    logout(player);
    LOG.info("玩家[{}][{}]退出游戏,原因是[{}]", player.getPlayerName(), player.getId(), "channel关闭退出");

  }


  /**
   * 重复登录退出消息.
   * 
   * @param player 玩家.
   */
  public void noticeLogoutForRepetition(Player player) {
    // logout(player); 重复登录不需要处理消息
    LOG.info("玩家[{}][{}]退出游戏只通知，不处理业务,原因是[{}]", player.getPlayerName(), player.getId(),
        "同一个账号重复登录被T号退出");
    loginMsgMgr.sendLogoutForRepetitionMsg(player);

  }

  /**
   * 锁定登录退出.
   * 
   * @param player 玩家.
   */
  public void noticeLogoutForLocked(Player player) {
    loginMsgMgr.sendLogoutForLockedMsg(player);
    logout(player);
    LOG.info("玩家[{}][{}]退出游戏,原因是[{}]", player.getPlayerName(), player.getId(), "账号被锁定");
  }


  /**
   * 游戏退出或者清除数据.
   * 
   * @param player 玩家.
   */
  public void noticeLogoutForGameExit(Player player) {
    loginMsgMgr.sendLogoutForGameExitMsg(player);
    logout(player);
    LOG.info("玩家[{}][{}]退出游戏,原因是[{}]", player.getPlayerName(), player.getId(), "游戏退出或者清除数据");
  }

}
