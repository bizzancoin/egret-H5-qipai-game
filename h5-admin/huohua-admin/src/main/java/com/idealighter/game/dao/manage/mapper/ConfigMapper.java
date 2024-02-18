package com.idealighter.game.dao.manage.mapper;

import com.idealighter.game.dao.manage.po.Config;
import com.idealighter.game.dao.manage.po.ConfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
  long countByExample(ConfigExample example);

  int deleteByExample(ConfigExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Config record);

  int insertSelective(Config record);

  List<Config> selectByExample(ConfigExample example);

  Config selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Config record,
      @Param("example") ConfigExample example);

  int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

  int updateByPrimaryKeySelective(Config record);

  int updateByPrimaryKey(Config record);
}
