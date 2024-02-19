package com.idealighter.game.admin.common.controller.convert;

import com.idealighter.game.admin.common.controller.dto.RoomListDto;
import com.idealighter.game.core.service.games.common.AbstractRoom;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomListDtoConvert {
  RoomListDtoConvert INSTANCE = Mappers.getMapper(RoomListDtoConvert.class);

  public RoomListDto toRoomListDto(AbstractRoom room);

  public List<RoomListDto> toRoomListDto(List<AbstractRoom> room);
}
