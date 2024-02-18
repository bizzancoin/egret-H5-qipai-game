package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Words;
import com.idealighter.game.dao.dic.po.WordsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WordsMapper {
  long countByExample(WordsExample example);

  int deleteByExample(WordsExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Words record);

  int insertSelective(Words record);

  List<Words> selectByExample(WordsExample example);

  Words selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Words record,
      @Param("example") WordsExample example);

  int updateByExample(@Param("record") Words record, @Param("example") WordsExample example);

  int updateByPrimaryKeySelective(Words record);

  int updateByPrimaryKey(Words record);
}
