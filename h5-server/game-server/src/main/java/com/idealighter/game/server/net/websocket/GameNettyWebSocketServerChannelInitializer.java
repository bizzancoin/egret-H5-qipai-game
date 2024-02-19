package com.idealighter.game.server.net.websocket;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.server.core.excepiton.ExceptionHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.timeout.IdleStateHandler;

@Singleton
public class GameNettyWebSocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {
  private final WebSocketServerHandler handler;

  @Inject
  public GameNettyWebSocketServerChannelInitializer(WebSocketServerHandler handler) {
    this.handler = handler;
  }

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ch.pipeline().addLast("encoder", new HttpResponseEncoder());
    ch.pipeline().addLast("decoder", new HttpRequestDecoder());
    // 将MessageWapper转HttpResponse,似的HttpResponseEncoder能够解析
    ch.pipeline().addLast(new MessageWapperHttpResponseEncoder());
    ch.pipeline().addLast(new HttpObjectAggregator(65536));
    ch.pipeline().addLast(new IdleStateHandler(60, 0, 0));
    ch.pipeline().addLast(handler); // 业务处理
    ch.pipeline().addLast(new ExceptionHandler());
  }

  public void shutdown() {
    handler.shutdown();
  }
  
  public void closing() {
    handler.closing();
  }
}
