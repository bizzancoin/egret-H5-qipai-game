
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyFishDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyFishMapper {

  @Select("select * from jcby_fish")
  List<JcbyFishDomain> selectAll();
}
