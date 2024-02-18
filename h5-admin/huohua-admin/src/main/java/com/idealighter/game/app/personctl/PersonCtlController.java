package com.idealighter.game.app.personctl;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.personctl.convert.PlayerCtrlBasicInfoDtoConvert;
import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.service.personctl.IPersonCtlService;
import com.idealighter.game.service.personctl.bo.PlayerCtrlDetailInfoBo;
import com.idealighter.game.service.personctl.bo.PlayerCtrlPlayerListBo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playerCtl")
public class PersonCtlController extends BaseController {
  @Autowired
  private PlayerCtrlBasicInfoDtoConvert playerCtrlItemDtoConvert;
  @Autowired
  private IPersonCtlService personCtlService;

  /**
   * findUserCtrlListInRoom.
   * 
   * @Description 获取房间中的玩家控制
   * @author houdongsheng
   * @date 2018年1月26日 下午2:53:53
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param page 页码
   * @param pageSize 每页大小
   * @return Result
   */
  @PreAuthorize("hasAuthority('C0101')")
  @PostMapping("/findUserCtrlListInRoom")
  public Result findUserCtrlListInRoom(@NotNull @RequestParam("gameId") Integer gameId,
      @RequestParam(name = "roomId", required = false) Integer roomId,
      @Min(1) @RequestParam("page") Integer page,
      @Min(1) @RequestParam("pageSize") Integer pageSize) {
    PlayerCtrlPlayerListBo resultBo =
        personCtlService.findPlayerCtrlList(gameId, roomId, page, pageSize);
    Result result = new Result();
    result.getMap().put("total", resultBo.getTotal());
    result.getMap().put("list", playerCtrlItemDtoConvert.bo2dto(resultBo.getList()));
    return result;
  }

  /**
   * findPlayerCtlInfo.
   * 
   * @Description 获取玩家控制信息
   * @author houdongsheng
   * @date 2018年1月26日 下午7:35:16
   * @param playerId 玩家编号
   * @param nickName 玩家昵称
   * @return Result
   */
  @PreAuthorize("hasAuthority('C0101')")
  @PostMapping("/findPlayerCtlInfo")
  public Result findPlayerCtlInfo(@RequestParam(value = "playerId", required = false) Long playerId,
      @RequestParam(value = "nickName", required = false) String nickName) {
    Result result = new Result(ErrorCode.BAD_REQUEST);
    PlayerCtrlDetailInfoBo resultBo = personCtlService.findPlayerCtrlDetailInfo(playerId, nickName);
    result.changeErrorCode(ErrorCode.OK);
    result.getMap().put("result", resultBo);
    return result;
  }

  /**
   * addPlayerCtl.
   * 
   * @Description 增加玩家控制
   * @author houdongsheng
   * @date 2018年1月27日 下午2:06:47
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param prizePools 奖金池奖金
   * @param control 控制信息
   * @return Result
   */
  @PreAuthorize("hasAuthority('C0102')")
  @PostMapping("/addPlayerCtl")
  public String addPlayerCtl(@Min(0) @RequestParam(value = "gameId") Integer gameId,
      @NotNull @RequestParam(value = "playerId") Long playerId,
      @NotNull @RequestParam(value = "prizePools") Long prizePools,
      @NotNull @RequestParam(value = "control") String control) {
    return personCtlService.addPlayerCtl(gameId, playerId, prizePools, control);
  }

  /**
   * pdatePrizePoolGold.
   * 
   * @Description 修改玩家控制奖池金币
   * @author houdongsheng
   * @date 2018年1月27日 下午2:06:47
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param prizePools 奖金池奖金
   * @return Result
   */
  @PreAuthorize("hasAuthority('C0102')")
  @PostMapping("/updatePrizePoolGold")
  public String updatePrizePoolGold(@Min(0) @RequestParam(value = "gameId") Integer gameId,
      @NotNull @RequestParam(value = "playerId") Long playerId,
      @NotNull @RequestParam(value = "prizePools") Long prizePools) {
    return personCtlService.updatePrizePoolGold(gameId, playerId, prizePools);
  }

  /**
   * pdateCtl.
   * 
   * @Description 修改玩家控制奖池控制
   * @author houdongsheng
   * @date 2018年1月27日 下午2:06:47
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param control 玩家控制
   * @return Result
   */
  @PreAuthorize("hasAuthority('C0102')")
  @PostMapping("/updateCtl")
  public String updateCtl(@Min(0) @RequestParam(value = "gameId") Integer gameId,
      @NotNull @RequestParam(value = "playerId") Long playerId,
      @NotNull @RequestParam(value = "control") String control) {
    return personCtlService.updateCtl(gameId, playerId, control);
  }
}
