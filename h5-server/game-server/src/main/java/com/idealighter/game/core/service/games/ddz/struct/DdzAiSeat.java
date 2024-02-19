package com.idealighter.game.core.service.games.ddz.struct;

import com.google.common.collect.TreeMultimap;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeAnalyzer;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeFinder;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeGetter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * 斗地主AISeat,增加智能出牌和压牌。配牌顺序为：火箭 . 炸弹 三条 单顺 双顺 对子 单牌.
 *
 */
public class DdzAiSeat extends DdzSeat {

  // ai牌中的王炸
  public final EnumSet<DdzCard> wangZha = EnumSet.noneOf(DdzCard.class);
  // ai牌中的炸弹,key：DouDiZhuCard的num,num代表了大小
  public final TreeMultimap<Integer, DdzCard> zhanDans = TreeMultimap.create();
  // ai牌中的三条,key：DouDiZhuCard的num,num代表了大小
  public final TreeMultimap<Integer, DdzCard> sanTiaos = TreeMultimap.create();
  // ai牌中的单顺,key：DouDiZhuCard的num,num代表了大小
  public final TreeMultimap<DdzCard, DdzCard> shunZis = TreeMultimap.create();
  // ai牌中的双顺,key：DouDiZhuCard的num,num代表了大小
  public final TreeMultimap<Integer, DdzCard> shuangShuns = TreeMultimap.create();
  // ai牌中的对子,key：DouDiZhuCard的num,num代表了大小
  public final TreeMultimap<Integer, DdzCard> duiZis = TreeMultimap.create();
  // ai牌中的单牌,key：DouDiZhuCard的num,num代表了大小
  public final TreeMap<Integer, DdzCard> danDans = new TreeMap<>();

  // 第一手出牌最高类型
  private static final List<DdzCardsType> FIRST_PLAY_CARDS_HIGHEST_TYPES =
      Arrays.asList(DdzCardsType.WANG_ZHA, DdzCardsType.ZHA_DAN, DdzCardsType.SAN_BU_DAI);
  // 第一手出牌最低类型
  private static final List<DdzCardsType> FIRST_PLAY_CARDS_LOWEST_TYPES;

  static {
    FIRST_PLAY_CARDS_LOWEST_TYPES = new ArrayList<>();
    for (DdzCardsType type : DdzCardsType.values()) {
      if (!FIRST_PLAY_CARDS_HIGHEST_TYPES.contains(type)) {
        FIRST_PLAY_CARDS_LOWEST_TYPES.add(type);
      }
    }
  }

  // 把牌出完的手数
  private int hands;
  // 牌的质量
  private int quality;

  /**
   * 位置.
   * 
   * @param order .
   * @param applyOrder .
   * @param icon .
   * @param table .
   */
  protected DdzAiSeat(int order, DdzTable table) {
    super(order, table);
  }

  /**
   * 计算ai牌型，火箭 . 炸弹 三条 单顺 双顺 对子 单牌。 .
   */
  public void calcCardsType() {
    resetCardsType();
    // 查询的牌的数字map
    TreeMultimap<Integer, DdzCard> fromCards = TreeMultimap.create();
    for (DdzCard fromCard : this.getCards()) {
      fromCards.put(fromCard.num, fromCard);
    }

    quality += fromCards.get(DdzCard.FANG_KUAI_ER.num).size();

    List<DdzCard> anlsWangZha = CardsTypeAnalyzer.analysisWangZha(fromCards);
    if (anlsWangZha != null && anlsWangZha.size() > 0) {
      wangZha.addAll(anlsWangZha);
      fromCards.values().removeAll(anlsWangZha);
      hands++;
      quality += 8;
    }

    TreeMultimap<Integer, DdzCard> anlsZhanDans = CardsTypeAnalyzer.analysisZhanDans(fromCards);
    if (anlsZhanDans != null && anlsZhanDans.size() > 0) {
      zhanDans.putAll(anlsZhanDans);
      fromCards.values().removeAll(anlsZhanDans.values());
      hands += anlsZhanDans.keySet().size();
      quality += 5 * anlsZhanDans.keySet().size();
    }

    TreeMultimap<Integer, DdzCard> anlsSanTiaos = CardsTypeAnalyzer.analysisSanTiaos(fromCards);
    if (anlsSanTiaos != null && anlsSanTiaos.size() > 0) {
      sanTiaos.putAll(anlsSanTiaos);
      fromCards.values().removeAll(anlsSanTiaos.values());
      hands += anlsSanTiaos.keySet().size();
    }

    TreeMultimap<DdzCard, DdzCard> anlsShunZis = CardsTypeAnalyzer.analysisShunZis(fromCards);
    if (anlsShunZis != null && anlsShunZis.size() > 0) {
      shunZis.putAll(anlsShunZis);
      fromCards.values().removeAll(anlsShunZis.values());
      hands += anlsShunZis.keySet().size();
    }

    TreeMultimap<Integer, DdzCard> anlsShuangShuns =
        CardsTypeAnalyzer.analysisShuangShuns(fromCards);
    if (anlsShuangShuns != null && anlsShuangShuns.size() > 0) {
      shuangShuns.putAll(anlsShuangShuns);
      fromCards.values().removeAll(anlsShuangShuns.values());
      hands += anlsShuangShuns.keySet().size();
    }

    TreeMultimap<Integer, DdzCard> anlsDuiZis = CardsTypeAnalyzer.analysisDuiZis(fromCards);
    if (anlsDuiZis != null && anlsDuiZis.size() > 0) {
      duiZis.putAll(anlsDuiZis);
      fromCards.values().removeAll(anlsDuiZis.values());
      hands += duiZis.keySet().size();
    }

    TreeMap<Integer, DdzCard> anlsDanPais = CardsTypeAnalyzer.analysisDanPais(fromCards);
    if (anlsDanPais != null && anlsDanPais.size() > 0) {
      danDans.putAll(anlsDanPais);
      fromCards.values().removeAll(anlsDanPais.values());
      hands += danDans.keySet().size();

      Collection<DdzCard> cards = anlsDanPais.values();
      if (cards.contains(DdzCard.DA_WANG)) {
        quality += 3;
      }
      if (cards.contains(DdzCard.XIAO_WANG)) {
        quality += 2;
      }
    }

    // （20一hands）+附加值总和
    quality = 20 - hands + quality;
    System.out.println("playerId: " + this.playerId + ", hands: " + this.hands);
  }

  /**
   * 第一手出牌 . 级别 最高 高 一般 低 最低 牌型 火箭 炸弹 单王 2 其他 .
   * 
   * @return .
   */
  public List<DdzCard> firstPlayCards() {
    if (isLandlord()) { // 地主第一手出牌
      return landlordFirstPlayCards();
    } else {
      DdzAiSeat nextSeat = table().nextSeat(this);
      if (!nextSeat.isLandlord()) { // 当前是地主下家出牌
        return nextFarmerFirstPlayCards();
      } else { // 当前是地主上家出牌
        return preFarmerFirstPlayCards();
      }
    }
  }

  /**
   * 非第一手出牌,压牌 .
   * 
   * @return .
   */
  public List<DdzCard> playCards() {
    DdzAiSeat nextSeat = table().nextSeat(this);

    if (isLandlord()) { // 当前是地主出牌
      return landlordPlayCards();
    } else if (nextSeat.isLandlord()) { // 当前是地主上家出牌
      return preFarmerPlayCards();
    } else { // 当前是地主下家出牌
      return nextFarmerPlayCards();
    }
  }

  /**
   * 地主非第一手出牌,压牌 .
   * 
   * @return .
   */
  public List<DdzCard> landlordPlayCards() {
    DdzTable table = super.table();
    List<DdzCard> prePlayCards = table.getPrePlayCards();
    DdzCardsType prePlayCardsType = table.getPrePlayCardsType();
    EnumSet<DdzCard> cards = getCards();
    List<DdzCard> biggerCards =
        CardsTypeFinder.findBiggerCards(cards, prePlayCards, prePlayCardsType, prePlayCards);
    if (biggerCards == null) {
      return null;
    }
    biggerCards = doNotBreakWangZha(biggerCards, cards, prePlayCardsType, prePlayCards);

    DdzAiSeat seat1 = table.nextSeat(this);
    DdzAiSeat seat2 = table.nextSeat(seat1);
    int hands1 = seat1.getHands();
    int hands2 = seat2.getHands();
    DdzCardsType cardsType = CardsTypeGetter.getCardsType(biggerCards);

    if (cardsType == DdzCardsType.WANG_ZHA && hands > 2 && (hands1 > 5 || hands2 > 5)) {
      return null;
    }

    if (cardsType == DdzCardsType.ZHA_DAN && hands > 1 && (hands1 > 5 || hands2 > 5)) {
      return null;
    } else if (!hasCardsType(prePlayCardsType) && (hands1 > 5 && hands2 > 5)) {
      return null;
    }

    return biggerCards;
  }

  /**
   * @Title doNotBreakWangZha.
   * @Description 不拆散王炸
   * @date 2018年8月30日 下午2:59:37
   * @param biggerCards 之前的牌
   * @param cards 玩家所有牌
   * @param prePlayCardsType 牌型
   * @return 最终稍微大的牌
   */
  private List<DdzCard> doNotBreakWangZha(List<DdzCard> biggerCards, EnumSet<DdzCard> cards,
      DdzCardsType prePlayCardsType, List<DdzCard> originCardList) {
    boolean hasWangZha = false;
    if (cards.contains(DdzCard.DA_WANG) && cards.contains(DdzCard.XIAO_WANG)) {
      hasWangZha = true;
    }
    if (hasWangZha
        && (biggerCards.contains(DdzCard.XIAO_WANG) || biggerCards.contains(DdzCard.DA_WANG))) {
      // 含有王炸时，不拆散
      if (biggerCards.size() == 1) {
        List<DdzCard> preCardList = Arrays.asList(DdzCard.DA_WANG);
        biggerCards =
            CardsTypeFinder.findBiggerCards(cards, preCardList, prePlayCardsType, originCardList);
      }
    }
    return biggerCards;
  }

  /**
   * 地主上家非第一手出牌,压牌 .
   * 
   * @return .
   */
  public List<DdzCard> preFarmerPlayCards() {
    List<DdzCard> prePlayCards = table().getPrePlayCards();
    DdzCardsType prePlayCardsType = table().getPrePlayCardsType();
    EnumSet<DdzCard> cards = getCards();
    List<DdzCard> biggerCards =
        CardsTypeFinder.findBiggerCards(cards, prePlayCards, prePlayCardsType, prePlayCards);
    if (biggerCards == null) {
      return null;
    }

    biggerCards = doNotBreakWangZha(biggerCards, cards, prePlayCardsType, prePlayCards);

    DdzAiSeat landlord = table().landlord();
    int landlordhHands = landlord.getHands();
    DdzCardsType cardsType = CardsTypeGetter.getCardsType(biggerCards);

    if (hands > 1 && (landlordhHands > 5) && cardsType == DdzCardsType.WANG_ZHA
        || cardsType == DdzCardsType.ZHA_DAN) {
      return null;
    } else if (!hasCardsType(prePlayCardsType) && landlordhHands > 5) {
      return null;
    }

    return biggerCards;
  }

  /**
   * 地主下家非第一手出牌,压牌,主要目的为垫牌。 .
   * 
   * @return .
   */
  public List<DdzCard> nextFarmerPlayCards() {
    List<DdzCard> prePlayCards = table().getPrePlayCards();
    DdzCardsType prePlayCardsType = table().getPrePlayCardsType();
    EnumSet<DdzCard> cards = getCards();
    List<DdzCard> biggerCards =
        CardsTypeFinder.findBiggerCards(cards, prePlayCards, prePlayCardsType, prePlayCards);
    if (biggerCards == null) {
      return null;
    }

    biggerCards = doNotBreakWangZha(biggerCards, cards, prePlayCardsType, prePlayCards);

    DdzAiSeat prePlaySeat = table().getPrePlaySeat();

    if (!prePlaySeat.isLandlord()) {
      return null;
    } else {
      return CardsTypeFinder.findBiggerCards(getCards(), prePlayCards, prePlayCardsType,
          prePlayCards);
    }
  }

  /**
   * 是否有指定牌型 .
   * 
   * @param cardsType .
   * @return .
   */
  private boolean hasCardsType(DdzCardsType cardsType) {
    switch (cardsType) {
      case DAN:
        return danDans.size() > 0;
      case DUI_ZI:
        return duiZis.size() > 0;
      case FEI_JI_DAI_DAN:
        break;
      case FEI_JI_DAI_DUI:
        break;
      case SAN_BU_DAI:
        return sanTiaos.size() > 0;
      case SAN_DAI_DAN:
        return sanTiaos.size() > 0 && danDans.size() > 0;
      case SAN_DAI_DUI:
        return sanTiaos.size() > 0 && duiZis.size() > 0;
      case SAN_SHUN:
        break;
      case SHUANG_SHUN:
        return shuangShuns.size() > 0;
      case SHUN_ZI:
        return shunZis.size() > 0;
      case SI_DAI_DAN:
        return zhanDans.size() > 0 && danDans.size() > 0;
      case SI_DAI_DUI:
        return zhanDans.size() > 0 && duiZis.size() > 0;
      case WANG_ZHA:
        return wangZha.size() > 0;
      case ZHA_DAN:
        return zhanDans.size() > 0;
      default:
        break;
    }

    return false;
  }

  /**
   * 地主第一手出牌 .
   * 
   * @return .
   */
  public List<DdzCard> landlordFirstPlayCards() {
    DdzAiSeat seat1 = table().nextSeat(this);
    DdzAiSeat seat2 = table().nextSeat(seat1);
    int hands1 = seat1.getHands();
    int hands2 = seat2.getHands();

    List<DdzCard> firstPlayCards = new ArrayList<>();
    if (hands1 == 5 || hands2 == 5) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.SAN_DAI_DUI);
    } else if (hands1 == 4 || hands2 == 4) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.SAN_DAI_DAN);
    } else if (hands1 == 2 || hands2 == 2) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.DUI_ZI);
    } else if (hands1 == 1 || hands2 == 1) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.DAN);
    } else {
      firstPlayCards = findFirstPlayCards();
    }

    return firstPlayCards;
  }

  /**
   * 上家农民第一手出牌,类似地主出牌 .
   * 
   * @return .
   */
  private List<DdzCard> preFarmerFirstPlayCards() {
    DdzAiSeat landlord = table().landlord();
    int landlordHands = landlord.hands;

    List<DdzCard> firstPlayCards = new ArrayList<>();
    if (landlordHands == 5) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.SAN_DAI_DUI);
    } else if (landlordHands == 4) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.SAN_DAI_DUI);
    } else if (landlordHands == 3) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.SAN_BU_DAI);
    } else if (landlordHands == 2) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.DUI_ZI);
    } else if (landlordHands == 1) {
      firstPlayCards = findFirstPlayCards(DdzCardsType.DAN);
    } else {
      firstPlayCards = findFirstPlayCards();
    }

    return firstPlayCards;
  }

  /**
   * 下家农民第一手出牌 .
   * 
   * @return .
   */
  private List<DdzCard> nextFarmerFirstPlayCards() {
    DdzAiSeat nextSeat = table().nextSeat(this);
    List<DdzCard> firstPlayCards = null;
    List<DdzCard> preCard = null;
    if (nextSeat.getCards().size() == 1) {
      preCard = Arrays.asList(DdzCard.HEI_TAO_SAN);
      EnumSet<DdzCard> cards = getCards();
      firstPlayCards = CardsTypeFinder.findBiggerCards(cards, preCard, DdzCardsType.DAN, preCard);

      firstPlayCards = doNotBreakWangZha(firstPlayCards, cards, DdzCardsType.DAN, preCard);

    } else if (nextSeat.getCards().size() == 2) {
      preCard = Arrays.asList(DdzCard.HEI_TAO_SAN, DdzCard.HONG_TAO_SAN);
      firstPlayCards =
          CardsTypeFinder.findBiggerCards(getCards(), preCard, DdzCardsType.DUI_ZI, preCard);
    } else {
      firstPlayCards = findFirstPlayCards();
    }

    if (firstPlayCards == null || firstPlayCards.size() == 0) {
      firstPlayCards = CardsTypeFinder.findMinCards(getCards());
    }

    return firstPlayCards;
  }

  /**
   * 获取出牌 . 级别 最高 高 一般 低 最低 牌型 火箭 炸弹 单王 2 其他.
   * 
   * @param excludeTyps .
   * @return .
   */
  private List<DdzCard> findFirstPlayCards(DdzCardsType... excludeTyps) {
    List<DdzCard> playCards = new ArrayList<>();

    Collections.shuffle(FIRST_PLAY_CARDS_LOWEST_TYPES);
    List<DdzCardsType> cardsTypes = new ArrayList<>(FIRST_PLAY_CARDS_LOWEST_TYPES);
    cardsTypes.addAll(FIRST_PLAY_CARDS_HIGHEST_TYPES);
    for (int i = 0; i < excludeTyps.length; i++) {
      cardsTypes.remove(excludeTyps[i]);
    }

    findFistPlayCards: for (DdzCardsType cardsType : cardsTypes) {
      switch (cardsType) {
        case DAN:
          Entry<Integer, DdzCard> danPaiEtr = danDans.firstEntry();
          DdzCard danPai = danPaiEtr == null ? null : danPaiEtr.getValue();
          if (danPai == null || danPai == DdzCard.DA_WANG || danPai == DdzCard.XIAO_WANG
              || danPai.num == DdzCard.HONG_TAO_ER.num) {
            break;
          }
          playCards.add(danPai);
          break findFistPlayCards;
        case DUI_ZI:
          Entry<Integer, Collection<DdzCard>> duiZiEtr = duiZis.asMap().firstEntry();
          if (duiZiEtr == null) {
            break;
          }
          playCards.addAll(duiZiEtr.getValue());
          break findFistPlayCards;
        case FEI_JI_DAI_DAN:
          break;
        case FEI_JI_DAI_DUI:
          break;
        case SAN_BU_DAI:
          Entry<Integer, Collection<DdzCard>> sanTiaoEtr = sanTiaos.asMap().firstEntry();
          if (sanTiaoEtr == null) {
            break;
          }
          playCards.addAll(sanTiaoEtr.getValue());
          break findFistPlayCards;
        case SAN_DAI_DAN:
          Entry<Integer, Collection<DdzCard>> sanDaiDanSanTiaoEtr = sanTiaos.asMap().firstEntry();
          if (sanDaiDanSanTiaoEtr == null) {
            break;
          }
          Entry<Integer, DdzCard> sanDaiDanDanPaiEtr = danDans.firstEntry();
          DdzCard sanDaiDanDanPai =
              sanDaiDanDanPaiEtr == null ? null : sanDaiDanDanPaiEtr.getValue();
          if (sanDaiDanDanPai == null || sanDaiDanDanPai == DdzCard.DA_WANG
              || sanDaiDanDanPai == DdzCard.XIAO_WANG
              || sanDaiDanDanPai.num == DdzCard.HONG_TAO_ER.num) {
            break;
          }

          playCards.addAll(sanDaiDanSanTiaoEtr.getValue());
          playCards.add(sanDaiDanDanPai);
          break findFistPlayCards;
        case SAN_DAI_DUI:
          Entry<Integer, Collection<DdzCard>> sanDaiDuiSanTiaoEtr = sanTiaos.asMap().firstEntry();
          if (sanDaiDuiSanTiaoEtr == null) {
            break;
          }
          Entry<Integer, Collection<DdzCard>> sanDaiDuiDuiZiEtr = duiZis.asMap().firstEntry();
          if (sanDaiDuiDuiZiEtr == null) {
            break;
          }
          playCards.addAll(sanDaiDuiSanTiaoEtr.getValue());
          playCards.addAll(sanDaiDuiDuiZiEtr.getValue());
          break findFistPlayCards;
        case SAN_SHUN:
          break;
        case SHUANG_SHUN:
          Entry<Integer, Collection<DdzCard>> firstShuangShunEtr = shuangShuns.asMap().firstEntry();
          if (firstShuangShunEtr == null) {
            break;
          }
          playCards.addAll(firstShuangShunEtr.getValue());
          break findFistPlayCards;
        case SHUN_ZI:
          Entry<DdzCard, Collection<DdzCard>> firstShunZiEtr = shunZis.asMap().firstEntry();
          if (firstShunZiEtr == null) {
            break;
          }
          playCards.addAll(firstShunZiEtr.getValue());
          break findFistPlayCards;
        case SI_DAI_DAN:
          Entry<Integer, Collection<DdzCard>> siDaiDanZhaDanEtr = zhanDans.asMap().firstEntry();
          if (siDaiDanZhaDanEtr == null) {
            break;
          }

          List<DdzCard> siDaiDanDanPais = new ArrayList<>();
          if (danDans.size() > 1) {

            for (DdzCard card : siDaiDanDanPais) {
              if (card == DdzCard.DA_WANG || card == DdzCard.XIAO_WANG
                  || card.num == DdzCard.HONG_TAO_ER.num) {
                continue;
              }
              siDaiDanDanPais.add(card);
              if (siDaiDanDanPais.size() == 2) {
                break;
              }
            }
          }

          if (siDaiDanDanPais.size() != 2) {
            break;
          }

          playCards.addAll(siDaiDanZhaDanEtr.getValue());
          playCards.addAll(siDaiDanDanPais);
          break findFistPlayCards;
        case SI_DAI_DUI:
          Entry<Integer, Collection<DdzCard>> siDaiDuiZhaDanEtr = zhanDans.asMap().firstEntry();
          if (siDaiDuiZhaDanEtr == null) {
            break;
          }

          NavigableMap<Integer, Collection<DdzCard>> duiZisMap = duiZis.asMap();
          if (duiZisMap.keySet().size() < 2) {
            break;
          }

          playCards.addAll(siDaiDuiZhaDanEtr.getValue());
          playCards.addAll(duiZisMap.firstEntry().getValue());
          playCards.addAll(duiZisMap.lastEntry().getValue());
          break findFistPlayCards;
        case WANG_ZHA:
          if (wangZha.size() == 0) {
            break;
          }

          playCards.addAll(wangZha);
          break findFistPlayCards;
        case ZHA_DAN:
          Entry<Integer, Collection<DdzCard>> zhaDanEtr = zhanDans.asMap().firstEntry();
          if (zhaDanEtr == null) {
            break;
          }

          playCards.addAll(zhaDanEtr.getValue());
          break findFistPlayCards;
        default:
          break;
      }
    }

    if (playCards.size() == 0) {
      playCards.addAll(CardsTypeFinder.findMinCards(this.getCards()));
    }

    return playCards;
  }

  /**
   * 重置牌型 .
   */
  private void resetCardsType() {
    this.hands = 0;
    this.quality = 0;
    this.wangZha.clear();
    this.zhanDans.clear();
    this.sanTiaos.clear();
    this.shunZis.clear();
    this.shunZis.clear();
    this.shuangShuns.clear();
    this.duiZis.clear();
    this.danDans.clear();
  }

  /**
   * 是否叫地主.
   * 
   * @return .
   */
  public boolean callLandlord() {
    return quality > 15;
  }

  /**
   * 是佛抢地主.
   * 
   * @return .
   */
  public boolean grabLandlord() {
    return quality > 18;
  }

  /**
   * 是否加倍 .
   */
  public boolean doDouble() {
    if (isLandlord()) {
      return quality > 24;
    } else {
      return quality > 20;
    }
  }

  @Override
  public void reset() {
    super.reset();
    resetCardsType();
  }

  public int getHands() {
    return hands;
  }

  public int getQuality() {
    return quality;
  }

}
