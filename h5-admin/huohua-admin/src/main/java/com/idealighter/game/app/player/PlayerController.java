package com.idealighter.game.app.player;

import com.idealighter.game.app.base.AdminSessionInfo;
import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.player.convert.PlayerDtoConvert;
import com.idealighter.game.app.player.dto.PlayerDto;
import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.common.constant.PlayerType;
import com.idealighter.game.common.util.IpUtils;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.gold.IGoldService;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.player.IPlayerService;
import com.idealighter.game.service.player.bo.PlayerBo;
import com.idealighter.game.service.player.bo.PlayerListBo;
import com.idealighter.utils.check.CheckUtil;
import com.idealighter.utils.json.JsonUtil;
import com.idealighter.utils.time.TimeUtil;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/user")
@Slf4j
public class PlayerController extends BaseController {

  @Autowired
  private IPlayerService playerService;

  @Autowired
  private PlayerDtoConvert userDtoConvert;

  @Autowired
  private IGoldService goldService;

  /**
   * 用户列表 .
   * 
   * @param playerId 玩家id.
   * @param nickname 昵称.
   * @param phone 手机.
   * @param page 页.
   * @param pageSize 页大小.
   * @return 用户列表.
   */
  @PreAuthorize("hasAuthority('B0101')")
  @GetMapping("/list")
  public Result list(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "playerType", required = false) Byte playerType,
      @RequestParam(name = "nickName", required = false) String nickname,
      @RequestParam(name = "userName", required = false) String userName,
      @RequestParam(name = "phone", required = false) String phone,
      @RequestParam(name = "channelId", required = false) String channelId,
      @RequestParam(name = "locked", required = false) Boolean locked,
      @RequestParam(name = "page") Integer page,
      @RequestParam(name = "pageSize") Integer pageSize) {

    Result result = new Result(true);

    ResultPage<PlayerListBo> bo = playerService.selectByPage(playerId, superId, playerType,
        nickname, userName, phone, channelId, locked, page, pageSize);

    List<PlayerDto> userDtos = userDtoConvert.toUserDtos(bo.getList());

    result.getMap().put("list", userDtos);

    result.getMap().put("total", bo.getTotal());

    return result;
  }

  /**
   * 在线用户列表.
   * 
   * @param playerId 玩家Id.
   * @param nickname 昵称.
   * @param gameId 游戏id.
   * @param roomId 房间id.
   * @param page 页大小.
   * @param pageSize 页大小.
   * @return 在线玩家.
   */
  @PreAuthorize("hasAuthority('B0201')")
  @GetMapping("/onlineList")
  public String onlineList(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "nickName", required = false) String nickname,
      @RequestParam(name = "userName", required = false) String userName,
      @RequestParam(name = "gameId", required = false) Integer gameId,
      @RequestParam(name = "roomId", required = false) Integer roomId,
      @RequestParam(name = "channelId", required = false) String channelId,
      @RequestParam(name = "page") Integer page,
      @RequestParam(name = "pageSize") Integer pageSize) {

    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("superId", superId);
    paramBuild.add("roomId", roomId);
    paramBuild.add("playerId", playerId);
    paramBuild.add("nickname", nickname);
    paramBuild.add("page", page);
    paramBuild.add("pageSize", pageSize);
    paramBuild.add("channelId", channelId);
    paramBuild.add("userName", userName);

    String result = GameServerRequest.get(GameServerUrl.PLAYER_ONLINE_USER, paramBuild);
    return result;
  }

  /**
   * 地址详情 .
   *
   * @author abin
   * @date 2018年5月28日 下午12:48:34
   * @param ip ip.
   * @return 地址.
   */
  @GetMapping("/ipToLocation")
  public Result locationDetail(@RequestParam(name = "ip") String ip) {
    Result result = new Result(true);

    String location = IpUtils.ipToLocation(ip);

    result.getMap().put("location", location);

    return result;
  }

  /**
   * 赠送身上金币.
   * 
   * @param playerId 玩家id.
   * @param gold 金币.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('B0102')")
  @PostMapping("/sendGold")
  public String sendGold(@NotNull @RequestParam("playerId") Long playerId,
      @NotNull @RequestParam("gold") Long gold) {

    if (gold == 0) {
      Result result = new Result(false);
      result.setMsg("金币不能为0");
      return JsonUtil.toJson(result);
    }

    PlayerBo playerBo = playerService.findById(playerId);

    IdeaAssert.notNull(playerBo);

    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("adminId", getAdminId());
    paramBuild.add("adminName", getAdminName());
    paramBuild.add("playerId", playerId);
    paramBuild.add("gold", gold);
    paramBuild.add("type", 0);

    String result = GameServerRequest.post(GameServerUrl.PLAYER_ADD_GOLD, paramBuild);

    Result httpResult = JsonUtil.fromJson(result, Result.class);

    if (httpResult != null && httpResult.getCode() == ErrorCode.OK.getCode()) {
      SendGoldRecordBo bo = new SendGoldRecordBo();
      AdminSessionInfo info = getAdminSessionInfo();
      bo.setAdminId(info.getAdminId());
      bo.setAdminName(info.getNickname());
      bo.setGold(gold);
      bo.setPlayerId(playerId);
      bo.setPlayerName(playerBo.getPlayerName());
      bo.setSafeGold(0L);
      bo.setSuperId(playerBo.getSuperId());
      bo.setTime(TimeUtil.now());
      boolean out = goldService.addSendGoldRecord(bo);

      if (!out) {
        log.error("存储赠送记录失败，存档以备手动更新 superId = {}  gold = {} safeGold = {} 操作人  {}[{}]",
            bo.getSuperId(), bo.getGold(), bo.getSafeGold(), bo.getAdminId(), bo.getAdminName());
      }
    }

    return result;
  }


  /**
   * 赠送保险箱金币.
   * 
   * @param playerId 玩家id.
   * @param gold 金币.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('B0102')")
  @PostMapping("/sendSafeGold")
  public String sendSafeGold(@NotNull @RequestParam("playerId") Long playerId,
      @NotNull @RequestParam("gold") Long gold) {

    PlayerBo playerBo = playerService.findById(playerId);

    IdeaAssert.notNull(playerBo);

    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("adminId", getAdminId());
    paramBuild.add("adminName", getAdminName());
    paramBuild.add("playerId", playerId);
    paramBuild.add("gold", gold);
    paramBuild.add("type", 1);


    String result = GameServerRequest.post(GameServerUrl.PLAYER_ADD_GOLD, paramBuild);

    Result httpResult = JsonUtil.fromJson(result, Result.class);

    if (httpResult != null && httpResult.getCode() == ErrorCode.OK.getCode()) {
      SendGoldRecordBo bo = new SendGoldRecordBo();
      AdminSessionInfo info = getAdminSessionInfo();
      bo.setAdminId(info.getAdminId());
      bo.setAdminName(info.getNickname());
      bo.setGold(0L);
      bo.setPlayerId(playerId);
      bo.setPlayerName(playerBo.getPlayerName());
      bo.setSafeGold(gold);
      bo.setSuperId(playerBo.getSuperId());
      bo.setTime(TimeUtil.now());
      bo.setChannelId(playerBo.getChannelId());

      boolean out = goldService.addSendGoldRecord(bo);

      if (!out) {
        log.error("存储赠送记录失败，存档以备手动更新 superId = {}  gold = {} safeGold = {} 操作人  {}[{}]",
            bo.getSuperId(), bo.getGold(), bo.getSafeGold(), bo.getAdminId(), bo.getAdminName());
      }
    }

    return result;
  }

  /**
   * 批量冻结.
   * 
   * @param playerIds 玩家id列表.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('B0103')")
  @PostMapping("/batchLock")
  public String lockPlayer(@NotEmpty @RequestBody List<Long> playerIds) {
    String result =
        GameServerRequest.postJson(GameServerUrl.PLAYER_LOCK_PLAYER, JsonUtil.toJson(playerIds));
    return result;
  }


  /**
   * 激活玩家.
   * 
   * @param playerIds 玩家id 列表.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('B0103')")
  @PostMapping("/batchActive")
  public String activePlayer(@NotEmpty @RequestBody List<Long> playerIds) {
    String result =
        GameServerRequest.postJson(GameServerUrl.PLAYER_UNLOCK_PLAYER, JsonUtil.toJson(playerIds));
    return result;
  }

  /**
   * 设置靓号.
   * 
   * @param playerId 玩家id.
   * @param superId 靓号id
   * @return 结果.
   */
  @PostMapping("/setSuperId")
  public String setSuperId(@NotNull @RequestParam("playerId") Long playerId,
      @NotNull @RequestParam("superId") Long superId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("superId", superId);
    paramBuild.add("playerType", PlayerType.AGENT);

    String result =
        GameServerRequest.post(GameServerUrl.PLAYER_CHANGE_USERTYPE_SUPERID, paramBuild);
    return result;
  }

  /**
   * 取消设置靓号与代理.
   * 
   * @param playerId 玩家id.
   * @return 结果.
   */
  @PostMapping("/undoSuperId")
  public String undoSuperId(@NotNull @RequestParam("playerId") Long playerId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("superId", playerId);
    paramBuild.add("playerType", PlayerType.USER);

    String result =
        GameServerRequest.post(GameServerUrl.PLAYER_CHANGE_USERTYPE_SUPERID, paramBuild);
    return result;
  }

  /**
   * 修改手机.
   * 
   * @param playerId 用户id.
   * @param phone 用户手机.
   * @return 结果.
   */
  @PostMapping("/changePhone")
  public String changePhone(@NotNull @RequestParam("playerId") Long playerId,
      @NotNull @RequestParam("phone") String phone) {

    IdeaAssert.isTrue(CheckUtil.checkTelephone(phone));

    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("playerId", playerId);
    paramBuild.add("key", "phone");
    paramBuild.add("value", phone);

    String result = GameServerRequest.post(GameServerUrl.PLAYER_MODIFY_INFO, paramBuild);
    return result;
  }

}
