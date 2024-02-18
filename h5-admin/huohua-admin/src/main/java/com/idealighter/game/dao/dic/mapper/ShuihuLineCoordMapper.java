package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuLineCoord;
import com.idealighter.game.dao.dic.po.ShuihuLineCoordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuLineCoordMapper {
  long countByExample(ShuihuLineCoordExample example);

  int deleteByExample(ShuihuLineCoordExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuLineCoord record);

  int insertSelective(ShuihuLineCoord record);

  List<ShuihuLineCoord> selectByExample(ShuihuLineCoordExample example);

  ShuihuLineCoord selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuLineCoord record,
      @Param("example") ShuihuLineCoordExample example);

  int updateByExample(@Param("record") ShuihuLineCoord record,
      @Param("example") ShuihuLineCoordExample example);

  int updateByPrimaryKeySelective(ShuihuLineCoord record);

  int updateByPrimaryKey(ShuihuLineCoord record);
}
