package com.idealighter.game.request;

public enum RobotServerUrl {

  ROBOT_SET("/robot/set"),

  ROBOT_RELOAD_ROBOT_CFG("/robot/reloadRobotCfg"),

  ROBOT_RELOAD_ROBOT_PLAYERS("/robot/reloadRobotPlayers"),
  
  ROBOT_RELOAD_ROOM("/robot/reloadRoom"),
  
  ROBOT_RELOAD_GAME("/robot/reloadGame");

  private String path;



  public String getPath() {
    return path;
  }



  private RobotServerUrl(String path) {
    this.path = path;
  }
}
