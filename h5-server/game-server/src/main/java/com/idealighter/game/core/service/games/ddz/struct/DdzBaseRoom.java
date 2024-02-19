package com.idealighter.game.core.service.games.ddz.struct;

import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.core.service.games.common.AbstractBaseRoom;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DdzBaseRoom extends AbstractBaseRoom {
  private final Map<Integer, DdzRoom> instanceMap;
  protected DdzRoom newInstance;

  /**
   * 初始化构造.
   * 
   * @param roomDom .
   */
  public DdzBaseRoom(DdzRoomDomain roomDom) {
    super(roomDom.getId());

    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    DdzRoom room = new DdzRoom(instanceId, roomDom);

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
  public List<DdzRoom> renewRoom(DdzRoomDomain roomDom) {
    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    DdzRoom room = new DdzRoom(instanceId, roomDom);

    newInstance = room;

    instanceMap.put(instanceId, room);

    List<DdzRoom> delRooms = new ArrayList<>();

    Collection<DdzRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (DdzRoom ddzRoom : rooms) {
        if (ddzRoom.getInstanceId() != newInstance.getInstanceId()) {
          ddzRoom.setInstanceState(InstanceState.TO_REMOVE);
          delRooms.add(ddzRoom);
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
  public DdzRoom getByInstanceId(int instanceId) {
    return instanceMap.get(instanceId);
  }


  /**
   * 所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:20:03
   * @return 实例列表.
   */
  public Collection<DdzRoom> allInstance() {
    return instanceMap.values();
  }

  /**
   * 删除所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月28日 下午4:43:38
   * @return 实例列表.
   */
  public Collection<DdzRoom> deleteAllInstance() {
    Collection<DdzRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (DdzRoom ddzRoom : rooms) {
        ddzRoom.setInstanceState(InstanceState.TO_REMOVE);
      }
    }
    return rooms;
  }
}
