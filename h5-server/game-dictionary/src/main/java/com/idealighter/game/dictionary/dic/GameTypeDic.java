
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.GameTypeDomain;
import com.idealighter.game.dictionary.domwrapper.GameTypeDomainWrapper;
import com.idealighter.game.dictionary.mapper.GameTypeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class GameTypeDic implements DictionaryTable<GameTypeDomainWrapper> {

  private GameTypeMapper mapper;
  private List<GameTypeDomainWrapper> list;
  private Map<Integer, GameTypeDomainWrapper> map;

  @Inject
  public GameTypeDic(GameTypeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<GameTypeDomainWrapper> list() {

    return list;
  }

  @Override
  public Map<Integer, GameTypeDomainWrapper> map() {

    return map;
  }

  @Override
  public void load() {
    List<GameTypeDomain> domains = mapper.selectAll();
    List<GameTypeDomainWrapper> domainWrappers = new ArrayList<>();
    for (GameTypeDomain domain : domains) {
      domainWrappers.add(new GameTypeDomainWrapper(domain));
    }
    this.list = domainWrappers;

    Map<Integer, GameTypeDomainWrapper> map = new HashMap<>();
    domainWrappers.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public GameTypeDomainWrapper get(int id) {

    return map.get(id);
  }
}
