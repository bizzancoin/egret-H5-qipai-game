package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotIcon;
import com.idealighter.game.dao.dic.po.FruitslotIconExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotIconMapper {
    long countByExample(FruitslotIconExample example);

    int deleteByExample(FruitslotIconExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotIcon record);

    int insertSelective(FruitslotIcon record);

    List<FruitslotIcon> selectByExample(FruitslotIconExample example);

    FruitslotIcon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotIcon record, @Param("example") FruitslotIconExample example);

    int updateByExample(@Param("record") FruitslotIcon record, @Param("example") FruitslotIconExample example);

    int updateByPrimaryKeySelective(FruitslotIcon record);

    int updateByPrimaryKey(FruitslotIcon record);
}