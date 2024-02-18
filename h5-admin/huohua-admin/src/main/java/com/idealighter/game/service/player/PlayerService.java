package com.idealighter.game.service.player;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.constant.PlayerType;
import com.idealighter.game.common.constant.PlayerTypeForClient;
import com.idealighter.game.common.constant.RegisterType;
import com.idealighter.game.common.util.CheckUtils;
import com.idealighter.game.dao.data.mapper.PlayerMainMapper;
import com.idealighter.game.dao.data.mapper.PlayerStatusRecordMapper;
import com.idealighter.game.dao.data.po.PlayerMain;
import com.idealighter.game.dao.data.po.PlayerMainExample;
import com.idealighter.game.dao.data.po.PlayerStatusRecord;
import com.idealighter.game.dao.data.po.PlayerStatusRecordExample;
import com.idealighter.game.service.player.bo.PlayerBo;
import com.idealighter.game.service.player.bo.PlayerListBo;
import com.idealighter.game.service.player.convert.PlayerBoConvert;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {

  @Autowired
  private PlayerMainMapper playerMainMapper;

  @Autowired
  private PlayerStatusRecordMapper playerStatusRecordMapper;

  @Autowired
  private PlayerBoConvert playerBoConvert;

  @Override
  public List<PlayerBo> selectByQuery(String query, int limit) {
    PlayerMainExample select = new PlayerMainExample();
    if (EmptyUtil.stringIsNotEmpty(query)) {
      String condition = query + "%";
      select.createCriteria().andPlayerNameLike(condition);

      if (CheckUtils.isNumeric(query)) {
        Long superId = Long.parseLong(query);
        select.or(select.createCriteria().andSuperIdEqualTo(superId));
      }
    }

    List<PlayerMain> players = playerMainMapper.selectByExample(select);

    List<PlayerBo> playerBos = playerBoConvert.toPlayerBos(players);

    return playerBos;
  }


  private PlayerStatusRecord selectPlayerStatusRecord(long playerId) {
    PlayerStatusRecord playerStatusRecordDomain = null;
    PlayerStatusRecordExample statusRecordSelect = new PlayerStatusRecordExample();
    statusRecordSelect.createCriteria().andPlayerIdEqualTo(playerId);
    List<PlayerStatusRecord> statusRecordDomains =
        playerStatusRecordMapper.selectByExample(statusRecordSelect);
    if (EmptyUtil.listIsNotEmpty(statusRecordDomains)) {
      playerStatusRecordDomain = statusRecordDomains.get(0);
    }
    return playerStatusRecordDomain;
  }

  @Override
  public ResultPage<PlayerListBo> selectByPage(Long playerId, Long superId, Byte playerType,
      String nickname, String userName, String phone, String channelId, Boolean locked,
      Integer page, Integer pageSize) {

    PlayerMainExample select = new PlayerMainExample();

    PlayerMainExample.Criteria criteria = select.createCriteria();

    if (playerId != null) {
      criteria.andIdEqualTo(playerId);
    }
    if (superId != null) {
      criteria.andSuperIdEqualTo(superId);
    }
    if (playerType != null) {
      if (playerType == PlayerTypeForClient.USER) {
        criteria.andTypeEqualTo(PlayerType.USER).andRegisterTypeNotEqualTo(RegisterType.ROBOT);
      } else if (playerType == PlayerTypeForClient.AGENT) {
        criteria.andTypeEqualTo(PlayerType.AGENT).andRegisterTypeNotEqualTo(RegisterType.ROBOT);
      } else if (playerType == PlayerTypeForClient.ROBOT) {
        criteria.andRegisterTypeEqualTo(RegisterType.ROBOT);
      }
    }
    if (EmptyUtil.stringIsNotEmpty(nickname)) {
      criteria.andPlayerNameLike(nickname + "%");
    }
    if (EmptyUtil.stringIsNotEmpty(phone)) {
      criteria.andPhoneEqualTo(phone);
    }
    if (EmptyUtil.stringIsNotEmpty(channelId)) {
      criteria.andChannelIdEqualTo(channelId);
    }
    if (EmptyUtil.stringIsNotEmpty(userName)) {
      criteria.andUserNameEqualTo(userName);
    }
    if (locked != null) {
      criteria.andLockedEqualTo(locked);
    }

    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);

    select.setOrderByClause("online desc, id desc");

    ResultPage<PlayerListBo> result = new ResultPage<>();

    List<PlayerListBo> bos = new ArrayList<>();
    List<PlayerMain> playerMains = playerMainMapper.selectByExample(select);
    if (EmptyUtil.listIsNotEmpty(playerMains)) {
      for (Iterator<PlayerMain> iterator = playerMains.iterator(); iterator.hasNext();) {
        PlayerMain playerMain = iterator.next();
        PlayerStatusRecord statusRecord = selectPlayerStatusRecord(playerMain.getId());
        PlayerListBo playerListBo = playerBoConvert.toPlayerListBo(playerMain, statusRecord);
        bos.add(playerListBo);
      }
    }

    long total = playerMainMapper.countByExample(select);
    result.setList(bos);
    result.setTotal(total);
    return result;
  }


  @Override
  public PlayerBo findById(Long id) {
    PlayerMain playerMain = playerMainMapper.selectByPrimaryKey(id);
    return playerBoConvert.toPlayerBo(playerMain);
  }


  @Override
  public long countChannel(String channelId) {
    PlayerMainExample select = new PlayerMainExample();
    select.createCriteria().andChannelIdEqualTo(channelId);
    return playerMainMapper.countByExample(select);
  }

}
