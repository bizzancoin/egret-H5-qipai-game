
package com.idealighter.game.games.errenniuniu.common;

import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeComparator;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeGetter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * 牛牛牌型比较单元测试.
 */
public class NiuNiuCardsTypeComparatorTest {

  @Test
  public void testWuNiu() {
    List<NiuNiuCard> myCards = new ArrayList<>();
    myCards.add(NiuNiuCard.MEI_HUA_ER);
    myCards.add(NiuNiuCard.FANG_KUAI_SAN);
    myCards.add(NiuNiuCard.HEI_TAO_SI);
    myCards.add(NiuNiuCard.HEI_TAO_WU);
    myCards.add(NiuNiuCard.HONG_TAO_LIU);

    final NiuNiuCardsType myCardsType =
        NiuNiuCardsTypeGetter.getCardsType(myCards, new ArrayList<>());

    List<NiuNiuCard> dealerCards = new ArrayList<>();
    dealerCards.add(NiuNiuCard.MEI_HUA_ER);
    dealerCards.add(NiuNiuCard.FANG_KUAI_SAN);
    dealerCards.add(NiuNiuCard.HEI_TAO_SI);
    dealerCards.add(NiuNiuCard.HEI_TAO_WU);
    dealerCards.add(NiuNiuCard.DA_WANG);

    NiuNiuCardsType dealerCardsType =
        NiuNiuCardsTypeGetter.getCardsType(dealerCards, new ArrayList<>());

    Assert.assertEquals(true,
        NiuNiuCardsTypeComparator.compare(myCards, myCardsType, dealerCards, dealerCardsType));
  }

}
