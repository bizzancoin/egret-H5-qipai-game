package com.idealighter.game.dao.manage.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Notice implements Serializable {
    private Integer id;

    private Date startTime;

    private Date endTime;

    private Boolean isMarquee;

    private Boolean isNotice;

    /**
     * 间隔时间(秒)
     */
    private Integer intervalTime;

    private String content;

    private Integer times;

    private String color;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsMarquee() {
        return isMarquee;
    }

    public void setIsMarquee(Boolean isMarquee) {
        this.isMarquee = isMarquee;
    }

    public Boolean getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Boolean isNotice) {
        this.isNotice = isNotice;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        Notice other = (Notice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getIsMarquee() == null ? other.getIsMarquee() == null : this.getIsMarquee().equals(other.getIsMarquee()))
            && (this.getIsNotice() == null ? other.getIsNotice() == null : this.getIsNotice().equals(other.getIsNotice()))
            && (this.getIntervalTime() == null ? other.getIntervalTime() == null : this.getIntervalTime().equals(other.getIntervalTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getIsMarquee() == null) ? 0 : getIsMarquee().hashCode());
        result = prime * result + ((getIsNotice() == null) ? 0 : getIsNotice().hashCode());
        result = prime * result + ((getIntervalTime() == null) ? 0 : getIntervalTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", isMarquee=").append(isMarquee);
        sb.append(", isNotice=").append(isNotice);
        sb.append(", intervalTime=").append(intervalTime);
        sb.append(", content=").append(content);
        sb.append(", times=").append(times);
        sb.append(", color=").append(color);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}