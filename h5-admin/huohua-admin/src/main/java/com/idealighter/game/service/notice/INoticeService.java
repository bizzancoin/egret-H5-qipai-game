package com.idealighter.game.service.notice;

import com.idealighter.game.service.notice.bo.NoticeBo;

import java.util.List;

public interface INoticeService {
  List<NoticeBo> findAllNotice();

  void save(NoticeBo bo);

  void delete(int id);

}
