package com.idealighter.game.app.common.convert;

import com.idealighter.game.app.common.dto.ChannelSimpleDto;
import com.idealighter.game.app.common.dto.GameListItemDto;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;
import com.idealighter.game.service.common.bo.GameListItemBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface CommonDtoConvert {
  public GameListItemDto bo2dto(GameListItemBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  public List<GameListItemDto> bo2dto(List<GameListItemBo> bos);

  ChannelSimpleDto toChannelSimpleDto(ThirdChannelBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<ChannelSimpleDto> toChannelSimpleDtos(List<ThirdChannelBo> bos);
}
