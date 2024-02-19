package com.idealighter.game.dictionary.dic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.DdzTimeDomain;
import com.idealighter.game.dictionary.mapper.DdzTimeMapper;

@Singleton
public class DdzTimeDic implements DictionaryTable<DdzTimeDomain> {

  private DdzTimeMapper mapper;
  private List<DdzTimeDomain> list;
  private Map<Integer, DdzTimeDomain> map;

  @Inject
  public DdzTimeDic(DdzTimeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<DdzTimeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, DdzTimeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, DdzTimeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public DdzTimeDomain get(int id) {

    return map.get(id);
  }
}
