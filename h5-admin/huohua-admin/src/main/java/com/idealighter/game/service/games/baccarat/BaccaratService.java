package com.idealighter.game.service.games.baccarat;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.dao.dic.mapper.BaccaratRoomMapper;
import com.idealighter.game.dao.dic.mapper.BaccaratRoomTypeMapper;
import com.idealighter.game.dao.dic.po.BaccaratRoom;
import com.idealighter.game.dao.dic.po.BaccaratRoomExample;
import com.idealighter.game.dao.dic.po.BaccaratRoomType;
import com.idealighter.game.dao.dic.po.BaccaratRoomTypeExample;
import com.idealighter.game.request.RequestHelper;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.game.convert.GameBoConvert;
import com.idealighter.game.service.games.baccarat.bo.BaccaratRoomBo;
import com.idealighter.game.service.games.baccarat.convert.BaccaratBoConvert;
import com.idealighter.game.service.robotcfg.IRobotCfgService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.utils.time.TimeUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaccaratService implements IBaccaratService {

  @Autowired
  private BaccaratRoomMapper baccaratRoomMapper;

  @Autowired
  private BaccaratRoomTypeMapper baccaratRoomTypeMapper;

  @Autowired
  private GameBoConvert gameBoConvert;

  @Autowired
  private BaccaratBoConvert baccaratBoConvert;

  @Autowired
  private IRobotCfgService robotCfgService;

  @Override
  public ResultPage<RoomTypeBo> findRoomType() {
    ResultPage<RoomTypeBo> resultPage = new ResultPage<>();
    List<RoomTypeBo> bos = null;
    int total = 0;

    List<BaccaratRoomType> baccaratRoomTypes =
        baccaratRoomTypeMapper.selectByExample(new BaccaratRoomTypeExample());
    bos = gameBoConvert.toRoomTypeBaccaratBos(baccaratRoomTypes);
    total = bos.size();

    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public ResultPage<BaccaratRoomBo> findRoomByPage(Integer page, Integer pageSize) {
    BaccaratRoomExample select = new BaccaratRoomExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("type asc , id desc");

    List<BaccaratRoom> baccaratRooms = baccaratRoomMapper.selectByExample(select);

    List<BaccaratRoomBo> bos = baccaratBoConvert.toBaccaratRoomBos(baccaratRooms);

    long total = baccaratRoomMapper.countByExample(select);

    ResultPage<BaccaratRoomBo> resultPage = new ResultPage<>();
    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Transactional
  @Override
  public void saveRoom(BaccaratRoomBo bo) {
    BaccaratRoom baccaratRoom = baccaratBoConvert.toBaccaratRoom(bo);

    int out = 0;
    if (baccaratRoom.getId() == null) {
      out = baccaratRoomMapper.insertSelective(baccaratRoom);
    } else {
      out = baccaratRoomMapper.updateByPrimaryKey(baccaratRoom);
      if (out > 0) {
        RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
        robotCfgListBo.setState(bo.getIsActive());
        robotCfgListBo.setGoldlower(baccaratRoom.getLower());
        robotCfgListBo.setGoldupper(baccaratRoom.getUpper());
        robotCfgListBo.setMaxgold(baccaratRoom.getUpper());

        robotCfgService.modifyByRoomChange(Game.BACCARAT.type, baccaratRoom.getId(),
            robotCfgListBo);
      }

    }

    if (out > 0 && baccaratRoom.getId() != null) {
      if (baccaratRoom.getIsActive() == 0) {
        RequestHelper.noticeShutdownRoom(Game.BACCARAT.type, baccaratRoom.getId());
      } else if (baccaratRoom.getIsActive() == 1) {
        RequestHelper.noticeStartRoom(Game.BACCARAT.type, baccaratRoom.getId());
      }
    }
  }

  @Transactional
  @Override
  public void changeStatus(int roomId, byte isActive) {
    BaccaratRoom baccaratRoom = new BaccaratRoom();
    baccaratRoom.setId(roomId);
    baccaratRoom.setIsActive(isActive);

    if (isActive == 1) {
      baccaratRoom.setTimeOpen(TimeUtil.now());
    }

    int out = baccaratRoomMapper.updateByPrimaryKeySelective(baccaratRoom);

    if (out > 0) {
      RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
      robotCfgListBo.setGoldlower(baccaratRoom.getLower());
      robotCfgListBo.setGoldupper(baccaratRoom.getUpper());
      robotCfgListBo.setMaxgold(baccaratRoom.getUpper());
      robotCfgListBo.setState(isActive);
      robotCfgService.modifyByRoomChange(Game.BACCARAT.type, baccaratRoom.getId(), robotCfgListBo);

      if (isActive == 0) {
        RequestHelper.noticeShutdownRoom(Game.BACCARAT.type, roomId);
      } else if (isActive == 1) {
        RequestHelper.noticeStartRoom(Game.BACCARAT.type, roomId);
      }
    }

  }

  @Transactional
  @Override
  public void deleteForCloseRoom(int id) {
    BaccaratRoom baccaratRoom = baccaratRoomMapper.selectByPrimaryKey(id);
    IdeaAssert.isTrue(baccaratRoom != null && baccaratRoom.getIsActive() == 0);
    int out = baccaratRoomMapper.deleteByPrimaryKey(id);
    if (out > 0) {
      robotCfgService.deleteByRoomClose(Game.BACCARAT.type, id);
      RequestHelper.noticeShutdownRoom(Game.BACCARAT.type, id);
    }

  }

  @Override
  public List<BaccaratRoomBo> findRoom(Byte active) {
    BaccaratRoomExample select = new BaccaratRoomExample();

    select.setOrderByClause("type asc , id desc");

    BaccaratRoomExample.Criteria criteria = select.createCriteria();

    if (active != null) {
      criteria.andIsActiveEqualTo(active);
    }

    List<BaccaratRoom> rooms = baccaratRoomMapper.selectByExample(select);

    return baccaratBoConvert.toBaccaratRoomBos(rooms);
  }

  @Override
  public BaccaratRoomBo findRoomById(Integer roomId) {
    BaccaratRoom room = baccaratRoomMapper.selectByPrimaryKey(roomId);
    return baccaratBoConvert.toBaccaratRoomBo(room);
  }

}
