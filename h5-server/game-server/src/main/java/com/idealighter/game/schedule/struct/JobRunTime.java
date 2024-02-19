package com.idealighter.game.schedule.struct;

/**
 * job运行时间bean .
 * 
 * @date 2014年11月15日 下午11:06:47
 *
 */
public class JobRunTime {

  /** job上次运行时间 . */
  private volatile long preRunTime;
  /** job下次运行时间 . */
  private volatile long nextRunTime;

  /**
   * 构造函数 .
   * 
   * @param preRunTime .
   * @param nextRunTime .
   */
  public JobRunTime(long preRunTime, long nextRunTime) {
    super();
    this.preRunTime = preRunTime;
    this.nextRunTime = nextRunTime;
  }

  public long getPreRunTime() {
    return preRunTime;
  }

  public void setPreRunTime(long preRunTime) {
    this.preRunTime = preRunTime;
  }

  public long getNextRunTime() {
    return nextRunTime;
  }

  public void setNextRunTime(long nextRunTime) {
    this.nextRunTime = nextRunTime;
  }

}
