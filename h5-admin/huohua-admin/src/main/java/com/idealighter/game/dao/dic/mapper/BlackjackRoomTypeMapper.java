package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BlackjackRoomType;
import com.idealighter.game.dao.dic.po.BlackjackRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlackjackRoomTypeMapper {
    long countByExample(BlackjackRoomTypeExample example);

    int deleteByExample(BlackjackRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlackjackRoomType record);

    int insertSelective(BlackjackRoomType record);

    List<BlackjackRoomType> selectByExample(BlackjackRoomTypeExample example);

    BlackjackRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlackjackRoomType record, @Param("example") BlackjackRoomTypeExample example);

    int updateByExample(@Param("record") BlackjackRoomType record, @Param("example") BlackjackRoomTypeExample example);

    int updateByPrimaryKeySelective(BlackjackRoomType record);

    int updateByPrimaryKey(BlackjackRoomType record);
}