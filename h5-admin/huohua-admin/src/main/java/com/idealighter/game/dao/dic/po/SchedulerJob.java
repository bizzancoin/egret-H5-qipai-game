package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class SchedulerJob implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 名称
   */
  private String name;

  /**
   * 规则
   */
  private String cron;

  /**
   * java类名
   */
  private String javaclass;

  /**
   * 描述
   */
  private String desc;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public String getJavaclass() {
    return javaclass;
  }

  public void setJavaclass(String javaclass) {
    this.javaclass = javaclass;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
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
    SchedulerJob other = (SchedulerJob) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getCron() == null ? other.getCron() == null
            : this.getCron().equals(other.getCron()))
        && (this.getJavaclass() == null ? other.getJavaclass() == null
            : this.getJavaclass().equals(other.getJavaclass()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getCron() == null) ? 0 : getCron().hashCode());
    result = prime * result + ((getJavaclass() == null) ? 0 : getJavaclass().hashCode());
    result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", cron=").append(cron);
    sb.append(", javaclass=").append(javaclass);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
