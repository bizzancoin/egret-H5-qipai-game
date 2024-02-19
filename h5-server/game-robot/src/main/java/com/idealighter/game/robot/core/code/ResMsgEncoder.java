package com.idealighter.game.robot.core.code;

import com.alibaba.fastjson.JSON;

import com.google.protobuf.CodedOutputStream;

import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.Attribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求消息编码器.
 * 
 */
public class ResMsgEncoder extends MessageToByteEncoder<ReqMessage> {

  private static final Logger log = LoggerFactory.getLogger("MessageLog");

  @Override
  protected void encode(ChannelHandlerContext ctx, ReqMessage reqMsg, ByteBuf out)
      throws Exception {
    /*
     * 请求协议：长度(shot) + 校验码(int) + 消息类型id(int) ＋ 消息内容(字节数组，不会压缩)
     */

    /*
     * 长度 ,校验码 ,消息类型id 占位
     */
    out.writeShort(0); // 长度
    out.writeInt(0); // 校验码
    out.writeInt(0); // 消息id

    // 消息id
    int msgId = reqMsg.getId();
    CodedOutputStream outputStream = CodedOutputStream.newInstance(new ByteBufOutputStream(out));
    MessageFactory.encodeReqMessage(outputStream, reqMsg);
    // 消息长度
    int length = outputStream.getTotalBytesWritten();
    outputStream.flush();

    Attribute<Integer> reqOrderAttr = ctx.channel().attr(SessionType.REQ_MSG_ORDER);
    // 请求消息的序号从1开始
    Integer reqOrder = reqOrderAttr.get();
    if (reqOrder == null) {
      reqOrder = 1;
    }

    // 生成校验码
    int key = reqOrder ^ length;
    key = ((~key & (1 << 9)) | (key & ~(1 << 9)));

    // 长度
    out.setShort(0, length);
    // 校验码
    out.setInt(2, key);
    // 消息id
    out.setInt(6, msgId);

    // 保存发送消息数据，生成校验码使用
    reqOrderAttr.set(++reqOrder);
    ctx.flush();

    if (log.isDebugEnabled() && msgId != ModuleMsgIdConstant.HEART_BEAT) {
      log.debug("发送会话[{}] 用户: {} 长度: {} sessionId: {} msgId: {} 消息[{}]成功", ctx.channel(),
          ctx.channel().attr(SessionType.PLAYER_ID_KEY).get(), length, reqOrder, msgId,
          JSON.toJSONString(reqMsg));
    }
  }
}
