package com.idealighter.game.server.core.executor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * <p>
 * . 封装游戏中的Executor，游戏中的Executor基本都是多生产者，单消费者(保证线程安全，不然就玩家身上所有的数据操作要加锁)。 . 在AMD a8-5600k APU with
 * Radeom(tm)HD . Graphics 3.59HZ;win7 64位；8G(7.48G可用)测试：吞吐400w，在任务中只计数。
 * java的newSingleThreadExecutor需要1500ms左右，DisruptorExecutor只需要920ms左右。 .
 * DisruptorExecutor在不同的环境还有调优的空间。 . 基于Disruptor的Executor,比Java自带的Executor速度快2-3倍.
 * </p>
 * .
 * 
 * @date 2015年7月29日 下午4:49:13
 *
 */
public class DisruptorExecutor implements Executor {

  // 线程名称
  private static final String DEFAULT_EXECUTOR_NAME = "disruptor-executor";
  // Disruptor的ringBuffer缓存大小，必须是2的幂
  private static final int BUFFER_SIZE = 65536;

  // 实际执行task的executor
  private final ExecutorService executor;

  /**
   * BlockingWaitStrategy是最慢的等待策略，但也是CPU使用率最低和最稳定的选项。然而，可以根据不同的部署环境调整选项以提高性能。 .
   * YieldingWaitStrategy是可以被用在低延迟系统中的两个策略之一，这种策略在减低系统延迟的同时也会增加CPU运算量。 .
   * YieldingWaitStrategy策略会循环等待sequence增加到合适的值。 .
   * 循环中调用Thread.yield()允许其他准备好的线程执行。如果需要高性能而且事件消费者线程比逻辑内核少的时候， .
   * 推荐使用YieldingWaitStrategy策略。例如：在开启超线程的时候。 . BusySpinWaitStrategy是性能最高的等待策略，同时也是对部署环境要求最高的策略。
   * 这个性能最好用在事件处理线程比物理内核数目还要小的时候。例如：在禁用超线程技术的时候。.
   */
  private final Disruptor<TaskEvent> disruptor;

  public DisruptorExecutor() {
    this(DEFAULT_EXECUTOR_NAME);
  }

  /**
   * 构造函数.
   * 
   * @param name 名称.
   */
  public DisruptorExecutor(String name) {
    this.executor = Executors.newSingleThreadExecutor(r -> new Thread(r, name));
    this.disruptor = new Disruptor<>(TaskEvent::new, BUFFER_SIZE, executor, ProducerType.MULTI,
        new BlockingWaitStrategy());
  }

  /**
   * 启动DisruptorExecutor.
   */
  @SuppressWarnings("unchecked")
  public void startUp() {
    disruptor.handleExceptionsWith(new LogExceptionHandler());
    disruptor.handleEventsWith((event, sequence, endOfBatch) -> event.getTask().run());
    disruptor.start();
  }

  /**
   * 停止任务调度（阻塞直到所有提交任务完成）.
   * 
   * @return 结果.
   */
  public boolean awaitAndShutdown() {

    return awaitAndShutdown(Integer.MAX_VALUE, TimeUnit.SECONDS);
  }

  /**
   * 停止任务调度（阻塞直到所有提交任务完成）.
   * 
   * @param timeout . 超时时间.
   * @param timeUnit . 时间单位.
   * @return 结果.
   */
  public boolean awaitAndShutdown(long timeout, TimeUnit timeUnit) {
    shutdown();

    try {

      return executor.awaitTermination(timeout, timeUnit);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return false;
  }

  /**
   * 停止任务调度.
   */
  void shutdown() {
    disruptor.shutdown();
    executor.shutdown();
  }

  /**
   * 强制停止任务调度（正在执行的任务将被停止，未执行的任务将被丢弃）.
   */
  void halt() {
    executor.shutdownNow();
    disruptor.halt();
  }

  /**
   * 执行任务.
   * 
   * @param task . 任务.
   */
  @Override
  public void execute(Runnable task) {
    disruptor.getRingBuffer().publishEvent((event, sequence, buffer) -> event.setTask(task), task);
  }
}


