
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.WordsDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface WordsMapper {

  @Select("select * from words")
  List<WordsDomain> selectAll();
}
