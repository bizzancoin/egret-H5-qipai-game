package com.idealighter.game.web.robotcfg;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.dictionary.dic.RobotConfigDic;
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.scheduler.PlayerScheduler;

@Path("/robot")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class RobotCfgController {
  @Inject
  private RobotConfigDic robotConfigDic;
  @Inject
  private PlayerScheduler playerScheduler;

  /**
   * 获取游戏列表.
   * 
   * @return 结果.
   */
  @Path("/reloadRobotCfg")
  @POST
  public Result reloadRobotCfg(@NotNull @FormParam("gameId") Integer gameId,
      @NotNull @FormParam("roomId") Integer roomId) {
    // 重新加载配置文件
    robotConfigDic.load();

    // 使该房间中的机器人退出
    ApplicationContext.getBean(PlayerScheduler.class).logOutRobotOfGameAndRoom(gameId, roomId);
    Result result = new Result(true);
    return result;
  }

  /**
   * 重新加载机器人 .
   *
   */
  @Path("/reloadRobotPlayers")
  @POST
  public Result reloadRobotPlayers() {
    playerScheduler.reloadRobots();
    Result result = new Result(true);
    return result;
  }

  /**
   * 重新加载房间配置 .
   *
   * @author abin
   * @date 2018年5月2日 下午7:24:40
   * @param gameId 游戏id.
   * @return 结果.
   */
  @Path("/reloadRoom")
  @POST
  public Result reloadRoom(@NotNull @FormParam("gameId") Integer gameId) {
    playerScheduler.reloadRoom(gameId);
    Result result = new Result(true);
    return result;
  }

  /**
   * 重新加载游戏配置.
   *
   * @author abin
   * @date 2018年5月16日 下午1:53:24
   * @return 操作结果.
   */
  @Path("/reloadGame")
  @POST
  public Result reloadGame() {
    playerScheduler.reloadGame();

    return new Result(true);
  }


}
