package com.idealighter.game.dao.logger.mapper;

import com.idealighter.game.dao.logger.po.GameGoldLog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface GameGoldLogMapper {

  @Select("select exists(SELECT DISTINCT t.table_name FROM information_schema.TABLES t WHERE  t.table_schema = (SELECT DATABASE()) and  t.table_name = 'gamegoldlog${yyyyMMdd}')")
  public int existTable(@Param("yyyyMMdd") String date);

  @Select({ "<script>", "SELECT", "*", "FROM", "gamegoldlog${yyyyMMdd}", "<where>", "isPlayer = 1",
      "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'channelId !=null '>and channelId = #{channelId} </if>", "</where>",
      "order by time desc, id desc", "limit #{skip}, #{limit}", "</script>" })
  List<GameGoldLog> selectByPage(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date, @Param("channelId") String channelId, @Param("skip") int skip,
      @Param("limit") int limit);

  @Select({ "<script>", "SELECT", "count(*)", "FROM", "gamegoldlog${yyyyMMdd}", "<where>",
      "isPlayer = 1", "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'channelId !=null '>and channelId = #{channelId} </if>", "</where>",
      "</script>" })
  long count(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date, @Param("channelId") String channelId);

  @Select({ "<script>", "SELECT", "IFNULL(SUM(betting), 0) AS betting ,",
      "IFNULL(SUM(bonus), 0) AS bonus ,", "IFNULL(SUM(profit), 0) AS profit ", "FROM",
      "gamegoldlog${yyyyMMdd}", "<where>", "isPlayer = 1",
      "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>",
      "<if test = 'channelId !=null '>and channelId = #{channelId} </if>", "</where>",
      "</script>" })
  @Results({
      @Result(column = "betting", property = "betting", jdbcType = JdbcType.BIGINT,
          javaType = Long.class),
      @Result(column = "bonus", property = "bonus", jdbcType = JdbcType.BIGINT,
          javaType = Long.class),
      @Result(column = "profit", property = "profit", jdbcType = JdbcType.BIGINT,
          javaType = Long.class) })
  Map<String, Long> sum(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date, @Param("channelId") String channelId);
}
