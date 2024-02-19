package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BaccaratRoomTypeDomain;
import com.idealighter.game.dictionary.mapper.BaccaratRoomTypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BaccaratRoomTypeDic implements DictionaryTable<BaccaratRoomTypeDomain> {

  private BaccaratRoomTypeMapper mapper;
  private List<BaccaratRoomTypeDomain> list;
  private Map<Integer, BaccaratRoomTypeDomain> map;

  @Inject
  public BaccaratRoomTypeDic(BaccaratRoomTypeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BaccaratRoomTypeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, BaccaratRoomTypeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BaccaratRoomTypeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BaccaratRoomTypeDomain get(int id) {

    return map.get(id);
  }
}
