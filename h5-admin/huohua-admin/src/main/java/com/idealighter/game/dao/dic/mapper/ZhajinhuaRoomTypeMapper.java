package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ZhajinhuaRoomType;
import com.idealighter.game.dao.dic.po.ZhajinhuaRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZhajinhuaRoomTypeMapper {
    long countByExample(ZhajinhuaRoomTypeExample example);

    int deleteByExample(ZhajinhuaRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZhajinhuaRoomType record);

    int insertSelective(ZhajinhuaRoomType record);

    List<ZhajinhuaRoomType> selectByExample(ZhajinhuaRoomTypeExample example);

    ZhajinhuaRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZhajinhuaRoomType record, @Param("example") ZhajinhuaRoomTypeExample example);

    int updateByExample(@Param("record") ZhajinhuaRoomType record, @Param("example") ZhajinhuaRoomTypeExample example);

    int updateByPrimaryKeySelective(ZhajinhuaRoomType record);

    int updateByPrimaryKey(ZhajinhuaRoomType record);
}