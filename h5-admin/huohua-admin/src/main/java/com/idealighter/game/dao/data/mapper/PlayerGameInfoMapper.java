package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerGameInfo;
import com.idealighter.game.dao.data.po.PlayerGameInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerGameInfoMapper {
    long countByExample(PlayerGameInfoExample example);

    int deleteByExample(PlayerGameInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayerGameInfo record);

    int insertSelective(PlayerGameInfo record);

    List<PlayerGameInfo> selectByExample(PlayerGameInfoExample example);

    PlayerGameInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayerGameInfo record, @Param("example") PlayerGameInfoExample example);

    int updateByExample(@Param("record") PlayerGameInfo record, @Param("example") PlayerGameInfoExample example);

    int updateByPrimaryKeySelective(PlayerGameInfo record);

    int updateByPrimaryKey(PlayerGameInfo record);
}