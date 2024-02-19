
package com.idealighter.game.dictionary.domain;

import lombok.Data;


@Data
public class RobotConfigDomain {
  // id
  private int id;
  // 游戏
  private int game;
  // 房间
  private int room;
  // 是否允许占桌
  private byte occupyTable;
  // 是否允许和玩家对局
  private byte gameWithPlayer;
  // 初始携带金币下限
  private long goldLower;
  // 初始携带金币上限
  private long goldUpper;
  // 最大携带金币数
  private long maxGold;
  // 单桌游戏时长下限(秒)
  private int tableGameLower;
  // 单桌游戏时长上限(秒)
  private int tableGameUpper;
  // 游戏总时长下限(秒)
  private int totalGameLower;
  // 游戏总时长上限(秒)
  private int totalGameUpper;
  // 上庄最小金币
  private long beBankerChips;
  // 最大申请上庄人数
  private byte bankerApplyNums;
  // 机器人申请上庄人数
  private byte robotBankerApplyNums;
  // 0:00~2:00人数
  private int time1Players;
  // 2:00~4:00人数
  private int time2Players;
  // 4:00~6:00人数
  private int time3Players;
  // 6:00~8:00人数
  private int time4Players;
  // 8:00~10:00人数
  private int time5Players;
  // 10:00~12:00人数
  private int time6Players;
  // 12:00~14:00人数
  private int time7Players;
  // 14:00~16:00人数
  private int time8Players;
  // 16:00~18:00人数
  private int time9Players;
  // 18:00~20:00人数
  private int time10Players;
  // 20:00~22:00人数
  private int time11Players;
  // 22:00~24:00人数
  private int time12Players;
  // 配置是否开启
  private byte state;
}
