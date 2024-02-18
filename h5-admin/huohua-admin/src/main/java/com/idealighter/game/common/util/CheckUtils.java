package com.idealighter.game.common.util;

import java.util.regex.Pattern;

public class CheckUtils {

  /**
   * 是否是数字.
   * @param str 待判定字符串.
   * @return 结果.
   */
  public static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(str).matches();
  }
}
