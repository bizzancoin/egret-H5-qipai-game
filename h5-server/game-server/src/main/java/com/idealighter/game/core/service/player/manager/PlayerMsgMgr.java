
package com.idealighter.game.core.service.player.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.player.message.ResPlayerAttrChangeMsg;

/**
 * 玩家消息管理 .
 * 
 * @date 2015年9月2日 下午2:19:59
 *
 */
@Singleton
public class PlayerMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;

  /**
   * 发送玩家属性改变消息 .
   * 
   * @param player 玩家.
   * @param type . 属性类型(1:元宝，2:金币,3:保险箱金币,4:积分,5:奖券,6:玩家等级,7:vip等级,8:vip时长,9:经验,10:礼物盒,11:下一集经验)
   * @param val .
   */
  public void noticeAttrChangeMsg(Player player, int type, long val) {
    ResPlayerAttrChangeMsg msg = new ResPlayerAttrChangeMsg();
    msg.setType(type);
    msg.setVal(val);
    msgWriter.writeMsg(player, msg);
  }

}
