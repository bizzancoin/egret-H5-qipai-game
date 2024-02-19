package com.idealighter.game.dblog.util;

public class ColumnInfo {
  // 列名
  public String name;
  // 列的类型
  public String type;
  // 列的大小
  public int size;

  /**
   * 列sql定义.
   * 
   * @return .
   */
  public String ddl() {

    return "`" + name + "` " + sqlType();
  }

  /**
   * 列类型定义.
   * 
   * @return .
   */
  private String sqlType() {

    switch (type) {
      case "text":
      case "longtext":
      case "tinytext":
      case "datetime":
      case "date":
      case "time":
      case "year":
        return type;
      default:
        return type + "(" + size + ")";
    }
  }


  @Override
  public String toString() {
    return "ColumnInfo [name=" + name + ", type=" + type + ", size=" + size + "]";
  }

}
