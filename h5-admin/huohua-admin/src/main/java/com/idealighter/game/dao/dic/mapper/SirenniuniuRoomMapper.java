package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SirenniuniuRoom;
import com.idealighter.game.dao.dic.po.SirenniuniuRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SirenniuniuRoomMapper {
    long countByExample(SirenniuniuRoomExample example);

    int deleteByExample(SirenniuniuRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SirenniuniuRoom record);

    int insertSelective(SirenniuniuRoom record);

    List<SirenniuniuRoom> selectByExample(SirenniuniuRoomExample example);

    SirenniuniuRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SirenniuniuRoom record, @Param("example") SirenniuniuRoomExample example);

    int updateByExample(@Param("record") SirenniuniuRoom record, @Param("example") SirenniuniuRoomExample example);

    int updateByPrimaryKeySelective(SirenniuniuRoom record);

    int updateByPrimaryKey(SirenniuniuRoom record);
}