package com.idealighter.game.dao.manage.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class ManageLog implements Serializable {
  private Integer id;

  private Integer adminId;

  private String adminName;

  private Long playerId;

  private String sys;

  private String doaction;

  private String docontent;

  private Date dotime;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public String getSys() {
    return sys;
  }

  public void setSys(String sys) {
    this.sys = sys;
  }

  public String getDoaction() {
    return doaction;
  }

  public void setDoaction(String doaction) {
    this.doaction = doaction;
  }

  public String getDocontent() {
    return docontent;
  }

  public void setDocontent(String docontent) {
    this.docontent = docontent;
  }

  public Date getDotime() {
    return dotime;
  }

  public void setDotime(Date dotime) {
    this.dotime = dotime;
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
    ManageLog other = (ManageLog) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getAdminId() == null ? other.getAdminId() == null
            : this.getAdminId().equals(other.getAdminId()))
        && (this.getAdminName() == null ? other.getAdminName() == null
            : this.getAdminName().equals(other.getAdminName()))
        && (this.getPlayerId() == null ? other.getPlayerId() == null
            : this.getPlayerId().equals(other.getPlayerId()))
        && (this.getSys() == null ? other.getSys() == null : this.getSys().equals(other.getSys()))
        && (this.getDoaction() == null ? other.getDoaction() == null
            : this.getDoaction().equals(other.getDoaction()))
        && (this.getDocontent() == null ? other.getDocontent() == null
            : this.getDocontent().equals(other.getDocontent()))
        && (this.getDotime() == null ? other.getDotime() == null
            : this.getDotime().equals(other.getDotime()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
    result = prime * result + ((getAdminName() == null) ? 0 : getAdminName().hashCode());
    result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
    result = prime * result + ((getSys() == null) ? 0 : getSys().hashCode());
    result = prime * result + ((getDoaction() == null) ? 0 : getDoaction().hashCode());
    result = prime * result + ((getDocontent() == null) ? 0 : getDocontent().hashCode());
    result = prime * result + ((getDotime() == null) ? 0 : getDotime().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", adminId=").append(adminId);
    sb.append(", adminName=").append(adminName);
    sb.append(", playerId=").append(playerId);
    sb.append(", sys=").append(sys);
    sb.append(", doaction=").append(doaction);
    sb.append(", docontent=").append(docontent);
    sb.append(", dotime=").append(dotime);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
