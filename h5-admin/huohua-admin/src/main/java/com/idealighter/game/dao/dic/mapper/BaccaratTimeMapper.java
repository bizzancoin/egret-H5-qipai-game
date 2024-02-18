package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BaccaratTime;
import com.idealighter.game.dao.dic.po.BaccaratTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaccaratTimeMapper {
  long countByExample(BaccaratTimeExample example);

  int deleteByExample(BaccaratTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(BaccaratTime record);

  int insertSelective(BaccaratTime record);

  List<BaccaratTime> selectByExample(BaccaratTimeExample example);

  BaccaratTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") BaccaratTime record,
      @Param("example") BaccaratTimeExample example);

  int updateByExample(@Param("record") BaccaratTime record,
      @Param("example") BaccaratTimeExample example);

  int updateByPrimaryKeySelective(BaccaratTime record);

  int updateByPrimaryKey(BaccaratTime record);
}
