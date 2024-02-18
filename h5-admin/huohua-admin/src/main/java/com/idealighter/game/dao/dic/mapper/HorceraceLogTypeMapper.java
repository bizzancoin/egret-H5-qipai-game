package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.HorceraceLogType;
import com.idealighter.game.dao.dic.po.HorceraceLogTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HorceraceLogTypeMapper {
  long countByExample(HorceraceLogTypeExample example);

  int deleteByExample(HorceraceLogTypeExample example);

  int deleteByPrimaryKey(Integer planid);

  int insert(HorceraceLogType record);

  int insertSelective(HorceraceLogType record);

  List<HorceraceLogType> selectByExample(HorceraceLogTypeExample example);

  HorceraceLogType selectByPrimaryKey(Integer planid);

  int updateByExampleSelective(@Param("record") HorceraceLogType record,
      @Param("example") HorceraceLogTypeExample example);

  int updateByExample(@Param("record") HorceraceLogType record,
      @Param("example") HorceraceLogTypeExample example);

  int updateByPrimaryKeySelective(HorceraceLogType record);

  int updateByPrimaryKey(HorceraceLogType record);
}
