package com.idealighter.game.service.channel.bo;

import lombok.Data;

@Data
public class ThirdChannelBo {

  /**
   * 渠道id.
   */
  private String channelId;

  /**
   * 渠道名称.
   */
  private String channelName;

  /**
   * 渠道备注.
   */
  private String channelRemark;

  /**
   * 渠道des key.
   */
  private String channelDesKey;

  /**
   * 渠道md5 key.
   */
  private String channelMd5Key;
}
