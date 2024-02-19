
package com.idealighter.game.core.service.welfare.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.welfare.message.ResRefreshWelfareMsg;
import com.idealighter.game.welfare.message.ResWelfareInfoMsg;

/**
 * 福利消息管理 .
 * 
 * @date 2015年12月9日 下午1:39:38
 *
 */
@Singleton
public class WelfareMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;

  /**
   * 发送福利信息消息 .
   * 
   * @param player 玩家.
   */
  public void noticeWelfareInfoMsg(Player player) {
    ResWelfareInfoMsg msg = new ResWelfareInfoMsg();
    msg.setWelfares(player.welfareData().welfares());
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送刷新福利消息 .
   * 
   * @param player 玩家.
   */
  public void sendRefreshWelfareMsg(Player player) {
    ResRefreshWelfareMsg msg = new ResRefreshWelfareMsg();
    msg.setWelfares(player.welfareData().welfares());
    msgWriter.writeMsg(player, msg);
  }
}
