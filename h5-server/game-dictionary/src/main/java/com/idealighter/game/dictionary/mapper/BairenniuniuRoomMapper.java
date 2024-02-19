
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface BairenniuniuRoomMapper {

  @Select("select * from bairenniuniu_room order by type")
  List<BairenniuniuRoomDomain> selectAll();
}
