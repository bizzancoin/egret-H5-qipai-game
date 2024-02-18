package com.idealighter.game.app.recharge;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.recharge.convert.RechargeDtoConvert;
import com.idealighter.game.app.recharge.dto.RechargeDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.recharge.IRechargeService;
import com.idealighter.game.service.recharge.bo.RechargeBo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/recharge")
@Validated
public class RechargeController extends BaseController {

  @Autowired
  private IRechargeService rechargeService;

  @Autowired
  private RechargeDtoConvert rechargeDtoConvert;

  /**
   * 获取充值列表 .
   *
   * @author abin
   * @date 2018年5月26日 下午3:50:51
   * @param trandeNo 订单号.
   * @param payType 支付类型(1支付宝2微信).
   * @param playerId 玩家id.
   * @param state 订单状态（0待支付，1支付到账）.
   * @param startTimeLong 开始时间.
   * @param endTimeLong 结束时间.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 列表.
   */
  @GetMapping("/list")
  public Result rechargeList(@RequestParam(name = "trandeNo", required = false) String trandeNo,
      @RequestParam(name = "payType", required = false) Byte payType,
      @RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "state", required = false) Byte state,
      @RequestParam(name = "startTime", required = false) Long startTimeLong,
      @RequestParam(name = "endTime", required = false) Long endTimeLong,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {

    Date startTime = null;
    Date endTime = null;

    if (startTimeLong != null) {
      startTime = new Date(startTimeLong);
    }
    if (endTimeLong != null) {
      endTime = new Date(endTimeLong);
    }

    ResultPage<RechargeBo> rechargeBos = rechargeService.findByPage(trandeNo, payType, playerId,
        state, startTime, endTime, page, pageSize);

    List<RechargeDto> rechargeDtos = rechargeDtoConvert.toRechargeDtos(rechargeBos.getList());

    Result result = new Result(true);
    result.getMap().put("list", rechargeDtos);
    result.getMap().put("total", rechargeBos.getTotal());

    return result;

  }

}
