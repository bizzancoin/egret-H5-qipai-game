package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.dictionary.mapper.DdzRoomMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DdzRoomDic implements DictionaryTable<DdzRoomDomain> {

  private DdzRoomMapper mapper;
  private List<DdzRoomDomain> list;
  private Map<Integer, DdzRoomDomain> map;

  @Inject
  public DdzRoomDic(DdzRoomMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<DdzRoomDomain> list() {
    return list;
  }

  @Override
  public Map<Integer, DdzRoomDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, DdzRoomDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public DdzRoomDomain get(int id) {

    return map.get(id);
  }
}
