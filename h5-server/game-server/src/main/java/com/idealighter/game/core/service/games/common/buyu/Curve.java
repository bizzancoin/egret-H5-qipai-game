package com.idealighter.game.core.service.games.common.buyu;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 路径曲线 . 路径曲线配置, id,曲线id angle,路径旋转度数，正为顺时针方向 0 ~ 360 负为逆时针方向 -360 ~ 0
 * ratio,比例尺，分子为屏幕宽度，分母为曲线x轴上最大取值范围 . data = {--曲线构成 [1] = {--一条曲线描述 t,这段曲线运动的时间 x1,x2,曲线的取值范围
 * ftype,曲线类型，1：抛物线[y . = ax2 + bx + c]， 2：三次曲线[y = ax3]， 3：指数函数[y = a(x)]， 4：对数函数[y = alog(x)]，
 * 5：正弦曲线[y . = asin(x)]， 6：余弦曲线[y = acos(x)] 7：圆[(x + a)2 + (y + b)2 = r2]， 8：直线[y = kx] param = {
 * a,b,c,r,k：为各曲线对应的参数 . } }, [...] = {...}, }
 *
 */
@Data
public class Curve {
  // 时间(秒)
  @JSONField(name = "t")
  private double time;
  // 取值范围
  private double x1;
  // 取值范围
  private double x2;
  // 函数曲线类型
  private int ftype;
  // 函数参数
  private Fparam fparam;

  /**
   * 根据起点和时间计算位置 .
   * 
   * @param start .
   * @param t . 毫秒 .
   * @return
   */
  public Position position(Position start, long t) {
    CurveType type = CurveType.get(ftype);
    double v = speed();
    switch (type) {
      case CIRCLE:
        return start.add(circle(fparam, v * t));
      case COS:
        return start.add(acos(fparam, v * t));
      case CUBICCVE:
        return start.add(acubiccve(fparam, v * t));
      case EXPONENTIAL:
        return start.add(aexponential(fparam, v * t));
      case LINE:
        return start.add(kline(fparam, v * t));
      case LOGARITHM:
        return start.add(alogarithm(fparam, v * t));
      case PARABOLA:
        return start.add(parabola(fparam, v * t));
      case SIN:
        return start.add(asin(fparam, v * t));
      default:
        throw new RuntimeException("不支持的曲线类型:" + type);
    }
  }

  /**
   * 速度 .
   * 
   * @return
   */
  public double speed() {
    return (x2 - x1) / (time * 1000);
  }

  /**
   * 抛物线[y . = ax2 + bx + c] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position parabola(Fparam fparam, double x) {

    return new Position(x, fparam.avar * x * x + fparam.bvar * x + fparam.cvar);
  }

  /**
   * 三次曲线[y . = ax3] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position acubiccve(Fparam fparam, double x) {

    return new Position(x, fparam.avar * x * x * x);
  }

  /**
   * 指数函数[y . = a(x)] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position aexponential(Fparam fparam, double x) {

    return new Position(x, Math.pow(fparam.avar, x));
  }

  /**
   * 对数函数[y . = alog(x)] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position alogarithm(Fparam fparam, double x) {

    return new Position(x, fparam.avar * Math.log(x));
  }

  /**
   * 正弦曲线[y . = asin(x)] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position asin(Fparam fparam, double x) {

    return new Position(x, fparam.avar * Math.sin(fparam.bvar * x));
  }

  /**
   * 余弦曲线[y . = acos(x)] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position acos(Fparam fparam, double x) {

    return new Position(x, fparam.avar * Math.cos(fparam.bvar * x));
  }

  /**
   * 圆[(x . + a)2 + (y + b)2 = r2] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position circle(Fparam fparam, double x) {
    if (fparam.way == 1) {
      return new Position(x,
          Math.sqrt(Math.pow(fparam.rvar, 2) - Math.pow(x + fparam.avar, 2)) - fparam.bvar);
    } else {
      return new Position(x,
          -Math.sqrt(Math.pow(fparam.rvar, 2) - Math.pow(x + fparam.avar, 2)) - fparam.bvar);
    }
  }

  /**
   * 直线[y . = kx] .
   * 
   * @param fparam .
   * @param x .
   * @return
   */
  public static Position kline(Fparam fparam, double x) {

    return new Position(x, fparam.kvar * x);
  }

  /**
   * 函数参数 .
   * 
   * 
   * @date 2016年3月8日 下午6:47:09
   *
   */
  @Data
  public class Fparam {
    @JSONField(name = "a")
    private double avar;
    @JSONField(name = "b")
    private double bvar;
    @JSONField(name = "c")
    private double cvar;
    private int way;
    @JSONField(name = "r")
    private double rvar;
    @JSONField(name = "k")
    private double kvar;
  }
}
