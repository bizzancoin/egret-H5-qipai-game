package com.idealighter.game.admin.roomctl.convert;

import com.idealighter.game.admin.roomctl.dto.GoldRangePrizePoolDto;
import com.idealighter.game.core.service.prizepool.struct.room.GoldRangeRoomPrizePool;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { StrategyDtoConvert.class })
public interface GoldRangePrizePoolDtoConvert {
  GoldRangePrizePoolDtoConvert INSTANCE = Mappers.getMapper(GoldRangePrizePoolDtoConvert.class);

  @Mapping(target = "prize", expression = "java(po.getPrize().get())")
  GoldRangePrizePoolDto toGoldRangePrizePoolDto(GoldRangeRoomPrizePool po);

  List<GoldRangePrizePoolDto> toGoldRangePrizePoolDto(List<GoldRangeRoomPrizePool> list);
}
