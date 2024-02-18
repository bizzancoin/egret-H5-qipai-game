package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.Games;
import com.idealighter.game.dao.dic.po.GamesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GamesMapper {
    long countByExample(GamesExample example);

    int deleteByExample(GamesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Games record);

    int insertSelective(Games record);

    List<Games> selectByExample(GamesExample example);

    Games selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Games record, @Param("example") GamesExample example);

    int updateByExample(@Param("record") Games record, @Param("example") GamesExample example);

    int updateByPrimaryKeySelective(Games record);

    int updateByPrimaryKey(Games record);
}