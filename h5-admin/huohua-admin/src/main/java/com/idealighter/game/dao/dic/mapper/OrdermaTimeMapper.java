package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OrdermaTime;
import com.idealighter.game.dao.dic.po.OrdermaTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaTimeMapper {
  long countByExample(OrdermaTimeExample example);

  int deleteByExample(OrdermaTimeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdermaTime record);

  int insertSelective(OrdermaTime record);

  List<OrdermaTime> selectByExample(OrdermaTimeExample example);

  OrdermaTime selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdermaTime record,
      @Param("example") OrdermaTimeExample example);

  int updateByExample(@Param("record") OrdermaTime record,
      @Param("example") OrdermaTimeExample example);

  int updateByPrimaryKeySelective(OrdermaTime record);

  int updateByPrimaryKey(OrdermaTime record);
}
