package com.idealighter.game.dao.data.po;

import java.io.Serializable;

/**
 * @author 
 */
public class PlayerVip implements Serializable {
    /**
     * 玩家id
     */
    private Integer id;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 播放道具特效
     */
    private Boolean playItemEffect;

    /**
     * vip设置的其他玩家进桌金币限制
     */
    private Long tableLimitGold;

    /**
     * vip设置的其他玩家进桌金币限制是否有效
     */
    private Boolean tableLimitGoldAbled;

    /**
     * vip设置的是否允许同自己ipy一样的玩家进入桌子
     */
    private Boolean tableLimitIp;

    /**
     * vip设置的桌子密码
     */
    private String tablePwd;

    /**
     * vip设置的桌子密码是否有效
     */
    private Boolean tablePwdAbled;

    /**
     * vip房间设置是否有效
     */
    private Boolean roomSettingAbled;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Boolean getPlayItemEffect() {
        return playItemEffect;
    }

    public void setPlayItemEffect(Boolean playItemEffect) {
        this.playItemEffect = playItemEffect;
    }

    public Long getTableLimitGold() {
        return tableLimitGold;
    }

    public void setTableLimitGold(Long tableLimitGold) {
        this.tableLimitGold = tableLimitGold;
    }

    public Boolean getTableLimitGoldAbled() {
        return tableLimitGoldAbled;
    }

    public void setTableLimitGoldAbled(Boolean tableLimitGoldAbled) {
        this.tableLimitGoldAbled = tableLimitGoldAbled;
    }

    public Boolean getTableLimitIp() {
        return tableLimitIp;
    }

    public void setTableLimitIp(Boolean tableLimitIp) {
        this.tableLimitIp = tableLimitIp;
    }

    public String getTablePwd() {
        return tablePwd;
    }

    public void setTablePwd(String tablePwd) {
        this.tablePwd = tablePwd;
    }

    public Boolean getTablePwdAbled() {
        return tablePwdAbled;
    }

    public void setTablePwdAbled(Boolean tablePwdAbled) {
        this.tablePwdAbled = tablePwdAbled;
    }

    public Boolean getRoomSettingAbled() {
        return roomSettingAbled;
    }

    public void setRoomSettingAbled(Boolean roomSettingAbled) {
        this.roomSettingAbled = roomSettingAbled;
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
        PlayerVip other = (PlayerVip) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getPlayItemEffect() == null ? other.getPlayItemEffect() == null : this.getPlayItemEffect().equals(other.getPlayItemEffect()))
            && (this.getTableLimitGold() == null ? other.getTableLimitGold() == null : this.getTableLimitGold().equals(other.getTableLimitGold()))
            && (this.getTableLimitGoldAbled() == null ? other.getTableLimitGoldAbled() == null : this.getTableLimitGoldAbled().equals(other.getTableLimitGoldAbled()))
            && (this.getTableLimitIp() == null ? other.getTableLimitIp() == null : this.getTableLimitIp().equals(other.getTableLimitIp()))
            && (this.getTablePwd() == null ? other.getTablePwd() == null : this.getTablePwd().equals(other.getTablePwd()))
            && (this.getTablePwdAbled() == null ? other.getTablePwdAbled() == null : this.getTablePwdAbled().equals(other.getTablePwdAbled()))
            && (this.getRoomSettingAbled() == null ? other.getRoomSettingAbled() == null : this.getRoomSettingAbled().equals(other.getRoomSettingAbled()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getPlayItemEffect() == null) ? 0 : getPlayItemEffect().hashCode());
        result = prime * result + ((getTableLimitGold() == null) ? 0 : getTableLimitGold().hashCode());
        result = prime * result + ((getTableLimitGoldAbled() == null) ? 0 : getTableLimitGoldAbled().hashCode());
        result = prime * result + ((getTableLimitIp() == null) ? 0 : getTableLimitIp().hashCode());
        result = prime * result + ((getTablePwd() == null) ? 0 : getTablePwd().hashCode());
        result = prime * result + ((getTablePwdAbled() == null) ? 0 : getTablePwdAbled().hashCode());
        result = prime * result + ((getRoomSettingAbled() == null) ? 0 : getRoomSettingAbled().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", playerId=").append(playerId);
        sb.append(", playItemEffect=").append(playItemEffect);
        sb.append(", tableLimitGold=").append(tableLimitGold);
        sb.append(", tableLimitGoldAbled=").append(tableLimitGoldAbled);
        sb.append(", tableLimitIp=").append(tableLimitIp);
        sb.append(", tablePwd=").append(tablePwd);
        sb.append(", tablePwdAbled=").append(tablePwdAbled);
        sb.append(", roomSettingAbled=").append(roomSettingAbled);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}