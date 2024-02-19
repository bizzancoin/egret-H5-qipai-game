package com.idealighter.game.core.service.games.ddz.struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.ddz.manager.DdzMgr;
import com.idealighter.game.games.ddz.constant.DdzStage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 斗地主桌子.
 * 
 * @date 2016年2月24日 上午11:36:53
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DdzTable extends AbstractTable {
  // 座位数量
  private static final int SEAT_NUM = 3;
  // 游戏阶段
  private DdzStage status = DdzStage.NO_START;
  // 所属房间
  private final DdzRoom room;
  // 牌桌的座位
  private final List<DdzAiSeat> seats;
  // 一副牌
  private final List<DdzCard> cards = Arrays.asList(DdzCard.values());
  // 下一个应该叫牌玩家座位
  private DdzAiSeat nextCallSeat = null;
  // 下一个应该出牌的座位
  private DdzAiSeat nextPlaySeat = null;
  // 上一次出牌的座位
  private DdzAiSeat prePlaySeat = null;
  // 上一次玩家出的牌
  private List<DdzCard> prePlayCards = null;
  // 上一次玩家出的牌的类型
  private DdzCardsType prePlayCardsType = null;
  // 游戏的倍数
  private int multiple = 1;
  // 游戏的底分
  private long base = 0;
  // 是否游戏中
  private boolean gaming = false;
  // 春天/反春天
  private int spring = 0; // -1:反春天,0:普通,1:春天
  // 住流程定时器
  public ScheduledFuture<?> stepFuture = null;

  /**
   * 构造函数.
   * 
   * @param id .
   * @param room .
   */
  public DdzTable(int id, DdzRoom room) {
    super(id);
    this.room = room;
    this.base = room.getRoomDomain().getBase();
    this.seats = createSeats();
  }

  /**
   * @Title gamingSeats.
   * @Description 游戏中的座位
   * @author houdongsheng
   * @date 2018年7月21日 上午10:32:33
   * @return .
   */
  public List<DdzAiSeat> gamingSeats() {
    List<DdzAiSeat> result = new ArrayList<>();
    for (DdzAiSeat item : seats) {
      if (item.getPlayerId() > 0) {
        result.add(item);
      }
    }
    return result;
  }

  /**
   * @Title gamingPlayerSeats.
   * @Description 游戏中的真实玩家
   * @author houdongsheng
   * @date 2018年7月21日 上午10:33:02
   * @return .
   */
  public List<DdzAiSeat> gamingPlayerSeats() {
    List<DdzAiSeat> result = new ArrayList<>();
    for (DdzAiSeat item : seats) {
      if (item.getPlayerId() > 0 && !item.isRobot()) {
        result.add(item);
      }
    }
    return result;
  }

  /**
   * @Title gamingRobotSeats.
   * @Description 游戏中的机器人
   * @author houdongsheng
   * @date 2018年7月21日 上午10:33:50
   * @return .
   */
  public List<DdzAiSeat> gamingRobotSeats() {
    List<DdzAiSeat> result = new ArrayList<>();
    for (DdzAiSeat item : seats) {
      if (item.getPlayerId() > 0 && item.isRobot()) {
        result.add(item);
      }
    }
    return result;
  }

  /**
   * 创建位置.
   * 
   * @return .
   */
  private List<DdzAiSeat> createSeats() {
    List<DdzAiSeat> seats = new ArrayList<>(SEAT_NUM);
    for (int i = 0; i < SEAT_NUM; i++) {
      seats.add(new DdzAiSeat(i, this));
    }

    return Collections.unmodifiableList(seats);
  }


  /**
   * 下一个位置order .
   * 
   * @param seat 座位信息.
   * @return .
   */
  public int nextSeatOrder(DdzAiSeat seat) {

    return (seat.getOrder() + 1) % seats.size();
  }

  /**
   * 下一个位置 .
   * 
   * @param seat 座位信息.
   * @return .
   */
  public DdzAiSeat nextSeat(DdzAiSeat seat) {
    return seats.get(nextSeatOrder(seat));
  }

  /**
   * 上一个位置 .
   * 
   * @param seat 座位信息.
   * @return .
   */
  public DdzAiSeat preSeat(DdzAiSeat seat) {
    return seats.get((seat.getOrder() + 2) % seats.size());
  }

  /**
   * 获取牌桌的地主位置.
   * 
   * @return .
   */
  public DdzAiSeat landlord() {
    for (DdzAiSeat seat : this.gamingSeats()) {
      if (seat.isLandlord()) {
        return seat;
      }
    }

    return null;
  }

  /**
   * 是否所有人都没有叫地主.
   * 
   * @return .
   */
  public boolean allNotCallLandlord() {
    // 是否所有人都没有叫牌
    boolean allNotCallCard = true;
    for (DdzAiSeat s : seats) {
      if (s.getCallType() == null || !s.getCallType().equals(DdzCallType.NOT_CALL_LANDLORD)) {
        allNotCallCard = false;
        break;
      }
    }

    return allNotCallCard;
  }

  /**
   * 下一个应该叫牌的seat .
   * 
   * @param seat 座位信息.
   * @return null:叫牌结束 .
   */
  public DdzAiSeat nextCallCardSeat(DdzAiSeat seat) {
    DdzAiSeat referSeat = seat;
    for (int i = 0; i < 2; i++) {
      DdzAiSeat preSeat = preSeat(referSeat);
      DdzAiSeat nextSeat = nextSeat(referSeat);

      if (nextSeat.getCallType() == null || nextSeat.getCallType().equals(null)) {
        return nextSeat;
      } else if (nextSeat.getCallType().equals(DdzCallType.CALL_LANDLORD)) {
        if ((preSeat.getCallType().equals(DdzCallType.NOT_CALL_LANDLORD)
            && seat.getCallType().equals(DdzCallType.NOT_CALL_LANDLORD))
            || (preSeat.getCallType().equals(DdzCallType.NOT_GRAB_LANDLORD)
                && seat.getCallType().equals(DdzCallType.NOT_GRAB_LANDLORD))) {
          return null;
        } else {
          return nextSeat;
        }
      }

      referSeat = nextSeat;
    }

    return null;
  }

  /**
   * 是否加倍结束 .
   * 
   * @return .
   */
  public boolean doubledOver() {
    for (DdzAiSeat seat : seats) {
      if (seat.getDoubled() == null) {
        return false;
      }
    }

    return true;
  }

  /**
   * 是否准备结束,人数少于SEAT_NUM,没有准备好 .
   */
  public boolean readyOver() {
    if (seats.size() < SEAT_NUM) {
      return false;
    }

    for (DdzAiSeat seat : seats) {
      if (!seat.readied()) {
        return false;
      }
    }

    return true;
  }

  /**
   * 是否有真实玩家 .
   * 
   * @return .
   */
  public boolean hasRealPlayer() {
    for (DdzAiSeat s : seats) {
      if (!s.isRobot()) {
        return true;
      }
    }

    return false;
  }

  /**
   * 重置牌桌 .
   */
  public void reset() {
    this.nextCallSeat = null;
    this.nextPlaySeat = null;
    this.prePlaySeat = null;
    this.prePlayCards = null;
    this.prePlayCardsType = null;
    this.multiple = 1;
    this.spring = 0;
  }

  /**
   * 牌桌的底牌,最后三张 .
   * 
   * @return .
   */
  public List<DdzCard> hiddenCards() {

    return cards.subList(cards.size() - DdzMgr.HIDDEN_CARDS, cards.size());
  }

  @Override
  public DdzRoom room() {
    return this.room;
  }

  @Override
  public List<DdzAiSeat> seats() {
    return seats;
  }

  @Override
  public boolean robotFull() {
    boolean full = false;

    // 现有机器人数量
    int robotNum = 0;
    for (AbstractSeat seat : seats()) {
      if (seat.getPlayerId() > 0 && seat.isRobot()) {
        robotNum++;
      }
    }

    // 计算最大机器人数量
    int maxRobotNum =
        (int) (this.getRoom().getRoomDomain().getRobotRatio() / 100.0 * seats().size());
    if (robotNum > maxRobotNum) {
      full = true;
    }

    return full;
  }
}
