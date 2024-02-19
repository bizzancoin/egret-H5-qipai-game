package com.idealighter.game.core.service.prizepool.struct.control;

/**
 * 难度范围 .
 * 
 * @date 2016年4月5日 下午4:48:15
 *
 */
public class FishDifficulty {
  // 鱼倍数下限
  private int multipleLower;
  // 鱼倍数上限
  private int multipleUpper;
  private int difficulty;

  public int getMultipleLower() {
    return multipleLower;
  }

  public void setMultipleLower(int multipleLower) {
    this.multipleLower = multipleLower;
  }

  public int getMultipleUpper() {
    return multipleUpper;
  }

  public void setMultipleUpper(int multipleUpper) {
    this.multipleUpper = multipleUpper;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }
}
