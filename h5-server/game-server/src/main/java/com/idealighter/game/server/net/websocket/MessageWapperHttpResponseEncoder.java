package com.idealighter.game.server.net.websocket;

import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.server.core.code.ResMsgEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

public class MessageWapperHttpResponseEncoder extends MessageToMessageEncoder<Object> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
    if (msg != null && msg instanceof MessageWapper) {
      ByteBuf byteBuf = ResMsgEncoder.encodeMessageWapper(ctx, (MessageWapper) msg, null);
      if (byteBuf != null && byteBuf.isReadable()) {
        BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(byteBuf);
        out.add(binaryWebSocketFrame);
      } else {
        // 释放空间
        byteBuf.release();
      }
    } else {
      ReferenceCountUtil.retain(msg);
      out.add(msg);
    }
  }
}
