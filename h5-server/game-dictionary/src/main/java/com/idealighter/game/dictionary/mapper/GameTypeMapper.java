
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.GameTypeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface GameTypeMapper {

  @Select("select * from game_type")
  List<GameTypeDomain> selectAll();
}
