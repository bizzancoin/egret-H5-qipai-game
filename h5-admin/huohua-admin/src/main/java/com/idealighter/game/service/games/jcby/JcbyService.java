package com.idealighter.game.service.games.jcby;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.dao.dic.mapper.JcbyRoomMapper;
import com.idealighter.game.dao.dic.mapper.JcbyRoomTypeMapper;
import com.idealighter.game.dao.dic.mapper.JcbyScenceMapper;
import com.idealighter.game.dao.dic.po.JcbyRoom;
import com.idealighter.game.dao.dic.po.JcbyRoomExample;
import com.idealighter.game.dao.dic.po.JcbyRoomType;
import com.idealighter.game.dao.dic.po.JcbyRoomTypeExample;
import com.idealighter.game.dao.dic.po.JcbyScence;
import com.idealighter.game.dao.dic.po.JcbyScenceExample;
import com.idealighter.game.request.RequestHelper;
import com.idealighter.game.service.game.bo.RoomTypeBo;
import com.idealighter.game.service.game.convert.GameBoConvert;
import com.idealighter.game.service.games.jcby.bo.JcbyRoomBo;
import com.idealighter.game.service.games.jcby.bo.JcbyScenceBo;
import com.idealighter.game.service.games.jcby.convert.JcbyBoConvert;
import com.idealighter.game.service.robotcfg.IRobotCfgService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.utils.time.TimeUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JcbyService implements IJcbyService {

  @Autowired
  private JcbyRoomTypeMapper jcbyRoomTypeMapper;

  @Autowired
  private JcbyRoomMapper jcbyRoomMapper;

  @Autowired
  private JcbyScenceMapper jcbyScenceMapper;

  @Autowired
  private GameBoConvert gameBoConvert;

  @Autowired
  private JcbyBoConvert jcbyRoomConvert;

  @Autowired
  private IRobotCfgService robotCfgService;

  @Override
  public ResultPage<RoomTypeBo> findRoomType() {
    ResultPage<RoomTypeBo> resultPage = new ResultPage<>();

    List<RoomTypeBo> bos = null;
    int total = 0;

    List<JcbyRoomType> jcbyRoomTypes =
        jcbyRoomTypeMapper.selectByExample(new JcbyRoomTypeExample());
    bos = gameBoConvert.toRoomTypeJcbyBos(jcbyRoomTypes);
    total = bos.size();

    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public ResultPage<JcbyRoomBo> findRoomByPage(Integer page, Integer pageSize) {
    JcbyRoomExample select = new JcbyRoomExample();
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("type asc , id desc");

    List<JcbyRoom> jcbyRooms = jcbyRoomMapper.selectByExample(select);

    List<JcbyRoomBo> bos = jcbyRoomConvert.toJcbyRoomBos(jcbyRooms);

    long total = jcbyRoomMapper.countByExample(select);

    ResultPage<JcbyRoomBo> resultPage = new ResultPage<>();
    resultPage.setList(bos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Transactional
  @Override
  public void saveRoom(JcbyRoomBo bo) {
    JcbyRoom jcbyRoom = jcbyRoomConvert.toJcbyRoom(bo);

    int out = 0;
    if (jcbyRoom.getId() == null) {
      out = jcbyRoomMapper.insertSelective(jcbyRoom);
    } else {
      out = jcbyRoomMapper.updateByPrimaryKey(jcbyRoom);
      if (out > 0) {
        RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
        robotCfgListBo.setState(bo.getIsActive());
        robotCfgListBo.setGoldlower(jcbyRoom.getLower());
        robotCfgListBo.setGoldupper(jcbyRoom.getUpper());
        robotCfgListBo.setMaxgold(jcbyRoom.getUpper());

        robotCfgService.modifyByRoomChange(Game.JCBY.type, jcbyRoom.getId(), robotCfgListBo);
      }

    }

    if (out > 0 && jcbyRoom.getId() != null) {
      if (jcbyRoom.getIsActive() == 0) {
        RequestHelper.noticeShutdownRoom(Game.JCBY.type, jcbyRoom.getId());
      } else if (jcbyRoom.getIsActive() == 1) {
        RequestHelper.noticeStartRoom(Game.JCBY.type, jcbyRoom.getId());
      }
    }
  }

  @Transactional
  @Override
  public void changeStatus(int id, byte isActive) {
    JcbyRoom jcbyRoom = new JcbyRoom();
    jcbyRoom.setId(id);
    jcbyRoom.setIsActive(isActive);

    if (isActive == 1) {
      jcbyRoom.setTimeOpen(TimeUtil.now());
    }

    int out = jcbyRoomMapper.updateByPrimaryKeySelective(jcbyRoom);
    if (out > 0) {
      RobotCfgListBo robotCfgListBo = new RobotCfgListBo();
      robotCfgListBo.setState(jcbyRoom.getIsActive());
      robotCfgListBo.setGoldlower(jcbyRoom.getLower());
      robotCfgListBo.setGoldupper(jcbyRoom.getUpper());
      robotCfgListBo.setMaxgold(jcbyRoom.getUpper());

      robotCfgService.modifyByRoomChange(Game.JCBY.type, jcbyRoom.getId(), robotCfgListBo);
    }

    if (out > 0 && jcbyRoom.getId() != null) {
      if (jcbyRoom.getIsActive() == 0) {
        RequestHelper.noticeShutdownRoom(Game.JCBY.type, jcbyRoom.getId());
      } else if (jcbyRoom.getIsActive() == 1) {
        RequestHelper.noticeStartRoom(Game.JCBY.type, jcbyRoom.getId());
      }
    }

  }

  @Transactional
  @Override
  public void deleteForCloseRoom(int id) {
    JcbyRoom jcbyRoom = jcbyRoomMapper.selectByPrimaryKey(id);
    IdeaAssert.isTrue(jcbyRoom != null && jcbyRoom.getIsActive() == 0);
    int out = jcbyRoomMapper.deleteByPrimaryKey(id);

    if (out > 0) {
      robotCfgService.deleteByRoomClose(Game.JCBY.type, id);
      RequestHelper.noticeShutdownRoom(Game.JCBY.type, id);
    }

  }

  @Override
  public List<JcbyScenceBo> findScenceList() {

    List<JcbyScence> sences = jcbyScenceMapper.selectByExample(new JcbyScenceExample());

    return jcbyRoomConvert.toJcbyScences(sences);
  }

  @Override
  public List<JcbyRoomBo> findRoom(Byte active) {
    JcbyRoomExample select = new JcbyRoomExample();

    select.setOrderByClause("type asc , id desc");

    JcbyRoomExample.Criteria criteria = select.createCriteria();

    if (active != null) {
      criteria.andIsActiveEqualTo(active);
    }

    List<JcbyRoom> rooms = jcbyRoomMapper.selectByExample(select);

    return jcbyRoomConvert.toJcbyRoomBos(rooms);
  }

  @Override
  public JcbyRoomBo findRoomById(Integer roomId) {
    JcbyRoom room = jcbyRoomMapper.selectByPrimaryKey(roomId);
    return jcbyRoomConvert.toJcbyRoomBo(room);
  }


}
