package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * transfer_gold_record
 * @author 
 */
public class TransferGoldRecord implements Serializable {
    private Integer id;

    /**
     * 1转出 2转入
     */
    private Byte type;

    /**
     * 对方类型（0普通用户1代理）
     */
    private Byte oppositeType;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 玩家靓号
     */
    private Long playerSuperId;

    private String playerName;

    /**
     * 对方玩家Id
     */
    private Long oppositePlayerId;

    /**
     * 对方靓号Id
     */
    private Long oppositeSuperId;

    private String oppositePlayerName;

    /**
     * 发送前银行金币
     */
    private Long beforeSafeGold;

    /**
     * 发送后银行金币
     */
    private Long afterSafeGold;

    /**
     * 手续费
     */
    private Long fee;

    /**
     * 费率
     */
    private Integer feeRate;

    /**
     * 变化的银行金币
     */
    private Long changeSafeGold;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 自己类型（0普通用户1代理）
     */
    private Byte playerType;

    private String channelId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getOppositeType() {
        return oppositeType;
    }

    public void setOppositeType(Byte oppositeType) {
        this.oppositeType = oppositeType;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerSuperId() {
        return playerSuperId;
    }

    public void setPlayerSuperId(Long playerSuperId) {
        this.playerSuperId = playerSuperId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getOppositePlayerId() {
        return oppositePlayerId;
    }

    public void setOppositePlayerId(Long oppositePlayerId) {
        this.oppositePlayerId = oppositePlayerId;
    }

    public Long getOppositeSuperId() {
        return oppositeSuperId;
    }

    public void setOppositeSuperId(Long oppositeSuperId) {
        this.oppositeSuperId = oppositeSuperId;
    }

    public String getOppositePlayerName() {
        return oppositePlayerName;
    }

    public void setOppositePlayerName(String oppositePlayerName) {
        this.oppositePlayerName = oppositePlayerName;
    }

    public Long getBeforeSafeGold() {
        return beforeSafeGold;
    }

    public void setBeforeSafeGold(Long beforeSafeGold) {
        this.beforeSafeGold = beforeSafeGold;
    }

    public Long getAfterSafeGold() {
        return afterSafeGold;
    }

    public void setAfterSafeGold(Long afterSafeGold) {
        this.afterSafeGold = afterSafeGold;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Integer getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Integer feeRate) {
        this.feeRate = feeRate;
    }

    public Long getChangeSafeGold() {
        return changeSafeGold;
    }

    public void setChangeSafeGold(Long changeSafeGold) {
        this.changeSafeGold = changeSafeGold;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Byte playerType) {
        this.playerType = playerType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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
        TransferGoldRecord other = (TransferGoldRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getOppositeType() == null ? other.getOppositeType() == null : this.getOppositeType().equals(other.getOppositeType()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getPlayerSuperId() == null ? other.getPlayerSuperId() == null : this.getPlayerSuperId().equals(other.getPlayerSuperId()))
            && (this.getPlayerName() == null ? other.getPlayerName() == null : this.getPlayerName().equals(other.getPlayerName()))
            && (this.getOppositePlayerId() == null ? other.getOppositePlayerId() == null : this.getOppositePlayerId().equals(other.getOppositePlayerId()))
            && (this.getOppositeSuperId() == null ? other.getOppositeSuperId() == null : this.getOppositeSuperId().equals(other.getOppositeSuperId()))
            && (this.getOppositePlayerName() == null ? other.getOppositePlayerName() == null : this.getOppositePlayerName().equals(other.getOppositePlayerName()))
            && (this.getBeforeSafeGold() == null ? other.getBeforeSafeGold() == null : this.getBeforeSafeGold().equals(other.getBeforeSafeGold()))
            && (this.getAfterSafeGold() == null ? other.getAfterSafeGold() == null : this.getAfterSafeGold().equals(other.getAfterSafeGold()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getFeeRate() == null ? other.getFeeRate() == null : this.getFeeRate().equals(other.getFeeRate()))
            && (this.getChangeSafeGold() == null ? other.getChangeSafeGold() == null : this.getChangeSafeGold().equals(other.getChangeSafeGold()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPlayerType() == null ? other.getPlayerType() == null : this.getPlayerType().equals(other.getPlayerType()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOppositeType() == null) ? 0 : getOppositeType().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getPlayerSuperId() == null) ? 0 : getPlayerSuperId().hashCode());
        result = prime * result + ((getPlayerName() == null) ? 0 : getPlayerName().hashCode());
        result = prime * result + ((getOppositePlayerId() == null) ? 0 : getOppositePlayerId().hashCode());
        result = prime * result + ((getOppositeSuperId() == null) ? 0 : getOppositeSuperId().hashCode());
        result = prime * result + ((getOppositePlayerName() == null) ? 0 : getOppositePlayerName().hashCode());
        result = prime * result + ((getBeforeSafeGold() == null) ? 0 : getBeforeSafeGold().hashCode());
        result = prime * result + ((getAfterSafeGold() == null) ? 0 : getAfterSafeGold().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getFeeRate() == null) ? 0 : getFeeRate().hashCode());
        result = prime * result + ((getChangeSafeGold() == null) ? 0 : getChangeSafeGold().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPlayerType() == null) ? 0 : getPlayerType().hashCode());
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", oppositeType=").append(oppositeType);
        sb.append(", playerId=").append(playerId);
        sb.append(", playerSuperId=").append(playerSuperId);
        sb.append(", playerName=").append(playerName);
        sb.append(", oppositePlayerId=").append(oppositePlayerId);
        sb.append(", oppositeSuperId=").append(oppositeSuperId);
        sb.append(", oppositePlayerName=").append(oppositePlayerName);
        sb.append(", beforeSafeGold=").append(beforeSafeGold);
        sb.append(", afterSafeGold=").append(afterSafeGold);
        sb.append(", fee=").append(fee);
        sb.append(", feeRate=").append(feeRate);
        sb.append(", changeSafeGold=").append(changeSafeGold);
        sb.append(", createTime=").append(createTime);
        sb.append(", playerType=").append(playerType);
        sb.append(", channelId=").append(channelId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}