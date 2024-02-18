package com.idealighter.game.service.games.baccarat.convert;

import com.idealighter.game.dao.dic.po.BaccaratRoom;
import com.idealighter.game.service.games.baccarat.bo.BaccaratRoomBo;
import com.idealighter.utils.json.JsonUtil;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = JsonUtil.class)
public interface BaccaratBoConvert {
  @Mapping(target = "betOptions",
      expression = "java(JsonUtil.fromJsonToList(pojo.getBetOptions(), Long.class))")
  BaccaratRoomBo toBaccaratRoomBo(BaccaratRoom pojo);

  List<BaccaratRoomBo> toBaccaratRoomBos(List<BaccaratRoom> pojos);

  @Mapping(target = "betOptions", expression = "java(JsonUtil.toJson(bo.getBetOptions()))")
  BaccaratRoom toBaccaratRoom(BaccaratRoomBo bo);
}