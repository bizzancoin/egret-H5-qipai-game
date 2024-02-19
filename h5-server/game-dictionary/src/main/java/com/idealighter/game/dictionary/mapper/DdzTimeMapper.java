
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.DdzTimeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface DdzTimeMapper {

  @Select("select * from ddz_time")
  List<DdzTimeDomain> selectAll();
}
