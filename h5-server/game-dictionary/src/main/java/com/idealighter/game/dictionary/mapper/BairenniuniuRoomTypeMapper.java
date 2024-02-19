
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BairenniuniuRoomTypeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface BairenniuniuRoomTypeMapper {

  @Select("select * from bairenniuniu_room_type")
  List<BairenniuniuRoomTypeDomain> selectAll();
}
