
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyScenceDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyScenceMapper {

  @Select("select * from jcby_scence")
  List<JcbyScenceDomain> selectAll();
}
