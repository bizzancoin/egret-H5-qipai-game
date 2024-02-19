
package com.idealighter.game.player.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.player.message.ResPlayerAttrChangeMsg;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

@ResMsgHandler(ModuleMsgIdConstant.Player.NOTICE_ATTR_CHANGE)

public class ResPlayerAttrChangeHandler implements ResMessageHandler {

  @Override
  public void action(Player player, ResMessage message) {
    ResPlayerAttrChangeMsg msg = (ResPlayerAttrChangeMsg) message;
    long val = msg.getVal();
    switch (msg.getType()) { // 1:元宝，2:金币,3:保险箱金币,4:积分,5:奖券,6:玩家等级,7:vip等级,8:vip时长
      case 1:
        player.playerInfo.setIngot((int) val);
        break;
      case 2:
        player.playerInfo.setGold(val);
        break;
      case 3:
        player.playerInfo.setSafeGold(val);
        break;
      case 4:
        player.playerInfo.setCedit(val);
        break;
      case 5:
        player.playerInfo.setLottery(val);
        break;
      case 6:
        player.playerInfo.setLevel((int) val);
        break;
      case 7:
        player.playerInfo.setVipLevel((int) val);
        break;
      case 8:
        player.playerInfo.setVipDuration((int) val);
        break;

      default:
        break;
    }
  }
}
