package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShuihuBonusNumCfg;
import com.idealighter.game.dao.dic.po.ShuihuBonusNumCfgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShuihuBonusNumCfgMapper {
  long countByExample(ShuihuBonusNumCfgExample example);

  int deleteByExample(ShuihuBonusNumCfgExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShuihuBonusNumCfg record);

  int insertSelective(ShuihuBonusNumCfg record);

  List<ShuihuBonusNumCfg> selectByExample(ShuihuBonusNumCfgExample example);

  ShuihuBonusNumCfg selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShuihuBonusNumCfg record,
      @Param("example") ShuihuBonusNumCfgExample example);

  int updateByExample(@Param("record") ShuihuBonusNumCfg record,
      @Param("example") ShuihuBonusNumCfgExample example);

  int updateByPrimaryKeySelective(ShuihuBonusNumCfg record);

  int updateByPrimaryKey(ShuihuBonusNumCfg record);
}
