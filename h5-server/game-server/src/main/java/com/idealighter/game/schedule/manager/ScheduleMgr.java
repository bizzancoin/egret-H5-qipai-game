
package com.idealighter.game.schedule.manager;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.concurrent.FixedTimeScheduler;
import com.idealighter.game.dictionary.dic.SchedulerJobDic;
import com.idealighter.game.dictionary.domain.SchedulerJobDomain;
import com.idealighter.game.schedule.core.CronExpressionEx;
import com.idealighter.game.schedule.listener.JobRunTimeListener;
import com.idealighter.game.schedule.struct.JobRunTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务调度管理,复杂的使用quantz，简单的使用jdk的ScheduledExecutorService .
 * 
 * @date 2015年9月22日 下午5:21:15
 *
 */
@Singleton
public class ScheduleMgr {
  private static final Logger LOG = LoggerFactory.getLogger(ScheduleMgr.class);

  // job的group
  public static final String GAME_JOB_GROUP = "game-job-scheduler";
  private final JobRunTimeMgr jobRunTimeMgr;
  private final SchedulerJobDic jobDic;
  // quantz作业调度管理器
  private Scheduler scheduler;
  // java定时Schedule,使用扩展的ScheduledThreadPoolExecutor
  public final FixedTimeScheduler scheduledExecutor =
      new FixedTimeScheduler(4, r -> new Thread(r, "game-scheduler"));

  /**
   * 构造函数 .
   * 
   * @param schedulerJobDic .
   * @param jobRunTimeMgr .
   * @param jobRunTimeListener .
   * @throws SchedulerException .
   */
  @Inject
  public ScheduleMgr(SchedulerJobDic schedulerJobDic, JobRunTimeMgr jobRunTimeMgr,
      JobRunTimeListener jobRunTimeListener) throws SchedulerException {
    this.jobRunTimeMgr = jobRunTimeMgr;
    this.jobDic = schedulerJobDic;

    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    scheduler = schedulerFactory.getScheduler();
    loadJobs();
    // 所有的job都触发事件
    scheduler.getListenerManager().addJobListener(jobRunTimeListener,
        GroupMatcher.<JobKey>groupEquals(GAME_JOB_GROUP));
    scheduler.start();
  }

  /**
   * 加载作业 .
   */
  @SuppressWarnings("unchecked")
  private void loadJobs() {
    List<SchedulerJobDomain> jobDoms = jobDic.list();

    for (SchedulerJobDomain jobDom : jobDoms) {
      Class<? extends Job> jobClass;
      try {
        jobClass = (Class<? extends Job>) Class.forName(jobDom.getJavaClass());
      } catch (ClassNotFoundException e) {
        LOG.error(jobDom.getJavaClass() + "类没有找到", e);
        continue;
      }

      scheduleJob(jobClass, jobDom.getName(), jobDom.getCron(), new JobDataMap());
    }
  }

  /**
   * 调度job .
   * 
   * @param jobClass . job的类
   * @param name . job名称,对job的简单描述：如：重置玩家兑换次数，日常任务定时刷新等
   * @param cron . cron表达式
   */
  public CronTrigger scheduleJob(Class<? extends Job> jobClass, String name, String cron,
      JobDataMap jobData) {
    JobDetail job = null;
    CronTrigger trigger = null;
    // 作业开启后第一次运行的时间
    Date firstRunTime = null;
    // cron表达式
    CronExpression cronExpression = null;
    // 上一次运行时间
    Date preRunTime = null;
    Date now = new Date();

    // 作业
    try {
      job = newJob(jobClass).withIdentity(jobClass.getName() + name, GAME_JOB_GROUP)
          .usingJobData(jobData).build();

      cronExpression = new CronExpressionEx(cron);
      preRunTime = cronExpression.getTimeBefore(now);

      trigger = newTrigger().withIdentity(jobClass.getName() + name, GAME_JOB_GROUP)
          .withSchedule(cronSchedule(cronExpression))// 传入自定义cron表达式实现,quantz的没有实现getTimeBefore
          .startAt(now).build();
      firstRunTime = scheduler.scheduleJob(job, trigger);
    } catch (ParseException e) {
      LOG.error("cron表达式: '" + cron + "'无效,.", e);
    } catch (SchedulerException e) {
      LOG.error("驱动job:" + job + ", trigger:" + trigger + "失败", e);
    }

    // 启动设置job的上一次和下一次运行时间
    jobRunTimeMgr.setJobRunTime(jobClass,
        new JobRunTime(preRunTime == null ? 0 : preRunTime.getTime(),
            firstRunTime == null ? 0 : firstRunTime.getTime()));

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    LOG.info(String.format("【%s】已启动,表达式:%s,上次时间:%s,首次时间:%s", name, trigger.getCronExpression(),
        format.format(preRunTime), format.format(firstRunTime)));

    return trigger;
  }

  /**
   * 定时 .
   * 
   * @param command .
   * @param delay .
   * @param unit .
   * @param executor . 实际执行任务的线程 Executor，null则由定时线程池执行
   * @return
   */
  public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit,
      Executor executor) {
    if (executor == null) {
      return scheduledExecutor.schedule(command, delay, unit);
    } else {
      return scheduledExecutor.schedule(() -> executor.execute(command), delay, unit);
    }
  }

  /**
   * 定时 .
   * 
   * @param command .
   * @param initDelay .
   * @param period .
   * @param unit .
   * @param executor . 实际执行任务的线程 Executor，null则由定时线程池执行
   * @return
   */
  public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initDelay, long period,
      TimeUnit unit, Executor executor) {
    if (executor == null) {
      return scheduledExecutor.scheduleAtFixedRate(command, initDelay, period, unit);
    } else {
      return scheduledExecutor.scheduleAtFixedRate(() -> executor.execute(command), initDelay,
          period, unit);
    }
  }

  /**
   * 定时 .
   * 
   * @param command .
   * @param initDelay .
   * @param delay .
   * @param unit .
   * @param executor . 实际执行任务的线程 Executor，null则由定时线程池执行
   * @return
   */
  public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initDelay, long delay,
      TimeUnit unit, Executor executor) {
    if (executor == null) {
      return scheduledExecutor.scheduleWithFixedDelay(command, initDelay, delay, unit);
    } else {
      return scheduledExecutor.scheduleWithFixedDelay(() -> executor.execute(command), initDelay,
          delay, unit);
    }
  }

  /**
   * 固定次数定时 .
   * 
   * @param command .
   * @param initDelay .
   * @param delay .
   * @param repeat . 重复次数,为0不重复
   * @param unit .
   * @param executor . 实际执行任务的线程 Executor，null则由定时线程池执行
   * @return
   */
  public ScheduledFuture<?> scheduleWithFixedTime(Runnable command, long initDelay, long delay,
      int repeat, TimeUnit unit, Executor executor) {
    if (executor == null) {
      return scheduledExecutor.scheduleWithFixedTime(command, initDelay, delay, repeat, unit);
    } else {
      return scheduledExecutor.scheduleWithFixedTime(() -> executor.execute(command), initDelay,
          delay, repeat, unit);
    }
  }

  public Scheduler getScheduler() {
    return scheduler;
  }
}
