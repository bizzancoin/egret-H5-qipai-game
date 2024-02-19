package com.idealighter.game.core.util;

import com.idealighter.utils.crypto.MD5Utils;

public class PwdEncode {
  private static String SALT = "IDEALIGHTER";

  public static String encodeWithTail(String value) {
    return MD5Utils.encode(value + SALT);
  }

  public static void main(String[] args) {
    System.out.println(encodeWithTail("robot1208363101001"));
  }
}
