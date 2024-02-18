package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.DdzRoomType;
import com.idealighter.game.dao.dic.po.DdzRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DdzRoomTypeMapper {
    long countByExample(DdzRoomTypeExample example);

    int deleteByExample(DdzRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DdzRoomType record);

    int insertSelective(DdzRoomType record);

    List<DdzRoomType> selectByExample(DdzRoomTypeExample example);

    DdzRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DdzRoomType record, @Param("example") DdzRoomTypeExample example);

    int updateByExample(@Param("record") DdzRoomType record, @Param("example") DdzRoomTypeExample example);

    int updateByPrimaryKeySelective(DdzRoomType record);

    int updateByPrimaryKey(DdzRoomType record);
}