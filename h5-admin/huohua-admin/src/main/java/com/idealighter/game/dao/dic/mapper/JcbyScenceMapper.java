package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.JcbyScence;
import com.idealighter.game.dao.dic.po.JcbyScenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JcbyScenceMapper {
    long countByExample(JcbyScenceExample example);

    int deleteByExample(JcbyScenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JcbyScence record);

    int insertSelective(JcbyScence record);

    List<JcbyScence> selectByExample(JcbyScenceExample example);

    JcbyScence selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JcbyScence record, @Param("example") JcbyScenceExample example);

    int updateByExample(@Param("record") JcbyScence record, @Param("example") JcbyScenceExample example);

    int updateByPrimaryKeySelective(JcbyScence record);

    int updateByPrimaryKey(JcbyScence record);
}