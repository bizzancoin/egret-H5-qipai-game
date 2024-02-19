package com.idealighter.game.core.service.games.bairenniuniu.manager;

import com.alibaba.fastjson.TypeReference;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuSeat;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuTable;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeComparator;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeGetter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.manager.RoomPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.struct.control.BaiRenNiuNiuControl;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePool;
import com.idealighter.game.dictionary.dic.BairenniuniuRoomDic;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 百人牛牛控制 .
 */
@Singleton
public class BaiRenNiuNiuControlScript {

  private static final Logger LOG = LoggerFactory.getLogger(BaiRenNiuNiuControlScript.class);

  private static final TypeReference<BaiRenNiuNiuControl> BAIREN_NIUNIU_CONTROL_TYPE =
      new TypeReference<BaiRenNiuNiuControl>() {};

  // 控制手牌数量(控制时，提前随机生成的手牌数量)
  private static final int CONTROL_HAND_CARDS_NUM = 10;

  @Inject
  private BaiRenNiuNiuMsgMgr msgMgr;
  @Inject
  private BairenniuniuRoomDic roomDic;
  @Inject
  private RoomPrizePoolMgrScript roomPrizePoolMgr;

  private final BaiRenNiuNiuDataMgr dataMgr;

  @Inject
  public BaiRenNiuNiuControlScript(BaiRenNiuNiuDataMgr dataMgr) {
    this.dataMgr = dataMgr;
  }

  /**
   * 控制玩家发牌,玩家和机器人玩才控制 .
   * 
   * @param table .
   */
  public void controlDealCards(BaiRenNiuNiuTable table) {
    // 是否系统坐庄
    boolean sysBanker = false;
    BaiRenNiuNiuSeat bankerSeat = null;
    long bankerId = table.getBankerId();
    if (bankerId > 0L) {
      bankerSeat = dataMgr.getPlayerSeat(bankerId);
      if (bankerSeat.isRobot()) { // 机器人坐庄为系统坐庄
        sysBanker = true;
      }
    } else { // 没有任何人坐庄为系统坐庄
      sysBanker = true;
    }

    // 玩家和机器人玩才控制，个人奖池控制优先房间奖池控制
    if ((sysBanker && table.playerTotalBets() > 0)
        || (!sysBanker && table.robotTotalBets() > 0)) {
      /*
       * 控制发牌方法：先将7手玩家牌排序(从小到大),再根据庄家和奖池去计算玩家对应的手牌 .
       */
      // 需要从小到大排序(从小到大),一共7副手牌(庄家的牌在中间)
      LinkedList<List<NiuNiuCard>> sortedCardsList = new LinkedList<>();
      LinkedList<List<NiuNiuCard>> dealCards = new LinkedList<>();

      // List<List<NiuNiuCard>> dealCards = Lists.newArrayList();
      /*
       * 提前生成7副排好序的牌(why?除去庄家，最多3个闲家，闲家最多全部取头3张或者后3张) .
       */
      int cardIndex = 0;
      for (int i = 0; i < CONTROL_HAND_CARDS_NUM; i++) {
        sortedCardsList
            .add(table.getCards().subList(cardIndex, cardIndex += BaiRenNiuNiuMgr.PLAYER_CARDS));
      }

      // 玩家的牌进行排序
      sortedCardsList.sort((cards1, cards2) -> {
        NiuNiuCardsType cards1Type =
            NiuNiuCardsTypeGetter.getCardsType(cards1, new ArrayList<>());
        NiuNiuCardsType cards2Type =
            NiuNiuCardsTypeGetter.getCardsType(cards2, new ArrayList<>());
        if (NiuNiuCardsTypeComparator.compare(cards1, cards1Type, cards2, cards2Type)) {
          return 1;
        } else {
          return -1;
        }
      });

      /*
       * 玩家计算奖池，庄家取sortedCardsList中间的手牌，比庄家大取提前生成的牌的头，反之取尾 . 先发庄家的牌,有了庄家的牌才能确定输赢计算筹码
       */
      int randIndex = RandCodeUtil.random(0, 3);
      dealCards.addAll(sortedCardsList.subList(randIndex, randIndex + 5));
      Collections.shuffle(table.getSysSeats());
      List<NiuNiuCard> dealerCards = sortedCardsList.remove(CONTROL_HAND_CARDS_NUM / 2);
      for (BaiRenNiuNiuSeat seat : table.getSysSeats()) {
        if (seat.getOrder() == BaiRenNiuNiuTable.BANKER_SEAT) {
          continue;
        }
        sortRanker(table, sysBanker, seat, sortedCardsList, dealerCards);
      }

      Collections.sort(table.getSysSeats(), new Comparator<BaiRenNiuNiuSeat>() {
        @Override
        public int compare(BaiRenNiuNiuSeat o1, BaiRenNiuNiuSeat o2) {
          return o2.getPower() - o1.getPower();
        }
      });

      for (BaiRenNiuNiuSeat seat : table.getSysSeats()) {
        if (seat.getOrder() == BaiRenNiuNiuTable.BANKER_SEAT) {
          dealDealerCards(seat, dealCards.removeLast());
        } else {
          dealPlayerCards(seat, dealCards.removeLast());
        }
      }

    } else {
      normalDealCards(table);
    }
    msgMgr.sendDealCardsMsg(table);
  }

  public void controlDealCards2(BaiRenNiuNiuTable table) {
    // 是否系统坐庄
    boolean sysBanker = false;
    BaiRenNiuNiuSeat bankerSeat = null;
    long bankerId = table.getBankerId();
    if (bankerId > 0L) {
      bankerSeat = dataMgr.getPlayerSeat(bankerId);
      if (bankerSeat.isRobot()) { // 机器人坐庄为系统坐庄
        sysBanker = true;
      }
    } else { // 没有任何人坐庄为系统坐庄
      sysBanker = true;
    }

    // 玩家和机器人玩才控制，个人奖池控制优先房间奖池控制
    if ((sysBanker && table.playerTotalBets() > 0)
        || (!sysBanker && table.robotTotalBets() > 0)) {
      // 所有分发的牌
      LinkedList<List<NiuNiuCard>> cardsList = new LinkedList<>();
      LinkedList<NiuNiuCardsType> cardTypeList = new LinkedList<>();
      int cardIndex = 0;
      for (int i = 0; i < CONTROL_HAND_CARDS_NUM; i++) {
        List<NiuNiuCard> subCards =
            table.getCards().subList(cardIndex, cardIndex += BaiRenNiuNiuMgr.PLAYER_CARDS);
        // 牌型
        NiuNiuCardsType cardType =
            NiuNiuCardsTypeGetter.getCardsType(subCards, new ArrayList<>());
        // 牌
        cardsList.add(subCards);
        // 牌型
        cardTypeList.add(cardType);
      }

      RoomPrizePool roomPrizePool =
          roomPrizePoolMgr.defaultRoomPrizePool(Game.BAIREN_NIUNIU, table.getRoom().getId());
      boolean sysWin = true;
      // 房间奖池
      if (roomPrizePool != null) {
        ControlStrategy strategy = roomPrizePool.abledControlStrategy();
        if (strategy != null) {
          // 牛牛控制
          BaiRenNiuNiuControl control = strategy.control(BAIREN_NIUNIU_CONTROL_TYPE);
          // 玩家赢
          boolean playerWin = RandCodeUtil
              .random(control.getLoseRatio() + control.getWinRatio()) >= control.getLoseRatio();
          // 取反算出系统赢
          sysWin = !playerWin;

          // 选牌
          CardsMapWithSysWinChips result =
              choseCards(cardsList, cardTypeList, sysWin, sysBanker, control.getLoseRatio(),
                  control.getWinRatio(), table.playerareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT1),
                  table.robotareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT1),
                  table.playerareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT2),
                  table.robotareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT2),
                  table.playerareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT3),
                  table.robotareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT3),
                  table.playerareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT4),
                  table.robotareaBets(-BaiRenNiuNiuTable.PLAYER_SEAT4));

          // 先发庄家的牌
          dealDealerCards(table.getSysSeats(BaiRenNiuNiuTable.BANKER_SEAT),
              result.getMap().get(BaiRenNiuNiuTable.BANKER_SEAT));
          // 再发闲家的牌
          for (Integer order : result.getMap().keySet()) {
            if (order != BaiRenNiuNiuTable.BANKER_SEAT) {
              dealNormalPlayerCards(table.getSysSeats(order), result.getMap().get(order));
            }
          }
        } else {
          normalDealCards(table);
        }
      } else {
        normalDealCards(table);
      }
    } else {
      normalDealCards(table);
    }
    msgMgr.sendDealCardsMsg(table);
  }

  public static CardsMapWithSysWinChips choseCards(LinkedList<List<NiuNiuCard>> cardsList, // 牌列表
      LinkedList<NiuNiuCardsType> cardTypeList, // 牌型列表
      boolean sysWin, // 是否系统赢
      boolean sysBanker, // 是否为系统庄,
      int sysWinRatio, // 系统赢的概率
      int sysLoseRatio, // 系统输的概率
      long seat1PlayerBets, // 闲家座位1玩家下注
      long seat1RobotBets, // 闲家座位1机器人下注
      long seat2PlayerBets, // 闲家座位2玩家下注
      long seat2RobotBets, // 闲家座位2机器人下注
      long seat3PlayerBets, // 闲家座位3玩家下注
      long seat3RobotBets, // 闲家座位3机器人下注
      long seat4PlayerBets, // 闲家座位4玩家下注
      long seat4RobotBets // 闲家座位4机器人下注
  ) {
    // 系统赢的列表
    List<CardsMapWithSysWinChips> sysWinList = new ArrayList<>();
    // 系统输的列表
    List<CardsMapWithSysWinChips> sysLoseList = new ArrayList<>();

    long start = System.currentTimeMillis();
    for (int a = 0; a < CONTROL_HAND_CARDS_NUM; a++) {
      List<NiuNiuCard> bankerSeatCards = cardsList.get(a);
      NiuNiuCardsType bankerCardType = cardTypeList.get(a);
      for (int b = 0; b < CONTROL_HAND_CARDS_NUM; b++) {
        if (b == a) {
          continue;
        }
        List<NiuNiuCard> seat1Cards = cardsList.get(b);
        NiuNiuCardsType seat1CardType = cardTypeList.get(b);
        for (int c = 0; c < CONTROL_HAND_CARDS_NUM; c++) {
          if (c == a || c == b) {
            continue;
          }
          List<NiuNiuCard> seat2Cards = cardsList.get(c);
          NiuNiuCardsType seat2CardType = cardTypeList.get(c);
          for (int d = 0; d < CONTROL_HAND_CARDS_NUM; d++) {
            if (d == a || d == b || d == c) {
              continue;
            }
            List<NiuNiuCard> seat3Cards = cardsList.get(d);
            NiuNiuCardsType seat3CardType = cardTypeList.get(d);
            for (int e = 0; e < CONTROL_HAND_CARDS_NUM; e++) {
              if (e == a || e == b || e == c || e == d) {
                continue;
              }
              List<NiuNiuCard> seat4Cards = cardsList.get(e);
              NiuNiuCardsType seat4CardType = cardTypeList.get(e);

              long sysWinChips = 0; // 系统赢分
              sysWinChips += sysWinChips(sysBanker, seat1PlayerBets, seat1RobotBets,
                  bankerSeatCards, bankerCardType, seat1Cards, seat1CardType);
              sysWinChips += sysWinChips(sysBanker, seat2PlayerBets, seat2RobotBets,
                  bankerSeatCards, bankerCardType, seat2Cards, seat2CardType);
              sysWinChips += sysWinChips(sysBanker, seat3PlayerBets, seat3RobotBets,
                  bankerSeatCards, bankerCardType, seat3Cards, seat3CardType);
              sysWinChips += sysWinChips(sysBanker, seat4PlayerBets, seat4RobotBets,
                  bankerSeatCards, bankerCardType, seat4Cards, seat4CardType);

              Map<Integer, List<NiuNiuCard>> cardsMap = new HashMap<>();
              cardsMap.put(BaiRenNiuNiuTable.BANKER_SEAT, bankerSeatCards);
              cardsMap.put(BaiRenNiuNiuTable.PLAYER_SEAT1, seat1Cards);
              cardsMap.put(BaiRenNiuNiuTable.PLAYER_SEAT2, seat2Cards);
              cardsMap.put(BaiRenNiuNiuTable.PLAYER_SEAT3, seat3Cards);
              cardsMap.put(BaiRenNiuNiuTable.PLAYER_SEAT4, seat4Cards);

              if (sysWinChips > 0) {
                sysWinList.add(new CardsMapWithSysWinChips(sysWinChips, cardsMap));
              } else {
                sysLoseList.add(new CardsMapWithSysWinChips(sysWinChips, cardsMap));
              }
            }
          }
        }
      }
    }

    long start1 = System.currentTimeMillis();
    LOG.info("百人牛牛控牌, for消耗时间: {}", (start1 - start) + "ms");
    // 系统赢分升序排序
    sysWinList.sort((item1, item2) -> {
      if (item1.getSysWinChips() > item2.getSysWinChips()) {
        return 1;
      } else if (item1.getSysWinChips() == item2.getSysWinChips()) {
        return 0;
      } else {
        return -1;
      }
    });

    // 系统输分降序排序(系统输的少的排前面)
    sysLoseList.sort((item1, item2) -> {
      if (item1.getSysWinChips() > item2.getSysWinChips()) {
        return -1;
      } else if (item1.getSysWinChips() == item2.getSysWinChips()) {
        return 0;
      } else {
        return 1;
      }
    });
    long end = System.currentTimeMillis();
    LOG.info("百人牛牛控牌, 排序花费时间: " + (end - start1) + "ms");

    CardsMapWithSysWinChips result = null;
    List<CardsMapWithSysWinChips> subList = null;
    if (sysWin) {
      // 玩家赢的概率
      Double ration = (sysWinRatio * 1.0) / (sysWinRatio + sysLoseRatio);
      // 理论上是不可能出现的
      HuohuaAssert.isTrue(EmptyUtil.listIsNotEmpty(sysWinList));
      if (sysWinList.size() > 5) {
        if (ration.compareTo(0.6) < 0) { // 玩家赢的概率在60%以下
          subList = sysWinList.subList(0, (int) (sysWinList.size() / 5.0));
        } else if (ration.compareTo(0.7) < 0) { // 玩家赢的概率在60%-70%
          subList = sysWinList.subList(0, (int) (sysWinList.size() * (2.0 / 5)));
        } else if (ration.compareTo(0.8) < 0) { // 玩家赢的概率在70%-80%
          subList = sysWinList.subList(0, (int) (sysWinList.size() * (3.0 / 5)));
        } else if (ration.compareTo(0.9) < 0) { // 玩家赢的概率在80%-90%
          subList = sysWinList.subList(0, (int) (sysWinList.size() * (4.0 / 5)));
        } else { // 玩家赢的概率在0.9-1
          subList = sysWinList.subList(0, sysWinList.size() - 1);
        }
      } else {
        subList = sysWinList.subList(0, sysWinList.size() - 1);
      }
    } else {
      // 玩家输的概率
      Double ration = (sysLoseRatio * 1.0) / (sysWinRatio + sysLoseRatio);
      // 理论上是不可能出现的
      HuohuaAssert.isTrue(EmptyUtil.listIsNotEmpty(sysLoseList));
      if (sysLoseList.size() > 5) {
        if (ration.compareTo(0.6) < 0) { // 玩家输的概率在60%以下
          subList = sysLoseList.subList(0, (int) (sysLoseList.size() / 5.0));
        } else if (ration.compareTo(0.7) < 0) { // 玩家输的概率在60%-70%
          subList = sysLoseList.subList(0, (int) (sysLoseList.size() * (2.0 / 5)));
        } else if (ration.compareTo(0.8) < 0) { // 玩家输的概率在70%-80%
          subList = sysLoseList.subList(0, (int) (sysLoseList.size() * (3.0 / 5)));
        } else if (ration.compareTo(0.9) < 0) { // 玩家输的概率在80%-90%
          subList = sysLoseList.subList(0, (int) (sysLoseList.size() * (4.0 / 5)));
        } else { // 玩家输的概率在0.9-1
          subList = sysLoseList.subList(0, sysLoseList.size() - 1);
        }
      } else {
        subList = sysLoseList.subList(0, sysLoseList.size() - 1);
      }
    }
    result = RandCodeUtil.randomList(subList);
    return result;
  }

  @Data
  @AllArgsConstructor
  public static class CardsMapWithSysWinChips {
    private long sysWinChips;
    private Map<Integer, List<NiuNiuCard>> map;
  }

  /**
   * @param sysBanker 是否系统坐庄
   * @param playerBets 玩家在改位置的下注
   * @param robotBets 机器人在改位置的下注
   * @param bankerSeatCards 庄家的牌
   * @param seatCards 本位置的牌
   * @return 系统输赢筹码
   */
  private static long sysWinChips(boolean sysBanker, long playerBets, long robotBets,
      List<NiuNiuCard> bankerSeatCards, NiuNiuCardsType bankerCardType,
      List<NiuNiuCard> seatCards, NiuNiuCardsType seatCardType) {
    Long sysWinChips = 0L;
    // 如果庄家牌更大
    if (NiuNiuCardsTypeComparator.compare(bankerSeatCards, bankerCardType, seatCards,
        seatCardType)) {
      if (sysBanker) { // 系统坐庄
        long bankerWinChips = bankerCardType.multiple * playerBets;
        sysWinChips += bankerWinChips;
      } else { // 玩家坐庄
        long bankerLoseChips = bankerCardType.multiple * robotBets;
        sysWinChips -= bankerLoseChips;
      }
    } else { // 闲家牌更大
      if (sysBanker) { // 系统坐庄
        long playerWinChips = seatCardType.multiple * playerBets;

        sysWinChips -= playerWinChips;
      } else { // 玩家坐庄
        long playerLoseChips = seatCardType.multiple * robotBets;
        sysWinChips += playerLoseChips;
      }
    }
    return sysWinChips;
  }

  private void sortRanker(BaiRenNiuNiuTable table, boolean sysBanker, BaiRenNiuNiuSeat seat,
      LinkedList<List<NiuNiuCard>> sortedCardsList, List<NiuNiuCard> dealerCards) {
    RoomPrizePool roomPrizePool =
        roomPrizePoolMgr.defaultRoomPrizePool(Game.BAIREN_NIUNIU, table.getRoom().getId());
    boolean controlSeatSysWin;
    List<NiuNiuCard> seatCards = null;
    if (roomPrizePool != null) {
      // 房间奖池控制策略
      ControlStrategy strategy;
      if (table.getTempGold() == 0) {
        strategy = roomPrizePool.abledControlStrategy();
        table.setTempGold(table.getTempGold() + roomPrizePool.getPrize().get());
      } else {
        strategy = roomPrizePool.abledControlStrategy(table.getTempGold());
      }

      // 控制输赢的玩家的输赢标识
      if (strategy != null) { // 有控制策略
        // 牛牛控制
        BaiRenNiuNiuControl control = strategy.control(BAIREN_NIUNIU_CONTROL_TYPE);
        // 玩家赢
        boolean playerWin = RandCodeUtil
            .random(control.getLoseRatio() + control.getWinRatio()) >= control.getLoseRatio();
        // 取反算出系统赢
        controlSeatSysWin = !playerWin;
      } else { // 没有随机输赢
        controlSeatSysWin = RandCodeUtil.randomBoolean();
      }
    } else {
      controlSeatSysWin = RandCodeUtil.randomBoolean();
    }

    // 当前区域玩家/机器人下注筹码
    long chips = 0;
    // 控制玩家赢得钱
    long winGold = 0;

    if (!sysBanker) {
      chips = table.robotareaBets(-seat.getOrder());
      if (controlSeatSysWin) {
        // 系统赢,本位置的牌比庄家大
        seatCards = sortedCardsList.removeLast();
        winGold = convertGold(table.getRoom().getId(),
            NiuNiuCardsTypeGetter.getCardsType(seatCards).multiple * chips);
        seat.setPower(1);
      } else {
        // 玩家赢,本位置的牌比庄家小
        seatCards = sortedCardsList.removeFirst();
        winGold = convertGold(table.getRoom().getId(),
            -NiuNiuCardsTypeGetter.getCardsType(dealerCards).multiple * chips);
        seat.setPower(-1);
      }
      seat.setWin(controlSeatSysWin);
    } else { // 系统坐庄
      chips = table.playerareaBets(-seat.getOrder());
      if (controlSeatSysWin) {
        // 系统赢,本位置的牌比庄家小
        seatCards = sortedCardsList.removeFirst();
        winGold = convertGold(table.getRoom().getId(),
            NiuNiuCardsTypeGetter.getCardsType(dealerCards).multiple * chips);
        seat.setPower(-1);
      } else {
        // 玩家赢,本位置的牌比庄家大
        seatCards = sortedCardsList.removeLast();
        winGold = convertGold(table.getRoom().getId(),
            -NiuNiuCardsTypeGetter.getCardsType(seatCards).multiple * chips);
        seat.setPower(1);
      }
      seat.setWin(!controlSeatSysWin);
    }

    LOG.info("[百人牛牛]下注区域[{}]信息: 是否赢[{}], power[{}]", seat.getPlayerName(), seat.isWin(),
        seat.getPower());

    // 奖池临时值
    table.setTempGold(table.getTempGold() + winGold);
  }

  /**
   * 正常发牌 .
   * 
   * @param table .
   */
  private void normalDealCards(BaiRenNiuNiuTable table) {
    List<NiuNiuCard> tableCards = table.getCards();
    BaiRenNiuNiuSeat dealer = table.dealer();
    int cardIndex = 0;

    // 先发庄家的牌,有了庄家的牌才能确定输赢计算筹码
    dealDealerCards(dealer,
        tableCards.subList(cardIndex, cardIndex += BaiRenNiuNiuMgr.PLAYER_CARDS));
    // dealDealerCards(dealer, specialCard());
    // dealDealerCards(dealer, loseCard());

    /*
     * 再发闲家牌，由于先发的庄家牌，所以发闲家牌时已经能计算输赢筹码了 .
     */
    for (BaiRenNiuNiuSeat seat : table.getSysSeats()) {
      if (seat.getOrder() != BaiRenNiuNiuTable.BANKER_SEAT) {
        dealNormalPlayerCards(seat,
            tableCards.subList(cardIndex, cardIndex += BaiRenNiuNiuMgr.PLAYER_CARDS));
        // dealPlayerCards(seat, specialCardSeat());
      }
    }
  }

  /**
   * 给庄家发牌 .
   * 
   * @param dealer .
   * @param cards .
   */
  private void dealDealerCards(BaiRenNiuNiuSeat dealer, List<NiuNiuCard> cards) {
    NiuNiuCardsType cardsType = NiuNiuCardsTypeGetter.getCardsType(cards, dealer.getBestCards());
    dealer.getCards().clear();
    dealer.getCards().addAll(cards);
    dealer.setCardsType(cardsType);

    LOG.info("[百人牛牛]发送庄家[{}][{}]牌[{}][{}]", dealer.getPlayerId(), dealer.getPlayerName(),
        dealer.getCardsType().getCardsTypeName(), dealer.getBestCards());
  }

  /**
   * 给闲家发牌(由于庄家的牌先发，闲家发牌的同时输赢已经出来了) .
   * 
   * @param seat 座位信息.
   * @param cards .
   */
  private void dealPlayerCards(BaiRenNiuNiuSeat seat, List<NiuNiuCard> cards) {
    NiuNiuCardsType seatCardsType =
        NiuNiuCardsTypeGetter.getCardsType(cards, seat.getBestCards());
    seat.getCards().clear();
    seat.getCards().addAll(cards);
    seat.setCardsType(seatCardsType);

    LOG.info("[百人牛牛]发送闲家[{}][{}]牌[{}][{}]", seat.getPlayerId(), seat.getPlayerName(),
        seat.getCardsType().getCardsTypeName(), seat.getBestCards());
  }

  /**
   * 给闲家发牌(由于庄家的牌先发，闲家发牌的同时输赢已经出来了) .
   * 
   * @param seat 座位信息.
   * @param cards .
   */
  private void dealNormalPlayerCards(BaiRenNiuNiuSeat seat, List<NiuNiuCard> cards) {
    NiuNiuCardsType seatCardsType =
        NiuNiuCardsTypeGetter.getCardsType(cards, seat.getBestCards());
    seat.getCards().clear();
    seat.getCards().addAll(cards);
    seat.setCardsType(seatCardsType);

    BaiRenNiuNiuSeat dealer = seat.getTable().dealer();
    List<NiuNiuCard> dealerCards = dealer.getCards();
    NiuNiuCardsType dealerCardsType = dealer.getCardsType();

    boolean win = NiuNiuCardsTypeComparator.compare(seat.getCards(), seatCardsType, dealerCards,
        dealerCardsType);
    seat.setWin(win);
    LOG.info("[百人牛牛]发送闲家[{}][{}]牌[{}]", seat.getPlayerId(), seat.getPlayerName(),
        seat.getBestCards());
  }

  /**
   * 结算房间奖池 .
   *
   * @param table .
   */
  public void balanceControl(BaiRenNiuNiuTable table) {
    long gold = convertGold(table.getRoom().getId(), table.getEarn());
    RoomPrizePool seatRoomPrizePool =
        roomPrizePoolMgr.defaultRoomPrizePool(Game.BAIREN_NIUNIU, table.getRoom().getId());
    if (seatRoomPrizePool != null) {
      long prize = seatRoomPrizePool.getPrize().get();
      prize += gold;
      seatRoomPrizePool.getPrize().getAndSet(prize);
    }
  }

  /**
   * 筹码换算金币,只会是多个金币等于一个筹码 .
   * 
   * @param roomId .
   * @param chips .
   */
  public long convertGold(int roomId, long chips) {
    BairenniuniuRoomDomain roomDomain = roomDic.get(roomId);

    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();
  }

}


