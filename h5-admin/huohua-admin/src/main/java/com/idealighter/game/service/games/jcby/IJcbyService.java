package com.idealighter.game.service.games.jcby;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.games.jcby.bo.JcbyRoomBo;
import com.idealighter.game.service.games.jcby.bo.JcbyScenceBo;

import java.util.List;

public interface IJcbyService {

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
  ResultPage<JcbyRoomBo> findRoomByPage(Integer page, Integer pageSize);

  /**
   * 添加房间.
   * 
   * @param bo 房间实体.
   */
  void saveRoom(JcbyRoomBo bo);

  /**
   * 修改房间状态.
   * 
   * @param id 房间id.
   * @param isActive 状态.
   */
  void changeStatus(int id, byte isActive);

  /**
   * 删除关闭的房间.
   * @param id id.
   */
  void deleteForCloseRoom(int id);
  

  List<JcbyScenceBo> findScenceList();

  List<JcbyRoomBo> findRoom(Byte active);

  JcbyRoomBo findRoomById(Integer roomId);
}
