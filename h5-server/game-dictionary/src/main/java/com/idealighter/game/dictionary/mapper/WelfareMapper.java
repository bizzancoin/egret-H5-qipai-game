
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.WelfareDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface WelfareMapper {

  @Select("select * from welfare")
  List<WelfareDomain> selectAll();
}
