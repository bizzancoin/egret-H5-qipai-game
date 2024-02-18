package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.RoomPrizePoolGroup;
import com.idealighter.game.dao.data.po.RoomPrizePoolGroupExample;
import com.idealighter.game.dao.data.po.RoomPrizePoolGroupKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoomPrizePoolGroupMapper {
  long countByExample(RoomPrizePoolGroupExample example);

  int deleteByExample(RoomPrizePoolGroupExample example);

  int deleteByPrimaryKey(RoomPrizePoolGroupKey key);

  int insert(RoomPrizePoolGroup record);

  int insertSelective(RoomPrizePoolGroup record);

  List<RoomPrizePoolGroup> selectByExampleWithBLOBs(RoomPrizePoolGroupExample example);

  List<RoomPrizePoolGroup> selectByExample(RoomPrizePoolGroupExample example);

  RoomPrizePoolGroup selectByPrimaryKey(RoomPrizePoolGroupKey key);

  int updateByExampleSelective(@Param("record") RoomPrizePoolGroup record,
      @Param("example") RoomPrizePoolGroupExample example);

  int updateByExampleWithBLOBs(@Param("record") RoomPrizePoolGroup record,
      @Param("example") RoomPrizePoolGroupExample example);

  int updateByExample(@Param("record") RoomPrizePoolGroup record,
      @Param("example") RoomPrizePoolGroupExample example);

  int updateByPrimaryKeySelective(RoomPrizePoolGroup record);

  int updateByPrimaryKeyWithBLOBs(RoomPrizePoolGroup record);

  int updateByPrimaryKey(RoomPrizePoolGroup record);
}
