package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotLineCoord;
import com.idealighter.game.dao.dic.po.FruitslotLineCoordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotLineCoordMapper {
    long countByExample(FruitslotLineCoordExample example);

    int deleteByExample(FruitslotLineCoordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotLineCoord record);

    int insertSelective(FruitslotLineCoord record);

    List<FruitslotLineCoord> selectByExample(FruitslotLineCoordExample example);

    FruitslotLineCoord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotLineCoord record, @Param("example") FruitslotLineCoordExample example);

    int updateByExample(@Param("record") FruitslotLineCoord record, @Param("example") FruitslotLineCoordExample example);

    int updateByPrimaryKeySelective(FruitslotLineCoord record);

    int updateByPrimaryKey(FruitslotLineCoord record);
}