
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyStrategyDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyStrategyMapper {

  @Select("select * from jcby_strategy")
  List<JcbyStrategyDomain> selectAll();
}
