package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyRoomTypeDomain;
import com.idealighter.game.dictionary.mapper.JcbyRoomTypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyRoomTypeDic implements DictionaryTable<JcbyRoomTypeDomain> {

  private JcbyRoomTypeMapper mapper;
  private List<JcbyRoomTypeDomain> list;
  private Map<Integer, JcbyRoomTypeDomain> map;

  @Inject
  public JcbyRoomTypeDic(JcbyRoomTypeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyRoomTypeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyRoomTypeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, JcbyRoomTypeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyRoomTypeDomain get(int id) {

    return map.get(id);
  }
}
