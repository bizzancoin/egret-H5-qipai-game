package com.idealighter.game.core.service.games.baccarat.manager;

import com.alibaba.fastjson.TypeReference;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratCard;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratTable;
import com.idealighter.game.core.service.games.baccarat.struct.BettingDecision;
import com.idealighter.game.core.service.games.baccarat.util.CardsTypeGetter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.manager.RoomPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.struct.control.BaccaratControl;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePool;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.List;

/**
 * 百家乐控制 .
 */
@Singleton
public class BaccaratControlScript {


  private static final TypeReference<BaccaratControl> BACCARAT_CONTROL_TYPE =
      new TypeReference<BaccaratControl>() {};

  @Inject
  private RoomPrizePoolMgrScript roomPrizePoolMgr;

  /**
   * 控制玩家发牌,玩家和机器人玩才控制 .
   * 
   * @param table .
   */
  // public void controlDealCards(BaccaratTable table) {
  //
  // // dealCards(table);
  //
  // }


  public boolean dealNoFitCards(BaccaratTable table) {
    boolean isFit = true;
    while (table.getNormalPlayer().size() < BaccaratMgr.PLAYER_CARDS) {
      BaccaratCard baccaratCard = table.popCard();
      table.getNormalPlayer().add(baccaratCard);
      isFit = false;
    }
    while (table.getBanker().size() < BaccaratMgr.PLAYER_CARDS) {
      BaccaratCard baccaratCard = table.popCard();
      table.getBanker().add(baccaratCard);
      isFit = false;
    }
    int normalPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer().subList(0, 2));
    int bankerPoint = CardsTypeGetter.getCardPoint(table.getBanker().subList(0, 2));

    // 即定胜负
    if (normalPoint >= 8 || bankerPoint >= 8) {
      while (table.getNormalPlayer().size() >= 3) {
        BaccaratCard card = table.getNormalPlayer().remove(table.getNormalPlayer().size() - 1);
        table.addCard(card);
        isFit = false;
      }
      while (table.getBanker().size() >= 3) {
        BaccaratCard card = table.getBanker().remove(table.getBanker().size() - 1);
        table.addCard(card);
        isFit = false;
      }
    }

    // 闲家补牌
    boolean hasPlayerThirdCard = false;
    int playerThirdCardPower = -1;
    if (table.getNormalPlayer().size() == BaccaratMgr.PLAYER_CARDS + 1) {
      playerThirdCardPower = table.getNormalPlayer().get(2).num % 10;
      hasPlayerThirdCard = true;
    }
    if (normalPoint <= 5 && bankerPoint < 8
        && table.getNormalPlayer().size() == BaccaratMgr.PLAYER_CARDS) {
      BaccaratCard baccaratCardPlayer = table.popCard();
      table.getNormalPlayer().add(baccaratCardPlayer);
      isFit = false;
      playerThirdCardPower = baccaratCardPlayer.num % 10;
      hasPlayerThirdCard = true;
    }

    // 庄家补牌
    normalPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer().subList(0, 2));
    if (normalPoint < 8 && bankerPoint < 8
        && table.getBanker().size() == BaccaratMgr.PLAYER_CARDS) {
      switch (bankerPoint) {
        case 0:
        case 1:
        case 2:
          table.getBanker().add(table.popCard());
          isFit = false;
          break;
        case 3:
          if (hasPlayerThirdCard && playerThirdCardPower != 8) {
            table.getBanker().add(table.popCard());
            isFit = false;
          }
          break;
        case 4:
          if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 8
              && playerThirdCardPower != 9 && playerThirdCardPower != 0) {
            table.getBanker().add(table.popCard());
            isFit = false;
          }
          break;
        case 5:
          if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 2
              && playerThirdCardPower != 3 && playerThirdCardPower != 8 && playerThirdCardPower != 9
              && playerThirdCardPower != 0) {
            table.getBanker().add(table.popCard());
            isFit = false;
          }
          break;
        case 6:
          if (hasPlayerThirdCard && (playerThirdCardPower == 6 || playerThirdCardPower == 7)) {
            table.getBanker().add(table.popCard());
            isFit = false;
          }
          break;
        case 7:
          break;
        case 8:
          break;
        case 9:
          break;

        default:
          break;
      }

    }
    return isFit;

  }

  /**
   * 控制发牌 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:26:21
   * @param table 桌子.
   */
  public void controlDealCards(BaccaratTable table) {
    RoomPrizePool roomPrizePool =
        roomPrizePoolMgr.defaultRoomPrizePool(Game.BACCARAT, table.getRoom().getId());
    boolean controlSeatWin;
    ControlStrategy strategy = null;
    if (roomPrizePool != null) {
      // 房间奖池控制策略
      if (table.getTempGold() == 0) {
        strategy = roomPrizePool.abledControlStrategy();
        table.setTempGold(table.getTempGold() + roomPrizePool.getPrize().get());
      } else {
        strategy = roomPrizePool.abledControlStrategy(table.getTempGold());
      }
    }

    BaccaratControl control = null;
    if (strategy != null) {
      control = strategy.control(BACCARAT_CONTROL_TYPE);
    }

    boolean win;

    /**
     * step1 判断大小.
     */
    controlSeatWin = control != null ? RandCodeUtil
        .random(control.getLoseRatio() + control.getWinRatio()) >= control.getWinRatio()
        : RandCodeUtil.randomBoolean();

    // 开和概率
    final boolean controlTie = control != null ? RandCodeUtil.random(100) <= control.getTieRito()
        : RandCodeUtil.randomBoolean();
    win = RandCodeUtil.randomBoolean();

    BettingDecision sb = win ? BettingDecision.BIG : BettingDecision.SMALL;
    BettingDecision otherSb = !win ? BettingDecision.BIG : BettingDecision.SMALL;

    // 该区域是否有人下注

    if (controlSeatWin) {
      if (table.playerareaBets(sb.type) * table.getCbMultiple()[sb.type]
          - table.playerareaBets(otherSb.type) * table.getCbMultiple()[otherSb.type] >= 0) {
        sb = otherSb;
      }

    } else {
      if (table.playerareaBets(sb.type) * table.getCbMultiple()[sb.type]
          - table.playerareaBets(otherSb.type) * table.getCbMultiple()[otherSb.type] <= 0) {
        sb = otherSb;
      }
    }

    /**
     * step2 龙虎判断.
     */

    controlSeatWin = control != null ? RandCodeUtil
        .random(control.getLoseRatio() + control.getWinRatio()) >= control.getWinRatio()
        : RandCodeUtil.randomBoolean();

    win = RandCodeUtil.randomBoolean();

    BettingDecision dt = win ? BettingDecision.DRAGON : BettingDecision.TIGER;

    if (table.playerareaBets(BettingDecision.DRAGON_TIGER_TIE.type)
        * table.getCbMultiple()[BettingDecision.DRAGON_TIGER_TIE.type] > (table.playerareaBets(
            BettingDecision.DRAGON.type) + table.playerareaBets(BettingDecision.TIGER.type))
        && !controlSeatWin) {
      dt = BettingDecision.DRAGON_TIGER_TIE;

    } else {
      BettingDecision otherdt = !win ? BettingDecision.DRAGON : BettingDecision.TIGER;

      if (controlSeatWin) {
        if (table.playerareaBets(dt.type) * table.getCbMultiple()[dt.type]
            - table.playerareaBets(otherdt.type) * table.getCbMultiple()[otherdt.type] >= 0) {
          dt = otherdt;
        }
      } else {
        if (table.playerareaBets(dt.type) * table.getCbMultiple()[dt.type]
            - table.playerareaBets(otherdt.type) * table.getCbMultiple()[otherdt.type] <= 0) {
          dt = otherdt;
        }

      }
    }

    /**
     * step3 庄闲和判断.
     */

    controlSeatWin = control != null ? RandCodeUtil
        .random(control.getLoseRatio() + control.getWinRatio()) >= control.getWinRatio()
        : RandCodeUtil.randomBoolean();

    // 0闲赢1庄赢2和

    long total = table.playerareaBets(BettingDecision.PLAYER.type)
        + table.playerareaBets(BettingDecision.BANK.type)
        + table.playerareaBets(BettingDecision.TIE.type);

    int result = -1;

    for (int i = 0; i <= 100000; i++) {
      result = RandCodeUtil.random(0, 2);
      double multi = table.getCbMultiple()[result] + 1;
      if (table.playerareaBets(result) * multi >= total && !controlSeatWin) {
        break;
      }
      if (table.playerareaBets(result) * multi < total && controlSeatWin) {
        break;
      }
    }

    // 开和不让开和
    if (result == 2 && !controlTie && control != null) {
      for (int i = 0; i <= 100000; i++) {
        result = RandCodeUtil.random(0, 1);
        double multi = table.getCbMultiple()[result] + 1;
        if (table.playerareaBets(result) * multi >= total && !controlSeatWin) {
          break;
        }
        if (table.playerareaBets(result) * multi < total && controlSeatWin) {
          break;
        }
      }
    }

    BettingDecision pbt = null;
    switch (result) {
      case 0:
        pbt = BettingDecision.BANK;
        break;
      case 1:
        pbt = BettingDecision.PLAYER;
        break;
      case 2:
        pbt = BettingDecision.TIE;
        break;
      default:
        break;
    }
    find(table, sb, dt, pbt);

  }

  /**
   * 配牌 .
   * 
   * 
   * @param table .
   * @param sb .
   * @param dt .
   * @param pbt .
   */
  public void find(BaccaratTable table, BettingDecision sb, BettingDecision dt,
      BettingDecision pbt) {

    List<BaccaratCard> player = Lists.newArrayList();
    List<BaccaratCard> banker = Lists.newArrayList();
    int index = RandCodeUtil.random(0, table.getCards().size() - 2);
    // 闲家第一张
    BaccaratCard pcard = table.getCards().get(index);
    player.add(pcard);
    // PS 若果有人拿到这张牌了,先将该张牌移除,然后再去拿其他牌
    for (BaccaratCard baccaratCard : table.getCards()) {
      if (dt == BettingDecision.DRAGON) {
        if (baccaratCard.power > pcard.power) {
          banker.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      } else if (dt == BettingDecision.TIGER) {
        if (baccaratCard.power < pcard.power) {
          banker.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      } else if (dt == BettingDecision.DRAGON_TIGER_TIE) {
        if (baccaratCard.power == pcard.power) {
          banker.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }
    }
    if (banker.size() == 0) {
      BaccaratCard baBanker = table.getCards().get(index + 1);
      banker.add(baBanker);
      table.getCards().remove(baBanker);
    }

    if ((dt == BettingDecision.DRAGON && banker.get(0).power < player.get(0).power)
        || (dt == BettingDecision.TIGER && banker.get(0).power > player.get(0).power)) {
      BaccaratCard ba = banker.remove(0);
      BaccaratCard pl = player.remove(0);
      banker.add(0, pl);
      player.add(0, ba);
    }
    int ppoint = RandCodeUtil.random(7); // 闲家
    int bpoint = 0; // 庄家

    int randPlayerPoint;
    int randBankerPoint = 0;

    if (sb == BettingDecision.BIG) {
      randPlayerPoint = RandCodeUtil.random(7);
      if (randPlayerPoint < 6) {
        if (randPlayerPoint < 2) {
          randBankerPoint = RandCodeUtil.random(2, 7);
        } else {
          randBankerPoint =
              RandCodeUtil.randomBoolean() ? RandCodeUtil.random(0, randPlayerPoint - 1)
                  : RandCodeUtil.random(randPlayerPoint + 1, 7);
        }

      } else {
        randBankerPoint = RandCodeUtil.random(0, randPlayerPoint - 1);
      }

      // 此处可能出现随机到同一个点数

      if (pbt == BettingDecision.BANK) {
        if (randBankerPoint < randPlayerPoint) {
          int temp = randBankerPoint;
          randBankerPoint = randPlayerPoint;
          randPlayerPoint = temp;
        }

      } else if (pbt == BettingDecision.PLAYER) {
        if (randBankerPoint > randPlayerPoint) {
          int temp = randBankerPoint;
          randBankerPoint = randPlayerPoint;
          randPlayerPoint = temp;
        }
      } else {
        randBankerPoint = randPlayerPoint;
      }
      // 前面两张牌点数
      if (ppoint < 6) {
        bpoint = RandCodeUtil.random(7);
      } else if (ppoint == 6) {
        bpoint = RandCodeUtil.random(6, 7);
      } else if (ppoint == 7) {
        bpoint = RandCodeUtil.random(5);
      }

      if (pbt == BettingDecision.BANK) {
        if (bpoint < ppoint) {
          int temp = bpoint;
          bpoint = ppoint;
          ppoint = temp;
        }

      } else if (pbt == BettingDecision.PLAYER) {
        if (bpoint > ppoint) {
          int temp = bpoint;
          bpoint = ppoint;
          ppoint = temp;
        }
      } else {
        bpoint = ppoint;
      }

    } else {
      randPlayerPoint = RandCodeUtil.random(7);
      if (pbt == BettingDecision.BANK) {
        randPlayerPoint = RandCodeUtil.random(6, 8);
        switch (randPlayerPoint) {
          case 6:
            randBankerPoint = RandCodeUtil.random(7, 9);
            break;
          case 7:
            randBankerPoint = RandCodeUtil.random(8, 9);
            break;
          case 8:
            randBankerPoint = 9;
            break;
          default:
            break;
        }

        if (randPlayerPoint > randBankerPoint) {
          int temp = randPlayerPoint;
          randBankerPoint = randPlayerPoint;
          randPlayerPoint = temp;
        }

      } else if (pbt == BettingDecision.PLAYER) {
        randPlayerPoint = RandCodeUtil.random(8, 9);
        switch (randPlayerPoint) {
          case 8:
            randBankerPoint = RandCodeUtil.random(0, 7);
            break;
          case 9:
            randBankerPoint = RandCodeUtil.random(0, 8);
            break;
          default:
            break;
        }
        if (randPlayerPoint < randBankerPoint) {
          int temp = randPlayerPoint;
          randBankerPoint = randPlayerPoint;
          randPlayerPoint = temp;
        }

      } else {
        randBankerPoint = randPlayerPoint = RandCodeUtil.random(7, 9);

      }
    }

    reNewCard(table, player, banker, randPlayerPoint, randBankerPoint, sb, pbt, false, null, bpoint,
        ppoint, -1);
    randPlayerPoint = CardsTypeGetter.getCardPoint(player);
    randBankerPoint = CardsTypeGetter.getCardPoint(banker);
    int diff = Math.abs(randBankerPoint - randPlayerPoint);
    boolean flag = player.size() == 2 && banker.size() == 2;

    int mulit = table.dragonMuliti(Math.abs(diff), flag, randPlayerPoint, randBankerPoint) + 2;
    // 闲赢

    if (randPlayerPoint >= randBankerPoint) {
      if (table.playerareaBets(BettingDecision.PLAYER_DRAGON.type) * mulit > table.dragonTotal()
          && diff > 1) {
        randBankerPoint += 1;
        reNewCard(table, player, banker, randPlayerPoint, randBankerPoint, sb, pbt, true, banker,
            bpoint, ppoint, randBankerPoint);

      }

    } else {
      if (table.playerareaBets(BettingDecision.BANKER_DRAGON.type) * mulit > table.dragonTotal()) {
        randPlayerPoint += 1;
        reNewCard(table, player, banker, randPlayerPoint, randBankerPoint, sb, pbt, true, player,
            bpoint, ppoint, randPlayerPoint);

      }
    }

    table.setCardIndex(table.getCardIndex() + player.size() + banker.size());
    table.getNormalPlayer().addAll(player);
    table.getBanker().addAll(banker);

    dealNoFitCards(table);

  }

  /**
   * 取牌 .
   * 
   * 
   * @param table .
   * @param player 玩家.
   * @param banker .
   * @param randPlayerPoint .
   * @param randBankerPoint .
   */
  public void reNewCard(BaccaratTable table, List<BaccaratCard> player, List<BaccaratCard> banker,
      int randPlayerPoint, int randBankerPoint, BettingDecision sb, BettingDecision pbt,
      boolean renew, List<BaccaratCard> cards, int bankerPoint, int playerPoint, int tpoint) {

    if (renew) {
      table.getCards().add(cards.remove(cards.size() - 1));
      int point = CardsTypeGetter.getCardPoint(cards);
      for (BaccaratCard baccaratCard : table.getCards()) {
        if (((point + baccaratCard.num) % 10) == tpoint) {
          cards.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }
      return;

    }

    if (sb == BettingDecision.SMALL) {
      for (BaccaratCard baccaratCard : table.getCards()) {
        if ((player.get(0).num + baccaratCard.num) % 10 == randPlayerPoint) {
          player.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }

      for (BaccaratCard baccaratCard : table.getCards()) {
        if ((banker.get(0).num + baccaratCard.num) % 10 == randBankerPoint) {
          banker.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }
    } else if (sb == BettingDecision.BIG) { // 是否需要博牌

      int ppoint = CardsTypeGetter.getCardPoint(player);
      int bpoint = CardsTypeGetter.getCardPoint(banker);

      // 闲家第二张牌
      for (BaccaratCard baccaratCard : table.getCards()) {
        if ((ppoint + baccaratCard.num) % 10 == playerPoint) {
          player.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }

      // 庄家第二张牌
      for (BaccaratCard baccaratCard : table.getCards()) {
        if ((bpoint + baccaratCard.num) % 10 == bankerPoint) {
          banker.add(baccaratCard);
          table.getCards().remove(baccaratCard);
          break;
        }
      }

      ppoint = CardsTypeGetter.getCardPoint(player);
      bpoint = CardsTypeGetter.getCardPoint(banker);
      boolean hasPlayerThirdCard = false;
      int playerThirdCardPower = -1;

      if (player.size() == BaccaratMgr.PLAYER_CARDS + 1) {
        hasPlayerThirdCard = true;
        playerThirdCardPower = player.get(2).num % 10;
      }

      // 闲家第三张牌
      if (bpoint < 8 && ppoint <= 5 && player.size() == BaccaratMgr.PLAYER_CARDS) {
        for (BaccaratCard baccaratCard : table.getCards()) {
          if ((ppoint + baccaratCard.num) % 10 == randPlayerPoint) {
            player.add(baccaratCard);
            table.getCards().remove(baccaratCard);
            hasPlayerThirdCard = true;
            playerThirdCardPower = baccaratCard.num % 10;
            break;
          }
        }
      }

      // 闲家前两张牌
      int tempPlayerPoint = 99;
      if (player.size() >= 2) {
        tempPlayerPoint = CardsTypeGetter.getCardPoint(player.subList(0, 2));
      }

      bpoint = CardsTypeGetter.getCardPoint(banker);
      if (tempPlayerPoint < 8 && bpoint < 8 && banker.size() == BaccaratMgr.PLAYER_CARDS) {
        if (player.size() == 2) {
          randBankerPoint = bankerPoint;
        }
        switch (bpoint) {
          case 0:
            for (BaccaratCard baccaratCard : table.getCards()) {
              if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                banker.add(baccaratCard);
                table.getCards().remove(baccaratCard);
                break;
              }
            }
            break;
          case 1:
            for (BaccaratCard baccaratCard : table.getCards()) {
              if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                banker.add(baccaratCard);
                table.getCards().remove(baccaratCard);
                break;
              }
            }
            break;
          case 2:
            for (BaccaratCard baccaratCard : table.getCards()) {
              if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                banker.add(baccaratCard);
                table.getCards().remove(baccaratCard);
                break;
              }
            }
            break;
          case 3:
            if (hasPlayerThirdCard && playerThirdCardPower != 8) {
              for (BaccaratCard baccaratCard : table.getCards()) {
                if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                  banker.add(baccaratCard);
                  table.getCards().remove(baccaratCard);
                  break;
                }
              }
            }
            break;
          case 4:
            if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 8
                && playerThirdCardPower != 9 && playerThirdCardPower != 0) {
              for (BaccaratCard baccaratCard : table.getCards()) {
                if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                  banker.add(baccaratCard);
                  table.getCards().remove(baccaratCard);
                  break;
                }
              }
            }
            break;
          case 5:
            if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 2
                && playerThirdCardPower != 3 && playerThirdCardPower != 8
                && playerThirdCardPower != 9 && playerThirdCardPower != 0) {
              for (BaccaratCard baccaratCard : table.getCards()) {
                if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                  banker.add(baccaratCard);
                  table.getCards().remove(baccaratCard);
                  break;
                }
              }
            }
            break;
          case 6:
            if (hasPlayerThirdCard && (playerThirdCardPower == 6 || playerThirdCardPower == 7)) {
              for (BaccaratCard baccaratCard : table.getCards()) {
                if ((bpoint + baccaratCard.num) % 10 == randBankerPoint) {
                  banker.add(baccaratCard);
                  table.getCards().remove(baccaratCard);
                  break;
                }
              }
            }
            break;
          default:
            break;

        }
      }
      bpoint = CardsTypeGetter.getCardPoint(banker);

      // 庄家没有补牌,没达到点数要求 - 改变闲家点数
      if (bpoint != randBankerPoint || banker.size() == 2) {
        BaccaratCard card = player.remove(player.size() - 1);
        table.getCards().add(card);
        for (BaccaratCard baccaratCard : table.getCards()) {
          if ((ppoint + baccaratCard.num) % 10 == playerPoint) {
            player.add(baccaratCard);
            table.getCards().remove(baccaratCard);
            break;
          }
        }
      }
    }
  }

  /**
   * 结算房间奖池 .
   *
   * @param table .
   */
  public void balanceControl(BaccaratTable table) {
    long gold = convertGold(table.getRoom().getRoomDomain(), table.getEarn());
    RoomPrizePool seatRoomPrizePool =
        roomPrizePoolMgr.defaultRoomPrizePool(Game.BACCARAT, table.getRoom().getId());
    if (seatRoomPrizePool != null) {
      long prize = seatRoomPrizePool.getPrize().get();
      prize += gold;
      seatRoomPrizePool.getPrize().getAndSet(prize);
    }
  }

  /**
   * 筹码换算金币,只会是多个金币等于一个筹码.
   * 
   * @param chips .
   */
  public long convertGold(BaccaratRoomDomain roomDomain, long chips) {
    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();
  }



  // /**
  // * .
  // *
  // * @param table .
  // */
  // private void dealCards2(BaccaratTable table) {
  // int cardIndex = table.getCardIndex();
  // /**
  // * 先发两张手牌 .
  // */
  // if (cardIndex + 12 > table.getCards().size()) {
  // cardIndex = 0;
  // }
  // List<BaccaratCard> cards1 = Lists.newArrayList();
  // List<BaccaratCard> cards2 = Lists.newArrayList();
  // cards1.addAll(table.getCards().subList(cardIndex, cardIndex += BaccaratMgr.PLAYER_CARDS));
  // cards2.addAll(table.getCards().subList(cardIndex, cardIndex += BaccaratMgr.PLAYER_CARDS));
  //
  //
  // int normalPoint2 = CardsTypeGetter.getCardPoint(cards1);
  // int bankerPoint = CardsTypeGetter.getCardPoint(cards2);
  //
  // // 闲家补牌
  // BaccaratCard normalThirdCard = null;
  // if (normalPoint2 <= 5 && bankerPoint < 8) {
  // BaccaratCard baccaratCardPlayer = table.getCards().get(cardIndex);
  // cards1.add(baccaratCardPlayer);
  // cardIndex += 1;
  // }
  //
  // // 庄家补牌
  //
  // BaccaratCard baccaratCardBanker = table.getCards().get(cardIndex);
  // if (normalPoint2 < 8 && bankerPoint < 8) {
  // int thirdPoint = normalThirdCard.num % 10;
  // switch (bankerPoint) {
  // case 0:
  // cards2.add(baccaratCardBanker);
  // break;
  // case 1:
  // cards2.add(baccaratCardBanker);
  // break;
  // case 2:
  // cards2.add(baccaratCardBanker);
  // break;
  // case 3:
  // if (thirdPoint != 8) {
  // cards2.add(baccaratCardBanker);
  // }
  // break;
  // case 4:
  // if (thirdPoint != 1 && thirdPoint != 8 && thirdPoint != 9 && thirdPoint != 0) {
  // cards2.add(baccaratCardBanker);
  // }
  // break;
  // case 5:
  // if (thirdPoint != 1 && thirdPoint != 2 && thirdPoint != 3 && thirdPoint != 8
  // && thirdPoint != 9 && thirdPoint != 0) {
  // cards2.add(baccaratCardBanker);
  // }
  // break;
  // case 6:
  // if (cards1.size() > 2 && (thirdPoint == 6 || thirdPoint == 7)) {
  // cards2.add(baccaratCardBanker);
  // }
  // break;
  // case 7:
  // break;
  // case 8:
  // break;
  // case 9:
  // break;
  //
  // default:
  // break;
  // }
  //
  // }
  // table.setCardIndex(table.getCardIndex() + cards1.size() + cards2.size());
  // table.getNormalPlayer().addAll(cards1);
  // table.getBanker().addAll(cards2);
  //
  // }

}
