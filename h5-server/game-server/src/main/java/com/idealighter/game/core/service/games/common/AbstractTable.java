
package com.idealighter.game.core.service.games.common;

import com.idealighter.game.gamehall.dto.TableInfo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

/**
 * 牌桌.
 * 
 * @date 2015年8月6日 下午8:49:25
 *
 */
@Data
public abstract class AbstractTable {
  // 最大桌子数10w
  public static final int MAX_TABLE = 100000;

  // 桌子id
  protected final int id;

  // 进桌玩家限制金币
  protected long limitGold = 0;
  // 进桌玩家限制ip
  protected String limitIp = null;
  // 桌子密码
  protected String password = null;

  /**
   * 桌子id构造函数.
   * 
   * @param id .
   */
  public AbstractTable(int id) {
    this.id = id;
  }

  /**
   * 桌子信息.
   * 
   * @return
   */
  public TableInfo tableInfo() {
    TableInfo tableInfo = new TableInfo();
    tableInfo.setId(id);
    AbstractRoom room = room();
    tableInfo.setRoomId(room.getId());
    tableInfo.setHasPwd(password != null ? 1 : 0);

    for (AbstractSeat seat : seats()) {
      tableInfo.getSeats().add(seat.seatInfo());
    }

    return tableInfo;
  }
  
  /**
   * 判断机器人在桌子上是否满.
   * 
   * @date 2018年7月27日 下午3:03:25
   * @return
   */
  public abstract boolean robotFull();

  /**
   * 桌子中的玩家.
   * 
   * @return
   */
  public Collection<Long> players() {
    Set<Long> players = new HashSet<>();
    for (AbstractSeat s : seats()) {
      if (s.playerId > 0) {
        players.add(s.playerId);
      }
    }

    return players;
  }

  // 房间所属的房间
  public abstract AbstractRoom room();

  public abstract List<? extends AbstractSeat> seats();
}
