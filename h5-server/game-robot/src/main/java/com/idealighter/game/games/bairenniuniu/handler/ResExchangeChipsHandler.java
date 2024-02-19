package com.idealighter.game.games.bairenniuniu.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

@ResMsgHandler(ModuleMsgIdConstant.BaiRenNiuNiu.EXCHAGE_CHIPS)
public class ResExchangeChipsHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage msg) {
    player.playerInfo.setSafeGold(0L);
  }

}
