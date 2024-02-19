package com.idealighter.game.core.service.event.struct;

import com.idealighter.game.core.service.player.struct.Player;

/**
 * 会话关闭事件.
 * 
 * @date 2015年9月2日 下午2:38:30
 *
 */
public class ChannelCloseEvent {

  public final Player player;

  public final Integer channelHashCode;

  /**
   * 构造函数.
   * 
   * @param player 玩家. 玩家.
   */
  public ChannelCloseEvent(Player player, int channelHashCode) {
    this.player = player;
    this.channelHashCode = channelHashCode;
  }

}
