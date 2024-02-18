package com.idealighter.game.app.search.convert;

import com.idealighter.game.app.search.dto.SearchPlayerDto;
import com.idealighter.game.service.player.bo.PlayerBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SearchDtoConvert {

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "playerSuperId", source = "superId")
  SearchPlayerDto toSearchPlayerDto(PlayerBo bo);

  List<SearchPlayerDto> toSearchPlayerDtos(List<PlayerBo> bos);
}
