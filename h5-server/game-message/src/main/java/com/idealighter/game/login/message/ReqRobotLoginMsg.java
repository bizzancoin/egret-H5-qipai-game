package com.idealighter.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import com.idealighter.game.core.annotation.ReqMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ReqMessage;

import lombok.Data;

/**
 * 机器人请求登录.
 * 
 * @author messageGenerator
 *
 */
@ReqMsg(ModuleMsgIdConstant.Account.ROBOT_LOGIN)
@Data
public class ReqRobotLoginMsg implements ReqMessage {
  @Protobuf(order = 1)
  private int id = ModuleMsgIdConstant.Account.ROBOT_LOGIN;
  // 玩家用户名
  @Protobuf(order = 2)
  private String name;
  // 玩家密码
  @Protobuf(order = 3)
  private String password;
  // 初始金币
  @Protobuf(order = 4)
  private long gold;

}
