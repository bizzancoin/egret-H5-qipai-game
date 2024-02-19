
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.dto.BatteryInfo;
import com.idealighter.game.games.jcby.message.ReqExchangeChipsMsg;
import com.idealighter.game.games.jcby.message.ResScenceMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.SCREEN)
public class ResScenceHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResScenceHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResScenceMsg msg = (ResScenceMsg) message;
    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入桌子[{}]成功", player.userName, player.playerId,
        player.table(player.playerId).getId());

    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    for (BatteryInfo battery : msg.getBatterys()) {
      if (battery.getPlayerId() == jcbyPlayer.playerId) {
        jcbyPlayer.battery = battery;
        break;
      }
    }
    // 随机延时3到5秒兑换筹码
    jcbyPlayer.schedule(() -> {
      exchangeChips(player);
      jcbyPlayer.scheduleFire();
    }, RandCodeUtil.random(3, 5), TimeUnit.SECONDS);
  }

  /**
   * @Description:进入桌子后兑换全部金币为筹码,然后马上准备
   * @param player
   */
  private void exchangeChips(Player player) {

    ReqExchangeChipsMsg msg = new ReqExchangeChipsMsg();
    msg.setGold(player.playerInfo.getSafeGold());
    player.sendMsg(msg);

    LOG.info("[金蝉捕鱼]玩家[{}] 金币 [{}] 兑换筹码", player.userName, player.playerInfo.getSafeGold());

  }
}
