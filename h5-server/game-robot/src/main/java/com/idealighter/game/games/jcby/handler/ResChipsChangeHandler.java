
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.message.ResChipsChangeMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.RES_CHIPS_CHANGE)
public class ResChipsChangeHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResChipsChangeHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResChipsChangeMsg msg = (ResChipsChangeMsg) message;
    if (msg.getPlayerId() != player.playerId) {
      return;
    }
    
    LOG.info("[金蝉捕鱼]玩家[{}] 补充筹码 [{}] 成功", player.userName, msg.getChips());


    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    RobotConfigDomain robotCfg = jcbyPlayer.configDomain;
    JcbyRoomDomain roomDom = ((JcbyPlayer) player).roomDomain;
    long chips = msg.getChips();

    jcbyPlayer.chips = chips;
    // 捕鱼机器人身上的鱼币转化为金币
    long preGold = jcbyPlayer.playerInfo.getSafeGold();
    long gold =
        chips * roomDom.getProportionGold() * Player.PRECISION / roomDom.getProportionChips();

    // 超过最大携带金币需要退出房间
    if (preGold + gold > robotCfg.getMaxGold()) {
      LOG.info("[金蝉捕鱼]玩家[{}][{}]原始金币[{}],最新金币[{}], 筹码[{}],金币:筹码转换比为[{}/{}]]", player.userName,
          player.playerId, preGold, gold, chips, roomDom.getProportionGold(),
          roomDom.getProportionChips());
      LOG.info("[金蝉捕鱼]玩家[{}][{}]身上的金币[{}]超过最大携带金币[{}]", player.userName, player.playerId, gold,
          robotCfg.getMaxGold());
      player.delayLogout();
      return;
    }

    /*
     * if (gold < roomDom.getLower()) { LOG.info("[金蝉捕鱼]机器人[{}][{}]金币小于房间下限退出游戏", player.userName,
     * player.playerId); player.delayLogout(); return; }
     */

    long upper = roomDom.getUpper();
    if (upper > 0 && preGold + gold > upper) {
      LOG.info("[金蝉捕鱼]机器人[{}][{}]金币高于房间下限退出游戏", player.userName, player.playerId);
      player.delayLogout();
      return;
    }
  }
}
