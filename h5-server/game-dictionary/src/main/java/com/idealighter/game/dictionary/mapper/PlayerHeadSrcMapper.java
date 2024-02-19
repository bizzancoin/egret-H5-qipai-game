
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.PlayerHeadSrcDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface PlayerHeadSrcMapper {

  @Select("select * from player_head_src")
  List<PlayerHeadSrcDomain> selectAll();
}
