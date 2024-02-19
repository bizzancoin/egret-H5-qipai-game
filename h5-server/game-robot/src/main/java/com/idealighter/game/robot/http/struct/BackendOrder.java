package com.idealighter.game.robot.http.struct;

import java.util.List;

/**
 * 后台命令对象.
 *
 */
public class BackendOrder {

  // GM命令
  private String cmd;
  // 命令参数
  private List<String> args;

  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public List<String> getArgs() {
    return args;
  }

  public void setArgs(List<String> args) {
    this.args = args;
  }

}
