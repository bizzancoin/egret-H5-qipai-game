package com.idealighter.game.schedule.listener;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.schedule.manager.JobRunTimeMgr;
import com.idealighter.game.schedule.struct.JobRunTime;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.listeners.JobListenerSupport;

/**
 * 作业运行时间监听器，主要用于记录job的上次和下次运行时间 .
 * 
 * @date 2014年11月19日 下午2:36:22
 *
 */
@Singleton
public class JobRunTimeListener extends JobListenerSupport {

  @Inject
  private JobRunTimeMgr jobRunTimeMgr;

  @Override
  public String getName() {
    return "JobRunTimeListener";
  }

  @Override
  public void jobToBeExecuted(JobExecutionContext context) {
    super.jobToBeExecuted(context);
    // job的运行时间
    JobRunTime jobRunTime = jobRunTimeMgr.getJobRunTime(context.getJobDetail().getJobClass());
    Date fireTime = context.getFireTime();
    Date nextFireTime = context.getNextFireTime();

    jobRunTime.setPreRunTime(fireTime == null ? 0 : fireTime.getTime());
    jobRunTime.setNextRunTime(nextFireTime == null ? 0 : nextFireTime.getTime());
  }
}
