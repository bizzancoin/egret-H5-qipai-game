package com.idealighter.game.robot.core;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.login.message.ReqHeartbeatMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.core.code.SessionType;
import com.idealighter.game.robot.handler.HandlerFactory;
import com.idealighter.game.robot.scheduler.PlayerScheduler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 机器人消息处理器.
 *
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(ClientHandler.class);

  // 机器人玩家定时调度器(单线程)
  // private final ScheduledExecutorService scheduler =
  // Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "robotPlayer-scheduler"));

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    Channel channel = ctx.channel();
    LOG.info("建立连接{}", channel);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    Channel channel = ctx.channel();
    LOG.info("断开连接[{}], playerId:[{}]", ctx.channel(),
        channel.attr(SessionType.PLAYER_ID_KEY).get());
    Player player = channel.attr(Player.PLAYER_KEY).get();

    // 双向解绑
    if (player != null) {
      // 移除玩家
      ApplicationContext.getBean(PlayerScheduler.class).removePlayer(player);
      channel.attr(SessionType.PLAYER_ID_KEY).set(null);
      channel.attr(Player.PLAYER_KEY).set(null);
    }
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    // 消息
    ResMessage resMsg = null;
    if (msg instanceof ResMessage) {
      resMsg = (ResMessage) msg;
    } else {
      LOG.error("消息[{}]不是响应消息", JSON.toJSONString(msg));
      return;
    }

    // 消息
    int msgId = resMsg.getId();
    // 机器人玩家
    Player player = ctx.channel().attr(Player.PLAYER_KEY).get();

    try {
      HandlerFactory.getHandler(msgId).action(player, resMsg);
    } catch (Exception e) {
      LOG.error("异常[" + ctx.channel() + "]机器人玩家[" + player.playerId + "][" + player.userName
          + "]消息[" + JSON.toJSONString(resMsg) + "]执行异常", e);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    LOG.error("通道发生异常", cause);
    ctx.close();
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
    /* 心跳处理 */
    if (evt instanceof IdleStateEvent) {
      ctx.channel().writeAndFlush(new ReqHeartbeatMsg());
    }
  }

}
