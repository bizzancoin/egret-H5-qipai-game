package com.idealighter.game.core.service.games.ddz.util;

import com.google.common.collect.TreeMultimap;
import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import com.idealighter.game.core.service.games.ddz.struct.DdzCardsType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.TreeMap;

/**
 * BiggerCardsType查找工具类 .
 * 
 * @date 2015年8月16日 下午1:08:21
 *
 */
public class CardsTypeFinder {

  private CardsTypeFinder() {}

  /**
   * 查找手上最小的牌，查找规则：最小的牌是单牌出单牌，对牌出对牌(不包括王炸)，三牌出三牌，没有继续找，都没有，从炸弹找，最后出王炸 .
   * 
   * @param fromCards .
   * @return .
   */
  public static List<DdzCard> findMinCards(Collection<DdzCard> fromCards) {
    if (fromCards == null || fromCards.size() == 0) {
      throw new IllegalArgumentException("fromCards不能为null且数量大于0");
    }

    // 查询的牌的数字map
    TreeMultimap<Integer, DdzCard> fromCardNums = TreeMultimap.create();
    for (DdzCard fromCard : fromCards) {
      fromCardNums.put(fromCard.num, fromCard);
    }

    // 四个一样的牌(炸弹)
    TreeMultimap<Integer, DdzCard> zhaDans = TreeMultimap.create();
    // 王炸
    boolean hasWangZha = false;

    // 是否含有王炸
    if (fromCardNums.keys().contains(DdzCard.XIAO_WANG.num)
        && fromCardNums.keys().contains(DdzCard.DA_WANG.num)) {
      hasWangZha = true;
    }

    // 手上最小的牌
    List<DdzCard> minCards = new ArrayList<>();

    for (Integer cardNum : fromCardNums.keys().elementSet()) {
      NavigableSet<DdzCard> cards = fromCardNums.get(cardNum);

      // 含有王炸时，跳过大小王
      if (hasWangZha && (cardNum == DdzCard.XIAO_WANG.num || cardNum == DdzCard.DA_WANG.num)) {
        continue;
      }

      if (cards.size() < 4) {
        minCards.addAll(cards);
        return minCards;
      }

      if (cards.size() == 4) {
        zhaDans.putAll(cardNum, cards);
      }
    }

    // 从炸弹中找
    if (zhaDans.size() > 0) {
      // 最小的炸弹牌数字
      Integer minZhaDanNum = zhaDans.keySet().first();
      minCards.addAll(zhaDans.get(minZhaDanNum));
      return minCards;
    }

    // 有王炸
    if (hasWangZha) {
      return Arrays.asList(DdzCard.DA_WANG, DdzCard.XIAO_WANG);
    }

    return minCards;
  }

  /**
   * 从fromCards找出比comparedCards更大的牌 . DAN, //单牌：单张牌 DUI_ZI, //对牌：两个 SAN_BU_DAI, //三张牌：三张牌点相同的牌。
   * SAN_DAI_DAN, . //三带单：三张牌 ＋ 一张单牌。例如： 888+9 SAN_DAI_DUI, //三带对：三张牌 ＋ 一对牌。例如： 888+99 SHUN_ZI,
   * //单顺：五张或更多连续的单牌。例如：3+4+5+6+7+8。不包括2和大、小王。 . SHUANG_SHUN, //双顺：三个或更多连续的对牌。例如：33+44+55。不包括2和大、小王。
   * SAN_SHUN, . //三顺：二个或更 多连续的三张牌。例如：333444、444555666777。不包括2和大、小王。 FEI_JI_DAI_DAN,
   * //三顺＋同数量的一套牌,333444+69,69也可以是一对 . FEI_JI_DAI_DUI, //三顺＋同数量的一套牌,333444555+667799 ZHA_DAN,
   * //四张牌点相同的牌（如四个8）。除火箭和比自己大的炸弹外，什么牌型都可打。 . WANG_ZHA, //火箭：即双王（双花牌），什么牌型都可打，是最大的牌 SI_DAI_DAN,
   * //四张牌＋任意两套张数相同的单牌。例如：5555＋3＋8 . SI_DAI_DUI; //四张牌＋任意两套张数相同的对牌。例如：4444＋55＋77
   * 
   * @param fromCards .
   * @param comparedSortedCards . 必须将牌按照从小到大排列 .
   * @param comparedCardsType .
   * @param originSortedCards 原始牌 .
   * @return null找不到更大的牌 .
   */
  public static List<DdzCard> findBiggerCards(Collection<DdzCard> fromCards,
      List<DdzCard> comparedSortedCards, DdzCardsType comparedCardsType,
      List<DdzCard> originSortedCards) {

    if (fromCards.size() == 0) {
      return null;
    }

    if (fromCards.size() == 0) {
      return null;
    }

    // 查询的牌的数字map
    TreeMultimap<Integer, DdzCard> fromCardNums = TreeMultimap.create();
    for (DdzCard fromCard : fromCards) {
      fromCardNums.put(fromCard.num, fromCard);
    }

    /*
     * 找出更大的牌 . comparedCardsType： 1.王炸， 2.炸弹，在炸弹中找 3.其他牌型，在同牌型中查找，没有则在炸弹中查找 如果找不到最后查找自己的牌中是否有王炸
     */
    List<DdzCard> biggerCards = null;

    if (comparedCardsType == DdzCardsType.WANG_ZHA) {
      return null;
    } else if (comparedCardsType == DdzCardsType.ZHA_DAN) {
      biggerCards = findBiggerZhaDan(comparedSortedCards.get(0), fromCardNums);
    } else {
      // 剩下的牌型(SHUN_ZI,DAN,DUI_ZI,SAN_BU_DAI,SAN_DAI_DAN,
      // SAN_DAI_DUI,SHUANG_SHUN,SAN_SHUN,FEI_JI_DAI_DAN,FEI_JI_DAI_DUI,SI_DAI_DAN,SI_DAI_DUI)

      // 单牌
      TreeMap<Integer, DdzCard> danPais = new TreeMap<>();
      // 对牌
      TreeMultimap<Integer, DdzCard> duiPais = TreeMultimap.create();
      // 三个一样的牌
      TreeMultimap<Integer, DdzCard> sanPais = TreeMultimap.create();
      // 四个一样的牌(炸弹)
      TreeMultimap<Integer, DdzCard> zhaDans = TreeMultimap.create();

      for (Integer cardNum : fromCardNums.keys().elementSet()) {
        NavigableSet<DdzCard> cards = fromCardNums.get(cardNum);
        if (cards.size() == 1) {
          danPais.put(cardNum, cards.first());
          continue;
        }
        if (cards.size() == 2) {
          duiPais.putAll(cardNum, cards);
          continue;
        }
        if (cards.size() == 3) {
          sanPais.putAll(cardNum, cards);
          continue;
        }
        if (cards.size() == 4) {
          zhaDans.putAll(cardNum, cards);
          continue;
        }
      }

      switch (comparedCardsType) {
        case SHUN_ZI:
          biggerCards = findBiggerShunZi(comparedSortedCards.get(0), comparedSortedCards.size(),
              fromCardNums);
          break;
        case DAN:
          biggerCards = findBiggerDanPai(comparedSortedCards.get(0), fromCardNums,
              originSortedCards.get(0), danPais, duiPais, sanPais);
          break;
        case DUI_ZI:
          biggerCards = findBiggerDuizi(comparedSortedCards.get(0), fromCardNums,
              originSortedCards.get(0), duiPais, sanPais);
          break;
        case SAN_BU_DAI:
          biggerCards = findBiggerSanZhang(comparedSortedCards.get(0), sanPais);
          break;
        case SAN_DAI_DAN:
          List<DdzCard> biggerSanDaiDanCards =
              findBiggerSanZhang(comparedSortedCards.get(2), sanPais);
          if (biggerSanDaiDanCards != null) {
            sanPais.removeAll(biggerSanDaiDanCards.get(0).num);
            List<DdzCard> minDans = findMinDanPai(1, danPais, duiPais, sanPais);
            if (minDans != null) {
              biggerSanDaiDanCards.addAll(minDans);
              biggerCards = biggerSanDaiDanCards;
            }
          }
          break;
        case SAN_DAI_DUI:
          List<DdzCard> biggerSanDaiDuiCards =
              findBiggerSanZhang(comparedSortedCards.get(2), sanPais);
          if (biggerSanDaiDuiCards != null) {
            sanPais.removeAll(biggerSanDaiDuiCards.get(0).num);
            List<DdzCard> minDuis = findMinDuiPai(1, duiPais, sanPais);
            if (minDuis != null) {
              biggerSanDaiDuiCards.addAll(minDuis);
              biggerCards = biggerSanDaiDuiCards;
            }
          }
          break;
        case SHUANG_SHUN:
          biggerCards = findBiggerShuangShun(comparedSortedCards.get(0),
              comparedSortedCards.size() / 2, duiPais, sanPais);
          break;
        case SAN_SHUN:
          biggerCards = findBiggerSanShun(comparedSortedCards.get(0),
              comparedSortedCards.size() / 3, sanPais);
          break;
        case FEI_JI_DAI_DAN:
          List<DdzCard> biggerFeiJiDaiDanCards =
              findBiggerSanShun(comparedSortedCards.get(comparedSortedCards.size() / 4),
                  comparedSortedCards.size() / 4, sanPais);
          if (biggerFeiJiDaiDanCards != null) {
            // 移除已经找到的三牌
            for (DdzCard biggerFeiJiDaiDanCard : biggerFeiJiDaiDanCards) {
              sanPais.removeAll(biggerFeiJiDaiDanCard.num);
            }

            List<DdzCard> minDanPais =
                findMinDanPai(comparedSortedCards.size() / 4, danPais, duiPais, sanPais);

            if (minDanPais != null) {
              biggerFeiJiDaiDanCards.addAll(minDanPais);
              biggerCards = biggerFeiJiDaiDanCards;
            }
          }
          break;
        case FEI_JI_DAI_DUI:
          List<DdzCard> biggerFeiJiDaiDuiCards =
              findBiggerSanShun(comparedSortedCards.get(comparedSortedCards.size() / 5 * 2),
                  comparedSortedCards.size() / 5, sanPais);
          if (biggerFeiJiDaiDuiCards != null) {
            // 移除已经找到的三牌
            for (DdzCard biggerFeiJiDaiDuiCard : biggerFeiJiDaiDuiCards) {
              sanPais.removeAll(biggerFeiJiDaiDuiCard.num);
            }

            List<DdzCard> minDuiPais =
                findMinDuiPai(comparedSortedCards.size() / 5, duiPais, sanPais);

            if (minDuiPais != null) {
              biggerFeiJiDaiDuiCards.addAll(minDuiPais);
              biggerCards = biggerFeiJiDaiDuiCards;
            }
          }
          break;
        case SI_DAI_DAN:
          List<DdzCard> biggerSiDaiDanCards = findBiggerZhaDan(comparedSortedCards.get(2), zhaDans);
          if (biggerSiDaiDanCards != null) {
            zhaDans.removeAll(biggerSiDaiDanCards.get(0).num);
            List<DdzCard> minDanPais = findMinDanPai(2, danPais, duiPais, sanPais);

            if (minDanPais != null) {
              biggerSiDaiDanCards.addAll(minDanPais);
              biggerCards = biggerSiDaiDanCards;
            }
          }
          break;
        case SI_DAI_DUI:
          DdzCard comparedSiDaiDuicard;
          if (comparedSortedCards.get(0).num == comparedSortedCards.get(1).num
              && comparedSortedCards.get(1).num == comparedSortedCards.get(2).num) {
            comparedSiDaiDuicard = comparedSortedCards.get(0);
          } else {
            comparedSiDaiDuicard = comparedSortedCards.get(4);
          }

          List<DdzCard> biggerSiDaiDuiCards = findBiggerZhaDan(comparedSiDaiDuicard, zhaDans);
          if (biggerSiDaiDuiCards != null) {
            zhaDans.removeAll(biggerSiDaiDuiCards.get(0).num);
            List<DdzCard> minDanDuis = findMinDuiPai(2, duiPais, sanPais);

            if (minDanDuis != null) {
              biggerSiDaiDuiCards.addAll(minDanDuis);
              biggerCards = biggerSiDaiDuiCards;
            }
          }
          break;
        default:
          break;
      }

      // 查找是否有炸弹
      if (biggerCards == null && zhaDans.size() > 0) {
        biggerCards = new ArrayList<>(zhaDans.asMap().firstEntry().getValue());
      }
    }

    /*
     * 最后查找是否有王炸 .
     */
    //
    if (biggerCards == null) {
      biggerCards = findWangZha(fromCardNums);
    }

    return biggerCards;
  }

  /**
   * 查找比comparedCard更大的单牌,查找顺序：单牌集合，对牌集合,三张牌的集合，必须找出足够num的牌，找不到则为空 .
   * 
   * @param comparedCard . 被比较的牌
   * @param fromCardNums . 玩家手上的牌
   * @param originSortedCards . 原始牌
   * @param danPais . 单牌集合
   * @param duiPais . 对牌集合
   * @param sanPais . 三张牌的集合
   * @return .
   */
  private static List<DdzCard> findBiggerDanPai(DdzCard comparedCard,
      TreeMultimap<Integer, DdzCard> fromCardNums, DdzCard originCard,
      TreeMap<Integer, DdzCard> danPais, TreeMultimap<Integer, DdzCard> duiPais,
      TreeMultimap<Integer, DdzCard> sanPais) {
    // 当前比较的牌为原始牌
    if (originCard.equals(comparedCard)) {
      // 从单牌中查找更大的单牌
      for (Entry<Integer, DdzCard> danPaiEtr : danPais.entrySet()) {
        if (danPaiEtr.getKey() > comparedCard.num) {
          return Arrays.asList(danPaiEtr.getValue());
        }
      }

      // 从对牌中查找更大的单牌
      for (Entry<Integer, DdzCard> duiPaiEtr : duiPais.entries()) {
        if (duiPaiEtr.getKey() > comparedCard.num) {
          return Arrays.asList(duiPaiEtr.getValue());
        }
      }

      // 从三张牌的集合中查找更大的单牌
      for (Entry<Integer, DdzCard> sanPaiEtr : sanPais.entries()) {
        if (sanPaiEtr.getKey() > comparedCard.num) {
          return Arrays.asList(sanPaiEtr.getValue());
        }
      }
    } else {
      if (danPais.keySet().contains(comparedCard.num)) { // 目前为单牌
        // 从单牌中查找更大的单牌
        for (Entry<Integer, DdzCard> danPaiEtr : danPais.entrySet()) {
          if (danPaiEtr.getKey() > comparedCard.num) {
            return Arrays.asList(danPaiEtr.getValue());
          }
        }

        // 从对牌中查找更大的单牌
        for (Entry<Integer, DdzCard> duiPaiEtr : duiPais.entries()) {
          if (duiPaiEtr.getKey() > originCard.num) {
            return Arrays.asList(duiPaiEtr.getValue());
          }
        }

        // 从三张牌的集合中查找更大的单牌
        for (Entry<Integer, DdzCard> sanPaiEtr : sanPais.entries()) {
          if (sanPaiEtr.getKey() > originCard.num) {
            return Arrays.asList(sanPaiEtr.getValue());
          }
        }
      } else if (duiPais.keySet().contains(comparedCard.num)) {
        // 从对牌中查找更大的单牌
        for (Entry<Integer, DdzCard> duiPaiEtr : duiPais.entries()) {
          if (duiPaiEtr.getKey() > comparedCard.num) {
            return Arrays.asList(duiPaiEtr.getValue());
          }
        }

        // 从三张牌的集合中查找更大的单牌
        for (Entry<Integer, DdzCard> sanPaiEtr : sanPais.entries()) {
          if (sanPaiEtr.getKey() > originCard.num) {
            return Arrays.asList(sanPaiEtr.getValue());
          }
        }
      } else if (sanPais.keySet().contains(comparedCard.num)) {
        for (Entry<Integer, DdzCard> sanPaiEtr : sanPais.entries()) {
          if (sanPaiEtr.getKey() > comparedCard.num) {
            return Arrays.asList(sanPaiEtr.getValue());
          }
        }
      }
    }

    return null;
  }

  /**
   * 查找比comparedCard更大的对牌,查找顺序：对牌集合,三张牌的集合 .
   * 
   * @param comparedCard 需要比较的牌.
   * @param fromCardNums 所有牌.
   * @param originCard 原始需要比较的牌.
   * @param duiPais 对牌集合 .
   * @param sanPais . 三张牌的集合 .
   * @return .
   */
  private static List<DdzCard> findBiggerDuizi(DdzCard comparedCard,
      TreeMultimap<Integer, DdzCard> fromCardNums, DdzCard originCard,
      TreeMultimap<Integer, DdzCard> duiPais, TreeMultimap<Integer, DdzCard> sanPais) {
    // 当前比较的牌为原始牌
    if (originCard.equals(comparedCard)) {
      // 从对牌中查找更大的对子
      for (Integer cardNum : duiPais.keys().elementSet()) {
        if (cardNum > comparedCard.num) {
          return new ArrayList<>(duiPais.get(cardNum));
        }
      }

      // 从三张牌的集合中查找更大的对子
      for (Integer cardNum : sanPais.keys().elementSet()) {
        if (cardNum > comparedCard.num) {
          NavigableSet<DdzCard> sanPai = sanPais.get(cardNum);
          DdzCard last = sanPai.last();

          return Arrays.asList(sanPai.lower(last), last);
        }
      }
    } else {
      // 当前比较的牌包含在对牌中
      if (duiPais.containsKey(comparedCard.num)) {
        // 从对牌中查找更大的对子
        for (Integer cardNum : duiPais.keys().elementSet()) {
          if (cardNum > comparedCard.num) {
            return new ArrayList<>(duiPais.get(cardNum));
          }
        }

        // 从三张牌的集合中查找更大的对子
        for (Integer cardNum : sanPais.keys().elementSet()) {
          if (cardNum > originCard.num) {
            NavigableSet<DdzCard> sanPai = sanPais.get(cardNum);
            DdzCard last = sanPai.last();

            return Arrays.asList(sanPai.lower(last), last);
          }
        }
      } else if (sanPais.containsKey(comparedCard.num)) {
        // 从三张牌的集合中查找更大的对子
        for (Integer cardNum : sanPais.keys().elementSet()) {
          if (cardNum > comparedCard.num) {
            NavigableSet<DdzCard> sanPai = sanPais.get(cardNum);
            DdzCard last = sanPai.last();

            return Arrays.asList(sanPai.lower(last), last);
          }
        }
      }
    }



    return null;
  }

  /**
   * 查找比comparedCard更大的三张一样的牌,查找顺序：三张牌的集合 .
   * 
   * @param comparedCard .
   * @param sanPais . 三张牌的集合 .
   * @return .
   */
  private static List<DdzCard> findBiggerSanZhang(DdzCard comparedCard,
      TreeMultimap<Integer, DdzCard> sanPais) {

    // 从三张牌的集合中查找更大的三张
    for (Integer cardNum : sanPais.keys().elementSet()) {
      if (cardNum > comparedCard.num) {

        return new ArrayList<>(sanPais.get(cardNum));
      }
    }

    return null;
  }

  /**
   * 查找最小的单牌 .
   * 
   * @param num . 最小的单牌数量 .
   * @param danPais .
   * @param duiPais .
   * @param sanPais .
   * @return .
   */
  private static List<DdzCard> findMinDanPai(int num, TreeMap<Integer, DdzCard> danPais,
      TreeMultimap<Integer, DdzCard> duiPais, TreeMultimap<Integer, DdzCard> sanPais) {
    List<DdzCard> minDanPais = new ArrayList<>();
    for (Integer cardNum : danPais.keySet()) {
      if (num > 0) {
        minDanPais.add(danPais.get(cardNum));
        num--;
      } else {
        return minDanPais;
      }
    }


    for (Entry<Integer, DdzCard> cardEtr : duiPais.entries()) {
      if (num > 0) {
        minDanPais.add(cardEtr.getValue());
        num--;
      } else {
        return minDanPais;
      }
    }

    for (Entry<Integer, DdzCard> cardEtr : sanPais.entries()) {
      if (num > 0) {
        minDanPais.add(cardEtr.getValue());
        num--;
      } else {
        return minDanPais;
      }
    }

    return num == minDanPais.size() ? minDanPais : null;
  }

  /**
   * 查找最小的对牌 .
   * 
   * @param num . 对牌的对数 .
   * @param duiPais .
   * @param sanPais .
   * @return .
   */
  private static List<DdzCard> findMinDuiPai(int num, TreeMultimap<Integer, DdzCard> duiPais,
      TreeMultimap<Integer, DdzCard> sanPais) {
    List<DdzCard> minDuiPais = new ArrayList<>();

    // 对牌查找
    for (Integer cardNum : duiPais.keys().elementSet()) {
      if (num > 0) {
        minDuiPais.addAll(duiPais.get(cardNum));
        num--;
      } else {
        return minDuiPais;
      }
    }

    // 从三牌中查找
    for (Integer cardNum : sanPais.keys().elementSet()) {
      if (num > 0) {
        NavigableSet<DdzCard> fistSanPai = sanPais.get(cardNum);
        minDuiPais.add(fistSanPai.first());
        minDuiPais.add(fistSanPai.last());
        num--;
      } else {
        return minDuiPais;
      }
    }

    if (num == 0) {
      return minDuiPais;
    } else {
      return null;
    }

  }

  /**
   * 查找比comparedCard更大的双顺 .
   * 
   * @param comparedCards .
   * @param num . 双顺的对子数量 .
   * @param duiPais .
   * @param sanPais .
   * @return .
   */
  private static List<DdzCard> findBiggerShuangShun(DdzCard comparedCard, int num,
      TreeMultimap<Integer, DdzCard> duiPais, TreeMultimap<Integer, DdzCard> sanPais) {

    for (int startNum = comparedCard.num; startNum <= DdzCard.FANG_KUAI_A.num - num; startNum++) {
      // 更大的对牌集合
      List<DdzCard> biggerCards = new ArrayList<>();
      // 更大的对牌数
      int biggerCardsCount = num * 2;

      for (int i = 0; i < num; i++) {
        int biggerCardNum = startNum + i + 1;

        /*
         * 先从对牌中找，再从三牌中找 .
         */
        NavigableSet<DdzCard> biggerNumCards = duiPais.get(biggerCardNum);
        if (biggerNumCards.size() == 0) {
          biggerNumCards = sanPais.get(biggerCardNum);
        }

        if (biggerCardNum < DdzCard.FANG_KUAI_ER.num && biggerNumCards.size() > 0
            && biggerCardsCount-- > 0) {
          biggerCards.add(biggerNumCards.first());
          biggerCards.add(biggerNumCards.last());
        } else {
          break;
        }
      }

      if (biggerCards.size() == num * 2) {
        return biggerCards;
      }
    }

    return null;
  }

  /**
   * 查找比comparedCards更大的三顺 .
   * 
   * @param minCard . 三顺中最小的牌的数字 .
   * @param num . 有几个三顺 .
   * @param sanPais .
   * @return .
   */
  private static List<DdzCard> findBiggerSanShun(DdzCard minCard, int num,
      TreeMultimap<Integer, DdzCard> sanPais) {

    for (int startNum = minCard.num; startNum <= DdzCard.FANG_KUAI_A.num - num; startNum++) {
      // 更大的三顺牌
      List<DdzCard> biggerCards = new ArrayList<>();
      // 更大的三顺牌数量
      int biggerCardsCount = num * 3;

      for (int i = 0; i < num; i++) {
        int biggerCardNum = startNum + i + 1;
        NavigableSet<DdzCard> biggerNumCards = sanPais.get(biggerCardNum);

        if (biggerCardNum < DdzCard.FANG_KUAI_ER.num && biggerNumCards.size() > 0
            && biggerCardsCount-- > 0) {
          biggerCards.addAll(biggerNumCards);
        } else {
          break;
        }
      }

      if (biggerCards.size() == num * 3) {
        return biggerCards;
      }
    }

    return null;
  }

  /**
   * 从fromCardNums找出比comparedCard大的顺子 .
   * 
   * @param comparedCard .
   * @param num . 顺子数量 .
   * @param fromCardNums .
   * @return .
   */
  private static List<DdzCard> findBiggerShunZi(DdzCard comparedCard, int num,
      TreeMultimap<Integer, DdzCard> fromCardNums) {

    for (int startNum = comparedCard.num; startNum <= DdzCard.FANG_KUAI_A.num - num; startNum++) {
      // 更大的顺子牌
      List<DdzCard> biggerCards = new ArrayList<>();
      // 更大的顺子牌数量
      int biggerCardsCount = num;

      for (int i = 0; i < num; i++) {
        int biggerCardNum = startNum + i + 1;
        NavigableSet<DdzCard> biggerNumCards = fromCardNums.get(biggerCardNum);

        if (biggerCardNum < DdzCard.FANG_KUAI_ER.num && biggerNumCards.size() > 0
            && biggerCardsCount-- > 0) {
          biggerCards.add(biggerNumCards.first());
        } else {
          break;
        }
      }

      if (biggerCards.size() == num) {
        return biggerCards;
      }
    }

    return null;
  }

  /**
   * 从fromCardNums找出比comparedCard更大的炸弹 .
   * 
   * @param comparedCard .
   * @param fromCardNums .
   * @return .
   */
  private static List<DdzCard> findBiggerZhaDan(DdzCard comparedCard,
      TreeMultimap<Integer, DdzCard> fromCardNums) {
    for (Integer fromCardNum : fromCardNums.keys()) {
      NavigableSet<DdzCard> biggerCards = fromCardNums.get(fromCardNum);
      if (biggerCards.size() == 4 && fromCardNum > comparedCard.num) {
        return new ArrayList<>(biggerCards);
      }
    }

    return null;
  }

  /**
   * 从fromCardNums查找王炸 .
   * 
   * @param fromCardNums .
   * @return .
   */
  static List<DdzCard> findWangZha(TreeMultimap<Integer, DdzCard> fromCardNums) {
    // 有大王
    boolean hasDaWang = false;
    // 有小王
    boolean hasXiaoWang = false;

    for (DdzCard fromCard : fromCardNums.values()) {
      if (fromCard.equals(DdzCard.DA_WANG)) {
        hasDaWang = true;
        continue;
      }

      if (fromCard.equals(DdzCard.XIAO_WANG)) {
        hasXiaoWang = true;
        continue;
      }
    }

    if (hasXiaoWang && hasDaWang) {
      return Arrays.asList(DdzCard.XIAO_WANG, DdzCard.DA_WANG);
    }
    return null;
  }
}
