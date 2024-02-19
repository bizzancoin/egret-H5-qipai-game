package com.idealighter.game.dblog.core;

import com.idealighter.game.dblog.annotation.Column;

import java.util.Date;

/**
 * 数据库日志对象抽象类.
 *
 */
public abstract class DbLog {
  /** 由日志系统插入. */
  @Column(type = "datetime", size = 3, remark = "时间")
  public final Date time = new Date();

}
