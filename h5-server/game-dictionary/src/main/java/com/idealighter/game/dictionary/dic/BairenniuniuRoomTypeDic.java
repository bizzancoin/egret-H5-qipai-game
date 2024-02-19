
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomTypeDomain;
import com.idealighter.game.dictionary.mapper.BairenniuniuRoomTypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BairenniuniuRoomTypeDic implements DictionaryTable<BairenniuniuRoomTypeDomain> {

  private BairenniuniuRoomTypeMapper mapper;
  private List<BairenniuniuRoomTypeDomain> list;
  private Map<Integer, BairenniuniuRoomTypeDomain> map;

  @Inject
  public BairenniuniuRoomTypeDic(BairenniuniuRoomTypeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BairenniuniuRoomTypeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, BairenniuniuRoomTypeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BairenniuniuRoomTypeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BairenniuniuRoomTypeDomain get(int id) {
    return map.get(id);
  }
}
