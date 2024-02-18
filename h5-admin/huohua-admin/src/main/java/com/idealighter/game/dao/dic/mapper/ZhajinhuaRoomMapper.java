package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ZhajinhuaRoom;
import com.idealighter.game.dao.dic.po.ZhajinhuaRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZhajinhuaRoomMapper {
    long countByExample(ZhajinhuaRoomExample example);

    int deleteByExample(ZhajinhuaRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZhajinhuaRoom record);

    int insertSelective(ZhajinhuaRoom record);

    List<ZhajinhuaRoom> selectByExample(ZhajinhuaRoomExample example);

    ZhajinhuaRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZhajinhuaRoom record, @Param("example") ZhajinhuaRoomExample example);

    int updateByExample(@Param("record") ZhajinhuaRoom record, @Param("example") ZhajinhuaRoomExample example);

    int updateByPrimaryKeySelective(ZhajinhuaRoom record);

    int updateByPrimaryKey(ZhajinhuaRoom record);
}