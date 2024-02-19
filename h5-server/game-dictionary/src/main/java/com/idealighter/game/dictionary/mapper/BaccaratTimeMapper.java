
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.BaccaratTimeDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BaccaratTimeMapper {

  @Select("select * from baccarat_time")
  List<BaccaratTimeDomain> selectAll();
}
