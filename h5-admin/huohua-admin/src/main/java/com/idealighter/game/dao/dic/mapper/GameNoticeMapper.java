package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.GameNotice;
import com.idealighter.game.dao.dic.po.GameNoticeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GameNoticeMapper {
  long countByExample(GameNoticeExample example);

  int deleteByExample(GameNoticeExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(GameNotice record);

  int insertSelective(GameNotice record);

  List<GameNotice> selectByExample(GameNoticeExample example);

  GameNotice selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") GameNotice record,
      @Param("example") GameNoticeExample example);

  int updateByExample(@Param("record") GameNotice record,
      @Param("example") GameNoticeExample example);

  int updateByPrimaryKeySelective(GameNotice record);

  int updateByPrimaryKey(GameNotice record);
}
