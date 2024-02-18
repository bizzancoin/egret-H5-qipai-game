package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.BaccaratRoom;
import com.idealighter.game.dao.dic.po.BaccaratRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaccaratRoomMapper {
    long countByExample(BaccaratRoomExample example);

    int deleteByExample(BaccaratRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaccaratRoom record);

    int insertSelective(BaccaratRoom record);

    List<BaccaratRoom> selectByExample(BaccaratRoomExample example);

    BaccaratRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaccaratRoom record, @Param("example") BaccaratRoomExample example);

    int updateByExample(@Param("record") BaccaratRoom record, @Param("example") BaccaratRoomExample example);

    int updateByPrimaryKeySelective(BaccaratRoom record);

    int updateByPrimaryKey(BaccaratRoom record);
}