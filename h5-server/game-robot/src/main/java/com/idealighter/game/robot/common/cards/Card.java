package com.idealighter.game.robot.common.cards;


public class Card implements Comparable<Card> {
  private final CardSuit suit;
  private final CardNumber number;

  public Card(final CardSuit suit, final CardNumber number) {
    this.suit = suit;
    this.number = number;
  }

  @Override
  public String toString() {
    return suit.toString() + number.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Card)) {
      return false;
    }

    Card other = (Card) obj;

    return suit.equals(other.suit) && number.equals(other.number);
  }

  @Override
  public int compareTo(Card card) {
    return number.getPower() - card.number.getPower();
  }

  public CardSuit getSuit() {
    return suit;
  }

  /**
   * 计算卡牌的唯一ID 给客户端展示用.
   *
   * @return .
   */
  public int cardId() {
    int baseId = 0;
    if (suit == CardSuit.DIAMOND) {
      baseId = 0;
    } else if (suit == CardSuit.CLUB) {
      baseId = 13;
    } else if (suit == CardSuit.HEART) {
      baseId = 26;
    } else if (suit == CardSuit.SPADE) {
      baseId = 39;
    }
    if (number.getPower() == 14) { // A做特殊处理
      return baseId;
    }
    return baseId + number.getPower() - 1;
  }


  public static CardSuit cardSuit(int cardId) {
    return CardSuit.getCardSuit(cardId / 13);
  }

  public static CardNumber cardNumber(int cardId) {
    return CardNumber.getCardNum(cardId % 13);
  }


  public CardNumber getNumber() {
    return number;
  }
}
