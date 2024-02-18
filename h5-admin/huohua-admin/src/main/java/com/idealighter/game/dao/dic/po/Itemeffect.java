package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class Itemeffect implements Serializable {
  /**
   * 道具ID
   */
  private Integer id;

  /**
   * 道具名（辨识用）
   */
  private String name;

  /**
   * 道具类型（道具在类型上的分组，0：会员，1：货币，2：礼品，3：喇叭）
   */
  private Integer type;

  /**
   * 道具使用后增加属性类型（0无，1元宝，2金币，4积分，5奖券），和item表对应
   */
  private Integer attr;

  /**
   * 增加属性数量
   */
  private Integer attrnum;

  /**
   * vip时限/天(只针对会员)
   */
  private Integer vipduration;

  /**
   * 是否允许对其他人使用（1允许，0不允许）
   */
  private Byte other;

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

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getAttr() {
    return attr;
  }

  public void setAttr(Integer attr) {
    this.attr = attr;
  }

  public Integer getAttrnum() {
    return attrnum;
  }

  public void setAttrnum(Integer attrnum) {
    this.attrnum = attrnum;
  }

  public Integer getVipduration() {
    return vipduration;
  }

  public void setVipduration(Integer vipduration) {
    this.vipduration = vipduration;
  }

  public Byte getOther() {
    return other;
  }

  public void setOther(Byte other) {
    this.other = other;
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
    Itemeffect other = (Itemeffect) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getAttr() == null ? other.getAttr() == null
            : this.getAttr().equals(other.getAttr()))
        && (this.getAttrnum() == null ? other.getAttrnum() == null
            : this.getAttrnum().equals(other.getAttrnum()))
        && (this.getVipduration() == null ? other.getVipduration() == null
            : this.getVipduration().equals(other.getVipduration()))
        && (this.getOther() == null ? other.getOther() == null
            : this.getOther().equals(other.getOther()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getAttr() == null) ? 0 : getAttr().hashCode());
    result = prime * result + ((getAttrnum() == null) ? 0 : getAttrnum().hashCode());
    result = prime * result + ((getVipduration() == null) ? 0 : getVipduration().hashCode());
    result = prime * result + ((getOther() == null) ? 0 : getOther().hashCode());
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
    sb.append(", type=").append(type);
    sb.append(", attr=").append(attr);
    sb.append(", attrnum=").append(attrnum);
    sb.append(", vipduration=").append(vipduration);
    sb.append(", other=").append(other);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
