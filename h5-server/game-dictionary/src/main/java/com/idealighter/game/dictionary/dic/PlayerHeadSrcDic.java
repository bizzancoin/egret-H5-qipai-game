
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.PlayerHeadSrcDomain;
import com.idealighter.game.dictionary.mapper.PlayerHeadSrcMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class PlayerHeadSrcDic implements DictionaryTable<PlayerHeadSrcDomain> {

  private PlayerHeadSrcMapper mapper;
  private List<PlayerHeadSrcDomain> list;
  private Map<Integer, PlayerHeadSrcDomain> map;
  // 玩家图标集合
  private List<Integer> icons;

  @Inject
  public PlayerHeadSrcDic(PlayerHeadSrcMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<PlayerHeadSrcDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, PlayerHeadSrcDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, PlayerHeadSrcDomain> map = new HashMap<>();
    List<Integer> iconsTmp = new ArrayList<>();
    list.forEach(val -> {
      map.put(val.getId(), val);
      iconsTmp.add(val.getId());
    });
    this.map = map;
    this.icons = iconsTmp;
  }

  @Override
  public PlayerHeadSrcDomain get(int id) {

    return map.get(id);
  }

  public List<Integer> getIcons() {
    return icons;
  }
}
