package com.idealighter.game.core.service.prizepool.struct.control;

import com.idealighter.game.core.annotation.GameCtl;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.common.GameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameControlMap {
  private static final Logger LOG = LoggerFactory.getLogger(GameControlMap.class);
  private static final String CONTROL_PATH = GameControl.class.getPackage().getName();
  private static Map<Integer, Class<? extends GameControl>> map;

  static {
    map = new HashMap<Integer, Class<? extends GameControl>>();

    ResolverUtil<GameControl> resolver =
        new ResolverUtil<GameControl>().find(new AnnotatedWith(GameCtl.class), CONTROL_PATH);

    Set<Class<? extends GameControl>> classes = resolver.getClasses();
    if (classes != null && !classes.isEmpty()) {
      for (Class<? extends GameControl> clz : classes) {
        GameCtl ctl = clz.getAnnotation(GameCtl.class);
        int gameId = ctl.value();
        Game game = GameMap.getGameByGameId(gameId);
        if (game == null) {
          LOG.warn("游戏[{}]control控制有误", gameId);
          System.exit(1);
        }
        if (map.containsKey(gameId)) {
          LOG.warn("游戏[{}]control重复", gameId);
          System.exit(1);
        }
        map.put(gameId, clz);
      }
    }
  }

  public static Class<? extends GameControl> getGameControlGameId(int gameId) {
    return map.get(gameId);
  }
}
