package com.idealighter.game.app.notice.convert;

import com.idealighter.game.app.notice.dto.NoticeDto;
import com.idealighter.game.service.notice.bo.NoticeBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface NoticeDtoConvert {
  NoticeDto toNoticeDto(NoticeBo bo);
  
  NoticeBo toNoticeBo(NoticeDto dto);

  List<NoticeDto> toNoticeDtos(List<NoticeBo> bos);
}
