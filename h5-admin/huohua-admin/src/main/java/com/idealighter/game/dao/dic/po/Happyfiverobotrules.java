package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class Happyfiverobotrules implements Serializable {
  /**
   * 策略ID
   */
  private Integer id;

  /**
   * 发牌轮数
   */
  private Integer cardsnum;

  /**
   * 下注次数
   */
  private Integer times;

  /**
   * 是否先说话（牌面最大）
   */
  private Integer first;

  /**
   * 是否有最大牌值的牌
   */
  private Integer biggest;

  /**
   * 自己是否有对子（0否1是）
   */
  private Integer pairedmy;

  /**
   * 对子是否是最大牌值（0否1是）
   */
  private Integer pairedmax;

  /**
   * 对子是否是最小牌值（0否1是）
   */
  private Integer pairedmin;

  /**
   * 其他人明牌中是否有对子（0否1是）
   */
  private Integer pairedothers;

  /**
   * 是否有两对
   */
  private Integer twotwo;

  /**
   * 其他人是否有两对
   */
  private Integer twotwoothers;

  /**
   * 是否有三条
   */
  private Integer three;

  /**
   * 其他人是否有三条
   */
  private Integer threeothers;

  /**
   * 是否有4张牌顺子（0否1是）
   */
  private Integer longfour;

  /**
   * 别人是否为4张以上顺子
   */
  private Integer longfourothers;

  /**
   * 是否为4张以上同花
   */
  private Integer samefour;

  /**
   * 别人是否为4张以上同花
   */
  private Integer samefourothers;

  /**
   * 手牌+明牌是否输对方明牌
   */
  private Integer win1;

  /**
   * 是否有人梭哈
   */
  private Integer showhandothers;

  /**
   * 最终是否获胜
   */
  private Integer win2;

  /**
   * 跟
   */
  private Integer follow;

  /**
   * 加注1
   */
  private Integer filling1;

  /**
   * 加注2
   */
  private Integer filling2;

  /**
   * 加注3
   */
  private Integer filling3;

  /**
   * 梭哈
   */
  private Integer showhand;

  /**
   * 放弃
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

  public Integer getTimes() {
    return times;
  }

  public void setTimes(Integer times) {
    this.times = times;
  }

  public Integer getFirst() {
    return first;
  }

  public void setFirst(Integer first) {
    this.first = first;
  }

  public Integer getBiggest() {
    return biggest;
  }

  public void setBiggest(Integer biggest) {
    this.biggest = biggest;
  }

  public Integer getPairedmy() {
    return pairedmy;
  }

  public void setPairedmy(Integer pairedmy) {
    this.pairedmy = pairedmy;
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

  public Integer getPairedothers() {
    return pairedothers;
  }

  public void setPairedothers(Integer pairedothers) {
    this.pairedothers = pairedothers;
  }

  public Integer getTwotwo() {
    return twotwo;
  }

  public void setTwotwo(Integer twotwo) {
    this.twotwo = twotwo;
  }

  public Integer getTwotwoothers() {
    return twotwoothers;
  }

  public void setTwotwoothers(Integer twotwoothers) {
    this.twotwoothers = twotwoothers;
  }

  public Integer getThree() {
    return three;
  }

  public void setThree(Integer three) {
    this.three = three;
  }

  public Integer getThreeothers() {
    return threeothers;
  }

  public void setThreeothers(Integer threeothers) {
    this.threeothers = threeothers;
  }

  public Integer getLongfour() {
    return longfour;
  }

  public void setLongfour(Integer longfour) {
    this.longfour = longfour;
  }

  public Integer getLongfourothers() {
    return longfourothers;
  }

  public void setLongfourothers(Integer longfourothers) {
    this.longfourothers = longfourothers;
  }

  public Integer getSamefour() {
    return samefour;
  }

  public void setSamefour(Integer samefour) {
    this.samefour = samefour;
  }

  public Integer getSamefourothers() {
    return samefourothers;
  }

  public void setSamefourothers(Integer samefourothers) {
    this.samefourothers = samefourothers;
  }

  public Integer getWin1() {
    return win1;
  }

  public void setWin1(Integer win1) {
    this.win1 = win1;
  }

  public Integer getShowhandothers() {
    return showhandothers;
  }

  public void setShowhandothers(Integer showhandothers) {
    this.showhandothers = showhandothers;
  }

  public Integer getWin2() {
    return win2;
  }

  public void setWin2(Integer win2) {
    this.win2 = win2;
  }

  public Integer getFollow() {
    return follow;
  }

  public void setFollow(Integer follow) {
    this.follow = follow;
  }

  public Integer getFilling1() {
    return filling1;
  }

  public void setFilling1(Integer filling1) {
    this.filling1 = filling1;
  }

  public Integer getFilling2() {
    return filling2;
  }

  public void setFilling2(Integer filling2) {
    this.filling2 = filling2;
  }

  public Integer getFilling3() {
    return filling3;
  }

  public void setFilling3(Integer filling3) {
    this.filling3 = filling3;
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
    Happyfiverobotrules other = (Happyfiverobotrules) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCardsnum() == null ? other.getCardsnum() == null
            : this.getCardsnum().equals(other.getCardsnum()))
        && (this.getTimes() == null ? other.getTimes() == null
            : this.getTimes().equals(other.getTimes()))
        && (this.getFirst() == null ? other.getFirst() == null
            : this.getFirst().equals(other.getFirst()))
        && (this.getBiggest() == null ? other.getBiggest() == null
            : this.getBiggest().equals(other.getBiggest()))
        && (this.getPairedmy() == null ? other.getPairedmy() == null
            : this.getPairedmy().equals(other.getPairedmy()))
        && (this.getPairedmax() == null ? other.getPairedmax() == null
            : this.getPairedmax().equals(other.getPairedmax()))
        && (this.getPairedmin() == null ? other.getPairedmin() == null
            : this.getPairedmin().equals(other.getPairedmin()))
        && (this.getPairedothers() == null ? other.getPairedothers() == null
            : this.getPairedothers().equals(other.getPairedothers()))
        && (this.getTwotwo() == null ? other.getTwotwo() == null
            : this.getTwotwo().equals(other.getTwotwo()))
        && (this.getTwotwoothers() == null ? other.getTwotwoothers() == null
            : this.getTwotwoothers().equals(other.getTwotwoothers()))
        && (this.getThree() == null ? other.getThree() == null
            : this.getThree().equals(other.getThree()))
        && (this.getThreeothers() == null ? other.getThreeothers() == null
            : this.getThreeothers().equals(other.getThreeothers()))
        && (this.getLongfour() == null ? other.getLongfour() == null
            : this.getLongfour().equals(other.getLongfour()))
        && (this.getLongfourothers() == null ? other.getLongfourothers() == null
            : this.getLongfourothers().equals(other.getLongfourothers()))
        && (this.getSamefour() == null ? other.getSamefour() == null
            : this.getSamefour().equals(other.getSamefour()))
        && (this.getSamefourothers() == null ? other.getSamefourothers() == null
            : this.getSamefourothers().equals(other.getSamefourothers()))
        && (this.getWin1() == null ? other.getWin1() == null
            : this.getWin1().equals(other.getWin1()))
        && (this.getShowhandothers() == null ? other.getShowhandothers() == null
            : this.getShowhandothers().equals(other.getShowhandothers()))
        && (this.getWin2() == null ? other.getWin2() == null
            : this.getWin2().equals(other.getWin2()))
        && (this.getFollow() == null ? other.getFollow() == null
            : this.getFollow().equals(other.getFollow()))
        && (this.getFilling1() == null ? other.getFilling1() == null
            : this.getFilling1().equals(other.getFilling1()))
        && (this.getFilling2() == null ? other.getFilling2() == null
            : this.getFilling2().equals(other.getFilling2()))
        && (this.getFilling3() == null ? other.getFilling3() == null
            : this.getFilling3().equals(other.getFilling3()))
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
    result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
    result = prime * result + ((getFirst() == null) ? 0 : getFirst().hashCode());
    result = prime * result + ((getBiggest() == null) ? 0 : getBiggest().hashCode());
    result = prime * result + ((getPairedmy() == null) ? 0 : getPairedmy().hashCode());
    result = prime * result + ((getPairedmax() == null) ? 0 : getPairedmax().hashCode());
    result = prime * result + ((getPairedmin() == null) ? 0 : getPairedmin().hashCode());
    result = prime * result + ((getPairedothers() == null) ? 0 : getPairedothers().hashCode());
    result = prime * result + ((getTwotwo() == null) ? 0 : getTwotwo().hashCode());
    result = prime * result + ((getTwotwoothers() == null) ? 0 : getTwotwoothers().hashCode());
    result = prime * result + ((getThree() == null) ? 0 : getThree().hashCode());
    result = prime * result + ((getThreeothers() == null) ? 0 : getThreeothers().hashCode());
    result = prime * result + ((getLongfour() == null) ? 0 : getLongfour().hashCode());
    result = prime * result + ((getLongfourothers() == null) ? 0 : getLongfourothers().hashCode());
    result = prime * result + ((getSamefour() == null) ? 0 : getSamefour().hashCode());
    result = prime * result + ((getSamefourothers() == null) ? 0 : getSamefourothers().hashCode());
    result = prime * result + ((getWin1() == null) ? 0 : getWin1().hashCode());
    result = prime * result + ((getShowhandothers() == null) ? 0 : getShowhandothers().hashCode());
    result = prime * result + ((getWin2() == null) ? 0 : getWin2().hashCode());
    result = prime * result + ((getFollow() == null) ? 0 : getFollow().hashCode());
    result = prime * result + ((getFilling1() == null) ? 0 : getFilling1().hashCode());
    result = prime * result + ((getFilling2() == null) ? 0 : getFilling2().hashCode());
    result = prime * result + ((getFilling3() == null) ? 0 : getFilling3().hashCode());
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
    sb.append(", times=").append(times);
    sb.append(", first=").append(first);
    sb.append(", biggest=").append(biggest);
    sb.append(", pairedmy=").append(pairedmy);
    sb.append(", pairedmax=").append(pairedmax);
    sb.append(", pairedmin=").append(pairedmin);
    sb.append(", pairedothers=").append(pairedothers);
    sb.append(", twotwo=").append(twotwo);
    sb.append(", twotwoothers=").append(twotwoothers);
    sb.append(", three=").append(three);
    sb.append(", threeothers=").append(threeothers);
    sb.append(", longfour=").append(longfour);
    sb.append(", longfourothers=").append(longfourothers);
    sb.append(", samefour=").append(samefour);
    sb.append(", samefourothers=").append(samefourothers);
    sb.append(", win1=").append(win1);
    sb.append(", showhandothers=").append(showhandothers);
    sb.append(", win2=").append(win2);
    sb.append(", follow=").append(follow);
    sb.append(", filling1=").append(filling1);
    sb.append(", filling2=").append(filling2);
    sb.append(", filling3=").append(filling3);
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
