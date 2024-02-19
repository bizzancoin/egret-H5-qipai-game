package com.idealighter.game.core.service.games.common.buyu;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 坐标位置 .
 * 
 * @date 2016年1月26日 下午8:40:42
 *
 */
public class Position {
  // x坐标
  @JSONField(name = "x")
  public double xcoord;
  // y坐标
  @JSONField(name = "y")
  public double ycoord;

  public Position() {}

  /**
   * 构造函数 .
   * 
   * @param xcoord .
   * @param ycoord .
   */
  public Position(double xcoord, double ycoord) {
    this.xcoord = xcoord;
    this.ycoord = ycoord;
  }


  /**
   * 构造函数 .
   * 
   * @param pos .
   */
  public Position(Position pos) {
    this.xcoord = pos.xcoord;
    this.ycoord = pos.ycoord;
  }

  /**
   * 坐标增加 .
   * 
   * @param pos .
   * @return
   */
  public Position add(Position pos) {

    return new Position(this.xcoord + pos.xcoord, this.ycoord + pos.ycoord);
  }

}
