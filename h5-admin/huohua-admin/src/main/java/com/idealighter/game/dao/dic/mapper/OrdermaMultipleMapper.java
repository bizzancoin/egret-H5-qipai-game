package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OrdermaMultiple;
import com.idealighter.game.dao.dic.po.OrdermaMultipleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaMultipleMapper {
  long countByExample(OrdermaMultipleExample example);

  int deleteByExample(OrdermaMultipleExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdermaMultiple record);

  int insertSelective(OrdermaMultiple record);

  List<OrdermaMultiple> selectByExample(OrdermaMultipleExample example);

  OrdermaMultiple selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdermaMultiple record,
      @Param("example") OrdermaMultipleExample example);

  int updateByExample(@Param("record") OrdermaMultiple record,
      @Param("example") OrdermaMultipleExample example);

  int updateByPrimaryKeySelective(OrdermaMultiple record);

  int updateByPrimaryKey(OrdermaMultiple record);
}
