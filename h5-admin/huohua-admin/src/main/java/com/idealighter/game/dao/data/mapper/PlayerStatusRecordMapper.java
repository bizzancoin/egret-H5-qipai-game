package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerStatusRecord;
import com.idealighter.game.dao.data.po.PlayerStatusRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerStatusRecordMapper {
    long countByExample(PlayerStatusRecordExample example);

    int deleteByExample(PlayerStatusRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayerStatusRecord record);

    int insertSelective(PlayerStatusRecord record);

    List<PlayerStatusRecord> selectByExample(PlayerStatusRecordExample example);

    PlayerStatusRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayerStatusRecord record, @Param("example") PlayerStatusRecordExample example);

    int updateByExample(@Param("record") PlayerStatusRecord record, @Param("example") PlayerStatusRecordExample example);

    int updateByPrimaryKeySelective(PlayerStatusRecord record);

    int updateByPrimaryKey(PlayerStatusRecord record);
}