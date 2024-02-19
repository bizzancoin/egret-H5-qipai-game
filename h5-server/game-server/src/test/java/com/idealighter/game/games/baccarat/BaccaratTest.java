package com.idealighter.game.games.baccarat;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.service.games.baccarat.manager.BaccaratControlScript;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratRoom;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratTable;
import com.idealighter.game.core.service.games.baccarat.util.CardsTypeGetter;
import com.idealighter.game.dictionary.dic.BaccaratRoomDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;

import org.junit.Assert;
import org.junit.Test;

public class BaccaratTest {


  private boolean checkVaild(BaccaratTable table) {
    int normalPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer().subList(0, 2));
    int bankerPoint = CardsTypeGetter.getCardPoint(table.getBanker().subList(0, 2));

    boolean result = true;

    if (normalPoint >= 8) {
      if (table.getNormalPlayer().size() != 2) {
        System.out.println("玩家牌非法");
        result = false;
      }
      if (table.getBanker().size() != 2) {
        System.out.println("庄家牌非法");
        result = false;
      }
      return result;
    }

    if (bankerPoint >= 8) {
      if (table.getNormalPlayer().size() != 2) {
        System.out.println("玩家牌非法");
        result = false;
      }
      if (table.getBanker().size() != 2) {
        System.out.println("庄家牌非法");
        result = false;
      }
      return result;
    }

    boolean hasPlayerThirdCard = false;
    int playerThirdCardPower = -1;
    // 闲家补牌
    if (normalPoint <= 5) {
      if (table.getNormalPlayer().size() != 3) {
        System.out.println("玩家牌非法");
        result = false;
      } else {
        hasPlayerThirdCard = true;
        playerThirdCardPower = table.getNormalPlayer().get(2).num % 10;
      }

    }

    // 庄家补牌
    // normalPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer());

    if (bankerPoint < 8) {
      switch (bankerPoint) {
        case 0:
        case 1:
        case 2:
          if (table.getBanker().size() != 3) {
            System.out.println("庄家牌非法");
            result = false;
          }
          break;
        case 3:
          if (hasPlayerThirdCard && playerThirdCardPower != 8) {
            if (table.getBanker().size() != 3) {
              System.out.println("庄家牌非法");
              result = false;
            }


          }
          break;
        case 4:
          if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 8
              && playerThirdCardPower != 9 && playerThirdCardPower != 0) {
            if (table.getBanker().size() != 3) {
              System.out.println("庄家牌非法");
              result = false;
            }
          }
          break;
        case 5:
          if (hasPlayerThirdCard && playerThirdCardPower != 1 && playerThirdCardPower != 2
              && playerThirdCardPower != 3 && playerThirdCardPower != 8 && playerThirdCardPower != 9
              && playerThirdCardPower != 0) {
            if (table.getBanker().size() != 3) {
              System.out.println("庄家牌非法");
              result = false;
            }
          }
          break;
        case 6:
          if (hasPlayerThirdCard && (playerThirdCardPower == 6 || playerThirdCardPower == 7)) {
            if (table.getBanker().size() != 3) {
              System.out.println("庄家牌非法");
              result = false;
            }
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

    return result;

  }

  BaccaratControlScript baccaratControlScript = null;

  @Test
  public void testControlDealCards() {

    Assert.assertNotNull(ApplicationContext.getBean(BaccaratControlScript.class));

    Assert.assertNotNull(ApplicationContext.getBean(BaccaratRoomDic.class));

    BaccaratRoomDic baccaratRoomDic = ApplicationContext.getBean(BaccaratRoomDic.class);


    BaccaratRoomDomain roomDom = baccaratRoomDic.list().get(0);
    BaccaratRoom room = new BaccaratRoom(roomDom.getId() * 100, roomDom);
    BaccaratTable table = new BaccaratTable(roomDom.getId() * 100 * 10000, room);

    baccaratControlScript = ApplicationContext.getBean(BaccaratControlScript.class);


    int cardIndex = table.getCardIndex();
    int i = 0;
    while (i < 10000) {
      i++;

      cardIndex = table.getCardIndex();
      baccaratControlScript.controlDealCards(table);
      Assert.assertEquals(cardIndex,
          table.getCardIndex() - table.getBanker().size() - table.getNormalPlayer().size());

      Assert.assertEquals(418, cardIndex + table.getCards().size());

      table.deduceWinner();

      Assert.assertEquals(true, checkVaild(table));

      Assert.assertEquals(true, table.getBanker().size() > 1);

      Assert.assertEquals(true, table.getNormalPlayer().size() > 1);
  
      if (table.getCards().size() < 50) {
        table.washCardRest();
      }

      table.reset();

    }
  }
}
