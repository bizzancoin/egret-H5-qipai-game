package com.idealighter.game.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMap {
  private static Map<Integer, String> errors;

  static {
    errors = new HashMap<Integer, String>();
    for (ErrorCode errorCode : ErrorCode.values()) {
      errors.put(errorCode.getCode(), errorCode.getMessage());
    }
  }

  public static String getMsgByCode(int code) {
    return errors.get(code);
  }

}
