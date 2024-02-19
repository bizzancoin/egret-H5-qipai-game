package com.idealighter.game.core.service.games.baccarat.struct;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.idealighter.game.core.service.games.baccarat.util.CardsTypeGetter;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.utils.code.RandCodeUtil;

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
 * 百家乐桌子 .
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaccaratTable extends AbstractTable {

  // 座位数量
  private static final int SEAT_NUM = 100;
  // 几副牌
  private static final int CARD_NUM = 8;
  // 庄闲家最少牌数量
  private static final int BASE_NUM = 2;
  // 历史记录
  public static final int HISTORY_SIZE = 100;
  private final BaccaratRoom room;
  // 房间里面的桌子
  private final List<BaccaratSeat> seats;
  // 玩家总下注信息
  private Map<Integer, Long> betsTotal = Maps.newConcurrentMap();
  // 一副牌
  private final List<BaccaratCard> cards;
  // 一副牌 - 不变的
  private final List<BaccaratCard> finalcards;
  // 当前状态
  private GameStatus status = GameStatus.BET;
  // 路单记录
  private final EvictingQueue<Integer> history = EvictingQueue.create(HISTORY_SIZE);
  // 龙虎记录
  private final EvictingQueue<Integer> historyDragonAndTiger = EvictingQueue.create(HISTORY_SIZE);
  // 大小记录
  private final EvictingQueue<Integer> hisBigSmall = EvictingQueue.create(HISTORY_SIZE);
  // 游戏步骤(休息、下注、开奖、结算)定时Future
  public ScheduledFuture<?> stepFuture = null;

  private long tempGold = 0;
  // 系统收益
  private long earn = 0;
  // 牌序位置
  private int cardIndex = 0;

  // 局数
  private int score = 0;

  // 庄赢局数
  private int bankerWin = 0;
  // 闲赢局数
  private int playerWin = 0;
  // 和局数
  private int tie = 0;
  // 庄对儿
  private int bankerPaire = 0;
  // 闲对儿
  private int playerPaire = 0;
  // 龙
  private int dragon = 0;
  // 虎
  private int tiger = 0;
  // 龙虎和
  private int dragonTigerTie = 0;

  // 洗牌卡
  private int yelloCardIndex = RandCodeUtil.random(200, 300);
  // 倍率
  // public final double cbMultiple[] = { 0.95, 1, 8, 0.53, 11, 1, 1.45, 1,
  // 11, 1, 8, 8 };
  private final double[] cbMultiple = { 0.95, 1, 8, 0.53, 11, 1, 0, 1.45, 1, 11, 0, 8 };

  // 宝龙倍率
  private final byte[] dragMuli = { 1, 2, 4, 6, 10, 30, -1, 1, 0 };
  // 开出结果
  private List<BettingDecision> result = Lists.newArrayList();
  // 庄家牌
  private List<BaccaratCard> banker = Lists.newArrayList();
  // 闲家牌
  private List<BaccaratCard> normalPlayer = Lists.newArrayList();

  // 上一轮剩余张数 0,1 ,2
  private int leftNum = 0;

  private boolean isNeedWashCard = false;



  /**
   * 构造函数.
   * 
   * @param id .
   * @param room .
   */
  public BaccaratTable(int id, BaccaratRoom room) {
    super(id);
    this.room = room;
    this.seats = createSeats();
    this.finalcards = createCards();
    this.cards = Lists.newArrayList();
    cards.addAll(finalcards);
  }

  private List<BaccaratCard> createCards() {
    List<BaccaratCard> cards = Lists.newArrayList();
    for (int i = 0; i < CARD_NUM; i++) {
      cards.addAll(Arrays.asList(BaccaratCard.values()));
    }
    Collections.shuffle(cards);
    return cards;
  }

  /**
   * 创建位置.
   * 
   * @return
   */
  private List<BaccaratSeat> createSeats() {
    List<BaccaratSeat> seats = new ArrayList<>(SEAT_NUM);
    for (int i = 0; i < SEAT_NUM; i++) {
      seats.add(new BaccaratSeat(i, this));
    }

    return Collections.unmodifiableList(seats);

  }

  /**
   * 是否洗牌.
   * 
   * 
   * @return
   */
  public boolean isWashCard() {
    return cardIndex >= yelloCardIndex;
  }

  /**
   * 删除已发的牌.
   * 
   * 
   * @param baccaratCards . 已发的牌
   */
  public void removeCard(List<BaccaratCard> baccaratCards) {
    for (BaccaratCard btCard : baccaratCards) {
      cards.remove(btCard);
      cardIndex++;
    }
  }

  /**
   * 是否存在该牌 .
   * 
   * 
   * @param baccaratCard .
   * @return
   */
  public boolean isCardExist(BaccaratCard baccaratCard) {
    for (BaccaratCard btCard : cards) {
      if (BaccaratCard.equal(btCard, baccaratCard)) {
        return true;
      }
    }
    return false;

  }

  /**
   * 机器人收益 .
   * 
   * @return
   */
  public long robotBill() {
    long bill = 0;
    for (BaccaratSeat seat : seats) {
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
   * 真实玩家收益 .
   * 
   * @return
   */
  public long playerBill() {
    long bill = 0;
    for (BaccaratSeat seat : seats) {
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
    for (BaccaratSeat seat : seats) {
      if (seat.getPlayerId() == 0) {
        continue;
      }
      for (int area = 0; area <= 11; area++) {
        long seatBeat = seat.getBets().getOrDefault(area, 0L);
        long areaBet = tableBets.getOrDefault(area, 0L);
        tableBets.put(area, areaBet + seatBeat);
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
    for (BaccaratSeat seat : seats) {
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
   * 玩家某个区域合计总下注 .
   * 
   * @param area .
   * @return
   */
  public Long playerareaBets(int area) {
    long playerareaBets = 0;
    for (BaccaratSeat seat : seats) {
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
   * 宝龙压注和.
   * 
   * 
   * @return
   */
  public long dragonTotal() {

    return playerareaBets(BettingDecision.PLAYER.type) + playerareaBets(BettingDecision.BANK.type)
        + playerareaBets(BettingDecision.TIE.type)
        + playerareaBets(BettingDecision.PLAYER_DRAGON.type)
        + playerareaBets(BettingDecision.BANKER_DRAGON.type)
        + playerareaBets(BettingDecision.DRAGON_TIGER_TIE.type);

  }

  /**
   * 机器人合计总下注.
   * 
   * @return
   */
  public long robotTotalBets() {
    long robotTotalBets = 0;
    for (BaccaratSeat seat : seats) {
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
    for (BaccaratSeat seat : seats) {
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
   * 桌子区域押注 .
   * 
   * @param area .
   * @return
   */
  public long bet(int area) {
    long areaBet = 0;
    for (BaccaratSeat seat : seats) {
      areaBet += seat.getBets().getOrDefault(area, 0L);
    }

    return areaBet;
  }

  /**
   * 重置桌子 .
   */
  public void reset() {
    this.earn = 0;
    this.tempGold = 0;
    // if (cardIndex == yelloCardIndex) {
    // Collections.shuffle(cards);
    // yelloCardIndex = RandCodeUtil.random(300, 400);
    // }
    banker.clear();
    normalPlayer.clear();
    result.clear();
  }

  /**
   * 闲家合计筹码.
   * 
   * @return
   */
  public long playesChips() {
    long playesChips = 0;
    for (BaccaratSeat s : seats) {
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
  public BaccaratSeat getSeat(int order) {

    return seats.get(order);
  }

  @Override
  public AbstractRoom room() {
    return room;
  }

  @Override
  public List<BaccaratSeat> seats() {

    return seats;
  }

  /**
   * 历史记录 .
   * 
   * 
   */
  public void addHistory() {
    addScoreRecord();
    addDragonAndTiger();
    addBigSmall();
    // addDragonTest();

  }

  /**
   * 路单记录 .
   * 
   * 
   */
  public void addScoreRecord() {
    boolean bankWin = false;
    boolean bankPaire = false;
    boolean playerPaire = false;
    boolean playerWin = false;
    ScoreRecord scoreRecord = null;
    for (BettingDecision bty : result) {
      if (bty == BettingDecision.BANK) {
        bankWin = true;
      } else if (bty == BettingDecision.PLAYER) {
        playerWin = true;
      } else if (bty == BettingDecision.TIE) {
        bankWin = false;
        playerWin = false;
      }
      if (bty == BettingDecision.BANKER_PAIRE) {
        bankPaire = true;
      }
      if (bty == BettingDecision.PLAYER_PAIRE) {
        playerPaire = true;
      }
    }
    if (bankWin && !playerWin && !bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.BANK_NORMAL;
    } else if (bankWin && !playerWin && !bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.BANKER_PLAYER_PAIRE;
    } else if (bankWin && !playerWin && bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.BANKER_PAIRE;
    } else if (bankWin && !playerWin && bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.BANKER_PAIRE_PLAYER_PAIRE;
    } else if (!bankWin && !playerWin && !bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.TIE;
    } else if (!bankWin && !playerWin && bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.TIE_BANKER_PAIRE;
    } else if (!bankWin && !playerWin && !bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.TIE_PLAYER_PAIRE;
    } else if (!bankWin && !playerWin && bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.TIE_BANKER_PAIRE_PLAYER_PAIRE;
    } else if (!bankWin && playerWin && !bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.PLAYER_NORMAL;
    } else if (!bankWin && playerWin && bankPaire && !playerPaire) {
      scoreRecord = ScoreRecord.PLAYER_BANKER_PAIRE;
    } else if (!bankWin && playerWin && !bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.PALYER_PAIRE;
    } else if (!bankWin && playerWin && bankPaire && playerPaire) {
      scoreRecord = ScoreRecord.PALYER_PAIRE_BANKER_PAIRE;
    }
    history.add(scoreRecord.type);
  }

  /**
   * 龙虎记录 .
   * 
   * 
   */
  public void addDragonAndTiger() {
    DragonAndTiger res = null;
    for (BettingDecision bty : result) {
      if (bty == BettingDecision.DRAGON) {
        res = DragonAndTiger.DRAGON;
      } else if (bty == BettingDecision.TIGER) {
        res = DragonAndTiger.TIGER;
      } else if (bty == BettingDecision.DRAGON_TIGER_TIE) {
        res = DragonAndTiger.TIE;
      }
    }
    historyDragonAndTiger.add(res.val);
    // historyDragonAndTiger.add(RandCodeUtil.random(1, 3));

  }

  /**
   * test.
   */
  public void addDragonTest() {
    for (int j = 0; j < 30; j++) {
      if (j > 20 && j <= 30) {
        historyDragonAndTiger.add(3);
      } else {
        historyDragonAndTiger.add(RandCodeUtil.random(1, 3));
      }
    }

  }

  /**
   * 大小记录 .
   * 
   * 
   */
  public void addBigSmall() {
    BettingDecision res = null;
    for (BettingDecision bty : result) {
      if (bty == BettingDecision.BIG) {
        res = BettingDecision.BIG;
      } else if (bty == BettingDecision.SMALL) {
        res = BettingDecision.SMALL;
      }
    }
    hisBigSmall.add(res.type);

  }

  /**
   * 推断结果 .
   * 
   * 
   */
  public void deduceWinner() {
    int pointBanker = CardsTypeGetter.getCardPoint(banker);
    int pointnormalPlayer = CardsTypeGetter.getCardPoint(normalPlayer);
    if (pointBanker > pointnormalPlayer) {
      result.add(BettingDecision.BANK);
      result.add(BettingDecision.BANKER_DRAGON);
      bankerWin++;
    } else if (pointBanker < pointnormalPlayer) {
      result.add(BettingDecision.PLAYER);
      result.add(BettingDecision.PLAYER_DRAGON);
      playerWin++;
    } else if (pointBanker == pointnormalPlayer) {
      result.add(BettingDecision.TIE);
      tie++;
    }
    if (banker.size() + normalPlayer.size() == BASE_NUM * 2) {
      result.add(BettingDecision.SMALL);
    } else if (banker.size() + normalPlayer.size() > BASE_NUM * 2) {
      result.add(BettingDecision.BIG);
    }

    if (CardsTypeGetter.getCardsType(banker.subList(0, 2)) == BaccaratCardsType.PAIR) {
      result.add(BettingDecision.BANKER_PAIRE);
      bankerPaire++;
    }
    if (CardsTypeGetter.getCardsType(normalPlayer.subList(0, 2)) == BaccaratCardsType.PAIR) {
      result.add(BettingDecision.PLAYER_PAIRE);
      playerPaire++;
    }

    if (banker.get(0).power > normalPlayer.get(0).power) {
      result.add(BettingDecision.DRAGON);
      dragon++;
    } else if (banker.get(0).power < normalPlayer.get(0).power) {
      result.add(BettingDecision.TIGER);
      tiger++;
    } else {
      result.add(BettingDecision.DRAGON_TIGER_TIE);
      dragonTigerTie++;
    }
    score++;
  }

  /**
   * 结算 .
   * 
   * 
   */
  public void balance() {
    int ppoint = CardsTypeGetter.getCardPoint(normalPlayer);
    int bpoint = CardsTypeGetter.getCardPoint(banker);
    int cardDif = bpoint - ppoint;
    boolean flag = (banker.size() == BASE_NUM && normalPlayer.size() == BASE_NUM) ? true : false;
    for (BaccaratSeat seat : seats) {
      if (seat.getPlayerId() > 0) {
        // 例合
        if (flag && ppoint == bpoint && ppoint >= 8) {
          seat.setBillChips(seat.getBillChips()
              + (seat.getBets().getOrDefault(BettingDecision.PLAYER_DRAGON.type, 0L)
                  + seat.getBets().getOrDefault(BettingDecision.BANKER_DRAGON.type, 0L)));

          // log.info("中了 {} ({}+{}) 总计 {}", "例和",
          // seat.getBets().getOrDefault(BettingDecision.PLAYER_DRAGON.type, 0L),
          // seat.getBets().getOrDefault(BettingDecision.BANKER_DRAGON.type, 0L),
          // seat.getBillChips());

        }
        for (BettingDecision betType : result) {
          long chips = seat.getBets().getOrDefault(betType.type, 0L);
          double muti = cbMultiple[betType.type];
          if (betType == BettingDecision.PLAYER_DRAGON
              || betType == BettingDecision.BANKER_DRAGON) {
            /**
             * 宝龙倍率 .
             */

            muti = dragonMuliti(Math.abs(cardDif), flag, ppoint, bpoint);

            // log.info("中了{} 玩家点数 [{}] ,庄家点数[{}], [{}],最终倍数 [{}] ", betType.desc, ppoint, bpoint,
            // flag ? "没补牌" : "补牌", muti);
            // if (cardDif >= 4) {
            // muti = dragMuli[cardDif - 4];
            // } else if (flag && cardDif != 0) {
            // muti = dragMuli[dragMuli.length - 2];
            // } else {
            // muti = dragMuli[dragMuli.length - 1];
            // }
          }
          if (betType == BettingDecision.DRAGON_TIGER_TIE) {
            seat.setBillChips(
                seat.getBillChips() + (seat.getBets().getOrDefault(BettingDecision.TIGER.type, 0L)
                    + seat.getBets().getOrDefault(BettingDecision.DRAGON.type, 0L)));

            // log.info("中了{} 返还筹码 {} + {} 总计 {}", betType.desc,
            // seat.getBets().getOrDefault(BettingDecision.TIGER.type, 0L),
            // seat.getBets().getOrDefault(BettingDecision.DRAGON.type, 0L), seat.getBillChips());
          }

          seat.setBillChips(seat.getBillChips() + (long) (chips * (muti + 1)));
          // log.info("中了 {} ({}+{})*{} = {} 总计 {}", betType.desc, muti, 1, chips,
          // (long) (chips * (muti + 1)), seat.getBillChips());


        }

      }
    }
  }

  /**
   * 获取宝龙倍率 .
   * 
   * 
   * @param cardDif .
   * @param flag .
   * @param ppoint .
   * @param bpoint .
   * @return
   */
  public int dragonMuliti(int cardDif, boolean flag, int ppoint, int bpoint) {
    int muti;
    if (cardDif >= 4 && !flag) {
      muti = dragMuli[cardDif - 4];
    } else if (!flag && cardDif < 3) {
      muti = dragMuli[dragMuli.length - 3];
    } else if (flag && cardDif != 0) {
      if (ppoint == 6 && bpoint == 7) {
        muti = dragMuli[dragMuli.length - 3];
      } else {
        muti = dragMuli[dragMuli.length - 2];
      }
    } else {
      muti = dragMuli[dragMuli.length - 1];
    }
    return muti;
  }

  public int countLeftNum() {
    return 6 - banker.size() - normalPlayer.size();
  }

  /**
   * 结算(只是结算筹码，并未告知客户端) .
   * 
   */
  public void balanceTable() {
    this.earn = playerTotalBets() - playerBill();
  }

  public BaccaratCard popCard() {
    cardIndex++;
    return cards.remove(cards.size() - 1);
  }

  public void addCard(BaccaratCard card) {
    cardIndex--;
    cards.add(card);
  }

  /**
   * 消息记录重置 .
   * 
   * 
   */
  public void washCardRest() {
    history.clear();
    historyDragonAndTiger.clear();
    hisBigSmall.clear();
    tie = 0;
    bankerPaire = 0;
    playerPaire = 0;
    bankerWin = 0;
    playerWin = 0;
    dragonTigerTie = 0;
    tiger = 0;
    dragon = 0;
    score = 0;
    cardIndex = 0;
    cards.clear();
    cards.addAll(finalcards);
    Collections.shuffle(cards);
    yelloCardIndex = RandCodeUtil.random(200, 300);

  }

  /**
   * 机器人满了.
   * 
   */
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
