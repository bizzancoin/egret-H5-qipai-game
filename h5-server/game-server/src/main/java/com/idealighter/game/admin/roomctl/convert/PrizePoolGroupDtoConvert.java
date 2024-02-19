package com.idealighter.game.admin.roomctl.convert;

import com.idealighter.game.admin.roomctl.dto.PrizePoolGroupDto;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePoolGroup;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { GoldRangePrizePoolDtoConvert.class, DefaultPrizePoolDtoConvert.class })
public interface PrizePoolGroupDtoConvert {
  PrizePoolGroupDtoConvert INSTANCE = Mappers.getMapper(PrizePoolGroupDtoConvert.class);

  @Mapping(target = "goldPrizePoolGroups", source = "goldRangePrizePools")
  PrizePoolGroupDto toPrizePoolGroupDto(RoomPrizePoolGroup group);
}
