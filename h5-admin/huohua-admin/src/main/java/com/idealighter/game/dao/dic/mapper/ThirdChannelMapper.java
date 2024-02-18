package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ThirdChannel;
import com.idealighter.game.dao.dic.po.ThirdChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThirdChannelMapper {
    long countByExample(ThirdChannelExample example);

    int deleteByExample(ThirdChannelExample example);

    int deleteByPrimaryKey(String channelId);

    int insert(ThirdChannel record);

    int insertSelective(ThirdChannel record);

    List<ThirdChannel> selectByExample(ThirdChannelExample example);

    ThirdChannel selectByPrimaryKey(String channelId);

    int updateByExampleSelective(@Param("record") ThirdChannel record, @Param("example") ThirdChannelExample example);

    int updateByExample(@Param("record") ThirdChannel record, @Param("example") ThirdChannelExample example);

    int updateByPrimaryKeySelective(ThirdChannel record);

    int updateByPrimaryKey(ThirdChannel record);
}