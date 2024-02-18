package com.idealighter.game.service.player.bo;

import java.util.Date;

import lombok.Data;

@Data
public class PlayerListBo {
  /**
   * 玩家id.
   */
  private Long id;

  /**
   * 靓号id.
   */
  private Long superId;

  /**
   * 用户名.
   */
  private String userName;

  /**
   * 玩家昵称.
   */
  private String playerName;

  /**
   * 手机号.
   */
  private String phone;


  /**
   * 金币.
   */
  private Long gold;

  /**
   * 保险箱金币.
   */
  private Long safeGold;

  /**
   * 是否在线.
   */
  private Boolean online;

  /**
   * 是否锁住.
   */
  private Boolean locked;


  /**
   * 注册ip.
   */
  private String registerIp;

  /**
   * 注册时间.
   */
  private Date registerTime;

  /**
   * 登录ip.
   */
  private String loginIp;

  /**
   * 登录时间.
   */
  private Date loginTime;

  /**
   * 登录次数.
   */
  private Integer loginCount;

  /**
   * 退出时间.
   */
  private Date logoutTime;

  /**
   * 游戏时间(毫秒).
   */
  private Long gametime;

  /**
   * 在线时长(毫秒).
   */
  private Long onlinetime;
  

  /**
   * 0:机器人,1:客户端,2:网页.
   */
  private Byte registerType;

  /**
   * 是否为游客.
   */
  private Boolean tourist;

  /**
   * 0普通用户 1代理.
   */
  private Byte type;
  
  private String channelId;

}
