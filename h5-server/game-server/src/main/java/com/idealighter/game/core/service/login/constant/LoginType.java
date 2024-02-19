package com.idealighter.game.core.service.login.constant;

public enum LoginType {
  TOURIST(1, "游客"), PHONE(2, "手机"), ROBOT(3, "机器人"), WECHAT(4, "微信"), H5(5, "H5");

  private int type;
  private String name;

  LoginType(int type, String name) {
    this.type = type;
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
