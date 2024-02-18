package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhStrategy;
import com.idealighter.game.dao.dic.po.WknhStrategyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhStrategyMapper {
  long countByExample(WknhStrategyExample example);

  int deleteByExample(WknhStrategyExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhStrategy record);

  int insertSelective(WknhStrategy record);

  List<WknhStrategy> selectByExample(WknhStrategyExample example);

  WknhStrategy selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhStrategy record,
      @Param("example") WknhStrategyExample example);

  int updateByExample(@Param("record") WknhStrategy record,
      @Param("example") WknhStrategyExample example);

  int updateByPrimaryKeySelective(WknhStrategy record);

  int updateByPrimaryKey(WknhStrategy record);
}
