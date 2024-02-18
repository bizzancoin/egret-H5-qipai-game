package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhFish;
import com.idealighter.game.dao.dic.po.WknhFishExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhFishMapper {
  long countByExample(WknhFishExample example);

  int deleteByExample(WknhFishExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhFish record);

  int insertSelective(WknhFish record);

  List<WknhFish> selectByExample(WknhFishExample example);

  WknhFish selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhFish record,
      @Param("example") WknhFishExample example);

  int updateByExample(@Param("record") WknhFish record, @Param("example") WknhFishExample example);

  int updateByPrimaryKeySelective(WknhFish record);

  int updateByPrimaryKey(WknhFish record);
}
