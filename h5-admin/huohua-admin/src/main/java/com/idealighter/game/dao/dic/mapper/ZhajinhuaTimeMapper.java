package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ZhajinhuaTime;
import com.idealighter.game.dao.dic.po.ZhajinhuaTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZhajinhuaTimeMapper {
    long countByExample(ZhajinhuaTimeExample example);

    int deleteByExample(ZhajinhuaTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZhajinhuaTime record);

    int insertSelective(ZhajinhuaTime record);

    List<ZhajinhuaTime> selectByExample(ZhajinhuaTimeExample example);

    ZhajinhuaTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZhajinhuaTime record, @Param("example") ZhajinhuaTimeExample example);

    int updateByExample(@Param("record") ZhajinhuaTime record, @Param("example") ZhajinhuaTimeExample example);

    int updateByPrimaryKeySelective(ZhajinhuaTime record);

    int updateByPrimaryKey(ZhajinhuaTime record);
}