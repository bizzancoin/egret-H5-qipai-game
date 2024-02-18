package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.DdzRoom;
import com.idealighter.game.dao.dic.po.DdzRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DdzRoomMapper {
    long countByExample(DdzRoomExample example);

    int deleteByExample(DdzRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DdzRoom record);

    int insertSelective(DdzRoom record);

    List<DdzRoom> selectByExample(DdzRoomExample example);

    DdzRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DdzRoom record, @Param("example") DdzRoomExample example);

    int updateByExample(@Param("record") DdzRoom record, @Param("example") DdzRoomExample example);

    int updateByPrimaryKeySelective(DdzRoom record);

    int updateByPrimaryKey(DdzRoom record);
}