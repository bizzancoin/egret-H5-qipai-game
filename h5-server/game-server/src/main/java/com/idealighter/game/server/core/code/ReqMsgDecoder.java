package com.idealighter.game.server.core.code;

import com.alibaba.fastjson.JSON;

import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

import com.google.protobuf.CodedInputStream;

import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.message.core.MessageHeader;
import com.idealighter.game.message.core.MessageWapper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.Attribute;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 请求消息解码器 .
 * 
 * @date 2014年8月16日 下午5:52:07
 * 
 */
public class ReqMsgDecoder extends ByteToMessageDecoder {

  private static final Logger MESSAGE_LOG = LoggerFactory.getLogger("MessageLog");
  /** 请求最大消息字节数. */
  private static final int MAX_MSG_BYTES = 10240;
  /** 请求缓冲最大字节数. */
  private static final int MAX_BUFF_BYTES = 10485760;

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    Object msg = decodeMsg(ctx, in);
    if (msg != null) {
      out.add(msg);
    }
  }

  /**
   * 消息解码 .
   *
   * @param ctx .
   * @param buf .
   * @return .
   */
  public static MessageWapper decodeMsg(ChannelHandlerContext ctx, ByteBuf buf) {
    MessageWapper result = null;
    /*
     * 请求协议：长度 . + 校验码 + 消息类型id ＋ 消息内容(字节数组,不会压缩)
     */

    // 校验消息消息头长度字节是否足够
    if (buf.readableBytes() < Short.BYTES) {
      return result;
    }

    // 标记读的位置
    buf.markReaderIndex();

    // 长度
    short length = buf.readShort();

    if (buf.readableBytes() < length + Integer.BYTES + Integer.BYTES) {
      buf.resetReaderIndex();
      return result;
    }

    // 校验码
    int key = buf.readInt();
    // 消息类型id
    int msgId = buf.readInt();

    Channel channel = ctx.channel();

    if (length > MAX_MSG_BYTES) {
      MESSAGE_LOG.error("会话[{}]发送的消息[{}]超过最大字节数[{}]", channel, msgId, MAX_MSG_BYTES);
      channel.close();
      return result;
    }

    if (buf.readableBytes() > MAX_BUFF_BYTES) {
      MESSAGE_LOG.error("会话[{}]剩余缓冲超过最大字节数[{}]", channel, msgId, MAX_BUFF_BYTES);
      channel.close();
      return result;
    }

    /*
     * 校验码验证,解码校验码和发送消息的序号比对 .
     */
    int decodeOrder = key ^ 0x200 ^ length;

    Attribute<Integer> reqOrderAttr = channel.attr(SessionType.REQ_MSG_ORDER);
    // 请求消息的序号从1开始
    Integer reqOrder = reqOrderAttr.get();
    if (reqOrder == null) {
      reqOrder = 1;
    }


    // 消息

    if (decodeOrder == reqOrder) {

      reqOrderAttr.set(++reqOrder);
      // 读取消息

      // byte[] data = new byte[length];
      // in.readBytes(data);

      Class<? extends Message> cls;
      try {
        cls = MessageFactory.getReqMsgClass(msgId);
        CodedInputStream inputStream =
            CodedInputStream.newInstance(new ByteBufInputStream(buf, length));
        Message message = ProtobufProxy.create(cls).readFrom(inputStream);
        MessageHeader msgHeader = new MessageHeader();
        msgHeader.setSessionId(decodeOrder);
        MessageWapper wapper = new MessageWapper(msgHeader, message);

        result = wapper;

        // 跳過心跳信息，以免log中包含太多心跳信息，不利于查看log
        if (MESSAGE_LOG.isDebugEnabled() && msgId != ModuleMsgIdConstant.HEART_BEAT) {
          MESSAGE_LOG.debug("接到并解析会话[{}] 用户: {} 长度: {} sessionId: {} msgId: {} 消息[{}]成功", channel,
              channel.attr(SessionType.PLAYER_ID_KEY).get(), length, decodeOrder, msgId,
              JSON.toJSONString(message));
        }

      } catch (InstantiationException exception) {
        exception.printStackTrace();
        MESSAGE_LOG.error("decoder [" + ctx.channel() + "]发生异常", exception);
      } catch (IllegalAccessException exception) {
        exception.printStackTrace();
        MESSAGE_LOG.error("decoder [" + ctx.channel() + "]发生异常", exception);
      } catch (IOException exception) {
        exception.printStackTrace();
        MESSAGE_LOG.error("decoder [" + ctx.channel() + "]发生异常", exception);
      }
    } else {
      MESSAGE_LOG.error("[{}]发送消息序列出错, 发包序列[{}],当前序列:[{}]",
          new Object[] { channel, decodeOrder, reqOrder, msgId });

      channel.close();
    }
    // 返回处理的消息
    return result;
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.fireExceptionCaught(cause);
  }
}
