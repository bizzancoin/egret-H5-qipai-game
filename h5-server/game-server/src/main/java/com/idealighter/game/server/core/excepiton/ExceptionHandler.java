package com.idealighter.game.server.core.excepiton;

import com.idealighter.game.core.service.player.struct.ExitType;
import com.idealighter.game.server.core.code.SessionType;
import com.idealighter.utils.json.JsonUtil;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandler extends ChannelDuplexHandler {
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    log.error("exception [" + ctx.channel() + "]发生异常", cause);
    ctx.channel().attr(SessionType.EXIT_TYPE).set(ExitType.EXCEPTION);
    ctx.close();
  }

  @Override
  public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
      SocketAddress localAddress, ChannelPromise promise) {
    ctx.connect(remoteAddress, localAddress, promise.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        if (!future.isSuccess()) {
          log.error("连接失败 [" + ctx.channel() + "]");
        }
      }
    }));
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
    ctx.write(msg, promise.addListener(new ChannelFutureListener() {
      @Override
      public void operationComplete(ChannelFuture future) {
        if (!future.isSuccess()) {
          log.error("写入失败 [" + ctx.channel() + "]发生异常: " + future.cause().getMessage());
          log.error(JsonUtil.toJson(msg));
          ctx.channel().attr(SessionType.EXIT_TYPE).set(ExitType.WRITE_EXCEPTION);
          ctx.close();
        }
      }
    }));
  }
}
