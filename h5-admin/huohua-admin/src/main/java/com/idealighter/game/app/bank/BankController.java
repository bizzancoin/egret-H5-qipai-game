package com.idealighter.game.app.bank;

import com.idealighter.game.app.bank.convert.BankDtoConvert;
import com.idealighter.game.app.bank.dto.TransferGoldRecordDto;
import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.bank.IBankService;
import com.idealighter.game.service.bank.bo.TransferGoldRecordBo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bank")
@Validated
public class BankController extends BaseController {

  @Autowired
  private IBankService bankService;

  @Autowired
  private BankDtoConvert bankDtoConvert;

  /**
   * 转账列表.
   * 
   * @param playerId 玩家id.
   * @param superId 靓号id.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 结果.
   */
  @GetMapping("/transferList")
  public Result list(@RequestParam(name = "playerId", required = false) Long playerId,
      @RequestParam(name = "superId", required = false) Long superId,
      @RequestParam(name = "type", required = false) Byte type,
      @RequestParam(name = "playerType", required = false) Byte playerType,
      @RequestParam(name = "oppositeType", required = false) Byte oppositeType,
      @RequestParam(name = "startTime", required = false) Long startTimeLong,
      @RequestParam(name = "endTime", required = false) Long endTimeLong,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {

    Date startTime = null;
    if (startTimeLong != null) {
      startTime = new Date(startTimeLong);
    }
    Date endTime = null;
    if (endTimeLong != null) {
      endTime = new Date(endTimeLong);
    }

    ResultPage<TransferGoldRecordBo> resultPage = bankService.findByPage(playerId, superId,
        playerType, type, oppositeType, startTime, endTime, page, pageSize);

    long totalGold = bankService.countTotalGold(playerId, superId, playerType, type,
        oppositeType, startTime, endTime);



    List<TransferGoldRecordDto> dtos =
        bankDtoConvert.toTransferGoldRecordDtos(resultPage.getList());

    Result result = new Result(true);
    result.getMap().put("list", dtos);
    result.getMap().put("total", resultPage.getTotal());
    result.getMap().put("totalGold", Math.abs(totalGold));

    return result;

  }
}
