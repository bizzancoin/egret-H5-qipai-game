
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.LevelExpDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface LevelExpMapper {

  @Select("select level, exp from level_exp order by level")
  List<LevelExpDomain> selectAll();
}
