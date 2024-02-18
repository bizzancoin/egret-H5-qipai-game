package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerSafeInfo;
import com.idealighter.game.dao.data.po.PlayerSafeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerSafeInfoMapper {
    long countByExample(PlayerSafeInfoExample example);

    int deleteByExample(PlayerSafeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayerSafeInfo record);

    int insertSelective(PlayerSafeInfo record);

    List<PlayerSafeInfo> selectByExample(PlayerSafeInfoExample example);

    PlayerSafeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayerSafeInfo record, @Param("example") PlayerSafeInfoExample example);

    int updateByExample(@Param("record") PlayerSafeInfo record, @Param("example") PlayerSafeInfoExample example);

    int updateByPrimaryKeySelective(PlayerSafeInfo record);

    int updateByPrimaryKey(PlayerSafeInfo record);
}