package com.idealighter.game.admin.roomctl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.admin.roomctl.convert.DefaultPrizePoolDtoConvert;
import com.idealighter.game.admin.roomctl.convert.GoldRangePrizePoolDtoConvert;
import com.idealighter.game.admin.roomctl.convert.PrizePoolGroupDtoConvert;
import com.idealighter.game.admin.roomctl.dto.DefaultPrizePoolDto;
import com.idealighter.game.admin.roomctl.dto.GoldRangePrizePoolDto;
import com.idealighter.game.admin.roomctl.dto.PrizePoolGroupDto;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.prizepool.manager.RoomPrizePoolMgrScript;
import com.idealighter.game.core.service.prizepool.struct.room.GoldRangeRoomPrizePool;
import com.idealighter.game.core.service.prizepool.struct.room.RoomPrizePoolGroup;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/roomCtl")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class RoomCtlController {
  @Inject
  private RoomPrizePoolMgrScript roomPrizePoolMgr;

  /**
   * 获取房间奖池信息.
   * 
   * @Title findCtlInfo.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:09:39
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return Result
   */
  @Path("/findCtlInfo")
  @POST
  public Result findCtlInfo(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId) {
    Game game = Game.getGame(gameId);
    RoomPrizePoolGroup group = roomPrizePoolMgr.getRoomPrizePoolGroup(game, roomId);
    PrizePoolGroupDto resultDto = PrizePoolGroupDtoConvert.INSTANCE.toPrizePoolGroupDto(group);

    Result result = new Result(true);
    result.getMap().put("result", resultDto);
    return result;
  }

  /**
   * 获取房间的默认奖池信息.
   * 
   * @Title findDefaultPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:09:13
   * @param gameId 游戏id
   * @param roomId 房间Id
   * @return Result
   */
  @Path("/findDefaultPrizePool")
  @POST
  public Result findDefaultPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId) {
    Game game = Game.getGame(gameId);
    DefaultPrizePoolDto resultDto = null;
    RoomPrizePoolGroup group = roomPrizePoolMgr.getRoomPrizePoolGroup(game, roomId);
    if (group != null) {
      resultDto = DefaultPrizePoolDtoConvert.INSTANCE
          .roomPrizePoolTodefaultPrizePoolDto(group.getDefaultPrizePool());
    }
    Result result = new Result(true);
    result.getMap().put("result", resultDto);
    return result;
  }

  /**
   * 获取金币奖池信息.
   * 
   * @Title findGoldPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:13:51
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @return Result
   */
  @Path("/findGoldPrizePool")
  @POST
  public Result findGoldPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId) {
    Game game = Game.getGame(gameId);
    GoldRangePrizePoolDto resultDto = null;
    RoomPrizePoolGroup group = roomPrizePoolMgr.getRoomPrizePoolGroup(game, roomId);
    if (group != null) {
      List<GoldRangeRoomPrizePool> list = group.getGoldRangePrizePools();
      GoldRangeRoomPrizePool prizePool = null;
      if (EmptyUtil.listIsNotEmpty(list)) {
        for (GoldRangeRoomPrizePool item : list) {
          if (item.getId() == prizePoolId) {
            prizePool = item;
            break;
          }
        }
      }
      resultDto = GoldRangePrizePoolDtoConvert.INSTANCE.toGoldRangePrizePoolDto(prizePool);
    }
    Result result = new Result(true);
    result.getMap().put("result", resultDto);
    return result;
  }

  /**
   * 增加默认奖池.
   * 
   * @Title addDefaultPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:25:22
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGolds 奖池金币
   * @return Result
   */
  @Path("/addDefaultPrizePool")
  @POST
  public Result addDefaultPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePools") Long prizePoolGolds) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addDefaultRoomPrizePool(game, roomId, prizePoolGolds);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加金币奖池.
   * 
   * @Title addGoldPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:35:25
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGolds 奖池金额
   * @param lower 玩家金币下限
   * @param upper 玩家金币上限
   * @return Result
   */
  @Path("/addGoldPrizePool")
  @POST
  public Result addGoldPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePools") Long prizePoolGolds,
      @FormParam("lower") Long lower, @FormParam("upper") Long upper) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addGoldRangeRoomPrizePool(game, roomId, prizePoolGolds, lower, upper);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加默认奖池金币.
   * 
   * @Title addDefaultPrizePoolGold.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:50:49
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param gold 金币数量
   * @return Result
   */
  @Path("/addDefaultPrizePoolGold")
  @POST
  public Result addDefaultPrizePoolGold(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("gold") Long gold) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addDefaultPrizePoolGold(game, roomId, gold);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加金币奖池金币.
   * 
   * @Title addGoldPrizePoolGold.
   * @Description
   * @author houdongsheng
   * @date 2018年1月28日 上午10:57:38
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param gold 新增金币数量
   * @return Result
   */
  @Path("/addGoldPrizePoolGold")
  @POST
  public Result addGoldPrizePoolGold(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("gold") Long gold) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addGoldRangePrizePoolGold(game, roomId, prizePoolId, gold);
    return new Result(ErrorCode.OK);
  }


  /**
   * 修改默认奖池.
   * 
   * @Title modifyDefaultPrizePoolGold.
   * @author houdongsheng
   * @date 2018年1月28日 上午10:48:01
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param gold 金币
   * @return Result
   */
  @Path("/modifyDefaultPrizePoolGold")
  @POST
  public Result modifyDefaultPrizePoolGold(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("gold") Long gold) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.modifyDefaultPrizePoolGold(game, roomId, gold);
    return new Result(ErrorCode.OK);
  }

  /**
   * 修改金币奖池金币.
   * 
   * @Title modifyGoldPrizePoolGold.
   * @author houdongsheng
   * @date 2018年1月28日 上午11:27:35
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param gold 金币数量
   * @return Result
   */
  @Path("/modifyGoldPrizePoolGold")
  @POST
  public Result modifyGoldPrizePoolGold(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("gold") Long gold) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.modifyGoldRangePrizePoolGold(game, roomId, prizePoolId, gold);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加默认奖池策略.
   * 
   * @Title addDefaultPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 上午11:33:14
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategy 策略
   * @return Result
   */
  @Path("/addDefaultPrizePoolStrategy")
  @POST
  public Result addDefaultPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("strategy") String strategy) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addDefaultRoomPrizePoolStrategy(game, roomId, strategy);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加金币奖池策略.
   * 
   * @Title addGoldPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 上午11:53:28
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param strategy 策略
   * @return Result
   */
  @Path("/addGoldPrizePoolStrategy")
  @POST
  public Result addGoldPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("strategy") String strategy) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.addGoldRangeRoomPrizePoolStrategy(game, roomId, prizePoolId, strategy);
    return new Result(ErrorCode.OK);
  }


  /**
   * 增加默认奖池策略.
   * 
   * @Title addDefaultPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 上午11:33:14
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategy 策略
   * @return Result
   */
  @Path("/modifyDefaultPrizePoolStrategy")
  @POST
  public Result modifyDefaultPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("strategyId") Long strategyId,
      @FormParam("strategy") String strategy) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.modifyDefaultRoomPrizePoolStrategy(game, roomId, strategyId, strategy);
    return new Result(ErrorCode.OK);
  }

  /**
   * 增加金币奖池策略.
   * 
   * @Title addGoldPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 上午11:53:28
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param strategy 策略
   * @return Result
   */
  @Path("/modifyGoldPrizePoolStrategy")
  @POST
  public Result modifyGoldPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("strategyId") Long strategyId, @FormParam("strategy") String strategy) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.modifyGoldRangeRoomPrizePoolStrategy(game, roomId, prizePoolId, strategyId,
        strategy);
    return new Result(ErrorCode.OK);
  }

  /**
   * 删除默认奖池策略.
   * 
   * @Title delDefaultPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 下午2:24:51
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategyId 策略编号
   * @return Result
   */
  @Path("/delDefaultPrizePoolStrategy")
  @POST
  public Result delDefaultPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("strategyId") Long strategyId) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.delDefaultRoomPrizePoolStrategy(game, roomId, strategyId);
    return new Result(ErrorCode.OK);
  }

  /**
   * 删除金币奖池策略.
   * 
   * @Title delGoldPrizePoolStrategy.
   * @author houdongsheng
   * @date 2018年1月28日 下午2:25:16
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖金池编号
   * @param strategyId 策略编号
   * @return Result
   */
  @Path("/delGoldPrizePoolStrategy")
  @POST
  public Result delGoldPrizePoolStrategy(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("strategyId") Long strategyId) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.delGoldRangeRoomPrizePoolStrategy(game, roomId, prizePoolId, strategyId);
    return new Result(ErrorCode.OK);
  }

  /**
   * 删除默认奖池.
   * 
   * @Title delDefaultPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 下午2:36:27
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return Result
   */
  @Path("/delDefaultPrizePool")
  @POST
  public Result delDefaultPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.delDefaultRoomPrizePool(game, roomId);
    return new Result(ErrorCode.OK);
  }

  /**
   * 删除金币奖池.
   * 
   * @Title delGoldPrizePool.
   * @author houdongsheng
   * @date 2018年1月28日 下午2:37:02
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @return Result
   */
  @Path("/delGoldPrizePool")
  @POST
  public Result delGoldPrizePool(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.delGoldRangeRoomPrizePool(game, roomId, prizePoolId);;
    return new Result(ErrorCode.OK);
  }

  /**
   * 修改金币奖池范围.
   * 
   * @Title modifyGoldPrizePoolRange.
   * @author houdongsheng
   * @date 2018年1月28日 下午2:42:12
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param lower 玩家金币下限
   * @param upper 玩家金币上限
   * @return Result
   */
  @Path("/modifyGoldPrizePoolRange")
  @POST
  public Result modifyGoldPrizePoolRange(@FormParam("gameId") Integer gameId,
      @FormParam("roomId") Integer roomId, @FormParam("prizePoolId") Long prizePoolId,
      @FormParam("lower") Long lower, @FormParam("upper") Long upper) {
    Game game = Game.getGame(gameId);
    roomPrizePoolMgr.modifyPrizePoolRange(game, roomId, prizePoolId, lower, upper);
    return new Result(ErrorCode.OK);
  }
}
