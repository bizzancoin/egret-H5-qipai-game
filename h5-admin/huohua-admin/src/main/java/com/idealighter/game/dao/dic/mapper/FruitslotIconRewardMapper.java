package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotIconReward;
import com.idealighter.game.dao.dic.po.FruitslotIconRewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotIconRewardMapper {
    long countByExample(FruitslotIconRewardExample example);

    int deleteByExample(FruitslotIconRewardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotIconReward record);

    int insertSelective(FruitslotIconReward record);

    List<FruitslotIconReward> selectByExample(FruitslotIconRewardExample example);

    FruitslotIconReward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotIconReward record, @Param("example") FruitslotIconRewardExample example);

    int updateByExample(@Param("record") FruitslotIconReward record, @Param("example") FruitslotIconRewardExample example);

    int updateByPrimaryKeySelective(FruitslotIconReward record);

    int updateByPrimaryKey(FruitslotIconReward record);
}