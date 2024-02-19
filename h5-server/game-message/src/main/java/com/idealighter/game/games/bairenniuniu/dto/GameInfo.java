package com.idealighter.game.games.bairenniuniu.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class GameInfo {
  @Protobuf(order = 1)
  private int stage; // 游戏阶段(1:休息, 2:下注, 3:发牌)
  @Protobuf(order = 2)
  private long stageTime; // 阶段剩余时间
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  private List<Integer> historyList = new ArrayList<>(); // 闲家对庄家的输赢记录
  @Protobuf(order = 4)
  private List<BetInfo> tableBetInfos = new ArrayList<>(); // 桌上筹码信息
  @Protobuf(order = 5)
  private List<BetInfo> myBetInfos = new ArrayList<>(); // 我的下注信息
  @Protobuf(order = 6, fieldType = FieldType.INT64)
  private List<Long> bankerApplicants = new ArrayList<>(); // 申请坐庄人列表
  @Protobuf(order = 7)
  private BankerInfo bankerInfo; // 庄家信息
  // 牌信息
  @Protobuf(order = 8)
  private List<CardsInfo> cardsInfo = new ArrayList<>();
  @Protobuf(order = 9)
  private BalanceInfo balanceInfo;

  @Data
  public static class BankerInfo {
    // 庄家id
    @Protobuf(order = 1)
    private long playerId;
    // 昵称
    @Protobuf(order = 2)
    private String name;
    // 性别 0男1女3系统
    @Protobuf(order = 3)
    private int sex;
    // 筹码
    @Protobuf(order = 4)
    private long chips;
    // 局数
    @Protobuf(order = 5)
    private int num;
    // 成绩
    @Protobuf(order = 6)
    private long score;
  }

  @Data
  public static class BalanceInfo {
    @Protobuf(order = 1)
    private long bankerChips; // 庄家结算信息
    // 玩家结算筹码
    @Protobuf(order = 2)
    private long playerChips; // 玩家自己的结算信息
  }
}
