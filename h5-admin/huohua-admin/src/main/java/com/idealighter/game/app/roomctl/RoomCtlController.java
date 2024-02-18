package com.idealighter.game.app.roomctl;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.roomctl.dto.ModifyPrizePoolDto;
import com.idealighter.game.service.roomctl.IRoomCtlService;
import com.idealighter.game.service.roomctl.bo.StrategyBo;
import com.idealighter.game.service.roomctl.convert.StrategyBoConvert;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roomCtl")
public class RoomCtlController extends BaseController {
  @Autowired
  private IRoomCtlService roomCtlService;
  @Autowired
  private StrategyBoConvert strategyBoConvert;

  /**
   * findCtlInfo.
   * 
   * @Description 获取房间控制信息
   * @author houdongsheng
   * @date 2018年1月26日 下午2:53:53
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return Result
   */
  @PreAuthorize("hasAuthority('D0101')")
  @PostMapping("/findCtlInfo")
  public String findCtlInfo(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId) {
    return roomCtlService.findCtlInfo(gameId, roomId);
  }

  /**
   * addDefaultPrizePool.
   * 
   * @Description 增加默认奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:00:17
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGold 预设奖金
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addDefaultPrizePool")
  public String addDefaultPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePools") Long prizePoolGold) {
    return roomCtlService.addDefaultPrizePool(gameId, roomId, prizePoolGold);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addDefaultPrizePoolGold")
  public String addDefaultPrizePoolGold(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "gold") Long gold) {
    return roomCtlService.addDefaultPrizePoolGold(gameId, roomId, gold);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/modifyDefaultPrizePoolGold")
  public String modifyDefaultPrizePoolGold(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "gold") Long gold) {
    return roomCtlService.modifyDefaultPrizePoolGold(gameId, roomId, gold);
  }

  /**
   * addDefaultPrizePoolStrategy.
   * 
   * @Description 增加默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午9:11:52
   * @param prizePool 策略信息
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addDefaultPrizePoolStrategy")
  public String addDefaultPrizePoolStrategy(@NotNull @RequestBody ModifyPrizePoolDto prizePool) {
    Integer gameId = prizePool.getGameId();
    Integer roomId = prizePool.getRoomId();
    StrategyBo strategy = strategyBoConvert.dto2bo(prizePool.getStrategy());
    return roomCtlService.addDefaultPrizePoolStrategy(gameId, roomId, strategy);
  }

  /**
   * modifyDefaultPrizePoolStrategy.
   * 
   * @Description 修改默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午9:12:15
   * @param prizePool 修改信息
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/modifyDefaultPrizePoolStrategy")
  public String modifyDefaultPrizePoolStrategy(@NotNull @RequestBody ModifyPrizePoolDto prizePool) {
    Integer gameId = prizePool.getGameId();
    Integer roomId = prizePool.getRoomId();
    StrategyBo strategy = strategyBoConvert.dto2bo(prizePool.getStrategy());
    return roomCtlService.modifyDefaultPrizePoolStrategy(gameId, roomId, strategy);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/delDefaultPrizePoolStrategy")
  public String delDefaultPrizePoolStrategy(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "strategyId") Long strategyId) {
    return roomCtlService.delDefaultPrizePoolStrategy(gameId, roomId, strategyId);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/delDefaultPrizePool")
  public String delDefaultPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId) {
    return roomCtlService.delDefaultPrizePool(gameId, roomId);
  }


  // ====
  /**
   * addGoldPrizePool.
   * 
   * @Description 增加金币奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:00:17
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGold 预设奖金
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addGoldPrizePool")
  public String addGoldPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @RequestParam(name = "prizePools") Long prizePoolGold,
      @NotNull @RequestParam(name = "lower") Long lower,
      @NotNull @RequestParam(name = "upper") Long upper) {
    return roomCtlService.addGoldPrizePool(gameId, roomId, prizePoolGold, lower, upper);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/delGoldPrizePool")
  public String delGoldPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId) {
    return roomCtlService.delGoldPrizePool(gameId, roomId, prizePoolId);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addGoldPrizePoolGold")
  public String addGoldPrizePoolGold(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId,
      @NotNull @RequestParam(name = "gold") Long gold) {
    return roomCtlService.addGoldPrizePoolGold(gameId, roomId, prizePoolId, gold);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/modifyGoldPrizePoolGold")
  public String modifyGoldPrizePoolGold(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId,
      @NotNull @RequestParam(name = "gold") Long gold) {
    return roomCtlService.modifyGoldPrizePoolGold(gameId, roomId, prizePoolId, gold);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/modifyGoldPrizePoolRange")
  public String modifyGoldPrizePoolRange(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId,
      @NotNull @RequestParam(name = "lower") Long lower,
      @NotNull @RequestParam(name = "upper") Long upper) {
    return roomCtlService.modifyGoldPrizePoolRange(gameId, roomId, prizePoolId, lower, upper);
  }

  /**
   * addGoldPrizePoolStrategy.
   * 
   * @Description 增加金币奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午9:10:19
   * @param prizePool 策略
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/addGoldPrizePoolStrategy")
  public String addGoldPrizePoolStrategy(@NotNull @RequestBody ModifyPrizePoolDto prizePool) {
    Integer gameId = prizePool.getGameId();
    Integer roomId = prizePool.getRoomId();
    Long prizePoolId = prizePool.getPrizePoolId();
    StrategyBo strategy = strategyBoConvert.dto2bo(prizePool.getStrategy());
    return roomCtlService.addGoldPrizePoolStrategy(gameId, roomId, prizePoolId, strategy);
  }

  /**
   * modifyGoldPrizePoolStrategy.
   * 
   * @Description 修改金币奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午9:10:50
   * @param prizePool 奖池信息
   * @return String
   */
  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/modifyGoldPrizePoolStrategy")
  public String modifyGoldPrizePoolStrategy(@NotNull @RequestBody ModifyPrizePoolDto prizePool) {
    Integer gameId = prizePool.getGameId();
    Integer roomId = prizePool.getRoomId();
    Long prizePoolId = prizePool.getPrizePoolId();
    StrategyBo strategy = strategyBoConvert.dto2bo(prizePool.getStrategy());
    return roomCtlService.modifyGoldPrizePoolStrategy(gameId, roomId, prizePoolId, strategy);
  }

  @PreAuthorize("hasAuthority('D0102')")
  @PostMapping("/delGoldPrizePoolStrategy")
  public String delGoldPrizePoolStrategy(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId,
      @NotNull @RequestParam(name = "strategyId") Long strategyId) {
    return roomCtlService.delGoldPrizePoolStrategy(gameId, roomId, prizePoolId, strategyId);
  }

  @PreAuthorize("hasAuthority('D0101')")
  @PostMapping("/findDefaultPrizePool")
  public String findDefaultPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId) {
    return roomCtlService.findDefaultPrizePool(gameId, roomId);
  }

  @PreAuthorize("hasAuthority('D0101')")
  @PostMapping("/findGoldPrizePool")
  public String findGoldPrizePool(@NotNull @RequestParam("gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId,
      @NotNull @RequestParam(name = "prizePoolId") Long prizePoolId) {
    return roomCtlService.findGoldPrizePool(gameId, roomId, prizePoolId);
  }
}
