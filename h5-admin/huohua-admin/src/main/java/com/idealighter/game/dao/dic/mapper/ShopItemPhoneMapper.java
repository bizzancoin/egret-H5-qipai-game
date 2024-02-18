package com.idealighter.game.dao.dic.mapper;

import com.idealighter.game.dao.dic.po.ShopItemPhone;
import com.idealighter.game.dao.dic.po.ShopItemPhoneExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopItemPhoneMapper {
  long countByExample(ShopItemPhoneExample example);

  int deleteByExample(ShopItemPhoneExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(ShopItemPhone record);

  int insertSelective(ShopItemPhone record);

  List<ShopItemPhone> selectByExample(ShopItemPhoneExample example);

  ShopItemPhone selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") ShopItemPhone record,
      @Param("example") ShopItemPhoneExample example);

  int updateByExample(@Param("record") ShopItemPhone record,
      @Param("example") ShopItemPhoneExample example);

  int updateByPrimaryKeySelective(ShopItemPhone record);

  int updateByPrimaryKey(ShopItemPhone record);
}
