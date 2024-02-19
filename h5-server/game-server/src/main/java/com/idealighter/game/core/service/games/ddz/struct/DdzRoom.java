package com.idealighter.game.core.service.games.ddz.struct;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.ddz.manager.DdzDataMgr;
import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 斗地主房间。房间，牌桌，座位之间的包含关系使用的内部类实现.
 * 
 * @date 2015年8月6日 下午4:04:49
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DdzRoom extends AbstractRoom {
  private final int instanceId;
  private InstanceState instanceState;
  private DdzRoomDomain roomDomain;
  // 桌子
  private final Map<Integer, DdzTable> tables;


  // // 当前比赛
  // public volatile DdzMatch curMatch = null;
  // // 游戏中的比赛
  // public List<DdzMatch> gamingMatchs = new ArrayList<>();

  /**
   * 构造函数 .
   * 
   * @param roomDom .
   */
  public DdzRoom(int instanceId, DdzRoomDomain roomDom) {
    super(roomDom.getId(), roomDom.getName());
    this.instanceState = InstanceState.NORMAL;
    this.instanceId = instanceId;
    this.roomDomain = roomDom;
    this.tables = createTables(roomDom.getTableNum());
  }

  /**
   * 创建桌子 .
   * 
   * @param tableNum .
   * @return .
   */
  private Map<Integer, DdzTable> createTables(int tableNum) {
    Map<Integer, DdzTable> tables = new LinkedHashMap<>();

    for (int i = 0; i < tableNum; i++) {
      int tableId = instanceId * AbstractTable.MAX_TABLE + i + 1;
      tables.put(tableId, new DdzTable(tableId, this));
    }

    return tables;
  }

  public DdzTable removeTable(int tableId) {
    return tables.remove(tableId);
  }

  @Override
  public Game game() {
    return Game.DDZ;
  }

  @Override
  public DdzSeat seat(long playerId) {
    return ApplicationContext.getBean(DdzDataMgr.class).getPlayerSeat(playerId);
  }

  @Override
  public Collection<DdzTable> tables() {
    return tables.values();
  }

}
