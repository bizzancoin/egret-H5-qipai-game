package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Itemeffect;
import com.idealighter.game.dao.dic.po.ItemeffectExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemeffectMapper {
  long countByExample(ItemeffectExample example);

  int deleteByExample(ItemeffectExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Itemeffect record);

  int insertSelective(Itemeffect record);

  List<Itemeffect> selectByExample(ItemeffectExample example);

  Itemeffect selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Itemeffect record,
      @Param("example") ItemeffectExample example);

  int updateByExample(@Param("record") Itemeffect record,
      @Param("example") ItemeffectExample example);

  int updateByPrimaryKeySelective(Itemeffect record);

  int updateByPrimaryKey(Itemeffect record);
}
