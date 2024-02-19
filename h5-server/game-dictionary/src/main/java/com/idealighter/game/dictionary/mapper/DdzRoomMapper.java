
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.DdzRoomDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DdzRoomMapper {

  @Select("select * from ddz_room")
  List<DdzRoomDomain> selectAll();
}
