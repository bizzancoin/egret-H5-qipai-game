package com.idealighter.game.games.baccarat;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.gamehall.dto.SeatInfo;
import com.idealighter.game.gamehall.dto.TableInfo;
import com.idealighter.game.games.baccarat.message.ReqBetMsg;
import com.idealighter.game.games.baccarat.message.ReqEnterGameHallMsg;
import com.idealighter.game.games.baccarat.message.ReqEnterRoomMsg;
import com.idealighter.game.games.baccarat.message.ReqEnterTableMsg;
import com.idealighter.game.games.baccarat.message.ReqExchangeChipsMsg;
import com.idealighter.game.robot.core.Player;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 百家乐机器人 .
 * 
 * @author zsc .
 * @date 2016年3月23日 下午8:15:57
 *
 */
public class BaccaratPlayer extends Player {

  public static final int BET = 1;
  public int state;
  public long totalBet = 0;

  public final BaccaratRoomDomain roomDomain;

  /**
   * 构造函数.
   * 
   * @param playerId 玩家id .
   * @param userName 玩家用户名 .
   * @param roomId 房间id.
   * @param initGold 初始化金币.
   * @param totalGameNum 总 .
   * @param tableGameNum 房间 .
   */
  public BaccaratPlayer(long playerId, String userName, int roomId, long initGold, int totalGameNum,
      int tableGameNum, RobotConfigDomain configDomain, BaccaratRoomDomain roomDomain) {
    super(playerId, userName, roomId, initGold, totalGameNum, tableGameNum, configDomain);
    this.roomDomain = roomDomain;
  }

  @Override
  public boolean goldCheck() {
    long gold = playerInfo.getSafeGold();
    if (gold < roomDomain.getLower()) {
      LOG.info("[百家乐]机器人[{}][{}]金币小于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    if (gold > roomDomain.getUpper()) {
      LOG.info("[百家乐]机器人[{}][{}]金币高于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    if (gold < roomDomain.getAfee()) {
      LOG.info("[百家乐]机器人[{}][{}]金币小于台费退出游戏", userName, playerId);
      delayLogout();
      return false;
    }
    return true;
  }

  @Override
  public void enterGameHall() {
    sendMsg(new ReqEnterGameHallMsg());
  }

  @Override
  public void enterRoom() {
    if (rooms.contains(roomId)) {
      if (!goldCheck()) {
        return;
      }

      ReqEnterRoomMsg msg = new ReqEnterRoomMsg();
      msg.setRoomId(roomId);
      sendMsg(msg);
    }
  }

  @Override
  public void enterTable() {
    if (!goldCheck()) {
      return;
    }
    SeatInfo emptySeat = findEmptySeat();
    if (emptySeat != null) {
      ReqEnterTableMsg msg = new ReqEnterTableMsg();
      msg.setTableId(emptySeat.getTableId());
      sendMsg(msg);
      return;
    }
  }

  private static int[] BET_LIST = new int[] { 0, 1, 2, 4, 9 };

  private static int[] BET_RATE = new int[] { 88, 85, 11, 8, 8 };

  private static int RATE_TOTAL = 200;

  /**
   * 下注 .
   */
  public void dobet() {
    MemInfo memInfo = member(playerId);
    if (memInfo.getChips() <= 0) {
      return;
    }
    /*
     * if (state != BET && stepFuture != null) { stepFuture.cancel(false); return; }
     */
    if (state != BET) {
      return;
    }
    ReqBetMsg msg = new ReqBetMsg();

    int random = RandCodeUtil.random(RATE_TOTAL + 1);

    int total = 0;
    int area = BET_LIST[0];
    for (int i = 0; i < BET_RATE.length; i++) {
      total += BET_RATE[i];
      if (random <= total) {
        area = BET_LIST[i];
        break;
      }
    }

    long bet = bet();
    if (bet == 0 || (totalBet + bet) > memInfo.getChips()) {
      return;
    }
    msg.setArea(area);
    msg.setChips(bet);
    sendMsg(msg);
    totalBet += bet;
  }

  /**
   * 下注筹码.
   *
   * @return .
   */
  private long bet() {
    MemInfo memInfo = member(playerId);
    List<Long> betOptions = new ArrayList<>(this.roomDomain.getBetOptions());
    Long total = 0L;
    for (Long option : this.roomDomain.getBetOptions()) {
      if (option > memInfo.getChips()) {
        betOptions.remove(option);
      } else {
        total += option;
      }
    }

    long chips = 0L;
    if (betOptions.size() > 0) {
      int index = RandCodeUtil.random(betOptions.size());
      chips = betOptions.get(index);
    }

    return chips;
  }

  /**
   * 兑换筹码 .
   *
   * @param player . .
   */
  public void exchangeChips(Player player) {
    ReqExchangeChipsMsg exchangeChipsMsg = new ReqExchangeChipsMsg();
    exchangeChipsMsg.setGold(playerInfo.getSafeGold());
    sendMsg(exchangeChipsMsg);
    LOG.info("[百家乐]玩家[{}][{}]兑换筹码", userName, playerId);
  }

  /**
   * 获取当前桌子 .
   * 
   * @return .
   */
  public TableInfo table() {
    for (TableInfo table : tables.values()) {
      List<SeatInfo> seats = table.getSeats();
      for (SeatInfo s : seats) {
        if (s.getPlayerId() == playerId) {
          return table;
        }
      }
    }

    return null;
  }

  @Override
  public Game game() {
    return Game.BACCARAT;
  }

  @Override
  public boolean enterPwdTableAble() {
    return true;
  }

}
