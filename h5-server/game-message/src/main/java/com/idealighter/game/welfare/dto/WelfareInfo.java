
package com.idealighter.game.welfare.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 福利信息.
 *
 */
@Data
public class WelfareInfo {
  // 类型(1:签到,2:救济金)
  @Protobuf(order = 1)
  private int type;
  // 次数(剩余)
  @Protobuf(order = 2)
  private int num;
  // 面板显示参数
  @Protobuf(fieldType = FieldType.STRING, order = 3)
  private List<String> params = new ArrayList<>();
}
