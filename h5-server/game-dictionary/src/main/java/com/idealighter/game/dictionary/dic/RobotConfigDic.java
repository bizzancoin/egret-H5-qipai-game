
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.util.GameRoomKeyUtil;
import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.dictionary.domwrapper.RobotConfigDomWrapper;
import com.idealighter.game.dictionary.mapper.RobotConfigMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RobotConfigDic implements DictionaryTable<RobotConfigDomWrapper> {

  private RobotConfigMapper mapper;
  private List<RobotConfigDomWrapper> list;
  private Map<Integer, RobotConfigDomWrapper> map;
  // key是根据gameId和roomId计算出来的一个long值
  private Map<Long, RobotConfigDomWrapper> robotCfgs;

  @Inject
  public RobotConfigDic(RobotConfigMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<RobotConfigDomWrapper> list() {

    return list;
  }

  @Override
  public Map<Integer, RobotConfigDomWrapper> map() {

    return map;
  }

  @Override
  public void load() {
    List<RobotConfigDomWrapper> list = new ArrayList<>();
    Map<Integer, RobotConfigDomWrapper> map = new HashMap<>();
    Map<Long, RobotConfigDomWrapper> robotCfgs = new HashMap<>();

    for (RobotConfigDomain robotConfigDom : mapper.selectAll()) {
      if (robotConfigDom.getState() == 0) {
        continue;
      }
      list.add(new RobotConfigDomWrapper(robotConfigDom));
    }

    list.forEach(val -> {
      map.put(val.getId(), val);
      robotCfgs.put(GameRoomKeyUtil.key(val.getGame(), val.getRoom()), val);
    });

    this.list = list;
    this.map = map;
    this.robotCfgs = robotCfgs;
  }

  /**
   * 根据游戏和房间获取机器人配置.
   * 
   * @param game .
   * @param roomId .
   * @return .
   */
  public RobotConfigDomWrapper getRobotCfg(Game game, int roomId) {

    return robotCfgs.get(GameRoomKeyUtil.key(game, roomId));
  }

  @Override
  public RobotConfigDomWrapper get(int id) {

    return map.get(id);
  }
}
