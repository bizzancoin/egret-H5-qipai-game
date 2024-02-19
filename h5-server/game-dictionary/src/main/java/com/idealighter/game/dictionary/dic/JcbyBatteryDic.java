
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.JcbyBatteryDomain;
import com.idealighter.game.dictionary.mapper.JcbyBatteryMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class JcbyBatteryDic implements DictionaryTable<JcbyBatteryDomain> {

  private JcbyBatteryMapper mapper;
  private List<JcbyBatteryDomain> list;
  private Map<Integer, JcbyBatteryDomain> map;

  // 最大炮分数
  private int maxScore = 0;

  @Inject
  public JcbyBatteryDic(JcbyBatteryMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<JcbyBatteryDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, JcbyBatteryDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, JcbyBatteryDomain> map = new HashMap<>();
    int maxScore = 0;
    for (JcbyBatteryDomain val : list) {
      map.put(val.getId(), val);
      if (val.getScore() > maxScore) {
        maxScore = val.getScore();
      }
    }
    this.map = map;
    this.maxScore = maxScore;
  }

  @Override
  public JcbyBatteryDomain get(int id) {

    return map.get(id);
  }

  public int getMaxScore() {
    return maxScore;
  }

}
