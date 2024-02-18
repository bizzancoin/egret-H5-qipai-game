package com.idealighter.game.service.notice.convert;

import com.idealighter.game.dao.manage.po.Notice;
import com.idealighter.game.service.notice.bo.NoticeBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface NoticeBoConvert {
  NoticeBo toNoticeBo(Notice notice);

  Notice toNotice(NoticeBo bo);

  List<NoticeBo> toNoticeBos(List<Notice> notices);


}
