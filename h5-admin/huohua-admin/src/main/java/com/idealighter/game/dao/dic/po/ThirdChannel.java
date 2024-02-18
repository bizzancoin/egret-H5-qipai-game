package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * third_channel
 * @author 
 */
public class ThirdChannel implements Serializable {
    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道备注
     */
    private String channelRemark;

    /**
     * 渠道des key
     */
    private String channelDesKey;

    /**
     * 渠道md5 key
     */
    private String channelMd5Key;

    private static final long serialVersionUID = 1L;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelRemark() {
        return channelRemark;
    }

    public void setChannelRemark(String channelRemark) {
        this.channelRemark = channelRemark;
    }

    public String getChannelDesKey() {
        return channelDesKey;
    }

    public void setChannelDesKey(String channelDesKey) {
        this.channelDesKey = channelDesKey;
    }

    public String getChannelMd5Key() {
        return channelMd5Key;
    }

    public void setChannelMd5Key(String channelMd5Key) {
        this.channelMd5Key = channelMd5Key;
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
        ThirdChannel other = (ThirdChannel) that;
        return (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()))
            && (this.getChannelName() == null ? other.getChannelName() == null : this.getChannelName().equals(other.getChannelName()))
            && (this.getChannelRemark() == null ? other.getChannelRemark() == null : this.getChannelRemark().equals(other.getChannelRemark()))
            && (this.getChannelDesKey() == null ? other.getChannelDesKey() == null : this.getChannelDesKey().equals(other.getChannelDesKey()))
            && (this.getChannelMd5Key() == null ? other.getChannelMd5Key() == null : this.getChannelMd5Key().equals(other.getChannelMd5Key()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        result = prime * result + ((getChannelName() == null) ? 0 : getChannelName().hashCode());
        result = prime * result + ((getChannelRemark() == null) ? 0 : getChannelRemark().hashCode());
        result = prime * result + ((getChannelDesKey() == null) ? 0 : getChannelDesKey().hashCode());
        result = prime * result + ((getChannelMd5Key() == null) ? 0 : getChannelMd5Key().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", channelId=").append(channelId);
        sb.append(", channelName=").append(channelName);
        sb.append(", channelRemark=").append(channelRemark);
        sb.append(", channelDesKey=").append(channelDesKey);
        sb.append(", channelMd5Key=").append(channelMd5Key);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}