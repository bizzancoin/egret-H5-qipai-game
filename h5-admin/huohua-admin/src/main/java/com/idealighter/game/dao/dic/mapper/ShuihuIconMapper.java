package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuIcon;
import com.idealighter.game.dao.dic.po.ShuihuIconExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuIconMapper {
  long countByExample(ShuihuIconExample example);

  int deleteByExample(ShuihuIconExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuIcon record);

  int insertSelective(ShuihuIcon record);

  List<ShuihuIcon> selectByExample(ShuihuIconExample example);

  ShuihuIcon selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuIcon record,
      @Param("example") ShuihuIconExample example);

  int updateByExample(@Param("record") ShuihuIcon record,
      @Param("example") ShuihuIconExample example);

  int updateByPrimaryKeySelective(ShuihuIcon record);

  int updateByPrimaryKey(ShuihuIcon record);
}
