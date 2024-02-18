package com.idealighter.game.service.recharge.convert;

import com.idealighter.game.dao.data.po.RechargeRecord;
import com.idealighter.game.service.recharge.bo.RechargeBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface RechargeBoConvert {
  
  RechargeBo toRechargeBo(RechargeRecord rechargeRecord);

  List<RechargeBo> toRechargeBos(List<RechargeRecord> rechargeRecords);
}
