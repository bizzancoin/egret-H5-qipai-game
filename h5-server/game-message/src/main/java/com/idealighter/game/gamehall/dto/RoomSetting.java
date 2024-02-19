
package com.idealighter.game.gamehall.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 房间设置.
 */
@Data
public class RoomSetting {
  // 其他玩家金币限制
  @Protobuf(order = 1)
  private long tableLimitGold;
  // 其他玩家金币限制是否有效(0:无效,非0:有效)
  @Protobuf(order = 2)
  private int tableLimitGoldAbled;
  // 是否不与同ip的玩家玩(0:否,非0:是)
  @Protobuf(order = 3)
  private int tableLimitIp;
  // 桌子密码
  @Protobuf(order = 4)
  private String tablePwd;
  // 桌子密码是否生效(0:无效,非0:有效)
  @Protobuf(order = 5)
  private int tablePwdAbled;
  // 当前房间设置是否生效(0:无效,非0:有效)
  @Protobuf(order = 6)
  private int roomSettingAbled;
}
