package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuBonusOuterKickOut;
import com.idealighter.game.dao.dic.po.ShuihuBonusOuterKickOutExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuBonusOuterKickOutMapper {
  long countByExample(ShuihuBonusOuterKickOutExample example);

  int deleteByExample(ShuihuBonusOuterKickOutExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuBonusOuterKickOut record);

  int insertSelective(ShuihuBonusOuterKickOut record);

  List<ShuihuBonusOuterKickOut> selectByExample(ShuihuBonusOuterKickOutExample example);

  ShuihuBonusOuterKickOut selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuBonusOuterKickOut record,
      @Param("example") ShuihuBonusOuterKickOutExample example);

  int updateByExample(@Param("record") ShuihuBonusOuterKickOut record,
      @Param("example") ShuihuBonusOuterKickOutExample example);

  int updateByPrimaryKeySelective(ShuihuBonusOuterKickOut record);

  int updateByPrimaryKey(ShuihuBonusOuterKickOut record);
}
