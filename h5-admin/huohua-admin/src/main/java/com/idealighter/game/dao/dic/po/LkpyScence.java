package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author 
 */
public class LkpyScence implements Serializable {
    /**
     * 场景id
     */
    private Integer id;

    /**
     * 刷鱼策略
     */
    private String strategys;

    /**
     * 场景存在时间（秒）
     */
    private Integer lifeTime;

    /**
     * 描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrategys() {
        return strategys;
    }

    public void setStrategys(String strategys) {
        this.strategys = strategys;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
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
        LkpyScence other = (LkpyScence) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStrategys() == null ? other.getStrategys() == null : this.getStrategys().equals(other.getStrategys()))
            && (this.getLifeTime() == null ? other.getLifeTime() == null : this.getLifeTime().equals(other.getLifeTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStrategys() == null) ? 0 : getStrategys().hashCode());
        result = prime * result + ((getLifeTime() == null) ? 0 : getLifeTime().hashCode());
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
        sb.append(", strategys=").append(strategys);
        sb.append(", lifeTime=").append(lifeTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}