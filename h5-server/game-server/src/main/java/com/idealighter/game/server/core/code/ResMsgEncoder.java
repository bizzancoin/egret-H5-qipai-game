package com.idealighter.game.server.core.code;

import com.alibaba.fastjson.JSON;

import com.google.protobuf.CodedOutputStream;

import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.message.core.ResMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 响应消息编码 .
 * 
 * @date 2015年7月23日 下午2:15:58
 *
 */
public class ResMsgEncoder extends MessageToByteEncoder<MessageWapper> {

  private static final Logger heartbeat = LoggerFactory.getLogger("heartbeat");
  private static final Logger MESSAGE_LOG = LoggerFactory.getLogger("MessageLog");

  /** 消息体的大小超过512则压缩. */
  // private static final int COMPRESS_BYTES = 512;

  @Override
  protected void encode(ChannelHandlerContext ctx, MessageWapper messageWapper, ByteBuf out) {
    encodeMessageWapper(ctx, messageWapper, out);
  }

  /**
   * 消息编码 .
   *
   * @param ctx .
   * @param messageWapper .
   * @param byteBuf .
   * @return .
   */
  public static ByteBuf encodeMessageWapper(ChannelHandlerContext ctx, MessageWapper messageWapper,
      ByteBuf byteBuf)  {
    if (byteBuf == null) {
      // 分配容量为256byte的容器(如果不够，可自动扩容)
      // byteBuf = Unpooled.buffer(256);
      byteBuf = ctx.alloc().ioBuffer();
    }
    /*
     * 响应协议：长度（消息内容byte） . + sessionId(int) +消息类型id ＋ 消息内容(字节数组，可能会压缩)
     */
    // 占位
    // byte[] data = resMsg.encode();

    ResMessage resMsg = (ResMessage) messageWapper.getMsg();

    byteBuf.markWriterIndex();
    // 消息长度
    int length = 0;
    // 长度
    byteBuf.writeShort(length);



    // 返回sessionId
    byteBuf.writeInt(messageWapper.getHeader().getSessionId());
    // 消息id
    int msgId = resMsg.getId();
    byteBuf.writeInt(msgId);

    try {
      CodedOutputStream outputStream =
          CodedOutputStream.newInstance(new ByteBufOutputStream(byteBuf));
      MessageFactory.encodeResMessage(outputStream, resMsg);

      length = outputStream.getTotalBytesWritten();

      byteBuf.setShort(0, length);

      outputStream.flush();
      ctx.flush();
    } catch (IOException exception) {
      exception.printStackTrace();
      MESSAGE_LOG.error("encoder [" + ctx.channel() + "]发生异常", exception);
      byteBuf.resetWriterIndex();
    }

    if (msgId == ModuleMsgIdConstant.HEART_BEAT) {
      Long playerId = ctx.channel().attr(SessionType.PLAYER_ID_KEY).get();
      heartbeat.debug("心跳通知[{}] 用户[{}], sessionId: [{}]", ctx.channel(), playerId,
          messageWapper.getHeader().getSessionId());
    } else {
      MESSAGE_LOG.debug("加密会话[{}] 用户: {} 长度: {} sessionId: {} msgId: {} 消息[{}]成功", ctx.channel(),
          ctx.channel().attr(SessionType.PLAYER_ID_KEY).get(), length,
          messageWapper.getHeader().getSessionId(), msgId, JSON.toJSONString(resMsg));
    }
    return byteBuf;
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.fireExceptionCaught(cause);
  }
}
