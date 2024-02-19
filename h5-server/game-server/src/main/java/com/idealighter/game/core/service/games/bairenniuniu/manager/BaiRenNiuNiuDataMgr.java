package com.idealighter.game.core.service.games.bairenniuniu.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuBaseRoom;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuRoom;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuSeat;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.dic.BairenniuniuRoomDic;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
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
 * 百人牛牛数据管理.
 */
@Singleton
public class BaiRenNiuNiuDataMgr {

  private static final Logger LOG = LoggerFactory.getLogger(BaiRenNiuNiuDataMgr.class);

  private final BairenniuniuRoomDic roomDic;

  private final Map<Integer, BaiRenNiuNiuBaseRoom> roomMap = new HashMap<>();
  
  public static volatile boolean roomEmpty = false;

  // 玩家的座位
  private final Map<Long, BaiRenNiuNiuSeat> playerSeats = new HashMap<>();

  private final GamesDic gamesDic;

  /**
   * 百人牛牛构造函数.
   * 
   * @param roomDic 房间dic.
   * @param gamesDic 游戏dic.
   */
  @Inject
  public BaiRenNiuNiuDataMgr(BairenniuniuRoomDic roomDic, GamesDic gamesDic) {
    this.roomDic = roomDic;
    this.gamesDic = gamesDic;
    createRooms();
  }

  private void createRoom(BairenniuniuRoomDomain roomDomain) {
    if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
      GamesDomain gamesDom = gamesDic.get(Game.BAIREN_NIUNIU.getType());
      if (gamesDom != null && gamesDom.getActive()) {
        this.roomMap.put(roomDomain.getId(), new BaiRenNiuNiuBaseRoom(roomDomain));
        LOG.info("[百人牛牛]创建房间[{}][{}]", roomDomain.getId(), roomDomain.getName());
      }
      roomEmpty = roomMap.values() == null || roomMap.values().isEmpty();
    }
  }

  /**
   * 创建房间.
   * 
   * @return 房间map.
   */
  private void createRooms() {
    List<BairenniuniuRoomDomain> roomDomains = roomDic.list();
    if (EmptyUtil.listIsNotEmpty(roomDomains)) {
      for (BairenniuniuRoomDomain roomDomain : roomDomains) {
        createRoom(roomDomain);
      }
    }

  }

  /**
   * 重新加载房间.
   *
   * @author abin
   * @date 2018年5月18日 上午11:28:38
   * @return 需要开启的房间.
   */
  public List<Integer> reloadRoom() {
    roomDic.load();
    List<Integer> ids = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.BAIREN_NIUNIU.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      List<BairenniuniuRoomDomain> rooms = roomDic.list();
      if (EmptyUtil.listIsNotEmpty(rooms)) {
        for (BairenniuniuRoomDomain roomDomain : rooms) {
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
  public List<BaiRenNiuNiuRoom> startRoom(int roomId, boolean reload) {
    if (reload) {
      roomDic.load();
    }
    List<BaiRenNiuNiuRoom> deleteRooms = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.BAIREN_NIUNIU.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      BairenniuniuRoomDomain roomDomain = roomDic.get(roomId);
      if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
        BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
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
  public BaiRenNiuNiuRoom getNewestRoom(int roomId) {
    BaiRenNiuNiuRoom room = null;
    BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
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
  public List<BaiRenNiuNiuRoom> deleteRoom(int roomId) {
    roomDic.load();
    List<BaiRenNiuNiuRoom> rooms = null;
    BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
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
  public BaiRenNiuNiuRoom getRoomByIntanceId(int instanceId) {
    BaiRenNiuNiuRoom room = null;
    int roomId = getRoomIdByIntanceId(instanceId);
    BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
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
  public void checkRemoveRoom(BaiRenNiuNiuBaseRoom baseRoom) {
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
    BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      BaiRenNiuNiuRoom room = baseRoom.getByInstanceId(instanceId);

      Collection<BaiRenNiuNiuTable> tables = room.tables();

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
  public Collection<BaiRenNiuNiuRoom> newestRooms() {
    List<BaiRenNiuNiuRoom> tempRooms = new ArrayList<>();
    for (BaiRenNiuNiuBaseRoom baseRoom : roomMap.values()) {
      BaiRenNiuNiuRoom baiRenNiuNiuRoom = baseRoom.getNewInstance();
      if (baiRenNiuNiuRoom != null
          && baiRenNiuNiuRoom.getInstanceState().equals(InstanceState.NORMAL)) {
        tempRooms.add(baiRenNiuNiuRoom);
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
  public Collection<BaiRenNiuNiuRoom> allRooms() {
    List<BaiRenNiuNiuRoom> tempRooms = new ArrayList<>();
    for (BaiRenNiuNiuBaseRoom baseRoom : roomMap.values()) {
      Collection<BaiRenNiuNiuRoom> allInstances = baseRoom.allInstance();
      if (allInstances != null && !allInstances.isEmpty()) {
        tempRooms.addAll(allInstances);
      }
    }
    return tempRooms;
  }

  public Collection<BaiRenNiuNiuBaseRoom> allBaseRoom() {
    return roomMap.values();
  }



  /**
   * 获取牌桌.
   * 
   * @param tableId 桌子id.
   * @return 21点桌子.
   */
  public BaiRenNiuNiuTable getTable(int tableId) {
    BaiRenNiuNiuTable table = null;
    int roomId = getRoomIdByTableId(tableId);
    BaiRenNiuNiuBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      int instanceId = getInstanceIdByTableId(tableId);
      BaiRenNiuNiuRoom room = baseRoom.getByInstanceId(instanceId);
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
    return tableId / BaiRenNiuNiuTable.MAX_TABLE;
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
    return instanceId / BaiRenNiuNiuRoom.MAX_INSTANCE;
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
   * 获取座位.
   * 
   * @param playerId 玩家id. 玩家Id.
   * @return 位置.
   */
  public BaiRenNiuNiuSeat getPlayerSeat(long playerId) {

    return playerSeats.get(playerId);
  }

  /**
   * 更新玩家座位 .
   * 
   * @param playerId 玩家id.
   * @param seat 座位信息.
   */
  public void updatePlayerSeats(long playerId, BaiRenNiuNiuSeat seat) {
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



}
