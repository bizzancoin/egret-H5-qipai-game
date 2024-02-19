
package com.idealighter.game.core.service.log.struct.server;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.TableType;

/**
 * 启动服务器日志 .
 * 
 * @date 2015年8月31日 上午11:27:27
 *
 */
@LogTable(type = TableType.SINGLE)
public class StartServerLog extends DbLog {
  // 启动服务器日志标识
  public static final long START_SERVER_NO = System.nanoTime();

  @Column(type = "bigint", size = 20, remark = "开服序号,和关服序号一致")
  public final long no = START_SERVER_NO;

}
