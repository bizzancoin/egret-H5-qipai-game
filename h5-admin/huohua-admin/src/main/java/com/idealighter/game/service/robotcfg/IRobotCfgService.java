package com.idealighter.game.service.robotcfg;

import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.game.service.robotcfg.bo.RobotLeftBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

import java.util.List;

public interface IRobotCfgService {
  public ResultPage<RobotCfgListBo> robotCfgList(Integer gameId, Integer roomId, Integer page,
      Integer pageSize);

  public List<IdName> noCfgRobotRoomList(Integer gameId);
  
  public List<RoomConfigBo> cfgRobotRoomList(Integer gameId);

  public RobotLeftBo robotLeft(Integer gameId, Integer roomId);

  public RobotCfgListBo findById(Integer id);

  public Integer modify(RobotCfgListBo cfg);

  public Integer delete(Integer id);

  public int modifyByRoomChange(int gameId, int roomId, RobotCfgListBo bo);

  public int deleteByRoomClose(int gameId, int roomId);

  public int changeState(Integer id, Byte state);
}
