package com.idealighter.game.core.service.player.struct;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;
import com.idealighter.game.core.service.welfare.struct.WelfareData;
import com.idealighter.game.gamehall.dto.MemInfo;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import io.netty.util.Timeout;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 在线玩家类是PlayerDomain的包装类 .
 * 
 * @date 2015年7月29日 下午3:26:30
 *
 */
public class Player {

  public static final AttributeKey<Player> PLAYER_KEY = AttributeKey.valueOf("player");

  public static final int PRECISION = 1000;
  // 密码格式
  public static final String PASSWORD_PATTERN = "[\\p{Alnum}\\p{Punct}]{6,30}";

  // 玩家数据,登录成功后才会有数据
  // private PlayerDomain playerDom;

  private PlayerBo playerBo;

  // 玩家日志操作id，每次msg请求的时候生成新的
  public volatile long actionId = System.nanoTime();
  // 玩家通道会话
  // public final Channel channel;
  private Channel channel;
  private int channelHashCode = 0;

  public Timeout logoutTimeout;

  // 玩家ip
  public final String ip;
  // 进入房间时间
  public long enterRoomTime;
  // 进入桌子时间
  public long enterTableTime;
  // 是否来之手机
  public boolean fromPhone;
  // 是否已经退出登录
  public volatile boolean logouted = false;
  // 玩家当前所在房间
  public volatile AbstractRoom curRoom = null;
  // 玩家当前桌子
  public volatile AbstractTable curTable = null;

  // 玩家当前座位
  public volatile AbstractSeat curSeat = null;

  // 注册/登录手机号码
  public String loginOrRegisterPhone = null;
  // 登录/注册手机验证码
  public String loginOrRegisterSmsCode = "";
  // 获取登录/注册手机验证码时间
  public long getloginOrRegisterSmsTime = 0;

  // 临时绑定手机号,通过验证后转入绑定手机
  public String tmpBindPhone;
  // 绑定手机是的手机验证码
  public String bindPhoneKey = null;
  // 获取绑定手机验证码时间
  public long getBindPhoneKeyTime = 0;
  // 获取解除绑定手机验证码时间
  public long getUnBindPhoneKeyTime = 0;
  // 解除绑定手机是的手机验证码
  public String unBindPhoneKey = null;

  /**
   * 重置玩家数据 .
   */
  public void reset() {
    this.logouted = false;
    this.tmpBindPhone = null;
    this.bindPhoneKey = null;
    this.getBindPhoneKeyTime = 0;
    this.getUnBindPhoneKeyTime = 0;
    this.unBindPhoneKey = null;
  }

  /**
   * 构造函数 .
   * 
   * @param channel .
   * @param ip .
   */
  public Player(Channel channel, String ip) {
    super();
    this.channel = channel;
    this.channelHashCode = channel.hashCode();
    this.ip = ip;
  }

  public Channel getChannel() {
    return this.channel;
  }

  public int getChannelHashCode() {
    return this.channelHashCode;
  }

  /**
   * 设置channel.
   * 
   * @Title setChannelNotNull.
   * @author houdongsheng
   * @date 2018年1月20日 下午8:22:01
   * @param channel 新的channel
   */
  public void setChannelNotNull(Channel channel) {
    HuohuaAssert.isTrue(channel != null);
    this.channel = channel;
    if (channel != null) {
      this.channelHashCode = channel.hashCode();
    }
  }

  public void clearChannel() {
    this.channel = null; // channel清空，但hashCode不清空
  }

  public PlayerBo playerBo() {
    return playerBo;
  }

  public void setPlayerBo(PlayerBo playerBo) {
    this.playerBo = playerBo;
  }

  /*
   * playerDomain代理方法 .
   */
  public long getId() {
    return playerBo.getId();
  }


  public long getSuperId() {
    return playerBo.getSuperId();
  }

  public void setSuperId(long superId) {
    playerBo.setSuperId(superId);
  }

  public String getUserName() {
    return playerBo.getUserName();
  }

  public void setUserName(String userName) {
    playerBo.setUserName(userName);
  }

  public String getUnionId() {
    return playerBo.getUnionId();
  }

  public void setUnionId(String unionId) {
    playerBo.setUnionId(unionId);
  }

  public String getPassword() {
    return playerBo.getPassword();
  }

  public void setPassword(String password) {
    playerBo.setPassword(password);
  }

  public String getSex() {
    return playerBo.getSex();
  }

  public void setSex(String sex) {
    playerBo.setSex(sex);
  }

  public String getPlayerName() {
    return playerBo.getPlayerName();
  }

  public void setPlayerName(String playerName) {
    playerBo.setPlayerName(playerName);
  }

  public String getIdcard() {
    return playerBo.getIdcard();
  }

  public void setIdcard(String idcard) {
    playerBo.setIdcard(idcard);
  }

  public String getWeixin() {
    return playerBo.getWeixin();
  }

  public void setWeixin(String weixin) {
    playerBo.setWeixin(weixin);
  }

  public String getPhone() {
    return playerBo.getPhone();
  }

  public void setPhone(String phone) {
    playerBo.setPhone(phone);
  }

  public String getEmail() {
    return playerBo.getEmail();
  }

  public void setEmail(String email) {
    playerBo.setEmail(email);
  }

  public boolean isValidatePhone() {
    return playerBo.isValidatePhone();
  }

  public void setValidatePhone(boolean validatePhone) {
    playerBo.setValidatePhone(validatePhone);
  }

  public boolean isValidateEmail() {
    return playerBo.isValidateEmail();
  }

  public void setValidateEmail(boolean validateEmail) {
    playerBo.setValidateEmail(validateEmail);
  }

  public int getLevel() {
    return playerBo.getLevel();
  }

  public void setLevel(int level) {
    playerBo.setLevel(level);
  }

  public int getVipLevel() {
    return playerBo.getVipLevel();
  }

  public void setVipLevel(int vipLevel) {
    playerBo.setVipLevel(vipLevel);
  }

  public long getRecommendId() {
    return playerBo.getRecommendId();
  }

  public void setRecommendId(long recommendId) {
    playerBo.setRecommendId(recommendId);
  }

  public byte getRegisterType() {
    return playerBo.getRegisterType();
  }

  public void setRegisterType(byte registerType) {
    playerBo.setRegisterType(registerType);
  }

  public String getRegisterIp() {
    return playerBo.getRegisterIp();
  }

  public void setRegisterIp(String registerIp) {
    playerBo.setRegisterIp(registerIp);
  }

  public Date getRegisterTime() {
    return playerBo.getRegisterTime();
  }

  public void setRegisterTime(Date registerTime) {
    playerBo.setRegisterTime(registerTime);
  }

  public String getLoginIp() {
    return playerBo.getLoginIp();
  }

  public void setLoginIp(String loginIp) {
    playerBo.setLoginIp(loginIp);
  }

  public Date getLoginTime() {
    return playerBo.getLoginTime();
  }

  public void setLoginTime(Date loginTime) {
    playerBo.setLoginTime(loginTime);
  }

  public boolean isLocked() {
    return playerBo.isLocked();
  }

  public void setLocked(boolean locked) {
    playerBo.setLocked(locked);
  }

  public Date getUpdateTime() {
    return playerBo.getUpdateTime();
  }

  public void setUpdateTime(Date updateTime) {
    playerBo.setUpdateTime(updateTime);
  }

  public boolean isOnline() {
    return playerBo.isOnline();
  }

  public void setOnline(boolean online) {
    playerBo.setOnline(online);
  }

  public String getName() {
    return playerBo.getName();
  }

  public void setName(String name) {
    playerBo.setName(name);
  }

  public Date getLogoutTime() {
    return playerBo.getLogoutTime();
  }

  public void setLogoutTime(Date logoutTime) {
    playerBo.setLogoutTime(logoutTime);
  }

  public int getLoginCount() {
    return playerBo.getLoginCount();
  }

  public void setLoginCount(int loginCount) {
    playerBo.setLoginCount(loginCount);
  }

  public Map<Integer, Integer> items() {
    return playerBo.items();
  }

  public Map<Integer, PlayerPrizePool> prizePools() {
    return playerBo.prizePools();
  }

  public boolean robot() {
    return playerBo.isRobot();
  }

  public int getIcon() {
    return playerBo.getIcon();
  }

  public void setIcon(int icon) {
    playerBo.setIcon(icon);
  }

  public long getIngot() {
    return playerBo.getIngot().get();
  }



  public long getGold() {
    return playerBo.getGold().get();
  }


  public long getSafeGold() {
    return playerBo.getSafeGold().get();
  }



  public long getWinGold() {
    return playerBo.getWinGold().get();
  }



  public long getCedit() {
    return playerBo.getCedit().get();
  }



  public long getLottery() {
    return playerBo.getLottery().get();
  }


  public long getExp() {
    return playerBo.getExp();
  }

  public void setExp(long exp) {
    playerBo.setExp(exp);
  }

  public String getBankPwd() {
    return playerBo.getBankPwd();
  }

  public void setBankPwd(String bankPwd) {
    playerBo.setBankPwd(bankPwd);
  }

  public int getBirthMonth() {
    return playerBo.getBirthMonth();
  }

  public void setBirthMonth(int birthMonth) {
    playerBo.setBirthMonth(birthMonth);
  }

  public int getBirthDay() {
    return playerBo.getBirthDay();
  }

  public void setBirthDay(int birthDay) {
    playerBo.setBirthDay(birthDay);
  }

  public String getProvince() {
    return playerBo.getProvince();
  }

  public void setProvince(String province) {
    playerBo.setProvince(province);
  }

  public String getCity() {
    return playerBo.getCity();
  }

  public void setCity(String city) {
    playerBo.setCity(city);
  }

  public String getSignature() {
    return playerBo.getSignature();
  }

  public void setSignature(String signature) {
    playerBo.setSignature(signature);
  }

  public String getAddr() {
    return playerBo.getAddr();
  }

  public void setAddr(String addr) {
    playerBo.setAddr(addr);
  }


  public String getReLoginCode() {
    return playerBo.getReLoginCode();
  }

  public void setReLoginCode(String reLoginCode) {
    playerBo.setReLoginCode(reLoginCode);
  }

  public int getAge() {
    return playerBo.getAge();
  }

  public void setAge(int age) {
    playerBo.setAge(age);
  }

  public String getFirstPwdProtectQ() {
    return playerBo.getFirstPwdProtectQ();
  }

  public void setFirstPwdProtectQ(String firstPwdProtectQ) {
    playerBo.setFirstPwdProtectQ(firstPwdProtectQ);
  }

  public String getFirstPwdProtectA() {
    return playerBo.getFirstPwdProtectA();
  }

  public void setFirstPwdProtectA(String firstPwdProtectA) {
    playerBo.setFirstPwdProtectA(firstPwdProtectA);
  }

  public String getSecondPwdProtectQ() {
    return playerBo.getSecondPwdProtectQ();
  }

  public void setSecondPwdProtectQ(String secondPwdProtectQ) {
    playerBo.setSecondPwdProtectQ(secondPwdProtectQ);
  }

  public String getSecondPwdProtectA() {
    return playerBo.getSecondPwdProtectA();
  }

  public void setSecondPwdProtectA(String secondPwdProtectA) {
    playerBo.setSecondPwdProtectA(secondPwdProtectA);
  }

  public boolean isLoginPhoneVerify() {
    return playerBo.isLoginPhoneVerify();
  }

  public void setLoginPhoneVerify(boolean loginPhoneVerify) {
    playerBo.setLoginPhoneVerify(loginPhoneVerify);
  }

  public boolean isPlayItemEffect() {
    return playerBo.isPlayItemEffect();
  }

  public void setPlayItemEffect(boolean playItemEffect) {
    playerBo.setPlayItemEffect(playItemEffect);
  }

  /**
   * 玩家房间成员基本信息,不包含房间和桌子信息.
   * 
   * @return 成员基本信息.
   */
  public MemInfo baseMemInfo() {
    return playerBo.baseMemInfo();
  }

  public boolean isRoomSettingAbled() {
    return playerBo.isRoomSettingAbled();
  }

  public void setRoomSettingAbled(boolean roomSettingAbled) {
    playerBo.setRoomSettingAbled(roomSettingAbled);
  }

  public boolean isTableLimitIp() {
    return playerBo.isTableLimitIp();
  }

  public void setTableLimitIp(boolean tableLimitIp) {
    playerBo.setTableLimitIp(tableLimitIp);
  }

  public String getTablePwd() {
    return playerBo.getTablePwd();
  }

  public void setTablePwd(String tablePwd) {
    playerBo.setTablePwd(tablePwd);
  }

  public boolean isTablePwdAbled() {
    return playerBo.isTablePwdAbled();
  }

  public void setTablePwdAbled(boolean tablePwdAbled) {
    playerBo.setTablePwdAbled(tablePwdAbled);
  }

  public long getTableLimitGold() {
    return playerBo.getTableLimitGold();
  }

  public void setTableLimitGold(long tableLimitGold) {
    playerBo.setTableLimitGold(tableLimitGold);
  }

  public boolean isTableLimitGoldAbled() {
    return playerBo.isTableLimitGoldAbled();
  }

  public void setTableLimitGoldAbled(boolean tableLimitGoldAbled) {
    playerBo.setTableLimitGoldAbled(tableLimitGoldAbled);
  }

  public boolean vip() {
    return playerBo.vip();
  }

  public int vipDuration() {
    return playerBo.vipDuration();
  }

  public LinkedHashSet<Integer> games() {
    return playerBo.games();
  }

  public long getVipEndTime() {
    return playerBo.getVipEndTime();
  }

  public void setVipEndTime(long vipEndTime) {
    playerBo.setVipEndTime(vipEndTime);
  }

  public long getGametime() {
    return playerBo.getGametime();
  }

  public void setGametime(long gametime) {
    playerBo.setGametime(gametime);
  }

  public long getOnlinetime() {
    return playerBo.getOnlinetime();
  }

  public void setOnlinetime(long onlinetime) {
    playerBo.setOnlinetime(onlinetime);
  }

  public boolean isRobot() {
    return playerBo.isRobot();
  }

  public WelfareData welfareData() {
    return playerBo.welfareData();
  }

  public boolean isTourist() {
    return playerBo.isTourist();
  }

  public void setTourist(boolean tourist) {
    playerBo.setTourist(tourist);
  }

  public byte getType() {
    return playerBo.getType();
  }

  public void setType(byte type) {
    playerBo.setType(type);
  }

  public long getTransferReward() {
    return playerBo.getTransferReward();
  }

  public void setTransferReward(long transferReward) {
    playerBo.setTransferReward(transferReward);
  }

  public void initCedit(long cedit) {
    playerBo.getCedit().set(cedit);
  }


  public void initCredit(long cedit) {
    playerBo.getCedit().set(cedit);
  }

  public void initGold(long gold) {
    playerBo.getGold().set(gold);
  }

  public void initIngot(long ingold) {
    playerBo.getIngot().set(ingold);
  }

  public void initLottery(long lottery) {
    playerBo.getLottery().set(lottery);
  }

  public void initWinGold(long winGold) {
    playerBo.getWinGold().set(winGold);
  }

  public String getChannelId() {
    return playerBo.getChannelId();
  }

  public void setChannelId(String channelId) {
    playerBo.setChannelId(channelId);
  }


}
