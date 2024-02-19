package com.idealighter.game.core.service.log.struct.gm;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.TableType;

/**
 * 后台命令log.
 * 
 * @date 2015年10月22日 下午4:45:56
 *
 */
@LogTable(type = TableType.MONTH)
public class BackendOrderLog extends DbLog {

  @Column(type = "varchar", size = 128, remark = "发送后台命令的ip")
  public final String ip;
  @Column(type = "varchar", size = 128, remark = "命令")
  public final String cmd;
  @Column(type = "varchar", size = 2048, remark = "命令参数")
  public final String args;

  /**
   * 构造函数.
   * 
   * @param ip . ip.
   * @param cmd . 命令.
   * @param args . 参数.
   */
  public BackendOrderLog(String ip, String cmd, String args) {
    this.ip = ip;
    this.cmd = cmd;
    this.args = args;
  }


}
