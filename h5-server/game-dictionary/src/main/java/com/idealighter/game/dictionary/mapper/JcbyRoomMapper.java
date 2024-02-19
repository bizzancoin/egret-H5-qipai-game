
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyRoomDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyRoomMapper {

  @Select("select * from jcby_room order by type")
  List<JcbyRoomDomain> selectAll();
}
