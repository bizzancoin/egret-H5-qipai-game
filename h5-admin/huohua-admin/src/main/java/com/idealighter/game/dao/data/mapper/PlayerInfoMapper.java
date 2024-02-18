package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerInfo;
import com.idealighter.game.dao.data.po.PlayerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerInfoMapper {
    long countByExample(PlayerInfoExample example);

    int deleteByExample(PlayerInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayerInfo record);

    int insertSelective(PlayerInfo record);

    List<PlayerInfo> selectByExample(PlayerInfoExample example);

    PlayerInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayerInfo record, @Param("example") PlayerInfoExample example);

    int updateByExample(@Param("record") PlayerInfo record, @Param("example") PlayerInfoExample example);

    int updateByPrimaryKeySelective(PlayerInfo record);

    int updateByPrimaryKey(PlayerInfo record);
}