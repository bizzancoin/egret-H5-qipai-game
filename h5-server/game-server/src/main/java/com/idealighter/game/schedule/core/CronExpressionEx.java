package com.idealighter.game.schedule.core;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.NavigableSet;
import java.util.SortedSet;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * CronExpression的getTimeBefore(根据传入的时间获取前一次触发的时间)， . 但是没有实现，
 * <p>
 * . CronExpressionEx覆盖了getTimeBefore方法，主要用于游戏中的停服，当机导致超过执行的时间， .
 * 游戏服启动时，获取启动时刻前一次执行时间，当玩家身上保存的job执行时刻小于job的前一次的执行则进行相应的重置或者刷新操作。 .
 * 下面是来自官方文档（http://www.quartz-scheduler.org/generated/2.2.1/html/qs-all/#page/Quartz_Scheduler_Documentation_Set%2Fco-trg_crontriggers.html）的用例，模拟的用例，我们游戏中job配置的用例，
 * . 选择的时间点是3月1号，因为3月1号比较特殊，往前推是2月，2月只有28或者29号，测试最坏情况的边界，结果： . job开始时间:2014-10-12 0:00:00 cron表达式:0
 * 15 10 . LW * ? . job下一步执行时间:2014-10-31 10:15:00 job上一步执行时间:2014-9-30 10:15:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 . 0 12 * * . ? job下一步执行时间:2014-3-1 12:00:00 job上一步执行时间:2014-2-28 12:00:00
 * job开始时间:2014-3-1 0:00:00 . cron表达式:0 15 10 ? * * . job下一步执行时间:2014-3-1 10:15:00
 * job上一步执行时间:2014-2-28 10:15:00 job开始时间:2014-3-1 . 0:00:00 cron表达式:0 15 10 * * . ?
 * job下一步执行时间:2014-3-1 10:15:00 job上一步执行时间:2014-2-28 10:15:00 . job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 15 10 * * . ? * job下一步执行时间:2014-3-1 . 10:15:00 job上一步执行时间:2014-2-28 . 10:15:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 15 10 * * . ? 2005 job下一步执行时间: job上一步执行时间:2005-12-31 . 10:15:00
 * job开始时间:2014-3-1 0:00:00 cron表达式:0 * 14 . * * . ? job下一步执行时间:2014-3-1 14:00:00 .
 * job上一步执行时间:2014-2-28 14:59:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0/5 14 * * . ?
 * job下一步执行时间:2014-3-1 . 14:00:00 job上一步执行时间:2014-2-28 14:55:00 job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 0/5 14,18 . * * . ? job下一步执行时间:2014-3-1 14:00:00 job上一步执行时间:2014-2-28 18:55:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 . 0-5 14 * * . ? job下一步执行时间:2014-3-1 14:00:00 job上一步执行时间:2014-2-28 14:05:00
 * job开始时间:2014-3-1 . 0:00:00 cron表达式:0 10,44 14 ? 3 WED job下一步执行时间:2014-3-5 14:10:00
 * job上一步执行时间:2013-3-27 . 14:44:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 15 10 ? * MON-FRI .
 * job下一步执行时间:2014-3-3 . 10:15:00 job上一步执行时间:2014-2-28 10:15:00 job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 15 10 . 15 * ? . job下一步执行时间:2014-3-15 10:15:00 job上一步执行时间:2014-2-15 10:15:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 . 15 10 L * ? . job下一步执行时间:2014-3-31 10:15:00 job上一步执行时间:2014-2-28 10:15:00
 * job开始时间:2014-3-1 . 0:00:00 cron表达式:0 15 10 L-2 * ? . job下一步执行时间:2014-3-29 10:15:00
 * job上一步执行时间:2014-2-26 . 10:15:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 15 10 ? * 6L .
 * job下一步执行时间:2014-3-28 . 10:15:00 job上一步执行时间:2014-2-28 10:15:00 job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 15 10 . ? * 6L . job下一步执行时间:2014-3-28 10:15:00 job上一步执行时间:2014-2-28 10:15:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 . 15 10 ? * 6L . 2002-2005 job下一步执行时间: job上一步执行时间:2005-12-30 10:15:00
 * job开始时间:2014-3-1 0:00:00 . cron表达式:0 15 10 ? * 6#3 . job下一步执行时间:2014-3-21 10:15:00
 * job上一步执行时间:2014-2-21 10:15:00 job开始时间:2014-3-1 . 0:00:00 cron表达式:0 0 12 1/5 * ? .
 * job下一步执行时间:2014-3-1 12:00:00 job上一步执行时间:2014-2-26 12:00:00 . job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 11 11 11 11 ? job下一步执行时间:2014-11-11 11:11:00 job上一步执行时间:2013-11-11 . 11:11:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 0 9,12,18,21 * * . ? * job下一步执行时间:2014-3-1 . 9:00:00 job上一步执行时间:2014-2-28
 * 21:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0 0 L-28W . * ? . job下一步执行时间:2014-3-3 0:00:00
 * job上一步执行时间:2014-1-3 0:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:10-20 . 10-20 * * . * ? . *
 * job下一步执行时间:2014-3-1 . 0:10:10 job上一步执行时间:2014-2-28 23:20:20 job开始时间:2014-3-1 . 0:00:00 cron表达式:0
 * 0 0 28,29,30,31 * ? . job下一步执行时间:2014-3-28 0:00:00 job上一步执行时间:2014-2-28 . 0:00:00
 * job开始时间:2014-3-1 0:00:00 cron表达式:0/5 * * . * * . ? * job下一步执行时间:2014-3-1 . 0:00:05 .
 * job上一步执行时间:2014-2-28 23:59:55 job开始时间:2014-3-1 0:00:00 cron表达式:0 0 6 * * . ? *
 * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0
 * 9,12,18,21 . * * . ? * job下一步执行时间:2014-3-1 . 9:00:00 job上一步执行时间:2014-2-28 21:00:00
 * job开始时间:2014-3-1 0:00:00 . cron表达式:0 0 21 * * . ? * job下一步执行时间:2014-3-1 . 21:00:00
 * job上一步执行时间:2014-2-28 21:00:00 job开始时间:2014-3-1 . 0:00:00 cron表达式:0 0 6 * * . ? *
 * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00 . job开始时间:2014-3-1 0:00:00 cron表达式:0
 * 0/5 * * . * ? . * job下一步执行时间:2014-3-1 . 0:05:00 job上一步执行时间:2014-2-28 . 23:55:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 0 0 * * . ? * job下一步执行时间:2014-3-2 . 0:00:00 . job上一步执行时间:2014-2-28 0:00:00
 * job开始时间:2014-3-1 0:00:00 cron表达式:0 0 0 1 * ? . * job下一步执行时间:2014-4-1 . 0:00:00
 * job上一步执行时间:2014-2-1 0:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0 6 * * . ? * job下一步执行时间:2014-3-1
 * . 6:00:00 job上一步执行时间:2014-2-28 6:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0 . 21 * * . ? *
 * job下一步执行时间:2014-3-1 . 21:00:00 job上一步执行时间:2014-2-28 21:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 .
 * 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00 job开始时间:2014-3-1 0:00:00
 * . cron表达式:0 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00
 * job开始时间:2014-3-1 . 0:00:00 cron表达式:0 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00
 * job上一步执行时间:2014-2-28 6:00:00 . job开始时间:2014-3-1 0:00:00 cron表达式:0 0 6 * * . ? *
 * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 . 6:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0
 * 21 * * . ? * job下一步执行时间:2014-3-1 . 21:00:00 . job上一步执行时间:2014-2-28 21:00:00 job开始时间:2014-3-1
 * 0:00:00 cron表达式:0 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00
 * job开始时间:2014-3-1 0:00:00 cron表达式:0 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00
 * job上一步执行时间:2014-2-28 6:00:00 job开始时间:2014-3-1 0:00:00 cron表达式:0 0 . 9,12,18,21 * * . ? *
 * job下一步执行时间:2014-3-1 . 9:00:00 job上一步执行时间:2014-2-28 21:00:00 job开始时间:2014-3-1 0:00:00 . cron表达式:0
 * 0 6 * * . ? * job下一步执行时间:2014-3-1 . 6:00:00 job上一步执行时间:2014-2-28 6:00:00
 * </p>
 * .
 * 
 * 
 * @date 2014年11月10日 下午3:01:46
 *
 */
public class CronExpressionEx extends CronExpression {
  private static final long serialVersionUID = 714957089323577451L;

  private static final Logger LOG = LoggerFactory.getLogger(CronExpressionEx.class);

  // 最小日期为当前日期前的100年，防止很鬼扯的cron表达式计算
  public static final int MIN_YEAR = Calendar.getInstance().get(Calendar.YEAR) - 100;

  public CronExpressionEx(String cronExpression) throws ParseException {
    super(cronExpression);
  }

  @Override
  public Date getTimeBefore(Date endTime) {
    // 采用公历
    Calendar cl = new java.util.GregorianCalendar(getTimeZone());

    /*
     * 往前推一秒，cron最小精度为秒，可能碰巧当前时间就是job触发的时间，所有往前推一秒获取前一次触发的时间 .
     */
    endTime = new Date(endTime.getTime() - 1000);

    /*
     * cron表达式不处理毫秒 .
     */
    cl.setTime(endTime);
    cl.set(Calendar.MILLISECOND, 0);

    // 是否找到前一个触发时间
    boolean gotOne = false;

    // 直到找到前一个触发时间为止
    while (!gotOne) {

      // 不能小于1970，date的getime距离1970的毫米数
      if (cl.get(Calendar.YEAR) < 1970) {
        return null;
      }

      SortedSet<Integer> st = null;


      // 时间中的秒数
      int sec = cl.get(Calendar.SECOND);
      // 时间中的分钟数
      int min = cl.get(Calendar.MINUTE);

      // 获取前一个触发点的秒................................
      st = seconds.headSet(sec, true);

      if (st != null && st.size() != 0) {
        sec = st.last();
      } else {
        sec = seconds.last();
        min--;
        cl.set(Calendar.MINUTE, min);
      }

      cl.set(Calendar.SECOND, sec);

      min = cl.get(Calendar.MINUTE);
      // 前一次触发点的小时
      int hr = cl.get(Calendar.HOUR_OF_DAY);
      int t = 0;
      t = -1;

      // 获取前一次触发的分钟数....................................
      st = minutes.headSet(min, true);

      if (st != null && st.size() != 0) {
        t = min;
        min = st.last();
      } else {
        min = minutes.last();
        hr--;
      }

      if (min != t) {
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MINUTE, min);
        setCalendarHour(cl, hr);
        continue;
      }
      cl.set(Calendar.MINUTE, min);

      hr = cl.get(Calendar.HOUR_OF_DAY);
      // 前一次触发的几号
      int day = cl.get(Calendar.DAY_OF_MONTH);
      t = -1;

      // 获取前一次触发的小时........................
      st = hours.headSet(hr, true);
      if (st != null && st.size() != 0) {
        t = hr;
        hr = st.last();
      } else {
        hr = hours.last();
        day--;
      }
      if (hr != t) {
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.DAY_OF_MONTH, day);
        setCalendarHour(cl, hr);
        continue;
      }

      cl.set(Calendar.HOUR_OF_DAY, hr);
      day = cl.get(Calendar.DAY_OF_MONTH);

      // Calendar的月份从0开始的，quantz的cron表达式计算是从0开始的
      int mon = cl.get(Calendar.MONTH) + 1;

      t = -1;
      int tmon = mon;

      // 获取触发前的号数............................
      // 月份中的号数是否包含'?'，NO_SPEC：问号，不关心具体数值
      boolean dayOfMSpec = !daysOfMonth.contains(NO_SPEC);
      // 星期中的是否包含'?'，NO_SPEC：问号，不关心具体数值
      boolean dayOfWSpec = !daysOfWeek.contains(NO_SPEC);

      if (dayOfMSpec && !dayOfWSpec) { // 根据月份中的号数规则计算前一次号数
        st = daysOfMonth.headSet(day, true);

        if (lastdayOfMonth) { // 月份的最后一天
          if (!nearestWeekday) { // nearestWeekday:最接近指定天的工作日（周一到周五）,每个月的最后一个工作日
            t = day;
            day = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));
            day -= lastdayOffset; // 假如cron为0 0 9,12,18,21 L-12 * ? . *，lastdayOffset为12
            if (t < day) {
              mon--;
              if (mon < 1) {
                mon = 12;
                tmon = 3333; // 保证mon != tmon
                cl.roll(Calendar.YEAR, 1);
              }

              day = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));
            }
          } else {
            t = day;
            day = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));
            day -= lastdayOffset;

            java.util.Calendar tcal = java.util.Calendar.getInstance(getTimeZone());
            tcal.set(Calendar.SECOND, 0);
            tcal.set(Calendar.MINUTE, 0);
            tcal.set(Calendar.HOUR_OF_DAY, 0);
            tcal.set(Calendar.DAY_OF_MONTH, day);
            tcal.set(Calendar.MONTH, mon - 1);
            tcal.set(Calendar.YEAR, cl.get(Calendar.YEAR));

            int ldom = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));
            int dow = tcal.get(Calendar.DAY_OF_WEEK);

            if (dow == Calendar.SATURDAY && day == 1) {
              day += 2;
            } else if (dow == Calendar.SATURDAY) {
              day -= 1;
            } else if (dow == Calendar.SUNDAY && day == ldom) {
              day -= 2;
            } else if (dow == Calendar.SUNDAY) {
              day += 1;
            }

            tcal.set(Calendar.SECOND, sec);
            tcal.set(Calendar.MINUTE, min);
            tcal.set(Calendar.HOUR_OF_DAY, hr);
            tcal.set(Calendar.DAY_OF_MONTH, day);
            tcal.set(Calendar.MONTH, mon - 1);
            Date ntime = tcal.getTime();
            if (ntime.after(endTime)) {
              day = getLastDayOfMonth(mon - 1, tcal.get(Calendar.YEAR));

              mon--;
            }
          }
        } else if (nearestWeekday) {
          t = day;
          day = daysOfMonth.first().intValue();

          java.util.Calendar tcal = java.util.Calendar.getInstance(getTimeZone());
          tcal.set(Calendar.SECOND, 0);
          tcal.set(Calendar.MINUTE, 0);
          tcal.set(Calendar.HOUR_OF_DAY, 0);
          tcal.set(Calendar.DAY_OF_MONTH, day);
          tcal.set(Calendar.MONTH, mon - 1);
          tcal.set(Calendar.YEAR, cl.get(Calendar.YEAR));

          int ldom = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));
          int dow = tcal.get(Calendar.DAY_OF_WEEK);

          if (dow == Calendar.SATURDAY && day == 1) {
            day += 2;
          } else if (dow == Calendar.SATURDAY) {
            day -= 1;
          } else if (dow == Calendar.SUNDAY && day == ldom) {
            day -= 2;
          } else if (dow == Calendar.SUNDAY) {
            day += 1;
          }

          tcal.set(Calendar.SECOND, sec);
          tcal.set(Calendar.MINUTE, min);
          tcal.set(Calendar.HOUR_OF_DAY, hr);
          tcal.set(Calendar.DAY_OF_MONTH, day);
          tcal.set(Calendar.MONTH, mon - 1);
          Date ntime = tcal.getTime();
          if (ntime.after(endTime)) {
            day = daysOfMonth.last().intValue();
            mon--;
          }
        } else if (st != null && st.size() != 0) {
          t = day;
          day = st.last();
        } else {
          day = daysOfMonth.last().intValue();
          mon--;

          // 小月还要继续处理
          int lastDay = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));

          NavigableSet<Integer> headSet = daysOfMonth.headSet(lastDay, true);

          // 比如：日期触发为：[28,29,30,31],碰巧为2月或者小月，计算出来的触发日期为：2月31
          if (headSet != null && headSet.size() != 0) {
            day = headSet.last();
          } else {
            day = daysOfMonth.last().intValue();
            mon--;
          }
        }

        if (day != t || mon != tmon) {
          cl.set(Calendar.SECOND, 59);
          cl.set(Calendar.MINUTE, 59);
          cl.set(Calendar.HOUR_OF_DAY, 23);
          cl.set(Calendar.DAY_OF_MONTH, day);
          cl.set(Calendar.MONTH, mon - 1);
          continue;
        }
      } else if (dayOfWSpec && !dayOfMSpec) { // 根据星期的规则来获取日期
        if (lastdayOfWeek) { // 每个月的最后一个星期几
          // 期望的星期几
          int dow = daysOfWeek.last().intValue();
          // 当前星期几
          int cdow = cl.get(Calendar.DAY_OF_WEEK);
          int daysToDel = 0;
          if (cdow > dow) {
            daysToDel = cdow - dow;
          }
          if (cdow < dow) {
            daysToDel = cdow + (7 - dow);
          }

          int lday = getLastDayOfMonth(mon, cl.get(Calendar.YEAR));

          if (day - daysToDel < 1 || day - daysToDel + 7 <= lday) { // 是否出过当月触发的时间
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(mon - 1, cl.get(Calendar.YEAR)));
            // 不用担心"mon - 2"为负数，为负数会自动退回年份和月份，月份从0开始的
            cl.set(Calendar.MONTH, mon - 2);
            continue;
          }

          day -= daysToDel;

          if (daysToDel > 0) {
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, day);
            // 月份从0开始的
            cl.set(Calendar.MONTH, mon - 1);
            continue;
          }

        } else if (nthdayOfWeek != 0) { // 每个月的第几个星期的日期计算
          // 期待的符合cron要求的日期，desired
          int dow = daysOfWeek.last().intValue();
          // 当前日期
          int cdow = cl.get(Calendar.DAY_OF_WEEK);

          // 前一个出发点需要减去的天数
          int daysToDel = 0;
          if (cdow > dow) {
            daysToDel = cdow - dow;
          } else if (cdow < dow) {
            daysToDel = cdow + (7 - dow);
          }

          // 日期是否变动
          boolean dayShifted = false;
          if (daysToDel > 0) {
            dayShifted = true;
          }

          day -= daysToDel;
          // 月份的第几个星期
          int weekOfMonth = day / 7;
          if (day % 7 > 0) {
            weekOfMonth++;
          }

          daysToDel = (weekOfMonth - nthdayOfWeek) * 7;
          day -= daysToDel;
          if (daysToDel < 0 || day < 1) { // 当月的日期不满足，向前推一个月
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(mon - 1, cl.get(Calendar.YEAR)));
            cl.set(Calendar.MONTH, mon - 2);
            continue;
          } else if (daysToDel > 0 || dayShifted) {
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, day);
            // 月份从0开始
            cl.set(Calendar.MONTH, mon - 1);
            continue;
          }
        } else {
          // 当前日期
          int cdow = cl.get(Calendar.DAY_OF_WEEK);
          // 期待的符合cron要求的日期，desired
          int dow = daysOfWeek.first().intValue();

          st = daysOfWeek.headSet(Integer.valueOf(cdow), true);
          if (st != null && st.size() > 0) {
            dow = st.last().intValue();
          }

          // 向前推的天数
          int daysToDel = 0;
          if (cdow > dow) {
            daysToDel = cdow - dow;
          }
          if (cdow < dow) {
            daysToDel = cdow + (7 - dow);
          }


          if (day - daysToDel < 1) { // 向前推的时间小于1号，前推一个月
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(mon - 1, cl.get(Calendar.YEAR)));
            // 月份从0开始
            cl.set(Calendar.MONTH, mon - 2);
            continue;
          } else if (daysToDel > 0) { // 和满足要求的星期天数的日期交换日期？
            cl.set(Calendar.SECOND, 59);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.HOUR_OF_DAY, 23);
            cl.set(Calendar.DAY_OF_MONTH, day - daysToDel);
            cl.set(Calendar.MONTH, mon - 1);
            continue;
          }
        }
      } else { // dayOfWSpec && !dayOfMSpec
        UnsupportedOperationException unsupportedOperationException =
            new UnsupportedOperationException(
                " a day-of-week AND a day-of-month parameter is not implemented.");

        LOG.error("cron表达式不支持在日期和周上配置参数", unsupportedOperationException);
        throw unsupportedOperationException;

      }
      cl.set(Calendar.DAY_OF_MONTH, day);

      // 我们的月份计算时从1开始 的
      mon = cl.get(Calendar.MONTH) + 1;
      int year = cl.get(Calendar.YEAR);
      t = -1;

      // 防止cron表达式错误，导致无法触发
      if (year < MIN_YEAR) {
        return null;
      }

      // 获取月份.............................
      st = months.headSet(Integer.valueOf(mon), true);
      if (st != null && st.size() != 0) {
        t = mon;
        mon = st.last().intValue();
      } else {
        mon = months.last().intValue();
        year--;
      }
      if (mon != t) {
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(mon, year));
        cl.set(Calendar.MONTH, mon - 1);
        cl.set(Calendar.YEAR, year);
        continue;
      }
      cl.set(Calendar.MONTH, mon - 1);

      year = cl.get(Calendar.YEAR);
      t = -1;

      // 获取年份.................
      st = years.headSet(Integer.valueOf(year), true);
      if (st != null && st.size() != 0) {
        t = year;
        year = st.last().intValue();
      } else {
        return null; // 没在触发年份内
      }

      if (year != t) {
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(12, year));
        cl.set(Calendar.MONTH, 11);
        cl.set(Calendar.YEAR, year);
        continue;
      }
      cl.set(Calendar.YEAR, year);

      gotOne = true;
    }

    return cl.getTime();
  }

  /*
   * public . static void main(String[] args) throws FileNotFoundException, ParseException {
   * 
   * Scanner . scanner = new Scanner(new File("C:\\Users\\Administrator\\Desktop\\cron.txt"));
   * Calendar . now = Calendar.getInstance(); //3月1号比较特殊，往前推是2月，2月只有28或者29号，测试边界 now.set(2014, 2, 1,
   * 0, . 0, 0);
   * 
   * while . (scanner.hasNextLine()) { CronExpressionEx cronExpression = new
   * CronExpressionEx(scanner.nextLine()); System.err.print("job开始时间:" +
   * now.getTime().toLocaleString() . + "\t"); System.err.print("cron表达式:" + cronExpression + "\t");
   * Date . timeAfter = cronExpression.getTimeAfter(now.getTime()); System.err.print("job下一步执行时间:" +
   * (timeAfter . == null ? "" : timeAfter.toLocaleString()) + "\t"); Date timeBefore =
   * cronExpression.getTimeBefore(now.getTime()); System.err.println("job上一步执行时间:" + (timeBefore ==
   * null . ? "" : timeBefore.toLocaleString()) + "\t"); }
   * 
   * } .
   */

  /*
   * public . static void main(String[] args) throws ParseException { Calendar now =
   * Calendar.getInstance(); now.set(2014, 8, 12, 0, 0, 0);
   * 
   * // . CronExpressionEx cronExpression = new CronExpressionEx("0 15 10 ? * 6L"); //
   * CronExpressionEx . cronExpression = new CronExpressionEx("0 15 10 ? * 6#3"); CronExpressionEx
   * cronExpression . = new CronExpressionEx("0 0 0 LW * ?"); System.err.print("job开始时间:" +
   * now.getTime().toLocaleString() . + "\t"); System.err.print("cron表达式:" + cronExpression + "\t");
   * Date . timeAfter = cronExpression.getTimeAfter(now.getTime()); System.err.print("job下一步执行时间:" +
   * (timeAfter . == null ? "" : timeAfter.toLocaleString()) + "\t"); Date timeBefore =
   * cronExpression.getTimeBefore(now.getTime()); System.err.println("job上一步执行时间:" + (timeBefore ==
   * null . ? "" : timeBefore.toLocaleString()) + "\t"); }
   */
}
