
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.game.dictionary.mapper.GamesMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class GamesDic implements DictionaryTable<GamesDomain> {

  private GamesMapper mapper;
  private List<GamesDomain> list;
  private Map<Integer, GamesDomain> map;

  @Inject
  public GamesDic(GamesMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<GamesDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, GamesDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, GamesDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public GamesDomain get(int id) {

    return map.get(id);
  }
}
