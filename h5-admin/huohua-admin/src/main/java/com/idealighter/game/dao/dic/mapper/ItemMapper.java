package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Item;
import com.idealighter.game.dao.dic.po.ItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
  long countByExample(ItemExample example);

  int deleteByExample(ItemExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Item record);

  int insertSelective(Item record);

  List<Item> selectByExample(ItemExample example);

  Item selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

  int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

  int updateByPrimaryKeySelective(Item record);

  int updateByPrimaryKey(Item record);
}
