
package com.idealighter.game.core.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定次数ScheduledThreadPoolExecutor,游戏中的捕鱼需要大量使用， 扩展ScheduledThreadPoolExecutor提供可以调度固定次数的任务.
 * 
 * @date 2016年4月8日 上午9:21:38
 *
 */
public class FixedTimeScheduler extends ScheduledThreadPoolExecutor {

  /**
   * 构造函数.
   * 
   * @param corePoolSize .
   * @param threadFactory .
   */
  public FixedTimeScheduler(int corePoolSize, ThreadFactory threadFactory) {
    super(corePoolSize, threadFactory);
  }

  /**
   * 固定次数执行任务.
   * 
   * @param command .
   * @param initDelay .
   * @param delay .
   * @param repeat .
   * @param unit .
   * @return .
   */
  public ScheduledFuture<?> scheduleWithFixedTime(Runnable command, long initDelay, long delay,
      int repeat, TimeUnit unit) {
    if (repeat < 0) {
      repeat = 0;
    }
    FixedTimeFutureTask t = new FixedTimeFutureTask(command, repeat, delay, unit);
    t.delegate = schedule(t, initDelay, unit);
    return t;
  }

  /**
   * 固定次数.
   *
   */
  private class FixedTimeFutureTask extends FutureTask<Void>
      implements RunnableScheduledFuture<Void> {
    // 剩余执行次数
    final AtomicInteger leftTime;
    // 每次执行延时
    final long delay;
    final TimeUnit unit;
    private final Runnable runnable;
    volatile ScheduledFuture<?> delegate;

    /**
     * 构造函数.
     * 
     * @param runnable .
     * @param result .
     */
    FixedTimeFutureTask(Runnable runnable, int repeat, long delay, TimeUnit unit) {
      super(runnable, null);
      this.runnable = runnable;
      this.leftTime = new AtomicInteger(repeat);
      this.delay = delay;
      this.unit = unit;
    }

    @Override
    public long getDelay(TimeUnit unit) {
      return delegate.getDelay(unit);
    }

    @Override
    public int compareTo(Delayed o) {
      return delegate.compareTo(o);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
      return delegate.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
      return delegate.isCancelled();
    }

    @Override
    public boolean isDone() {
      return delegate.isDone();
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
      delegate.get();
      return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
      delegate.get(timeout, unit);
      return null;
    }

    @Override
    public boolean isPeriodic() {
      return false;
    }

    @Override
    protected void done() {
      if (!isCancelled() && leftTime.getAndDecrement() > 0) {
        this.delegate = schedule(new FutureTask<Void>(runnable, null) {
          @Override
          protected void done() {
            FixedTimeFutureTask.this.done();
          }
        }, delay, unit);
      }
    }
  }
}
