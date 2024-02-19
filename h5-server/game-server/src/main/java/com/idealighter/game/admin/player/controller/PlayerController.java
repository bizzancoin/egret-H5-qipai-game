package com.idealighter.game.admin.player.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.admin.player.controller.convert.PlayerDtoConvert;
import com.idealighter.game.admin.player.controller.dto.OnLinePlayerDto;
import com.idealighter.game.admin.player.controller.dto.PlayerStatusDto;
import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.assertions.IdeaAssert;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.blacklist.manager.BlackListMgr;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.gm.BackendAddGoldLog;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.IPlayerService;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.util.PwdEncode;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.third.utils.ThirdOrderUtils;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

@Path("/player")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class PlayerController {

  @Inject
  private PlayerMgr playerMgr;

  @Inject
  private BlackListMgr blackListMgr;

  @Inject
  private IPlayerService playerService;

  /**
   * 在线玩家.
   * 
   * @param gameId 游戏id.
   * @param roomId 房间Id.
   * @param playerId 玩家Id.
   * @param nickname 昵称.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 在线用户.
   */
  @Path("/onlinePlayers")
  @GET
  public Result onlinePlayer(@QueryParam("gameId") Integer gameId,
      @QueryParam("superId") Long superId, @QueryParam("roomId") Integer roomId,
      @QueryParam("playerId") Long playerId, @QueryParam("nickname") String nickname,
      @QueryParam("userName") String userName, @QueryParam("channelId") String channelId,
      @NotNull @Min(1) @QueryParam("page") Integer page,
      @NotNull @Min(1) @QueryParam("pageSize") Integer pageSize) {

    ConcurrentHashMap<Long, Player> allPlayers = playerMgr.onLinePlayers();

    // 每页最大行数
    int start = (page - 1) * pageSize;
    int total = 0;
    List<OnLinePlayerDto> onLinePlayers = new ArrayList<>();

    for (Player player : allPlayers.values()) {
      if (!player.robot()) {
        if (channelId != null && !channelId.isEmpty() && !channelId.equals(player.getChannelId())) {
          continue;
        }

        if (playerId != null && playerId > 0 && player.getId() != playerId) {
          continue;
        }
        if (superId != null && superId > 0 && player.getSuperId() != superId) {
          continue;
        }

        if (EmptyUtil.stringIsNotEmpty(nickname) && !player.getPlayerName().equals(nickname)) {
          continue;
        }
        if (EmptyUtil.stringIsNotEmpty(userName) && !player.getUserName().equals(userName)) {
          continue;
        }

        AbstractRoom room = player.curRoom;
        if (gameId == null && roomId != null) {
          continue;
        }

        if (gameId != null && (room == null || room.game().getType() != gameId)) {
          continue;
        }

        if (roomId != null
            && (room == null || room.game().getType() != gameId || room.getId() != roomId)) {
          continue;
        }

        if (total >= start && total < start + pageSize) {
          OnLinePlayerDto dto = PlayerDtoConvert.INSTANCE.toOnLinePlayerDto(player);
          onLinePlayers.add(dto);
        }
        total++;
      }
    }

    Result result = new Result(true);
    result.getMap().put("total", total);
    result.getMap().put("list", onLinePlayers);

    return result;
  }

  /**
   * 在线玩家信息.
   * 
   * @param playerId 玩家Id.
   * @return 在线玩家信息.
   */
  @Path("onlinePlayerInfo")
  @GET
  public Result onLinePlayerInfo(@NotNull @QueryParam("playerId") Long playerId) {

    Player player = playerMgr.getPlayer(playerId);

    Result result = new Result(ErrorCode.INTERNAL_SERVER_ERROR);
    if (player != null) {
      OnLinePlayerDto dto = PlayerDtoConvert.INSTANCE.toOnLinePlayerDto(player);
      result.getMap().put("player", dto);
    }

    return result;
  }

  /**
   * 玩家在线状态.
   * 
   * @param playerIds 玩家id列表.
   * @return 玩家在线列表.
   */
  @Path("playerStatus")
  @GET
  public Result playerStatus(@NotEmpty @QueryParam("ids") List<Long> playerIds) {
    List<PlayerStatusDto> statusDtos = new ArrayList<>();
    for (Iterator<Long> iterator = playerIds.iterator(); iterator.hasNext();) {
      Long playerId = iterator.next();
      Player player = playerMgr.getPlayer(playerId);
      PlayerStatusDto statusDto = new PlayerStatusDto();
      statusDto.setPlayerId(playerId);
      if (player == null) {
        statusDto.setPlayerStatus("离线");
      } else {
        AbstractRoom room = player.curRoom;
        if (room == null) {
          statusDto.setPlayerStatus("游戏大厅");
        } else {
          statusDto.setPlayerStatus(room.game().getDesc() + "-" + room.getName());
        }
      }
      statusDtos.add(statusDto);
    }
    Result result = new Result(true);
    result.getMap().put("status", statusDtos);

    return result;

  }

  /**
   * 修改用户数据.
   * 
   * @param playerId 玩家Id.
   * @param key key.
   * @param value 值.
   * @return 修改信息.
   */
  @Path("/modify")
  @POST
  public Result modifyPlayerData(@NotNull @FormParam("playerId") Long playerId,
      @NotEmpty @FormParam("key") String key, @NotEmpty @FormParam("value") String value) {
    PlayerBo playerBo = playerMgr.selectPlayer(playerId);

    Result result = new Result(ErrorCode.USER_NOT_EXIST);
    if (playerBo != null) {
      switch (key) {
        case "name":
          playerBo.setName(value);
          break;
        case "password":
          playerBo.setPassword(PwdEncode.encodeWithTail(value));
          break;
        case "idcard":
          playerBo.setIdcard(value);
          break;
        case "weixin":
          playerBo.setWeixin(value);
          break;
        case "phone":
          playerBo.setPhone(value);
          break;
        case "email":
          playerBo.setEmail(value);
          break;
        case "bankPwd":
          playerBo.setBankPwd(PwdEncode.encodeWithTail(value));
          break;
        case "playerName":
          playerBo.setPlayerName(value);
          break;
        case "userName":
          playerBo.setUserName(value);
          break;
        default:
          break;
      }
      result.successMg();
      // 立即同步保存一次
      playerMgr.updatePlayer(playerBo);
    }
    return result;
  }

  /**
   * 修改用户靓号和用户类型.
   * 
   * @param playerId 玩家id.
   * @param superId 靓号.
   * @param playerType 玩家类型.
   * @return 结果.
   */
  @Path("/changeUserTypeAndSuperId")
  @POST
  public Result toAgent(@NotNull @FormParam("playerId") Long playerId,
      @NotNull @FormParam("superId") Long superId,
      @NotNull @Min(0) @Max(1) @FormParam("playerType") Byte playerType) {
    Result result = new Result(true);

    boolean out = playerService.existsSuperId(superId);

    if (!out) {
      PlayerBo playerBo = playerMgr.selectPlayer(playerId);
      HuohuaAssert.notNull(playerBo, ErrorCode.USER_NOT_EXIST);

      playerBo.setType(playerType);
      playerBo.setSuperId(superId);

      try {
        // 如果保存数据失败(如：数据库连接断开,网络异常等)则保存数据到日志文件以便恢复
        playerService.updateById(playerBo);

        Player player = playerMgr.getPlayer(playerId);
        if (player != null) {
          playerMgr.changeUserTypeAndSuperId(player, superId, playerType);
        }
      } catch (Exception e) {
        result.changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
      }
    } else {
      result.changeErrorCode(ErrorCode.USER_SUPERID_EXIST);
    }
    return result;
  }


  /**
   * 冻结玩家.
   * 
   * @param playerIds 玩家列表.
   * @return 结果.
   */
  @Path("/lock")
  @POST
  public Result lockPlayer(@NotEmpty List<Long> playerIds) {
    for (Iterator<Long> iterator = playerIds.iterator(); iterator.hasNext();) {
      Long playerId = iterator.next();
      PlayerBo playerDom = blackListMgr.lockPlayer(playerId);
      // 立即更新,后台实时看到结果
      playerMgr.updatePlayer(playerDom);
    }

    return new Result(true);
  }


  /**
   * 解冻玩家.
   * 
   * @param playerIds 玩家列表.
   * @return 结果.
   */
  @Path("/unlock")
  @POST
  public Result unLockPlayer(@NotEmpty List<Long> playerIds) {
    for (Iterator<Long> iterator = playerIds.iterator(); iterator.hasNext();) {
      Long playerId = iterator.next();
      PlayerBo playerDom = blackListMgr.unLockPlayer(playerId);
      // 立即更新,后台实时看到结果
      playerMgr.updatePlayer(playerDom);
    }

    return new Result(true);
  }

  /**
   * 后台增加金币.
   * 
   * @param adminId 管理Id.
   * @param adminName 管理昵称.
   * @param playerId 玩家Id.
   * @param gold 金币.
   * @param type 类型.
   * @return 结果.
   */
  @Path("/addGold")
  @POST
  public Result addGold(@NotNull @FormParam("adminId") Integer adminId,
      @NotEmpty @FormParam("adminName") String adminName,
      @NotNull @Min(1) @FormParam("playerId") Long playerId, @NotNull @FormParam("gold") Long gold,
      @NotNull @FormParam("type") Integer type) {

    IdeaAssert.isTrue(gold != 0);

    String playerName = null;
    long superId = 0;
    if (type == 0) {
      Player player = playerMgr.getPlayer(playerId);

      if (player != null) {
        IdeaAssert.isTrue(gold + player.getGold() >= 0, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
        playerMgr.addGold(player, gold, LogReason.BACKEND_ADD_GOLD);
        playerMgr.updatePlayer(player.playerBo());
        playerName = player.getPlayerName();
        superId = player.getSuperId();
      } else {
        PlayerBo playerBo = playerMgr.selectPlayer(playerId);
        IdeaAssert.isTrue(gold + playerBo.getGold().get() >= 0, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
        playerMgr.addGold(playerBo, gold, LogReason.BACKEND_ADD_GOLD);
        playerName = playerBo.getPlayerName();
        superId = playerBo.getSuperId();
        // 立即更新,后台实时看到结果
        playerMgr.updatePlayer(playerBo);
      }
    } else if (type == 1) {
      Player player = playerMgr.getPlayer(playerId);
      if (player != null) {
        IdeaAssert.isTrue(gold + player.getSafeGold() >= 0, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);

        if (gold > 0) {
          playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
              LogReason.BACKEND_ADD_GOLD);
        } else {
          playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
              LogReason.BACKEND_MINUS_GOLD);
        }


        playerMgr.updatePlayer(player.playerBo());
        playerName = player.getPlayerName();
        superId = player.getSuperId();
      } else {
        PlayerBo playerBo = playerMgr.selectPlayer(playerId);
        IdeaAssert.isTrue(gold + playerBo.getSafeGold().get() >= 0,
            ErrorCode.PLAYER_GOLD_NOT_ENOUGH);

        if (gold > 0) {
          playerMgr.addSafeGold(playerBo, gold, ThirdOrderUtils.generateThirdOrderNo(),
              LogReason.BACKEND_ADD_GOLD);
        } else {
          playerMgr.addSafeGold(playerBo, gold, ThirdOrderUtils.generateThirdOrderNo(),
              LogReason.BACKEND_MINUS_GOLD);
        }

        playerName = playerBo.getPlayerName();
        superId = playerBo.getSuperId();
        // 立即更新,后台实时看到结果
        playerMgr.updatePlayer(playerBo);
      }
    }

    DbLogService
        .log(new BackendAddGoldLog(adminId, adminName, playerId, superId, playerName, gold));
    return new Result(true);
  }
}
