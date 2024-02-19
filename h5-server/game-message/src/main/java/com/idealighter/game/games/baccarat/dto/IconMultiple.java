
package com.idealighter.game.games.baccarat.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 区域图标倍率 .
 *
 */
@Data
public class IconMultiple {
  // 图标ID
  @Protobuf(order = 1)
  private int areaId;
  // 图标倍率(客户端缩小100倍后显示)
  @Protobuf(order = 2)
  private int rate;
}
