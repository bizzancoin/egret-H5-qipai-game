package com.idealighter.game.robot.core.code;

import com.alibaba.fastjson.JSON;

import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

import com.google.protobuf.CodedInputStream;

import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.message.core.Message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 响应消息解码器.
 * 
 * 
 * @date 2014年8月16日 下午5:52:07
 * 
 */
public class ReqMsgDecoder extends ByteToMessageDecoder {

  private static final Logger log = LoggerFactory.getLogger("MessageLog");

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    /*
     * 响应协议：长度（Short） + sessionId(int) +消息类型id(int) ＋ 消息内容
     */
    // 校验消息长度(length)字节是否足够
    if (in.readableBytes() < Short.BYTES) {
      return;
    }

    // 标记读的位置
    in.markReaderIndex();

    // 长度(消息内容长度)
    short length = in.readShort();

    if (in.readableBytes() < length + Integer.BYTES + Integer.BYTES) {
      in.resetReaderIndex();
      return;
    }

    // sessionId编号
    int sessionId = in.readInt();

    // 消息Id
    int msgId = in.readInt();

    // byte[] data = new byte[length];
    // int readIndex = in.readerIndex();
    // in.getBytes(readIndex, data);
    //
    // StringBuilder sb = new StringBuilder();
    // for (byte b : data) {
    // sb.append(String.format("%02X ", b));
    // }
    //
    // if (msgId == 303) {
    // ResItemInfosMsg resItemInfosMsg = ProtobufProxy.create(ResItemInfosMsg.class).decode(data);
    //
    // CodedInputStream inputStream =
    // CodedInputStream.newInstance(new ByteBufInputStream(in, length));
    // ResItemInfosMsg resItemInfosMsg2 =
    // ProtobufProxy.create(ResItemInfosMsg.class).readFrom(inputStream);
    //
    // }


    // 消息
    Class<? extends Message> cls = MessageFactory.getResMsgClass(msgId);
    CodedInputStream inputStream = CodedInputStream.newInstance(new ByteBufInputStream(in, length));

    Message message = ProtobufProxy.create(cls).readFrom(inputStream);
    out.add(message);

    if (log.isDebugEnabled()) {
      log.debug("解码会话[{}] 用户: {}  长度: {} sessionId: {} msgId: {}, 消息[{}]成功", ctx.channel(),
          ctx.channel().attr(SessionType.PLAYER_ID_KEY).get(), length, sessionId, msgId,
          JSON.toJSONString(message));
    }
  }

}
