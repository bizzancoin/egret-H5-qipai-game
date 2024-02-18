package com.idealighter.game.dao.data.dao;

import com.idealighter.game.dao.data.mapper.TransferGoldRecordMapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransferGoldRecordDao extends TransferGoldRecordMapper {

  @Select({ "<script>", "SELECT", "IFNULL(sum(change_safe_gold),0)", "FROM ",
      "transfer_gold_record", "<where>",
      "<if test='playerId!=null'>and player_id = #{playerId} </if>",
      "<if test='superId!=null'>and player_super_id = #{superId} </if>",
      "<if test='playerType!=null'>and player_type = #{playerType} </if>",
      "<if test='type!=null'>and type = #{type} </if>",
      "<if test='oppositeType!=null'>and opposite_type = #{oppositeType} </if>",
      "<if test='startTime!=null'>and create_time &gt;= #{startTime} </if>",
      "<if test='endTime!=null'>and create_time &lt;= #{endTime} </if>", "</where>",
      "</script>" })
  long countTotalGold(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("playerType") Byte playerType, @Param("type") Byte type,
      @Param("oppositeType") Byte oppositeType, @Param("startTime") Date startTime,
      @Param("endTime") Date endTime);
}
