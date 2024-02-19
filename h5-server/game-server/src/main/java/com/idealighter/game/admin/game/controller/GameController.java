package com.idealighter.game.admin.game.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.ClearGameDataEvent;
import com.idealighter.game.core.service.event.struct.ShutdownGameEvent;
import com.idealighter.game.core.service.event.struct.ShutdownRoomEvent;
import com.idealighter.game.core.service.event.struct.StartGameEvent;
import com.idealighter.game.core.service.event.struct.StartRoomEvent;
import com.idealighter.game.dictionary.dic.GamesDic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/game")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class GameController {

  @Inject
  private EventMgr eventMgr;

  @Inject
  private GamesDic gamesDic;

  /**
   * 清空玩家数据.
   * 
   * @param playerId 玩家Id.
   * @return 结果.
   */
  @Path("/clearPlayerData")
  @POST
  public Result clearGameData(@NotNull @Min(1) @FormParam("playerId") Long playerId) {

    eventMgr.post(new ClearGameDataEvent(playerId));

    return new Result(true);
  }

  /**
   * 关闭服务器.
   * 
   * @return 操作结果.
   */
  @Path("/shutdownServer")
  @POST
  public Result shutdownServer() {
    System.exit(0);
    return new Result(true);
  }

  /**
   * 房间开启.
   * 
   * @param gameId 游戏Id.
   * @param roomId 房间Id.
   * @return 结果.
   */
  @Path("/startRoom")
  @POST
  public Result startRoom(@NotNull @Min(1) @FormParam("gameId") Integer gameId,
      @NotNull @Min(1) @FormParam("roomId") Integer roomId) {
    Game game = Game.getGame(gameId);

    eventMgr.post(new StartRoomEvent(game, roomId));
    return new Result(true);
  }


  /**
   * 房间关闭.
   * 
   * @param gameId 游戏Id.
   * @param roomId 房间Id.
   * @return 结果.)
   */
  @Path("/shutdownRoom")
  @POST
  public Result shutdownRoom(@NotNull @Min(1) @FormParam("gameId") Integer gameId,
      @NotNull @Min(1) @FormParam("roomId") Integer roomId) {

    Game game = Game.getGame(gameId);

    eventMgr.post(new ShutdownRoomEvent(game, roomId));
    return new Result(true);
  }

  /**
   * 关闭游戏响应 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:33:49
   * @param gameId 游戏id.
   * @return 结果.
   */
  @Path("/shutdownGame")
  @POST
  public Result shutDownGame(@NotNull @Min(1) @FormParam("gameId") Integer gameId) {
    gamesDic.load();
    Game game = Game.getGame(gameId);
    eventMgr.post(new ShutdownGameEvent(game));

    return new Result(true);
  }

  /**
   * 开启游戏 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:34:05
   * @param gameId 游戏id.
   * @return 结果.
   */
  @Path("/startGame")
  @POST
  public Result startGame(@NotNull @Min(1) @FormParam("gameId") Integer gameId) {
    gamesDic.load();

    Game game = Game.getGame(gameId);
    eventMgr.post(new StartGameEvent(game));

    return new Result(true);
  }
}
