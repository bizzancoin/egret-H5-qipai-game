package com.idealighter.game.server.net.tcp;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.server.core.handler.InboundBusinessHandler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Sharable
public class TcpServerHandler extends ChannelInboundHandlerAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(TcpServerHandler.class);
  private static final String NET_TYPE = "TCP";

  private final InboundBusinessHandler inboundBusinessHandler;

  // 是否停服，停服后不再接受消息处理
  private boolean shutdown = false;

  @Inject
  public TcpServerHandler(InboundBusinessHandler messageWapperHandler) {
    this.inboundBusinessHandler = messageWapperHandler;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msgWapper) throws Exception {
    // super.channelRead(ctx, msgWapper);
    if (shutdown) {
      return;
    }

    if (msgWapper instanceof MessageWapper) {
      inboundBusinessHandler.handler(ctx, (MessageWapper) msgWapper);
    } else {
      LOG.error("消息[{}]不是请求消息", JSON.toJSONString(msgWapper));
      return;
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    inboundBusinessHandler.active(ctx, NET_TYPE);
  }

  public void shutdown() {
    shutdown = true;
  }

  public void closing() {
    inboundBusinessHandler.closeing();
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    inboundBusinessHandler.inactive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    inboundBusinessHandler.exception(ctx, cause);
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
    inboundBusinessHandler.eventTriggered(ctx, evt);
  }
}
