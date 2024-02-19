
package com.idealighter.game.core.service.welfare.manager;

import com.google.inject.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 签到数据管理.
 * 
 * @date 2015年12月9日 下午2:00:50
 *
 */
@Singleton
public class WelfareDataMgr {

  // ip签到次数
  private final Map<String, AtomicInteger> ipSignInNums = new ConcurrentHashMap<>();
  // 机器码签到次数
  private final Map<String, AtomicInteger> macSignInNums = new ConcurrentHashMap<>();

  // ip领取福利次数
  private final Map<String, AtomicInteger> ipReceiveBenefitsNums = new ConcurrentHashMap<>();
  // 机器码领取福利次数
  private final Map<String, AtomicInteger> macReceiveBenefitsNums = new ConcurrentHashMap<>();

  /**
   * 获取ip的签到次数 .
   * 
   * @param ip .
   */
  public int getIpSignInNum(String ip) {
    AtomicInteger num = ipSignInNums.get(ip);

    return num == null ? 0 : num.get();
  }

  /**
   * 增加ip的签到次数 .
   * 
   * @param ip .
   */
  public void addIpSignInNum(String ip) {
    AtomicInteger num = ipSignInNums.get(ip);
    if (num == null) {
      ipSignInNums.put(ip, new AtomicInteger(1));
    } else {
      num.incrementAndGet();
    }
  }

  /**
   * 获取mac的签到次数 .
   * 
   * @param mac .
   */
  public int getMacSignInNum(String mac) {
    AtomicInteger num = macSignInNums.get(mac);

    return num == null ? 0 : num.get();
  }

  /**
   * 增加mac的签到次数 .
   * 
   * @param mac .
   */
  public void addMacSignInNum(String mac) {
    AtomicInteger num = macSignInNums.get(mac);
    if (num == null) {
      macSignInNums.put(mac, new AtomicInteger(1));
    } else {
      num.incrementAndGet();
    }
  }

  /**
   * 获取ip的领取福利次数 .
   * 
   * @param ip .
   */
  public int getIpReceiveBenefitsNum(String ip) {
    AtomicInteger num = ipReceiveBenefitsNums.get(ip);

    return num == null ? 0 : num.get();
  }

  /**
   * 增加ip的领取福利次数 .
   * 
   * @param ip .
   */
  public void addIpReceiveBenefitsNum(String ip) {
    AtomicInteger num = ipReceiveBenefitsNums.get(ip);
    if (num == null) {
      ipReceiveBenefitsNums.put(ip, new AtomicInteger(1));
    } else {
      num.incrementAndGet();
    }
  }

  /**
   * 获取mac的领取福利次数次数 .
   * 
   * @param mac .
   */
  public int getMacReceiveBenefitsNum(String mac) {
    AtomicInteger num = macReceiveBenefitsNums.get(mac);

    return num == null ? 0 : num.get();
  }

  /**
   * 增加mac的领取福利次数 .
   * 
   * @param mac .
   */
  public void addMacReceiveBenefitsNum(String mac) {
    AtomicInteger num = macReceiveBenefitsNums.get(mac);
    if (num == null) {
      macReceiveBenefitsNums.put(mac, new AtomicInteger(1));
    } else {
      num.incrementAndGet();
    }
  }

  /**
   * 清空福利数据 .
   */
  public void clearWelfareData() {
    ipSignInNums.clear();
    macSignInNums.clear();
    ipReceiveBenefitsNums.clear();
    macReceiveBenefitsNums.clear();
  }

}
