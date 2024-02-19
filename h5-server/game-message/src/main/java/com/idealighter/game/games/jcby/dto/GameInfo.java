
package com.idealighter.game.games.jcby.dto;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 金蝉捕鱼房间信息 .
 *
 */
@Data
public class GameInfo {
  @Protobuf(order = 1)
  private List<LockInfo> lockInfos = new ArrayList<>();
 
  @Data
  public static class LockInfo {
    @Protobuf(order = 1)
    private long playerId;
    //锁定鱼编号
    @Protobuf(order = 2)
    private int lockFishId;
    // 锁定方向
    @Protobuf(order = 3)
    private int lockAngle=-1;
 }
}
