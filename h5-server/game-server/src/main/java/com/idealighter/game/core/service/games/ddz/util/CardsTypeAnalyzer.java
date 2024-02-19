package com.idealighter.game.core.service.games.ddz.util;

import com.google.common.collect.TreeMultimap;
import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * 牌型分析器，主要用于ai .
 * 
 * @date 2016年4月14日 上午11:09:21
 *
 */
public class CardsTypeAnalyzer {
  private CardsTypeAnalyzer() {}

  /**
   * 从fromCards中分析出王炸 .
   * 
   * @param fromCards .
   * @return .
   */
  public static List<DdzCard> analysisWangZha(TreeMultimap<Integer, DdzCard> fromCards) {

    return CardsTypeFinder.findWangZha(fromCards);
  }

  /**
   * 分析出炸弹 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMultimap<Integer, DdzCard> analysisZhanDans(
      TreeMultimap<Integer, DdzCard> fromCards) {
    TreeMultimap<Integer, DdzCard> zhanDans = TreeMultimap.create();
    for (Integer cardNum : fromCards.keySet()) {
      NavigableSet<DdzCard> cards = fromCards.get(cardNum);
      if (cards.size() == 4) {
        zhanDans.putAll(cardNum, cards);
      }
    }

    return zhanDans;
  }

  /**
   * 从fromCards中分析出三条 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMultimap<Integer, DdzCard> analysisSanTiaos(
      TreeMultimap<Integer, DdzCard> fromCards) {
    TreeMultimap<Integer, DdzCard> sanTiaos = TreeMultimap.create();
    for (Integer cardNum : fromCards.keySet()) {
      NavigableSet<DdzCard> cards = fromCards.get(cardNum);
      if (cards.size() == 3) {
        sanTiaos.putAll(cardNum, cards);
      }
    }

    return sanTiaos;
  }

  /**
   * 从fromCards中分析出顺子 . 单顺的确定 a) 选取五连，先取出最小的一个五连，再在剩余的牌中取出最小的一个五连，依此类推，直到没有五连为止。 b)
   * 扩展五连，将剩余的牌与已经取出的牌进行比对，如果某张剩余的牌与已知的连牌能组成更大的连牌，则将其合并。一直到无法合并为止。 . c)
   * 合并连牌，如果某两组连牌能无缝连接成更大的连牌，则将其合并成一组。 . 经过上述选取、扩展和合并，则将一手牌中的所有连牌提取出来了， 举例如下：
   * 假定一手牌是：2AKQJ1099877766543 . 第一步，选取出34567，678910两个连牌组。剩余的牌还有79JQKA2
   * 第二步，剩余的JQKA能和678910组成新的连牌678910JQKA。 .
   * 第三步，已知的两个连牌组不能合并成新的、更大的连牌组，则这手牌就被分成了34567、678910JQKA两个连牌组和7、9、2三张单牌。 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMultimap<DdzCard, DdzCard> analysisShunZis(
      TreeMultimap<Integer, DdzCard> fromCards) {
    /*
     * 选取五连，先取出最小的一个五连，再在剩余的牌中取出最小的一个五连，依此类推，直到没有五连为止 .
     */
    // key为顺子的第一张牌
    TreeMultimap<DdzCard, DdzCard> shunZis = TreeMultimap.create();
    TreeMultimap<Integer, DdzCard> analysisCards = TreeMultimap.create(fromCards);

    if (fromCards.size() == 0) {
      return shunZis;
    }

    // 分析顺子时的最小牌num
    int analysisMinCardNum = analysisCards.keySet().first();

    for (; analysisMinCardNum < DdzCard.FANG_KUAI_J.num;) { // minCardNum小于J才能组成连顺
      List<DdzCard> shunZi = new ArrayList<>(5);
      for (int i = 0; i <= DdzCard.FANG_KUAI_A.num - analysisMinCardNum; i++) {
        int cardNum = analysisMinCardNum + i;
        NavigableSet<DdzCard> cards = analysisCards.get(cardNum);

        if (cards.size() == 0) {
          analysisMinCardNum = cardNum + 1;
          break;
        } else {
          DdzCard card = cards.first();
          shunZi.add(card);

          if (shunZi.size() == 5) {
            shunZis.putAll(shunZi.get(0), shunZi);
            analysisCards.values().removeAll(shunZi);
            break;
          }
        }
      }
    }

    /*
     * 扩展五连，将剩余的牌与已经取出的牌进行比对，如果某张剩余的牌与已知的连牌能组成更大的连牌，则将其合并。一直到无法合并为止 .
     */
    for (DdzCard fisrtCard : shunZis.keySet()) {
      // 顺子的最小牌num
      Integer minCardNum = fisrtCard.num;
      for (int i = 5; i <= DdzCard.FANG_KUAI_A.num - minCardNum; i++) {
        NavigableSet<DdzCard> cards = analysisCards.get(minCardNum + i);
        if (cards.size() > 0) {
          DdzCard card = cards.first();
          shunZis.put(fisrtCard, card);
          analysisCards.values().remove(card);
        } else {
          break;
        }
      }
    }

    /*
     * 合并连牌，如果某两组连牌能无缝连接成更大的连牌，则将其合并成一组 .
     */
    Set<DdzCard> mergedFisrtCards = new HashSet<>();
    for (DdzCard fisrtCard : shunZis.keySet()) {
      // 顺子的最小牌num
      Integer minCardNum = fisrtCard.num;
      NavigableSet<DdzCard> cards = shunZis.get(fisrtCard);
      for (DdzCard mergedFisrtCard : shunZis.keySet()) {
        Integer mergedMinCardNum = mergedFisrtCard.num;
        NavigableSet<DdzCard> mergedCards = shunZis.get(mergedFisrtCard);

        if (minCardNum + cards.size() == mergedMinCardNum
            && !mergedFisrtCards.contains(mergedFisrtCard)) { // 头尾相接合并,最多合并一次
          cards.addAll(mergedCards);
          mergedFisrtCards.add(mergedFisrtCard);
          break;
        }
      }
    }

    for (DdzCard mergedFisrtCard : mergedFisrtCards) {
      shunZis.removeAll(mergedFisrtCard);
    }

    /*
     * 单顺全部配完之后，从最小单顺开始，计算手牌中，除该单顺外其余该单顺中牌值的牌总数，设为z， . 则当z不小于（m/2）+1时，该单顺解散，分为单牌和对子。当z小于（m/2）+1时，
     * 该单顺配牌完成。（备注：当m/2不为整数时，取整数位+1。例如当m=7，则m/2=3+1=4） .
     */
    for (DdzCard fisrtCard : shunZis.keySet()) {
      // 顺子对应的散牌数量
      int scatterNum = 0;
      NavigableSet<DdzCard> shunZiCards = shunZis.get(fisrtCard);
      for (DdzCard shunZiCard : shunZiCards) {
        scatterNum += analysisCards.get(shunZiCard.num).size();
      }

      if (scatterNum >= shunZiCards.size() / 2 + 1) {
        shunZis.removeAll(fisrtCard);
        break;
      }
    }

    return shunZis;
  }

  /**
   * 从fromCards中分析出双顺 . 有3组或3组以上相连的对子，有则将其组合成双顺。 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMultimap<Integer, DdzCard> analysisShuangShuns(
      TreeMultimap<Integer, DdzCard> fromCards) {
    // key为顺子的第一张牌的数字
    TreeMultimap<Integer, DdzCard> shuangShuns = TreeMultimap.create();
    if (fromCards.size() == 0) {
      return shuangShuns;
    }

    // 分析顺子时的最小牌num
    int minCardNum = fromCards.keySet().first();

    for (; minCardNum < DdzCard.FANG_KUAI_J.num;) {
      List<DdzCard> shuangShun = new ArrayList<>();
      for (int i = 0; i <= DdzCard.FANG_KUAI_A.num - minCardNum; i++) {
        int cardNum = minCardNum + i;
        NavigableSet<DdzCard> cards = fromCards.get(cardNum);

        if (cards.size() < 2) {
          minCardNum = cardNum + 1;
          break;
        } else {
          DdzCard firstCard = cards.first();
          DdzCard lastCard = cards.last();
          if (!shuangShun.contains(firstCard)) {
            shuangShun.add(firstCard);
          }
          if (!shuangShun.contains(lastCard)) {
            shuangShun.add(lastCard);
          }
          // shuangShun.add(firstCard);
          // shuangShun.add(lastCard);
          // cards.remove(firstCard);
          // cards.remove(lastCard);
        }
      }

      if (shuangShun.size() > 5) {
        shuangShuns.putAll(shuangShun.get(0).num, shuangShun);
      }
    }

    return shuangShuns;
  }

  /**
   * 从fromCards中分析出对子 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMultimap<Integer, DdzCard> analysisDuiZis(
      TreeMultimap<Integer, DdzCard> fromCards) {
    // key为顺子的第一张牌的数字
    TreeMultimap<Integer, DdzCard> duiZis = TreeMultimap.create();

    for (Integer cardNum : fromCards.keySet()) {
      NavigableSet<DdzCard> cards = fromCards.get(cardNum);
      if (cards.size() == 2) {
        duiZis.putAll(cardNum, cards);
      }
    }

    return duiZis;
  }

  /**
   * 从fromCards分析出单牌 .
   * 
   * @param fromCards .
   * @return .
   */
  public static TreeMap<Integer, DdzCard> analysisDanPais(
      TreeMultimap<Integer, DdzCard> fromCards) {
    TreeMap<Integer, DdzCard> danPais = new TreeMap<>();

    for (Integer cardNum : fromCards.keySet()) {
      NavigableSet<DdzCard> cards = fromCards.get(cardNum);
      if (cards.size() == 1) {
        danPais.put(cardNum, cards.first());
      }
    }

    return danPais;
  }
}
