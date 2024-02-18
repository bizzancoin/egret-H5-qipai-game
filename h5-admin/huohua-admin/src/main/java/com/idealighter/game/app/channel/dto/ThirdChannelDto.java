package com.idealighter.game.app.channel.dto;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class ThirdChannelDto {
  /**
   * 渠道id.
   */
  @NotEmpty
  @Length(min = 2, max = 10)
  private String channelId;

  /**
   * 渠道名称.
   */
  @NotEmpty
  @Length(min = 2, max = 20)
  private String channelName;

  /**
   * 渠道备注.
   */
  @NotEmpty
  @Length(min = 1, max = 45)
  private String channelRemark;

  /**
   * 渠道des key.
   */
  @NotEmpty
  @Length(min = 24, max = 24)
  private String channelDesKey;

  /**
   * 渠道md5 key.
   */
  @NotEmpty
  @Length(min = 6, max = 8)
  private String channelMd5Key;
}
