package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuIconReward;
import com.idealighter.game.dao.dic.po.ShuihuIconRewardExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuIconRewardMapper {
  long countByExample(ShuihuIconRewardExample example);

  int deleteByExample(ShuihuIconRewardExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuIconReward record);

  int insertSelective(ShuihuIconReward record);

  List<ShuihuIconReward> selectByExample(ShuihuIconRewardExample example);

  ShuihuIconReward selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuIconReward record,
      @Param("example") ShuihuIconRewardExample example);

  int updateByExample(@Param("record") ShuihuIconReward record,
      @Param("example") ShuihuIconRewardExample example);

  int updateByPrimaryKeySelective(ShuihuIconReward record);

  int updateByPrimaryKey(ShuihuIconReward record);
}
