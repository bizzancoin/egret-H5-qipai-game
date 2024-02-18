package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyFish;
import com.idealighter.game.dao.dic.po.LkpyFishExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LkpyFishMapper {
  long countByExample(LkpyFishExample example);

  int deleteByExample(LkpyFishExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(LkpyFish record);

  int insertSelective(LkpyFish record);

  List<LkpyFish> selectByExample(LkpyFishExample example);

  LkpyFish selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") LkpyFish record,
      @Param("example") LkpyFishExample example);

  int updateByExample(@Param("record") LkpyFish record, @Param("example") LkpyFishExample example);

  int updateByPrimaryKeySelective(LkpyFish record);

  int updateByPrimaryKey(LkpyFish record);
}
