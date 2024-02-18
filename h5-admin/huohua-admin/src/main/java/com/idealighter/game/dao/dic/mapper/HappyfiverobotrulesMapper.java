package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Happyfiverobotrules;
import com.idealighter.game.dao.dic.po.HappyfiverobotrulesExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HappyfiverobotrulesMapper {
  long countByExample(HappyfiverobotrulesExample example);

  int deleteByExample(HappyfiverobotrulesExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Happyfiverobotrules record);

  int insertSelective(Happyfiverobotrules record);

  List<Happyfiverobotrules> selectByExample(HappyfiverobotrulesExample example);

  Happyfiverobotrules selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Happyfiverobotrules record,
      @Param("example") HappyfiverobotrulesExample example);

  int updateByExample(@Param("record") Happyfiverobotrules record,
      @Param("example") HappyfiverobotrulesExample example);

  int updateByPrimaryKeySelective(Happyfiverobotrules record);

  int updateByPrimaryKey(Happyfiverobotrules record);
}
