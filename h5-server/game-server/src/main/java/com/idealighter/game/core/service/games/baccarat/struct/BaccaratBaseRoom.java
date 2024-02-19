package com.idealighter.game.core.service.games.baccarat.struct;

import com.idealighter.game.core.service.games.common.AbstractBaseRoom;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaccaratBaseRoom extends AbstractBaseRoom {
  private final Map<Integer, BaccaratRoom> instanceMap;

  protected BaccaratRoom newInstance;

  /**
   * 初始化构造.
   * 
   * @param roomDom .
   */
  public BaccaratBaseRoom(BaccaratRoomDomain roomDom) {
    super(roomDom.getId());

    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    BaccaratRoom room = new BaccaratRoom(instanceId, roomDom);

    newInstance = room;

    instanceMap = new HashMap<>();
    instanceMap.put(instanceId, room);
  }

  /**
   * 更新最新房间实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:00:07
   * @param roomDom 房间.
   * @return 返回需要删除的实例列表.
   */
  public List<BaccaratRoom> renewRoom(BaccaratRoomDomain roomDom) {
    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    BaccaratRoom room = new BaccaratRoom(instanceId, roomDom);

    newInstance = room;

    instanceMap.put(instanceId, room);

    List<BaccaratRoom> delRooms = new ArrayList<>();

    Collection<BaccaratRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (BaccaratRoom baccaratRoom : rooms) {
        if (baccaratRoom.getInstanceId() != newInstance.getInstanceId()) {
          baccaratRoom.setInstanceState(InstanceState.TO_REMOVE);
          delRooms.add(baccaratRoom);
        }
      }
    }
    return delRooms;
  }

  /**
   * 删除房间实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午3:57:16
   * @param instanceId 实例id.
   */
  public void removeInstance(int instanceId) {
    instanceMap.remove(instanceId);
  }

  /**
   * 获取实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午3:59:41
   * @param instanceId 实例id.
   * @return 房间实例.
   */
  public BaccaratRoom getByInstanceId(int instanceId) {
    return instanceMap.get(instanceId);
  }


  /**
   * 所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:20:03
   * @return 实例列表.
   */
  public Collection<BaccaratRoom> allInstance() {
    return instanceMap.values();
  }

  /**
   * 删除所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月28日 下午4:43:38
   * @return 实例列表.
   */
  public Collection<BaccaratRoom> deleteAllInstance() {
    Collection<BaccaratRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (BaccaratRoom baccaratRoom : rooms) {
        baccaratRoom.setInstanceState(InstanceState.TO_REMOVE);
      }
    }
    return rooms;
  }
}
