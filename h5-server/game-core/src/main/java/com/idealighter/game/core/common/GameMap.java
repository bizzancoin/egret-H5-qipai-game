package com.idealighter.game.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
  private static Map<Integer, Game> map;

  static {
    map = new HashMap<Integer, Game>();
    for (Game item : Game.values()) {
      map.put(item.getType(), item);
    }
  }

  public static Game getGameByGameId(int gameId) {
    return map.get(gameId);
  }

  /**
   * 获取所有游戏.
   * 
   * @Title allGame.
   * @author houdongsheng
   * @date 2018年1月27日 下午5:33:27
   * @return Game列表
   */
  public static List<Game> allGame() {
    List<Game> list = new ArrayList<Game>();
     list.add(Game.JCBY);
    list.add(Game.BAIREN_NIUNIU);
    list.add(Game.BACCARAT);
    return list;
  }

}
