package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerMain;
import com.idealighter.game.dao.data.po.PlayerMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerMainMapper {
    long countByExample(PlayerMainExample example);

    int deleteByExample(PlayerMainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PlayerMain record);

    int insertSelective(PlayerMain record);

    List<PlayerMain> selectByExample(PlayerMainExample example);

    PlayerMain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PlayerMain record, @Param("example") PlayerMainExample example);

    int updateByExample(@Param("record") PlayerMain record, @Param("example") PlayerMainExample example);

    int updateByPrimaryKeySelective(PlayerMain record);

    int updateByPrimaryKey(PlayerMain record);
}