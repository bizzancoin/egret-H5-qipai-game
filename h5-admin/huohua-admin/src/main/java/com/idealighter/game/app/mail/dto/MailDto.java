package com.idealighter.game.app.mail.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MailDto {

  /**
   * 表id.
   */
  private Integer id;

  /**
   * 收件人uid 所有人=0 单人=uid.
   */
  @NotNull
  private Long playerId;

  private Long playerSuperId;

  /**
   * 收件人昵称.
   */
  private String playerName;

  /**
   * 发件人uid.
   */
  private Long fromPlayerId;

  private Long fromPlayerSuperId;

  /**
   * 发件人昵称.
   */
  private String fromPlayerName;

  /**
   * 附件金币.
   */
  private Long gold;

  /**
   * 标题.
   */
  private String title;

  /**
   * 0=未读,1=已读.
   */
  private Boolean isRead;

  /**
   * 邮件领取附件 0=未领取 1=已领取.
   */
  private Boolean isRecGold;

  /**
   * 时间.
   */
  private Date createTime;

  /**
   * 内容.
   */
  private String content;
}
