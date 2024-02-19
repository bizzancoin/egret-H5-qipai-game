
package com.idealighter.game.robot.http.struct;

/**
 * 后台gm调用结果消息.
 *
 */
public class ResBackendOrder {

  // GM命令执行后的状态(1:成功, 0:失败)
  private int res;

  // 失败时对失败原因的说明
  private String msg;

  // 有返回的GM命令的执行结果 。如果不是单个字符串或数值 则将结果序列化为json字符串;无返回是为空串
  private String data;

  public ResBackendOrder() {}

  /**
   * 构造函数.
   * 
   * @param res .
   * @param msg .
   * @param data .
   */
  public ResBackendOrder(int res, String msg, String data) {
    this.res = res;
    this.msg = msg;
    this.data = data;
  }

  public int getRes() {
    return res;
  }

  public void setRes(int res) {
    this.res = res;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
