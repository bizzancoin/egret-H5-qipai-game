package com.idealighter.game.app.game.convert;

import com.idealighter.game.app.game.dto.GameDto;
import com.idealighter.game.service.game.bo.GameBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface GameDtoConvert {
  GameDto toGameDto(GameBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<GameDto> toGameDtos(List<GameBo> bos);
  


  
}
