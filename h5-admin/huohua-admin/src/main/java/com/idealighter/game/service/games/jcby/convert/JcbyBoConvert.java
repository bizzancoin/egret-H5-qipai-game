package com.idealighter.game.service.games.jcby.convert;

import com.idealighter.game.common.util.StringArrayUtils;
import com.idealighter.game.dao.dic.po.JcbyRoom;
import com.idealighter.game.dao.dic.po.JcbyScence;
import com.idealighter.game.service.games.jcby.bo.JcbyRoomBo;
import com.idealighter.game.service.games.jcby.bo.JcbyScenceBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = StringArrayUtils.class)
public interface JcbyBoConvert {

  @Mapping(target = "scences",
      expression = "java(StringArrayUtils.toIntegerArray(pojo.getScences(), \",\"))")
  JcbyRoomBo toJcbyRoomBo(JcbyRoom pojo);

  List<JcbyRoomBo> toJcbyRoomBos(List<JcbyRoom> pojos);

  @Mapping(target = "scences",
      expression = "java(StringArrayUtils.fromIntegerArray(bo.getScences(), \",\"))")
  JcbyRoom toJcbyRoom(JcbyRoomBo bo);

  JcbyScenceBo toJcbyScence(JcbyScence pojo);

  List<JcbyScenceBo> toJcbyScences(List<JcbyScence> pojos);
}
