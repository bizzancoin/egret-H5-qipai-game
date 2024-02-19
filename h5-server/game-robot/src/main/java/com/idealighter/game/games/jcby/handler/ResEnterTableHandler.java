
package com.idealighter.game.games.jcby.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.games.jcby.message.ReqScenceMsg;
import com.idealighter.game.games.jcby.message.ResEnterTableMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;
import com.idealighter.utils.code.RandCodeUtil;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ResMsgHandler(ModuleMsgIdConstant.Jcby.FAST_ENTER_TABLE)
public class ResEnterTableHandler implements ResMessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ResEnterTableHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    ResEnterTableMsg msg = (ResEnterTableMsg) message;
    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入金蝉捕鱼桌子[{}]成功", player.userName, player.playerId,
        player.table(player.playerId).getId());
    player.position = PlayerPosition.TABLE;
    JcbyPlayer jcbyPlayer = (JcbyPlayer) player;
    jcbyPlayer.chips = msg.getTotalChips();

    // 延时5秒请求场景数据
    jcbyPlayer.schedule(() -> {
      jcbyPlayer.sendMsg(new ReqScenceMsg());
    }, RandCodeUtil.random(5, 8), TimeUnit.SECONDS);
  }
}
