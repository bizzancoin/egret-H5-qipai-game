package com.idealighter.game.third.utils;

import com.idealighter.utils.code.RandCodeUtil;
import com.idealighter.utils.time.TimeUtil;

import java.util.Date;

public class ThirdOrderUtils {

  private static String uniqid() {
    return "" + RandCodeUtil.random(10000000L, 99999999L);
  }

  private static String microtime() {
    long mstime = System.currentTimeMillis();
    long seconds = mstime / 1000;
    double decimal = (mstime - (seconds * 1000)) / 1000d;
    return String.format("%.2f", decimal) + " " + seconds;
  }

  /**
   * 产生第三方订单号.
   *
   * @author abin
   * @date 2018年8月17日 下午4:09:17
   * @return 第三方订单号.
   */
  public static String generateThirdOrderNo() {
    StringBuilder builder = new StringBuilder();
    Date now = new Date();

    builder.append("Z");
    builder.append(TimeUtil.format("yyyyMMddHHmmss", now));
    builder.append(microtime().substring(2, 4));
    builder.append(uniqid());


    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(generateThirdOrderNo());
  }
}
