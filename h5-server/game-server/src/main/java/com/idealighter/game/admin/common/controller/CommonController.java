package com.idealighter.game.admin.common.controller;

import com.google.inject.Singleton;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.admin.common.controller.convert.GameListDtoConvert;
import com.idealighter.game.admin.common.controller.convert.RoomListDtoConvert;
import com.idealighter.game.admin.common.controller.dto.AllRoomListDto;
import com.idealighter.game.admin.common.controller.dto.GameListDto;
import com.idealighter.game.admin.common.controller.dto.JcbyControlDto;
import com.idealighter.game.admin.common.controller.dto.RoomListDto;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.common.GameIdConstant;
import com.idealighter.game.core.common.GameMap;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.games.baccarat.manager.BaccaratDataMgr;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratRoom;
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuDataMgr;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuRoom;
import com.idealighter.game.core.service.games.jcby.manager.JcbyDataMgr;
import com.idealighter.game.core.service.games.jcby.struct.JcbyDifficulty;
import com.idealighter.game.core.service.games.jcby.struct.JcbyRoom;
import com.idealighter.game.dictionary.dic.JcbyRoomDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/common")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class CommonController {
  /**
   * 获取游戏列表.
   * 
   * @return 结果.
   */
  @Path("/findGameList")
  @POST
  public Result findGameList() {

    List<Game> games = GameMap.allGame();
    List<GameListDto> list = GameListDtoConvert.INSTANCE.toGameListDto(games);

    Result result = new Result(true);
    result.getMap().put("list", list);
    return result;
  }

  /**
   * 获取游戏中的房间列表.
   * 
   * @Title findRoomListByGameId.
   * @author houdongsheng
   * @date 2018年1月27日 下午5:39:51
   * @param gameId 游戏编号
   * @return Result
   */
  @Path("/findRoomListByGameId")
  @POST
  public Result findRoomListByGameId(@NotNull @FormParam("gameId") Integer gameId) {
    List<RoomListDto> rooms = new ArrayList<RoomListDto>();
    switch (gameId) {
      
      case GameIdConstant.JCBY:
        JcbyDataMgr jcbyDataMgr = ApplicationContext.getBean(JcbyDataMgr.class);
        for (JcbyRoom item : jcbyDataMgr.allRooms()) {
          JcbyRoomDomain domain = item.getRoomDomain();
          if (domain.getIsActive() == RoomActiveConstant.ACTIVE) {
            rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
          }
        }
        break;
     
     
      case GameIdConstant.BACCARAT:
        BaccaratDataMgr baccaratDataMgr = ApplicationContext.getBean(BaccaratDataMgr.class);
        for (BaccaratRoom item : baccaratDataMgr.newestRooms()) {
          BaccaratRoomDomain domain = item.getRoomDomain();
          if (domain.getIsActive() == RoomActiveConstant.ACTIVE) {
            rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
          }
        }
        break;

      case GameIdConstant.BAIREN_NIUNIU:
        BaiRenNiuNiuDataMgr brnnDataMgr = ApplicationContext.getBean(BaiRenNiuNiuDataMgr.class);
        for (BaiRenNiuNiuRoom item : brnnDataMgr.newestRooms()) {
          BairenniuniuRoomDomain wrapper = item.getRoomDomain();
          if (wrapper.getIsActive() == RoomActiveConstant.ACTIVE) {
            rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
          }
        }

        break;
      default:
        break;
    }
    Result result = new Result(true);
    result.getMap().put("list", rooms);
    return result;
  }

  /**
   * 获取房间列表.
   * 
   * @return 房间列表.
   */
  @Path("/findAllRoomList")
  @POST
  public Result findAllRoomList() {

    List<RoomListDto> rooms = new ArrayList<RoomListDto>();
   
  

    // 金蝉捕鱼
    AllRoomListDto jcby = new AllRoomListDto();
    jcby.setGameId(GameIdConstant.JCBY);
    jcby.setGameName(Game.getGame(GameIdConstant.JCBY).getDesc());

    rooms = new ArrayList<RoomListDto>();
    JcbyRoomDic jcpyRoomDic = ApplicationContext.getBean(JcbyRoomDic.class);
    JcbyDataMgr jcbyDataMgr = ApplicationContext.getBean(JcbyDataMgr.class);
    for (JcbyRoom item : jcbyDataMgr.allRooms()) {
      JcbyRoomDomain roomDomain = jcpyRoomDic.get(item.getId());
      if (roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
        rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
      }
    }
    jcby.setRooms(rooms);
    List<AllRoomListDto> list = new ArrayList<AllRoomListDto>();
    list.add(jcby);



    // 百家乐
    AllRoomListDto baccarat = new AllRoomListDto();
    baccarat.setGameId(GameIdConstant.BACCARAT);
    baccarat.setGameName(Game.getGame(GameIdConstant.BACCARAT).getDesc());


    rooms = new ArrayList<RoomListDto>();
    BaccaratDataMgr baccaratDataMgr = ApplicationContext.getBean(BaccaratDataMgr.class);

    for (BaccaratRoom item : baccaratDataMgr.newestRooms()) {
      BaccaratRoomDomain wrapper = item.getRoomDomain();
      if (wrapper.getIsActive() == RoomActiveConstant.ACTIVE) {
        rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
      }
    }
    baccarat.setRooms(rooms);
    list.add(baccarat);

    // 百人牛牛
    AllRoomListDto brnn = new AllRoomListDto();
    brnn.setGameId(GameIdConstant.BAIREN_NIUNIU);
    brnn.setGameName(Game.getGame(GameIdConstant.BAIREN_NIUNIU).getDesc());

    rooms = new ArrayList<RoomListDto>();
    BaiRenNiuNiuDataMgr baiRenNiuNiuDataMgr = ApplicationContext.getBean(BaiRenNiuNiuDataMgr.class);
    for (BaiRenNiuNiuRoom item : baiRenNiuNiuDataMgr.newestRooms()) {
      BairenniuniuRoomDomain roomDomain = item.getRoomDomain();
      if (roomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
        rooms.add(RoomListDtoConvert.INSTANCE.toRoomListDto(item));
      }
    }
    brnn.setRooms(rooms);
    list.add(brnn);

    Result result = new Result(true);
    result.getMap().put("list", list);
    return result;
  }

  /**
   * 获取游戏控制配置信息.
   * 
   * @Title findCtlCfgInfo. *
   * @author houdongsheng
   * @date 2018年1月27日 下午7:47:57
   * @param gameId 游戏编号
   * @return Result
   */
  @Path("/findCtlCfgInfo")
  @POST
  public Result findCtlCfgInfo(@NotNull @FormParam("gameId") Integer gameId) {
    Result result = new Result(true);
    switch (gameId) {     
     

      case GameIdConstant.JCBY: // 金蝉捕鱼
        List<JcbyControlDto> jcbyControlDtoList = new ArrayList<JcbyControlDto>();
        for (JcbyDifficulty item : JcbyDifficulty.values()) {
          jcbyControlDtoList.add(new JcbyControlDto(item.getDifficulty(), item.getName()));
        }
        result.getMap().put("difficultList", jcbyControlDtoList);
        break;    
     
      case GameIdConstant.BAIREN_NIUNIU: // 百人牛牛
        break;
      default:
        break;
    }
    return result;
  }
}
