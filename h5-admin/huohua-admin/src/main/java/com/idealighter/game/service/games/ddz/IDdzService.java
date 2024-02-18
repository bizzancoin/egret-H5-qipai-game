package com.idealighter.game.service.games.ddz;

import java.util.List;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;

public interface IDdzService {
  /**
   * 获取房间类型.
   * 
   * @return 房间类型列表.
   */
  ResultPage<RoomTypeBo> findRoomType();

  /**
   * 分页获取房间列表.
   * 
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 房间列表.
   */
  ResultPage<DdzRoomBo> findRoomByPage(Integer page, Integer pageSize);

  /**
   * 添加房间.
   * 
   * @param bo 房间实体.
   */
  void saveRoom(DdzRoomBo bo);

  /**
   * 修改房间状态.
   * 
   * @param id 房间id.
   * @param isActive 状态.
   */
  void changeStatus(int id, byte isActive);

  /**
   * 删除关闭的房间.
   * 
   * @param id id.
   */
  void deleteForCloseRoom(int id);

  List<DdzRoomBo> findRoom(Byte active);

  DdzRoomBo findRoomById(Integer roomId);
}
