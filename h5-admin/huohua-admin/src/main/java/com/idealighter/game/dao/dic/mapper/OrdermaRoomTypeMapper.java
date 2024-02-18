package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OrdermaRoomType;
import com.idealighter.game.dao.dic.po.OrdermaRoomTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaRoomTypeMapper {
  long countByExample(OrdermaRoomTypeExample example);

  int deleteByExample(OrdermaRoomTypeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdermaRoomType record);

  int insertSelective(OrdermaRoomType record);

  List<OrdermaRoomType> selectByExample(OrdermaRoomTypeExample example);

  OrdermaRoomType selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdermaRoomType record,
      @Param("example") OrdermaRoomTypeExample example);

  int updateByExample(@Param("record") OrdermaRoomType record,
      @Param("example") OrdermaRoomTypeExample example);

  int updateByPrimaryKeySelective(OrdermaRoomType record);

  int updateByPrimaryKey(OrdermaRoomType record);
}
