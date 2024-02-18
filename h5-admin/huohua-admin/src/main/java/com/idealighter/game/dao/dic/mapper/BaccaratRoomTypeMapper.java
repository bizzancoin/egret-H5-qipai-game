package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BaccaratRoomType;
import com.idealighter.game.dao.dic.po.BaccaratRoomTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaccaratRoomTypeMapper {
    long countByExample(BaccaratRoomTypeExample example);

    int deleteByExample(BaccaratRoomTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaccaratRoomType record);

    int insertSelective(BaccaratRoomType record);

    List<BaccaratRoomType> selectByExample(BaccaratRoomTypeExample example);

    BaccaratRoomType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaccaratRoomType record, @Param("example") BaccaratRoomTypeExample example);

    int updateByExample(@Param("record") BaccaratRoomType record, @Param("example") BaccaratRoomTypeExample example);

    int updateByPrimaryKeySelective(BaccaratRoomType record);

    int updateByPrimaryKey(BaccaratRoomType record);
}