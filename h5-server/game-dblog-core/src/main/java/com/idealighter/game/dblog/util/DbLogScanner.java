package com.idealighter.game.dblog.util;

import com.idealighter.game.dblog.core.DbLog;

import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;

/**
 * 数据库日志类扫描工具类.
 *
 */
public class DbLogScanner {

  private DbLogScanner() {}

  /**
   * 扫描packageName下的DbLog的子类 .
   * @param packageName . .
   * @return .
   */
  public static <C extends DbLog> Set<Class<? extends C>> scanLogClass(String packageName) {
    ResolverUtil<C> dbLogResolver =
        new ResolverUtil<C>().find(new ResolverUtil.IsA(DbLog.class), packageName);

    return dbLogResolver.getClasses();
  }

}
