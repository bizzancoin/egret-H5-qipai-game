package com.idealighter.game.dao.dic.po;

import java.io.Serializable;
import java.util.Date;

/**
 * zhajinhua_room
 * @author 
 */
public class ZhajinhuaRoom implements Serializable {
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
    private Integer maxNum;

    /**
     * 空闲状态人数百分比
     */
    private Integer free;

    /**
     * 普通状态人数百分比
     */
    private Integer general;

    /**
     * 拥挤状态人数百分比
     */
    private Integer crowded;

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
     * 默认最低（筹码）
     */
    private Integer minOne;

    /**
     * 默认最高（筹码）最大1kw
     */
    private Integer maxOne;

    /**
     * 台费(进桌扣一次)
     */
    private Integer afee;

    /**
     * 进入类型（0点击入座，1自动分配）
     */
    private Integer inType;

    /**
     * 底注
     */
    private Integer base;

    /**
     * 封顶
     */
    private Integer top;

    /**
     * 筹码1
     */
    private Integer chip1;

    /**
     * 筹码2
     */
    private Integer chip2;

    /**
     * 筹码3
     */
    private Integer chip3;

    /**
     * 客户端展示的属性列信息
     */
    private String displays;

    /**
     * 客户端展示的属性列信息占位符
     */
    private String placeHolder;

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

    public Integer getMinOne() {
        return minOne;
    }

    public void setMinOne(Integer minOne) {
        this.minOne = minOne;
    }

    public Integer getMaxOne() {
        return maxOne;
    }

    public void setMaxOne(Integer maxOne) {
        this.maxOne = maxOne;
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

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getChip1() {
        return chip1;
    }

    public void setChip1(Integer chip1) {
        this.chip1 = chip1;
    }

    public Integer getChip2() {
        return chip2;
    }

    public void setChip2(Integer chip2) {
        this.chip2 = chip2;
    }

    public Integer getChip3() {
        return chip3;
    }

    public void setChip3(Integer chip3) {
        this.chip3 = chip3;
    }

    public String getDisplays() {
        return displays;
    }

    public void setDisplays(String displays) {
        this.displays = displays;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
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
        ZhajinhuaRoom other = (ZhajinhuaRoom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMaxNum() == null ? other.getMaxNum() == null : this.getMaxNum().equals(other.getMaxNum()))
            && (this.getFree() == null ? other.getFree() == null : this.getFree().equals(other.getFree()))
            && (this.getGeneral() == null ? other.getGeneral() == null : this.getGeneral().equals(other.getGeneral()))
            && (this.getCrowded() == null ? other.getCrowded() == null : this.getCrowded().equals(other.getCrowded()))
            && (this.getLower() == null ? other.getLower() == null : this.getLower().equals(other.getLower()))
            && (this.getUpper() == null ? other.getUpper() == null : this.getUpper().equals(other.getUpper()))
            && (this.getOrdinarPeople() == null ? other.getOrdinarPeople() == null : this.getOrdinarPeople().equals(other.getOrdinarPeople()))
            && (this.getProportionGold() == null ? other.getProportionGold() == null : this.getProportionGold().equals(other.getProportionGold()))
            && (this.getProportionChips() == null ? other.getProportionChips() == null : this.getProportionChips().equals(other.getProportionChips()))
            && (this.getTableNum() == null ? other.getTableNum() == null : this.getTableNum().equals(other.getTableNum()))
            && (this.getChair() == null ? other.getChair() == null : this.getChair().equals(other.getChair()))
            && (this.getMinOne() == null ? other.getMinOne() == null : this.getMinOne().equals(other.getMinOne()))
            && (this.getMaxOne() == null ? other.getMaxOne() == null : this.getMaxOne().equals(other.getMaxOne()))
            && (this.getAfee() == null ? other.getAfee() == null : this.getAfee().equals(other.getAfee()))
            && (this.getInType() == null ? other.getInType() == null : this.getInType().equals(other.getInType()))
            && (this.getBase() == null ? other.getBase() == null : this.getBase().equals(other.getBase()))
            && (this.getTop() == null ? other.getTop() == null : this.getTop().equals(other.getTop()))
            && (this.getChip1() == null ? other.getChip1() == null : this.getChip1().equals(other.getChip1()))
            && (this.getChip2() == null ? other.getChip2() == null : this.getChip2().equals(other.getChip2()))
            && (this.getChip3() == null ? other.getChip3() == null : this.getChip3().equals(other.getChip3()))
            && (this.getDisplays() == null ? other.getDisplays() == null : this.getDisplays().equals(other.getDisplays()))
            && (this.getPlaceHolder() == null ? other.getPlaceHolder() == null : this.getPlaceHolder().equals(other.getPlaceHolder()))
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
        result = prime * result + ((getFree() == null) ? 0 : getFree().hashCode());
        result = prime * result + ((getGeneral() == null) ? 0 : getGeneral().hashCode());
        result = prime * result + ((getCrowded() == null) ? 0 : getCrowded().hashCode());
        result = prime * result + ((getLower() == null) ? 0 : getLower().hashCode());
        result = prime * result + ((getUpper() == null) ? 0 : getUpper().hashCode());
        result = prime * result + ((getOrdinarPeople() == null) ? 0 : getOrdinarPeople().hashCode());
        result = prime * result + ((getProportionGold() == null) ? 0 : getProportionGold().hashCode());
        result = prime * result + ((getProportionChips() == null) ? 0 : getProportionChips().hashCode());
        result = prime * result + ((getTableNum() == null) ? 0 : getTableNum().hashCode());
        result = prime * result + ((getChair() == null) ? 0 : getChair().hashCode());
        result = prime * result + ((getMinOne() == null) ? 0 : getMinOne().hashCode());
        result = prime * result + ((getMaxOne() == null) ? 0 : getMaxOne().hashCode());
        result = prime * result + ((getAfee() == null) ? 0 : getAfee().hashCode());
        result = prime * result + ((getInType() == null) ? 0 : getInType().hashCode());
        result = prime * result + ((getBase() == null) ? 0 : getBase().hashCode());
        result = prime * result + ((getTop() == null) ? 0 : getTop().hashCode());
        result = prime * result + ((getChip1() == null) ? 0 : getChip1().hashCode());
        result = prime * result + ((getChip2() == null) ? 0 : getChip2().hashCode());
        result = prime * result + ((getChip3() == null) ? 0 : getChip3().hashCode());
        result = prime * result + ((getDisplays() == null) ? 0 : getDisplays().hashCode());
        result = prime * result + ((getPlaceHolder() == null) ? 0 : getPlaceHolder().hashCode());
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
        sb.append(", free=").append(free);
        sb.append(", general=").append(general);
        sb.append(", crowded=").append(crowded);
        sb.append(", lower=").append(lower);
        sb.append(", upper=").append(upper);
        sb.append(", ordinarPeople=").append(ordinarPeople);
        sb.append(", proportionGold=").append(proportionGold);
        sb.append(", proportionChips=").append(proportionChips);
        sb.append(", tableNum=").append(tableNum);
        sb.append(", chair=").append(chair);
        sb.append(", minOne=").append(minOne);
        sb.append(", maxOne=").append(maxOne);
        sb.append(", afee=").append(afee);
        sb.append(", inType=").append(inType);
        sb.append(", base=").append(base);
        sb.append(", top=").append(top);
        sb.append(", chip1=").append(chip1);
        sb.append(", chip2=").append(chip2);
        sb.append(", chip3=").append(chip3);
        sb.append(", displays=").append(displays);
        sb.append(", placeHolder=").append(placeHolder);
        sb.append(", isActive=").append(isActive);
        sb.append(", timeCreate=").append(timeCreate);
        sb.append(", timeOpen=").append(timeOpen);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}