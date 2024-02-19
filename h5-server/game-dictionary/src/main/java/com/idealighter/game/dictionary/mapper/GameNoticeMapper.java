
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.GameNoticeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface GameNoticeMapper {

  @Select("select * from game_notice")
  List<GameNoticeDomain> selectAll();
}
