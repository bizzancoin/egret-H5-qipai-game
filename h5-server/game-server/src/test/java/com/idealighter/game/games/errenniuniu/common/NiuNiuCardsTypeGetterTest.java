
package com.idealighter.game.games.errenniuniu.common;

import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeGetter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 牛牛牌型获取测试.
 */
public class NiuNiuCardsTypeGetterTest {

  /**
   * 测试没牛类型 .
   */
  @Test
  public void testMeiNiu() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_QI);
    cards.add(NiuNiuCard.HONG_TAO_QI);
    cards.add(NiuNiuCard.MEI_HUA_QI);
    cards.add(NiuNiuCard.HEI_TAO_ER);
    cards.add(NiuNiuCard.HONG_TAO_ER);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.MEI_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

  /**
   * 测试牛类型（牛1到牛牛） .
   */
  @Test
  public void testNiu() {
    List<NiuNiuCard> cards = new ArrayList<>();
    final List<NiuNiuCard> bestCards = new ArrayList<>();

    cards.add(NiuNiuCard.HEI_TAO_QI);
    cards.add(NiuNiuCard.HONG_TAO_QI);
    cards.add(NiuNiuCard.HEI_TAO_J);
    cards.add(NiuNiuCard.HEI_TAO_LIU);
    cards.add(NiuNiuCard.HEI_TAO_A);


    Assert.assertSame(NiuNiuCardsType.NIU_1, NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));


    cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_QI);
    cards.add(NiuNiuCard.HONG_TAO_QI);
    cards.add(NiuNiuCard.HEI_TAO_J);
    cards.add(NiuNiuCard.HEI_TAO_LIU);
    cards.add(NiuNiuCard.HONG_TAO_J);

    Assert.assertSame(NiuNiuCardsType.NIU_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

  @Test
  public void tesWuXiao() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_A);
    cards.add(NiuNiuCard.HONG_TAO_A);
    cards.add(NiuNiuCard.MEI_HUA_A);
    cards.add(NiuNiuCard.HEI_TAO_ER);
    cards.add(NiuNiuCard.HONG_TAO_ER);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.WU_XIAO,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

  @Test
  public void testWuHua() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_K);
    cards.add(NiuNiuCard.HONG_TAO_K);
    cards.add(NiuNiuCard.FANG_KUAI_K);
    cards.add(NiuNiuCard.HEI_TAO_Q);
    cards.add(NiuNiuCard.MEI_HUA_Q);

    Assert.assertSame(NiuNiuCardsType.WU_HUA,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));
    cards.clear();
    cards.add(NiuNiuCard.HEI_TAO_K);
    cards.add(NiuNiuCard.HONG_TAO_K);
    cards.add(NiuNiuCard.FANG_KUAI_K);
    cards.add(NiuNiuCard.HEI_TAO_Q);
    cards.add(NiuNiuCard.DA_WANG);

    Assert.assertNotSame(NiuNiuCardsType.WU_HUA,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));
  }

  @Test
  public void testSiZha() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_K);
    cards.add(NiuNiuCard.HONG_TAO_K);
    cards.add(NiuNiuCard.FANG_KUAI_K);
    cards.add(NiuNiuCard.MEI_HUA_K);
    cards.add(NiuNiuCard.DA_WANG);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.SI_ZHA, NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

  @Test
  public void testSiHua() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_K);
    cards.add(NiuNiuCard.HONG_TAO_K);
    cards.add(NiuNiuCard.FANG_KUAI_K);
    cards.add(NiuNiuCard.MEI_HUA_K);
    cards.add(NiuNiuCard.MEI_HUA_SHI);

    Assert.assertSame(NiuNiuCardsType.SI_HUA,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));

    cards.clear();
    cards.add(NiuNiuCard.HEI_TAO_K);
    cards.add(NiuNiuCard.HONG_TAO_K);
    cards.add(NiuNiuCard.FANG_KUAI_K);
    cards.add(NiuNiuCard.MEI_HUA_Q);
    cards.add(NiuNiuCard.DA_WANG);
    Assert.assertNotSame(NiuNiuCardsType.SI_HUA,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));
  }

  @Test
  public void testWuXiao() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_A);
    cards.add(NiuNiuCard.HONG_TAO_A);
    cards.add(NiuNiuCard.FANG_KUAI_A);
    cards.add(NiuNiuCard.MEI_HUA_ER);
    cards.add(NiuNiuCard.MEI_HUA_ER);

    Assert.assertSame(NiuNiuCardsType.WU_XIAO,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));

    cards.clear();
    cards.add(NiuNiuCard.HEI_TAO_A);
    cards.add(NiuNiuCard.HONG_TAO_A);
    cards.add(NiuNiuCard.FANG_KUAI_A);
    cards.add(NiuNiuCard.MEI_HUA_ER);
    cards.add(NiuNiuCard.DA_WANG);
    Assert.assertNotSame(NiuNiuCardsType.WU_XIAO,
        NiuNiuCardsTypeGetter.getCardsType(cards, new ArrayList<>()));
  }

  @Test
  public void testNiuNiu() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_LIU);
    cards.add(NiuNiuCard.HEI_TAO_QI);
    cards.add(NiuNiuCard.MEI_HUA_QI);
    cards.add(NiuNiuCard.HEI_TAO_J);
    cards.add(NiuNiuCard.HONG_TAO_J);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.NIU_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

  /**
   * 测试有一个王的牛 .
   */
  @Test
  public void test1WangNiu() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_LIU);
    cards.add(NiuNiuCard.HEI_TAO_BA);
    cards.add(NiuNiuCard.MEI_HUA_QI);
    cards.add(NiuNiuCard.HEI_TAO_J);
    cards.add(NiuNiuCard.DA_WANG);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.NIU_8, NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));

    cards = new ArrayList<>();
    cards.add(NiuNiuCard.XIAO_WANG);
    cards.add(NiuNiuCard.HEI_TAO_WU);
    cards.add(NiuNiuCard.HEI_TAO_SI);
    cards.add(NiuNiuCard.HONG_TAO_LIU);
    cards.add(NiuNiuCard.HEI_TAO_JIU);

    bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.NIU_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));

  }

  /**
   * 测试有2个王的牛 .
   */
  @Test
  public void test2WangNiu() {
    List<NiuNiuCard> cards = new ArrayList<>();
    cards.add(NiuNiuCard.HEI_TAO_LIU);
    cards.add(NiuNiuCard.HEI_TAO_BA);
    cards.add(NiuNiuCard.MEI_HUA_QI);
    cards.add(NiuNiuCard.XIAO_WANG);
    cards.add(NiuNiuCard.DA_WANG);

    List<NiuNiuCard> bestCards = new ArrayList<>();
    Assert.assertSame(NiuNiuCardsType.NIU_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));

    cards = new ArrayList<>();
    cards.add(NiuNiuCard.DA_WANG);
    cards.add(NiuNiuCard.HEI_TAO_SAN);
    cards.add(NiuNiuCard.HEI_TAO_SI);
    cards.add(NiuNiuCard.HEI_TAO_QI);
    cards.add(NiuNiuCard.HEI_TAO_SHI);
    bestCards.clear();
    Assert.assertSame(NiuNiuCardsType.NIU_NIU,
        NiuNiuCardsTypeGetter.getCardsType(cards, bestCards));
  }

}
