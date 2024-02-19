
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BaccaratRoomTypeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BaccaratRoomTypeMapper {

  @Select("select * from baccarat_room_type")
  List<BaccaratRoomTypeDomain> selectAll();
}
