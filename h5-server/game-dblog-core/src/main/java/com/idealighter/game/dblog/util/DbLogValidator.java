package com.idealighter.game.dblog.util;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.service.DbLogService;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库日志类校验工具类.
 *
 */
public class DbLogValidator {

  private static final Logger LOG = LoggerFactory.getLogger(DbLogValidator.class);

  private DbLogValidator() {}

  /**
   * 校验数据库中的日志表,在调整日志策略和调整字段后，启动服务器时要校验数据库的表的列定义是否需要调整 .
   * @param logType . .
   */
  public static void validateTbl(Class<? extends DbLog> logType) {
    Connection conn = null;
    String tblName = null;
    Date now = new Date();
    // 不一致需要执行的sql语句
    List<String> resSqls = new ArrayList<>();

    try {
      conn = DbLogService.getConnection();
      tblName = DbLogSqlBuilder.buildTblName(logType, now);

      List<ColumnInfo> classTblColumns = classTblColumns(logType);
      // 数据库表中的列定义信息，如果日志表还未产生，则为null
      List<ColumnInfo> dbTblColumns = dbTblColumns(tblName, conn);
      if (dbTblColumns == null) {
        return;
      }

      Map<String, ColumnInfo> dbTblColumnsMap = new HashMap<>();
      for (ColumnInfo column : dbTblColumns) {
        dbTblColumnsMap.put(column.name, column);
      }

      /*
       * 比较日志class中定义的列和表中定义的列，以代码中的列定义为准
       */
      for (ColumnInfo classTblColumn : classTblColumns) {
        ColumnInfo dbTblColumn = dbTblColumnsMap.get(classTblColumn.name.toLowerCase());

        if (dbTblColumn == null) {
          resSqls.add("ALTER TABLE " + tblName + " ADD COLUMN " + classTblColumn.ddl() + ";");
        } else {
          String ddl = compare(classTblColumn, dbTblColumn);
          if (ddl != null) {
            resSqls.add("ALTER TABLE " + tblName + " MODIFY COLUMN " + ddl + ";");
          }
        }
      }

      // 执行调整列的sql语句
      if (resSqls.size() > 0) {
        Statement createStatement = conn.createStatement();
        for (String resSql : resSqls) {
          LOG.info("检查到变更" + resSql);
          createStatement.addBatch(resSql);
        }
        createStatement.executeBatch();
      }

    } catch (Exception e) {
      LOG.error("校验[" + logType.getName() + "]日志表错误", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          LOG.error("校验[" + logType.getName() + "]日志表时关闭数据库连接失败", e);
        }
      }
    }
  }

  /**
   * 比较代码中的列定义和表中的列定义.
   * @param classColumn . .
   * @param dbColumn . .
   * @return . 返回修改表结构的列定义字符串
   */
  private static String compare(ColumnInfo classColumn, ColumnInfo dbColumn) {
    if (classColumn.type.startsWith(dbColumn.type)) {
      return null;
    }

    return classColumn.ddl();
  }

  /**
   * 获取代码中的列定义 .
   * @param logType . .
   * @return .
   */
  private static List<ColumnInfo> classTblColumns(Class<? extends DbLog> logType) {

    List<ColumnInfo> clmnInfos = new ArrayList<>();

    for (Field field : logType.getFields()) {
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        ColumnInfo clmnInfo = new ColumnInfo();
        clmnInfo.name = field.getName();
        clmnInfo.type = column.type();
        clmnInfo.size = column.size();
        clmnInfos.add(clmnInfo);
      }
    }

    return clmnInfos;
  }


  /**
   * 获取数据库表中的列定义信息，如果日志表还未产生，则为null.
   * @param tblName . .
   * @param conn . .
   * @return .
   * @throws SQLException .
   */
  private static List<ColumnInfo> dbTblColumns(String tblName, Connection conn)
      throws SQLException {
    DatabaseMetaData metaData = conn.getMetaData();
    ResultSet columns = metaData.getColumns(null, "%", tblName, "%");
    List<ColumnInfo> clmnInfos = new ArrayList<>();
    while (columns.next()) {
      ColumnInfo clmnInfo = new ColumnInfo();
      clmnInfo.name = columns.getString("COLUMN_NAME").toLowerCase();
      clmnInfo.type = columns.getString("TYPE_NAME").toLowerCase();
      clmnInfo.size = columns.getInt("COLUMN_SIZE");
      clmnInfos.add(clmnInfo);
    }

    return clmnInfos.size() > 0 ? clmnInfos : null;
  }


}

