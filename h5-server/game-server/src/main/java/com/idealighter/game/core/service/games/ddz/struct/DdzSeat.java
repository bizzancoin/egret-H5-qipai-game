package com.idealighter.game.core.service.games.ddz.struct;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.games.ddz.dto.SeatInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 斗地主位置.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DdzSeat extends AbstractSeat {
  // 所属桌子
  private DdzTable table;
  // // 是否准备好游戏(客户端加载资源会请求准备完成)
  // private boolean ready = false;
  // 牌数据
  private final EnumSet<DdzCard> cards = EnumSet.noneOf(DdzCard.class);
  // 叫牌类型
  private DdzCallType callType = null;
  // 是否地主
  private boolean landlord = false;
  // 出牌次数
  private int playNum = 0;
  // 上一次给玩家提示出的牌
  private List<DdzCard> prePromptCards = null;
  // 上一次给玩家提示出的牌类型
  private DdzCardsType prePromptCardsType = null;
  // 玩家是否加倍
  private Boolean doubled = null;
  // 当前结算筹码
  private long billChips = 0;
  // 步骤(准备、叫地主、加倍、出牌)定时Future
  public ScheduledFuture<?> stepFuture = null;
  // 系统托管
  private boolean sysHost = false;
  private boolean loaded = false;
  // 是否体验游戏
  private boolean playedGame = false;

  /**
   * 构造函数.
   * 
   * @param order .
   * @param table .
   */
  DdzSeat(int order, DdzTable table) {
    super(order);
    this.table = table;
  }

  /**
   * 创建真实玩家座位 .
   * 
   * @param player 玩家.
   * @param order .
   * @param table .
   * @return .
   */
  /*
   * public static DouDiZhuSeat createPlayerSeat(Player player, int applyOrder) { DouDiZhuSeat seat
   * = new DouDiZhuSeat(0, applyOrder, player.getIcon(), null); seat.playerId = player.getId();
   * seat.playerName . = player.getPlayerName(); seat.sex = player.getSex().equals("男")?(byte)0 :
   * (byte)1; seat.robot = false; seat.state = SeatState.SEATED;
   * 
   * return seat; }
   */

  /**
   * 创建机器人座位,机器人的playerId为0 .
   * 
   * @param playerName .
   * @param order .
   * @param table .
   * @return .
   */
  /*
   * public . static DouDiZhuSeat createRobotSeat(long playerId, String playerName, int icon, int
   * applyOrder) . { DouDiZhuSeat seat = new DouDiZhuSeat(0, applyOrder, icon, null); seat.playerId
   * = playerId; seat.playerName = playerName; seat.sex = RandCodeUtil.randomBoolean()?(byte)0 :
   * (byte)1; seat.robot = true; seat.state = SeatState.SEATED; seat.ready = true;
   * 
   * return . seat; }
   */

  /**
   * 获取SeatInfo .
   * 
   * @return .
   */
  public SeatInfo getSeatInfo() {
    SeatInfo seatInfo = new SeatInfo();
    seatInfo.setOrder(order);
    seatInfo.setPlayerId(playerId);
    seatInfo.setPlayerName(playerName);
    seatInfo.setTableId(table.getId());
    seatInfo.setSex(sex);
    seatInfo.setChips(totalChips);
    seatInfo.setOffline(sysHost ? 1 : 0);
    return seatInfo;
  }

  /**
   * 重置座位数据 .
   */
  public void reset() {
    this.state = SeatState.SEATED;
    this.prePromptCards = null;
    this.prePromptCardsType = null;
    this.callType = null;
    this.landlord = false;
    this.playNum = 0;
    this.doubled = null;
    this.billChips = 0;
    this.sysHost = false;
    this.loaded = false;
    this.cards.clear();
    if (this.stepFuture != null && !(this.stepFuture.isCancelled() || this.stepFuture.isDone())) {
      this.stepFuture.cancel(false);
      this.stepFuture = null;
    }
  }

  /**
   * 清空座位数据 .
   */
  public void clear() {
    this.playerId = 0;
    this.playerName = null;
    this.totalChips = 0;
    this.playedGame = false;
    reset();
  }

  @Override
  public DdzTable table() {
    return table;
  }

}
