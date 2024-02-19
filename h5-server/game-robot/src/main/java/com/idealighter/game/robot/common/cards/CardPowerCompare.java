package com.idealighter.game.robot.common.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 牌型比较器.
 */
public abstract class CardPowerCompare {

  public final Comparator<CardNumber> cardNumberComparator =
      (cardNumber1, cardNumber2) -> cardNumber1.getPower() - cardNumber2.getPower();

  /**
   * 获取牌型和最优牌 .
   *
   * @param cards . .
   * @return .
   */
  public abstract CardCompare rank(List<Card> cards);


  /**
   * .
   * 
   * @param numberGroup number.
   * @return .
   */
  public List<CardNumber> getFullHouse(MapList<CardNumber, Card> numberGroup) {
    List<CardNumber> fullHouseCardNumbers = new ArrayList<>();

    List<CardNumber> cardNumbers = new ArrayList<>(numberGroup.keySet());
    Collections.sort(cardNumbers, cardNumberComparator);
    Collections.reverse(cardNumbers);

    // Find the best cards for the triple
    for (CardNumber cardNumber : cardNumbers) {
      if (numberGroup.get(cardNumber).size() >= 3) {
        fullHouseCardNumbers.add(cardNumber);
        break;
      }
    }

    // Find the best card for the pair
    if (fullHouseCardNumbers.size() > 0) {
      for (CardNumber cardNumber : cardNumbers) {
        if (numberGroup.get(cardNumber).size() >= 2
            && !cardNumber.equals(fullHouseCardNumbers.get(0))) {
          fullHouseCardNumbers.add(cardNumber);
          break;
        }
      }
    }

    return fullHouseCardNumbers;
  }

  /**
   * .
   * 
   * @param pairsCardNumber .
   * @param cardsSortedByNumber .
   * @return .
   */
  public List<CardNumber> calculateTwoPairsTie(List<CardNumber> pairsCardNumber,
      List<Card> cardsSortedByNumber) {
    Collections.sort(pairsCardNumber, cardNumberComparator);
    Collections.reverse(pairsCardNumber);
    List<CardNumber> tieBreakingInformation = new ArrayList<>(pairsCardNumber);

    for (int i = cardsSortedByNumber.size() - 1; i >= 0; i--) {
      CardNumber cardNumber = cardsSortedByNumber.get(i).getNumber();
      if (!pairsCardNumber.contains(cardNumber)) {
        tieBreakingInformation.add(cardNumber);
        return tieBreakingInformation;
      }
    }
    return tieBreakingInformation;
  }

  /**
   * .
   * 
   * @param numberGroup .
   * @return .
   */
  public List<CardNumber> getPairs(MapList<CardNumber, Card> numberGroup) {
    List<CardNumber> pairsCardNumber = new ArrayList<>();
    for (List<Card> cards : numberGroup) {
      if (cards.size() >= 2) {
        pairsCardNumber.add(cards.get(0).getNumber());
      }
    }
    Collections.sort(pairsCardNumber, cardNumberComparator);
    Collections.reverse(pairsCardNumber);

    if (pairsCardNumber.size() > 2) {
      pairsCardNumber.remove(pairsCardNumber.size() - 1);
    }

    return pairsCardNumber;
  }

  public List<CardNumber> calculateFlushTie(CardSuit flushSuit, MapList<CardSuit, Card> suitGroup) {
    List<Card> cards = suitGroup.get(flushSuit);
    return bestCardsNumberInList(cards);
  }

  /**
   * .
   * 
   * @param cards .
   * @return .
   */
  public List<CardNumber> bestCardsNumberInList(List<Card> cards) {
    // if (cards.size() < 5) {
    // return null;
    // }
    List<CardNumber> cardNumbers = cardsToCardNumber(cards);
    Collections.sort(cardNumbers, cardNumberComparator);
    Collections.reverse(cardNumbers);
    return cardNumbers.subList(0, cards.size());
  }

  /**
   * 对牌进行排序 .
   *
   * @param cards . .
   * @return .
   */
  public List<Card> getCardsSortedByNumber(List<Card> cards) {
    List<Card> cardsSortedByNumber = new ArrayList<Card>(cards);
    Collections.sort(cardsSortedByNumber);

    return cardsSortedByNumber;
  }

  /**
   * .
   * 
   * @param sameKindCount . .
   * @param sameKindCardNumber . .
   * @param cardsSortedByNumber . .
   * @return .
   */
  public List<CardNumber> calculateSameKindTie(Integer sameKindCount, CardNumber sameKindCardNumber,
      List<Card> cardsSortedByNumber) {
    List<CardNumber> tieBreakingInformation = new ArrayList<>();
    tieBreakingInformation.add(sameKindCardNumber);

    int left = 5 - sameKindCount;
    for (int i = cardsSortedByNumber.size() - 1; i >= 0; i--) {
      Card card = cardsSortedByNumber.get(i);

      if (!card.getNumber().equals(sameKindCardNumber) && left > 0) {
        tieBreakingInformation.add(card.getNumber());
        left--;
      }
    }

    return tieBreakingInformation;
  }

  /**
   * .
   * 
   * @param count . .
   * @param numberGroup . .
   * @return .
   */
  public CardNumber getCardNumberForCount(Integer count, MapList<CardNumber, Card> numberGroup) {
    for (Map.Entry<CardNumber, List<Card>> entry : numberGroup.entrySet()) {
      if (entry.getValue().size() >= count) {
        return entry.getKey();
      }
    }
    return null;
  }

  public CardNumber getStraight(MapList<CardNumber, Card> numberGroup, int staightNum) {
    List<CardNumber> cardNumbers = new ArrayList<>(numberGroup.keySet());
    return getStraightNumber(cardNumbers, staightNum);
  }

  /**
   * 是否是同花顺.
   *
   * @param suitGroup . .
   * @param staightNum . .
   * @return .
   */
  public abstract CardNumber getStraightFlushNumber(MapList<CardSuit, Card> suitGroup,
      int staightNum);

  /**
   * cardToCardNumber.
   * 
   * @param cards . 卡片.
   * @return number list.
   */
  public List<CardNumber> cardsToCardNumber(List<Card> cards) {
    List<CardNumber> cardNumbers = new ArrayList<>();

    for (Card card : cards) {
      cardNumbers.add(card.getNumber());
    }
    return cardNumbers;
  }

  /**
   * 顺子判断.
   *
   * @param cardNumberList . .
   * @param staightNum . .
   * @return .
   */
  public abstract CardNumber getStraightNumber(List<CardNumber> cardNumberList, int staightNum);

  /**
   * 是否满足同花条件 .
   *
   * @param suitGroup . .
   * @return .
   */
  public CardSuit getFlush(MapList<CardSuit, Card> suitGroup, int suitNum) {
    for (List<Card> cards : suitGroup) {
      if (cards.size() >= suitNum) {
        return cards.get(0).getSuit();
      }
    }
    return null;
  }

  /**
   * 牌转换成Map结构 .
   *
   * @param cards . .
   * @return .
   */
  public MapList<CardNumber, Card> getNumberGroup(List<Card> cards) {
    MapList<CardNumber, Card> numberGroup = new MapList<>();
    for (Card card : cards) {
      numberGroup.add(card.getNumber(), card);
    }
    return numberGroup;
  }

  /**
   * 牌转换成Map结构 .
   *
   * @param cards . .
   * @return .
   */
  public MapList<CardSuit, Card> getSuitGroup(List<Card> cards) {
    MapList<CardSuit, Card> suitGroup = new MapList<>();
    for (Card card : cards) {
      suitGroup.add(card.getSuit(), card);
    }
    return suitGroup;
  }
}
