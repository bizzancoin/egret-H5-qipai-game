package com.idealighter.game.dao.manage.mapper;

import com.idealighter.game.dao.manage.po.ManageLog;
import com.idealighter.game.dao.manage.po.ManageLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ManageLogMapper {
  long countByExample(ManageLogExample example);

  int deleteByExample(ManageLogExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ManageLog record);

  int insertSelective(ManageLog record);

  List<ManageLog> selectByExample(ManageLogExample example);

  ManageLog selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ManageLog record,
      @Param("example") ManageLogExample example);

  int updateByExample(@Param("record") ManageLog record,
      @Param("example") ManageLogExample example);

  int updateByPrimaryKeySelective(ManageLog record);

  int updateByPrimaryKey(ManageLog record);
}
