package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyRoom;
import com.idealighter.game.dao.dic.po.JcbyRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JcbyRoomMapper {
    long countByExample(JcbyRoomExample example);

    int deleteByExample(JcbyRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JcbyRoom record);

    int insertSelective(JcbyRoom record);

    List<JcbyRoom> selectByExample(JcbyRoomExample example);

    JcbyRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JcbyRoom record, @Param("example") JcbyRoomExample example);

    int updateByExample(@Param("record") JcbyRoom record, @Param("example") JcbyRoomExample example);

    int updateByPrimaryKeySelective(JcbyRoom record);

    int updateByPrimaryKey(JcbyRoom record);
}