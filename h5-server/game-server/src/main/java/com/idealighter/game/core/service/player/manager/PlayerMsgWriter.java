
package com.idealighter.game.core.service.player.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.common.message.ResDefaultMsg;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.core.MessageHeader;
import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.core.code.SessionType;

import io.netty.channel.Channel;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

/**
 * 通道会话管理.
 * 
 * @date 2015年8月7日 上午11:38:21
 *
 */
@Slf4j
@Singleton
public class PlayerMsgWriter {


  private static final MessageHeader DEFAULT_HEADER = new MessageHeader(0);

  @Inject
  private PlayerMgr playerMgr;


  /**
   * 向多个玩家发送消息 .
   * 
   * @param playerIds .
   * @param msg .
   */
  public void write(Collection<Long> playerIds, ResMessage msg) {
    playerIds.forEach((playerId) -> writeMsg(playerId, msg));
  }

  /**
   * 向指定channel发送消息 .
   * 
   * @param channel .
   * @param msg .
   */
  public void writeMsg(Channel channel, ResMessage msg, MessageHeader header) {
    if (channel != null && channel.isActive()) {
      if (msg != null) {
        MessageWapper messageWapper = new MessageWapper();
        messageWapper.setHeader(header);
        messageWapper.setMsg(msg);
        channel.writeAndFlush(messageWapper);
      }
    } else {
      if (log.isDebugEnabled() && channel != null) {
        log.debug("向玩家[{}]发送数据, 因Channel未连接, 发送失败", channel.attr(SessionType.PLAYER_ID_KEY).get());
      }
    }
  }

  /**
   * 向玩家发送消息 .
   * 
   * @param playerId 玩家id.
   * @param msg .
   */
  public void writeMsg(long playerId, ResMessage msg) {
    writeMsg(playerMgr.getPlayer(playerId), msg);
  }

  /**
   * 向玩家发送消息 .
   * 
   * @param player 玩家.
   * @param msg .
   */
  public void writeMsg(Player player, ResMessage msg) {
    writeMsg(player, msg, DEFAULT_HEADER);
  }

  /**
   * 向玩家发送消息 .
   * 
   * @param channel 指定通道.
   * @param msg .
   */
  public void writeMsg(Channel channel, ResMessage msg) {
    writeMsg(channel, msg, DEFAULT_HEADER);
  }

  /**
   * 向玩家发送信息.
   * 
   * @param player 玩家实体.
   * @param msg 消息.
   * @param header 消息头部.
   */
  public void writeMsg(Player player, ResMessage msg, MessageHeader header) {
    if (player != null) {
      writeMsg(player.getChannel(), msg, header);
    }
  }

  /**
   * 写入非默认信息 .
   * 
   * @param playerId 玩家id.
   * @param msg 消息.
   */
  public void writeMsgNotDefault(long playerId, ResMessage msg) {
    if (msg != null && !(msg instanceof ResDefaultMsg)) {
      // 发送消息
      writeMsg(playerMgr.getPlayer(playerId), msg);
    }
  }

  /**
   * 写入非默认消息.
   * 
   * @param player 玩家.
   * @param msg 消息.
   */
  public void writeMsgNotDefault(Player player, ResMessage msg) {
    if (msg != null && !(msg instanceof ResDefaultMsg)) {
      // 发送消息
      writeMsg(player, msg);
    }
  }

  /**
   * 全服发送消息 .
   * 
   * @param msg .
   */
  public void writeWorld(ResMessage msg) {
    playerMgr.onLinePlayers().values().forEach((p) -> writeMsg(p, msg));
  }


}
