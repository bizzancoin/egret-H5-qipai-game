
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.OnlineRewardDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface OnlineRewardMapper {

  @Select("select * from online_reward")
  List<OnlineRewardDomain> selectAll();
}
