package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BaccaratTimeDomain;
import com.idealighter.game.dictionary.mapper.BaccaratTimeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BaccaratTimeDic implements DictionaryTable<BaccaratTimeDomain> {

  private BaccaratTimeMapper mapper;
  private List<BaccaratTimeDomain> list;
  private Map<Integer, BaccaratTimeDomain> map;

  @Inject
  public BaccaratTimeDic(BaccaratTimeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BaccaratTimeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, BaccaratTimeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BaccaratTimeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BaccaratTimeDomain get(int id) {

    return map.get(id);
  }
}
