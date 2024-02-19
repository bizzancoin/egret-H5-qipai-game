
package com.idealighter.game.robot.handler;

import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;

/**
 * 返回消息处理.
 *
 */
public interface ResMessageHandler {

  void action(Player player, ResMessage msg);
}
