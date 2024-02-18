package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Itemattribute;
import com.idealighter.game.dao.dic.po.ItemattributeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemattributeMapper {
  long countByExample(ItemattributeExample example);

  int deleteByExample(ItemattributeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Itemattribute record);

  int insertSelective(Itemattribute record);

  List<Itemattribute> selectByExample(ItemattributeExample example);

  Itemattribute selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Itemattribute record,
      @Param("example") ItemattributeExample example);

  int updateByExample(@Param("record") Itemattribute record,
      @Param("example") ItemattributeExample example);

  int updateByPrimaryKeySelective(Itemattribute record);

  int updateByPrimaryKey(Itemattribute record);
}
