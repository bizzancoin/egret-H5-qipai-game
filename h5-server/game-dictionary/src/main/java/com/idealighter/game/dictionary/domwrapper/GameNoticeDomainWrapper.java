
package com.idealighter.game.dictionary.domwrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.idealighter.game.dictionary.domain.GameNoticeDomain;

/**
 * GameNoticeDomainWrapper.
 *
 */
public class GameNoticeDomainWrapper extends GameNoticeDomain {
  private final GameNoticeDomain dom;
  // 解析后的condition
  private final JSONObject conditionData;

  /**
   * 构造函数.
   * 
   * @param dom .
   */
  public GameNoticeDomainWrapper(GameNoticeDomain dom) {
    super();
    this.dom = dom;
    this.conditionData = JSON.parseObject(dom.getCondition());
  }

  public JSONObject conditionData() {

    return conditionData;
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
  public int getGame() {
    return dom.getGame();
  }

  @Override
  public void setGame(int game) {
    dom.setGame(game);
  }

  @Override
  public String getCondition() {
    return dom.getCondition();
  }

  @Override
  public void setCondition(String condition) {
    dom.setCondition(condition);
  }

  @Override
  public String getDesc() {
    return dom.getDesc();
  }

  @Override
  public void setDesc(String desc) {
    dom.setDesc(desc);
  }

  @Override
  public int getMarqueeShow() {
    return dom.getMarqueeShow();
  }

  @Override
  public void setMarqueeShow(int marqueeShow) {
    dom.setMarqueeShow(marqueeShow);
  }

  @Override
  public int getChatShow() {
    return dom.getChatShow();
  }

  @Override
  public void setChatShow(int chatShow) {
    dom.setChatShow(chatShow);
  }

  @Override
  public String getContent() {
    return dom.getContent();
  }

  @Override
  public void setContent(String content) {
    dom.setContent(content);
  }
}
