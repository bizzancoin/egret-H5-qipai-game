
package com.idealighter.game.core.util;

import com.idealighter.game.core.common.Game;

/**
 * 游戏房间key工具类,根据游戏和房间id计算出一个唯一的long值做key.
 *
 */
public class GameRoomKeyUtil {

  private GameRoomKeyUtil() {}

  /**
   * 根据游戏id和房间id计算出一个唯一的long值做key.
   * 
   * @param game .
   * @param roomId .
   * @return .
   */
  public static long key(Game game, int roomId) {

    return key(game == null ? 0 : game.getType(), roomId);
  }

  /**
   * 根据游戏id和房间id计算出一个唯一的long值做key.
   * 
   * @param gameId .
   * @param roomId .
   * @return .
   */
  public static long key(int gameId, int roomId) {
    // 简写
    long l1 = (gameId & 0x00000000ffffffffL) << 32;
    long l2 = roomId & 0x00000000ffffffffL;

    return l1 | l2;
  }

  /**
   * 根据key计算出房间.
   * 
   * @param key .
   * @return .
   */
  public static int room(long key) {

    return (int) key;
  }

  /**
   * 根据key计算出游戏.
   * 
   * @param key .
   * @return .
   */
  public static Game game(long key) {

    return Game.getGame((int) (key >> 32));
  }
}
