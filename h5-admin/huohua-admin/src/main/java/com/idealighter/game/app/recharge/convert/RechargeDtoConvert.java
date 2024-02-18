package com.idealighter.game.app.recharge.convert;

import com.idealighter.game.app.recharge.dto.RechargeDto;
import com.idealighter.game.service.recharge.bo.RechargeBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface RechargeDtoConvert {
  RechargeDto toRechargeDto(RechargeBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<RechargeDto> toRechargeDtos(List<RechargeBo> bos);
}
