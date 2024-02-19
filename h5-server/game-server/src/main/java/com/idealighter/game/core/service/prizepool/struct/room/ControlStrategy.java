package com.idealighter.game.core.service.prizepool.struct.room;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.idealighter.game.core.service.prizepool.struct.control.GameControl;

/**
 * 奖池控制策略.
 * 
 * @date 2015年10月15日 下午9:35:21
 *
 */
public class ControlStrategy {
  private Long id;
  // 提取下限
  private long lower;
  // 提取上限
  private long upper;
  // 提取时间间隔(分钟)
  private int time;
  // 提取类型(0:金币,非0:提取比例)
  private int withdrawType;
  // 提取金币
  private long gold;
  // 提取比例
  private int ratio;
  // 奖池控制(json字符串,每个游戏自己解析)
  private String control;

  public <C extends GameControl> C control(TypeReference<C> controlType) {

    return JSON.parseObject(control, controlType);
  }

  public ControlStrategy() {}

  /**
   * 构造函数.
   * 
   * @param lower .
   * @param upper .
   * @param time .
   * @param gold .
   * @param ratio .
   * @param control .
   */
  public ControlStrategy(long id, long lower, long upper, int time, long gold, int ratio,
      String control) {
    super();
    this.lower = lower;
    this.upper = upper;
    this.time = time;
    this.gold = gold;
    this.ratio = ratio;
    this.control = control;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLower() {
    return lower;
  }

  public void setLower(long lower) {
    this.lower = lower;
  }

  public long getUpper() {
    return upper;
  }

  public void setUpper(long upper) {
    this.upper = upper;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public long getGold() {
    return gold;
  }

  public void setGold(long gold) {
    this.gold = gold;
  }

  public int getRatio() {
    return ratio;
  }

  public void setRatio(int ratio) {
    this.ratio = ratio;
  }

  public String getControl() {
    return control;
  }

  public void setControl(String control) {
    this.control = control;
  }

  public int getWithdrawType() {
    return withdrawType;
  }

  public void setWithdrawType(int withdrawType) {
    this.withdrawType = withdrawType;
  }

}
