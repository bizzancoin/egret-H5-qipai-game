package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Welfare;
import com.idealighter.game.dao.dic.po.WelfareExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WelfareMapper {
  long countByExample(WelfareExample example);

  int deleteByExample(WelfareExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Welfare record);

  int insertSelective(Welfare record);

  List<Welfare> selectByExample(WelfareExample example);

  Welfare selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Welfare record,
      @Param("example") WelfareExample example);

  int updateByExample(@Param("record") Welfare record, @Param("example") WelfareExample example);

  int updateByPrimaryKeySelective(Welfare record);

  int updateByPrimaryKey(Welfare record);
}
