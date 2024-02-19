package com.idealighter.game.dblog.util;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.dblog.annotation.Column;
import com.idealighter.game.dblog.annotation.LogIndexKey;
import com.idealighter.game.dblog.annotation.LogIndexKeyField;
import com.idealighter.game.dblog.annotation.LogTable;
import com.idealighter.game.dblog.core.DbLog;
import com.idealighter.game.dblog.core.IndexType;
import com.idealighter.game.dblog.core.TableType;
import com.idealighter.game.dblog.service.DbLogService;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * sql构建器.
 *
 */
public class DbLogSqlBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(DbLogService.class);
  private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyyMMdd");
  private static final SimpleDateFormat YYYY_MM = new SimpleDateFormat("yyyyMM");
  private static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

  private DbLogSqlBuilder() {}

  /**
   * 构建创建表sql .
   * 
   * @param dbLog . .
   * @return .
   */
  public static String buildCreateTblSql(DbLog dbLog) {
    // 表定义sql
    StringBuilder ddl = new StringBuilder(256);

    ddl.append("CREATE TABLE IF NOT EXISTS `").append(buildTblName(dbLog.getClass(), dbLog.time));

    boolean checkResult = checkTableIndex(dbLog.getClass());

    StringBuffer fieldBuffer = new StringBuffer("");
    // 所有的字段必须是public的且被Column注解
    Field[] fileArrays = dbLog.getClass().getFields();
    List<String> fieldNames = new ArrayList<String>();
    for (int i = 0; i < fileArrays.length; i++) {
      Field field = fileArrays[i];
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        // 添加到字段列表
        fieldNames.add(field.getName().toLowerCase());

        if (checkResult) {
          List<String> ids = idColumn(dbLog.getClass());
          if (ids.contains(field.getName())) { // 主键字段not null
            fieldBuffer.append("`" + field.getName() + "`\t");
            // 根据类型和大小拼接ddl，如：bigint(20)
            fieldBuffer.append(column.type() + (column.size() > 0 ? "(" + column.size() + ")" : "")
                + "\t" + "NOT NULL" + "\t");
            fieldBuffer.append("COMMENT '" + column.remark() + "'").append(",\n");
            continue;
          }
        }
        fieldBuffer.append("`" + field.getName() + "`\t");
        // 根据类型和大小拼接ddl，如：bigint(20)
        fieldBuffer
            .append(column.type() + (column.size() > 0 ? "(" + column.size() + ")" : "") + "\t");
        fieldBuffer.append("COMMENT '" + column.remark() + "'").append(" DEFAULT NULL,\n");
      }
    }

    // 索引字段合法（包含没有索引的情况）
    if (checkResult) {
      List<String> ids = idColumn(dbLog.getClass());
      if (ids == null || ids.size() == 0 && !fieldNames.contains("id")) {
        ddl.append("` (\n`id` int(11) NOT NULL AUTO_INCREMENT,\n");
      } else {
        ddl.append("` (\n");
      }
    } else { // 索引字段不合法
      ddl.append("` (\n");
    }

    // 添加字段
    ddl.append(fieldBuffer);

    // 索引
    String indexString = tableIndex(dbLog.getClass());
    if (indexString != null && indexString.trim().length() > 0) {
      if (!indexString.toLowerCase().contains("primary key") && !fieldNames.contains("id")) {
        ddl.append("PRIMARY KEY (`id`),\n");
      }
      ddl.append(indexString);
    } else if (!fieldNames.contains("id")) {
      ddl.append("PRIMARY KEY (`id`)");
    } else {
      int lastIndex = ddl.lastIndexOf(",\n");
      ddl.deleteCharAt(lastIndex);
      ddl.deleteCharAt(lastIndex);
    }

    ddl.append("\n) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4");
    return ddl.toString();
  }

  /**
   * 构建插入sql .
   * 
   * @param dbLog . .
   * @return .
   */
  public static String buildInsertSql(DbLog dbLog) throws Exception {
    StringBuilder insertSql = new StringBuilder(256);
    insertSql.append("insert into `" + buildTblName(dbLog.getClass(), dbLog.time) + "` ");

    StringBuilder fieldsStr = new StringBuilder(128);
    StringBuilder valuesStr = new StringBuilder(128);
    fieldsStr.append("(");
    valuesStr.append("(");

    Field[] fields = dbLog.getClass().getFields();
    // 所有的字段必须是public的且被Column注解
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      if (field.getAnnotation(Column.class) != null) {
        fieldsStr.append("`" + field.getName() + "`,");
        valuesStr.append(formatVal(field.get(dbLog)) + ",");
      }
    }

    if (fieldsStr.length() > 1) {
      fieldsStr.deleteCharAt(fieldsStr.length() - 1);
    }
    if (valuesStr.length() > 1) {
      valuesStr.deleteCharAt(valuesStr.length() - 1);
    }
    fieldsStr.append(")");
    valuesStr.append(")");

    return insertSql.append(fieldsStr).append(" values ").append(valuesStr).toString();
  }

  /**
   * 构建更新sql .
   * 
   * @param dbLog . .
   * @return .
   */
  public static String buildUpdateByIdSql(DbLog dbLog) throws Exception {
    StringBuilder updateSql = new StringBuilder(256);
    updateSql.append("update " + buildTblName(dbLog.getClass(), dbLog.time) + " set ");

    List<String> ids = idColumn(dbLog.getClass());
    if (ids == null) {
      ids = new ArrayList<String>();
    }

    if (ids.size() == 0) {
      // 全部字段
      List<String> fieldNames = new ArrayList<String>();
      Field[] fileArrays = dbLog.getClass().getFields();
      for (int i = 0; i < fileArrays.length; i++) {
        Field field = fileArrays[i];
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
          // 添加到字段列表
          fieldNames.add(field.getName().toLowerCase());
        }
      }

      if (!fieldNames.contains("id")) {
        ids.add("id");
      }
    }

    Map<String, String> idValMaps = new HashMap<String, String>();
    Field[] fields = dbLog.getClass().getFields();
    // 所有的字段必须是public的且被Column注解
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      if (field.getAnnotation(Column.class) != null) {
        String formatVal = formatVal(field.get(dbLog));
        updateSql.append('`' + field.getName() + '`');
        updateSql.append("=");
        updateSql.append(formatVal);
        updateSql.append(",");
        String fieldName = field.getName();
        for (String idColumn : ids) {
          if (fieldName.equals(idColumn)) {
            idValMaps.put(idColumn, formatVal);
            break;
          }
        }
      }
    }

    updateSql.deleteCharAt(updateSql.length() - 1).append(" where ");
    String and = " and ";
    for (String fieldName : idValMaps.keySet()) {
      updateSql.append(fieldName).append("=").append(idValMaps.get(fieldName));
      updateSql.append(and);
    }
    int updateLen = updateSql.length();
    updateSql.delete(updateLen - and.length(), updateLen - 1);


    return updateSql.toString();
  }

  /**
   * 格式化filed的val值 .
   * 
   * @param val . .
   * @return .
   */
  private static String formatVal(Object val) {
    if (val == null) {
      return "null";
    }

    if (val instanceof Date) {
      return "'" + YYYY_MM_DD_HH_MM_SS.format(val) + "'";
    }

    if (val instanceof Integer || val instanceof String || val instanceof Long
        || val instanceof Short || val instanceof Byte || val instanceof String) {
      return "'" + val + "'";
    }

    if (val instanceof Boolean) {

      return (Boolean) val ? "1" : "0";
    }

    return "'" + JSON.toJSONString(val) + "'";
  }

  /**
   * 构建表名sql .
   * 
   * @param logType . .
   * @param time . .
   * @return .
   */
  public static String buildTblName(Class<? extends DbLog> logType, Date time) {
    StringBuilder tblName = new StringBuilder(logType.getSimpleName());
    LogTable logTable = logType.getAnnotation(LogTable.class);

    // 表类型,默认单表
    TableType type = TableType.SINGLE;
    if (logTable != null) {
      type = logTable.type();
    }

    switch (type) {
      case YEAR:
        tblName.append(YYYY.format(time));
        break;
      case MONTH:
        tblName.append(YYYY_MM.format(time));
        break;
      case DAY:
        tblName.append(YYYY_MM_DD.format(time));
        break;
      case SINGLE:
        break;
      default:
        break;
    }

    return tblName.toString();
  }

  /**
   * 获取id列名称 .
   * 
   * @param logType . .
   * @return .
   */
  public static List<String> idColumn(Class<? extends DbLog> logType) {
    List<String> result = new ArrayList<String>();
    LogIndexKey[] indexKeys = allLogIndexKey(logType);
    if (indexKeys != null && indexKeys.length > 0) {
      for (LogIndexKey item : indexKeys) {
        if (item.type().equals(IndexType.PRIMARY)) {
          for (int i = 0; i < item.fields().length; i++) {
            LogIndexKeyField fieldItem = item.fields()[i];
            result.add(fieldItem.field());
          }
        }
      }
    }
    return result;
  }

  /**
   * 创建桌子索引.
   * 
   * @Title tableIndex.
   * @author houdongsheng
   * @date 2018年2月2日 下午6:01:24
   * @param logType 类
   * @return 建索引语句
   */
  private static String tableIndex(Class<? extends DbLog> logType) {
    LogIndexKey[] indexKeys = allLogIndexKey(logType);
    StringBuffer result = new StringBuffer("");
    int indexKeySize = 0;
    if (indexKeys != null && (indexKeySize = indexKeys.length) > 0) {
      for (LogIndexKey item : indexKeys) {
        if (item.type().equals(IndexType.PRIMARY)) {
          result.append("PRIMARY KEY ");
        } else if (item.type().equals(IndexType.UNIQUE)) {
          result.append("UNIQUE KEY ");
        } else if (item.type().equals(IndexType.NORMAY)) {
          result.append("KEY ");
        }
        if (!item.type().equals(IndexType.PRIMARY)) {
          result.append("`").append(item.keyName()).append("`");
        }
        result.append("(");
        List<LogIndexKeyField> indexFieldItem = Arrays.asList(item.fields());
        Collections.sort(indexFieldItem, (a, b) -> a.order() - b.order());
        for (int i = 0; i < indexFieldItem.size(); i++) {
          LogIndexKeyField fieldItem = indexFieldItem.get(i);
          result.append("`").append(fieldItem.field()).append("`");
          if (fieldItem.len() > 0) {
            result.append("(").append(fieldItem.len()).append(")");
          }

          if (i != item.fields().length - 1) {
            result.append(",");
          }
        }

        if (item != indexKeys[indexKeySize - 1]) {
          result.append("),\n");
        } else {
          result.append(")");
        }
      }
    }
    return result.toString();
  }

  private static boolean checkIndexFiledName(String filed, Class<? extends DbLog> dbLog) {
    boolean result = false;
    for (Field field : dbLog.getFields()) {
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        String realName = field.getName();
        if (realName.equals(filed)) {
          result = true;
          break;
        }
      }
    }
    return result;
  }

  private static LogIndexKey[] allLogIndexKey(Class<? extends DbLog> dbLog) {
    Map<LogIndexKey, LogIndexKey> map = new HashMap<LogIndexKey, LogIndexKey>();
    Class<?> clz = dbLog;
    while (DbLog.class.isAssignableFrom(clz)) {
      LogIndexKey[] indexKeys = clz.getAnnotationsByType(LogIndexKey.class);
      if (indexKeys != null && indexKeys.length > 0) {
        for (LogIndexKey item : indexKeys) {
          map.put(item, item);
        }
      }
      // 判断父类
      clz = clz.getSuperclass();
    }
    LogIndexKey[] result = new LogIndexKey[map.size()];
    map.keySet().toArray(result);
    return result;
  }

  private static boolean checkTableIndex(Class<? extends DbLog> dbLog) {
    LogIndexKey[] indexKeys = allLogIndexKey(dbLog);
    boolean result = true;
    if (indexKeys != null && indexKeys.length > 0) {
      Map<String, LogIndexKey> maps = new HashMap<String, LogIndexKey>();
      outer: for (LogIndexKey item : indexKeys) {
        for (LogIndexKey okItem : maps.values()) {
          if (okItem.keyName().equals(item.keyName())) {
            LOG.error("索引名称重复:[{}]", dbLog);
            result = false;
            break outer;
          }
          if (item.type().equals(IndexType.PRIMARY) && okItem.type().equals(IndexType.PRIMARY)) {
            LOG.error("不能设置多个主键注解:[{}]", dbLog);
            result = false;
            break outer;
          }
        }
        String keyName = item.keyName();
        if (!item.type().equals(IndexType.PRIMARY)) {
          if (keyName == null || keyName.trim().length() == 0) {
            LOG.error("索引[{}]缺少索引名称", dbLog, item.keyName());
            result = false;
            break outer;
          }
        }

        LogIndexKeyField[] filedItems = item.fields();
        if (filedItems == null || filedItems.length == 0) {
          LOG.error("索引[{}][{}]缺少字段", dbLog, item.keyName());
          result = false;
          break outer;
        }
        List<Integer> okFiledOrder = new ArrayList<Integer>();
        for (LogIndexKeyField fieldItem : filedItems) {
          String fieldName = fieldItem.field();
          if (fieldName == null || fieldName.trim().length() == 0) {
            LOG.error("索引[{}][{}][{}]缺少字段名", dbLog, item.keyName(), fieldItem);
            result = false;
            break outer;
          }
          if (!checkIndexFiledName(fieldName, dbLog)) {
            LOG.error("索引[{}][{}]字段名[{}]不合法", dbLog, item.keyName(), fieldName);
            result = false;
            break outer;
          }
          int order = fieldItem.order();
          if (order != -1) {
            if (okFiledOrder.contains(order)) {
              LOG.error("索引[{}][{}][{}]序号重复[{}]", dbLog, item.keyName(), fieldName, order);
              result = false;
              break outer;
            }
            okFiledOrder.add(order);
          }
          int len = fieldItem.len();
          if (len != -1) {
            if (len <= 0) {
              LOG.error("索引[{}][{}][{}]长度[{}]不合法", dbLog, item.keyName(), fieldName, order);
              result = false;
              break outer;
            }
          }
        }
        maps.put(item.keyName(), item);
      }
    }
    return result;
  }
}
