
package com.idealighter.game.dblog.task;

import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.util.DbLogSqlBuilder;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 保存数据库日志task,如果数据存在则更新，不存在则插入.
 *
 */
public class UpdateDbLogTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(UpdateDbLogTask.class);

  // 数据源
  private final DataSource ds;
  // 数据库日志对象
  private final DbLog dbLog;

  /**
   * 构造函数.
   * 
   * @param ds 数据源.
   * @param dbLog 数据log.
   */
  public UpdateDbLogTask(DataSource ds, DbLog dbLog) {
    super();
    this.ds = ds;
    this.dbLog = dbLog;
  }

  @Override
  public void run() {
    String updateSql = "";
    String createTableSql = "";
    Connection connection = null;
    Statement statement = null;

    try {

      updateSql = DbLogSqlBuilder.buildUpdateByIdSql(dbLog);
      connection = this.ds.getConnection();
      statement = connection.createStatement();
      try {
        int rows = statement.executeUpdate(updateSql);
        if (rows == 0) {
          statement.executeUpdate(DbLogSqlBuilder.buildInsertSql(dbLog));
        }
      } catch (Exception e) {
        createTableSql = DbLogSqlBuilder.buildCreateTblSql(dbLog);

        statement.execute(createTableSql);
        statement.executeUpdate(DbLogSqlBuilder.buildInsertSql(dbLog));
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug(updateSql);
        LOG.debug(createTableSql);
      }

    } catch (Exception e) {
      LOG.error("更新数据库日志[" + dbLog.getClass().getName() + "]错误,sql[" + updateSql + "]", e);
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
