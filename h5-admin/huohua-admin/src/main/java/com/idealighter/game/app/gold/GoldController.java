package com.idealighter.game.app.gold;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.gold.convert.GoldDtoConvert;
import com.idealighter.game.app.gold.dto.DailyDto;
import com.idealighter.game.app.gold.dto.GameGoldLogDto;
import com.idealighter.game.app.gold.dto.GoldLogDto;
import com.idealighter.game.app.gold.dto.SafeGoldLogDto;
import com.idealighter.game.app.gold.dto.SendGoldRecordDto;
import com.idealighter.game.app.gold.dto.TransferMoneyGoldLogDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.ResultPageTotal;
import com.idealighter.game.service.gold.IGoldService;
import com.idealighter.game.service.gold.bo.DailyBo;
import com.idealighter.game.service.gold.bo.GameGoldLogBo;
import com.idealighter.game.service.gold.bo.GoldLogBo;
import com.idealighter.game.service.gold.bo.SafeGoldLogBo;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.gold.bo.TransferMoneyGoldLogBo;
import com.idealighter.utils.check.EmptyUtil;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gold")
@Validated
public class GoldController extends BaseController {

  @Autowired
  private IGoldService goldService;

  @Autowired
  private GoldDtoConvert goldDtoConvert;

  /**
   * 赠送金币列表.
   * 
   * @param adminId 管理员id.
   * @param playerId 玩家id.
   * @param startTimeLong 开始时间.
   * @param endTimeLong 结束时间.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 列表.
   */
  @PreAuthorize("hasAuthority('E0101')")
  @GetMapping("/sendlist")
  public Result sendGoldList(@RequestParam(name = "adminId", required = false) Integer adminId,
      @RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "startTime", required = false) Long startTimeLong,
      @RequestParam(name = "endTime", required = false) Long endTimeLong,
      @RequestParam(name = "channelId", required = false) String channelId,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {

    Date startTime = null;
    Date endTime = null;
    if (startTimeLong != null) {
      startTime = new Date(startTimeLong);
    }
    if (endTimeLong != null) {
      endTime = new Date(endTimeLong);
    }
    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    ResultPageTotal<SendGoldRecordBo> resultPage = goldService.findGoldRecordByPage(adminId,
        playerId, superId, startTime, endTime, channelId, page, pageSize);

    List<SendGoldRecordDto> dtos = goldDtoConvert.toSendGoldRecordDtos(resultPage.getList());

    Result result = new Result(true);

    result.getMap().put("list", dtos);

    result.getMap().put("total", resultPage.getTotal());

    result.getMap().put("statistics", resultPage.getStatistics());

    return result;
  }

  /**
   * 获取金币列表.
   * 
   * @param playerId 玩家id.
   * @param timeLong 时间.
   * @param page 页面.
   * @param pageSize 页大小.
   * @return 金币列表.
   */
  @PreAuthorize("hasAuthority('E0201')")
  @GetMapping("/goldList")
  public Result goldList(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "channelId", required = false) String channelId,
      @NotNull @RequestParam("time") Long timeLong, @NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {

    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    Date selectTime = new Date(timeLong);

    ResultPage<GoldLogBo> resultPage =
        goldService.findGoldLogByPage(playerId, superId, selectTime, channelId, page, pageSize);

    Result result = new Result(true);
    List<GoldLogDto> dtos = goldDtoConvert.toGoldLogDtos(resultPage.getList());

    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());

    return result;
  }


  /**
   * 安全金币 列表 .
   *
   * @author abin
   * @date 2018年6月28日 上午11:46:01
   * @param playerId 玩家id.
   * @param superId 靓号.
   * @param timeLong 时间.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 列表.
   */
  @PreAuthorize("hasAuthority('E0301')")
  @GetMapping("/safeGoldList")
  public Result safeGoldList(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "channelId", required = false) String channelId,
      @RequestParam(name = "orderNo", required = false) String orderNo,
      @NotNull @RequestParam("time") Long timeLong, @NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {

    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    if (EmptyUtil.stringIsEmpty(orderNo)) {
      orderNo = null;
    }

    Date selectTime = new Date(timeLong);

    ResultPage<SafeGoldLogBo> resultPage = goldService.findSafeGoldLogByPage(playerId, superId,
        selectTime, channelId, orderNo, page, pageSize);

    Result result = new Result(true);
    List<SafeGoldLogDto> dtos = goldDtoConvert.toSafeGoldLogDtos(resultPage.getList());

    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());

    return result;
  }


  /**
   * 主站与游戏转账记录 .
   *
   * @author abin
   * @date 2018年6月28日 下午2:48:27
   * @param playerId 玩家id.
   * @param superId 靓号id.
   * @param flag 标志.
   * @param timeLong 时间.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('E0501')")
  @GetMapping("/transferMoneyGoldList")
  public Result transferMoneyGoldList(
      @RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "flag", required = false) Byte flag,
      @RequestParam(name = "channelId", required = false) String channelId,
      @RequestParam(name = "orderNo", required = false) String orderNo,
      @NotNull @RequestParam("time") Long timeLong, @NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {

    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    if (EmptyUtil.stringIsEmpty(orderNo)) {
      orderNo = null;
    }

    Date selectTime = new Date(timeLong);

    ResultPageTotal<TransferMoneyGoldLogBo> resultPage = goldService.findTransferLogByPage(playerId,
        superId, selectTime, flag, channelId, orderNo, page, pageSize);

    Result result = new Result(true);
    List<TransferMoneyGoldLogDto> dtos =
        goldDtoConvert.toTransferMoneyGoldLogDtos(resultPage.getList());

    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());
    result.getMap().put("statistics", resultPage.getStatistics());

    return result;
  }



  /**
   * 获取游戏金币列表.
   * 
   * @param playerId 玩家id.
   * @param timeLong 时间.
   * @param page 页面.
   * @param pageSize 页大小.
   * @return 金币列表.
   */
  @PreAuthorize("hasAuthority('E0401')")
  @GetMapping("/gameGoldList")
  public Result gameGoldList(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "channelId", required = false) String channelId,
      @NotNull @RequestParam("time") Long timeLong, @NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {

    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    Date selectTime = new Date(timeLong);

    ResultPageTotal<GameGoldLogBo> resultPage =
        goldService.findGameGoldByPage(playerId, superId, selectTime, channelId, page, pageSize);

    Result result = new Result(true);
    List<GameGoldLogDto> dtos = goldDtoConvert.toGameGoldLogDtos(resultPage.getList());

    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());
    result.getMap().put("statistics", resultPage.getStatistics());

    return result;
  }

  /**
   * 每日统计 .
   *
   * @author abin
   * @date 2018年9月4日 下午3:23:35
   * @param playerId 玩家id.
   * @param superId 超级id.
   * @param channelId channelId.
   * @param startTimeLong 开始时间.
   * @param endTimeLong 结束时间.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 统计结果.
   */
  @PreAuthorize("hasAuthority('E0601')")
  @GetMapping("/dailyList")
  public Result dailyList(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "channelId", required = false) String channelId,
      @NotNull @RequestParam("startTime") Long startTimeLong,
      @NotNull @RequestParam("endTime") Long endTimeLong, @NotNull @RequestParam("page") int page,
      @NotNull @RequestParam("pageSize") int pageSize) {


    Date startTime = new Date(startTimeLong);
    Date endTime = new Date(endTimeLong);


    if (EmptyUtil.stringIsEmpty(channelId)) {
      channelId = null;
    }

    ResultPageTotal<DailyBo> resultPage = goldService.findDailyByPage(playerId, superId, channelId,
        startTime, endTime, page, pageSize);
    Result result = new Result(true);

    List<DailyDto> dtos = goldDtoConvert.toDailyDtos(resultPage.getList());
    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());
    result.getMap().put("statistics", resultPage.getStatistics());

    return result;
  }
}
