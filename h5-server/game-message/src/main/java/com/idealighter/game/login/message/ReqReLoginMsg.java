package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;
import lombok.Data;

/**
 * 断线重新登录.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.Account.RE_LOGIN)
@Data
public class ReqReLoginMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.RE_LOGIN;
  // 类型(1:游客登录, 2:手机登录, 3:机器人登录, 4:微信)
  @Protobuf(order = 2)
  private int type;
  // 密码(游客：随机码; 玩家:随机码; 机器人: 密码)
  @Protobuf(order = 3)
  private String code;
  // 是否手动登录(1:是(token免密登录), 0:否(断线重连))
  @Protobuf(order = 4)
  private int byHand;
  @Protobuf(order = 5)
  private int platform = 0; // 平台(0:ios,1:android,2:pc)
}
