package com.idealighter.game.core.service.games.jcby.struct;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.jcby.manager.JcbyDataMgr;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 金蝉捕鱼房间 .
 * 
 * @date 2016年3月7日 下午8:58:36
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JcbyRoom extends AbstractRoom {
  // 桌子
  private final Map<Integer, JcbyTable> tables;
  // 李逵fishId
  private final int likui;
  // 李逵的最小倍数
  private final int likuiMinRate;
  // 李逵的最大倍数
  private final int likuiMaxRate;
  // 李逵的升级分数(即最大炮分数)
  private final int likuiUpgradeScore;
  // 李逵吃子弹的数量
  private int likuiEatBullets = 0;

  private final int instanceId;

  private InstanceState instanceState;

  private JcbyRoomDomain roomDomain;

  /**
   * 构造函数 .
   * 
   * @param roomDom .
   * @param likui .
   * @param likuiMinRate .
   * @param likuiMaxRate .
   * @param likuiUpgradeScore .
   */
  public JcbyRoom(int instanceId, JcbyRoomDomain roomDom, int likui, int likuiMinRate,
      int likuiMaxRate, int likuiUpgradeScore) {
    super(roomDom.getId(), roomDom.getName());
    this.instanceState = InstanceState.NORMAL;
    this.instanceId = instanceId;
    this.roomDomain = roomDom;
    this.likui = roomDom.getLiKui();
    this.likuiMinRate = likuiMinRate;
    this.likuiMaxRate = likuiMaxRate;
    this.likuiUpgradeScore = likuiUpgradeScore;
    this.tables = createTables(roomDom.getTableNum(), roomDom.getChair(),
        JSON.parseArray("[" + roomDom.getScences() + "]", Integer.class));
  }

  /**
   * 创建桌子 .
   * 
   * @param tableNum .
   * @param chairNum .
   * @return
   */
  private Map<Integer, JcbyTable> createTables(int tableNum, int chairNum, List<Integer> scences) {
    Map<Integer, JcbyTable> tables = new LinkedHashMap<>();
    for (int i = 0; i < tableNum; i++) {
      int tableId = instanceId * AbstractTable.MAX_TABLE + i + 1;
      tables.put(tableId, new JcbyTable(tableId, this, chairNum, scences));
    }

    return tables;
  }

  /**
   * 李逵倍率 .
   * 
   * @return
   */
  public int likuiMultiple() {
    int likuiRate = likuiMinRate + likuiEatBullets / likuiUpgradeScore;

    return likuiRate > likuiMaxRate ? likuiMaxRate : likuiRate;
  }

  public JcbyTable removeTable(int tableId) {
    return tables.remove(tableId);
  }

  @Override
  public Game game() {
    return Game.JCBY;
  }

  @Override
  public AbstractSeat seat(long playerId) {
    return ApplicationContext.getBean(JcbyDataMgr.class).getPlayerSeat(playerId);
  }

  @Override
  public Collection<JcbyTable> tables() {
    return tables.values();
  }

}
