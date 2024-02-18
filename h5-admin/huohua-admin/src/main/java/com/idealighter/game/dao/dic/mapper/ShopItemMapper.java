package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShopItem;
import com.idealighter.game.dao.dic.po.ShopItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopItemMapper {
  long countByExample(ShopItemExample example);

  int deleteByExample(ShopItemExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShopItem record);

  int insertSelective(ShopItem record);

  List<ShopItem> selectByExample(ShopItemExample example);

  ShopItem selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShopItem record,
      @Param("example") ShopItemExample example);

  int updateByExample(@Param("record") ShopItem record, @Param("example") ShopItemExample example);

  int updateByPrimaryKeySelective(ShopItem record);

  int updateByPrimaryKey(ShopItem record);
}
