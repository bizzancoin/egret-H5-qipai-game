package com.idealighter.game.dictionary.domwrapper;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.dictionary.domain.JcbyScenceDomain;

import java.util.List;

/**
 * JcbyScenceDomWrapper.
 *
 */
public class JcbyScenceDomWrapper extends JcbyScenceDomain {

  private final JcbyScenceDomain dom;
  private final List<Integer> strategysData;

  /**
   * 构造函数.
   * @param dom .
   */
  public JcbyScenceDomWrapper(JcbyScenceDomain dom) {
    this.dom = dom;
    this.strategysData = JSON.parseArray("[" + dom.getStrategys() + "]", Integer.class);
  }

  public List<Integer> getStrategysData() {
    return strategysData;
  }

  @Override
  public int getId() {
    return dom.getId();
  }

  @Override
  public void setId(int id) {
    dom.setId(id);
  }

  @Override
  public String getStrategys() {
    return dom.getStrategys();
  }

  @Override
  public void setStrategys(String strategys) {
    dom.setStrategys(strategys);
  }

  public String getDesc() {
    return dom.getRemark();
  }

  public void setDesc(String desc) {
    dom.setRemark(desc);
  }

  @Override
  public int getLifeTime() {
    return dom.getLifeTime();
  }

  @Override
  public void setLifeTime(int lifeTime) {
    dom.setLifeTime(lifeTime);
  }

}
