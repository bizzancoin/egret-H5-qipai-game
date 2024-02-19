
package com.idealighter.game.schedule.job;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.welfare.manager.WelfareDataMgr;
import com.idealighter.game.core.service.welfare.manager.WelfareMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 福利刷新job .
 * 
 * @date 2015年12月8日 下午9:31:04
 *
 */
public class WelfareJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    PlayerMgr playerMgr = ApplicationContext.getBean(PlayerMgr.class);
    WelfareMgr welfareMgr = ApplicationContext.getBean(WelfareMgr.class);
    ExecutorMgr executorMgr = ApplicationContext.getBean(ExecutorMgr.class);
    // 清空福利数据
    ApplicationContext.getBean(WelfareDataMgr.class).clearWelfareData();

    for (Player player : playerMgr.onLinePlayers().values()) {
      DisruptorExecutor playerExecutor = executorMgr.getPlayerExecutor(player.getId());
      playerExecutor.execute(() -> welfareMgr.refreshPlayerWelfareData(player, true));
    }
  }

}
