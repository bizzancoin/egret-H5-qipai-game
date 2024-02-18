package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Message;
import com.idealighter.game.dao.dic.po.MessageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
  long countByExample(MessageExample example);

  int deleteByExample(MessageExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Message record);

  int insertSelective(Message record);

  List<Message> selectByExample(MessageExample example);

  Message selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Message record,
      @Param("example") MessageExample example);

  int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

  int updateByPrimaryKeySelective(Message record);

  int updateByPrimaryKey(Message record);
}
