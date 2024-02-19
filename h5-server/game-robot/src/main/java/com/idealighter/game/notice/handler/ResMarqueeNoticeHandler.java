
package com.idealighter.game.notice.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.handler.ResMessageHandler;


@ResMsgHandler(ModuleMsgIdConstant.Notice.NOTICE_MARQUEE_NOTICE)

public class ResMarqueeNoticeHandler implements ResMessageHandler {

  // private static final Logger LOG = LoggerFactory.getLogger(ResMarqueeNoticeHandler.class);

  @Override
  public void action(Player player, ResMessage message) {
    // ResMarqueeNoticeMsg msg = (ResMarqueeNoticeMsg) message;

  }
}
