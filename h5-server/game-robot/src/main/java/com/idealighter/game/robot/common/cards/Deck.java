package com.idealighter.game.robot.common.cards;

/**
 * Created by Administrator on 2015/10/28.
 */

import com.google.common.collect.Lists;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class Deck {
  private final List<Card> cards = Lists.newArrayList();

  /**
   * 创建card.
   */
  public Deck() {
    for (CardSuit suit : CardSuit.values()) {
      for (CardNumber number : CardNumber.values()) {
        Card card = new Card(suit, number);
        cards.add(card);
      }
    }

    Collections.shuffle(cards);
  }

  public List<Card> getCards() {
    return cards;
  }

  public Card removeTopCard() {
    return cards.remove(0);
  }

  public boolean removeCard(Card card) {
    return cards.remove(card);
  }

  /**
   * .
   * 
   * @return .
   */
  public List<List<Card>> fromDeckToCouplesOfCard() {
    List<List<Card>> couplesOfCard = new ArrayList<List<Card>>();
    for (int i = 0; i < this.cards.size(); i++) {
      for (int j = i + 1; j < this.cards.size(); j++) {
        List<Card> tmpCards = new ArrayList<Card>();
        tmpCards.add(this.cards.get(i));
        tmpCards.add(this.cards.get(j));
        couplesOfCard.add(tmpCards);
      }
    }
    return couplesOfCard;
  }

  /**
   * .
   * 
   * @param suit .
   * @param number .
   * @return .
   */
  public Card card(CardSuit suit, CardNumber number) {
    for (Card card : cards) {
      if (card.getSuit() == suit && card.getNumber() == number) {
        return card;
      }
    }
    return null;
  }
}
