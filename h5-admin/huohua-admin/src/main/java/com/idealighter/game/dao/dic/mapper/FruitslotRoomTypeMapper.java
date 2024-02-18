package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.FruitslotRoomType;
import com.idealighter.game.dao.dic.po.FruitslotRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FruitslotRoomTypeMapper {
    long countByExample(FruitslotRoomTypeExample example);

    int deleteByExample(FruitslotRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitslotRoomType record);

    int insertSelective(FruitslotRoomType record);

    List<FruitslotRoomType> selectByExample(FruitslotRoomTypeExample example);

    FruitslotRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitslotRoomType record, @Param("example") FruitslotRoomTypeExample example);

    int updateByExample(@Param("record") FruitslotRoomType record, @Param("example") FruitslotRoomTypeExample example);

    int updateByPrimaryKeySelective(FruitslotRoomType record);

    int updateByPrimaryKey(FruitslotRoomType record);
}