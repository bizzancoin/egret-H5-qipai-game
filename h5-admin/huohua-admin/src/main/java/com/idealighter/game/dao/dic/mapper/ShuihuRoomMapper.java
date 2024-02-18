package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuRoom;
import com.idealighter.game.dao.dic.po.ShuihuRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShuihuRoomMapper {
    long countByExample(ShuihuRoomExample example);

    int deleteByExample(ShuihuRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShuihuRoom record);

    int insertSelective(ShuihuRoom record);

    List<ShuihuRoom> selectByExample(ShuihuRoomExample example);

    ShuihuRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShuihuRoom record, @Param("example") ShuihuRoomExample example);

    int updateByExample(@Param("record") ShuihuRoom record, @Param("example") ShuihuRoomExample example);

    int updateByPrimaryKeySelective(ShuihuRoom record);

    int updateByPrimaryKey(ShuihuRoom record);
}