
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.JcbyCurveDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface JcbyCurveMapper {

  @Select("select * from jcby_curve")
  List<JcbyCurveDomain> selectAll();
}
