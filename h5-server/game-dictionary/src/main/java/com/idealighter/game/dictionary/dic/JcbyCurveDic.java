
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyCurveDomain;
import com.idealighter.game.dictionary.mapper.JcbyCurveMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyCurveDic implements DictionaryTable<JcbyCurveDomain> {

  private JcbyCurveMapper mapper;
  private List<JcbyCurveDomain> list;
  private Map<Integer, JcbyCurveDomain> map;

  @Inject
  public JcbyCurveDic(JcbyCurveMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyCurveDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyCurveDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, JcbyCurveDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyCurveDomain get(int id) {

    return map.get(id);
  }
}
