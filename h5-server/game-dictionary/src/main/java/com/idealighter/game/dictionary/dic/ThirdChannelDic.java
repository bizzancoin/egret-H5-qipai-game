
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.ThirdChannelDomain;
import com.idealighter.game.dictionary.mapper.ThirdChannelMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ThirdChannelDic implements DictionaryTable<ThirdChannelDomain> {

  private ThirdChannelMapper mapper;
  private List<ThirdChannelDomain> list;
  private Map<String, ThirdChannelDomain> map;

  @Inject
  public ThirdChannelDic(ThirdChannelMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<ThirdChannelDomain> list() {
    return list;
  }


  @Override
  public void load() {
    List<ThirdChannelDomain> list = new ArrayList<>();
    Map<String, ThirdChannelDomain> map = new HashMap<>();

    list = mapper.selectAll();

    if (EmptyUtil.listIsNotEmpty(list)) {
      list.forEach(dom -> {
        map.put(dom.getChannelId(), dom);
      });
    }
    this.list = list;
    this.map = map;
  }

  @Override
  public Map<Integer, ThirdChannelDomain> map() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ThirdChannelDomain get(int id) {
    // TODO Auto-generated method stub
    return null;
  }

  public ThirdChannelDomain getByChannelId(String channelId) {
    return map.get(channelId);
  }


}
