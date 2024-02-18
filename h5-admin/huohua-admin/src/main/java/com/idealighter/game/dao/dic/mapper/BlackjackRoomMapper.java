package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BlackjackRoom;
import com.idealighter.game.dao.dic.po.BlackjackRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlackjackRoomMapper {
    long countByExample(BlackjackRoomExample example);

    int deleteByExample(BlackjackRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlackjackRoom record);

    int insertSelective(BlackjackRoom record);

    List<BlackjackRoom> selectByExample(BlackjackRoomExample example);

    BlackjackRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlackjackRoom record, @Param("example") BlackjackRoomExample example);

    int updateByExample(@Param("record") BlackjackRoom record, @Param("example") BlackjackRoomExample example);

    int updateByPrimaryKeySelective(BlackjackRoom record);

    int updateByPrimaryKey(BlackjackRoom record);
}