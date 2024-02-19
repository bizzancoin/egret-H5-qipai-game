
package com.idealighter.game.dictionary.domain;

import lombok.Data;

/**
 * @author exccel .-generator .
 *
 */
@Data
public class JcbyStrategyDomain {

  // 策略id
  private int id;
  // 类型(1:普通,2:鱼阵,3:点,4:线,5:圆,6:鱼排,7:死后出圈鱼)
  private int type;
  // 配置数据(json格式)
  private String data;
  // 描述
  private String desc;

}
