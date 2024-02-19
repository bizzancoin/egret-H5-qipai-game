package com.idealighter.game.core.dao.convert;

import com.idealighter.game.core.dao.generate.domain.RechargeRecordDomain;

import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RechargeRecordConvert {

  RechargeRecordConvert INSTANCE = Mappers.getMapper(RechargeRecordConvert.class);

  @Mapping(target = "id", ignore = true)
  public RechargeRecordDomain newInstance(String tradeNo, String outsideNo, Byte payType,
      Long playerId, Integer rechargeItemId, Long gold, Long sendGold, Integer price,
      Date createTime, Date payTime, Byte state);
}
