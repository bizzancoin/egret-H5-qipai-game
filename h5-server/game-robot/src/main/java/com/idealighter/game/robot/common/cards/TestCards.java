package com.idealighter.game.robot.common.cards;

// package com.idealighter.game.robot.common.cards;
//
// import com.google.common.collect.Lists;
// import com.idealighter.game.dictionary.domain.TexapokerRobotRulesDomain;
//
// import java.util.List;
//
/// **
// * Created by Administrator on 2015/11/20.
// */
// public class TestCards {
//
//
//// public TexapokerRobotRulesDomain domain = new TexapokerRobotRulesDomain();
// public static void main(String[] args) {
//
//// TexapokerRobotRulesDomainTest domain = new TexapokerRobotRulesDomainTest();
// TexapokerRobotRulesDomain domain = new TexapokerRobotRulesDomain();
// DeckPowerCompare compare = new DeckPowerCompare();
// TestCards testCards = new TestCards();
// List<Card> cardList = Lists.newArrayList();
// cardList.addAll(testCards.testCommonCard());
// cardList.addAll(testCards.testhandCard());
//// HandPowerRankerTest handPowerRanker = new HandPowerRankerTest();
// compare.isHave(cardList, testCards.testhandCard(), domain);
// domain.toString();
//
// }
//
//
// private List<Card> testCards(int Num) {
// List<Card> list = Lists.newArrayList();
// for (int i = 0; i < Num; i++) {
// Card card = new Card(CardSuit.CLUB, CardNumber.ACE);
// list.add(card);
// }
// return list;
// }
//
// /**
// * 公牌
// * @return .
// */
// private List<Card> testCommonCard() {
// List<Card> list = Lists.newArrayList();
// Card card = new Card(CardSuit.CLUB, CardNumber.NINE);
// Card card1 = new Card(CardSuit.HEART, CardNumber.NINE);
// Card card3 = new Card(CardSuit.SPADE, CardNumber.NINE);
// Card card4 = new Card(CardSuit.CLUB, CardNumber.SEVEN);
// Card card5 = new Card(CardSuit.SPADE, CardNumber.SIX);
//// list.add(card);
// list.add(card1);
// list.add(card3);
// list.add(card4);
// list.add(card5);
// return list;
// }
//
// /**
// * 手牌
// * @return .
// */
// private List<Card> testhandCard() {
// List<Card> list = Lists.newArrayList();
// Card card4 = new Card(CardSuit.HEART, CardNumber.EIGHT);
// Card card5 = new Card(CardSuit.HEART, CardNumber.SIX);
// list.add(card4);
// list.add(card5);
// return list;
// }
//
// }
