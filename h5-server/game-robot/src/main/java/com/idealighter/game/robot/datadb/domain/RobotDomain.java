
package com.idealighter.game.robot.datadb.domain;

import lombok.Data;

/**
 * 机器人
 * 
 * @date 2015年10月29日 下午12:59:31
 *
 */
@Data
public class RobotDomain {
  // 玩家id
  private long id;
  // 靓号
  private int superId;
  // 用户名
  private String userName;
  // 玩家昵称
  private String playerName;
  // 是否锁住
  private boolean locked;
  // 数据创建时间
  // private Date createTime;


}
