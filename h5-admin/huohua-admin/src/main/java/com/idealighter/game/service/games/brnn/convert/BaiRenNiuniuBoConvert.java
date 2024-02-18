package com.idealighter.game.service.games.brnn.convert;

import com.idealighter.game.dao.dic.po.BairenniuniuRoom;
import com.idealighter.game.service.games.brnn.bo.BaiRenNiuniuRoomBo;
import com.idealighter.utils.json.JsonUtil;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = JsonUtil.class)
public interface BaiRenNiuniuBoConvert {
  @Mapping(target = "betOptions",
      expression = "java(JsonUtil.fromJsonToList(pojo.getBetOptions(), Long.class))")
  BaiRenNiuniuRoomBo toNiuniuRoomBo(BairenniuniuRoom pojo);

  List<BaiRenNiuniuRoomBo> toBaiRenNiuniuRoomBos(List<BairenniuniuRoom> pojos);

  @Mapping(target = "betOptions", expression = "java(JsonUtil.toJson(bo.getBetOptions()))")
  BairenniuniuRoom toBaiRenNiuniuRoom(BaiRenNiuniuRoomBo bo);
}

