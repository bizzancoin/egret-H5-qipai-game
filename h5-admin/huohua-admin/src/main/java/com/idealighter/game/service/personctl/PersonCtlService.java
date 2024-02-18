package com.idealighter.game.service.personctl;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.dao.data.mapper.PlayerMainMapper;
import com.idealighter.game.dao.data.po.PlayerMain;
import com.idealighter.game.dao.data.po.PlayerMainExample;
import com.idealighter.game.dao.logger.mapper.GameWinLoseLogMapper;
import com.idealighter.game.dao.logger.po.SimpleWinLoseLog;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.personctl.bo.PlayerCtrlBasicInfoBo;
import com.idealighter.game.service.personctl.bo.PlayerCtrlDetailInfoBo;
import com.idealighter.game.service.personctl.bo.PlayerCtrlPlayerListBo;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.json.JsonUtil;
import com.idealighter.utils.time.TimeUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCtlService implements IPersonCtlService {
  @Autowired
  private GameWinLoseLogMapper gameWinLoseLogMapper;
  @Autowired
  private PlayerMainMapper playerMainMapper;

  @Override
  public PlayerCtrlPlayerListBo findPlayerCtrlList(Integer gameId, Integer roomId, Integer page,
      Integer pageSize) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("page", page);
    paramBuild.add("pageSize", pageSize);

    String serviceResponse =
        GameServerRequest.post(GameServerUrl.PERSONCTL_PERSON_LIST, paramBuild);
    Result responseResult = JsonUtil.fromJson(serviceResponse, Result.class);
    PlayerCtrlPlayerListBo result = new PlayerCtrlPlayerListBo();
    if (responseResult.getCode() == ErrorCode.OK.getCode()) {
      result.setTotal(((Integer) responseResult.getMap().get("total")).longValue());

      List<PlayerCtrlBasicInfoBo> resultList = JsonUtil.fromJsonToList(
          JsonUtil.toJson(responseResult.getMap().get("list")), PlayerCtrlBasicInfoBo.class);
      if (EmptyUtil.listIsNotEmpty(resultList)) {
        List<Long> playerIds = new ArrayList<Long>();
        for (PlayerCtrlBasicInfoBo item : resultList) {
          playerIds.add(item.getPlayerId());
        }
        String curDate = TimeUtil.yyyyMMdd(TimeUtil.now()).replace("-", "");
        String yesToday = TimeUtil
            .yyyyMMdd(new Date(TimeUtil.now().getTime() - 1000 * 60 * 60 * 24)).replace("-", "");
        List<SimpleWinLoseLog> curDateWinLose = null;
        List<SimpleWinLoseLog> yesTodayWinLose = null;

        if (gameWinLoseLogMapper.existTable(gameId, curDate) == 1) {
          curDateWinLose = gameWinLoseLogMapper.findPlayerWinLoseOfGame(gameId, curDate, playerIds);
        }
        if (gameWinLoseLogMapper.existTable(gameId, yesToday) == 1) {
          yesTodayWinLose =
              gameWinLoseLogMapper.findPlayerWinLoseOfGame(gameId, yesToday, playerIds);
        }

        if (EmptyUtil.listIsNotEmpty(curDateWinLose)) {
          for (PlayerCtrlBasicInfoBo item : resultList) {
            Long totalBetResult = 0L;
            for (SimpleWinLoseLog winLose : curDateWinLose) {
              if (winLose.getPlayerId().equals(item.getPlayerId())) {
                totalBetResult = winLose.getWinLose();
                break;
              }
            }
            item.setTodayBetResult(totalBetResult);
          }
        }

        if (EmptyUtil.listIsNotEmpty(yesTodayWinLose)) {
          for (PlayerCtrlBasicInfoBo item : resultList) {
            Long yestodayBetResult = 0L;
            for (SimpleWinLoseLog winLose : yesTodayWinLose) {
              if (winLose.getPlayerId().equals(item.getPlayerId())) {
                yestodayBetResult = winLose.getWinLose();
                break;
              }
            }
            item.setYesTodayBetResult(yestodayBetResult);
          }
        }
      }
      result.setList(resultList);
    } else {
      result.setTotal(0L);
    }
    return result;
  }

  @Override
  public PlayerCtrlDetailInfoBo findPlayerCtrlDetailInfo(Long playerId, String nickName) {
    IdeaAssert.isTrue(playerId != null || EmptyUtil.stringIsNotEmpty(nickName),
        ErrorCode.BAD_REQUEST);
    PlayerMainExample example = new PlayerMainExample();
    PlayerMainExample.Criteria criteria = example.createCriteria();
    if (playerId != null) {
      criteria.andIdEqualTo(playerId);
    }
    if (EmptyUtil.stringIsNotEmpty(nickName)) {
      criteria.andPlayerNameEqualTo(nickName);
    }
    example.setLimit(1);
    List<PlayerMain> players = playerMainMapper.selectByExample(example);
    IdeaAssert.isTrue(EmptyUtil.listIsNotEmpty(players), ErrorCode.PLAYER_NOT_EXIST);
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", players.get(0).getId());

    String serviceResponse =
        GameServerRequest.post(GameServerUrl.PERSONCTL_PERSON_DETAIL, paramBuild);
    Result responseResult = JsonUtil.fromJson(serviceResponse, Result.class);

    PlayerCtrlDetailInfoBo result = null;
    if (responseResult.getCode() == ErrorCode.OK.getCode()) {
      String json = JsonUtil.toJson(responseResult.getMap().get("result"));
      result = JsonUtil.fromJson(json, PlayerCtrlDetailInfoBo.class);

      Integer gameId = result.getPlayerInfo().getGameId();
      SimpleWinLoseLog curDateWinLose = null;
      SimpleWinLoseLog yestodayWinLose = null;
      if (gameId != null && gameId > 0) {
        // 获取玩家输赢
        String curDate = TimeUtil.yyyyMMdd(TimeUtil.now()).replace("-", "");
        String yesToday = TimeUtil
            .yyyyMMdd(new Date(TimeUtil.now().getTime() - 1000 * 60 * 60 * 24)).replace("-", "");
        if (gameWinLoseLogMapper.existTable(gameId, curDate) == 1) {
          curDateWinLose =
              gameWinLoseLogMapper.findWinLoseOfGameByPlayerId(gameId, curDate, playerId);
        }

        if (gameWinLoseLogMapper.existTable(gameId, yesToday) == 1) {
          yestodayWinLose =
              gameWinLoseLogMapper.findWinLoseOfGameByPlayerId(gameId, yesToday, playerId);
        }
      }

      Long todayWinLose = 0L;
      if (curDateWinLose != null) {
        todayWinLose = curDateWinLose.getWinLose();
      }
      Long yestodayWinLoseChips = 0L;
      if (yestodayWinLose != null) {
        yestodayWinLoseChips = yestodayWinLose.getWinLose();
      }
      result.getPlayerInfo().setTodayBetResult(todayWinLose);
      result.getPlayerInfo().setYesTodayBetResult(yestodayWinLoseChips);
    }
    return result;
  }

  @Override
  public String addPlayerCtl(Integer gameId, Long playerId, Long prizePool, String control) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("gameId", gameId);
    paramBuild.add("prizePoolGold", prizePool);
    paramBuild.add("control", control);

    return GameServerRequest.post(GameServerUrl.PERSONCTL_ADD, paramBuild);
  }

  @Override
  public String updatePrizePoolGold(Integer gameId, Long playerId, Long prizePoolGold) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("gameId", gameId);
    paramBuild.add("prizePoolGold", prizePoolGold);

    return GameServerRequest.post(GameServerUrl.PERSONCTL_UPDATE_GOLD, paramBuild);
  }

  @Override
  public String updateCtl(Integer gameId, Long playerId, String control) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("gameId", gameId);
    paramBuild.add("control", control);

    return GameServerRequest.post(GameServerUrl.PERSONCTL_UPDATE_CTL, paramBuild);
  }
}
