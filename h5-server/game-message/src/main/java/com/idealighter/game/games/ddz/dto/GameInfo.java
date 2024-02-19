package com.idealighter.game.games.ddz.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GameInfo {
  @Protobuf(order = 1)
  private int stage; // 游戏阶段(0:准备阶段, 1:叫庄阶段, 2:下注阶段, 3:摊牌阶段)
  @Protobuf(order = 2)
  private int stageTime; // 阶段剩余时间
  @Protobuf(order = 3)
  private int spring; // 春天(-1:反春天,0:普通,1:春天)
  // 用户准备情况
  @Protobuf(order = 4)
  private List<ReadyItem> readyList = new ArrayList<ReadyItem>();
  // 我自己的牌
  @Protobuf(fieldType = FieldType.INT32, order = 5)
  private List<Integer> myCards = new ArrayList<>();
  // 用户叫牌情况
  @Protobuf(order = 6)
  private List<CallTypeItem> callTypeList = new ArrayList<CallTypeItem>();
  // 下一个叫牌玩家编号
  @Protobuf(order = 7)
  private long nextCallPlayerId; //
  // 地主编号,尚未确定时添0
  @Protobuf(order = 8)
  private long landlord;
  // 加倍情况
  @Protobuf(order = 9)
  private List<CallDoubleItem> callDoubleList = new ArrayList<CallDoubleItem>();
  @Protobuf(order = 10)
  private List<TrusteeItem> trusteeList = new ArrayList<TrusteeItem>();
  @Protobuf(order = 11)
  private List<CardNumItem> cardNumList = new ArrayList<>();
  // 隐藏的三张牌
  @Protobuf(fieldType = FieldType.INT32, order = 12)
  private List<Integer> hiddenCards = new ArrayList<>();
  // 游戏倍数
  @Protobuf(order = 13)
  private int multiple;
  // 上一家出牌玩家编号,没有为0
  @Protobuf(order = 14)
  private long prePlayerId; //
  // 上一家出牌的牌型编号
  @Protobuf(order = 15)
  private long prePlayCardTypeId; // 1:单张,2:一对,3:三张牌,4:三带1,5：三带对,6:顺子,7:连对,8飞机,9:飞机带单,10飞机带对,11:炸弹,12:王炸,13:炸弹带两单，14：炸弹带两对
  // 上一家出的牌
  @Protobuf(fieldType = FieldType.INT32, order = 16)
  private List<Integer> prePlayCards = new ArrayList<>();
  // 下一家出牌的玩家编号
  @Protobuf(order = 17)
  private long nextPlayerId; //

  @Protobuf(order = 18)
  private List<BillInfo> billInfos = new ArrayList<>();
  //

  @Data
  public static class ReadyItem {
    @Protobuf(order = 1)
    private int order;
    @Protobuf(order = 2)
    private long playerId;
    @Protobuf(order = 3)
    private int ready;
  }

  @Data
  public static class CallTypeItem {
    @Protobuf(order = 1)
    private int order; // 玩家座位编号
    @Protobuf(order = 2)
    private long playerId; // 玩家编号
    @Protobuf(order = 3)
    private int callType; // 叫牌类型(0:不叫地主,1:叫地主,2:不抢地主,3:抢地主)
  }

  @Data
  public static class TrusteeItem {
    @Protobuf(order = 1)
    private int order; // 玩家座位编号
    @Protobuf(order = 2)
    private long playerId; // 玩家编号
    @Protobuf(order = 3)
    private int trustee; // 0:非托管,1:托管
  }

  @Data
  public static class CardNumItem {
    @Protobuf(order = 1)
    private int order; // 玩家座位编号
    @Protobuf(order = 2)
    private long playerId; // 玩家编号
    @Protobuf(order = 3)
    private int num; // 牌数量
  }

  @Data
  public static class CallDoubleItem {
    @Protobuf(order = 1)
    private int order; // 玩家座位编号
    @Protobuf(order = 2)
    private long playerId; // 玩家编号
    @Protobuf(order = 3)
    private int doubleCount; // 加倍情况(0:不加倍,1:加倍,2:超级加倍)
  }
}
