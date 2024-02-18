package com.idealighter.game.service.notice;

import com.idealighter.game.service.notice.bo.NoticeCache;

import java.util.List;

public interface INoticeCacheService {

  List<NoticeCache> findCacheNotice();

  void loadCacheNotice(boolean reload);
}
