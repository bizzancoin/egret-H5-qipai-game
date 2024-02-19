package com.idealighter.game.schedule.manager;

import com.google.inject.Singleton;

import com.idealighter.game.schedule.struct.JobRunTime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.Job;


/**
 * job运行时间统一管理 .
 * 
 * @date 2014年11月15日 下午11:11:22
 *
 */
@Singleton
public class JobRunTimeMgr {

  /** job运行时间map . */
  private Map<Class<? extends Job>, JobRunTime> jobRunTimes = new ConcurrentHashMap<>();

  /**
   * 获取指定job的运行时间封装 .
   * 
   * @param jobClzz .
   * @return
   */
  public JobRunTime getJobRunTime(Class<? extends Job> jobClzz) {

    return jobRunTimes.get(jobClzz);
  }

  /**
   * 设置job的运行时间 .
   * 
   * @param jobClzz .
   * @param jobRunTime .
   */
  public void setJobRunTime(Class<? extends Job> jobClzz, JobRunTime jobRunTime) {

    jobRunTimes.put(jobClzz, jobRunTime);
  }
}
