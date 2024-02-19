package com.idealighter.game.core.service.games.jcby.manager;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.common.buyu.Road;
import com.idealighter.game.core.service.games.common.buyu.strategy.CircleStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.GeneralStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.LineStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.PointStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.RowStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.ShoalStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.SpreadStrategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.Strategy;
import com.idealighter.game.core.service.games.common.buyu.strategy.StrategyType;
import com.idealighter.game.core.service.games.jcby.struct.JcbyRoom;
import com.idealighter.game.core.service.games.jcby.struct.JcbySeat;
import com.idealighter.game.core.service.games.jcby.struct.JcbyTable;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.dic.JcbyBatteryDic;
import com.idealighter.game.dictionary.dic.JcbyCurveDic;
import com.idealighter.game.dictionary.dic.JcbyFishDic;
import com.idealighter.game.dictionary.dic.JcbyRoomDic;
import com.idealighter.game.dictionary.dic.JcbyScenceDic;
import com.idealighter.game.dictionary.dic.JcbyStrategyDic;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.game.dictionary.domain.JcbyCurveDomain;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.domain.JcbyStrategyDomain;
import com.idealighter.game.dictionary.domwrapper.JcbyFishDomWrapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 金蝉捕鱼数据管理 .
 * 
 * @date 2016年3月7日 下午8:55:42
 *
 */
@Singleton
public class JcbyDataMgr {

  private static final Logger LOG = LoggerFactory.getLogger(JcbyDataMgr.class);

  // 玩家的座位
  private final Map<Long, JcbySeat> playerSeats = new HashMap<>();
  // 刷鱼策略
  private final Map<Integer, Strategy> strategys = new HashMap<>();
  // 鱼的路径
  private final Map<Integer, Road> roads = new HashMap<>();
  // 游戏房间
  private final Map<Integer, JcbyBaseRoom> roomMap = new HashMap<>();
  private final JcbyRoomDic roomDic;
  private final JcbyFishDic fishDic;
  private final JcbyBatteryDic batteryDic;

  private final JcbyStrategyDic strategyDic;
  private final JcbyCurveDic curveDic;

  private final GamesDic gamesDic;
  
  public static volatile boolean roomEmpty = false;

  /**
   * 构造函数 .
   * 
   * @param roomDic .
   * @param fishDic .
   * @param batteryDic .
   * @param curveDic .
   * @param scenceDic .
   * @param strategyDic .
   */
  @Inject
  public JcbyDataMgr(JcbyRoomDic roomDic, JcbyFishDic fishDic, JcbyBatteryDic batteryDic,
      JcbyCurveDic curveDic, JcbyScenceDic scenceDic, JcbyStrategyDic strategyDic,
      GamesDic gamesDic) {
    this.roomDic = roomDic;
    this.fishDic = fishDic;
    this.batteryDic = batteryDic;
    this.curveDic = curveDic;
    this.strategyDic = strategyDic;
    this.gamesDic = gamesDic;
    createRooms();
    init();
  }

  private void createRoom(JcbyRoomDomain roomDom, int maxScore) {
    if (roomDom != null && roomDom.getIsActive() == RoomActiveConstant.ACTIVE) {
      GamesDomain gamesDom = gamesDic.get(Game.JCBY.getType());
      if (gamesDom != null && gamesDom.getActive()) {
        int liKui = roomDom.getLiKui();
        if (liKui > 0) {
          JcbyFishDomWrapper fishDom = fishDic.get(liKui);
          this.roomMap.put(roomDom.getId(), new JcbyBaseRoom(roomDom, fishDom.getId(),
              fishDom.getMinRate(), fishDom.getMaxRate(), maxScore));
        } else {
          this.roomMap.put(roomDom.getId(), new JcbyBaseRoom(roomDom, 0, 0, 0, maxScore));
        }
        LOG.info("[金蝉捕鱼]创建房间[{}][{}]", roomDom.getId(), roomDom.getName());
      }
      
      roomEmpty = roomMap.values() == null || roomMap.values().isEmpty();
    }
  }

  /**
   * 创建房间 .
   * 
   * @return
   */
  private void createRooms() {
    // 最大炮分数
    int maxScore = batteryDic.getMaxScore();
    List<JcbyRoomDomain> roomDomains = roomDic.list();
    if (EmptyUtil.listIsNotEmpty(roomDomains)) {
      for (JcbyRoomDomain roomDom : roomDomains) {
        createRoom(roomDom, maxScore);
      }
    }
  }

  /**
   * 初始化刷鱼策略和鱼的路径 .
   */
  private void init() {
    for (JcbyStrategyDomain strategyDom : strategyDic.list()) {

      StrategyType type = StrategyType.get(strategyDom.getType());
      Strategy strategy = null;

      switch (type) {
        case CIRCLE:
          strategy = JSON.parseObject(strategyDom.getData(), CircleStrategy.class);
          break;
        case SPREAD:
          strategy = JSON.parseObject(strategyDom.getData(), SpreadStrategy.class);
          break;
        case GENERAL:
          strategy = JSON.parseObject(strategyDom.getData(), GeneralStrategy.class);
          break;
        case LINE:
          strategy = JSON.parseObject(strategyDom.getData(), LineStrategy.class);
          break;
        case POINT:
          strategy = JSON.parseObject(strategyDom.getData(), PointStrategy.class);
          break;
        case ROW:
          strategy = JSON.parseObject(strategyDom.getData(), RowStrategy.class);
          break;
        case SHOAL:
          strategy = JSON.parseObject(strategyDom.getData(), ShoalStrategy.class);
          break;
        default:
          break;
      }

      strategys.put(strategyDom.getId(), strategy);
    }

    for (JcbyCurveDomain curveDom : curveDic.list()) {
      Road road = new Road(curveDom);
      roads.put(road.id, road);
    }
  }

  /**
   * 获取策略 .
   * 
   * @param strategyId .
   * @return
   */
  public Strategy getStrategy(int strategyId) {

    return strategys.get(strategyId);
  }

  /**
   * 重新加载房间，并返回最新房间id列表.
   *
   * @author abin
   * @date 2018年5月16日 下午2:48:38
   * @return 房间id列表.
   */
  public List<Integer> reloadRoom() {
    roomDic.load();
    List<Integer> ids = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.JCBY.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      List<JcbyRoomDomain> rooms = roomDic.list();
      if (EmptyUtil.listIsNotEmpty(rooms)) {
        for (JcbyRoomDomain roomDomain : rooms) {
          if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
            ids.add(roomDomain.getId());
          }
        }
      }
    }
    return ids;
  }


  /**
   * 开启房间.
   *
   * @author abin
   * @date 2018年4月28日 下午2:14:26
   * @param roomId 房间id.
   * @return return old room(delete) list.
   */
  public List<JcbyRoom> startRoom(int roomId, boolean reload) {
    if (reload) {
      roomDic.load();
    }
    List<JcbyRoom> deleteRooms = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.JCBY.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      JcbyRoomDomain roomDomain = roomDic.get(roomId);
      if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {

        JcbyBaseRoom baseRoom = roomMap.get(roomId);

        int maxScore = batteryDic.getMaxScore();
        if (baseRoom == null) {
          createRoom(roomDomain, maxScore);
        } else {
          int liKui = roomDomain.getLiKui();
          if (liKui > 0) {
            JcbyFishDomWrapper fishDom = fishDic.get(liKui);
            deleteRooms = baseRoom.renewRoom(roomDomain, fishDom.getId(), fishDom.getMinRate(),
                fishDom.getMaxRate(), maxScore);
          } else {
            deleteRooms = baseRoom.renewRoom(roomDomain, 0, 0, 0, maxScore);
          }
        }
      }
    }
    return deleteRooms;
  }

  /**
   * 获取最新房间实例.
   * 
   * @param roomId 房间id.
   * @return 21点房间.
   */
  public JcbyRoom getNewestRoom(int roomId) {
    JcbyRoom room = null;
    JcbyBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      room = baseRoom.getNewInstance();
    }
    return room;
  }


  /**
   * 获取所有实例 .
   *
   * @author abin
   * @date 2018年4月28日 下午4:32:06
   * @param roomId 房间id.
   * @return 获取所有实例.
   */
  public List<JcbyRoom> deleteRoom(int roomId) {
    roomDic.load();
    List<JcbyRoom> rooms = null;
    JcbyBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      rooms = new ArrayList<>(baseRoom.deleteAllInstance());
    }
    return rooms;
  }

  /**
   * 实例id获取房间实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午6:01:41
   * @param instanceId 实例id.
   * @return 21点房间.
   */
  public JcbyRoom getRoomByIntanceId(int instanceId) {
    JcbyRoom room = null;
    int roomId = getRoomIdByIntanceId(instanceId);
    JcbyBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      room = baseRoom.getByInstanceId(instanceId);
    }
    return room;
  }


  /**
   * 校验是否remove room.
   *
   * @author abin
   * @date 2018年4月28日 下午7:39:33
   * @param baseRoom 基本房间.
   */
  public void checkRemoveRoom(JcbyBaseRoom baseRoom) {
    if (baseRoom.allInstance() == null || baseRoom.allInstance().isEmpty()) {
      roomMap.remove(baseRoom.getRoomId());
      baseRoom = null;
    }

    roomEmpty = roomMap.values() == null || roomMap.values().isEmpty();
  }

  /**
   * 清空房间实例.
   *
   * @author abin
   * @date 2018年4月28日 下午5:55:32
   * @param roomId 房间id.
   * @param instanceId 实例id.
   */
  public boolean checkRemoveInstance(int roomId, int instanceId) {
    boolean remove = false;
    JcbyBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      JcbyRoom room = baseRoom.getByInstanceId(instanceId);

      Collection<JcbyTable> tables = room.tables();

      if (tables == null || tables.isEmpty()) {
        baseRoom.removeInstance(instanceId);
        checkRemoveRoom(baseRoom);
        remove = true;
      }
    }
    return remove;
  }

  /**
   * 获取所有最新的房间.
   * 
   * @return 21点房间列表.
   */
  public Collection<JcbyRoom> newestRooms() {
    List<JcbyRoom> tempRooms = new ArrayList<>();
    for (JcbyBaseRoom baseRoom : roomMap.values()) {
      JcbyRoom jcbyRoom = baseRoom.getNewInstance();
      if (jcbyRoom != null && jcbyRoom.getInstanceState().equals(InstanceState.NORMAL)) {
        tempRooms.add(jcbyRoom);
      }
    }
    return tempRooms;
  }

  /**
   * 获取所有的房间列表 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:22:40
   * @return 所有的房间列表.
   */
  public Collection<JcbyRoom> allRooms() {
    List<JcbyRoom> tempRooms = new ArrayList<>();
    for (JcbyBaseRoom baseRoom : roomMap.values()) {
      Collection<JcbyRoom> allInstances = baseRoom.allInstance();
      if (allInstances != null && !allInstances.isEmpty()) {
        tempRooms.addAll(allInstances);
      }
    }
    return tempRooms;
  }

  public Collection<JcbyBaseRoom> allBaseRoom() {
    return roomMap.values();
  }



  /**
   * 获取牌桌.
   * 
   * @param tableId 桌子id.
   * @return 21点桌子.
   */
  public JcbyTable getTable(int tableId) {
    JcbyTable table = null;
    int roomId = getRoomIdByTableId(tableId);
    JcbyBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      int instanceId = getInstanceIdByTableId(tableId);
      JcbyRoom room = baseRoom.getByInstanceId(instanceId);
      if (room != null) {
        table = room.getTables().get(tableId);
      }
    }
    return table;
  }

  /**
   * 根据桌子id计算房间实例id.
   * 
   * @param tableId 桌子Id.
   * @return 房间Id.
   */
  public int getInstanceIdByTableId(int tableId) {
    return tableId / JcbyTable.MAX_TABLE;
  }

  /**
   * 根据实例id计算房间id .
   *
   * @author abin
   * @date 2018年4月27日 下午5:54:05
   * @param instanceId 实例id.
   * @return 房间id.
   */
  public int getRoomIdByIntanceId(int instanceId) {
    return instanceId / JcbyRoom.MAX_INSTANCE;
  }

  /**
   * 根据桌子获取房间id .
   *
   * @author abin
   * @date 2018年4月27日 下午5:56:03
   * @param tableId 桌子id.
   * @return 房间id.
   */
  public int getRoomIdByTableId(int tableId) {
    return getRoomIdByIntanceId(getInstanceIdByTableId(tableId));
  }


  /**
   * 获取座位 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  public JcbySeat getPlayerSeat(long playerId) {

    return playerSeats.get(playerId);
  }

  /**
   * 更新玩家座位 .
   * 
   * @param playerId 玩家id.
   * @param seat 座位信息.
   */
  public void updatePlayerSeats(long playerId, JcbySeat seat) {
    playerSeats.put(playerId, seat);
  }

  /**
   * 移除玩家seat .
   * 
   * @param playerId 玩家id.
   */
  public void removePlayerSeat(long playerId) {
    playerSeats.remove(playerId);
  }



  /**
   * 获取路径 .
   * 
   * @param roadId .
   * @return
   */
  public Road getRoad(int roadId) {

    return roads.get(roadId);
  }



}
