
package com.idealighter.game.core.service.log.struct.other;

import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogIndexKey;
import com.idealighter.game.dblog.annotation.LogIndexKeyField;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.IndexType;
import com.idealighter.game.dblog.core.TableType;

/**
 * 日志库中的LogReason数据 .
 * 
 * @date 2015年8月31日 下午9:15:43
 *
 */
@LogTable(type = TableType.SINGLE)
@LogIndexKey(type = IndexType.PRIMARY, keyName = "", fields = { @LogIndexKeyField(field = "id") })
public class LogReasonData extends DbLog {
  @Column(type = "int", size = 11, remark = "reason标识")
  public final int id;
  @Column(type = "varchar", size = 256, remark = "reason描述")
  public final String desc;

  /**
   * 构造函数 .
   * 
   * @param reason .
   */
  public LogReasonData(LogReason reason) {
    super();
    this.id = reason.id;
    this.desc = reason.desc;
  }

}
