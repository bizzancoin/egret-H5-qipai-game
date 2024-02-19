package com.idealighter.game.server.core.executor;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * DisruptorExecutor组合.
 *
 */
public class DisruptorExecutorGroup {

  private final AtomicInteger childIndex = new AtomicInteger();
  private final DisruptorExecutor[] children;

  /**
   * 构造函数.
   * 
   * @param size . 直接executor 数量.
   * @param executorName . 执行数量.
   */
  public DisruptorExecutorGroup(int size, String executorName) {
    this.children = new DisruptorExecutor[size];

    for (int i = 0; i < size; i++) {
      children[i] = new DisruptorExecutor(executorName + "-" + i);
    }
  }

  /**
   * 根据index获取DisruptorExecutor.
   * 
   * @param index . index.
   * @return 执行器.
   */
  public DisruptorExecutor getExecutor(int index) {
    if (index < 0 || index >= children.length) {
      return null;
    }

    return children[index];
  }

  /**
   * 获取Executor.
   * 
   * @return executor.
   */
  public DisruptorExecutor next() {
    return children[Math.abs(childIndex.getAndIncrement() % children.length)];
  }

  /**
   * 获取group的大小.
   * 
   * @return 数量.
   */
  public int size() {

    return children.length;
  }

  /**
   * 启动.
   */
  public void startUp() {
    for (DisruptorExecutor executor : children) {
      executor.startUp();
    }
  }
}
