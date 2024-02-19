
package com.idealighter.game.core.util;

import com.idealighter.utils.code.RandCodeUtil;

/**
 * 随机检验码生成去器.
 *
 */
public class RandomKeyGenerator {

  private static final char[] KEY_CHARS = { 'a', 'b', 'C', 'D', 'E', 'F', 'G', 'h', 'i', 'j', 'k',
      'L', 'm', 'N', 'o', 'P', 'q', 'r', 's', 't', 'U', 'V', 'W', 'X', 'Y', 'z', '0', '1', '2', '3',
      '4', '5', '6', '7', '8', '9' };

  private static final char[] NUM_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

  private RandomKeyGenerator() {}

  /**
   * 生成校验码.
   * 
   * @param num . 校验码长度
   * @return .
   */
  public static String generate(int num) {
    char[] data = new char[num];
    for (int i = 0; i < num; i++) {
      data[i] = KEY_CHARS[RandCodeUtil.random(KEY_CHARS.length)];
    }

    return String.valueOf(data);
  }

  /**
   * 生成校验码.
   * 
   * @param num . 校验码长度
   * @return .
   */
  public static String generateNum(int num) {
    char[] data = new char[num];
    for (int i = 0; i < num; i++) {
      data[i] = NUM_CHARS[RandCodeUtil.random(NUM_CHARS.length)];
    }

    return String.valueOf(data);
  }

}
