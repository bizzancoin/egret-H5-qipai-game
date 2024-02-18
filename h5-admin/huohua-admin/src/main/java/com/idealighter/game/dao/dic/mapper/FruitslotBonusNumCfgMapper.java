package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotBonusNumCfg;
import com.idealighter.game.dao.dic.po.FruitslotBonusNumCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotBonusNumCfgMapper {
    long countByExample(FruitslotBonusNumCfgExample example);

    int deleteByExample(FruitslotBonusNumCfgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotBonusNumCfg record);

    int insertSelective(FruitslotBonusNumCfg record);

    List<FruitslotBonusNumCfg> selectByExample(FruitslotBonusNumCfgExample example);

    FruitslotBonusNumCfg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotBonusNumCfg record, @Param("example") FruitslotBonusNumCfgExample example);

    int updateByExample(@Param("record") FruitslotBonusNumCfg record, @Param("example") FruitslotBonusNumCfgExample example);

    int updateByPrimaryKeySelective(FruitslotBonusNumCfg record);

    int updateByPrimaryKey(FruitslotBonusNumCfg record);
}