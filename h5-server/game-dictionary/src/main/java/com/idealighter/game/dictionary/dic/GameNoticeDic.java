
package com.idealighter.game.dictionary.dic;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.GameNoticeDomain;
import com.idealighter.game.dictionary.domwrapper.GameNoticeDomainWrapper;
import com.idealighter.game.dictionary.mapper.GameNoticeMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class GameNoticeDic implements DictionaryTable<GameNoticeDomainWrapper> {

  private GameNoticeMapper mapper;
  private List<GameNoticeDomainWrapper> list;
  private Map<Integer, GameNoticeDomainWrapper> map;
  // key:游戏id
  private Multimap<Integer, GameNoticeDomainWrapper> gameDoms;

  @Inject
  public GameNoticeDic(GameNoticeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<GameNoticeDomainWrapper> list() {

    return list;
  }

  @Override
  public Map<Integer, GameNoticeDomainWrapper> map() {

    return map;
  }

  @Override
  public void load() {
    List<GameNoticeDomain> domains = mapper.selectAll();
    List<GameNoticeDomainWrapper> domainWrappers = new ArrayList<>();
    for (GameNoticeDomain domain : domains) {
      domainWrappers.add(new GameNoticeDomainWrapper(domain));
    }

    Map<Integer, GameNoticeDomainWrapper> map = new HashMap<>();
    Multimap<Integer, GameNoticeDomainWrapper> gameDoms = HashMultimap.create();
    domainWrappers.forEach(val -> {
      map.put(val.getId(), val);
      gameDoms.put(val.getGame(), val);
    });

    this.list = domainWrappers;
    this.map = map;
    this.gameDoms = gameDoms;
  }

  @Override
  public GameNoticeDomainWrapper get(int id) {

    return map.get(id);
  }

  public Multimap<Integer, GameNoticeDomainWrapper> getGameDoms() {
    return gameDoms;
  }

}
