package com.idealighter.game.core.constant;

public enum Operator {
  SYSTEM(0);

  private int code;

  private Operator(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
