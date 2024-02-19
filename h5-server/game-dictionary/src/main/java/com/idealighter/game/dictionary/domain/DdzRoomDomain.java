package com.idealighter.game.dictionary.domain;

import lombok.Data;

/**
 * @author exccel .-generator.
 *
 */
@Data
public class DdzRoomDomain {
  // 房间id
  private int id;
  // 房间名称
  private String name;
  // 房间类型(errenniuniu_room_type)
  private int type;
  // 房间最大人数
  private int maxNum;
  // 进入下限
  private long lower;
  // 进入上限
  private long upper;
  // 金币与筹码比例（金币）
  private int proportionGold;
  // 金币与筹码比例（筹码）
  private int proportionChips;
  // 桌子数
  private int tableNum;
  // 每桌椅子数
  private int chair;
  // 底分
  private long base;
  // 单局台费（筹码）
  private int afee;
  // 进入类型（0点击入座，1自动分配）
  private int inType;
  // 普通玩家进入人数
  private int ordinarPeople;
  // 机器人所占的比例
  private int robotRatio;
  // vip玩家进入最大人数
  private int vipPeople;
  // 状态(0:关闭,1:开启)
  private byte isActive;
  // 创建时间
  private java.util.Date timeCreate;
  // 开启时间
  private java.util.Date timeOpen;
}
