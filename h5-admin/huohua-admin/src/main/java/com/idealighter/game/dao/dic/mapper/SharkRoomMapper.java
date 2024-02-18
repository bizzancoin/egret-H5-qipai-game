package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SharkRoom;
import com.idealighter.game.dao.dic.po.SharkRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SharkRoomMapper {
    long countByExample(SharkRoomExample example);

    int deleteByExample(SharkRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SharkRoom record);

    int insertSelective(SharkRoom record);

    List<SharkRoom> selectByExample(SharkRoomExample example);

    SharkRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SharkRoom record, @Param("example") SharkRoomExample example);

    int updateByExample(@Param("record") SharkRoom record, @Param("example") SharkRoomExample example);

    int updateByPrimaryKeySelective(SharkRoom record);

    int updateByPrimaryKey(SharkRoom record);
}