package com.idealighter.game.app.games.baccarat.convert;

import com.idealighter.game.app.games.baccarat.dto.BaccaratRoomDto;
import com.idealighter.game.service.games.baccarat.bo.BaccaratRoomBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BaccaratDtoConvert {
  BaccaratRoomDto toBaccaratRoomDto(BaccaratRoomBo bo);

  @Mapping(target = "maxNum", expression = "java(dto.getTableNum() * dto.getChair())")
  BaccaratRoomBo toBaccaratRoomBo(BaccaratRoomDto dto);

  List<BaccaratRoomDto> toBaccaratRoomDtos(List<BaccaratRoomBo> bos);
}
