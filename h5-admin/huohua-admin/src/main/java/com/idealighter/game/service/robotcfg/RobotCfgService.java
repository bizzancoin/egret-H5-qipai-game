package com.idealighter.game.service.robotcfg;

import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.common.constant.RegisterType;
import com.idealighter.game.dao.data.mapper.PlayerMainMapper;
import com.idealighter.game.dao.data.po.PlayerMainExample;
import com.idealighter.game.dao.dic.mapper.RobotConfigMapper;
import com.idealighter.game.dao.dic.po.RobotConfig;
import com.idealighter.game.dao.dic.po.RobotConfigExample;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.request.RobotServerRequest;
import com.idealighter.game.request.RobotServerUrl;
import com.idealighter.game.service.game.GameService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.game.service.robotcfg.bo.RobotLeftBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;
import com.idealighter.game.service.robotcfg.convert.RobotCfgListBoConvert;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RobotCfgService implements IRobotCfgService {
  @Autowired
  private RobotConfigMapper robotConfigMapper;
  @Autowired
  private RobotCfgListBoConvert robotCfgListBoConvert;
  @Autowired
  private PlayerMainMapper playerMainMapper;

  @Autowired
  private GameService gameService;

  @Override
  public ResultPage<RobotCfgListBo> robotCfgList(Integer gameId, Integer roomId, Integer page,
      Integer pageSize) {
    Game game = Game.getGame(gameId);
    IdeaAssert.isTrue(game != null);
    RobotConfigExample example = new RobotConfigExample();
    RobotConfigExample.Criteria criteria = example.createCriteria();
    if (gameId != null) {
      criteria.andGameEqualTo(gameId);
    }
    if (roomId != null) {
      criteria.andRoomEqualTo(roomId);
    }
    example.setOffset((page - 1) * pageSize);
    example.setLimit(pageSize);
    example.setOrderByClause("id desc");
    Long count = robotConfigMapper.countByExample(example);
    List<RobotCfgListBo> resultList = new ArrayList<RobotCfgListBo>();
    if (count > 0) {
      // 获取所有房间信息
      List<IdName> idNames = gameService.findRoom(gameId, null);
      if (EmptyUtil.listIsNotEmpty(idNames)) {

        List<RobotConfig> list = robotConfigMapper.selectByExample(example);
        if (EmptyUtil.listIsNotEmpty(list)) {
          resultList = robotCfgListBoConvert.po2bo(list);
          for (RobotCfgListBo item : resultList) {
            for (IdName idName : idNames) {
              item.setGameName(game.desc);
              if (idName.getId() == item.getRoom()) {
                item.setRoomName(idName.getName());
                break;
              }
            }
          }
        }
      }
    }

    ResultPage<RobotCfgListBo> result = new ResultPage<>();
    result.setTotal(count);
    result.setList(resultList);
    return result;
  }

  @Override
  public RobotCfgListBo findById(Integer id) {
    RobotConfig config = robotConfigMapper.selectByPrimaryKey(id);
    RobotCfgListBo result = robotCfgListBoConvert.po2bo(config);

    Game game = Game.getGame(config.getGame());
    IdeaAssert.isTrue(game != null);
    if (result != null) {
      result.setGameName(game.desc);
      IdName idName = gameService.findRoomById(config.getGame(), config.getRoom());

      if (idName != null) {
        result.setRoomName(idName.getName());
      }
    }
    return result;
  }

  @Transactional
  @Override
  public Integer modify(RobotCfgListBo cfg) {
    // 第一步：校验游戏编号和房间是否存在
    Integer gameId = cfg.getGame();
    Integer roomId = cfg.getRoom();

    IdName idName = gameService.findRoomById(gameId, roomId);

    Integer sign = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    if (idName == null) {
      sign = ErrorCode.ROOM_NOT_EXIST.getCode();
    } else {
      // // 校验填写的值是否为限定的值
      // RobotLeftBo robotLeft = robotLeft(gameId, roomId);
      RobotConfig record = robotCfgListBoConvert.bo2po(cfg);

      RobotConfigExample example = new RobotConfigExample();
      example.createCriteria().andGameEqualTo(gameId).andRoomEqualTo(roomId);
      example.setLimit(1);
      List<RobotConfig> existCfgs = robotConfigMapper.selectByExample(example);
      // 更新
      if (cfg.getId() != null) {
        if (EmptyUtil.listIsNotEmpty(existCfgs)) {
          RobotConfig existCfg = existCfgs.get(0);
          if (existCfg.getId().equals(record.getId())) {
            int count = robotConfigMapper.updateByPrimaryKeySelective(record);
            if (count > 0) {
              refreshRobotServer(gameId, roomId);
              sign = ErrorCode.OK.getCode();
              // if (sign != ErrorCode.OK.getCode()) {
              // throw new RuntimeException("执行失败");
              // }
            }
          } else {
            sign = ErrorCode.ROBOT_CFG_CAN_NOT_UPDATE_GAMEID_ROOMID.getCode();
          }
        }
      } else { // 新增加
        if (EmptyUtil.listIsEmpty(existCfgs)) {
          record.setId(gameId * 1000 + roomId);
          int count = robotConfigMapper.insertSelective(record);
          if (count > 0) {
            refreshRobotServer(gameId, roomId);
            sign = ErrorCode.OK.getCode();
            // if (sign != ErrorCode.OK.getCode()) {
            // throw new RuntimeException("执行失败");
            // }
          }
        } else {
          sign = ErrorCode.ROBOT_CFG_EXIST_CFG.getCode();
        }
      }
    }
    return sign;
  }

  @Override
  @Transactional()
  public Integer delete(Integer id) {
    RobotConfig robotConfig = robotConfigMapper.selectByPrimaryKey(id);
    if (robotConfig != null) {
      robotConfigMapper.deleteByPrimaryKey(id);
      refreshRobotServer(robotConfig.getGame(), robotConfig.getRoom());
    }
    
    // if (sign != ErrorCode.OK.getCode()) {
    // throw new RuntimeException("执行失败");
    // }
    return ErrorCode.OK.getCode();
  }

  private void refreshRobotServer(int game, int room) {
    // return ErrorCode.OK.getCode();
    try {

      RequestParamBuild paramBuild = new RequestParamBuild();
      paramBuild.add("gameId", game);
      paramBuild.add("roomId", room);
      
      String serviceResponse =
          RobotServerRequest.post(RobotServerUrl.ROBOT_RELOAD_ROBOT_CFG, paramBuild);
      JsonUtil.fromJson(serviceResponse, Result.class);

    } catch (Exception exception) {
      if (exception instanceof java.net.ConnectException) {
        // 连接错误，不处理
      }
    }
  }

  @Override
  public List<IdName> noCfgRobotRoomList(Integer gameId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);

    List<IdName> idNames = gameService.findRoom(gameId, null);

    if (EmptyUtil.listIsNotEmpty(idNames)) {
      // 剔除已经配置机器人的房间
      RobotConfigExample example = new RobotConfigExample();
      example.createCriteria().andGameEqualTo(gameId);
      List<RobotConfig> robotCfgs = robotConfigMapper.selectByExample(example);
      if (EmptyUtil.listIsNotEmpty(robotCfgs)) {
        for (RobotConfig cfg : robotCfgs) {
          for (int i = 0; i < idNames.size();) {
            IdName room = idNames.get(i);
            if (room.getId() == cfg.getRoom()) {
              idNames.remove(room);
            } else {
              i++;
            }
          }
        }
      }
    }
    return idNames;
  }
  
  @Override
  public List<RoomConfigBo> cfgRobotRoomList(Integer gameId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);

    List<RoomConfigBo> roomConfigBos = gameService.findRoomConfig(gameId, null);

    if (EmptyUtil.listIsNotEmpty(roomConfigBos)) {
      // 已经配置机器人的房间
      RobotConfigExample example = new RobotConfigExample();
      example.createCriteria().andGameEqualTo(gameId);
      List<RobotConfig> robotCfgs = robotConfigMapper.selectByExample(example);
      if (EmptyUtil.listIsNotEmpty(robotCfgs)) {
        for (RobotConfig cfg : robotCfgs) {
          for (int i = 0; i < roomConfigBos.size(); i++) {
            RoomConfigBo room = roomConfigBos.get(i);
            if (room.getId() == cfg.getRoom()) {
              room.setHasRobot(true);
            }
            if (room.getUpper() == 0) {
              room.setUpper(room.getLower() * 50);
            }
          }
        }
      }
    }
    return roomConfigBos;
  }

  @Override
  public RobotLeftBo robotLeft(Integer gameId, Integer roomId) {
    // 首先查找所有机器人的数量
    PlayerMainExample example = new PlayerMainExample();
    example.createCriteria().andRegisterTypeEqualTo(RegisterType.ROBOT).andLockedEqualTo(false);
    Long total = playerMainMapper.countByExample(example);
    // 再查找剩余机器人数量
    RobotLeftBo left = robotConfigMapper.selectLeftRobot(total.intValue());

    RobotConfigExample configExample = new RobotConfigExample();
    configExample.createCriteria().andGameEqualTo(gameId).andRoomEqualTo(roomId);
    configExample.setLimit(1);
    List<RobotConfig> robotCfg = robotConfigMapper.selectByExample(configExample);
    RobotConfig item = null;
    if (EmptyUtil.listIsNotEmpty(robotCfg)) {
      item = robotCfg.get(0);
    }
    if (item != null) {
      left.setTime1(left.getTime1() + item.getTime1players());
      left.setTime2(left.getTime2() + item.getTime2players());
      left.setTime3(left.getTime3() + item.getTime3players());
      left.setTime4(left.getTime4() + item.getTime4players());
      left.setTime5(left.getTime5() + item.getTime5players());
      left.setTime6(left.getTime6() + item.getTime6players());
      left.setTime7(left.getTime7() + item.getTime7players());
      left.setTime8(left.getTime8() + item.getTime8players());
      left.setTime9(left.getTime9() + item.getTime9players());
      left.setTime10(left.getTime10() + item.getTime10players());
      left.setTime11(left.getTime11() + item.getTime11players());
      left.setTime12(left.getTime12() + item.getTime12players());
    }
    return left;
  }

  @Override
  public int modifyByRoomChange(int gameId, int roomId, RobotCfgListBo bo) {
    RobotConfigExample configExample = new RobotConfigExample();
    configExample.createCriteria().andGameEqualTo(gameId).andRoomEqualTo(roomId);
    RobotConfig record = robotCfgListBoConvert.bo2po(bo);
    int out = robotConfigMapper.updateByExampleSelective(record, configExample);
    if (out > 0) {
      refreshRobotServer(gameId, roomId);
    }

    return out;
  }

  @Override
  public int deleteByRoomClose(int gameId, int roomId) {
    RobotConfigExample configExample = new RobotConfigExample();
    configExample.createCriteria().andGameEqualTo(gameId).andRoomEqualTo(roomId);
    int out = robotConfigMapper.deleteByExample(configExample);

    if (out > 0) {
      refreshRobotServer(gameId, roomId);
    }

    return out;
  }

  @Override
  public int changeState(Integer id, Byte state) {

    RobotConfig robotConfig = new RobotConfig();
    robotConfig.setId(id);
    robotConfig.setState(state);
    RobotConfig config = robotConfigMapper.selectByPrimaryKey(id);
    int out = 0;
    if (config != null) {
      out = robotConfigMapper.updateByPrimaryKeySelective(robotConfig);
      if (out > 0) {
        refreshRobotServer(config.getGame(), config.getRoom());
      }
    }

    return out;
  }

}
