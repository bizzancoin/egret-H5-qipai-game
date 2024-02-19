
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.SysconfigDomain;
import com.idealighter.game.dictionary.mapper.SysconfigMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class SysconfigDic implements DictionaryTable<SysconfigDomain> {

  private SysconfigMapper mapper;
  private List<SysconfigDomain> list;
  private Map<Integer, SysconfigDomain> map;

  @Inject
  public SysconfigDic(SysconfigMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<SysconfigDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, SysconfigDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, SysconfigDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;
  }

  @Override
  public SysconfigDomain get(int id) {

    return map.get(id);
  }

  /**
   * 获取int值.
   * 
   * @param id .
   * @return .
   */
  public int getIntVal(int id) {

    return Integer.parseInt(map.get(id).getVal());
  }

  /**
   * 获取string值.
   * 
   * @param id .
   * @return .
   */
  public String getStrVal(int id) {

    return map.get(id).getVal();
  }
}
