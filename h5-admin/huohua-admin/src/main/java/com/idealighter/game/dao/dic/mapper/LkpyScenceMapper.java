package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.LkpyScence;
import com.idealighter.game.dao.dic.po.LkpyScenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LkpyScenceMapper {
    long countByExample(LkpyScenceExample example);

    int deleteByExample(LkpyScenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LkpyScence record);

    int insertSelective(LkpyScence record);

    List<LkpyScence> selectByExample(LkpyScenceExample example);

    LkpyScence selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LkpyScence record, @Param("example") LkpyScenceExample example);

    int updateByExample(@Param("record") LkpyScence record, @Param("example") LkpyScenceExample example);

    int updateByPrimaryKeySelective(LkpyScence record);

    int updateByPrimaryKey(LkpyScence record);
}