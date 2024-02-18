package com.idealighter.game.dao.dic.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class HappyfiveRoom implements Serializable {
  private Integer id;

  /**
   * 房间名称
   */
  private String name;

  /**
   * 房间类型(errenniuniu_room_type)
   */
  private Integer type;

  /**
   * 房间最大人数
   */
  private Integer maxnum;

  /**
   * 空闲状态人数
   */
  private Integer free;

  /**
   * 普通状态人数
   */
  private Integer general;

  /**
   * 拥挤状态人数
   */
  private Integer crowded;

  /**
   * 进入下限
   */
  private Integer lower;

  /**
   * 进入上限
   */
  private Integer upper;

  /**
   * 普通玩家进入人数百分比
   */
  private Integer ordinarpeople;

  /**
   * 金币与筹码比例（金币）
   */
  private Integer proportiongold;

  /**
   * 金币与筹码比例（筹码）
   */
  private Integer proportionchips;

  /**
   * 桌子数
   */
  private Integer table;

  /**
   * 每桌椅子数
   */
  private Integer chair;

  /**
   * 单局上限（筹码）暂时没用
   */
  private Integer top;

  /**
   * 默认最低（筹码）
   */
  private Integer minone;

  /**
   * 默认最高（筹码）
   */
  private Integer maxone;

  /**
   * 台费(进桌扣一次)
   */
  private Integer afee;

  /**
   * 进入类型（0点击入座，1自动分配）
   */
  private Integer intype;

  /**
   * 客户端展示的属性列信息
   */
  private String displays;

  /**
   * 客户端展示的属性列信息占位符
   */
  private String placeholder;

  /**
   * 状态(0:关闭,1:开启)
   */
  private Byte isactive;

  /**
   * 创建时间
   */
  private Date timecreate;

  /**
   * 开启时间
   */
  private Date timeopen;

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

  public Integer getMaxnum() {
    return maxnum;
  }

  public void setMaxnum(Integer maxnum) {
    this.maxnum = maxnum;
  }

  public Integer getFree() {
    return free;
  }

  public void setFree(Integer free) {
    this.free = free;
  }

  public Integer getGeneral() {
    return general;
  }

  public void setGeneral(Integer general) {
    this.general = general;
  }

  public Integer getCrowded() {
    return crowded;
  }

  public void setCrowded(Integer crowded) {
    this.crowded = crowded;
  }

  public Integer getLower() {
    return lower;
  }

  public void setLower(Integer lower) {
    this.lower = lower;
  }

  public Integer getUpper() {
    return upper;
  }

  public void setUpper(Integer upper) {
    this.upper = upper;
  }

  public Integer getOrdinarpeople() {
    return ordinarpeople;
  }

  public void setOrdinarpeople(Integer ordinarpeople) {
    this.ordinarpeople = ordinarpeople;
  }

  public Integer getProportiongold() {
    return proportiongold;
  }

  public void setProportiongold(Integer proportiongold) {
    this.proportiongold = proportiongold;
  }

  public Integer getProportionchips() {
    return proportionchips;
  }

  public void setProportionchips(Integer proportionchips) {
    this.proportionchips = proportionchips;
  }

  public Integer getTable() {
    return table;
  }

  public void setTable(Integer table) {
    this.table = table;
  }

  public Integer getChair() {
    return chair;
  }

  public void setChair(Integer chair) {
    this.chair = chair;
  }

  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
  }

  public Integer getMinone() {
    return minone;
  }

  public void setMinone(Integer minone) {
    this.minone = minone;
  }

  public Integer getMaxone() {
    return maxone;
  }

  public void setMaxone(Integer maxone) {
    this.maxone = maxone;
  }

  public Integer getAfee() {
    return afee;
  }

  public void setAfee(Integer afee) {
    this.afee = afee;
  }

  public Integer getIntype() {
    return intype;
  }

  public void setIntype(Integer intype) {
    this.intype = intype;
  }

  public String getDisplays() {
    return displays;
  }

  public void setDisplays(String displays) {
    this.displays = displays;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public Byte getIsactive() {
    return isactive;
  }

  public void setIsactive(Byte isactive) {
    this.isactive = isactive;
  }

  public Date getTimecreate() {
    return timecreate;
  }

  public void setTimecreate(Date timecreate) {
    this.timecreate = timecreate;
  }

  public Date getTimeopen() {
    return timeopen;
  }

  public void setTimeopen(Date timeopen) {
    this.timeopen = timeopen;
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
    HappyfiveRoom other = (HappyfiveRoom) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getName() == null ? other.getName() == null
            : this.getName().equals(other.getName()))
        && (this.getType() == null ? other.getType() == null
            : this.getType().equals(other.getType()))
        && (this.getMaxnum() == null ? other.getMaxnum() == null
            : this.getMaxnum().equals(other.getMaxnum()))
        && (this.getFree() == null ? other.getFree() == null
            : this.getFree().equals(other.getFree()))
        && (this.getGeneral() == null ? other.getGeneral() == null
            : this.getGeneral().equals(other.getGeneral()))
        && (this.getCrowded() == null ? other.getCrowded() == null
            : this.getCrowded().equals(other.getCrowded()))
        && (this.getLower() == null ? other.getLower() == null
            : this.getLower().equals(other.getLower()))
        && (this.getUpper() == null ? other.getUpper() == null
            : this.getUpper().equals(other.getUpper()))
        && (this.getOrdinarpeople() == null ? other.getOrdinarpeople() == null
            : this.getOrdinarpeople().equals(other.getOrdinarpeople()))
        && (this.getProportiongold() == null ? other.getProportiongold() == null
            : this.getProportiongold().equals(other.getProportiongold()))
        && (this.getProportionchips() == null ? other.getProportionchips() == null
            : this.getProportionchips().equals(other.getProportionchips()))
        && (this.getTable() == null ? other.getTable() == null
            : this.getTable().equals(other.getTable()))
        && (this.getChair() == null ? other.getChair() == null
            : this.getChair().equals(other.getChair()))
        && (this.getTop() == null ? other.getTop() == null : this.getTop().equals(other.getTop()))
        && (this.getMinone() == null ? other.getMinone() == null
            : this.getMinone().equals(other.getMinone()))
        && (this.getMaxone() == null ? other.getMaxone() == null
            : this.getMaxone().equals(other.getMaxone()))
        && (this.getAfee() == null ? other.getAfee() == null
            : this.getAfee().equals(other.getAfee()))
        && (this.getIntype() == null ? other.getIntype() == null
            : this.getIntype().equals(other.getIntype()))
        && (this.getDisplays() == null ? other.getDisplays() == null
            : this.getDisplays().equals(other.getDisplays()))
        && (this.getPlaceholder() == null ? other.getPlaceholder() == null
            : this.getPlaceholder().equals(other.getPlaceholder()))
        && (this.getIsactive() == null ? other.getIsactive() == null
            : this.getIsactive().equals(other.getIsactive()))
        && (this.getTimecreate() == null ? other.getTimecreate() == null
            : this.getTimecreate().equals(other.getTimecreate()))
        && (this.getTimeopen() == null ? other.getTimeopen() == null
            : this.getTimeopen().equals(other.getTimeopen()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
    result = prime * result + ((getMaxnum() == null) ? 0 : getMaxnum().hashCode());
    result = prime * result + ((getFree() == null) ? 0 : getFree().hashCode());
    result = prime * result + ((getGeneral() == null) ? 0 : getGeneral().hashCode());
    result = prime * result + ((getCrowded() == null) ? 0 : getCrowded().hashCode());
    result = prime * result + ((getLower() == null) ? 0 : getLower().hashCode());
    result = prime * result + ((getUpper() == null) ? 0 : getUpper().hashCode());
    result = prime * result + ((getOrdinarpeople() == null) ? 0 : getOrdinarpeople().hashCode());
    result = prime * result + ((getProportiongold() == null) ? 0 : getProportiongold().hashCode());
    result =
        prime * result + ((getProportionchips() == null) ? 0 : getProportionchips().hashCode());
    result = prime * result + ((getTable() == null) ? 0 : getTable().hashCode());
    result = prime * result + ((getChair() == null) ? 0 : getChair().hashCode());
    result = prime * result + ((getTop() == null) ? 0 : getTop().hashCode());
    result = prime * result + ((getMinone() == null) ? 0 : getMinone().hashCode());
    result = prime * result + ((getMaxone() == null) ? 0 : getMaxone().hashCode());
    result = prime * result + ((getAfee() == null) ? 0 : getAfee().hashCode());
    result = prime * result + ((getIntype() == null) ? 0 : getIntype().hashCode());
    result = prime * result + ((getDisplays() == null) ? 0 : getDisplays().hashCode());
    result = prime * result + ((getPlaceholder() == null) ? 0 : getPlaceholder().hashCode());
    result = prime * result + ((getIsactive() == null) ? 0 : getIsactive().hashCode());
    result = prime * result + ((getTimecreate() == null) ? 0 : getTimecreate().hashCode());
    result = prime * result + ((getTimeopen() == null) ? 0 : getTimeopen().hashCode());
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
    sb.append(", maxnum=").append(maxnum);
    sb.append(", free=").append(free);
    sb.append(", general=").append(general);
    sb.append(", crowded=").append(crowded);
    sb.append(", lower=").append(lower);
    sb.append(", upper=").append(upper);
    sb.append(", ordinarpeople=").append(ordinarpeople);
    sb.append(", proportiongold=").append(proportiongold);
    sb.append(", proportionchips=").append(proportionchips);
    sb.append(", table=").append(table);
    sb.append(", chair=").append(chair);
    sb.append(", top=").append(top);
    sb.append(", minone=").append(minone);
    sb.append(", maxone=").append(maxone);
    sb.append(", afee=").append(afee);
    sb.append(", intype=").append(intype);
    sb.append(", displays=").append(displays);
    sb.append(", placeholder=").append(placeholder);
    sb.append(", isactive=").append(isactive);
    sb.append(", timecreate=").append(timecreate);
    sb.append(", timeopen=").append(timeopen);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
