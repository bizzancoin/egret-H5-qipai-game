package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyRoom;
import com.idealighter.game.dao.dic.po.LkpyRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LkpyRoomMapper {
    long countByExample(LkpyRoomExample example);

    int deleteByExample(LkpyRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LkpyRoom record);

    int insertSelective(LkpyRoom record);

    List<LkpyRoom> selectByExample(LkpyRoomExample example);

    LkpyRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LkpyRoom record, @Param("example") LkpyRoomExample example);

    int updateByExample(@Param("record") LkpyRoom record, @Param("example") LkpyRoomExample example);

    int updateByPrimaryKeySelective(LkpyRoom record);

    int updateByPrimaryKey(LkpyRoom record);
}