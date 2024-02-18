package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author 
 */
public class FruitslotBonusNumCfg implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 橘子数量(3-5:为单线)
     */
    private Byte orangenum;

    /**
     * bonus game次数
     */
    private Byte bonusnum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getOrangenum() {
        return orangenum;
    }

    public void setOrangenum(Byte orangenum) {
        this.orangenum = orangenum;
    }

    public Byte getBonusnum() {
        return bonusnum;
    }

    public void setBonusnum(Byte bonusnum) {
        this.bonusnum = bonusnum;
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
        FruitslotBonusNumCfg other = (FruitslotBonusNumCfg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrangenum() == null ? other.getOrangenum() == null : this.getOrangenum().equals(other.getOrangenum()))
            && (this.getBonusnum() == null ? other.getBonusnum() == null : this.getBonusnum().equals(other.getBonusnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrangenum() == null) ? 0 : getOrangenum().hashCode());
        result = prime * result + ((getBonusnum() == null) ? 0 : getBonusnum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orangenum=").append(orangenum);
        sb.append(", bonusnum=").append(bonusnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}