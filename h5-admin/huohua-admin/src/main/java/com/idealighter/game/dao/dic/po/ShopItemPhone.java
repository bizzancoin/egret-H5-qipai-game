package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShopItemPhone implements Serializable {
  /**
   * 道具ID
   */
  private Integer id;

  /**
   * 道具名（辨识用）
   */
  private String name;

  /**
   * 道具出售价格（RMB）
   */
  private Integer price;

  /**
   * 价格单位（1RMB，2YB）
   */
  private Integer currency;

  /**
   * 单个道具赠送游戏币
   */
  private Integer give;

  /**
   * 道具在商城的分类（1购买元宝，2礼品商城，3喇叭）
   */
  private Integer type;

  /**
   * 道具描述所对应的字符串ID，不显示描述填0
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

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getCurrency() {
    return currency;
  }

  public void setCurrency(Integer currency) {
    this.currency = currency;
  }

  public Integer getGive() {
    return give;
  }

  public void setGive(Integer give) {
    this.give = give;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
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
    ShopItemPhone other = (ShopItemPhone) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getPrice() == null ? other.getPrice() == null
            : this.getPrice().equals(other.getPrice()))
        && (this.getCurrency() == null ? other.getCurrency() == null
            : this.getCurrency().equals(other.getCurrency()))
        && (this.getGive() == null ? other.getGive() == null
            : this.getGive().equals(other.getGive()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
    result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
    result = prime * result + ((getGive() == null) ? 0 : getGive().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
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
    sb.append(", price=").append(price);
    sb.append(", currency=").append(currency);
    sb.append(", give=").append(give);
    sb.append(", type=").append(type);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
