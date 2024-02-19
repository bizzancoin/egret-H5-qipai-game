package com.idealighter.game.safegold;

import com.idealighter.game.core.assertions.HuohuaAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class SafeGoldTest {

  private static AtomicLong gold = new AtomicLong(0);

  public static void add(Long change) {

    gold.addAndGet(change);
  }

  /**
   * 测试 .
   *
   * @author abin
   * @date 2018年6月23日 下午4:06:26
   * @param change .
   */
  public static void addBeforeAfterCheck(Long change) {
    long before = gold.get();

    long after = gold.addAndGet(change);

    HuohuaAssert.isTrue(change == after - before);
  }

  public static boolean addForcompareAndSet(Long before, Long change) {
    return gold.compareAndSet(before, before + change);
  }

  /**
   * test .
   *
   * @author abin
   * @date 2018年6月23日 下午4:06:43
   * @param args .
   * @throws InterruptedException .
   */
  public static void main(String[] args) throws InterruptedException {

    List<Thread> threads = new ArrayList<>();

    for (int i = 0; i < 1000000; i++) {
      Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
          SafeGoldTest.add(1L);

          // Long before = gold.get();
          // int fail = 0;
          // while (!SafeGoldTest.addForcompareAndSet(before, 1L)) {
          // before = gold.get();
          // fail++;
          // }
          // if (fail > 0) {
          // System.out.println("失败 " + fail + "次数");
          // }
        }
      });

      threads.add(thread);
    }

    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    System.out.println(gold.get());

  }
}
