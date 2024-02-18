package com.idealighter.game.service.game;

import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.common.constant.Game;
import com.idealighter.game.dao.dic.mapper.GamesMapper;
import com.idealighter.game.dao.dic.po.Games;
import com.idealighter.game.dao.dic.po.GamesExample;
import com.idealighter.game.request.RequestHelper;
import com.idealighter.game.service.game.bo.GameBo;
import com.idealighter.game.service.game.convert.GameBoConvert;
import com.idealighter.game.service.games.baccarat.IBaccaratService;
import com.idealighter.game.service.games.brnn.IBaiRenNiuniuService;
import com.idealighter.game.service.games.jcby.IJcbyService;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {

  @Autowired
  private GamesMapper gamesMapper;

  @Autowired
  private GameBoConvert gameBoConvert;



  @Autowired
  private IBaiRenNiuniuService baiRenNiuniuService;

  @Autowired
  private IJcbyService jcbyService;

  @Autowired
  private IBaccaratService baccaratService;


  @Override
  public List<GameBo> findGame(Boolean active) {
    GamesExample gamesExample = new GamesExample();
    GamesExample.Criteria criteria = gamesExample.createCriteria();
    if (active != null) {
      criteria.andActiveEqualTo(active);
    }

    List<Games> games = gamesMapper.selectByExample(gamesExample);
    return gameBoConvert.toGameBos(games);
  }

  @Override
  public void changeState(int gameId, boolean isActive) {
    Games updateGames = new Games();
    updateGames.setId(gameId);
    updateGames.setActive(isActive);

    int out = gamesMapper.updateByPrimaryKeySelective(updateGames);

    if (out > 0) {
      if (isActive) {
        RequestHelper.noticeGameStart(gameId);
      } else {
        RequestHelper.noticeGameShutdown(gameId);
      }
    }

  }

  @Override
  public List<IdName> findRoom(Integer gameId, Byte active) {

    List<IdName> rooms = null;
    Game game = Game.getGame(gameId);
    if (game != null) {
      switch (game) {
        case BAIREN_NIUNIU:
          rooms = gameBoConvert.toIdNameBaiRenNiunius(baiRenNiuniuService.findRoom(active));
          break;
        case JCBY:
          rooms = gameBoConvert.toIdNameJcbys(jcbyService.findRoom(active));
          break;
        case BACCARAT:
          rooms = gameBoConvert.toIdNameBaccarats(baccaratService.findRoom(active));
          break;
        default:

          rooms = new ArrayList<>();
          break;
      }
    } else {
      rooms = new ArrayList<>();
    }

    return rooms;
  }
  
  @Override
  public List<RoomConfigBo> findRoomConfig(Integer gameId, Byte active) {

    List<RoomConfigBo> rooms = null;
    Game game = Game.getGame(gameId);
    if (game != null) {
      switch (game) {
       
        case BAIREN_NIUNIU:
          rooms = gameBoConvert.toRoomConfigBoBaiRenNiunius(baiRenNiuniuService.findRoom(active));
          break;
        case JCBY:
          rooms = gameBoConvert.toRoomConfigBoJcbys(jcbyService.findRoom(active));
          break;       
        case BACCARAT:
          rooms = gameBoConvert.toRoomConfigBoBaccarats(baccaratService.findRoom(active));
          break;      

        default:

          rooms = new ArrayList<>();
          break;
      }
    } else {
      rooms = new ArrayList<>();
    }

    return rooms;
  }

  @Override
  public IdName findRoomById(Integer gameId, Integer roomId) {
    IdName room = null;
    Game game = Game.getGame(gameId);

    switch (game) {
      case BAIREN_NIUNIU:
        room = gameBoConvert.toIdNameBaiRenNiuniu(baiRenNiuniuService.findRoomById(roomId));
        break;
      case JCBY:
        room = gameBoConvert.toIdNameJcby(jcbyService.findRoomById(roomId));
        break;
      case BACCARAT:
        room = gameBoConvert.toIdNameBaccarat(baccaratService.findRoomById(roomId));
        break;
      default:
        break;
    }


    return room;
  }



}
