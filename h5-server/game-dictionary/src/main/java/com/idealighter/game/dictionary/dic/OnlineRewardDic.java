
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.util.GameRoomKeyUtil;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.OnlineRewardDomain;
import com.idealighter.game.dictionary.mapper.OnlineRewardMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class OnlineRewardDic implements DictionaryTable<OnlineRewardDomain> {

  private OnlineRewardMapper mapper;
  private List<OnlineRewardDomain> list;
  private Map<Integer, OnlineRewardDomain> map;

  private Map<Long, OnlineRewardDomain> roomRewards;

  @Inject
  public OnlineRewardDic(OnlineRewardMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<OnlineRewardDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, OnlineRewardDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, OnlineRewardDomain> map = new HashMap<>();
    Map<Long, OnlineRewardDomain> roomRewards = new HashMap<>();

    list.forEach(val -> {
      map.put(val.getId(), val);
      roomRewards.put(GameRoomKeyUtil.key(val.getGame(), val.getRoom()), val);
    });
    this.map = map;
    this.roomRewards = roomRewards;
  }

  @Override
  public OnlineRewardDomain get(int id) {

    return map.get(id);
  }

  /**
   * 根据游戏和房间获取奖励配置.
   * @param gameId .
   * @param roomId .
   * @return .
   */
  public OnlineRewardDomain get(int gameId, int roomId) {

    return roomRewards.get(GameRoomKeyUtil.key(gameId, roomId));
  }
}
