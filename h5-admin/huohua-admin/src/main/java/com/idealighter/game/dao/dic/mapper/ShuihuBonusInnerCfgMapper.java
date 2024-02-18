package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuBonusInnerCfg;
import com.idealighter.game.dao.dic.po.ShuihuBonusInnerCfgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuBonusInnerCfgMapper {
  long countByExample(ShuihuBonusInnerCfgExample example);

  int deleteByExample(ShuihuBonusInnerCfgExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuBonusInnerCfg record);

  int insertSelective(ShuihuBonusInnerCfg record);

  List<ShuihuBonusInnerCfg> selectByExample(ShuihuBonusInnerCfgExample example);

  ShuihuBonusInnerCfg selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuBonusInnerCfg record,
      @Param("example") ShuihuBonusInnerCfgExample example);

  int updateByExample(@Param("record") ShuihuBonusInnerCfg record,
      @Param("example") ShuihuBonusInnerCfgExample example);

  int updateByPrimaryKeySelective(ShuihuBonusInnerCfg record);

  int updateByPrimaryKey(ShuihuBonusInnerCfg record);
}
