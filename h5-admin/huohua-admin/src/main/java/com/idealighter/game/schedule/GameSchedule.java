package com.idealighter.game.schedule;

import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.notice.INoticeCacheService;
import com.idealighter.game.service.notice.bo.NoticeCache;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.time.TimeUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GameSchedule implements CommandLineRunner {

  @Autowired
  private INoticeCacheService noticeCacheService;

  @Override
  public void run(String... arg0) throws Exception {
    noticeCacheService.loadCacheNotice(true);
  }

  /**
   * 定时加载通知.
   */
  @Scheduled(fixedDelayString = "${schedule.notice.load.period}")
  public void loadNoticeSchedule() {
    noticeCacheService.loadCacheNotice(true);
  }

  /**
   * 定时通知.
   */
  @Scheduled(fixedDelayString = "${schedule.notice.period}")
  public void noticeSchedule() {
    Date now = TimeUtil.now();
    List<NoticeCache> bos = noticeCacheService.findCacheNotice();
    if (EmptyUtil.listIsNotEmpty(bos)) {
      for (Iterator<NoticeCache> iterator = bos.iterator(); iterator.hasNext();) {
        NoticeCache noticeCache = iterator.next();
        if (noticeCache.getStartTime().before(now) && noticeCache.getEndTime().after(now)
            && noticeCache.getSendTime().before(now)) {
          int num = 0;


          if (noticeCache.getIsMarquee()) {
            num = 1;

          }
          if (noticeCache.getIsNotice()) {
            num = 2;
          }
          if (noticeCache.getIsNotice() && noticeCache.getIsMarquee()) {
            num = 3;
          }

          String content = noticeCache.getContent();
          int times = noticeCache.getTimes();
          String color = noticeCache.getColor();
          int intervalTime = noticeCache.getIntervalTime();
          if (num > 0) {
            RequestParamBuild paramBuild = new RequestParamBuild();
            paramBuild.add("type", num);
            paramBuild.add("content", content);
            paramBuild.add("color", color);
            paramBuild.add("times", times);
            paramBuild.add("interval", intervalTime);

            GameServerRequest.post(GameServerUrl.NOTICE_MARQUEE, paramBuild);
          }

        }
      }
    }
  }



}
