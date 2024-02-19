
package com.idealighter.game.dictionary.domwrapper;

import com.alibaba.fastjson.JSONArray;

import com.idealighter.game.dictionary.domain.GameTypeDomain;

import java.util.List;

/**
 * GameTypeDomainWrapper.
 *
 */
public class GameTypeDomainWrapper extends GameTypeDomain {
  private final GameTypeDomain domain;
  private final List<Integer> games;

  /**
   * 构造函数.
   * @param domain .
   */
  public GameTypeDomainWrapper(GameTypeDomain domain) {
    super();
    this.domain = domain;
    this.games = JSONArray.parseArray("[" + domain.getGames() + "]", Integer.class);
  }

  public GameTypeDomain domain() {
    return domain;
  }

  public List<Integer> games() {
    return games;
  }

  @Override
  public int getId() {
    return domain.getId();
  }

  @Override
  public void setId(int id) {
    domain.setId(id);
  }

  @Override
  public String getName() {
    return domain.getName();
  }

  @Override
  public void setName(String name) {
    domain.setName(name);
  }

  @Override
  public int getIndex() {
    return domain.getIndex();
  }

  @Override
  public void setIndex(int index) {
    domain.setIndex(index);
  }

  @Override
  public String getGames() {
    return domain.getGames();
  }

  @Override
  public void setGames(String games) {
    domain.setGames(games);
  }

}
