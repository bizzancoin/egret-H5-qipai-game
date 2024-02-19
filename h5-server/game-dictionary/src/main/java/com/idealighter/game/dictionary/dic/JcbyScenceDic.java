
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyScenceDomain;
import com.idealighter.game.dictionary.domwrapper.JcbyScenceDomWrapper;
import com.idealighter.game.dictionary.mapper.JcbyScenceMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyScenceDic implements DictionaryTable<JcbyScenceDomWrapper> {

  private JcbyScenceMapper mapper;
  private List<JcbyScenceDomWrapper> list;
  private Map<Integer, JcbyScenceDomWrapper> map;

  @Inject
  public JcbyScenceDic(JcbyScenceMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyScenceDomWrapper> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyScenceDomWrapper> map() {

    return map;
  }

  @Override
  public void load() {
    List<JcbyScenceDomWrapper> list = new ArrayList<>();
    for (JcbyScenceDomain jcbyScenceDom : mapper.selectAll()) {
      list.add(new JcbyScenceDomWrapper(jcbyScenceDom));

    }
    this.list = list;
    Map<Integer, JcbyScenceDomWrapper> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyScenceDomWrapper get(int id) {

    return map.get(id);
  }
}
