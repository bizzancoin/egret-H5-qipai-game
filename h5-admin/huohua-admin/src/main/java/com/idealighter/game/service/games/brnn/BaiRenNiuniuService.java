package com.idealighter.game.service.games.brnn;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.dao.dic.mapper.BairenniuniuRoomMapper;
import com.idealighter.game.dao.dic.mapper.BairenniuniuRoomTypeMapper;
import com.idealighter.game.dao.dic.po.BairenniuniuRoom;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomExample;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomType;
import com.idealighter.game.dao.dic.po.BairenniuniuRoomTypeExample;
import com.idealighter.game.request.RequestHelper;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.game.convert.GameBoConvert;
import com.idealighter.game.service.games.brnn.bo.BaiRenNiuniuRoomBo;
import com.idealighter.game.service.games.brnn.convert.BaiRenNiuniuBoConvert;
import com.idealighter.game.service.robotcfg.IRobotCfgService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.utils.time.TimeUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaiRenNiuniuService implements IBaiRenNiuniuService {
  @Autowired
  private BairenniuniuRoomTypeMapper niuniuRoomTypeMapper;

  @Autowired
  private BairenniuniuRoomMapper niuniuRoomMapper;

  @Autowired
  private GameBoConvert gameBoConvert;

  @Autowired
  private BaiRenNiuniuBoConvert niuniuRoomConvert;
  

  @Autowired
  private IRobotCfgService robotCfgService;

  @Override
  public ResultPage<RoomTypeBo> findRoomType() {
    ResultPage<RoomTypeBo> resultPage = new ResultPage<>();

    List<RoomTypeBo> bos = null;
    int total = 0;

    List<BairenniuniuRoomType> niuniuRoomTypes =
        niuniuRoomTypeMapper.selectByExample(new BairenniuniuRoomTypeExample());
    bos = gameBoConvert.toRoomTypeBaiRenNiuniuBos(niuniuRoomTypes);
    total = bos.size();

    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public ResultPage<BaiRenNiuniuRoomBo> findRoomByPage(Integer page, Integer pageSize) {
    BairenniuniuRoomExample select = new BairenniuniuRoomExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("type asc , id desc");

    List<BairenniuniuRoom> niuniuRooms = niuniuRoomMapper.selectByExample(select);

    List<BaiRenNiuniuRoomBo> bos = niuniuRoomConvert.toBaiRenNiuniuRoomBos(niuniuRooms);

    long total = niuniuRoomMapper.countByExample(select);

    ResultPage<BaiRenNiuniuRoomBo> resultPage = new ResultPage<>();
    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Transactional
  @Override
  public void saveRoom(BaiRenNiuniuRoomBo bo) {
    BairenniuniuRoom niuniuRoom = niuniuRoomConvert.toBaiRenNiuniuRoom(bo);

    int out = 0;
    if (niuniuRoom.getId() == null) {
      out = niuniuRoomMapper.insertSelective(niuniuRoom);
    } else {
      out = niuniuRoomMapper.updateByPrimaryKey(niuniuRoom);
      if (out > 0) {
        RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
        robotCfgListBo.setState(bo.getIsActive());
        robotCfgListBo.setGoldlower(niuniuRoom.getLower());
        robotCfgListBo.setGoldupper(niuniuRoom.getUpper());
        robotCfgListBo.setMaxgold(niuniuRoom.getUpper());

        robotCfgService.modifyByRoomChange(Game.BAIREN_NIUNIU.type, niuniuRoom.getId(),
            robotCfgListBo);
      }

    }

    if (out > 0 && niuniuRoom.getId() != null) {
      if (niuniuRoom.getIsActive() == 0) {
        RequestHelper.noticeShutdownRoom(Game.BAIREN_NIUNIU.type, niuniuRoom.getId());
      } else if (niuniuRoom.getIsActive() == 1) {
        RequestHelper.noticeStartRoom(Game.BAIREN_NIUNIU.type, niuniuRoom.getId());
      }
    }
  }

  @Transactional
  @Override
  public void changeStatus(int roomId, byte isActive) {
    BairenniuniuRoom niuniuRoom = new BairenniuniuRoom();
    niuniuRoom.setId(roomId);
    niuniuRoom.setIsActive(isActive);

    if (isActive == 1) {
      niuniuRoom.setTimeOpen(TimeUtil.now());
    }

    int out = niuniuRoomMapper.updateByPrimaryKeySelective(niuniuRoom);
    if (out > 0) {
      RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
      robotCfgListBo.setState(isActive);
      robotCfgService.modifyByRoomChange(Game.BAIREN_NIUNIU.type, niuniuRoom.getId(),
          robotCfgListBo);

      if (isActive == 0) {
        RequestHelper.noticeShutdownRoom(Game.BAIREN_NIUNIU.type, roomId);
      } else if (isActive == 1) {
        RequestHelper.noticeStartRoom(Game.BAIREN_NIUNIU.type, roomId);
      }
    }

  }

  @Transactional
  @Override
  public void deleteForCloseRoom(int id) {
    BairenniuniuRoom niuniuRoom = niuniuRoomMapper.selectByPrimaryKey(id);
    IdeaAssert.isTrue(niuniuRoom != null && niuniuRoom.getIsActive() == 0);
    int out = niuniuRoomMapper.deleteByPrimaryKey(id);

    if (out > 0) {
      robotCfgService.deleteByRoomClose(Game.BAIREN_NIUNIU.type, id);
      RequestHelper.noticeShutdownRoom(Game.BAIREN_NIUNIU.type, id);
    }
  }

  @Override
  public List<BaiRenNiuniuRoomBo> findRoom(Byte active) {
    BairenniuniuRoomExample select = new BairenniuniuRoomExample();

    select.setOrderByClause("type asc , id desc");

    BairenniuniuRoomExample.Criteria criteria = select.createCriteria();

    if (active != null) {
      criteria.andIsActiveEqualTo(active);
    }

    List<BairenniuniuRoom> rooms = niuniuRoomMapper.selectByExample(select);

    return niuniuRoomConvert.toBaiRenNiuniuRoomBos(rooms);
  }

  @Override
  public BaiRenNiuniuRoomBo findRoomById(Integer roomId) {
    BairenniuniuRoom room = niuniuRoomMapper.selectByPrimaryKey(roomId);
    return niuniuRoomConvert.toNiuniuRoomBo(room);
  }
}
