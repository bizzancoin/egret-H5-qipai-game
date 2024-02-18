package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FoursLogType;
import com.idealighter.game.dao.dic.po.FoursLogTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FoursLogTypeMapper {
  long countByExample(FoursLogTypeExample example);

  int deleteByExample(FoursLogTypeExample example);

  int deleteByPrimaryKey(Integer planid);

  int insert(FoursLogType record);

  int insertSelective(FoursLogType record);

  List<FoursLogType> selectByExample(FoursLogTypeExample example);

  FoursLogType selectByPrimaryKey(Integer planid);

  int updateByExampleSelective(@Param("record") FoursLogType record,
      @Param("example") FoursLogTypeExample example);

  int updateByExample(@Param("record") FoursLogType record,
      @Param("example") FoursLogTypeExample example);

  int updateByPrimaryKeySelective(FoursLogType record);

  int updateByPrimaryKey(FoursLogType record);
}
