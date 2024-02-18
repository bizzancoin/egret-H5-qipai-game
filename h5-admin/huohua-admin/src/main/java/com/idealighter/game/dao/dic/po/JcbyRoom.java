package com.idealighter.game.dao.dic.po;

import java.io.Serializable;
import java.util.Date;

/**
 * jcby_room
 * @author 
 */
public class JcbyRoom implements Serializable {
    private Integer id;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 房间类型(jcby_room_type)
     */
    private Integer type;

    /**
     * 房间最大人数
     */
    private Integer maxNum;

    /**
     * 鱼的场景
     */
    private String scences;

    /**
     * 李逵的fishId
     */
    private Integer liKui;

    /**
     * 能量炮出现倍率
     */
    private Integer powerBatteryMultiple;

    /**
     * 能量炮出现概率(万分比)
     */
    private Integer powerBatteryPro;

    /**
     * 能量炮时间(秒)
     */
    private Integer powerBatteryTime;

    /**
     * 进入下限
     */
    private Long lower;

    /**
     * 进入上限
     */
    private Long upper;

    /**
     * 普通玩家进入人数百分比
     */
    private Integer ordinarPeople;

    private Integer robotRatio;

    /**
     * 金币与筹码比例（金币）
     */
    private Integer proportionGold;

    /**
     * 金币与筹码比例（筹码）
     */
    private Integer proportionChips;

    /**
     * 桌子数
     */
    private Integer tableNum;

    /**
     * 每桌椅子数
     */
    private Integer chair;

    /**
     * 台费(进桌扣一次)
     */
    private Integer afee;

    /**
     * 进入类型（0点击入座，1自动分配）
     */
    private Integer inType;

    /**
     * 状态(0:关闭,1:开启)
     */
    private Byte isActive;

    /**
     * 创建时间
     */
    private Date timeCreate;

    /**
     * 开启时间
     */
    private Date timeOpen;

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

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getScences() {
        return scences;
    }

    public void setScences(String scences) {
        this.scences = scences;
    }

    public Integer getLiKui() {
        return liKui;
    }

    public void setLiKui(Integer liKui) {
        this.liKui = liKui;
    }

    public Integer getPowerBatteryMultiple() {
        return powerBatteryMultiple;
    }

    public void setPowerBatteryMultiple(Integer powerBatteryMultiple) {
        this.powerBatteryMultiple = powerBatteryMultiple;
    }

    public Integer getPowerBatteryPro() {
        return powerBatteryPro;
    }

    public void setPowerBatteryPro(Integer powerBatteryPro) {
        this.powerBatteryPro = powerBatteryPro;
    }

    public Integer getPowerBatteryTime() {
        return powerBatteryTime;
    }

    public void setPowerBatteryTime(Integer powerBatteryTime) {
        this.powerBatteryTime = powerBatteryTime;
    }

    public Long getLower() {
        return lower;
    }

    public void setLower(Long lower) {
        this.lower = lower;
    }

    public Long getUpper() {
        return upper;
    }

    public void setUpper(Long upper) {
        this.upper = upper;
    }

    public Integer getOrdinarPeople() {
        return ordinarPeople;
    }

    public void setOrdinarPeople(Integer ordinarPeople) {
        this.ordinarPeople = ordinarPeople;
    }

    public Integer getRobotRatio() {
        return robotRatio;
    }

    public void setRobotRatio(Integer robotRatio) {
        this.robotRatio = robotRatio;
    }

    public Integer getProportionGold() {
        return proportionGold;
    }

    public void setProportionGold(Integer proportionGold) {
        this.proportionGold = proportionGold;
    }

    public Integer getProportionChips() {
        return proportionChips;
    }

    public void setProportionChips(Integer proportionChips) {
        this.proportionChips = proportionChips;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public Integer getChair() {
        return chair;
    }

    public void setChair(Integer chair) {
        this.chair = chair;
    }

    public Integer getAfee() {
        return afee;
    }

    public void setAfee(Integer afee) {
        this.afee = afee;
    }

    public Integer getInType() {
        return inType;
    }

    public void setInType(Integer inType) {
        this.inType = inType;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
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
        JcbyRoom other = (JcbyRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMaxNum() == null ? other.getMaxNum() == null : this.getMaxNum().equals(other.getMaxNum()))
            && (this.getScences() == null ? other.getScences() == null : this.getScences().equals(other.getScences()))
            && (this.getLiKui() == null ? other.getLiKui() == null : this.getLiKui().equals(other.getLiKui()))
            && (this.getPowerBatteryMultiple() == null ? other.getPowerBatteryMultiple() == null : this.getPowerBatteryMultiple().equals(other.getPowerBatteryMultiple()))
            && (this.getPowerBatteryPro() == null ? other.getPowerBatteryPro() == null : this.getPowerBatteryPro().equals(other.getPowerBatteryPro()))
            && (this.getPowerBatteryTime() == null ? other.getPowerBatteryTime() == null : this.getPowerBatteryTime().equals(other.getPowerBatteryTime()))
            && (this.getLower() == null ? other.getLower() == null : this.getLower().equals(other.getLower()))
            && (this.getUpper() == null ? other.getUpper() == null : this.getUpper().equals(other.getUpper()))
            && (this.getOrdinarPeople() == null ? other.getOrdinarPeople() == null : this.getOrdinarPeople().equals(other.getOrdinarPeople()))
            && (this.getRobotRatio() == null ? other.getRobotRatio() == null : this.getRobotRatio().equals(other.getRobotRatio()))
            && (this.getProportionGold() == null ? other.getProportionGold() == null : this.getProportionGold().equals(other.getProportionGold()))
            && (this.getProportionChips() == null ? other.getProportionChips() == null : this.getProportionChips().equals(other.getProportionChips()))
            && (this.getTableNum() == null ? other.getTableNum() == null : this.getTableNum().equals(other.getTableNum()))
            && (this.getChair() == null ? other.getChair() == null : this.getChair().equals(other.getChair()))
            && (this.getAfee() == null ? other.getAfee() == null : this.getAfee().equals(other.getAfee()))
            && (this.getInType() == null ? other.getInType() == null : this.getInType().equals(other.getInType()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getTimeCreate() == null ? other.getTimeCreate() == null : this.getTimeCreate().equals(other.getTimeCreate()))
            && (this.getTimeOpen() == null ? other.getTimeOpen() == null : this.getTimeOpen().equals(other.getTimeOpen()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMaxNum() == null) ? 0 : getMaxNum().hashCode());
        result = prime * result + ((getScences() == null) ? 0 : getScences().hashCode());
        result = prime * result + ((getLiKui() == null) ? 0 : getLiKui().hashCode());
        result = prime * result + ((getPowerBatteryMultiple() == null) ? 0 : getPowerBatteryMultiple().hashCode());
        result = prime * result + ((getPowerBatteryPro() == null) ? 0 : getPowerBatteryPro().hashCode());
        result = prime * result + ((getPowerBatteryTime() == null) ? 0 : getPowerBatteryTime().hashCode());
        result = prime * result + ((getLower() == null) ? 0 : getLower().hashCode());
        result = prime * result + ((getUpper() == null) ? 0 : getUpper().hashCode());
        result = prime * result + ((getOrdinarPeople() == null) ? 0 : getOrdinarPeople().hashCode());
        result = prime * result + ((getRobotRatio() == null) ? 0 : getRobotRatio().hashCode());
        result = prime * result + ((getProportionGold() == null) ? 0 : getProportionGold().hashCode());
        result = prime * result + ((getProportionChips() == null) ? 0 : getProportionChips().hashCode());
        result = prime * result + ((getTableNum() == null) ? 0 : getTableNum().hashCode());
        result = prime * result + ((getChair() == null) ? 0 : getChair().hashCode());
        result = prime * result + ((getAfee() == null) ? 0 : getAfee().hashCode());
        result = prime * result + ((getInType() == null) ? 0 : getInType().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getTimeCreate() == null) ? 0 : getTimeCreate().hashCode());
        result = prime * result + ((getTimeOpen() == null) ? 0 : getTimeOpen().hashCode());
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
        sb.append(", maxNum=").append(maxNum);
        sb.append(", scences=").append(scences);
        sb.append(", liKui=").append(liKui);
        sb.append(", powerBatteryMultiple=").append(powerBatteryMultiple);
        sb.append(", powerBatteryPro=").append(powerBatteryPro);
        sb.append(", powerBatteryTime=").append(powerBatteryTime);
        sb.append(", lower=").append(lower);
        sb.append(", upper=").append(upper);
        sb.append(", ordinarPeople=").append(ordinarPeople);
        sb.append(", robotRatio=").append(robotRatio);
        sb.append(", proportionGold=").append(proportionGold);
        sb.append(", proportionChips=").append(proportionChips);
        sb.append(", tableNum=").append(tableNum);
        sb.append(", chair=").append(chair);
        sb.append(", afee=").append(afee);
        sb.append(", inType=").append(inType);
        sb.append(", isActive=").append(isActive);
        sb.append(", timeCreate=").append(timeCreate);
        sb.append(", timeOpen=").append(timeOpen);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}