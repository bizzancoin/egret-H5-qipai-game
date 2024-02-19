package com.idealighter.game.core.service.games.jcby.manager;

import com.idealighter.game.core.service.games.common.AbstractBaseRoom;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.jcby.struct.JcbyRoom;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class JcbyBaseRoom extends AbstractBaseRoom {
  private final Map<Integer, JcbyRoom> instanceMap;

  protected JcbyRoom newInstance;


  /**
   * 构造函数.
   * 
   * @param roomDom .
   * @param likui .
   * @param likuiMinRate .
   * @param likuiMaxRate .
   * @param likuiUpgradeScore .
   */
  public JcbyBaseRoom(JcbyRoomDomain roomDom, int likui, int likuiMinRate, int likuiMaxRate,
      int likuiUpgradeScore) {
    super(roomDom.getId());

    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    JcbyRoom room =
        new JcbyRoom(instanceId, roomDom, likui, likuiMinRate, likuiMaxRate, likuiUpgradeScore);

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
  public List<JcbyRoom> renewRoom(JcbyRoomDomain roomDom, int likui, int likuiMinRate,
      int likuiMaxRate, int likuiUpgradeScore) {
    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    JcbyRoom room =
        new JcbyRoom(instanceId, roomDom, likui, likuiMinRate, likuiMaxRate, likuiUpgradeScore);

    newInstance = room;

    instanceMap.put(instanceId, room);

    List<JcbyRoom> delRooms = new ArrayList<>();

    Collection<JcbyRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (JcbyRoom jcbyRoom : rooms) {
        if (jcbyRoom.getInstanceId() != newInstance.getInstanceId()) {
          jcbyRoom.setInstanceState(InstanceState.TO_REMOVE);
          delRooms.add(jcbyRoom);
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
  public JcbyRoom getByInstanceId(int instanceId) {
    return instanceMap.get(instanceId);
  }


  /**
   * 所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:20:03
   * @return 实例列表.
   */
  public Collection<JcbyRoom> allInstance() {
    return instanceMap.values();
  }

  /**
   * 删除所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月28日 下午4:43:38
   * @return 实例列表.
   */
  public Collection<JcbyRoom> deleteAllInstance() {
    Collection<JcbyRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (JcbyRoom room : rooms) {
        room.setInstanceState(InstanceState.TO_REMOVE);
      }
    }
    return rooms;
  }
}
