package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class Item implements Serializable {
  /**
   * 道具ID
   */
  private Integer id;

  /**
   * 道具名（辨识用）
   */
  private String name;

  /**
   * 道具的最大数量，道具达到最大数量时不可再获得
   */
  private Integer maxcount;

  /**
   * 道具描述所对应的字符串ID，不显示描述填0
   */
  private String desc;

  /**
   * 道具类型（0：会员，1：货币，2：礼品，3：喇叭，4：表情）
   */
  private Integer type;

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

  public Integer getMaxcount() {
    return maxcount;
  }

  public void setMaxcount(Integer maxcount) {
    this.maxcount = maxcount;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
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
    Item other = (Item) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getMaxcount() == null ? other.getMaxcount() == null
            : this.getMaxcount().equals(other.getMaxcount()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getMaxcount() == null) ? 0 : getMaxcount().hashCode());
    result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
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
    sb.append(", maxcount=").append(maxcount);
    sb.append(", desc=").append(desc);
    sb.append(", type=").append(type);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
