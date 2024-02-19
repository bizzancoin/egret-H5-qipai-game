package com.idealighter.game.server.net;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadNameFactory implements ThreadFactory {
  private ThreadGroup group;
  private AtomicInteger threadNumber = new AtomicInteger(0);
  private String namePrefix;
  private boolean daemon;

  public ThreadNameFactory(String namePreFix) {
    this(namePreFix, false);
  }

  /**
   * 构造函数.
   * 
   * @param namePreFix .
   * @param daemon .
   */
  public ThreadNameFactory(String namePreFix, boolean daemon) {
    SecurityManager s = System.getSecurityManager();
    group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    this.namePrefix = namePreFix + "-thread-";
    this.daemon = daemon;
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
    if (daemon) {
      t.setDaemon(daemon);
    } else {
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      if (t.getPriority() != Thread.NORM_PRIORITY) {
        t.setPriority(Thread.NORM_PRIORITY);
      }
    }
    return t;
  }

  public String getNamePrefix() {
    return namePrefix;
  }
}
