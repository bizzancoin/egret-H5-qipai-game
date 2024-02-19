package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyStrategyDomain;
import com.idealighter.game.dictionary.mapper.JcbyStrategyMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyStrategyDic implements DictionaryTable<JcbyStrategyDomain> {

  private JcbyStrategyMapper mapper;
  private List<JcbyStrategyDomain> list;
  private Map<Integer, JcbyStrategyDomain> map;

  @Inject
  public JcbyStrategyDic(JcbyStrategyMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyStrategyDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyStrategyDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, JcbyStrategyDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyStrategyDomain get(int id) {

    return map.get(id);
  }
}
