
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.DdzRoomTypeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DdzRoomTypeMapper {

  @Select("select * from ddz_room_type")
  List<DdzRoomTypeDomain> selectAll();
}
