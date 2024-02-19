package com.idealighter.game.games.bairenniuniu;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.gamehall.dto.SeatInfo;
import com.idealighter.game.gamehall.dto.TableInfo;
import com.idealighter.game.games.bairenniuniu.message.ReqBetMsg;
import com.idealighter.game.games.bairenniuniu.message.ReqEnterGameHallMsg;
import com.idealighter.game.games.bairenniuniu.message.ReqEnterRoomMsg;
import com.idealighter.game.games.bairenniuniu.message.ReqExchangeChipsMsg;
import com.idealighter.game.games.bairenniuniu.message.ReqFastEnterTableMsg;
import com.idealighter.game.games.common.NiuNiuCardsType;
import com.idealighter.game.robot.core.Player;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 百人牛牛机器人.
 *
 */
public class BaiRenNiuNiuPlayer extends Player {

  public static final int BET = 2;
  public int state;
  public long totalBet = 0;
  public long bankerTotalBets = 0;

  public final BairenniuniuRoomDomain roomDomain;

  /**
   * 构造函数.
   * 
   * @param playerId . .
   * @param userName . .
   * @param roomId . .
   * @param initGold . .
   * @param totalGameNum . .
   * @param tableGameNum . .
   */
  public BaiRenNiuNiuPlayer(long playerId, String userName, int roomId, long initGold,
      int totalGameNum, int tableGameNum, RobotConfigDomain configDomain,
      BairenniuniuRoomDomain roomDomain) {
    super(playerId, userName, roomId, initGold, totalGameNum, tableGameNum, configDomain);
    this.roomDomain = roomDomain;
  }

  @Override
  public boolean goldCheck() {
    long gold = playerInfo.getSafeGold();
    if (gold < roomDomain.getLower()) {
      LOG.info("[百人牛牛]机器人[{}][{}]金币小于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    long upper = roomDomain.getUpper();
    if (upper > 0L && gold > upper) {
      LOG.info("[百人牛牛]机器人[{}][{}]金币高于房间下限退出游戏", userName, playerId);
      delayLogout();
      return false;
    }

    if (gold < roomDomain.getAfee()) {
      LOG.info("[百人牛牛]机器人[{}][{}]金币小于台费退出游戏", userName, playerId);
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
      ReqFastEnterTableMsg msg = new ReqFastEnterTableMsg();
      msg.setRoomId(roomId);
      sendMsg(msg);
      return;
    }
  }

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
    int areA = RandCodeUtil.random(1, 4);
    long bet = bet();
    if (bet == 0 || (totalBet + bet) > memInfo.getChips() / 8) {
      return;
    }
    msg.setArea(areA);
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
    int maxMultiple = NiuNiuCardsType.getMaxMultiple();
    // 需要判断庄家分
    MemInfo bankerInfo = null;
    long limitChips = memInfo.getChips() / maxMultiple;
    if (bankerId > 0) {
      bankerInfo = member(bankerId);
      long bankerLimit = bankerInfo.getChips() / maxMultiple - this.bankerTotalBets;
      if (bankerLimit < 0) {
        bankerLimit = 0L;
      }
      if (bankerLimit < limitChips) {
        limitChips = bankerLimit;
      }
    }

    List<Long> betOptions = new ArrayList<>(this.roomDomain.getBetOptions());
    Long total = 0L;
    for (Long option : this.roomDomain.getBetOptions()) {
      if (option > limitChips) {
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

    //
    // for (int i = 0; i < betOptions.size() - 1; i++) {
    // if (chips >= betOptions.get(i) && chips < betOptions.get(i + 1)) {
    // return betOptions.get(i);
    // }
    // }
    return chips;
  }

  /**
   * @param player
   * @Description:兑换筹码
   */
  public void exchangeChips(Player player) {
    ReqExchangeChipsMsg exchangeChipsMsg = new ReqExchangeChipsMsg();
    exchangeChipsMsg.setGold(playerInfo.getSafeGold());
    sendMsg(exchangeChipsMsg);
    LOG.info("[百人牛牛]玩家[{}][{}]兑换筹码", userName, playerId);
  }

  /**
   * 获取当前桌子.
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
    return Game.BAIREN_NIUNIU;
  }

  @Override
  public boolean enterPwdTableAble() {
    return true;
  }

}
