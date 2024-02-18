package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.PlayerVip;
import com.idealighter.game.dao.data.po.PlayerVipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlayerVipMapper {
    long countByExample(PlayerVipExample example);

    int deleteByExample(PlayerVipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlayerVip record);

    int insertSelective(PlayerVip record);

    List<PlayerVip> selectByExample(PlayerVipExample example);

    PlayerVip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlayerVip record, @Param("example") PlayerVipExample example);

    int updateByExample(@Param("record") PlayerVip record, @Param("example") PlayerVipExample example);

    int updateByPrimaryKeySelective(PlayerVip record);

    int updateByPrimaryKey(PlayerVip record);
}