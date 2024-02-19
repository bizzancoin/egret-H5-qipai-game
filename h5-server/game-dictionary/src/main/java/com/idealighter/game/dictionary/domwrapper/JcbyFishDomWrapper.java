package com.idealighter.game.dictionary.domwrapper;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.dictionary.domain.JcbyFishDomain;

import java.util.HashSet;
import java.util.Set;

/**
 * JcbyFishDomWrapper .
 *
 */

public class JcbyFishDomWrapper extends JcbyFishDomain {

  private final JcbyFishDomain dom;
  private final Set<Integer> fishsData;

  /**
   * 构造函数.
   * @param dom .
   */
  public JcbyFishDomWrapper(JcbyFishDomain dom) {
    this.dom = dom;
    this.fishsData = new HashSet<>(JSON.parseArray("[" + dom.getFishs() + "]", Integer.class));
  }

  public Set<Integer> getFishsData() {
    return fishsData;
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
  public int getBbxId() {
    return dom.getBbxId();
  }

  @Override
  public void setBbxId(int bbxId) {
    dom.setBbxId(bbxId);
  }

  @Override
  public int getLockLeve() {
    return dom.getLockLeve();
  }

  @Override
  public void setLockLeve(int lockLeve) {
    dom.setLockLeve(lockLeve);
  }

  @Override
  public int getType() {
    return dom.getType();
  }

  @Override
  public void setType(int type) {
    dom.setType(type);
  }

  @Override
  public String getFishs() {
    return dom.getFishs();
  }

  @Override
  public void setFishs(String fishs) {
    dom.setFishs(fishs);
  }

  @Override
  public int getMinRate() {
    return dom.getMinRate();
  }

  @Override
  public void setMinRate(int minRate) {
    dom.setMinRate(minRate);
  }

  @Override
  public int getMaxRate() {
    return dom.getMaxRate();
  }

  @Override
  public void setMaxRate(int maxRate) {
    dom.setMaxRate(maxRate);
  }

  @Override
  public int getDeathStrategy() {
    return dom.getDeathStrategy();
  }

  @Override
  public void setDeathStrategy(int deathStrategy) {
    dom.setDeathStrategy(deathStrategy);
  }

  @Override
  public int getNoticeType() {
    return dom.getNoticeType();
  }

  @Override
  public void setNoticeType(int noticeType) {
    dom.setNoticeType(noticeType);
  }

  @Override
  public String getNoticeContent() {
    return dom.getNoticeContent();
  }

  public void setNotice_content(String noticeContent) {
    dom.setNoticeContent(noticeContent);
  }

  @Override
  public String getDesc() {
    return dom.getDesc();
  }

  @Override
  public void setDesc(String desc) {
    dom.setDesc(desc);
  }

}
