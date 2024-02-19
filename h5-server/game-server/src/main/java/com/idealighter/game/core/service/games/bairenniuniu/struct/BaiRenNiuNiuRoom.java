package com.idealighter.game.core.service.games.bairenniuniu.struct;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuDataMgr;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 百人牛牛房间.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaiRenNiuNiuRoom extends AbstractRoom {

  private final int instanceId;

  private BairenniuniuRoomDomain roomDomain;

  private InstanceState instanceState;

  // 桌子
  private final Map<Integer, BaiRenNiuNiuTable> tables;

  /**
   * 构造函数.
   * 
   * @param roomDom .
   */
  public BaiRenNiuNiuRoom(int instanceId, BairenniuniuRoomDomain roomDom) {
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
  private Map<Integer, BaiRenNiuNiuTable> createTables(int tableNum) {
    Map<Integer, BaiRenNiuNiuTable> tables = new LinkedHashMap<>();

    for (int i = 0; i < tableNum; i++) {
      int tableId = instanceId * AbstractTable.MAX_TABLE + i + 1;
      tables.put(tableId, new BaiRenNiuNiuTable(tableId, this));
    }

    return tables;
  }

  public BaiRenNiuNiuTable removeTable(int tableId) {
    return tables.remove(tableId);
  }

  @Override
  public Game game() {

    return Game.BAIREN_NIUNIU;
  }

  @Override
  public AbstractSeat seat(long playerId) {

    return ApplicationContext.getBean(BaiRenNiuNiuDataMgr.class).getPlayerSeat(playerId);
  }

  @Override
  public Collection<BaiRenNiuNiuTable> tables() {

    return tables.values();
  }

}
