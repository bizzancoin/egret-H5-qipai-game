
package com.idealighter.game.login.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Account.ROBOT_LOGIN)
public class ResRobotLoginHandler implements ResMessageHandler {
  protected static final Logger LOG = LoggerFactory.getLogger(ResRobotLoginHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // 登录成功后在游戏广场里
    player.position = PlayerPosition.PLAZA;

    LOG.info("机器人[{}][{}]登录成功", player.userName, player.playerId);
  }
}
