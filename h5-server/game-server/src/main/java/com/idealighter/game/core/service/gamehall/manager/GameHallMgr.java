
package com.idealighter.game.core.service.gamehall.manager;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.ShutdownGameEvent;
import com.idealighter.game.core.service.event.struct.StartGameEvent;
import com.idealighter.game.core.service.gamehall.event.EnterRoomEvent;
import com.idealighter.game.core.service.gamehall.event.EnterTableEvent;
import com.idealighter.game.core.service.gamehall.event.ExitRoomEvent;
import com.idealighter.game.core.service.gamehall.event.ExitTableEvent;
import com.idealighter.game.core.service.gamehall.event.RoomMemInfoUpdateEvent;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.log.struct.game.EnterRoomLog;
import com.idealighter.game.core.service.log.struct.game.EnterTableLog;
import com.idealighter.game.core.service.log.struct.game.ExitRoomLog;
import com.idealighter.game.core.service.log.struct.game.ExitTableLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.util.PwdEncode;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;

import java.util.LinkedHashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏大厅管理 .
 * 
 * @date 2015年11月17日 下午3:52:42
 *
 */
@Singleton
public class GameHallMgr {

  private static final Logger LOG = LoggerFactory.getLogger(GameHallMgr.class);

  @Inject
  private GameHallMsgMgr msgMgr;
  @Inject
  private CommonMsgMgr commonMsgMgr;
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private LoginMgr loginMgr;

  @Inject
  private GamesDic gamesDic;

  private final String defaultSmsCode;

  /**
   * 构造函数.
   * 
   * @param eventMgr .
   * @param defaultSmsCode .
   * @param gamesDic .
   */
  @Inject
  public GameHallMgr(EventMgr eventMgr, @Named("smscode.default") String defaultSmsCode,
      GamesDic gamesDic) {
    this.defaultSmsCode = defaultSmsCode;
    this.gamesDic = gamesDic;
    eventMgr.register(this);
  }

  /**
   * 房间大厅玩家进入房间事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onEnterRoom(EnterRoomEvent event) {
    Player player = event.player;
    AbstractRoom room = event.room;
    if (player == null || room == null) {
      return;
    }
    player.enterRoomTime = System.currentTimeMillis();
    msgMgr.noticeMemInfoUpdateMsg(room, player.getId());
    // 重置房间vip设置有效标识
    player.setRoomSettingAbled(false);

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new EnterRoomLog(player, room));
    }

  }

  /**
   * 玩家退出游戏房间事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onExitRoom(ExitRoomEvent event) {
    Player player = event.player;
    AbstractRoom room = event.room;
    if (player == null || room == null) {
      return;
    }

    msgMgr.noticeRemoveMemInfo(player.getId(), room);
    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new ExitRoomLog(player, room));
    }
  }

  /**
   * 玩家进入桌子事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onEnterTable(EnterTableEvent event) {
    Player player = event.player;
    AbstractSeat seat = event.seat;
    AbstractTable table = seat.table();
    final AbstractRoom room = table.room();
    if (player == null) {
      return;
    }

    player.curTable = table;
    player.enterTableTime = System.currentTimeMillis();
    msgMgr.noticeMemInfoUpdateMsg(room, player.getId());

    // 加入玩家进入过的游戏,并实时通知
    int gameType = room.game().getType();
    LinkedHashSet<Integer> games = player.games();
    if (games.contains(gameType)) {
      games.remove(gameType);
    }
    games.add(gameType);

    // 当前房间配置有效且是vip第一个进入房间时修改桌子配置
    if (player.isRoomSettingAbled() && player.vip() && table.players().size() == 1) {
      if (player.isTableLimitGoldAbled()) {
        table.setLimitGold(player.getTableLimitGold());
      }

      if (player.isTableLimitIp()) {
        table.setLimitIp(player.ip);
      }

    }
    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new EnterTableLog(player, table));
    }
  }

  /**
   * 玩家退出桌子事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onExitTable(ExitTableEvent event) {
    Player player = event.player;
    AbstractSeat seat = event.seat;
    AbstractRoom room = seat.table().room();
    msgMgr.noticeSeatInfoUpdateMsg(seat);

    AbstractTable table = seat.table();
    // 所有人退出后，重置房间设置
    if (table.players().size() == 0) {
    }

    if (player != null) {
      msgMgr.noticeMemInfoUpdateMsg(room, player.getId());
      if (player.enterTableTime > 0) {
        player
            .setGametime(player.getGametime() + System.currentTimeMillis() - player.enterTableTime);
      }

      // 机器人不加log
      if (!player.isRobot()) {
        DbLogService.log(new ExitTableLog(player, table));
      }
    }
  }

  /**
   * .
   * 
   * @param event .
   */
  @Subscribe
  public void onRoomMemInfoUpdate(RoomMemInfoUpdateEvent event) {
    Player player = event.player;
    if (player != null) {
      msgMgr.noticeMemInfoUpdateMsg(player.curRoom, player.getId());
    }
  }

  @Subscribe
  public void onGameStart(StartGameEvent event) {
    msgMgr.noticeGameStart(event.getGame());
  }

  @Subscribe
  public void onGameClose(ShutdownGameEvent event) {
    msgMgr.noticeGameClose(event.getGame());
  }

  /**
   * 玩家进桌前的房间设置校验(密码、ip限制、金币限制) .
   * 
   * @param player 玩家.
   * @param table .
   * @param tablePwd .
   * @return
   */
  public boolean checkTableSetting(Player player, AbstractTable table, String tablePwd) {
    if (table.getPassword() != null && !table.getPassword().equals(tablePwd)) {
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.GAME_ROOM_PWD_ERROR, Operator.SYSTEM);
      return false;
    }

    if (table.getLimitIp() != null && table.getLimitIp().equals(player.ip)) {
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.GAME_NOT_PLAY_WITH_IP, Operator.SYSTEM);
      return false;
    }

    if (player.getGold() < table.getLimitGold()) {
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.GAME_NOT_PLAY_WITH_MIN_GOLD, Operator.SYSTEM,
          "" + table.getLimitGold());
      return false;
    }

    return true;
  }

  /**
   * 游客账号升级 .
   * 
   * @param player 玩家.
   * @param smsCode 手机验证码.
   * @param playerName 玩家昵称.
   * @param recommend 推荐人编号.
   */
  public ResMessage touristUpdate(Player player, String smsCode, String playerName,
      long recommend) {

    HuohuaAssert.isTrue(EmptyUtil.stringIsNotEmpty(player.tmpBindPhone));

    if (!player.isTourist()
        || (player.getPhone() != null && player.getPhone().trim().length() > 0)) {
      LOG.error("玩家[{}][{}]已经是正式账号不能升级", player.getPlayerName(), player.getId());

      HuohuaAssert.isTrue(false, ErrorCode.NOT_TOURIST_COULD_NOT_UPDATE);
      // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.NOT_TOURIST_COULD_NOT_UPDATE,
      // Operator.SYSTEM);
      // return;
    }

    if (!smsCode.equals(player.bindPhoneKey) && !defaultSmsCode.equals(smsCode)) {
      LOG.error("短信验证码[{}:{}]不正确,正确的验证码为[{}:{}]", player.tmpBindPhone, smsCode, player.tmpBindPhone,
          player.bindPhoneKey);
      // commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.SMS_CODE_ERROR, Operator.SYSTEM);
      HuohuaAssert.isTrue(false, ErrorCode.SMS_CODE_ERROR);

    }

    // 校验用户存在否
    PlayerBo palyerDomain = playerMgr.selectPlayerByPhone(player.tmpBindPhone);
    if (palyerDomain != null) {
      LOG.error("手机[{}]已经绑定", player.tmpBindPhone);
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.PHONE_HAD_BIND, Operator.SYSTEM);

      HuohuaAssert.isTrue(false, ErrorCode.PHONE_HAD_BIND);
    }

    HuohuaAssert.isTrue(loginMgr.checkPlayerName(player, playerName));



    player.setUserName(player.tmpBindPhone); // 账号设置为手机号码
    player.setTourist(false);
    player.setValidatePhone(true);
    player.setPlayerName(playerName);
    player.setRecommendId(recommend);
    player.setPassword(PwdEncode.encodeWithTail(""));
    player.setPhone(player.tmpBindPhone); // 手机号码
    player.tmpBindPhone = null;
    player.bindPhoneKey = null;
    // 异步更新玩家数据
    playerMgr.asyncUpdatePlayer(player);

    LOG.error("玩家[{}][{}]游客账号成功升级为正式账号", player.getPlayerName(), player.getId());
    // 发送升级账号结果消息
    return msgMgr.sendTouristUpdateMsg(player);
  }

  public ResMessage getGameList() {
    List<GamesDomain> gamesDomains = gamesDic.list();
    return msgMgr.resGameList(gamesDomains);
  }


}
