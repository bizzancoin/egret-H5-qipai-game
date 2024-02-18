package com.idealighter.game.app.robotcfg;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.app.robotcfg.convert.RobotCfgListDtoConvert;
import com.idealighter.game.app.robotcfg.convert.RobotLeftDtoConvert;
import com.idealighter.game.app.robotcfg.dto.RobotCfgListDto;
import com.idealighter.game.app.robotcfg.dto.RobotLeftDto;
import com.idealighter.game.app.robotcfg.dto.RoomConfigDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.robotcfg.IRobotCfgService;
import com.idealighter.game.service.robotcfg.bo.RobotCfgListBo;
import com.idealighter.game.service.robotcfg.bo.RobotLeftBo;
import com.idealighter.game.service.robotcfg.bo.RoomConfigBo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import okhttp3.internal.http2.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/robot/cfg")
public class RobotCfgController extends BaseController {
  @Autowired
  private IRobotCfgService robotCfgService;
  @Autowired
  private RobotCfgListDtoConvert robotCfgListDtoConvert;
  @Autowired
  private RobotLeftDtoConvert robotLeftDtoConvert;

  /**
   * 分页获取控制信息.
   * 
   * @Title list.
   * @author houdongsheng
   * @date 2018年3月21日 下午4:19:01
   * @param gameId 游戏编号，不限可不传
   * @param roomId 房间编号，不限可不传
   * @param page 页码
   * @param pageSize 每页大小
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0101')")
  @GetMapping("/list")
  public Result list(@RequestParam(name = "gameId", required = false) Integer gameId,
      @RequestParam(name = "roomId", required = false) Integer roomId,
      @RequestParam(name = "page") Integer page,
      @RequestParam(name = "pageSize") Integer pageSize) {

    Result result = new Result(true);
    ResultPage<RobotCfgListBo> bo = robotCfgService.robotCfgList(gameId, roomId, page, pageSize);
    result.getMap().put("list", robotCfgListDtoConvert.bo2dto(bo.getList()));
    result.getMap().put("total", bo.getTotal());
    return result;
  }

  /**
   * 获取未配置机器人的房间.
   * 
   * @Title noCfgRobotRoomList.
   * @author houdongsheng
   * @date 2018年3月21日 下午8:07:45
   * @param gameId 游戏编号
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0101')")
  @GetMapping("/noCfgRobotRoomList")
  public Result noCfgRobotRoomList(@NotNull @RequestParam(name = "gameId") Integer gameId) {
    Result result = new Result(true);
    List<IdName> idNames = robotCfgService.noCfgRobotRoomList(gameId);
    result.getMap().put("list", idNames);
    return result;
  }
  
  /**
   * 获取所有房间简单配置.
   *
   * @author abin
   * @date 2018年7月28日 上午11:12:17
   * @param gameId 游戏id.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('F0101')")
  @GetMapping("/cfgRobotRoomList")
  public Result cfgRobotRoomList(@NotNull @RequestParam(name = "gameId") Integer gameId) {
    Result result = new Result(true);
    List<RoomConfigBo> roomConfigBos = robotCfgService.cfgRobotRoomList(gameId);
    List<RoomConfigDto> dtos = robotLeftDtoConvert.toRoomConfigDtos(roomConfigBos);
    result.getMap().put("list", dtos);
    return result;
  }


  /**
   * 获取可填写机器人数量.
   * 
   * @Title robotLeftList.
   * @author houdongsheng
   * @date 2018年3月21日 下午8:21:08
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0101')")
  @GetMapping("/robotLeftList")
  public Result robotLeftList(@NotNull @RequestParam(name = "gameId") Integer gameId,
      @NotNull @RequestParam(name = "roomId") Integer roomId) {
    Result result = new Result(true);
    RobotLeftBo bo = robotCfgService.robotLeft(gameId, roomId);
    RobotLeftDto resultDto = robotLeftDtoConvert.bo2dto(bo);
    result.getMap().put("result", resultDto);
    return result;
  }

  /**
   * 通过编号获取配置信息.
   * 
   * @Title findById.
   * @author houdongsheng
   * @date 2018年3月21日 下午4:35:47
   * @param id 配置编号
   * @return Result
   */
  @GetMapping("/findById")
  public Result findById(@RequestParam(name = "id") Integer id) {
    Result result = new Result(true);
    RobotCfgListBo bo = robotCfgService.findById(id);
    result.getMap().put("result", robotCfgListDtoConvert.bo2dto(bo));
    return result;
  }

  /**
   * 增加配置.
   * 
   * @Title add.
   * @author houdongsheng
   * @date 2018年3月21日 下午9:00:27
   * @param cfg 配置内容
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0102')")
  @PostMapping("/add")
  public Result add(@NotNull @Valid @RequestBody RobotCfgListDto cfg) {
    Result result = new Result(true);
    cfg.setId(null);
    Integer sign = robotCfgService.modify(robotCfgListDtoConvert.dto2bo(cfg));
    result.changeReturnCode(sign);
    return result;
  }

  /**
   * 修改配置.
   * 
   * @Title modify.
   * @author houdongsheng
   * @date 2018年3月21日 下午9:00:27
   * @param cfg 配置内容
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0102')")
  @PostMapping("/modify")
  public Result modify(@NotNull @Valid @RequestBody RobotCfgListDto cfg) {
    Result result = new Result(true);
    Integer sign = null;
    if (cfg.getId() != null && cfg.getId() > 0) {
      sign = robotCfgService.modify(robotCfgListDtoConvert.dto2bo(cfg));
    } else {
      sign = ErrorCode.INTERNAL_ERROR.httpCode;
    }
    result.changeReturnCode(sign);
    return result;
  }

  /**
   * 删除配置.
   * 
   * @Title delete.
   * @author houdongsheng
   * @date 2018年3月21日 下午8:51:48
   * @param id 配置编号
   * @return Result
   */
  @PreAuthorize("hasAuthority('F0103')")
  @PostMapping("/delete")
  public Result delete(@NotNull Integer id) {
    Result result = new Result(true);
    int sign = robotCfgService.delete(id);
    result.changeReturnCode(sign);
    // RobotCfgListBo bo = robotCfgService.findById(id);
    // result.getMap().put("result", bo);
    return result;
  }

  /**
   * 修改机器人配置.
   *
   * @author abin
   * @date 2018年5月5日 下午7:26:04
   * @param id id.
   * @param state 状态.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('F0104')")
  @PostMapping("/changeCfgStatus")
  public Result changeState(@NotNull @RequestParam("id") Integer id,
      @NotNull @Min(0) @RequestParam("state") Byte state) {
    Result result = new Result(true);

    robotCfgService.changeState(id, state);

    return result;
  }
}
