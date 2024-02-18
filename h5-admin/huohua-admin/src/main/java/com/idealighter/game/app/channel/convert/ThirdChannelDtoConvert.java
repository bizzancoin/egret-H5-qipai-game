package com.idealighter.game.app.channel.convert;

import com.idealighter.game.app.channel.dto.ThirdChannelDto;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface ThirdChannelDtoConvert {

  ThirdChannelDto toThirdChannelDto(ThirdChannelBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<ThirdChannelDto> toThirdChannelDtos(List<ThirdChannelBo> bos);

  ThirdChannelBo toThirdChannelBo(ThirdChannelDto dto);
}
