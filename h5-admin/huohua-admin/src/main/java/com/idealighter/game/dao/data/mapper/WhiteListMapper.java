package com.idealighter.game.dao.data.mapper;

import com.idealighter.game.dao.data.po.WhiteList;
import com.idealighter.game.dao.data.po.WhiteListExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WhiteListMapper {
  long countByExample(WhiteListExample example);

  int deleteByExample(WhiteListExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WhiteList record);

  int insertSelective(WhiteList record);

  List<WhiteList> selectByExample(WhiteListExample example);

  WhiteList selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WhiteList record,
      @Param("example") WhiteListExample example);

  int updateByExample(@Param("record") WhiteList record,
      @Param("example") WhiteListExample example);

  int updateByPrimaryKeySelective(WhiteList record);

  int updateByPrimaryKey(WhiteList record);
}
