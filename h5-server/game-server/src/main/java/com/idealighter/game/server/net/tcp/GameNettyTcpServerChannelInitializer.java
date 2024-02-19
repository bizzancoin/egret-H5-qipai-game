package com.idealighter.game.server.net.tcp;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.server.core.code.ReqMsgDecoder;
import com.idealighter.game.server.core.code.ResMsgEncoder;
import com.idealighter.game.server.core.excepiton.ExceptionHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

@Singleton
public class GameNettyTcpServerChannelInitializer extends ChannelInitializer<SocketChannel> {
  private final TcpServerHandler handler;

  @Inject
  public GameNettyTcpServerChannelInitializer(TcpServerHandler handler) {
    this.handler = handler;
  }

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ch.pipeline().addLast(new ReqMsgDecoder());
    ch.pipeline().addLast(new ResMsgEncoder());
    ch.pipeline().addLast(new IdleStateHandler(60, 0, 0));
    ch.pipeline().addLast(this.handler);
    ch.pipeline().addLast(new ExceptionHandler());
  }

  public void shutdown() {
    handler.shutdown();
  }

  public void closing() {
    handler.closing();
  }
}
