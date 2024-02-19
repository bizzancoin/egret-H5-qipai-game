
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.GamesDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface GamesMapper {

  @Select("select * from games")
  List<GamesDomain> selectAll();
}
