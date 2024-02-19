
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.mapper.BaccaratRoomMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class BaccaratRoomDic implements DictionaryTable<BaccaratRoomDomain> {

  private BaccaratRoomMapper mapper;
  private List<BaccaratRoomDomain> list;
  private Map<Integer, BaccaratRoomDomain> map;

  @Inject
  public BaccaratRoomDic(BaccaratRoomMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<BaccaratRoomDomain> list() {
    return list;
  }

  @Override
  public Map<Integer, BaccaratRoomDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, BaccaratRoomDomain> map = new HashMap<>();
    list.stream().filter(item -> item.getIsActive() == RoomActiveConstant.ACTIVE)
    .collect(Collectors.toList());
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public BaccaratRoomDomain get(int id) {

    return map.get(id);
  }
}
