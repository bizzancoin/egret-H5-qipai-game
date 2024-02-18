package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.TongbiniuniuTime;
import com.idealighter.game.dao.dic.po.TongbiniuniuTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TongbiniuniuTimeMapper {
  long countByExample(TongbiniuniuTimeExample example);

  int deleteByExample(TongbiniuniuTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TongbiniuniuTime record);

  int insertSelective(TongbiniuniuTime record);

  List<TongbiniuniuTime> selectByExample(TongbiniuniuTimeExample example);

  TongbiniuniuTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") TongbiniuniuTime record,
      @Param("example") TongbiniuniuTimeExample example);

  int updateByExample(@Param("record") TongbiniuniuTime record,
      @Param("example") TongbiniuniuTimeExample example);

  int updateByPrimaryKeySelective(TongbiniuniuTime record);

  int updateByPrimaryKey(TongbiniuniuTime record);
}
