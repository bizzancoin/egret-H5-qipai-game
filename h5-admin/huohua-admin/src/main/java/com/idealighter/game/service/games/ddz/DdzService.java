package com.idealighter.game.service.games.ddz;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.dao.dic.mapper.DdzRoomMapper;
import com.idealighter.game.dao.dic.mapper.DdzRoomTypeMapper;
import com.idealighter.game.dao.dic.po.DdzRoomExample;
import com.idealighter.game.dao.dic.po.DdzRoom;
import com.idealighter.game.dao.dic.po.DdzRoomType;
import com.idealighter.game.dao.dic.po.DdzRoomTypeExample;
import com.idealighter.game.request.RequestHelper;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.game.convert.GameBoConvert;
import com.idealighter.game.service.games.ddz.bo.DdzRoomBo;
import com.idealighter.game.service.games.ddz.convert.DdzBoConvert;
import com.idealighter.game.service.robotcfg.IRobotCfgService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.utils.time.TimeUtil;

@Service
public class DdzService implements IDdzService {
  @Autowired
  private DdzRoomTypeMapper ddzRoomTypeMapper;

  @Autowired
  private DdzRoomMapper ddzRoomMapper;

  @Autowired
  private GameBoConvert gameBoConvert;

  @Autowired
  private DdzBoConvert ddzRoomConvert;
  

  @Autowired
  private IRobotCfgService robotCfgService;

  @Override
  public ResultPage<RoomTypeBo> findRoomType() {
    ResultPage<RoomTypeBo> resultPage = new ResultPage<>();

    List<RoomTypeBo> bos = null;
    int total = 0;

    List<DdzRoomType> roomTypes =
        ddzRoomTypeMapper.selectByExample(new DdzRoomTypeExample());
    bos = gameBoConvert.toRoomTypeDdzBos(roomTypes);
    total = bos.size();

    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public ResultPage<DdzRoomBo> findRoomByPage(Integer page, Integer pageSize) {
    DdzRoomExample select = new DdzRoomExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("type asc , id desc");

    List<DdzRoom> rooms = ddzRoomMapper.selectByExample(select);

    List<DdzRoomBo> bos = ddzRoomConvert.toRoomBos(rooms);

    long total = ddzRoomMapper.countByExample(select);

    ResultPage<DdzRoomBo> resultPage = new ResultPage<>();
    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Transactional
  @Override
  public void saveRoom(DdzRoomBo bo) {
    DdzRoom room = ddzRoomConvert.toRoom(bo);

    int out = 0;
    if (room.getId() == null) {
      out = ddzRoomMapper.insertSelective(room);
    } else {
      out = ddzRoomMapper.updateByPrimaryKey(room);
      if (out > 0) {
        RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
        robotCfgListBo.setState(bo.getIsActive());
        robotCfgListBo.setGoldlower(room.getLower());
        robotCfgListBo.setGoldupper(room.getUpper());
        robotCfgListBo.setMaxgold(room.getUpper());

        robotCfgService.modifyByRoomChange(Game.DDZ.type, room.getId(),
            robotCfgListBo);
      }

    }

    if (out > 0 && room.getId() != null) {
      if (room.getIsActive() == 0) {
        RequestHelper.noticeShutdownRoom(Game.DDZ.type, room.getId());
      } else if (room.getIsActive() == 1) {
        RequestHelper.noticeStartRoom(Game.DDZ.type, room.getId());
      }
    }
  }

  @Transactional
  @Override
  public void changeStatus(int roomId, byte isActive) {
    DdzRoom room = new DdzRoom();
    room.setId(roomId);
    room.setIsActive(isActive);

    if (isActive == 1) {
      room.setTimeOpen(TimeUtil.now());
    }

    int out = ddzRoomMapper.updateByPrimaryKeySelective(room);
    if (out > 0) {
      RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
      robotCfgListBo.setState(isActive);
      robotCfgService.modifyByRoomChange(Game.DDZ.type, room.getId(),
          robotCfgListBo);

      if (isActive == 0) {
        RequestHelper.noticeShutdownRoom(Game.DDZ.type, roomId);
      } else if (isActive == 1) {
        RequestHelper.noticeStartRoom(Game.DDZ.type, roomId);
      }
    }

  }

  @Transactional
  @Override
  public void deleteForCloseRoom(int id) {
    DdzRoom room = ddzRoomMapper.selectByPrimaryKey(id);
    IdeaAssert.isTrue(room != null && room.getIsActive() == 0);
    int out = ddzRoomMapper.deleteByPrimaryKey(id);

    if (out > 0) {
      robotCfgService.deleteByRoomClose(Game.DDZ.type, id);
      RequestHelper.noticeShutdownRoom(Game.DDZ.type, id);
    }
  }

  @Override
  public List<DdzRoomBo> findRoom(Byte active) {
    DdzRoomExample select = new DdzRoomExample();

    select.setOrderByClause("type asc , id desc");

    DdzRoomExample.Criteria criteria = select.createCriteria();

    if (active != null) {
      criteria.andIsActiveEqualTo(active);
    }

    List<DdzRoom> rooms = ddzRoomMapper.selectByExample(select);

    return ddzRoomConvert.toRoomBos(rooms);
  }

  @Override
  public DdzRoomBo findRoomById(Integer roomId) {
    DdzRoom room = ddzRoomMapper.selectByPrimaryKey(roomId);
    return ddzRoomConvert.toRoomBo(room);
  }
}
