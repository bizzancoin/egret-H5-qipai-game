
package com.idealighter.game.core.service.games.common;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.gamehall.dto.MemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * 游戏房间 .
 * 
 * 
 * @date 2015年8月6日 下午6:08:31
 *
 */
@Data
public abstract class AbstractRoom {
  // 最大桌子数10
  public static final int MAX_INSTANCE = 100;
  // 房间id
  protected final int id;
  // 房间名称
  protected final String name;
  // 房间中的玩家
  protected final Set<Long> players = new HashSet<>();

  /**
   * 构造函数.
   * 
   * @param id .
   * @param name .
   */
  protected AbstractRoom(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * 玩家房间成员信息.
   * 
   * @param playerId 玩家id.
   * @return
   */
  public MemInfo memInfo(long playerId) {
    if (!players.contains(playerId)) {
      return null;
    }

    PlayerMgr playerMgr = ApplicationContext.getBean(PlayerMgr.class);
    Player player = playerMgr.getPlayer(playerId);
    if (player == null) {
      return null;
    }

    MemInfo memInfo = player.baseMemInfo();
    AbstractSeat seat = seat(playerId);
    if (seat != null) {
      memInfo.setTableId(seat.table().id);
      memInfo.setOrder(seat.order);
      memInfo.setChips(seat.totalChips);
      memInfo.setState(seat.state != null ? seat.state.val : 0);
    }

    return memInfo;
  }

  /**
   * 房间中成员的信息 .
   * 
   * @return
   */
  public List<MemInfo> memInfos() {
    List<MemInfo> memInfos = new ArrayList<>();

    for (Long playerId : players) {
      MemInfo memInfo = memInfo(playerId);
      if (memInfo != null) {
        memInfos.add(memInfo);
      }
    }

    return memInfos;
  }


  /**
   * 房间所属的游戏 .
   * 
   * @return
   */
  public abstract Game game();

  /**
   * 房间中玩家位置 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  public abstract AbstractSeat seat(long playerId);

  /**
   * 房间中的桌子 .
   * 
   * @return
   */
  public abstract Collection<? extends AbstractTable> tables();

}
