package com.idealighter.game.app.games.ddz.convert;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import com.idealighter.game.app.games.ddz.dto.DdzRoomDto;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;

@Mapper
public interface DdzDtoConvert {
  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<DdzRoomDto> toRoomDtos(List<DdzRoomBo> bos);

  @Mapping(target = "maxNum", expression = "java(dto.getTableNum() * dto.getChair())")
  DdzRoomBo toRoomBo(DdzRoomDto dto);
}
