package com.idealighter.game.core.service.games.ddz.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.ddz.struct.DdzAiSeat;
import com.idealighter.game.core.service.games.ddz.struct.DdzBaseRoom;
import com.idealighter.game.core.service.games.ddz.struct.DdzRoom;
import com.idealighter.game.core.service.games.ddz.struct.DdzTable;
import com.idealighter.game.dictionary.dic.DdzRoomDic;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.utils.check.EmptyUtil;

/**
 * 斗地主数据管理 .
 * 
 * @date 2015年8月6日 下午9:39:01
 *
 */
@Singleton
public class DdzDataMgr {

  private static final Logger LOG = LoggerFactory.getLogger(DdzDataMgr.class);

  private final DdzRoomDic roomDic;
  private final GamesDic gamesDic;

  public static volatile boolean roomEmpty = false;
  
  // 斗地主游戏房间
  private final Map<Integer, DdzBaseRoom> roomMap = new HashMap<>();
  // 玩家座位
  private final Map<Long, DdzAiSeat> playerSeats = new HashMap<>();

  /**
   *  .
   * @param roomDic .
   * @param gamesDic .
   */
  @Inject
  public DdzDataMgr(DdzRoomDic roomDic, GamesDic gamesDic) {
    this.roomDic = roomDic;
    this.gamesDic = gamesDic;
    // 创建房间
    createRooms();
  }

  private void createRoom(DdzRoomDomain roomDomain) {
    if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
      GamesDomain gamesDom = gamesDic.get(Game.DDZ.getType());
      if (gamesDom != null && gamesDom.getActive()) {
        this.roomMap.put(roomDomain.getId(), new DdzBaseRoom(roomDomain));
        LOG.info("[炸金花]创建房间[{}][{}]", roomDomain.getId(), roomDomain.getName());
      }
      roomEmpty = roomMap.values() == null || roomMap.values().isEmpty();
    }
  }

  /**
   * 创建房间 .
   * 
   * @return .
   */
  private void createRooms() {
    List<DdzRoomDomain> roomDomains = roomDic.list();
    if (EmptyUtil.listIsNotEmpty(roomDomains)) {
      for (DdzRoomDomain roomDomain : roomDomains) {
        createRoom(roomDomain);
      }
    }
  }

  /**
   * 重新加载房间，并返回最新房间id列表.
   *
   * @return 房间id列表.
   */
  public List<Integer> reloadRoom() {
    roomDic.load();
    List<Integer> ids = new ArrayList<>();
    GamesDomain gamesDom = gamesDic.get(Game.DDZ.getType());
    if (gamesDom != null && gamesDom.getActive()) {
      List<DdzRoomDomain> rooms = roomDic.list();
      if (EmptyUtil.listIsNotEmpty(rooms)) {
        for (DdzRoomDomain roomDomain : rooms) {
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
   * @param roomId 房间id.
   * @return return old room(delete) list.
   */
  public List<DdzRoom> startRoom(int roomId, boolean reload) {
    if (reload) {
      roomDic.load();
    }
    List<DdzRoom> deleteRooms = new ArrayList<>();

    GamesDomain gamesDom = gamesDic.get(Game.DDZ.getType());
    if (gamesDom != null && gamesDom.getActive()) {

      DdzRoomDomain roomDomain = roomDic.get(roomId);
      if (roomDomain != null && roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
        DdzBaseRoom baseRoom = roomMap.get(roomId);
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
  public DdzRoom getNewestRoom(int roomId) {
    DdzRoom room = null;
    DdzBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      room = baseRoom.getNewInstance();
    }
    return room;
  }

  /**
   * 获取所有实例 .
   * 
   * @param roomId 房间id.
   * @return 获取所有实例.
   */
  public List<DdzRoom> deleteRoom(int roomId) {
    roomDic.load();
    List<DdzRoom> rooms = null;
    DdzBaseRoom baseRoom = roomMap.get(roomId);
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
   * @return 房间.
   */
  public DdzRoom getRoomByIntanceId(int instanceId) {
    DdzRoom room = null;
    int roomId = getRoomIdByIntanceId(instanceId);
    DdzBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      room = baseRoom.getByInstanceId(instanceId);
    }
    return room;
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
    return instanceId / DdzRoom.MAX_INSTANCE;
  }

  /**
   * 校验是否remove room.
   *
   * @author abin
   * @date 2018年4月28日 下午7:39:33
   * @param baseRoom 基本房间.
   */
  public void checkRemoveRoom(DdzBaseRoom baseRoom) {
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
    DdzBaseRoom baseRoom = roomMap.get(roomId);
    if (baseRoom != null) {
      DdzRoom room = baseRoom.getByInstanceId(instanceId);

      Collection<DdzTable> tables = room.tables();

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
   */
  public Collection<DdzRoom> newestRooms() {
    List<DdzRoom> tempRooms = new ArrayList<>();
    for (DdzBaseRoom baseRoom : roomMap.values()) {
      DdzRoom DdzRoom = baseRoom.getNewInstance();
      if (DdzRoom != null && DdzRoom.getInstanceState().equals(InstanceState.NORMAL)) {
        tempRooms.add(DdzRoom);
      }
    }
    return tempRooms;
  }

  /**
   * 获取所有房间集合 .
   * 
   * @return .
   */
  public Collection<DdzRoom> allRooms() {
    List<DdzRoom> tempRooms = new ArrayList<>();
    for (DdzBaseRoom baseRoom : roomMap.values()) {
      Collection<DdzRoom> allInstances = baseRoom.allInstance();
      if (allInstances != null && !allInstances.isEmpty()) {
        tempRooms.addAll(allInstances);
      }
    }
    return tempRooms;
  }

  public Collection<DdzBaseRoom> allBaseRoom() {
    return roomMap.values();
  }

  /**
   * 获取玩家的座位 .
   * 
   * @param playerId 玩家id.
   * @return .
   */
  public DdzAiSeat getPlayerSeat(long playerId) {
    return playerSeats.get(playerId);
  }

  /**
   * 更新玩家座位 .
   * 
   * @param playerId 玩家id.
   * @param seat 座位信息.
   */
  public void updatePlayerSeats(long playerId, DdzAiSeat seat) {
    playerSeats.put(playerId, seat);
  }

  /**
   * 移除玩家座位 .
   * 
   * @param playerId 玩家id.
   */
  public void removePlayerSeat(long playerId) {
    playerSeats.remove(playerId);
  }

}
