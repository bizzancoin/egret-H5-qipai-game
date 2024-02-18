package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class BlackList implements Serializable {
  private Integer id;

  /**
   * IP/MAC
   */
  private String limittype;

  /**
   * 登录/注册
   */
  private String limitaction;

  /**
   * IP地址/MAC地址
   */
  private String limitvalue;

  /**
   * 有效期
   */
  private Date endtime;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLimittype() {
    return limittype;
  }

  public void setLimittype(String limittype) {
    this.limittype = limittype;
  }

  public String getLimitaction() {
    return limitaction;
  }

  public void setLimitaction(String limitaction) {
    this.limitaction = limitaction;
  }

  public String getLimitvalue() {
    return limitvalue;
  }

  public void setLimitvalue(String limitvalue) {
    this.limitvalue = limitvalue;
  }

  public Date getEndtime() {
    return endtime;
  }

  public void setEndtime(Date endtime) {
    this.endtime = endtime;
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
    BlackList other = (BlackList) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getLimittype() == null ? other.getLimittype() == null
            : this.getLimittype().equals(other.getLimittype()))
        && (this.getLimitaction() == null ? other.getLimitaction() == null
            : this.getLimitaction().equals(other.getLimitaction()))
        && (this.getLimitvalue() == null ? other.getLimitvalue() == null
            : this.getLimitvalue().equals(other.getLimitvalue()))
        && (this.getEndtime() == null ? other.getEndtime() == null
            : this.getEndtime().equals(other.getEndtime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getLimittype() == null) ? 0 : getLimittype().hashCode());
    result = prime * result + ((getLimitaction() == null) ? 0 : getLimitaction().hashCode());
    result = prime * result + ((getLimitvalue() == null) ? 0 : getLimitvalue().hashCode());
    result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", limittype=").append(limittype);
    sb.append(", limitaction=").append(limitaction);
    sb.append(", limitvalue=").append(limitvalue);
    sb.append(", endtime=").append(endtime);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
