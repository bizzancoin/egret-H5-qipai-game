
package com.idealighter.game.plaza.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ResMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.plaza.dto.GameType;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 游戏类型.
 *
 */
@ResMsg(111201)
@Data
public class ResGameTypesMsg implements ResMessage {
  @Protobuf(order = 1)
  private int id = 111201;
  // 游戏类型
  @Protobuf(order = 2)
  private List<GameType> types = new ArrayList<>();
}
