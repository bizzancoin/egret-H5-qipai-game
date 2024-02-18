package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OrdermaRoom;
import com.idealighter.game.dao.dic.po.OrdermaRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrdermaRoomMapper {
  long countByExample(OrdermaRoomExample example);

  int deleteByExample(OrdermaRoomExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OrdermaRoom record);

  int insertSelective(OrdermaRoom record);

  List<OrdermaRoom> selectByExample(OrdermaRoomExample example);

  OrdermaRoom selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OrdermaRoom record,
      @Param("example") OrdermaRoomExample example);

  int updateByExample(@Param("record") OrdermaRoom record,
      @Param("example") OrdermaRoomExample example);

  int updateByPrimaryKeySelective(OrdermaRoom record);

  int updateByPrimaryKey(OrdermaRoom record);
}
