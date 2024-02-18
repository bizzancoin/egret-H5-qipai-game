package com.idealighter.game.app.games.brnn.convert;

import com.idealighter.game.app.games.brnn.dto.BaiRenNiuniuRoomDto;
import com.idealighter.game.service.games.brnn.bo.BaiRenNiuniuRoomBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface BaiRenNiuniuDtoConvert {

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<BaiRenNiuniuRoomDto> toNiuniuRoomDtos(List<BaiRenNiuniuRoomBo> bos);

  @Mapping(target = "maxNum", expression = "java(dto.getTableNum() * dto.getChair())")
  BaiRenNiuniuRoomBo toNiuniuRoomBo(BaiRenNiuniuRoomDto dto);

}
