package com.idealighter.game.server.handler;

import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.message.core.ResMessage;

public interface ReqMessageHandler {

  ResMessage action(Player player, ReqMessage msg);
}
