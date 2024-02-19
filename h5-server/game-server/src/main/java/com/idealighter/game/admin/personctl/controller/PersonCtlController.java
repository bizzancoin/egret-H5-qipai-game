package com.idealighter.game.admin.personctl.controller;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.admin.personctl.controller.dto.PlayerCtrlBasicInfoDto;
import com.idealighter.game.admin.personctl.controller.dto.PlayerCtrlDetailInfoDto;
import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.manager.PlayerPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.struct.control.GameControl;
import com.idealighter.game.core.service.prizepool.struct.control.GameControlMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/personctl")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class PersonCtlController {
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private PlayerPrizePoolMgrScript playerPrizePoolMgr;

  /**
   * 在线玩家.
   * 
   * @param gameId 游戏id.
   * @param roomId 房间Id.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 在线用户.
   */
  @Path("/playerLists")
  @POST
  public Result onlinePlayer(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @NotNull @Min(1) @FormParam("page") Integer page,
      @NotNull @Min(1) @FormParam("pageSize") Integer pageSize) {

    ConcurrentHashMap<Long, Player> allPlayers = playerMgr.onLinePlayers();

    // 每页最大行数
    int start = (page - 1) * pageSize;
    Long total = 0L;
    List<PlayerCtrlBasicInfoDto> playerLists = new ArrayList<PlayerCtrlBasicInfoDto>();

    for (Player player : allPlayers.values()) {
      if (!player.robot()) {

        AbstractRoom room = player.curRoom;
        if (gameId == 0 && room != null) {
          continue;
        }

        if (gameId > 0 && (room == null || room.game().getType() != gameId)) {
          continue;
        }

        if (roomId != null && roomId > 0
            && (room == null || room.game().getType() != gameId || room.getId() != roomId)) {
          continue;
        }

        if (total >= start && total < start + pageSize) {
          PlayerCtrlBasicInfoDto dto = new PlayerCtrlBasicInfoDto(player);
          playerLists.add(dto);
        }
        total++;
      }
    }

    Result result = new Result(true);
    result.getMap().put("total", total);
    result.getMap().put("list", playerLists);

    return result;
  }

  /**
   * 获取玩家详情.
   * 
   * @Title onlinePlayer.
   * @author houdongsheng
   * @date 2018年1月27日 上午11:12:38
   * @param playerId 玩家编号
   * @return Result
   */
  @Path("/playerDetail")
  @POST
  public Result onlinePlayer(@FormParam("playerId") Long playerId) {
    Player player = playerMgr.getPlayer(playerId);
    PlayerCtrlDetailInfoDto resultDto = null;
    if (player != null) {
      resultDto = new PlayerCtrlDetailInfoDto(player);
    } else {
      PlayerBo domain = playerMgr.selectPlayer(playerId);
      resultDto = new PlayerCtrlDetailInfoDto(domain);
    }

    Result result = new Result(true);
    result.getMap().put("result", resultDto);
    return result;
  }

  /**
   * 增加玩家控制.
   * 
   * @Title addPlayerCtl.
   * @author houdongsheng
   * @date 2018年1月27日 下午3:52:27
   * @param playerId 玩家编号
   * @param gameId 游戏编号
   * @param prizePoolGold 奖金数量
   * @param control 控制
   * @return Result
   */
  @Path("/addPlayerCtl")
  @POST
  public Result addPlayerCtl(@FormParam("playerId") Long playerId,
      @FormParam("gameId") Integer gameId, @FormParam("prizePoolGold") Long prizePoolGold,
      @FormParam("control") String control) {
    Class<? extends GameControl> clz = GameControlMap.getGameControlGameId(gameId);
    HuohuaAssert.isTrue(clz != null);
    GameControl controlObj = JSON.parseObject(control, clz);
    HuohuaAssert.isTrue(controlObj != null);
    playerPrizePoolMgr.addPrizePool(playerId, gameId, prizePoolGold, control);
    return new Result(ErrorCode.OK);
  }

  /**
   * 修改玩家奖池中的金币.
   * 
   * @Title updatePrizePoolGold.
   * @Description
   * @author houdongsheng
   * @date 2018年1月27日 下午3:55:12
   * @param playerId 玩家编号
   * @param gameId 游戏编号
   * @param prizePoolGold 奖池金币
   * @return Result
   */
  @Path("/updatePrizePoolGold")
  @POST
  public Result updatePrizePoolGold(@FormParam("playerId") Long playerId,
      @FormParam("gameId") Integer gameId, @FormParam("prizePoolGold") Long prizePoolGold) {
    playerPrizePoolMgr.updatePrizePoolGold(playerId, gameId, prizePoolGold);
    return new Result(ErrorCode.OK);
  }

  /**
   * 修改玩家奖池控制.
   * 
   * @Title updateCtl.
   * @Description
   * @author houdongsheng
   * @date 2018年1月27日 下午3:58:13
   * @param playerId 玩家编号
   * @param gameId 游戏编号
   * @param control 控制
   * @return Result
   */
  @Path("/updateCtl")
  @POST
  public Result updateCtl(@FormParam("playerId") Long playerId, @FormParam("gameId") Integer gameId,
      @FormParam("control") String control) {
    Class<? extends GameControl> clz = GameControlMap.getGameControlGameId(gameId);
    HuohuaAssert.isTrue(clz != null);
    GameControl controlObj = JSON.parseObject(control, clz);
    HuohuaAssert.isTrue(controlObj != null);

    playerPrizePoolMgr.updatePrizePoolControl(playerId, gameId, control);
    return new Result(ErrorCode.OK);
  }
}
