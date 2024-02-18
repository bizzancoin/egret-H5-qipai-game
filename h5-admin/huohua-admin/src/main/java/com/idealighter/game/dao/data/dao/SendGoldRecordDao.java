package com.idealighter.game.dao.data.dao;

import com.idealighter.game.dao.data.mapper.SendGoldRecordMapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SendGoldRecordDao extends SendGoldRecordMapper {
  @Select({ 
      "<script>", 
        "SELECT", "IFNULL(SUM(safe_gold), 0)", 
      "FROM ",
        "send_gold_record", 
      "<where>",
        "<if test='adminId!=null'>and admin_id = #{adminId} </if>",
        "<if test='playerId!=null'>and player_id = #{playerId} </if>",
        "<if test='superId!=null'>and super_id = #{superId} </if>",
        "<if test='startTime!=null'>and time &gt;= #{startTime} </if>",
        "<if test='endTime!=null'>and time &lt;= #{endTime} </if>", 
        "<if test='channelId!=null'>and channel_id = #{channelId} </if>",
      "</where>",
      "</script>" })
  
  long sumSafeGold(@Param("adminId") Integer adminId,@Param("playerId") Long playerId,
      @Param("superId") Long superId, @Param("startTime") Date startTime,
      @Param("endTime") Date endTime, @Param("channelId") String channelId);

}
