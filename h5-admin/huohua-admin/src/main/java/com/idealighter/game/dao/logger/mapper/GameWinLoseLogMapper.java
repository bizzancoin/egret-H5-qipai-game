package com.idealighter.game.dao.logger.mapper;

import com.idealighter.game.dao.logger.po.SimpleWinLoseLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameWinLoseLogMapper {
  @Select("<script>"
      + "SELECT playerId, sum(chips) as winLose FROM game${gameId}winloselog${yyyyMMdd} where gold != 0 and playerId"
      + " in(" //
      + "<foreach collection =\"playerIds\" item=\"item\" separator =\",\"> "
      + "#{item,jdbcType=BIGINT}" //
      + "</foreach >" //
      + ")" //
      + "group by playerId" //
      + "</script>") //
  public List<SimpleWinLoseLog> findPlayerWinLoseOfGame(@Param("gameId") Integer gameId,
      @Param("yyyyMMdd") String date, @Param("playerIds") List<Long> playerIds);

  @Select("select exists(SELECT DISTINCT t.table_name FROM information_schema.TABLES t WHERE  t.table_schema = (SELECT DATABASE()) and  t.table_name = 'game${gameId}winloselog${yyyyMMdd}')")
  public int existTable(@Param("gameId") Integer gameId, @Param("yyyyMMdd") String date);

  @Select("SELECT playerId, sum(chips) as winLose FROM game${gameId}winloselog${yyyyMMdd} where gold != 0 and playerId = #{playerId,jdbcType=BIGINT} group by playerId")
  public SimpleWinLoseLog findWinLoseOfGameByPlayerId(@Param("gameId") Integer gameId,
      @Param("yyyyMMdd") String date, @Param("playerId") Long playerId);
}
