
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyRoomTypeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyRoomTypeMapper {

  @Select("select * from jcby_room_type")
  List<JcbyRoomTypeDomain> selectAll();
}
