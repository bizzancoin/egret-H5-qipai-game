package com.idealighter.game.core.service.games.baccarat.struct;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.baccarat.manager.BaccaratDataMgr;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 百家乐房间 .
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaccaratRoom extends AbstractRoom {

  // 桌子
  private final Map<Integer, BaccaratTable> tables;

  private final int instanceId;

  private BaccaratRoomDomain roomDomain;

  private InstanceState instanceState;

  /**
   * 构造函数.
   * 
   * @param roomDom .
   */
  public BaccaratRoom(int instanceId, BaccaratRoomDomain roomDom) {
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
   * @return
   */
  private Map<Integer, BaccaratTable> createTables(int tableNum) {
    Map<Integer, BaccaratTable> tables = new LinkedHashMap<>();

    for (int i = 0; i < tableNum; i++) {
      int tableId = instanceId * AbstractTable.MAX_TABLE + i + 1;
      tables.put(tableId, new BaccaratTable(tableId, this));
    }

    return tables;
  }

  @Override
  public Game game() {

    return Game.BACCARAT;
  }


  @Override
  public AbstractSeat seat(long playerId) {

    return ApplicationContext.getBean(BaccaratDataMgr.class).getPlayerSeat(playerId);
  }

  @Override
  public Collection<BaccaratTable> tables() {

    return tables.values();
  }

  public BaccaratTable removeTable(int tableId) {
    return tables.remove(tableId);
  }

}
