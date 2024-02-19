
package com.idealighter.game.core.service.games.common.buyu;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.dictionary.domain.JcbyCurveDomain;

import java.util.List;

/**
 * 路径 .
 * 
 * @date 2016年3月8日 下午5:20:36
 *
 */
public class Road {
  // 路径id
  public final int id;
  // 夹角
  public final int angle;
  // 缩放
  public final int ratio;
  // 路径曲线
  public final List<Curve> curves;
  // 路径总时间(毫秒)
  public final long lifeTime;

  /**
   * 构造函数 .
   * 
   * @param curveDom .
   */
  public Road(JcbyCurveDomain curveDom) {
    this.id = curveDom.getId();
    this.angle = curveDom.getAngle();
    this.ratio = curveDom.getRatio();
    this.curves = JSON.parseArray(curveDom.getData(), Curve.class);
    long lifeTime = 0;
    for (Curve curve : curves) {
      lifeTime += curve.getTime() * 1000;
    }
    this.lifeTime = lifeTime;
  }

  /**
   * 根据起点和时间t计算位置 .
   * 
   * @param start .
   * @param t . 路径时间毫秒
   * @param fishAngle . 鱼的夹角
   * @return
   */
  public Position position(Position start, long t, int fishAngle) {
    long startT = 0;
    long endT = 0;

    // 用比例尺和相对坐标计算的起点
    Position ratioPos = new Position();
    for (int i = 0; i < curves.size(); i++) {
      Curve curve = curves.get(i);
      endT += curve.getTime() * 1000;
      if (t < startT) {
        break;
      } else if (t <= endT) {
        ratioPos = curve.position(ratioPos, t - startT);
      } else {
        ratioPos = curve.position(ratioPos, endT - startT);
      }
      startT += curve.getTime() * 1000;
    }

    if (fishAngle > 0) {
      double radians = Math.toRadians(fishAngle);
      double angleX = ratioPos.xcoord * Math.cos(radians) + ratioPos.ycoord * Math.sin(radians);
      double angleY = ratioPos.ycoord * Math.cos(radians) - ratioPos.xcoord * Math.sin(radians);
      ratioPos = new Position(angleX, angleY);
    } else if (this.angle > 0) {
      double radians = Math.toRadians(this.angle);
      double angleX = ratioPos.xcoord * Math.cos(radians) + ratioPos.ycoord * Math.sin(radians);
      double angleY = ratioPos.ycoord * Math.cos(radians) - ratioPos.xcoord * Math.sin(radians);
      ratioPos = new Position(angleX, angleY);
    }

    return new Position(start.xcoord + ratioPos.xcoord * ratio,
        start.ycoord + ratioPos.ycoord * ratio);
  }

}
