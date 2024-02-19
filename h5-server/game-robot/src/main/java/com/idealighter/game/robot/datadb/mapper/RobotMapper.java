
package com.idealighter.game.robot.datadb.mapper;

import com.idealighter.game.robot.datadb.domain.RobotDomain;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 * RobotMapper
 * 
 * @date 2015年10月29日 下午12:58:46
 *
 */
public interface RobotMapper {

  @Select("select * from player_main where register_type = 0")
  @Results({ @Result(column = "id", property = "id"),
      @Result(column = "super_id", property = "superId"),
      @Result(column = "user_name", property = "userName"),
      @Result(column = "player_name", property = "playerName"),
      @Result(column = "locked", property = "locked", jdbcType = JdbcType.BIT) })
  List<RobotDomain> selectAll();
}
