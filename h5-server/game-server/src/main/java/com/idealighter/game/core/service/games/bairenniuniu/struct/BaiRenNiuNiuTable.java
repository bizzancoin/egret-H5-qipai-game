package com.idealighter.game.core.service.games.bairenniuniu.struct;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Maps;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.utils.check.EmptyUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 百人牛牛桌子 .
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaiRenNiuNiuTable extends AbstractTable {

  // 座位数量
  private static final int SEAT_NUM = 100;
  private static final int SYS_SEAT_NUM = 5;
  public static final int MAX_BANKER_SIZE = 3;
  private static final int HISTORY_SIZE = 20;

  // 1个庄家和4个虚拟闲家
  public static final int BANKER_SEAT = -5;
  public static final int PLAYER_SEAT1 = -1;
  public static final int PLAYER_SEAT2 = -2;
  public static final int PLAYER_SEAT3 = -3;
  public static final int PLAYER_SEAT4 = -4;

  private final BaiRenNiuNiuRoom room;
  // 房间里面的桌子
  private final List<BaiRenNiuNiuSeat> seats;
  // 庄家和4个闲家
  private final List<BaiRenNiuNiuSeat> sysSeats;
  // 桌子是否准备完成
  private boolean readied = false;
  // 桌上的总筹码
  private long totalChips = 0;

  // 当前庄家Id，系统坐庄为0
  private long bankerId = 0;
  // 玩家总下注信息
  private Map<Integer, Long> betsTotal = Maps.newConcurrentMap();
  // 一副牌
  private final List<NiuNiuCard> cards = Arrays.asList(NiuNiuCard.values());
  // 当前状态
  private GameStatus status = GameStatus.REST;
  // 队列庄家
  private EvictingQueue<Long> applyBankers = EvictingQueue.create(MAX_BANKER_SIZE);

  private final EvictingQueue<Integer> history = EvictingQueue.create(HISTORY_SIZE);

  // 游戏步骤(休息、下注、开奖、结算)定时Future
  public ScheduledFuture<?> stepFuture = null;

  private long tempGold = 0;

  // 系统收益
  private long earn = 0;

  /**
   * 构造函数.
   * 
   * @param id .
   * @param room .
   */
  public BaiRenNiuNiuTable(int id, BaiRenNiuNiuRoom room) {
    super(id);
    this.room = room;
    this.seats = createSeats();
    this.sysSeats = createSysSeat();
  }
  
  public BaiRenNiuNiuSeat getSysSeats(int order) {
    BaiRenNiuNiuSeat result = null;
    if (EmptyUtil.listIsNotEmpty(sysSeats)) {
      for (BaiRenNiuNiuSeat seat: sysSeats) {
        if (seat.getOrder() == order) {
          result = seat; 
        }
      }
    }
    return result;
  }

  /**
   * 创建位置 .
   * 
   * @return
   */
  private List<BaiRenNiuNiuSeat> createSeats() {
    List<BaiRenNiuNiuSeat> seats = new ArrayList<>(SEAT_NUM);
    for (int i = 0; i < SEAT_NUM; i++) {
      seats.add(new BaiRenNiuNiuSeat(i, this));
    }

    return Collections.unmodifiableList(seats);

  }

  /**
   * 创建系统位置.
   * 
   * @return
   */
  private List<BaiRenNiuNiuSeat> createSysSeat() {
    List<BaiRenNiuNiuSeat> seatList = new ArrayList<>(SYS_SEAT_NUM);
    for (int i = 1; i <= SYS_SEAT_NUM; i++) {
      BaiRenNiuNiuSeat sysSeat = new BaiRenNiuNiuSeat(-i, this);
      if (i == -BANKER_SEAT) {
        sysSeat.setPlayerName("系统坐庄");
      } else {
        sysSeat.setPlayerName("闲家" + (i));
      }
      sysSeat.setPlayerId(0);
      seatList.add(sysSeat);
    }
    return seatList;
  }

  /**
   * 输赢的历史记录 .
   */
  public void addHistory() {
    int weigh = 0;
    for (BaiRenNiuNiuSeat seat : this.sysSeats) {
      if (seat.getOrder() == BANKER_SEAT) {
        continue;
      }
      // TODO: ?
      if (seat.isWin()) {
        weigh += Math.pow(2, 4 - Math.abs(seat.getOrder()));
      }
    }
    this.history.add(weigh);

  }

  /**
   * 机器人收益.
   * 
   * @return
   */
  public long robotBill() {
    long bill = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      if (seat.isRobot()) {
        bill += seat.getBillChips();
      }
    }
    return bill;
  }

  /**
   * 真实玩家收益.
   * 
   * @return
   */
  public long playerBill() {
    long bill = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      if (!seat.isRobot()) {
        bill += seat.getBillChips();
      }
    }
    return bill;
  }

  /**
   * 桌子玩家下注.
   * 
   * @return
   */
  public Map<Integer, Long> tableBets() {
    Map<Integer, Long> tableBets = new HashMap<>();
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (Entry<Integer, Long> seatBetEtr : seat.getBets().entrySet()) {
        int area = seatBetEtr.getKey();
        long areaBet = tableBets.getOrDefault(area, 0L);
        tableBets.put(area, areaBet + seatBetEtr.getValue());
      }
    }

    return tableBets;
  }

  /**
   * 玩家合计总下注.
   * 
   * @return
   */
  public long playerTotalBets() {
    long playerTotalBets = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (Entry<Integer, Long> seatBetEtr : seat.getBets().entrySet()) {
        if (!seat.isRobot()) {
          playerTotalBets += seatBetEtr.getValue();
        }
      }
    }

    return playerTotalBets;
  }

  /**
   * 总下注 .
   *
   * @return 总下注.
   */
  public Long tableTotalBets() {
    Map<Integer, Long> tableBetMap = tableBets();
    long totalBet = 0L;
    for (Long bet : tableBetMap.values()) {
      totalBet += bet;
    }
    return totalBet;
  }


  /**
   * 玩家某个区域合计总下注 .
   * 
   * @param area .
   * @return
   */
  public Long playerareaBets(int area) {
    long playerareaBets = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (Entry<Integer, Long> seatBetEtr : seat.getBets().entrySet()) {
        if (!seat.isRobot() && seatBetEtr.getKey() == area) {
          playerareaBets += seatBetEtr.getValue();
        }
      }
    }

    return playerareaBets;
  }

  /**
   * 机器人合计总下注.
   * 
   * @return
   */
  public long robotTotalBets() {
    long robotTotalBets = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (Entry<Integer, Long> seatBetEtr : seat.getBets().entrySet()) {
        if (seat.isRobot()) {
          robotTotalBets += seatBetEtr.getValue();
        }
      }
    }

    return robotTotalBets;
  }

  /**
   * 机器人某个区域合计总下注 .
   * 
   * @param area .
   * @return
   */
  public Long robotareaBets(int area) {
    long robotareaBets = 0;
    for (BaiRenNiuNiuSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (Entry<Integer, Long> seatBetEtr : seat.getBets().entrySet()) {
        if (seat.isRobot() && seatBetEtr.getKey() == area) {
          robotareaBets += seatBetEtr.getValue();
        }
      }
    }

    return robotareaBets;
  }

  /**
   * 重置桌子 .
   */
  public void reset() {
    this.readied = false;
    this.earn = 0;
    this.totalChips = 0;
    this.tempGold = 0;
    Collections.shuffle(cards);
    Collections.shuffle(cards);
  }

  /**
   * 闲家合计筹码.
   * 
   * @return
   */
  public long playesChips() {
    long playesChips = 0;
    for (BaiRenNiuNiuSeat s : seats) {
      if (!s.isDealer() && s.getPlayerId() > 0) {
        playesChips += s.getTotalChips();
      }
    }

    return playesChips;
  }

  /**
   * 获取seat.
   * 
   * @param order .
   * @return
   */
  public BaiRenNiuNiuSeat getSeat(int order) {

    return seats.get(order);
  }

  @Override
  public AbstractRoom room() {
    return room;
  }

  @Override
  public List<BaiRenNiuNiuSeat> seats() {

    return seats;
  }

  /**
   * 庄家.
   * 
   * @return
   */
  public BaiRenNiuNiuSeat banker() {
    if (bankerId == 0) {
      return dealer();
    } else {
      for (BaiRenNiuNiuSeat seat : seats) {
        if (seat.getPlayerId() == bankerId) {
          return seat;
        }
      }
    }
    return dealer();
  }

  /**
   * 庄家.
   * 
   * @return
   */
  public BaiRenNiuNiuSeat dealer() {

    for (BaiRenNiuNiuSeat seat : sysSeats) {
      if (seat.getOrder() == BANKER_SEAT) {
        return seat;
      }
    }
    return null;
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
    if (robotNum >= maxRobotNum) {
      full = true;
    }

    return full;
  }
  

}
