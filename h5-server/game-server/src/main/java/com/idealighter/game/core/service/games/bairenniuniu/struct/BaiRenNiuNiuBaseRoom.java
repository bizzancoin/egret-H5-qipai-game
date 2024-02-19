package com.idealighter.game.core.service.games.bairenniuniu.struct;

import com.idealighter.game.core.service.games.common.AbstractBaseRoom;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiRenNiuNiuBaseRoom extends AbstractBaseRoom {

  private final Map<Integer, BaiRenNiuNiuRoom> instanceMap;

  protected BaiRenNiuNiuRoom newInstance;

  /**
   * 初始化构造.
   * 
   * @param roomDom .
   */
  public BaiRenNiuNiuBaseRoom(BairenniuniuRoomDomain roomDom) {
    super(roomDom.getId());

    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    BaiRenNiuNiuRoom room = new BaiRenNiuNiuRoom(instanceId, roomDom);

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
  public List<BaiRenNiuNiuRoom> renewRoom(BairenniuniuRoomDomain roomDom) {
    serial++;

    int instanceId = roomId * AbstractRoom.MAX_INSTANCE + serial;

    BaiRenNiuNiuRoom room = new BaiRenNiuNiuRoom(instanceId, roomDom);

    newInstance = room;

    instanceMap.put(instanceId, room);

    List<BaiRenNiuNiuRoom> delRooms = new ArrayList<>();

    Collection<BaiRenNiuNiuRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (BaiRenNiuNiuRoom baiRenNiuNiuRoom : rooms) {
        if (baiRenNiuNiuRoom.getInstanceId() != newInstance.getInstanceId()) {
          baiRenNiuNiuRoom.setInstanceState(InstanceState.TO_REMOVE);
          delRooms.add(baiRenNiuNiuRoom);
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
  public BaiRenNiuNiuRoom getByInstanceId(int instanceId) {
    return instanceMap.get(instanceId);
  }


  /**
   * 所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月27日 下午4:20:03
   * @return 实例列表.
   */
  public Collection<BaiRenNiuNiuRoom> allInstance() {
    return instanceMap.values();
  }

  /**
   * 删除所有的游戏实例 .
   *
   * @author abin
   * @date 2018年4月28日 下午4:43:38
   * @return 实例列表.
   */
  public Collection<BaiRenNiuNiuRoom> deleteAllInstance() {
    Collection<BaiRenNiuNiuRoom> rooms = allInstance();
    if (rooms != null && !rooms.isEmpty()) {
      for (BaiRenNiuNiuRoom baiRenNiuNiuRoom : rooms) {
        baiRenNiuNiuRoom.setInstanceState(InstanceState.TO_REMOVE);
      }
    }
    return rooms;
  }
}
