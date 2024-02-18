package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Sysconfig;
import com.idealighter.game.dao.dic.po.SysconfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysconfigMapper {
  long countByExample(SysconfigExample example);

  int deleteByExample(SysconfigExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Sysconfig record);

  int insertSelective(Sysconfig record);

  List<Sysconfig> selectByExample(SysconfigExample example);

  Sysconfig selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Sysconfig record,
      @Param("example") SysconfigExample example);

  int updateByExample(@Param("record") Sysconfig record,
      @Param("example") SysconfigExample example);

  int updateByPrimaryKeySelective(Sysconfig record);

  int updateByPrimaryKey(Sysconfig record);
}
