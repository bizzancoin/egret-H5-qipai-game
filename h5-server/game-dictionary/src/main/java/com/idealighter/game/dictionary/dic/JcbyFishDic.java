
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyFishDomain;
import com.idealighter.game.dictionary.domwrapper.JcbyFishDomWrapper;
import com.idealighter.game.dictionary.mapper.JcbyFishMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyFishDic implements DictionaryTable<JcbyFishDomWrapper> {

  private JcbyFishMapper mapper;
  private List<JcbyFishDomWrapper> list;
  private Map<Integer, JcbyFishDomWrapper> map;

  @Inject
  public JcbyFishDic(JcbyFishMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyFishDomWrapper> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyFishDomWrapper> map() {

    return map;
  }

  @Override
  public void load() {
    List<JcbyFishDomWrapper> list = new ArrayList<>();
    for (JcbyFishDomain fishDom : mapper.selectAll()) {
      list.add(new JcbyFishDomWrapper(fishDom));
    }
    this.list = list;
    Map<Integer, JcbyFishDomWrapper> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyFishDomWrapper get(int id) {

    return map.get(id);
  }
}
