package com.idealighter.game.core.convert;

public class BooleanStateConvert {

  public Boolean toBoolean(Byte state) {
    return state != null && state == 1 ? true : false;
  }

  public Byte toByte(Boolean bool) {
    return bool != null && bool ? (byte) 1 : (byte) 0;
  }
}
