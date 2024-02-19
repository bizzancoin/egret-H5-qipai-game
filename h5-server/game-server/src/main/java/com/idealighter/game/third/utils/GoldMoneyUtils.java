package com.idealighter.game.third.utils;

import java.text.DecimalFormat;

public class GoldMoneyUtils {

  /**
   * money 转金币 .
   *
   * @author abin
   * @date 2018年6月21日 下午7:57:21
   * @param money 钱.
   * @return 金币.
   */
  public static long moneyToGold(String money) {
    int index = money.indexOf(".");
    int length = money.length();
    long amLong = 0;
    if (index == -1) {
      amLong = Long.valueOf(money + "000");
    } else if (length - index >= 4) {
      amLong = Long.valueOf((money.substring(0, index + 4)).replace(".", ""));
    } else if (length - index == 3) {
      amLong = Long.valueOf((money.substring(0, index + 3)).replace(".", "") + "0");
    } else if (length - index == 2) {
      amLong = Long.valueOf((money.substring(0, index + 2)).replace(".", "") + "00");
    } else {
      amLong = Long.valueOf((money.substring(0, index + 1)).replace(".", "") + "000");
    }
    return amLong;
  }


  /**
   * 金币转钱.
   *
   * @author abin
   * @date 2018年6月21日 下午7:56:53
   * @param gold 金币.
   * @return money.
   */
  public static String goldToMoney(Long gold) {
    String result = null;
    if (gold == null) {
      result = "0.000";
    } else {
      DecimalFormat jiaoDecimalFormat = new DecimalFormat("0.000");
      result = jiaoDecimalFormat.format(gold / 1000.0);
    }

    return result;
  }

  /**
   * test .
   *
   * @author abin
   * @date 2018年6月21日 下午8:02:55
   * @param args args.
   */
  public static void main(String[] args) {
    System.out.println(moneyToGold("5.514212"));

    System.out.println(moneyToGold("5.5149"));

    System.out.println(moneyToGold("5.5144"));

    System.out.println(moneyToGold("5.51"));

    System.out.println(moneyToGold("5.5"));

    System.out.println(moneyToGold("5"));
    System.out.println(moneyToGold("5.0"));

    System.out.println(moneyToGold("5."));

    System.out.println(moneyToGold("1"));

    System.out.println(goldToMoney(212122L));

    System.out.println(goldToMoney(0L));

    System.out.println(goldToMoney(-1212L));

    System.out.println(goldToMoney(-1210L));
    System.out.println(goldToMoney(-1200L));
    System.out.println(goldToMoney(-1000L));
  }
}
