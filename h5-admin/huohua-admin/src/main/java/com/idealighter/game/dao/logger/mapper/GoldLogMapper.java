package com.idealighter.game.dao.logger.mapper;

import com.idealighter.game.dao.logger.po.GoldLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoldLogMapper {

  @Select("select exists(SELECT DISTINCT t.table_name FROM information_schema.TABLES t WHERE  t.table_schema = (SELECT DATABASE()) and  t.table_name = 'goldlog${yyyyMMdd}')")
  public int existTable(@Param("yyyyMMdd") String date);


  @Select({ "<script>", 
              "SELECT", 
                "*", 
              "FROM", 
                "goldlog${yyyyMMdd}", 
            "<where>", 
              "isPlayer = 1",
              "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
              "<if test = 'superId !=null'>and superId = #{superId} </if>", 
              "<if test = 'channelId !=null '>and channelId = #{channelId} </if>", 
            "</where>",
            "order by time desc, id desc", 
            "limit #{skip}, #{limit}", 
            "</script>" })
  List<GoldLog> selectByPage(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date,@Param("channelId")String channelId, @Param("skip") int skip, @Param("limit") int limit);

  @Select({ "<script>", 
            "SELECT", 
              "count(*)", 
            "FROM", 
              "goldlog${yyyyMMdd}", 
            "<where>",
              "isPlayer = 1", 
             "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
             "<if test = 'superId !=null'>and superId = #{superId} </if>", 
             "<if test = 'channelId !=null '>and channelId = #{channelId} </if>", 
            "</where>", 
            "</script>" })
  long count(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date,@Param("channelId")String channelId);
}
