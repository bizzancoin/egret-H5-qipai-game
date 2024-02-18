package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BairenniuniuRoom;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BairenniuniuRoomMapper {
    long countByExample(BairenniuniuRoomExample example);

    int deleteByExample(BairenniuniuRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BairenniuniuRoom record);

    int insertSelective(BairenniuniuRoom record);

    List<BairenniuniuRoom> selectByExample(BairenniuniuRoomExample example);

    BairenniuniuRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BairenniuniuRoom record, @Param("example") BairenniuniuRoomExample example);

    int updateByExample(@Param("record") BairenniuniuRoom record, @Param("example") BairenniuniuRoomExample example);

    int updateByPrimaryKeySelective(BairenniuniuRoom record);

    int updateByPrimaryKey(BairenniuniuRoom record);
}