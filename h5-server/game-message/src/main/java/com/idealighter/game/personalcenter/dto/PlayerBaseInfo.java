
package com.idealighter.game.personalcenter.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 玩家基本信息 . .
 *
 */
@Data
public class PlayerBaseInfo {
  // 头像id
  @Protobuf(order = 1)
  private int icon;
  // 昵称
  @Protobuf(order = 2)
  private String nickName;
}
