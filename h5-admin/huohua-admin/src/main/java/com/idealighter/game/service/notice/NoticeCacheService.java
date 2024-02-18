package com.idealighter.game.service.notice;

import com.idealighter.game.service.notice.bo.NoticeBo;
import com.idealighter.game.service.notice.bo.NoticeCache;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.time.TimeUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeCacheService implements INoticeCacheService {

  private volatile Vector<NoticeCache> cacheNotices = new Vector<>();

  private volatile Vector<Integer> cacheIds = new Vector<>();


  @Autowired
  private INoticeService noticeService;

  @Override
  public List<NoticeCache> findCacheNotice() {
    synchronized (this) {
      return cacheNotices;
    }
  }

  @Override
  public void loadCacheNotice(boolean reload) {
    synchronized (this) {
      if (reload) {
        cacheNotices.clear();
        cacheIds.clear();
      }
      List<NoticeBo> bos = noticeService.findAllNotice();

      if (EmptyUtil.listIsNotEmpty(bos)) {
        Date now = TimeUtil.now();
        for (Iterator<NoticeBo> iterator = bos.iterator(); iterator.hasNext();) {

          NoticeBo bo = iterator.next();

          if (!cacheIds.contains(bo.getId())) {
            NoticeCache cache = new NoticeCache();

            cache.setColor(bo.getColor());
            cache.setContent(bo.getContent());
            cache.setEndTime(bo.getEndTime());
            cache.setId(bo.getId());
            cache.setIntervalTime(bo.getIntervalTime());
            cache.setIsMarquee(bo.getIsMarquee());
            cache.setIsNotice(bo.getIsNotice());
            cache.setStartTime(bo.getStartTime());
            cache.setTimes(bo.getTimes());
            cache.setSendTime(now);

            cacheNotices.add(cache);

            cacheIds.add(bo.getId());
          }
        }
      }
    }
  }

}
