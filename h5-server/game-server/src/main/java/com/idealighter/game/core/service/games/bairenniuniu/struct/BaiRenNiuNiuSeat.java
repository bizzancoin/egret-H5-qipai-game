
package com.idealighter.game.core.service.games.bairenniuniu.struct;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 百人牛牛座位 .
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaiRenNiuNiuSeat extends AbstractSeat {

  // 所属桌子
  private final BaiRenNiuNiuTable table;

  // 是否是庄家
  private boolean dealer = false;

  // 当前下注的筹码
  private long betedchips = 0;
  // 当前结算筹码
  private long billChips = 0;

  private final Map<Integer, Long> areaBill = new HashMap<>();

  private final Map<Integer, Long> bets = new HashMap<>();

  // 玩家的牌数据
  private final List<NiuNiuCard> cards = new ArrayList<>();
  // 玩家的牌最优牌(3+2)组合
  private final List<NiuNiuCard> bestCards = new ArrayList<>();
  // 当前牌型
  private NiuNiuCardsType cardsType = null;

  // 手牌大小
  private int power = 0;

  private boolean win;

  private int bankerNum;

  private long bankerScore;
  
  private long betting = 0;
  
  private long bonus = 0;

  /**
   * 百人位置.
   * 
   * @param order .
   * @param table .
   */
  public BaiRenNiuNiuSeat(int order, BaiRenNiuNiuTable table) {
    super(order);
    this.table = table;
  }

  public int getPower() {
    return power;
  }

  /**
   * 合计所有图标押注.
   * 
   * @return
   */
  public long totalBets() {
    long totalBets = 0;
    for (Long bet : bets.values()) {
      totalBets += bet;
    }

    return totalBets;
  }

  /**
   * 某个区域的下注 .
   * 
   * @param area .
   * @return
   */
  public long totalAreaBets(int area) {
    long totalBets = 0;
    for (Entry<Integer, Long> bet : bets.entrySet()) {
      if (bet.getKey() == area) {
        totalBets += bet.getValue();
      }
    }
    return totalBets;
  }

  /**
   * 重置座位数据 .
   */
  public void reset() {
    this.dealer = false;
    this.bets.clear();
    this.betedchips = 0;
    this.billChips = 0;
    this.totalChips = 0;
    this.cards.clear();
    this.bestCards.clear();
    this.cardsType = null;
    this.state = SeatState.SEATED;
    this.power = 0;
    this.win = false;
    this.bankerNum = 0;
    this.bankerScore = 0;    
    this.betting = 0;    
    this.bonus = 0;
  }

  /**
   * 重置每次的游戏数据数据 .
   */
  public void resetGameData() {
    this.bets.clear();
    this.billChips = 0;
    
    this.betting = 0;    
    this.bonus = 0;
  }

  /**
   * 重置座位数据除了totalChips .
   */
  public void resetButTotalChips() {
    long totalChips = this.totalChips;
    reset();
    this.totalChips = totalChips;
  }

  /**
   * 清空座位数据 .
   */
  public void clear() {
    this.playerId = 0;
    this.playerName = null;
    reset();

    if (table.players().size() <= 1) {
      table.reset();
    }
  }

  @Override
  public AbstractTable table() {
    return table;
  }
}
