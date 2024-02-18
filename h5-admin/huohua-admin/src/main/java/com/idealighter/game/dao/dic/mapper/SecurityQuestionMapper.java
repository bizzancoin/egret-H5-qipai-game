package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.SecurityQuestion;
import com.idealighter.game.dao.dic.po.SecurityQuestionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SecurityQuestionMapper {
  long countByExample(SecurityQuestionExample example);

  int deleteByExample(SecurityQuestionExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(SecurityQuestion record);

  int insertSelective(SecurityQuestion record);

  List<SecurityQuestion> selectByExample(SecurityQuestionExample example);

  SecurityQuestion selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") SecurityQuestion record,
      @Param("example") SecurityQuestionExample example);

  int updateByExample(@Param("record") SecurityQuestion record,
      @Param("example") SecurityQuestionExample example);

  int updateByPrimaryKeySelective(SecurityQuestion record);

  int updateByPrimaryKey(SecurityQuestion record);
}
