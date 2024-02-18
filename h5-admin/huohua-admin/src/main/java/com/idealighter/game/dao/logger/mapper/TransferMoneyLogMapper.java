package com.idealighter.game.dao.logger.mapper;

import com.idealighter.game.dao.logger.po.TransferMoneyGoldLog;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransferMoneyLogMapper {

  @Select("select exists(SELECT DISTINCT t.table_name FROM information_schema.TABLES t WHERE  t.table_schema = (SELECT DATABASE()) and  t.table_name = 'transfermoneylog${yyyyMM}')")
  public int existTable(@Param("yyyyMM") String date);

  @Select({ "<script>", "SELECT", "*", "FROM", "transfermoneylog${yyyyMM}", "<where>",
      "isPlayer = 1", "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'flag !=null'>and flag = #{flag} </if>",
      "<if test = 'channelId !=null '>and channelId = #{channelId} </if>",
      "<if test = 'orderNo !=null'>and orderNo = #{orderNo} </if>", "</where>",
      "order by time desc, id desc", "limit #{skip}, #{limit}", "</script>" })
  List<TransferMoneyGoldLog> selectByPage(@Param("playerId") Long playerId,
      @Param("superId") Long superId, @Param("yyyyMM") String date, @Param("flag") Byte flag,
      @Param("channelId") String channelId, @Param("orderNo") String orderNo,
      @Param("skip") int skip, @Param("limit") int limit);

  @Select({ "<script>", "SELECT", "count(*)", "FROM", "transfermoneylog${yyyyMM}", "<where>",
      "isPlayer = 1", "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'flag !=null'>and flag = #{flag} </if>",
      "<if test = 'channelId !=null'>and channelId = #{channelId} </if>",
      "<if test = 'orderNo !=null'>and orderNo = #{orderNo} </if>", "</where>", "</script>" })
  long count(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMM") String date, @Param("flag") Byte flag, @Param("channelId") String channelId,
      @Param("orderNo") String orderNo);

  @Select({ "<script>", "SELECT", "IFNULL(SUM(price), 0) AS price", "FROM",
      "transfermoneylog${yyyyMM}", "<where>", "isPlayer = 1",
      "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'flag !=null'>and flag = #{flag} </if>",
      "<if test = 'channelId !=null'>and channelId = #{channelId} </if>",
      "<if test = 'orderNo !=null'>and orderNo = #{orderNo} </if>",
      "<if test='startTime!=null'>and time &gt;= #{startTime} </if>",
      "<if test='endTime!=null'>and time &lt;= #{endTime} </if>", 
      "</where>", "</script>" })
  long sumPrice(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMM") String date, @Param("flag") Byte flag, @Param("channelId") String channelId,
      @Param("orderNo") String orderNo, @Param("startTime") Date startTime,
      @Param("endTime") Date endTime);
}
