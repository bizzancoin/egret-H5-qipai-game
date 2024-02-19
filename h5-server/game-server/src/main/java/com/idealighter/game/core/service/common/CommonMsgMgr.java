package com.idealighter.game.core.service.common;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.common.message.ResErrorMsg;
import com.idealighter.game.common.message.ResShowMsg;
import com.idealighter.game.common.message.ResTipMsg;
import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.gamehall.message.NoticeRoomCloseKickoutMsg;

import java.util.Arrays;

/**
 * 通用消息管理 .
 * 
 * @date 2015年10月21日 下午5:27:06
 *
 */
@Singleton
public class CommonMsgMgr {
  @Inject
  private PlayerMsgWriter msgWriter;

  /**
   * 发送通用错误提示框消息 .
   * 
   * @param player 玩家.
   * @param errorCode .
   * @param system . 系统(暂时未用) .
   * @param args .
   */
  public void sendErrorDlgMsg(Player player, ErrorCode errorCode, Operator system, String... args) {
    ResErrorMsg msg = new ResErrorMsg();
    msg.setErrorCode(errorCode.getCode());
    msg.setArgs(Arrays.asList(args));
    msg.setSystem(system.getCode());

    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送通用警告提示框消息 .
   * 
   * @param player 玩家.
   * @param errorCode .
   * @param system . 系统(暂时未用) .
   * @param args .
   */
  // public void sendWarnDlgMsg(Player player, ErrorCode errorCode, Operator system, String... args)
  // {
  // ResWarnMsg msg = new ResWarnMsg();
  // msg.setErrorCode(errorCode.getCode());
  // msg.setArgs(Arrays.asList(args));
  // msg.setSystem(system.getCode());
  //
  // msgWriter.writeMsg(player, msg);
  // }

  /**
   * 发送提示框消息 .
   * 
   * @param player 玩家.
   * @param errorCode .
   * @param system .
   * @param args .
   */
  public void sendTipDlgMsg(Player player, ErrorCode errorCode, Operator system, String... args) {
    ResTipMsg msg = new ResTipMsg();
    msg.setErrorCode(errorCode.getCode());
    msg.setArgs(Arrays.asList(args));
    msg.setSystem(system.getCode());

    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送右下角展示提示框 .
   * 
   * @param player 玩家.
   * @param msgId .
   * @param system .
   * @param args .
   */
  public void sendShowDlgMsg(Player player, int msgId, Operator system, String... args) {
    ResShowMsg msg = new ResShowMsg();
    msg.setMsgId(msgId);
    msg.setArgs(Arrays.asList(args));
    msg.setSystem(system.getCode());

    msgWriter.writeMsg(player, msg);
  }


  /**
   * 房间关闭，退出通知 .
   *
   * @author abin
   * @date 2018年4月28日 下午5:07:39
   * @param player 玩家.
   */
  public void noticeRoomCloseKickout(Player player) {
    NoticeRoomCloseKickoutMsg msg = new NoticeRoomCloseKickoutMsg();
    msgWriter.writeMsg(player, msg);
  }

}
