
package com.idealighter.game.dictionary.domain;

import lombok.Data;

/**
 * @author exccel .-generator .
 *
 */
@Data
public class GameNoticeDomain {

  // id
  private int id;
  // 游戏ID
  private int game;
  // 公告需要满足的条件
  private String condition;
  // 条件描述（辨识用）
  private String desc;
  // 是否跑马灯显示（否：0；是：1）
  private int marqueeShow;
  // 聊天区是否显示（否：0；是：1）
  private int chatShow;
  // 公告内容
  private String content;
  // 间隔时间
  private int interval;
  // 显示次数
  private int times;
  // 颜色
  private String color;
}
