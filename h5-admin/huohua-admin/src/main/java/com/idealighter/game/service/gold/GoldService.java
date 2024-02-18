package com.idealighter.game.service.gold;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.ResultPageTotal;
import com.idealighter.game.common.TransferFlag;
import com.idealighter.game.common.util.LocalDateTimeUtils;
import com.idealighter.game.dao.data.dao.SendGoldRecordDao;
import com.idealighter.game.dao.data.po.SendGoldRecord;
import com.idealighter.game.dao.data.po.SendGoldRecordExample;
import com.idealighter.game.dao.logger.mapper.GameGoldLogMapper;
import com.idealighter.game.dao.logger.mapper.GoldLogMapper;
import com.idealighter.game.dao.logger.mapper.SafeGoldLogMapper;
import com.idealighter.game.dao.logger.mapper.TransferMoneyLogMapper;
import com.idealighter.game.dao.logger.po.GameGoldLog;
import com.idealighter.game.dao.logger.po.GoldLog;
import com.idealighter.game.dao.logger.po.SafeGoldLog;
import com.idealighter.game.dao.logger.po.TransferMoneyGoldLog;
import com.idealighter.game.service.gold.bo.DailyBo;
import com.idealighter.game.service.gold.bo.GameGoldLogBo;
import com.idealighter.game.service.gold.bo.GoldLogBo;
import com.idealighter.game.service.gold.bo.SafeGoldLogBo;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.gold.bo.TransferMoneyGoldLogBo;
import com.idealighter.game.service.gold.convert.GoldBoConvert;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.time.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoldService implements IGoldService {

  @Autowired
  private SendGoldRecordDao sendGoldRecordDao;

  @Autowired
  private GoldLogMapper goldLogMapper;

  @Autowired
  private SafeGoldLogMapper safeGoldLogMapper;

  @Autowired
  private TransferMoneyLogMapper transferMoneyLogMapper;

  @Autowired
  private GameGoldLogMapper gameGoldLogMapper;

  @Autowired
  private GoldBoConvert goldBoConvert;

  @Override
  public boolean addSendGoldRecord(SendGoldRecordBo bo) {
    SendGoldRecord record = goldBoConvert.toSendGoldRecord(bo);
    return sendGoldRecordDao.insert(record) > 0;
  }

  @Override
  public ResultPageTotal<SendGoldRecordBo> findGoldRecordByPage(Integer adminId, Long playerId,
      Long superId, Date startTime, Date endTime, String channelId, int page, int pageSize) {
    SendGoldRecordExample select = new SendGoldRecordExample();
    SendGoldRecordExample.Criteria criteria = select.createCriteria();
    if (adminId != null) {
      criteria.andAdminIdEqualTo(adminId);
    }
    if (playerId != null) {
      criteria.andPlayerIdEqualTo(playerId);
    }
    if (superId != null) {
      criteria.andSuperIdEqualTo(superId);
    }
    if (startTime != null) {
      criteria.andTimeGreaterThanOrEqualTo(startTime);
    }
    if (endTime != null) {
      criteria.andTimeLessThanOrEqualTo(endTime);
    }
    if (EmptyUtil.stringIsNotEmpty(channelId)) {
      criteria.andChannelIdEqualTo(channelId);
    }
    select.setOffset((page - 1) * pageSize);
    select.setLimit(pageSize);
    select.setOrderByClause("time desc, id desc");

    List<SendGoldRecord> records = sendGoldRecordDao.selectByExample(select);

    List<SendGoldRecordBo> bos = goldBoConvert.toSendGoldRecordBos(records);

    long total = sendGoldRecordDao.countByExample(select);

    Map<String, Long> statistics = new HashMap<>();

    long sumSafeGold =
        sendGoldRecordDao.sumSafeGold(adminId, playerId, superId, startTime, endTime, channelId);

    statistics.put("safeGold", sumSafeGold);

    ResultPageTotal<SendGoldRecordBo> resultPage = new ResultPageTotal<>();

    resultPage.setList(bos);
    resultPage.setTotal(total);
    resultPage.setStatistics(statistics);


    return resultPage;
  }

  @Override
  public ResultPage<GoldLogBo> findGoldLogByPage(Long playerId, Long superId, Date time,
      String channelId, int page, int pageSize) {
    int skip = (page - 1) * pageSize;
    int limit = pageSize;

    String selectTime = TimeUtil.yyyyMMdd(time).replace("-", "");

    ResultPage<GoldLogBo> resultPage = new ResultPage<>();
    if (goldLogMapper.existTable(selectTime) > 0) {

      List<GoldLog> goldLogs =
          goldLogMapper.selectByPage(playerId, superId, selectTime, channelId, skip, limit);
      List<GoldLogBo> goldLogBos = goldBoConvert.toGoldLogBos(goldLogs);

      long total = goldLogMapper.count(playerId, superId, selectTime, channelId);

      resultPage.setList(goldLogBos);
      resultPage.setTotal(total);
    }

    return resultPage;
  }

  @Override
  public ResultPage<SafeGoldLogBo> findSafeGoldLogByPage(Long playerId, Long superId, Date time,
      String channelId, String orderNo, int page, int pageSize) {
    int skip = (page - 1) * pageSize;
    int limit = pageSize;

    String selectTime = TimeUtil.yyyyMMdd(time).replace("-", "");
    ResultPage<SafeGoldLogBo> resultPage = new ResultPage<>();

    if (safeGoldLogMapper.existTable(selectTime) > 0) {
      List<SafeGoldLog> safeGoldLogs = safeGoldLogMapper.selectByPage(playerId, superId, selectTime,
          channelId, orderNo, skip, limit);
      List<SafeGoldLogBo> goldLogBos = goldBoConvert.toSafeGoldLogBos(safeGoldLogs);


      long total = safeGoldLogMapper.count(playerId, superId, selectTime, channelId, orderNo);


      resultPage.setList(goldLogBos);
      resultPage.setTotal(total);
    }

    return resultPage;
  }

  @Override
  public ResultPageTotal<TransferMoneyGoldLogBo> findTransferLogByPage(Long playerId, Long superId,
      Date time, Byte flag, String channelId, String orderNo, int page, int pageSize) {
    int skip = (page - 1) * pageSize;
    int limit = pageSize;

    String selectTime = TimeUtil.format("yyyyMM", time).replace("-", "");

    ResultPageTotal<TransferMoneyGoldLogBo> resultPage = new ResultPageTotal<>();
    if (transferMoneyLogMapper.existTable(selectTime) > 0) {
      List<TransferMoneyGoldLog> transferMoneyGoldLogs = transferMoneyLogMapper
          .selectByPage(playerId, superId, selectTime, flag, channelId, orderNo, skip, limit);
      List<TransferMoneyGoldLogBo> goldLogBos =
          goldBoConvert.toTransferMoneyGoldLogBos(transferMoneyGoldLogs);

      long total =
          transferMoneyLogMapper.count(playerId, superId, selectTime, flag, channelId, orderNo);

      Map<String, Long> statistics = new HashMap<>();

      long sumPrice = transferMoneyLogMapper.sumPrice(playerId, superId, selectTime, flag,
          channelId, orderNo, null, null);

      statistics.put("price", sumPrice);
      resultPage.setList(goldLogBos);
      resultPage.setTotal(total);
      resultPage.setStatistics(statistics);
    }

    return resultPage;
  }

  @Override
  public ResultPageTotal<GameGoldLogBo> findGameGoldByPage(Long playerId, Long superId, Date time,
      String channelId, int page, int pageSize) {
    int skip = (page - 1) * pageSize;
    int limit = pageSize;

    String selectTime = TimeUtil.yyyyMMdd(time).replace("-", "");
    ResultPageTotal<GameGoldLogBo> resultPage = new ResultPageTotal<>();

    if (gameGoldLogMapper.existTable(selectTime) > 0) {
      List<GameGoldLog> gameGoldLogs =
          gameGoldLogMapper.selectByPage(playerId, superId, selectTime, channelId, skip, limit);
      List<GameGoldLogBo> goldLogBos = goldBoConvert.toGameGoldLogBos(gameGoldLogs);

      long total = gameGoldLogMapper.count(playerId, superId, selectTime, channelId);

      Map<String, Long> statistics =
          gameGoldLogMapper.sum(playerId, superId, selectTime, channelId);

      resultPage.setList(goldLogBos);
      resultPage.setTotal(total);
      resultPage.setStatistics(statistics);
    }

    return resultPage;
  }

  private static final LocalTime START_LOCALTIME = LocalTime.of(0, 0, 0, 0);

  private static final LocalTime END_LOCALTIME = LocalTime.of(23, 59, 59, 999999999);

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  private static final DateTimeFormatter FORMATTER_YYYY_MM = DateTimeFormatter.ofPattern("yyyyMM");


  @Override
  public ResultPageTotal<DailyBo> findDailyByPage(Long playerId, Long superId, String channelId,
      Date startTime, Date endTime, int page, int pageSize) {

    LocalDate startLocalDate = LocalDateTimeUtils.toLocalDate(startTime);
    LocalDate endLocalDate = LocalDateTimeUtils.toLocalDate(endTime);

    int skip = (page - 1) * pageSize;
    int limit = pageSize;

    ResultPageTotal<DailyBo> resultPage = new ResultPageTotal<>();
    long total = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
    resultPage.setTotal(total);

    List<DailyBo> dailyBos = new ArrayList<>();
    resultPage.setList(dailyBos);

    int index = 0;
    DailyBo totalDailyBo = new DailyBo();
    totalDailyBo.init();

    LocalDateTime startLocalDateTime = LocalDateTime.of(startLocalDate, START_LOCALTIME);
    LocalDateTime endLocalDateTime = LocalDateTime.of(endLocalDate, END_LOCALTIME);
    while (startLocalDateTime.isBefore(endLocalDateTime)) {
      LocalDateTime tempEndDateTime =
          LocalDateTime.of(startLocalDateTime.toLocalDate(), END_LOCALTIME);
      DailyBo dailyBo =
          countDaily(playerId, superId, channelId, startLocalDateTime, tempEndDateTime);
      if (index >= skip && limit > 0) {
        limit--;
        dailyBos.add(dailyBo);
      }
      totalDailyBo.setAllBetting(totalDailyBo.getAllBetting() + dailyBo.getAllBetting());
      totalDailyBo.setAllBonus(totalDailyBo.getAllBonus() + dailyBo.getAllBonus());
      totalDailyBo
          .setAllInoutProfit(totalDailyBo.getAllInoutProfit() + dailyBo.getAllInoutProfit());
      totalDailyBo.setAllProfit(totalDailyBo.getAllProfit() + dailyBo.getAllProfit());
      totalDailyBo.setAllSendGold(totalDailyBo.getAllSendGold() + dailyBo.getAllSendGold());
      totalDailyBo.setAllTransferInGold(
          totalDailyBo.getAllTransferInGold() + dailyBo.getAllTransferInGold());
      totalDailyBo.setAllTransferOutGold(
          totalDailyBo.getAllTransferOutGold() + dailyBo.getAllTransferOutGold());

      startLocalDateTime = startLocalDateTime.plusDays(1);
      index++;
    }

    Map<String, Long> statistics = new HashMap<>();
    statistics.put("allBetting", totalDailyBo.getAllBetting());
    statistics.put("allBonus", totalDailyBo.getAllBonus());
    statistics.put("allProfit", totalDailyBo.getAllProfit());
    statistics.put("allSendGold", totalDailyBo.getAllSendGold());
    statistics.put("allTransferInGold", totalDailyBo.getAllTransferInGold());
    statistics.put("allTransferOutGold", totalDailyBo.getAllTransferOutGold());
    statistics.put("allInoutProfit", totalDailyBo.getAllInoutProfit());

    resultPage.setStatistics(statistics);

    return resultPage;
  }

  private DailyBo countDaily(Long playerId, Long superId, String channelId,
      LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {

    String selectTime = startLocalDateTime.format(FORMATTER);
    DailyBo dailyBo = new DailyBo();
    dailyBo.setTime(selectTime);
    if (gameGoldLogMapper.existTable(selectTime) > 0) {
      // 游戏金币
      // 投注 中奖
      Map<String, Long> statistics =
          gameGoldLogMapper.sum(playerId, superId, selectTime, channelId);
      dailyBo.setAllBetting(statistics.get("betting"));
      dailyBo.setAllBonus(statistics.get("bonus"));
    } else {
      dailyBo.setAllBetting(0L);
      dailyBo.setAllBonus(0L);
    }

    // 后台赠送金币
    long sumSafeGold = sendGoldRecordDao.sumSafeGold(null, playerId, superId,
        LocalDateTimeUtils.toDate(startLocalDateTime), LocalDateTimeUtils.toDate(endLocalDateTime),
        channelId);
    dailyBo.setAllSendGold(sumSafeGold);

    String selectYearMonth = startLocalDateTime.format(FORMATTER_YYYY_MM);
    if (transferMoneyLogMapper.existTable(selectYearMonth) > 0) {
      // 总的转入、总的转出
      long allTransferInGold = transferMoneyLogMapper.sumPrice(playerId, superId, selectYearMonth,
          TransferFlag.RECHARGE, channelId, null, LocalDateTimeUtils.toDate(startLocalDateTime),
          LocalDateTimeUtils.toDate(endLocalDateTime));
      dailyBo.setAllTransferInGold(allTransferInGold);

      long allTransferOutGold = transferMoneyLogMapper.sumPrice(playerId, superId, selectYearMonth,
          TransferFlag.WITHDRAW, channelId, null, LocalDateTimeUtils.toDate(startLocalDateTime),
          LocalDateTimeUtils.toDate(endLocalDateTime));
      dailyBo.setAllTransferOutGold(allTransferOutGold);
    } else {
      dailyBo.setAllTransferInGold(0L);
      dailyBo.setAllTransferOutGold(0L);
    }

    // 平台盈亏
    dailyBo
        .setAllProfit(dailyBo.getAllBetting() - dailyBo.getAllBonus() - dailyBo.getAllSendGold());

    // 收支盈亏
    dailyBo.setAllInoutProfit(dailyBo.getAllTransferInGold() - dailyBo.getAllTransferOutGold());

    return dailyBo;
  }

}
