package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.PlayerHeadSrc;
import com.idealighter.game.dao.dic.po.PlayerHeadSrcExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlayerHeadSrcMapper {
  long countByExample(PlayerHeadSrcExample example);

  int deleteByExample(PlayerHeadSrcExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(PlayerHeadSrc record);

  int insertSelective(PlayerHeadSrc record);

  List<PlayerHeadSrc> selectByExample(PlayerHeadSrcExample example);

  PlayerHeadSrc selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") PlayerHeadSrc record,
      @Param("example") PlayerHeadSrcExample example);

  int updateByExample(@Param("record") PlayerHeadSrc record,
      @Param("example") PlayerHeadSrcExample example);

  int updateByPrimaryKeySelective(PlayerHeadSrc record);

  int updateByPrimaryKey(PlayerHeadSrc record);
}
