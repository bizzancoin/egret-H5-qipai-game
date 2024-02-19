
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BairenniuniuTimeDomain;
import com.idealighter.game.dictionary.mapper.BairenniuniuTimeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BairenniuniuTimeDic implements DictionaryTable<BairenniuniuTimeDomain> {

  private BairenniuniuTimeMapper mapper;
  private List<BairenniuniuTimeDomain> list;
  private Map<Integer, BairenniuniuTimeDomain> map;

  @Inject
  public BairenniuniuTimeDic(BairenniuniuTimeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BairenniuniuTimeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, BairenniuniuTimeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BairenniuniuTimeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BairenniuniuTimeDomain get(int id) {
    return map.get(id);
  }
}
