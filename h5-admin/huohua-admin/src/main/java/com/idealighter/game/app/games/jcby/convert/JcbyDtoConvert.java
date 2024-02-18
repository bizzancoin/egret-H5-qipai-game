package com.idealighter.game.app.games.jcby.convert;

import com.idealighter.game.app.games.jcby.dto.JcbyRoomDto;
import com.idealighter.game.app.games.jcby.dto.JcbyScenceDto;
import com.idealighter.game.service.games.jcby.bo.JcbyRoomBo;
import com.idealighter.game.service.games.jcby.bo.JcbyScenceBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface JcbyDtoConvert {

  JcbyRoomDto toJcbyRoomDto(JcbyRoomBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<JcbyRoomDto> toJcbyRoomDtos(List<JcbyRoomBo> bos);

  @Mapping(target = "maxNum", expression = "java(dto.getTableNum() * dto.getChair())")
  JcbyRoomBo toJcbyRoomBo(JcbyRoomDto dto);

  JcbyScenceDto toJcbyScenceDto(JcbyScenceBo bo);

  List<JcbyScenceDto> toJcbyScenceDtos(List<JcbyScenceBo> bos);
}
