package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.SendGoldRecord;
import com.idealighter.game.dao.data.po.SendGoldRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendGoldRecordMapper {
    long countByExample(SendGoldRecordExample example);

    int deleteByExample(SendGoldRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendGoldRecord record);

    int insertSelective(SendGoldRecord record);

    List<SendGoldRecord> selectByExample(SendGoldRecordExample example);

    SendGoldRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendGoldRecord record, @Param("example") SendGoldRecordExample example);

    int updateByExample(@Param("record") SendGoldRecord record, @Param("example") SendGoldRecordExample example);

    int updateByPrimaryKeySelective(SendGoldRecord record);

    int updateByPrimaryKey(SendGoldRecord record);
}