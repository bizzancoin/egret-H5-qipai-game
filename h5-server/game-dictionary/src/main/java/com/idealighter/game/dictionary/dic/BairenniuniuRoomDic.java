
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.mapper.BairenniuniuRoomMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BairenniuniuRoomDic implements DictionaryTable<BairenniuniuRoomDomain> {

  private BairenniuniuRoomMapper mapper;
  private List<BairenniuniuRoomDomain> list;
  private Map<Integer, BairenniuniuRoomDomain> map;

  @Inject
  public BairenniuniuRoomDic(BairenniuniuRoomMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BairenniuniuRoomDomain> list() {
    return list;
  }

  @Override
  public Map<Integer, BairenniuniuRoomDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BairenniuniuRoomDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BairenniuniuRoomDomain get(int id) {
    return map.get(id);
  }
}
