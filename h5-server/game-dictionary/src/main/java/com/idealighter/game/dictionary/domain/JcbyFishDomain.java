
package com.idealighter.game.dictionary.domain;

import lombok.Data;

/**
 * @author exccel .-generator .
 *
 */
@Data
public class JcbyFishDomain {

  // 鱼id
  private int id;
  // 包围盒id
  private int bbxId;
  // 锁定等级
  private int lockLeve;
  // 鱼类型(1:普通鱼,2:同类炸弹,3:全屏炸弹)
  private int type;
  // 同类炸弹鱼种类
  private String fishs;
  // 最小倍率
  private int minRate;
  // 最大倍率
  private int maxRate;
  // 鱼死亡后的刷鱼策略id
  private int deathStrategy;
  // 公告类型(0:无公告,1:全服公告,2:本房间公告)
  private int noticeType;
  // 公告内容
  private String noticeContent;
  // 描述
  private String desc;

}
