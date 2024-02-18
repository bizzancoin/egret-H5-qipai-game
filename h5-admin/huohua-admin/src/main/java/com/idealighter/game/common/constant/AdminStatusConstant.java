package com.idealighter.game.common.constant;

public class AdminStatusConstant {
  public static final byte LOCK = 0;
  public static final byte NORMAL = 1;

  /**
   * 包含 .
   * 
   * @param state 角色.
   * @return .
   */
  public static boolean contains(Byte state) {
    boolean result = false;
    if (state != null) {
      switch (state.byteValue()) {
        case LOCK:
        case NORMAL:
          result = true;
          break;
        default:
          break;
      }
    }
    return result;
  }
}
