package com.idealighter.game.core.error;

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

  public static ErrorCode getById(int code) {
    ErrorCode result = null;
    for (ErrorCode errorCode : ErrorCode.values()) {
      if (errorCode.getCode() == (code)) {
        result = errorCode;
      }
    }
    return result;
  }

}
