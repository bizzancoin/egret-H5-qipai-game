
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BairenniuniuTimeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface BairenniuniuTimeMapper {

  @Select("select * from bairenniuniu_time")
  List<BairenniuniuTimeDomain> selectAll();
}
