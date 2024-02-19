
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.mapper.JcbyRoomMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyRoomDic implements DictionaryTable<JcbyRoomDomain> {

  private JcbyRoomMapper mapper;
  private List<JcbyRoomDomain> list;
  private Map<Integer, JcbyRoomDomain> map;

  @Inject
  public JcbyRoomDic(JcbyRoomMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyRoomDomain> list() {
    return list;
  }

  @Override
  public Map<Integer, JcbyRoomDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, JcbyRoomDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public JcbyRoomDomain get(int id) {

    return map.get(id);
  }
}
