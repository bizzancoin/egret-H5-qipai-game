
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BaccaratRoomMapper {

  @Select("select * from baccarat_room order by type")
  List<BaccaratRoomDomain> selectAll();

  @Select("select * from baccarat_room where id = #{id}")
  BaccaratRoomDomain selectById(@Param("id") int id);
}
