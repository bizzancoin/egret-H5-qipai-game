
package com.idealighter.game.dblog.task;

import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.util.DbLogSqlBuilder;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库插入日志task.
 *
 */
public class InsertDbLogTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(InsertDbLogTask.class);

  // 数据源
  private final DataSource ds;
  // 数据库日志对象
  private final DbLog dbLog;

  /**
   * 插入日志.
   * 
   * @param ds 数据源.
   * @param dbLog 数据log.
   */
  public InsertDbLogTask(DataSource ds, DbLog dbLog) {
    super();
    this.ds = ds;
    this.dbLog = dbLog;
  }

  @Override
  public void run() {
    String insertSql = "";
    String createTableSql = "";
    Connection connection = null;
    Statement statement = null;

    try {

      insertSql = DbLogSqlBuilder.buildInsertSql(dbLog);
      connection = this.ds.getConnection();
      statement = connection.createStatement();
      try {
        statement.executeUpdate(insertSql);
      } catch (Exception e) {
        createTableSql = DbLogSqlBuilder.buildCreateTblSql(dbLog);
        statement.execute(createTableSql);
        statement.executeUpdate(insertSql);
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug(insertSql);
        LOG.debug(createTableSql);
      }

    } catch (Exception e) {
      LOG.error("插入数据库日志[" + dbLog.getClass().getName() + "]错误", e);
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }

        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
      }
    }
  }

}
