package com.idealighter.game.core.dao.mapper;

import com.idealighter.game.core.dao.TransferTotal;
import com.idealighter.game.core.dao.generate.mapper.TransferGoldRecordDomainMapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TransferGoldRecordMapper extends TransferGoldRecordDomainMapper {
  @Select({ "select sum(change_safe_gold) as total ,type, opposite_type from",
      "transfer_gold_record ", "where", "player_id = #{playerId,jdbcType=BIGINT} ",
      "and create_time >= #{beginTime,jdbcType=TIMESTAMP}",
      "and create_time < #{endTime,jdbcType=TIMESTAMP}", "group by type,opposite_type" })
  public List<TransferTotal> sumTotal(@Param("playerId") long playerId,
      @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
