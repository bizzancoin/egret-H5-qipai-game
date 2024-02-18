package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuLineCoord implements Serializable {
  /**
   * 线条（id）
   */
  private Integer id;

  /**
   * 线条序号
   */
  private Integer line;

  /**
   * 行
   */
  private Integer row;

  /**
   * 列
   */
  private Integer column;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }

  public Integer getRow() {
    return row;
  }

  public void setRow(Integer row) {
    this.row = row;
  }

  public Integer getColumn() {
    return column;
  }

  public void setColumn(Integer column) {
    this.column = column;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    ShuihuLineCoord other = (ShuihuLineCoord) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getLine() == null ? other.getLine() == null
            : this.getLine().equals(other.getLine()))
        && (this.getRow() == null ? other.getRow() == null : this.getRow().equals(other.getRow()))
        && (this.getColumn() == null ? other.getColumn() == null
            : this.getColumn().equals(other.getColumn()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getLine() == null) ? 0 : getLine().hashCode());
    result = prime * result + ((getRow() == null) ? 0 : getRow().hashCode());
    result = prime * result + ((getColumn() == null) ? 0 : getColumn().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", line=").append(line);
    sb.append(", row=").append(row);
    sb.append(", column=").append(column);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
