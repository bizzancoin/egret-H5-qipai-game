package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.TransferGoldRecord;
import com.idealighter.game.dao.data.po.TransferGoldRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransferGoldRecordMapper {
    long countByExample(TransferGoldRecordExample example);

    int deleteByExample(TransferGoldRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransferGoldRecord record);

    int insertSelective(TransferGoldRecord record);

    List<TransferGoldRecord> selectByExample(TransferGoldRecordExample example);

    TransferGoldRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransferGoldRecord record, @Param("example") TransferGoldRecordExample example);

    int updateByExample(@Param("record") TransferGoldRecord record, @Param("example") TransferGoldRecordExample example);

    int updateByPrimaryKeySelective(TransferGoldRecord record);

    int updateByPrimaryKey(TransferGoldRecord record);
}