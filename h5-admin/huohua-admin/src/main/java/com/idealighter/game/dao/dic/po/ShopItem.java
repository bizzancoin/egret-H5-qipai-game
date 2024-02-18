package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShopItem implements Serializable {
  /**
   * 道具ID
   */
  private Integer id;

  /**
   * 道具名（辨识用）
   */
  private String name;

  /**
   * 金币商城是否出现（0是，1否）
   */
  private Integer gold;

  /**
   * 道具出售价格（金币）
   */
  private Integer pricegold;

  /**
   * 元宝商城是否出现（0是，1否）
   */
  private Integer ingot;

  /**
   * 道具出售价格（元宝）
   */
  private Integer priceingot;

  /**
   * 单个道具赠送游戏币
   */
  private Integer give;

  /**
   * 道具分类（道具在类型上的分组，0：会员，1：货币，2：礼品，3：喇叭，4：表情）
   */
  private Integer type;

  /**
   * 是否热卖（0是，1否）
   */
  private Integer hot;

  /**
   * 单次购买最大数量
   */
  private Integer maxcount;

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

  public Integer getGold() {
    return gold;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  public Integer getPricegold() {
    return pricegold;
  }

  public void setPricegold(Integer pricegold) {
    this.pricegold = pricegold;
  }

  public Integer getIngot() {
    return ingot;
  }

  public void setIngot(Integer ingot) {
    this.ingot = ingot;
  }

  public Integer getPriceingot() {
    return priceingot;
  }

  public void setPriceingot(Integer priceingot) {
    this.priceingot = priceingot;
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

  public Integer getHot() {
    return hot;
  }

  public void setHot(Integer hot) {
    this.hot = hot;
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
    ShopItem other = (ShopItem) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getGold() == null ? other.getGold() == null
            : this.getGold().equals(other.getGold()))
        && (this.getPricegold() == null ? other.getPricegold() == null
            : this.getPricegold().equals(other.getPricegold()))
        && (this.getIngot() == null ? other.getIngot() == null
            : this.getIngot().equals(other.getIngot()))
        && (this.getPriceingot() == null ? other.getPriceingot() == null
            : this.getPriceingot().equals(other.getPriceingot()))
        && (this.getGive() == null ? other.getGive() == null
            : this.getGive().equals(other.getGive()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getHot() == null ? other.getHot() == null : this.getHot().equals(other.getHot()))
        && (this.getMaxcount() == null ? other.getMaxcount() == null
            : this.getMaxcount().equals(other.getMaxcount()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getGold() == null) ? 0 : getGold().hashCode());
    result = prime * result + ((getPricegold() == null) ? 0 : getPricegold().hashCode());
    result = prime * result + ((getIngot() == null) ? 0 : getIngot().hashCode());
    result = prime * result + ((getPriceingot() == null) ? 0 : getPriceingot().hashCode());
    result = prime * result + ((getGive() == null) ? 0 : getGive().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getHot() == null) ? 0 : getHot().hashCode());
    result = prime * result + ((getMaxcount() == null) ? 0 : getMaxcount().hashCode());
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
    sb.append(", gold=").append(gold);
    sb.append(", pricegold=").append(pricegold);
    sb.append(", ingot=").append(ingot);
    sb.append(", priceingot=").append(priceingot);
    sb.append(", give=").append(give);
    sb.append(", type=").append(type);
    sb.append(", hot=").append(hot);
    sb.append(", maxcount=").append(maxcount);
    sb.append(", desc=").append(desc);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
