package com.idealighter.game.common.constant;

public class AdminTypeConstant {
  public static final byte ADMIN = 1;
  public static final byte SUPER_ADMIN = 2;

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
        case ADMIN:
        case SUPER_ADMIN:
          result = true;
          break;
        default:
          break;
      }
    }
    return result;
  }
}
