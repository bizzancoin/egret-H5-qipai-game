package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotRoom;
import com.idealighter.game.dao.dic.po.FruitslotRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotRoomMapper {
    long countByExample(FruitslotRoomExample example);

    int deleteByExample(FruitslotRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotRoom record);

    int insertSelective(FruitslotRoom record);

    List<FruitslotRoom> selectByExample(FruitslotRoomExample example);

    FruitslotRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotRoom record, @Param("example") FruitslotRoomExample example);

    int updateByExample(@Param("record") FruitslotRoom record, @Param("example") FruitslotRoomExample example);

    int updateByPrimaryKeySelective(FruitslotRoom record);

    int updateByPrimaryKey(FruitslotRoom record);
}