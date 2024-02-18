package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.WknhScence;
import com.idealighter.game.dao.dic.po.WknhScenceExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WknhScenceMapper {
  long countByExample(WknhScenceExample example);

  int deleteByExample(WknhScenceExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(WknhScence record);

  int insertSelective(WknhScence record);

  List<WknhScence> selectByExample(WknhScenceExample example);

  WknhScence selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") WknhScence record,
      @Param("example") WknhScenceExample example);

  int updateByExample(@Param("record") WknhScence record,
      @Param("example") WknhScenceExample example);

  int updateByPrimaryKeySelective(WknhScence record);

  int updateByPrimaryKey(WknhScence record);
}
