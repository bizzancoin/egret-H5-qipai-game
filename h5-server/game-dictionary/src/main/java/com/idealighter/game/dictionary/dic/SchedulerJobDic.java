
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.SchedulerJobDomain;
import com.idealighter.game.dictionary.mapper.SchedulerJobMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class SchedulerJobDic implements DictionaryTable<SchedulerJobDomain> {

  private SchedulerJobMapper mapper;
  private List<SchedulerJobDomain> list;
  private Map<Integer, SchedulerJobDomain> map;

  @Inject
  public SchedulerJobDic(SchedulerJobMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<SchedulerJobDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, SchedulerJobDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, SchedulerJobDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public SchedulerJobDomain get(int id) {

    return map.get(id);
  }
}
