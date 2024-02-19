
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.LevelExpDomain;
import com.idealighter.game.dictionary.mapper.LevelExpMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class LevelExpDic implements DictionaryTable<LevelExpDomain> {

  private int maxLevel = 0;

  private LevelExpMapper mapper;
  private List<LevelExpDomain> list;
  private Map<Integer, LevelExpDomain> map;

  public int getMaxLevel() {
    return maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  @Inject
  public LevelExpDic(LevelExpMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<LevelExpDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, LevelExpDomain> map() {
    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, LevelExpDomain> map = new HashMap<>();
    list.forEach(val -> {
      if (maxLevel < val.getLevel()) {
        maxLevel = val.getLevel();
      }
      map.put(val.getLevel(), val);
    });
    this.map = map;
  }

  @Override
  public LevelExpDomain get(int level) {

    return map.get(level);
  }
}
