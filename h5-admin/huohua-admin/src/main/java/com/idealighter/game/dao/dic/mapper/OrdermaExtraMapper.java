package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OrdermaExtra;
import com.idealighter.game.dao.dic.po.OrdermaExtraExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaExtraMapper {
  long countByExample(OrdermaExtraExample example);

  int deleteByExample(OrdermaExtraExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdermaExtra record);

  int insertSelective(OrdermaExtra record);

  List<OrdermaExtra> selectByExample(OrdermaExtraExample example);

  OrdermaExtra selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdermaExtra record,
      @Param("example") OrdermaExtraExample example);

  int updateByExample(@Param("record") OrdermaExtra record,
      @Param("example") OrdermaExtraExample example);

  int updateByPrimaryKeySelective(OrdermaExtra record);

  int updateByPrimaryKey(OrdermaExtra record);
}
