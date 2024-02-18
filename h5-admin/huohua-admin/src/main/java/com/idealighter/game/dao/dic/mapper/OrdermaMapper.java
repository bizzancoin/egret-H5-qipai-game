package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Orderma;
import com.idealighter.game.dao.dic.po.OrdermaExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaMapper {
  long countByExample(OrdermaExample example);

  int deleteByExample(OrdermaExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Orderma record);

  int insertSelective(Orderma record);

  List<Orderma> selectByExample(OrdermaExample example);

  Orderma selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Orderma record,
      @Param("example") OrdermaExample example);

  int updateByExample(@Param("record") Orderma record, @Param("example") OrdermaExample example);

  int updateByPrimaryKeySelective(Orderma record);

  int updateByPrimaryKey(Orderma record);
}
