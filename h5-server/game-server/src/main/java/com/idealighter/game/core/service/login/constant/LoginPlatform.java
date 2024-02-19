package com.idealighter.game.core.service.login.constant;

public enum LoginPlatform {

  IOS(0, "ios"), ANDROID(1, "android"), PC(2, "pc"), ROBOT(3, "robot");

  private int id;
  private String name;

  LoginPlatform(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static boolean isClient(int platform) {
    return platform == IOS.id || platform == ANDROID.id || platform == PC.id
        || platform == ROBOT.id;
  }
}
