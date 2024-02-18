package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkIconRate;
import com.idealighter.game.dao.dic.po.SharkIconRateExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SharkIconRateMapper {
  long countByExample(SharkIconRateExample example);

  int deleteByExample(SharkIconRateExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SharkIconRate record);

  int insertSelective(SharkIconRate record);

  List<SharkIconRate> selectByExample(SharkIconRateExample example);

  SharkIconRate selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SharkIconRate record,
      @Param("example") SharkIconRateExample example);

  int updateByExample(@Param("record") SharkIconRate record,
      @Param("example") SharkIconRateExample example);

  int updateByPrimaryKeySelective(SharkIconRate record);

  int updateByPrimaryKey(SharkIconRate record);
}
