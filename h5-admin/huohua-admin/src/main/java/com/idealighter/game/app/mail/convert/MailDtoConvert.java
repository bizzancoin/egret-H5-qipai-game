package com.idealighter.game.app.mail.convert;

import com.idealighter.game.app.mail.dto.MailDto;
import com.idealighter.game.service.mail.bo.MailBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface MailDtoConvert {

  MailBo toMailBo(MailDto dto);

  MailDto toMailDto(MailBo bo);

  List<MailDto> toMailDtos(List<MailBo> bos);
}
