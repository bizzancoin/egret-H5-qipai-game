package com.idealighter.game.robot.common.cards;

public enum CardNumber {
  ONE("1", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7",
      7), EIGHT("8", 8), NINE("9",
          9), TEN("10", 10), JACK("J", 11), QUEEN("Q", 12), KING("K", 13), ACE("A", 14);

  private final String symbol;
  private final int power;

  private CardNumber(String symbol, int power) {
    this.symbol = symbol;
    this.power = power;
  }

  @Override
  public String toString() {
    return symbol;
  }

  /**
   * 2-14.
   *
   * @return .
   */
  public int getPower() {
    return power;
  }

  /**
   * .
   * 
   * @param power .
   * @return .
   */
  public static CardNumber cardNumber(int power) {
    CardNumber cardNumber = null;
    switch (power) {
      case 2:
        cardNumber = CardNumber.TWO;
        break;
      case 3:
        cardNumber = CardNumber.THREE;
        break;
      case 4:
        cardNumber = CardNumber.FOUR;
        break;
      case 5:
        cardNumber = CardNumber.FIVE;
        break;
      case 6:
        cardNumber = CardNumber.SIX;
        break;
      case 7:
        cardNumber = CardNumber.SEVEN;
        break;
      case 8:
        cardNumber = CardNumber.EIGHT;
        break;
      case 9:
        cardNumber = CardNumber.NINE;
        break;
      case 10:
        cardNumber = CardNumber.TEN;
        break;
      case 11:
        cardNumber = CardNumber.JACK;
        break;
      case 12:
        cardNumber = CardNumber.QUEEN;
        break;
      case 13:
        cardNumber = CardNumber.KING;
        break;
      case 14:
        cardNumber = CardNumber.ACE;
        break;
      default:
        break;
    }
    return cardNumber;

  }


  /**
   * card.
   * 
   * @param card 卡牌.
   * @return .
   */
  public static CardNumber getCardNum(int card) {
    // if (cardId == 0) {
    // return CardNumber.ACE;
    // }
    // int power = cardId % 13;
    // if (power == 0) {
    // power = 14;
    // }
    // if (power > 14 || power < 2) {
    // return null;
    // }
    CardNumber cardNumber = null;
    switch (card) {
      case 0:
        cardNumber = CardNumber.ACE;
        break;
      case 1:
        cardNumber = CardNumber.TWO;
        break;
      case 2:
        cardNumber = CardNumber.THREE;
        break;
      case 3:
        cardNumber = CardNumber.FOUR;
        break;
      case 4:
        cardNumber = CardNumber.FIVE;
        break;
      case 5:
        cardNumber = CardNumber.SIX;
        break;
      case 6:
        cardNumber = CardNumber.SEVEN;
        break;
      case 7:
        cardNumber = CardNumber.EIGHT;
        break;
      case 8:
        cardNumber = CardNumber.NINE;
        break;
      case 9:
        cardNumber = CardNumber.TEN;
        break;
      case 10:
        cardNumber = CardNumber.JACK;
        break;
      case 11:
        cardNumber = CardNumber.QUEEN;
        break;
      case 12:
        cardNumber = CardNumber.KING;
        break;
      default:
        break;
    }
    return cardNumber;
  }
}
