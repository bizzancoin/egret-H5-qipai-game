package com.idealighter.game.admin.roomctl.dto;

import lombok.Data;

@Data
public class StrategyDto {
  private Long id;
  // 奖池控制(json字符串,每个游戏自己解析)
  private String control;
  // 提取金币
  private Long gold;
  // 提取下限
  private Long lower;
  // 提取上限
  private Long upper;
  // 提取比例
  private int ratio;
  // 提取时间间隔(分钟)
  private int time;
  // 提取类型(0:金币,非0:提取比例)
  private int withdrawType;
}
