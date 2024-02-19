
package com.idealighter.game.core.service.notice.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.notice.message.ResMarqueeNoticeMsg;

/**
 * 公告消息管理 .
 * 
 * @date 2015年11月13日 下午3:01:36
 *
 */
@Singleton
public class NoticeMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;


  /**
   * 发送跑马灯公告消息 .
   * 
   * @param content 内容.
   * @param type 类型(0:游戏公告,1:官方公告).
   * @param interval 间隔时间 秒.
   * @param times 显示次数.
   * @param color 颜色.
   */
  public void sendMarqueeNoticeMsg(String content, int type, int interval, int times,
      String color) {
    ResMarqueeNoticeMsg msg = new ResMarqueeNoticeMsg();
    msg.setContent(content);
    msg.setType(type);
    msg.setInterval(interval);
    msg.setTimes(times);
    msg.setColor(color);
    msgWriter.writeWorld(msg);
  }

  /**
   * 发送房间跑马灯公告消息.
   * 
   * @param content 内容.
   * @param type 类型(0:游戏公告,1:官方公告).
   * @param interval 间隔时间 秒.
   * @param times 显示次数.
   * @param color 颜色.
   * @param room .
   */
  public void sendMarqueeNoticeMsg(String content, int type, int interval, int times, String color,
      AbstractRoom room) {
    ResMarqueeNoticeMsg msg = new ResMarqueeNoticeMsg();
    msg.setContent(content);
    msg.setType(type);
    msg.setInterval(interval);
    msg.setTimes(times);
    msg.setColor(color);
    msgWriter.write(room.getPlayers(), msg);
  }

}
