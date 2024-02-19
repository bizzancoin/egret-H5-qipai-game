package com.idealighter.game.logger.dao.mapper;

import com.idealighter.game.logger.dao.po.GameGoldLogPo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameGoldLogMapper {

  @Select("select exists(SELECT DISTINCT t.table_name FROM information_schema.TABLES t "
      + "WHERE t.table_name = 'gamegoldlog${yyyyMMdd}')")
  public int existTable(@Param("yyyyMMdd") String date);

  @Select({ "<script>", "SELECT", "*", "FROM", "gamegoldlog${yyyyMMdd}", "<where>", "isPlayer = 1",
      "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>", "</where>",
      "order by time desc, id desc", "limit #{skip}, #{limit}", "</script>" })
  List<GameGoldLogPo> selectByPage(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date, @Param("skip") int skip, @Param("limit") int limit);

  @Select({ "<script>", "SELECT", "*", "FROM", "gamegoldlog${yyyyMMdd}",
           "<where>", "isPlayer = 1",
               "<if test = 'channelId !=null'>and channelId = #{channelId} </if>", 
                 "and id &gt; #{id} ", 
              "</where>", 
               "order by id ",
                   "limit  #{limit}",
             "</script>" })
  List<GameGoldLogPo> selectByLimit(@Param("id") Integer id, @Param("channelId") String channelId,
      @Param("yyyyMMdd") String date, @Param("limit") int limit);

  @Select({ "<script>", "SELECT", "count(*)", "FROM", "gamegoldlog${yyyyMMdd}", "<where>",
      "isPlayer = 1", "<if test = 'playerId !=null'>and playerId = #{playerId} </if>",
      "<if test = 'superId !=null'>and superId = #{superId} </if>", "</where>", "</script>" })
  long count(@Param("playerId") Long playerId, @Param("superId") Long superId,
      @Param("yyyyMMdd") String date);
}
