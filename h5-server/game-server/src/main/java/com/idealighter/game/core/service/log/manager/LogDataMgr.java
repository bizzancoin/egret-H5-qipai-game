package com.idealighter.game.core.service.log.manager;

import com.google.inject.Singleton;

import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.other.LogReasonData;
import com.idealighter.game.dblog.service.DbLogService;

/**
 * 数据库日志数据管理 .
 * 
 * @date 2015年8月31日 下午9:30:59
 *
 */
@Singleton
public class LogDataMgr {

  public LogDataMgr() {
    initLogReasonData();
  }

  /**
   * 初始化LogReason数据.
   */
  public void initLogReasonData() {
    for (LogReason reason : LogReason.values()) {
      DbLogService.updateLog(new LogReasonData(reason));
    }
  }

}
