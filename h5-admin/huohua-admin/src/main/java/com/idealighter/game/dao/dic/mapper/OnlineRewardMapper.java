package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.OnlineReward;
import com.idealighter.game.dao.dic.po.OnlineRewardExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OnlineRewardMapper {
  long countByExample(OnlineRewardExample example);

  int deleteByExample(OnlineRewardExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(OnlineReward record);

  int insertSelective(OnlineReward record);

  List<OnlineReward> selectByExample(OnlineRewardExample example);

  OnlineReward selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") OnlineReward record,
      @Param("example") OnlineRewardExample example);

  int updateByExample(@Param("record") OnlineReward record,
      @Param("example") OnlineRewardExample example);

  int updateByPrimaryKeySelective(OnlineReward record);

  int updateByPrimaryKey(OnlineReward record);
}
