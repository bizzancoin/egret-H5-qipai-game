package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class TexapokerRobotRules implements Serializable {
  /**
   * 策略ID
   */
  private Integer id;

  /**
   * 发牌轮数
   */
  private Integer cardsnum;

  /**
   * 是否有对子（0否1是）
   */
  private Integer paired;

  /**
   * 对子是否是最大牌值（0否1是）
   */
  private Integer pairedmax;

  /**
   * 对子是否是最小牌值（0否1是）
   */
  private Integer pairedmin;

  /**
   * 对子中是否有手牌
   */
  private Integer pairedmy;

  /**
   * 是否有两对
   */
  private Integer twotwo;

  /**
   * 两对中是否有手牌
   */
  private Integer twotwomy;

  /**
   * 是否有三条
   */
  private Integer three;

  /**
   * 三条是否是最大牌值（0否1是）
   */
  private Integer threemax;

  /**
   * 三条中是否有手牌
   */
  private Integer threemy;

  /**
   * 是否有4张
   */
  private Integer four;

  /**
   * 4张是否有手牌
   */
  private Integer fourmy;

  /**
   * 是否有3张牌顺子（0否1是）
   */
  private Integer longthree;

  /**
   * 顺子中是否有手牌（0否1是）
   */
  private Integer longthreemy;

  /**
   * 是否有4张牌顺子（0否1是）
   */
  private Integer longfour;

  /**
   * 顺子中是否有手牌（0否1是）
   */
  private Integer longfourmy;

  /**
   * 是否有5张牌顺子（0否1是）
   */
  private Integer longfive;

  /**
   * 顺子中是否有手牌（0否1是）
   */
  private Integer longfivemy;

  /**
   * 是否为4张以上同花
   */
  private Integer samefour;

  /**
   * 4张以上同花是否有手牌
   */
  private Integer samefourmy;

  /**
   * 是否为5张同花
   */
  private Integer samefive;

  /**
   * 5张以上同花是否有手牌
   */
  private Integer samefivemy;

  /**
   * 是否是5张同花顺
   */
  private Integer samelong;

  /**
   * 同花顺是否有手牌
   */
  private Integer samelongmy;

  /**
   * 跟注后是否还有剩余筹码
   */
  private Integer showhandmust;

  /**
   * 最终是否获胜
   */
  private Integer win;

  /**
   * 跟注
   */
  private Integer follow;

  /**
   * 加最小注
   */
  private Integer filling;

  /**
   * 梭哈
   */
  private Integer showhand;

  /**
   * 让/放弃
   */
  private Integer giveup;

  /**
   * 操作最小时间
   */
  private Integer timemin;

  /**
   * 操作最大时间
   */
  private Integer timemax;

  /**
   * 备注
   */
  private String remark;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCardsnum() {
    return cardsnum;
  }

  public void setCardsnum(Integer cardsnum) {
    this.cardsnum = cardsnum;
  }

  public Integer getPaired() {
    return paired;
  }

  public void setPaired(Integer paired) {
    this.paired = paired;
  }

  public Integer getPairedmax() {
    return pairedmax;
  }

  public void setPairedmax(Integer pairedmax) {
    this.pairedmax = pairedmax;
  }

  public Integer getPairedmin() {
    return pairedmin;
  }

  public void setPairedmin(Integer pairedmin) {
    this.pairedmin = pairedmin;
  }

  public Integer getPairedmy() {
    return pairedmy;
  }

  public void setPairedmy(Integer pairedmy) {
    this.pairedmy = pairedmy;
  }

  public Integer getTwotwo() {
    return twotwo;
  }

  public void setTwotwo(Integer twotwo) {
    this.twotwo = twotwo;
  }

  public Integer getTwotwomy() {
    return twotwomy;
  }

  public void setTwotwomy(Integer twotwomy) {
    this.twotwomy = twotwomy;
  }

  public Integer getThree() {
    return three;
  }

  public void setThree(Integer three) {
    this.three = three;
  }

  public Integer getThreemax() {
    return threemax;
  }

  public void setThreemax(Integer threemax) {
    this.threemax = threemax;
  }

  public Integer getThreemy() {
    return threemy;
  }

  public void setThreemy(Integer threemy) {
    this.threemy = threemy;
  }

  public Integer getFour() {
    return four;
  }

  public void setFour(Integer four) {
    this.four = four;
  }

  public Integer getFourmy() {
    return fourmy;
  }

  public void setFourmy(Integer fourmy) {
    this.fourmy = fourmy;
  }

  public Integer getLongthree() {
    return longthree;
  }

  public void setLongthree(Integer longthree) {
    this.longthree = longthree;
  }

  public Integer getLongthreemy() {
    return longthreemy;
  }

  public void setLongthreemy(Integer longthreemy) {
    this.longthreemy = longthreemy;
  }

  public Integer getLongfour() {
    return longfour;
  }

  public void setLongfour(Integer longfour) {
    this.longfour = longfour;
  }

  public Integer getLongfourmy() {
    return longfourmy;
  }

  public void setLongfourmy(Integer longfourmy) {
    this.longfourmy = longfourmy;
  }

  public Integer getLongfive() {
    return longfive;
  }

  public void setLongfive(Integer longfive) {
    this.longfive = longfive;
  }

  public Integer getLongfivemy() {
    return longfivemy;
  }

  public void setLongfivemy(Integer longfivemy) {
    this.longfivemy = longfivemy;
  }

  public Integer getSamefour() {
    return samefour;
  }

  public void setSamefour(Integer samefour) {
    this.samefour = samefour;
  }

  public Integer getSamefourmy() {
    return samefourmy;
  }

  public void setSamefourmy(Integer samefourmy) {
    this.samefourmy = samefourmy;
  }

  public Integer getSamefive() {
    return samefive;
  }

  public void setSamefive(Integer samefive) {
    this.samefive = samefive;
  }

  public Integer getSamefivemy() {
    return samefivemy;
  }

  public void setSamefivemy(Integer samefivemy) {
    this.samefivemy = samefivemy;
  }

  public Integer getSamelong() {
    return samelong;
  }

  public void setSamelong(Integer samelong) {
    this.samelong = samelong;
  }

  public Integer getSamelongmy() {
    return samelongmy;
  }

  public void setSamelongmy(Integer samelongmy) {
    this.samelongmy = samelongmy;
  }

  public Integer getShowhandmust() {
    return showhandmust;
  }

  public void setShowhandmust(Integer showhandmust) {
    this.showhandmust = showhandmust;
  }

  public Integer getWin() {
    return win;
  }

  public void setWin(Integer win) {
    this.win = win;
  }

  public Integer getFollow() {
    return follow;
  }

  public void setFollow(Integer follow) {
    this.follow = follow;
  }

  public Integer getFilling() {
    return filling;
  }

  public void setFilling(Integer filling) {
    this.filling = filling;
  }

  public Integer getShowhand() {
    return showhand;
  }

  public void setShowhand(Integer showhand) {
    this.showhand = showhand;
  }

  public Integer getGiveup() {
    return giveup;
  }

  public void setGiveup(Integer giveup) {
    this.giveup = giveup;
  }

  public Integer getTimemin() {
    return timemin;
  }

  public void setTimemin(Integer timemin) {
    this.timemin = timemin;
  }

  public Integer getTimemax() {
    return timemax;
  }

  public void setTimemax(Integer timemax) {
    this.timemax = timemax;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
    TexapokerRobotRules other = (TexapokerRobotRules) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCardsnum() == null ? other.getCardsnum() == null
            : this.getCardsnum().equals(other.getCardsnum()))
        && (this.getPaired() == null ? other.getPaired() == null
            : this.getPaired().equals(other.getPaired()))
        && (this.getPairedmax() == null ? other.getPairedmax() == null
            : this.getPairedmax().equals(other.getPairedmax()))
        && (this.getPairedmin() == null ? other.getPairedmin() == null
            : this.getPairedmin().equals(other.getPairedmin()))
        && (this.getPairedmy() == null ? other.getPairedmy() == null
            : this.getPairedmy().equals(other.getPairedmy()))
        && (this.getTwotwo() == null ? other.getTwotwo() == null
            : this.getTwotwo().equals(other.getTwotwo()))
        && (this.getTwotwomy() == null ? other.getTwotwomy() == null
            : this.getTwotwomy().equals(other.getTwotwomy()))
        && (this.getThree() == null ? other.getThree() == null
            : this.getThree().equals(other.getThree()))
        && (this.getThreemax() == null ? other.getThreemax() == null
            : this.getThreemax().equals(other.getThreemax()))
        && (this.getThreemy() == null ? other.getThreemy() == null
            : this.getThreemy().equals(other.getThreemy()))
        && (this.getFour() == null ? other.getFour() == null
            : this.getFour().equals(other.getFour()))
        && (this.getFourmy() == null ? other.getFourmy() == null
            : this.getFourmy().equals(other.getFourmy()))
        && (this.getLongthree() == null ? other.getLongthree() == null
            : this.getLongthree().equals(other.getLongthree()))
        && (this.getLongthreemy() == null ? other.getLongthreemy() == null
            : this.getLongthreemy().equals(other.getLongthreemy()))
        && (this.getLongfour() == null ? other.getLongfour() == null
            : this.getLongfour().equals(other.getLongfour()))
        && (this.getLongfourmy() == null ? other.getLongfourmy() == null
            : this.getLongfourmy().equals(other.getLongfourmy()))
        && (this.getLongfive() == null ? other.getLongfive() == null
            : this.getLongfive().equals(other.getLongfive()))
        && (this.getLongfivemy() == null ? other.getLongfivemy() == null
            : this.getLongfivemy().equals(other.getLongfivemy()))
        && (this.getSamefour() == null ? other.getSamefour() == null
            : this.getSamefour().equals(other.getSamefour()))
        && (this.getSamefourmy() == null ? other.getSamefourmy() == null
            : this.getSamefourmy().equals(other.getSamefourmy()))
        && (this.getSamefive() == null ? other.getSamefive() == null
            : this.getSamefive().equals(other.getSamefive()))
        && (this.getSamefivemy() == null ? other.getSamefivemy() == null
            : this.getSamefivemy().equals(other.getSamefivemy()))
        && (this.getSamelong() == null ? other.getSamelong() == null
            : this.getSamelong().equals(other.getSamelong()))
        && (this.getSamelongmy() == null ? other.getSamelongmy() == null
            : this.getSamelongmy().equals(other.getSamelongmy()))
        && (this.getShowhandmust() == null ? other.getShowhandmust() == null
            : this.getShowhandmust().equals(other.getShowhandmust()))
        && (this.getWin() == null ? other.getWin() == null : this.getWin().equals(other.getWin()))
        && (this.getFollow() == null ? other.getFollow() == null
            : this.getFollow().equals(other.getFollow()))
        && (this.getFilling() == null ? other.getFilling() == null
            : this.getFilling().equals(other.getFilling()))
        && (this.getShowhand() == null ? other.getShowhand() == null
            : this.getShowhand().equals(other.getShowhand()))
        && (this.getGiveup() == null ? other.getGiveup() == null
            : this.getGiveup().equals(other.getGiveup()))
        && (this.getTimemin() == null ? other.getTimemin() == null
            : this.getTimemin().equals(other.getTimemin()))
        && (this.getTimemax() == null ? other.getTimemax() == null
            : this.getTimemax().equals(other.getTimemax()))
        && (this.getRemark() == null ? other.getRemark() == null
            : this.getRemark().equals(other.getRemark()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getCardsnum() == null) ? 0 : getCardsnum().hashCode());
    result = prime * result + ((getPaired() == null) ? 0 : getPaired().hashCode());
    result = prime * result + ((getPairedmax() == null) ? 0 : getPairedmax().hashCode());
    result = prime * result + ((getPairedmin() == null) ? 0 : getPairedmin().hashCode());
    result = prime * result + ((getPairedmy() == null) ? 0 : getPairedmy().hashCode());
    result = prime * result + ((getTwotwo() == null) ? 0 : getTwotwo().hashCode());
    result = prime * result + ((getTwotwomy() == null) ? 0 : getTwotwomy().hashCode());
    result = prime * result + ((getThree() == null) ? 0 : getThree().hashCode());
    result = prime * result + ((getThreemax() == null) ? 0 : getThreemax().hashCode());
    result = prime * result + ((getThreemy() == null) ? 0 : getThreemy().hashCode());
    result = prime * result + ((getFour() == null) ? 0 : getFour().hashCode());
    result = prime * result + ((getFourmy() == null) ? 0 : getFourmy().hashCode());
    result = prime * result + ((getLongthree() == null) ? 0 : getLongthree().hashCode());
    result = prime * result + ((getLongthreemy() == null) ? 0 : getLongthreemy().hashCode());
    result = prime * result + ((getLongfour() == null) ? 0 : getLongfour().hashCode());
    result = prime * result + ((getLongfourmy() == null) ? 0 : getLongfourmy().hashCode());
    result = prime * result + ((getLongfive() == null) ? 0 : getLongfive().hashCode());
    result = prime * result + ((getLongfivemy() == null) ? 0 : getLongfivemy().hashCode());
    result = prime * result + ((getSamefour() == null) ? 0 : getSamefour().hashCode());
    result = prime * result + ((getSamefourmy() == null) ? 0 : getSamefourmy().hashCode());
    result = prime * result + ((getSamefive() == null) ? 0 : getSamefive().hashCode());
    result = prime * result + ((getSamefivemy() == null) ? 0 : getSamefivemy().hashCode());
    result = prime * result + ((getSamelong() == null) ? 0 : getSamelong().hashCode());
    result = prime * result + ((getSamelongmy() == null) ? 0 : getSamelongmy().hashCode());
    result = prime * result + ((getShowhandmust() == null) ? 0 : getShowhandmust().hashCode());
    result = prime * result + ((getWin() == null) ? 0 : getWin().hashCode());
    result = prime * result + ((getFollow() == null) ? 0 : getFollow().hashCode());
    result = prime * result + ((getFilling() == null) ? 0 : getFilling().hashCode());
    result = prime * result + ((getShowhand() == null) ? 0 : getShowhand().hashCode());
    result = prime * result + ((getGiveup() == null) ? 0 : getGiveup().hashCode());
    result = prime * result + ((getTimemin() == null) ? 0 : getTimemin().hashCode());
    result = prime * result + ((getTimemax() == null) ? 0 : getTimemax().hashCode());
    result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", cardsnum=").append(cardsnum);
    sb.append(", paired=").append(paired);
    sb.append(", pairedmax=").append(pairedmax);
    sb.append(", pairedmin=").append(pairedmin);
    sb.append(", pairedmy=").append(pairedmy);
    sb.append(", twotwo=").append(twotwo);
    sb.append(", twotwomy=").append(twotwomy);
    sb.append(", three=").append(three);
    sb.append(", threemax=").append(threemax);
    sb.append(", threemy=").append(threemy);
    sb.append(", four=").append(four);
    sb.append(", fourmy=").append(fourmy);
    sb.append(", longthree=").append(longthree);
    sb.append(", longthreemy=").append(longthreemy);
    sb.append(", longfour=").append(longfour);
    sb.append(", longfourmy=").append(longfourmy);
    sb.append(", longfive=").append(longfive);
    sb.append(", longfivemy=").append(longfivemy);
    sb.append(", samefour=").append(samefour);
    sb.append(", samefourmy=").append(samefourmy);
    sb.append(", samefive=").append(samefive);
    sb.append(", samefivemy=").append(samefivemy);
    sb.append(", samelong=").append(samelong);
    sb.append(", samelongmy=").append(samelongmy);
    sb.append(", showhandmust=").append(showhandmust);
    sb.append(", win=").append(win);
    sb.append(", follow=").append(follow);
    sb.append(", filling=").append(filling);
    sb.append(", showhand=").append(showhand);
    sb.append(", giveup=").append(giveup);
    sb.append(", timemin=").append(timemin);
    sb.append(", timemax=").append(timemax);
    sb.append(", remark=").append(remark);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
