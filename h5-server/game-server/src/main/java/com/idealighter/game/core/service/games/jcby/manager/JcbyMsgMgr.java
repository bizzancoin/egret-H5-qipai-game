
package com.idealighter.game.core.service.games.jcby.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.jcby.struct.JcbyFish;
import com.idealighter.game.core.service.games.jcby.struct.JcbyRoom;
import com.idealighter.game.core.service.games.jcby.struct.JcbyScence;
import com.idealighter.game.core.service.games.jcby.struct.JcbySeat;
import com.idealighter.game.core.service.games.jcby.struct.JcbyTable;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.JcbyBatteryDic;
import com.idealighter.game.dictionary.dic.JcbyRoomTypeDic;
import com.idealighter.game.dictionary.domain.JcbyBatteryDomain;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.dto.FishDieInfo;
import com.idealighter.game.games.jcby.dto.GameInfo;
import com.idealighter.game.games.jcby.dto.GameInfo.LockInfo;
import com.idealighter.game.games.jcby.dto.RoomInfo;
import com.idealighter.game.games.jcby.dto.RoomTypeDetailInfo;
import com.idealighter.game.games.jcby.message.ResBatteryChangeMsg;
import com.idealighter.game.games.jcby.message.ResCancelLockMsg;
import com.idealighter.game.games.jcby.message.ResChipsChangeMsg;
import com.idealighter.game.games.jcby.message.ResEnterGameHallMsg;
import com.idealighter.game.games.jcby.message.ResEnterRoomMsg;
import com.idealighter.game.games.jcby.message.ResEnterTableMsg;
import com.idealighter.game.games.jcby.message.ResExitTableMsg;
import com.idealighter.game.games.jcby.message.ResFastEnterMsg;
import com.idealighter.game.games.jcby.message.ResFireMsg;
import com.idealighter.game.games.jcby.message.ResGameInfoMsg;
import com.idealighter.game.games.jcby.message.ResHitMsg;
import com.idealighter.game.games.jcby.message.ResInsteadPlayersUpgradeMsg;
import com.idealighter.game.games.jcby.message.ResLikuiUpgradeMsg;
import com.idealighter.game.games.jcby.message.ResLockMsg;
import com.idealighter.game.games.jcby.message.ResOtherEnterTableMsg;
import com.idealighter.game.games.jcby.message.ResProduceFishMsg;
import com.idealighter.game.games.jcby.message.ResScenceMsg;
import com.idealighter.game.games.jcby.message.ResSwitchSceneMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 金蝉捕鱼.
 * 
 * @date 2016年3月7日 下午9:19:01
 *
 */
@Singleton
public class JcbyMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;
  @Inject
  private JcbyDataMgr dataMgr;
  @Inject
  private JcbyRoomTypeDic roomTypeDic;
  @Inject
  private JcbyBatteryDic batteryDic;

  /**
   * 进入游戏大厅消息.
   */
  public ResMessage sendEnterGameHallMsg() {
    ResEnterGameHallMsg msg = new ResEnterGameHallMsg();

    Collection<JcbyRoom> rooms = dataMgr.newestRooms();
    for (JcbyRoom room : rooms) {
      JcbyRoomDomain roomDom = room.getRoomDomain();

      if (roomDom.getIsActive() != RoomActiveConstant.ACTIVE) {
        continue;
      }
      // if (player.fromPhone && roomDom.getPhone() != 1) {
      // continue;
      // }
      //
      // /*
      // * if . (!player.fromPhone && roomDom.getPhone() == 1) { continue; }
      // */
      // // 机器人也可以进入手机房间
      // if (!player.fromPhone && !player.robot() && roomDom.getPhone() == 1) {
      // continue;
      // }

      RoomTypeDetailInfo roomTypeDetailInfo = new RoomTypeDetailInfo();

      roomTypeDetailInfo.setType(roomDom.getType());
      roomTypeDetailInfo.setTypeName(roomTypeDic.map().get(roomDom.getType()).getName());

      RoomInfo roomInfo = new RoomInfo();
      roomInfo.setRoomId(room.getId());
      roomInfo.setName(roomDom.getName());
      roomInfo.setType(roomDom.getType());
      roomInfo.setMaxNum(roomDom.getMaxNum());
      roomInfo.setLower(roomDom.getLower());
      roomInfo.setUpper(roomDom.getUpper());
      roomInfo.setProportionGold(roomDom.getProportionGold());
      roomInfo.setProportionChips(roomDom.getProportionChips());
      roomInfo.setTabble(roomDom.getTableNum());
      roomInfo.setAfee(roomDom.getAfee());
      roomInfo.setInType(roomDom.getInType());
      roomInfo.setPlayerNum(room.getPlayers().size());
      roomTypeDetailInfo.getRooms().add(roomInfo);

      msg.getRoomTypes().add(roomTypeDetailInfo);
    }

    if (EmptyUtil.listIsNotEmpty(msg.getRoomTypes())) {
      msg.getRoomTypes().sort(new Comparator<RoomTypeDetailInfo>() {

        @Override
        public int compare(RoomTypeDetailInfo o1, RoomTypeDetailInfo o2) {
          return o1.getType() - o2.getType();
        }
      });
    }

    return msg;
  }

  /**
   * 发送游戏最近信息.
   * 
   * @Title sendGameLastInfo.
   * @author houdongsheng
   * @date 2018年3月12日 下午5:50:34
   * @param seat 座位
   * @return ResMessage
   */
  public ResMessage sendGameLastInfo(JcbySeat seat) {
    ResGameInfoMsg msg = new ResGameInfoMsg();
    AbstractRoom room = seat.getTable().room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : seat.getTable().players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setRoom(toRoomInfo(seat.getTable().getRoom()));
    msg.setGameInfo(toGameInfo(seat));
    return msg;
  }

  private RoomInfo toRoomInfo(JcbyRoom room) {
    RoomInfo roomInfo = new RoomInfo();
    JcbyRoomDomain roomDom = room.getRoomDomain();
    roomInfo.setRoomId(room.getId());
    roomInfo.setName(roomDom.getName());
    roomInfo.setType(roomDom.getType());
    roomInfo.setMaxNum(roomDom.getMaxNum());
    roomInfo.setLower(roomDom.getLower());
    roomInfo.setUpper(roomDom.getUpper());
    roomInfo.setProportionGold(roomDom.getProportionGold());
    roomInfo.setProportionChips(roomDom.getProportionChips());
    roomInfo.setTabble(roomDom.getTableNum());
    roomInfo.setAfee(roomDom.getAfee());
    roomInfo.setInType(roomDom.getInType());
    roomInfo.setPlayerNum(room.getPlayers().size());

    return roomInfo;
  }

  private GameInfo toGameInfo(JcbySeat seat) {
    GameInfo gameInfo = new GameInfo();
    List<LockInfo> lockInfos = new ArrayList<>();
    JcbyTable table = seat.getTable();
    for (JcbySeat s : table.getSeats()) {
      LockInfo lock = new LockInfo();
      lock.setPlayerId(s.getPlayerId());
      lock.setLockFishId(s.getLockFish());
      lock.setLockAngle(s.getLockAngle());
      lockInfos.add(lock);
    }
    gameInfo.setLockInfos(lockInfos);
    return gameInfo;
  }

  /**
   * 发送进入桌子消息 .
   * 
   * @param room .
   */
  public ResMessage sendEnterRoomMsg(JcbyRoom room) {
    ResEnterRoomMsg msg = new ResEnterRoomMsg();
    for (AbstractTable table : room.tables()) {
      msg.getTables().add(table.tableInfo());
    }
    msg.setMembers(room.memInfos());
    return msg;
  }


  /**
   * 发送兑换筹码消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendChipsChangeMsg(JcbySeat seat) {
    ResChipsChangeMsg msg = new ResChipsChangeMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setChips(seat.getTotalChips());
    msg.setOrder(seat.getOrder());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 快速进入 .
   *
   * @author abin
   * @date 2018年9月10日 下午5:58:15
   * @param table 桌子.
   * @param totalChips 总筹码.
   * @return 消息.
   */
  public ResMessage sendFastEnterMsg(JcbyTable table, long totalChips) {
    ResFastEnterMsg msg = new ResFastEnterMsg();
    AbstractRoom room = table.room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : table.players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setTotalChips(totalChips);
    return msg;
  }

  /**
   * 发送进入桌子消息 .
   * 
   * @param table .
   */
  public ResMessage sendEnterTableMsg(JcbyTable table, long totalChips) {
    ResEnterTableMsg msg = new ResEnterTableMsg();
    AbstractRoom room = table.room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : table.players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setTotalChips(totalChips);
    return msg;
  }

  /**
   * 发送场景数据消息 .
   * 
   * @param player 玩家.
   * @param table .
   */
  public ResMessage sendScenceMsg(Player player, JcbyTable table) {
    JcbyScence scence = table.scence();
    ResScenceMsg msg = new ResScenceMsg();
    msg.setScenceId(table.scence().getId());
    for (JcbyFish fish : scence.getFishs().values()) {
      if (fish.alive()) {
        msg.getFishs().add(fish.finshInfo());
      }
    }

    for (JcbySeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0) {
        msg.getBatterys().add(batteryInfo(seat));
      }
    }

    if (table.getLeader() == player.getId()) { // 队长代发机器人碰撞
      List<Long> insteadPlayers = msg.getInsteadPlayers();
      for (JcbySeat s : table.getSeats()) {
        if (s.isRobot() && s.getPlayerId() > 0) {
          insteadPlayers.add(s.getPlayerId());
        }
      }
    }
    return msg;
  }

  /**
   * 发送代码碰撞玩家列表更新消息 .
   * 
   * @param table .
   */
  public void sendInsteadPlayersUpgradeMsg(JcbyTable table) {
    ResInsteadPlayersUpgradeMsg msg = new ResInsteadPlayersUpgradeMsg();
    List<Long> insteadPlayers = msg.getInsteadPlayers();
    for (JcbySeat s : table.getSeats()) {
      if (s.isRobot() && s.getPlayerId() > 0) {
        insteadPlayers.add(s.getPlayerId());
      }
    }

    msgWriter.writeMsg(table.getLeader(), msg);
  }

  /**
   * 发送其他人进入桌子消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendOtherEnterTableMsg(JcbySeat seat) {
    ResOtherEnterTableMsg msg = new ResOtherEnterTableMsg();
    msg.setBattery(batteryInfo(seat));
    msg.setMem(seat.getTable().getRoom().memInfo(seat.getPlayerId()));
    for (Long playerId : seat.getTable().players()) {
      if (playerId != seat.getPlayerId()) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }


  /**
   * 发送退出桌子消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendExitTableMsg(JcbySeat seat) {
    ResExitTableMsg msg = new ResExitTableMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送开炮结果消息 .
   * 
   * @param seat 座位信息.
   * @param bulletId .
   * @param angle .
   */
  public void sendFireMsg(JcbySeat seat, long bulletId, int angle) {
    ResFireMsg msg = new ResFireMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    msg.setAngle(angle);
    msg.setBulletId(bulletId);

    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送切换炮台消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendBatteryChangeMsg(JcbySeat seat) {
    ResBatteryChangeMsg msg = new ResBatteryChangeMsg();
    msg.setBattery(batteryInfo(seat));

    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 炮台信息 .
   * 
   * @param seat 座位信息.
   * @return
   */
  private BatteryInfo batteryInfo(JcbySeat seat) {
    JcbyBatteryDomain batteryDom = batteryDic.get(seat.getBatteryId());
    BatteryInfo batteryInfo = new BatteryInfo();
    batteryInfo.setNum(batteryDom.getNum());
    batteryInfo.setScore(batteryDom.getScore());
    batteryInfo.setOrder(seat.getOrder());
    batteryInfo.setPlayerId(seat.getPlayerId());
    batteryInfo.setPower(seat.isPower() ? 1 : 0);

    return batteryInfo;
  }

  /**
   * 发送切换场景 .
   * 
   * @param table .
   * @param scenceIndex .
   */
  public void sendSwitchSceneMsg(JcbyTable table, int scenceIndex) {
    ResSwitchSceneMsg msg = new ResSwitchSceneMsg();
    msg.setScene(table.getScences().get(scenceIndex).getId());
    sendTablePlayersMsg(table, msg);
  }

  /**
   * 发送子弹打中消息 .
   * 
   * @param seat 座位信息.
   * @param fishScores . 被打死的鱼分数(key：鱼id,val:鱼的分数)
   */
  public void sendHitsMsg(JcbySeat seat, Map<Integer, Integer> fishScores) {
    ResHitMsg msg = new ResHitMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setOrder(seat.getOrder());
    for (Entry<Integer, Integer> diedFishScore : fishScores.entrySet()) {
      FishDieInfo dieInfo = new FishDieInfo();
      dieInfo.setFishId(diedFishScore.getKey());
      dieInfo.setScore(diedFishScore.getValue());
      msg.getDies().add(dieInfo);
    }

    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送锁定鱼 .
   * 
   * @param seat 座位信息.
   * @param fishId .
   */
  public void sendLockMsg(JcbySeat seat, int fishId, int angle) {
    ResLockMsg msg = new ResLockMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setOrder(seat.getOrder());
    msg.setFishId(fishId);
    msg.setAngle(angle);
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送取消锁定消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendCancelLockMsg(JcbySeat seat) {
    ResCancelLockMsg msg = new ResCancelLockMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setOrder(seat.getOrder());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送产生鱼消息 .
   * 
   * @param table .
   */
  public void sendProduceFishMsg(JcbyTable table, List<JcbyFish> fishs) {
    ResProduceFishMsg msg = new ResProduceFishMsg();
    for (JcbyFish fish : fishs) {
      msg.getFishs().add(fish.finshInfo());
    }

    sendTablePlayersMsg(table, msg);
  }

  /**
   * 发送李逵升级消息 .
   * 
   * @param room .
   * @param likuiRate .
   */
  public void sendLikuiUpgradeMsg(JcbyRoom room, int likuiRate) {
    ResLikuiUpgradeMsg msg = new ResLikuiUpgradeMsg();
    msg.setRate(likuiRate);
    msg.setFishId(room.getLikui());

    sendRoomPlayersMsg(room, msg);
  }

  /**
   * 单个玩家发送李逵升级消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendLikuiUpgradeMsg(JcbySeat seat) {
    JcbyRoom room = seat.getTable().getRoom();
    ResLikuiUpgradeMsg msg = new ResLikuiUpgradeMsg();
    msg.setRate(room.likuiMultiple());
    msg.setFishId(room.getLikui());
    msgWriter.writeMsg(seat.getPlayerId(), msg);
  }

  /**
   * 向同桌的玩家(包括自己)发送消息 .
   * 
   * @param table .
   * @param msg .
   */
  public void sendTablePlayersMsg(JcbyTable table, ResMessage msg) {
    List<? extends AbstractSeat> seats = table.seats();
    for (int i = 0; i < seats.size(); i++) {
      AbstractSeat seat = seats.get(i);
      long playerId = seat.getPlayerId();
      if (playerId > 0) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }

  /**
   * 向同房间的玩家(包括自己)发送消息 .
   * 
   * @param room .
   * @param msg .
   */
  public void sendRoomPlayersMsg(JcbyRoom room, ResMessage msg) {
    for (Long p : room.getPlayers()) {
      msgWriter.writeMsg(p, msg);
    }
  }
}
