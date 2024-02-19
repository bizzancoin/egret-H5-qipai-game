package com.idealighter.game.core.service.games.baccarat.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratBaseRoom;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratRoom;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratSeat;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.dic.BaccaratRoomDic;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 百家乐数据管理 .
 */
@Singleton
public class BaccaratDataMgr {

  private static final Logger LOG = LoggerFactory.getLogger(BaccaratDataMgr.class);

  private final BaccaratRoomDic roomDic;

  private final GamesDic gamesDic;

  // 21点游戏房间
  private final Map<Integer, BaccaratBaseRoom> roomMap = new HashMap<>();

  public static volatile boolean roomEmpty = false;

  // 玩家的座位
  private final Map<Long, BaccaratSeat> playerSeats = new HashMap<>();

  /**
   * 百家乐.
   * 
   * @param roomDic 房间dic.
   * @param gamesDic 游戏dic.
   */
  @Inject
  public BaccaratDataMgr(BaccaratRoomDic roomDic, GamesDic gamesDic) {
    this.roomDic = roomDic;
    this.gamesDic = gamesDic;

    createRooms();

  }

  private void createRoom(BaccaratRoomDomain roomDomain) {
    if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
      GamesDomain gamesDom = gamesDic.get(Game.BACCARAT.getType());
      if (gamesDom != null && gamesDom.getActive()) {
        this.roomMap.put(roomDomain.getId(), new BaccaratBaseRoom(roomDomain));
        LOG.info("[百家乐]创建房间[{}][{}]", roomDomain.getId(), roomDomain.getName());
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
    List<BaccaratRoomDomain> rooms = roomDic.list();
    if (EmptyUtil.listIsNotEmpty(rooms)) {
      for (BaccaratRoomDomain roomDomain : rooms) {
        createRoom(roomDomain);
      }
    }
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
    GamesDomain gamesDom = gamesDic.get(Game.BACCARAT.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      List<BaccaratRoomDomain> rooms = roomDic.list();
      if (EmptyUtil.listIsNotEmpty(rooms)) {
        for (BaccaratRoomDomain roomDomain : rooms) {
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
  public List<BaccaratRoom> startRoom(int roomId, boolean reload) {
    if (reload) {
      roomDic.load();
    }

    List<BaccaratRoom> deleteRooms = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.BACCARAT.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      BaccaratRoomDomain roomDomain = roomDic.get(roomId);
      if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
        BaccaratBaseRoom baseRoom = roomMap.get(roomId);
        if (baseRoom == null) {
          createRoom(roomDomain);
        } else {
          deleteRooms = baseRoom.renewRoom(roomDomain);
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
  public BaccaratRoom getNewestRoom(int roomId) {
    BaccaratRoom room = null;
    BaccaratBaseRoom baseRoom = roomMap.get(roomId);
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
  public List<BaccaratRoom> deleteRoom(int roomId) {
    List<BaccaratRoom> rooms = null;
    BaccaratBaseRoom baseRoom = roomMap.get(roomId);
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
  public BaccaratRoom getRoomByIntanceId(int instanceId) {
    BaccaratRoom room = null;
    int roomId = getRoomIdByIntanceId(instanceId);
    BaccaratBaseRoom baseRoom = roomMap.get(roomId);
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
  public void checkRemoveRoom(BaccaratBaseRoom baseRoom) {
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
    BaccaratBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      BaccaratRoom room = baseRoom.getByInstanceId(instanceId);

      Collection<BaccaratTable> tables = room.tables();

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
  public Collection<BaccaratRoom> newestRooms() {
    List<BaccaratRoom> tempRooms = new ArrayList<>();
    for (BaccaratBaseRoom baseRoom : roomMap.values()) {
      BaccaratRoom baccaratRoom = baseRoom.getNewInstance();
      if (baccaratRoom != null && baccaratRoom.getInstanceState().equals(InstanceState.NORMAL)) {
        tempRooms.add(baccaratRoom);
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
  public Collection<BaccaratRoom> allRooms() {
    List<BaccaratRoom> tempRooms = new ArrayList<>();
    for (BaccaratBaseRoom baseRoom : roomMap.values()) {
      Collection<BaccaratRoom> allInstances = baseRoom.allInstance();
      if (allInstances != null && !allInstances.isEmpty()) {
        tempRooms.addAll(allInstances);
      }
    }
    return tempRooms;
  }

  public Collection<BaccaratBaseRoom> allBaseRoom() {
    return roomMap.values();
  }


  /**
   * 获取座位 .
   * 
   * @param playerId 玩家id.
   * @return
   */
  public BaccaratSeat getPlayerSeat(long playerId) {

    return playerSeats.get(playerId);
  }

  /**
   * 更新玩家座位 .
   * 
   * @param playerId 玩家id.
   * @param seat 座位信息.
   */
  public void updatePlayerSeats(long playerId, BaccaratSeat seat) {
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
   * 获取牌桌 .
   * 
   * @param tableId .
   * @return
   */
  public BaccaratTable getTable(int tableId) {
    BaccaratTable table = null;
    int roomId = getRoomIdByTableId(tableId);
    BaccaratBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      int instanceId = getInstanceIdByTableId(tableId);
      BaccaratRoom room = baseRoom.getByInstanceId(instanceId);
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
    return tableId / BaccaratTable.MAX_TABLE;
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
    return instanceId / BaccaratRoom.MAX_INSTANCE;
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
}
