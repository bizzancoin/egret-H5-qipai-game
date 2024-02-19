package com.idealighter.game.dictionary.dic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.DdzRoomTypeDomain;
import com.idealighter.game.dictionary.mapper.DdzRoomTypeMapper;

@Singleton
public class DdzRoomTypeDic implements DictionaryTable<DdzRoomTypeDomain> {

  private DdzRoomTypeMapper mapper;
  private List<DdzRoomTypeDomain> list;
  private Map<Integer, DdzRoomTypeDomain> map;

  @Inject
  public DdzRoomTypeDic(DdzRoomTypeMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<DdzRoomTypeDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, DdzRoomTypeDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, DdzRoomTypeDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public DdzRoomTypeDomain get(int id) {

    return map.get(id);
  }
}
