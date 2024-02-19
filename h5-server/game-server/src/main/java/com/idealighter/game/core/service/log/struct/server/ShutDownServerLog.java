
package com.idealighter.game.core.service.log.struct.server;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.TableType;

/**
 * 关闭服务器日志 .
 * 
 * @date 2015年8月31日 上午11:27:09
 *
 */
@LogTable(type = TableType.SINGLE)
public class ShutDownServerLog extends DbLog {

  @Column(type = "bigint", size = 20, remark = "关服序号,和开服序号一致")
  public final long no = StartServerLog.START_SERVER_NO;

}
