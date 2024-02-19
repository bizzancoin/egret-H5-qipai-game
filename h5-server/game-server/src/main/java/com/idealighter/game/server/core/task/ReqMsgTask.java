package com.idealighter.game.server.core.task;

import com.idealighter.game.common.message.ResDefaultMsg;
import com.idealighter.game.common.message.ResErrorMsg;
import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.exception.HuohuaRunTimeException;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.MessageHeader;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.server.handler.HandlerFactory;
import com.idealighter.utils.json.JsonUtil;

import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求消息task .
 */
public class ReqMsgTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(ReqMsgTask.class);
  private static final Logger MSGHANDLER_LOG = LoggerFactory.getLogger("MsgHandlerLog");


  private PlayerMsgWriter msgWriter;

  private final Player player;
  private final ReqMessage msg;
  private MessageHeader header;

  /**
   * 构造函数 .
   * 
   * @param player 玩家. 玩家.
   * @param msg . 消息.
   */
  public ReqMsgTask(Player player, ReqMessage msg, MessageHeader header,
      PlayerMsgWriter msgWriter) {
    super();
    this.player = player;
    this.msg = msg;
    this.header = header;
    this.msgWriter = msgWriter;
  }

  @Override
  public final void run() {
    try {
      final long now = System.currentTimeMillis();
      Channel channel = player.getChannel();
      ResMessage message = HandlerFactory.getHandler(msg.getId()).action(player, msg);

      if (message != null) {
        if (message instanceof ResDefaultMsg) {
          message = new ResDefaultMsg();
          ((ResDefaultMsg) message).setId(msg.getId());
        }

        HuohuaAssert.isTrue(message.getId() == msg.getId());
        // 退出操作后会讲玩家的channel解绑，所以，此时使用channel给玩家发送信息
        if (msg.getId() == ModuleMsgIdConstant.Account.LOGOUT) {
          msgWriter.writeMsg(channel, message, header);
        } else {
          msgWriter.writeMsg(player, message, header);
        }
      }

      long duration = System.currentTimeMillis() - now;
      // 超过10毫秒的操作
      if (duration > 10) {
        MSGHANDLER_LOG.info("消息[{}]处理时间[{}]", msg.getClass().getName(), duration);
        LOG.info("消息[{}]处理时间[{}]", msg.getClass().getName(), duration);
      }
    } catch (HuohuaRunTimeException runTimeException) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("业务异常[" + JsonUtil.toJson(msg) + "]执行异常", runTimeException);
      }

      ResErrorMsg msg = new ResErrorMsg();
      msg.setErrorCode(runTimeException.getErrorCode().getCode());
      msg.setArgs(runTimeException.getArgs());
      msg.setSystem(Operator.SYSTEM.getCode());

      msgWriter.writeMsg(player, msg, header);

    } catch (Exception exception) {
      LOG.error("未处理清楚的异常[" + JsonUtil.toJson(msg) + "]执行异常", exception);
    }
  }
}
