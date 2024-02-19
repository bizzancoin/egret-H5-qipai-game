
package com.idealighter.game.core.service.log.core;

/**
 * 日志操作reason分类,reason由系统和在该系统所做的操作构成，如：扫荡关卡为LEVEL(关卡)_MOP(扫荡) .
 * 
 * @date 2014年11月7日 下午9:28:51
 *
 */
public enum LogReason {
  /**
   * 玩家充值.
   */
  RECHARGE(1, "玩家充值"),

  /**
   * 玩家购买物品.
   */
  BUY_ITEM(2, "玩家购买物品"),

  /**
   * gm增加物品.
   */
  GM_ADD_ITEM(4, "gm增加物品"),

  /**
   * 玩家购买物品赠送.
   */
  BUY_ITEM_GIVE(5, "玩家购买物品赠送"),



  /**
   * 房间在线奖励金币.
   */
  ROOM_ONLINE_REWARD_GOLD(7, "房间在线奖励金币"),

  /**
   * 桌子在线奖励金币.
   */
  TABLE_ONLINE_REWARD_GOLD(8, "桌子在线奖励金币"),

  /**
   * 后台增加道具.
   */
  BACKEND_ADD_ITEM(9, "后台增加道具"),

  /**
   * 二人牛牛兑换筹码.
   */
  ERREN_NIUNIU_EXCHANGE_CHIP(10, "二人牛牛兑换筹码"),

  /**
   * 二人牛牛退出牌桌兑换金币.
   */
  ERREN_NIUNIU_EXIT_TABLE_EXCHANGE_GOLD(11, "二人牛牛退出牌桌兑换金币"),

  /**
   * 二人牛牛玩家手动兑换金币.
   */
  ERREN_NIUNIU_EXCHANGE_GOLD(12, "二人牛牛玩家手动兑换金币"),

  /**
   * 二人牛牛玩家进入桌子扣除台费.
   */
  ERREN_NIUNIU_ENTER_TABLE_MINUS_AFEE(13, "二人牛牛玩家进入桌子扣除台费"),

  /**
   * 二人牛牛游戏结束结算.
   */
  ERREN_NIUNIU_BILL(14, "二人牛牛游戏结束结算"),

  /**
   * 水浒传兑换筹码.
   */
  SHUI_HU_ZHUAN_EXCHANGE_CHIP(20, "水浒传兑换筹码"),

  /**
   * 二人牛牛退出牌桌兑换金币.
   */
  SHUI_HU_ZHUAN_EXIT_TABLE_EXCHANGE_GOLD(21, "二人牛牛退出牌桌兑换金币"),

  /**
   * 水浒传玩家进入桌子扣除台费.
   */
  SHUI_HU_ZHUAN_ENTER_TABLE_MINUS_AFEE(22, "水浒传玩家进入桌子扣除台费"),

  /**
   * 水浒传玩家手动兑换金币.
   */
  SHUI_HU_ZHUAN_EXCHANGE_GOLD(23, "水浒传玩家手动兑换金币"),

  /**
   * 水浒传结算.
   */
  SHUI_HU_ZHUAN_BILL(24, "水浒传结算"),

  /**
   * 水浒传骰子比倍结算.
   */
  SHUI_HU_ZHUAN_DICE_BILL(25, "水浒传骰子比倍结算"),

  /**
   * 水浒传小玛丽结算.
   */
  SHUI_HU_ZHUAN_XIAO_MA_LI_BILL(26, "水浒传小玛丽结算"),

  /**
   * 水浒传押注.
   */
  SHUI_HU_ZHUAN_BET(27, "水浒传押注"),

  /**
   * 水浒传比倍押注.
   */
  SHUI_HU_ZHUAN_DICE_BET(28, "水浒传比倍押注"),

  /**
   * 4s店结算.
   */
  FOURS_SHOP_BILL(30, "4s店结算"),

  /**
   * 4s店兑换筹码.
   */
  FOURS_SHOP_EXCHANGE_CHIL(31, "4s店兑换筹码"),

  /**
   * 4s店桌子扣除台费.
   */
  FOURS_SHOP_ENTER_TABLE_MINUS_AFEE(32, "4s店桌子扣除台费"),

  /**
   * 4s店兑换金币.
   */
  FOURS_SHOP_EXCHANGE_GOLD(33, "4s店兑换金币"),

  /**
   * 庄家下注开奖阶段强退扣除身上的筹码金币.
   */
  FOURS_SHOP_BANKER_EXIT(34, "庄家下注开奖阶段强退扣除身上的筹码金币"),

  /**
   * 4s店玩家下注金币变化.
   */
  FOURS_SHOP_BET(35, "4s店玩家下注金币变化"),

  /**
   * 德州扑克退出牌桌兑换金币.
   */
  TEXAPOKER_EXIT_TABLE_EXCHANGE_GOLD(40, "德州扑克退出牌桌兑换金币"),

  /**
   * 德州扑克结算.
   */
  TEXAPOKER_BILL(41, "德州扑克结算"),

  /**
   * 德州扑克扣除台费.
   */
  TEXAPOKER_ENTER_TABLE_MINUS_AFEE(42, "德州扑克扣除台费"),

  /**
   * 欢乐五张退出牌桌兑换金币.
   */
  HAPPYFIVE_EXIT_TABLE_EXCHANGE_GOLD(45, "欢乐五张退出牌桌兑换金币"),

  /**
   * 欢乐五张结算.
   */
  HAPPYFIVE_BILL(46, "欢乐五张结算"),

  /**
   * 欢乐五张扣除台费.
   */
  HAPPYFIVE_ENTER_TABLE_MINUS_AFEE(47, "欢乐五张扣除台费"),

  /**
   * 使用道具.
   */
  USE_ITEM(50, "使用道具"),

  /**
   * 存金币.
   */
  DEPOSITE_GOLD(51, "存金币"),

  /**
   * 取金币.
   */
  WITHDRAW_GOLD(52, "取金币"),

  /**
   * 转移金币(转账).
   */
  TRANSFER_GOLD(53, "转移金币(转账)"),

  /**
   * 获取礼物奖励.
   */
  TRANSFER_GET_REWARD(54, "银行获取礼物盒"),
  /**
   * 签到.
   */
  SIGN_IN(60, "签到"),

  /**
   * 领取福利.
   */
  RECEIVE_BENEFITS(61, "领取福利"),

  /**
   * 领取注册奖励.
   */
  RECEIVE_REGISTER_REWARD(62, "领取注册奖励"),

  /**
   * 领取微信分享奖励.
   */
  SHARE_WECHAT(63, "领取微信分享奖励"),

  /**
   * 四人牛牛玩家进入桌子扣除台费.
   */
  SIREN_NIUNIU_ENTER_TABLE_MINUS_AFEE(70, "四人牛牛玩家进入桌子扣除台费"),

  /**
   * 四人牛牛游戏结束结算.
   */
  SIREN_NIUNIU_BILL(71, "四人牛牛游戏结束结算"),

  /**
   * 四人牛牛游戏玩家退出时(尚未发牌)退还下注金币.
   */
  SIREN_NIUNIU_PAY_BACK(72, "四人牛牛游戏玩家退出时(尚未发牌)退还下注金币"),

  /**
   * 鲨银鲨玩家进入桌子扣除台费.
   */
  SHARK_ENTER_TABLE_MINUS_AFEE(80, "飞禽走兽玩家进入桌子扣除台费"),


  /**
   * 飞禽走兽游戏结束结算.
   */
  SHARK_BILL(81, "飞禽走兽游戏结束结算"),

  /**
   * 飞禽走兽退桌返还押注.
   */
  SHARK_EXIT_TABLE_RETURN_BET(82, "飞禽走兽退桌返还押注"),

  /**
   * 飞禽走兽下注.
   */
  SHARK_BET(83, "飞禽走兽下注"),

  /**
   * 通比牛牛玩家进入桌子扣除台费.
   */
  TONGBI_NIUNIU_ENTER_TABLE_MINUS_AFEE(91, "通比牛牛玩家进入桌子扣除台费"),

  /**
   * 通比牛牛游戏结束结算.
   */
  TONGBI_NIUNIU_BILL(92, "通比牛牛游戏结束结算"),

  /**
   * 百人牛牛玩家进入桌子扣除台费.
   */
  BAIREN_NIUNIU_ENTER_TABLE_MINUS_AFEE(100, "百人牛牛玩家进入桌子扣除台费"),

  /**
   * 百人牛牛游戏结束结算.
   */
  BAIREN_NIUNIU_BILL(101, "百人牛牛游戏结束结算"),

  /**
   * 百人牛牛退桌返还押注.
   */
  BEIRENNIUNIU_BET(102, "百人牛牛退桌返还押注"),

  BAIREN_NIUNIU_EXCHANGE_CHIP(103, "百人牛牛兑换筹码"),

  BAIREN_NIUNIU_EXCHANGE_GOLD(104, "百人牛牛兑换金币"),

  BAIREN_NIUNIU_EXIT_TABLE_EXCHANGE_GOLD(105, "百人牛牛离开桌子兑换金币"),

  /**
   * 二人德州扑克退出牌桌兑换金币.
   */
  ERREN_TEXAPOKER_EXIT_TABLE_EXCHANGE_GOLD(111, "二人德州扑克退出牌桌兑换金币"),

  /**
   * 二人德州扑克结算.
   */
  ERREN_TEXAPOKER_BILL(112, "二人德州扑克结算"),


  /**
   * 二人德州扑克扣除台费.
   */
  ERREN_TEXAPOKER_ENTER_TABLE_MINUS_AFEE(113, "二人德州扑克扣除台费"),

  /**
   * 赛马退出兑换金币.
   */
  HORSE_RACE_EXIT_TABLE_EXCHANGE_GOLD(121, "赛马退出兑换金币"),

  /**
   * 赛马结算.
   */
  HORSE_RACE_BILL(122, "赛马结算"),


  /**
   * 赛马下注.
   */
  HORSE_RACE_BET(124, "赛马下注"),

  /**
   * 赛马扣除台费.
   */
  HORSE_RACE_ENTER_TABLE_MINUS_AFEE(125, "赛马扣除台费"),

  /**
   * 赛马清空下注返筹码.
   */
  HORSE_CLEAR_BET(126, "赛马清空下注返筹码"),

  /**
   * 斗地主报名.
   */
  DOUDIZHU_APPLY(131, "斗地主报名"),


  /**
   * 斗地主发放排名奖励.
   */
  DOUDIZHU_GIVE_RANK_REWARK(132, "斗地主发放排名奖励"),

  /**
   * 斗地主取消报名.
   */
  DOUDIZHU_CANCEL(133, "斗地主取消报名"),

  /**
   * 斗地主结算.
   */
  DDZ_BILL(134, "斗地主(金币场)结算"),

  /**
   * 斗地主台费.
   */
  DDZ_ENTER_TABLE_MINUS_AFEE(135, "斗地主进桌扣台费"),

  /**
   * 斗地主兑换筹码.
   */
  DDZ_EXCHANGE_CHIP(136, "斗地主兑换筹码"),

  /**
   * 斗地主兑换金币.
   */
  DDZ_EXCHANGE_GOLD(137, "斗地主兑换金币"),

  /**
   * 斗地主离开桌子兑换金币.
   */
  DDZ_EXIT_TABLE_EXCHANGE_GOLD(138, "斗地主离开桌子兑换金币"),

  /**
   * 斗地主返还台费.
   */
  DDZ_RETURN_AFEE(139, "斗地主返还台费"),

  /**
   * 金蝉捕鱼退桌.
   */
  JCBY_EXIT_TABL(143, "金蝉捕鱼退桌"),


  /**
   * 金蝉捕鱼开火.
   */
  JCBY_FIRE(144, "金蝉捕鱼开火"),

  /**
   * 金蝉捕鱼打中鱼.
   */
  JCBY_HIT(145, "金蝉捕鱼打中鱼"),

  JCBY_EXCHANGE_CHIP(146, "金蝉捕鱼兑换筹码"),

  JCBY_EXCHANGE_GOLD(147, "金蝉捕鱼兑换金币"),

  JCBY_EXIT_TABLE_EXCHANGE_GOLD(148, "金蝉捕鱼离开桌子兑换金币"),

  JCBY_EXIT_BILL(149, "金蟾捕鱼离开结算"),

  /**
   * 李逵捕鱼退桌.
   */
  LKBY_EXIT_TABL(153, "李逵捕鱼退桌"),

  /**
   * 李逵捕鱼开火.
   */
  LKBY_FIRE(154, "李逵捕鱼开火"),

  /**
   * 李逵捕鱼打中鱼.
   */
  LKBY_HIT(155, "李逵捕鱼打中鱼"),

  /**
   * 扎金花下注.
   */
  ZJH_BET(160, "扎金花下注"),


  /**
   * 扎金花比牌.
   */
  ZJH_VERSUS(161, "扎金花比牌"),

  /**
   * 扎金花结算.
   */
  ZJH_BILL(162, "扎金花结算"),

  /**
   * 扎金花进桌扣台费.
   */
  ZJH_ENTER_TABLE_MINUS_AFEE(163, "扎金花进桌扣台费"),

  /**
   * 扎金花扣除底注.
   */
  ZJH_READY_MINUS_BASE_BET(164, "扎金花扣除底注"),

  /**
   * 百家乐结算.
   */
  BACCARAT_BILL(200, "百家乐结算"),

  /**
   * 百家乐进桌子扣台费.
   */
  BACCARAT_ENTER_TABLE_MINUS_AFEE(201, "百家乐进桌子扣台费"),

  /**
   * 百家乐下注.
   */
  BACCARAT_BET(202, "百家乐下注"),


  /**
   * 百家乐下注清空.
   */
  BACCARAT_CLEAR(203, "百家乐下注清空"),

  /**
   * BACCARAT_RETURN_BET 百家乐离桌，退还筹码.
   */
  BACCARAT_RETURN_BET(204, "百家乐离桌，退还筹码"),

  /**
   * 百家乐兑换筹码.
   */
  BACCARAT_EXCHANGE_CHIP(205, "百家乐兑换筹码"),

  /**
   * 百家乐退出牌桌兑换金币.
   */
  BACCARAT_EXIT_TABLE_EXCHANGE_GOLD(206, "百家乐退出牌桌兑换金币"),

  /**
   * 百家乐玩家手动兑换金币.
   */
  BACCARAT_EXCHANGE_GOLD(207, "百家乐玩家手动兑换金币"),

  /**
   * 魏蜀吴结算.
   */
  WEISHUWU_BILL(170, "魏蜀吴结算"),

  /**
   * 魏蜀吴进桌扣台费.
   */
  WEISHUWU_ENTER_TABLE_MINUS_AFEE(171, "魏蜀吴进桌扣台费"),


  /**
   * 魏蜀吴清空下注.
   */
  WEISHUWU_CLEAR_BET(172, "魏蜀吴清空下注"),

  /**
   * 魏蜀吴下注.
   */
  WEISHUWU_BET(173, "魏蜀吴下注"),

  /**
   * 血战到底下雨.
   */
  XZDD_XIA_YU(181, "血战到底下雨"),

  /**
   * 血战到底刮风.
   */
  XZDD_GUA_FENG(182, "血战到底刮风"),

  /**
   * 血战到底胡牌.
   */
  XZDD_HU(183, "血战到底胡牌"),


  /**
   * 血战到底查牌.
   */
  XZDD_CHA_PAI(184, "血战到底查牌"),

  /**
   * 血战到底退出游戏.
   */
  XZDD_EXIT_GAME(185, "血战到底退出游戏"),


  /**
   * 定制麻将下雨.
   */
  ORDERMA_XIA_YU(191, "定制麻将下雨"),

  /**
   * 定制麻将刮风.
   */
  ORDERMA_GUA_FENG(192, "定制麻将刮风"),

  /**
   * 定制麻将到底胡牌.
   */
  ORDERMA_HU(193, "定制麻将到底胡牌"),

  /**
   * 定制麻将到底查牌.
   */
  ORDERMA_CHA_PAI(194, "定制麻将到底查牌"),

  /**
   * 定制麻将退出游戏.
   */
  ORDERMA_EXIT_GAME(195, "定制麻将退出游戏"),

  /**
   * 悟空闹海退桌.
   */
  WKNH_EXIT_TABL(210, "悟空闹海退桌"),

  /**
   * 悟空闹海开火.
   */
  WKNH_FIRE(211, "悟空闹海开火"),

  /**
   * 悟空闹海打中鱼.
   */
  WKNH_HIT(212, "悟空闹海打中鱼"),



  /**
   * 水果拉霸玩家进入桌子扣除台费.
   */
  FRUITSLOT_ENTER_TABLE_MINUS_AFEE(250, "水果拉霸玩家进入桌子扣除台费"),

  /**
   * 水果拉霸玩家手动兑换金币.
   */
  FRUITSLOT_EXCHANGE_GOLD(251, "水果拉霸玩家手动兑换金币"),

  /**
   * 水果拉霸结算.
   */
  FRUITSLOT_BILL(252, "水果拉霸结算"),

  /**
   * 水果拉霸骰子比倍结算.
   */
  FRUITSLOT_DICE_BILL(253, "水果拉霸骰子比倍结算"),

  /**
   * 水果拉霸水果篮结算.
   */
  FRUITSLOT_XIAO_MA_LI_BILL(254, "水果拉霸水果篮结算"),

  /**
   * 水果拉霸押注.
   */
  FRUITSLOT_BET(255, "水果拉霸押注"),


  /**
   * 21点玩家进入桌子扣除台费.
   */
  BLACKJACK_ENTER_TABLE_MINUS_AFEE(270, "21点玩家进入桌子扣除台费"),

  /**
   * 21点玩家手动兑换金币.
   */
  BLACKJACK_EXCHANGE_GOLD(271, "21点玩家手动兑换金币"),


  /**
   * 21点押注.
   */
  BLACKJACK_BET(272, "21点押注"),


  /**
   * BLACKJACK_BET_DOUBLE_DOWN 21点押注.
   */
  BLACKJACK_BET_DOUBLE_DOWN(273, "21点双倍押注"),

  /**
   * BLACKJACK_INSURANCE 21点买保险.
   */
  BLACKJACK_INSURANCE(274, "21点买保险"),

  /**
   * BLACKJACK_INSURANCE_BILL 21点保险结算.
   */
  BLACKJACK_INSURANCE_BILL(275, "21点保险结算"),
  /**
   * 21点结算.
   */
  BLACKJACK_BILL(276, "21点结算"),

  /**
   * BLACKJACK_SPLIT 21点分牌.
   */
  BLACKJACK_SPLIT(272, "21点分牌"),


  THIRD_RECHARGE(1001, "主站充值"),

  THIRD_WITHDRAW(1002, "主站提现"),

  /**
   * 后台增加金币.
   */
  BACKEND_ADD_GOLD(1003, "后台增加金币"),


  /**
   * 后台扣除金币.
   */
  BACKEND_MINUS_GOLD(1004, "后台扣除金币"),

  /*
   * Copyright 2018 the original author or authors.
   *
   * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
   * in compliance with the License. You may obtain a copy of the License at
   *
   * http://www.apache.org/licenses/LICENSE-2.0
   *
   * Unless required by applicable law or agreed to in writing, software distributed under the License
   * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   * or implied. See the License for the specific language governing permissions and limitations under
   * the License.
   */


  ;

  // 标识id
  public final int id;
  // 描述
  public final String desc;

  private LogReason(int id, String desc) {
    this.id = id;
    this.desc = desc;
  }

  @Override
  public String toString() {
    return desc;
  }
}
