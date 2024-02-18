package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WeishuwuTime;
import com.idealighter.game.dao.dic.po.WeishuwuTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WeishuwuTimeMapper {
  long countByExample(WeishuwuTimeExample example);

  int deleteByExample(WeishuwuTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WeishuwuTime record);

  int insertSelective(WeishuwuTime record);

  List<WeishuwuTime> selectByExample(WeishuwuTimeExample example);

  WeishuwuTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WeishuwuTime record,
      @Param("example") WeishuwuTimeExample example);

  int updateByExample(@Param("record") WeishuwuTime record,
      @Param("example") WeishuwuTimeExample example);

  int updateByPrimaryKeySelective(WeishuwuTime record);

  int updateByPrimaryKey(WeishuwuTime record);
}
