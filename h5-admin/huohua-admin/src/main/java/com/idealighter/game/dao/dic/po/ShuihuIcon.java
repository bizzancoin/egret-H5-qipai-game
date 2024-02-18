package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuIcon implements Serializable {
  /**
   * 图标id
   */
  private Integer id;

  /**
   * 图标名称
   */
  private String name;

  /**
   * 图标描述
   */
  private String desc;

  /**
   * 类型(0:人物,1:武器,2:其他)
   */
  private Byte type;

  /**
   * 权重(合计权重1w)
   */
  private Integer weight;

  /**
   * 单独特效id
   */
  private String effect1;

  /**
   * 全体特效id
   */
  private String effect2;

  /**
   * 单独音效
   */
  private String music1;

  /**
   * 全体音效
   */
  private String music2;

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

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getEffect1() {
    return effect1;
  }

  public void setEffect1(String effect1) {
    this.effect1 = effect1;
  }

  public String getEffect2() {
    return effect2;
  }

  public void setEffect2(String effect2) {
    this.effect2 = effect2;
  }

  public String getMusic1() {
    return music1;
  }

  public void setMusic1(String music1) {
    this.music1 = music1;
  }

  public String getMusic2() {
    return music2;
  }

  public void setMusic2(String music2) {
    this.music2 = music2;
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
    ShuihuIcon other = (ShuihuIcon) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getWeight() == null ? other.getWeight() == null
            : this.getWeight().equals(other.getWeight()))
        && (this.getEffect1() == null ? other.getEffect1() == null
            : this.getEffect1().equals(other.getEffect1()))
        && (this.getEffect2() == null ? other.getEffect2() == null
            : this.getEffect2().equals(other.getEffect2()))
        && (this.getMusic1() == null ? other.getMusic1() == null
            : this.getMusic1().equals(other.getMusic1()))
        && (this.getMusic2() == null ? other.getMusic2() == null
            : this.getMusic2().equals(other.getMusic2()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
    result = prime * result + ((getEffect1() == null) ? 0 : getEffect1().hashCode());
    result = prime * result + ((getEffect2() == null) ? 0 : getEffect2().hashCode());
    result = prime * result + ((getMusic1() == null) ? 0 : getMusic1().hashCode());
    result = prime * result + ((getMusic2() == null) ? 0 : getMusic2().hashCode());
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
    sb.append(", desc=").append(desc);
    sb.append(", type=").append(type);
    sb.append(", weight=").append(weight);
    sb.append(", effect1=").append(effect1);
    sb.append(", effect2=").append(effect2);
    sb.append(", music1=").append(music1);
    sb.append(", music2=").append(music2);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
