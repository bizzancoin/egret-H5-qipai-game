package com.idealighter.game.admin.roomctl.convert;

import com.idealighter.game.admin.roomctl.dto.DefaultPrizePoolDto;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePool;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { StrategyDtoConvert.class })
public interface DefaultPrizePoolDtoConvert {
  DefaultPrizePoolDtoConvert INSTANCE = Mappers.getMapper(DefaultPrizePoolDtoConvert.class);

  @Mapping(target = "prize", expression = "java(roomPrizePool.getPrize().get())")
  DefaultPrizePoolDto roomPrizePoolTodefaultPrizePoolDto(RoomPrizePool roomPrizePool);
}
