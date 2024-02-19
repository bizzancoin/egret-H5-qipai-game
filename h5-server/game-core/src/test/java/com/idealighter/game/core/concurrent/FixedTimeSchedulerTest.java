
package com.idealighter.game.core.concurrent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Ignore;
import org.junit.Test;

public class FixedTimeSchedulerTest {

  @Test
  @Ignore
  public void testScheduleWithFixedTime() {
    FixedTimeScheduler fixedTimeScheduler =
        new FixedTimeScheduler(4, r -> new Thread(r, "game-scheduler"));

    Runnable r = new Runnable() {
      private AtomicInteger atomicInteger = new AtomicInteger(0);

      @Override
      public void run() {
        System.out.println(atomicInteger.incrementAndGet());
      }
    };

    ScheduledFuture<?> scheduleWithFixedTime =
        fixedTimeScheduler.scheduleWithFixedTime(r, 2, 1, 1000000, TimeUnit.NANOSECONDS);


    try {
      Thread.sleep(5000);
      scheduleWithFixedTime.cancel(false);
      // Thread.sleep(100000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
