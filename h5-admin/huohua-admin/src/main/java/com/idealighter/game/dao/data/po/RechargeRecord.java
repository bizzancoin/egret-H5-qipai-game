package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * recharge_record
 * @author 
 */
public class RechargeRecord implements Serializable {
    private Integer id;

    /**
     * 订单号（本地）
     */
    private String tradeNo;

    /**
     * 外部交易号(微信/支付的订单编号)
     */
    private String outsideNo;

    /**
     * 支付类型(1支付宝2微信)
     */
    private Byte payType;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 充值itemId
     */
    private Integer rechargeItemId;

    /**
     * 金币
     */
    private Long gold;

    /**
     * 赠送金币
     */
    private Long sendGold;

    /**
     * 价格（分）
     */
    private Integer price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 订单状态（0待支付，1支付到账）
     */
    private Byte state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutsideNo() {
        return outsideNo;
    }

    public void setOutsideNo(String outsideNo) {
        this.outsideNo = outsideNo;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Integer getRechargeItemId() {
        return rechargeItemId;
    }

    public void setRechargeItemId(Integer rechargeItemId) {
        this.rechargeItemId = rechargeItemId;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSendGold() {
        return sendGold;
    }

    public void setSendGold(Long sendGold) {
        this.sendGold = sendGold;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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
        RechargeRecord other = (RechargeRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getOutsideNo() == null ? other.getOutsideNo() == null : this.getOutsideNo().equals(other.getOutsideNo()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getRechargeItemId() == null ? other.getRechargeItemId() == null : this.getRechargeItemId().equals(other.getRechargeItemId()))
            && (this.getGold() == null ? other.getGold() == null : this.getGold().equals(other.getGold()))
            && (this.getSendGold() == null ? other.getSendGold() == null : this.getSendGold().equals(other.getSendGold()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getOutsideNo() == null) ? 0 : getOutsideNo().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getRechargeItemId() == null) ? 0 : getRechargeItemId().hashCode());
        result = prime * result + ((getGold() == null) ? 0 : getGold().hashCode());
        result = prime * result + ((getSendGold() == null) ? 0 : getSendGold().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", outsideNo=").append(outsideNo);
        sb.append(", payType=").append(payType);
        sb.append(", playerId=").append(playerId);
        sb.append(", rechargeItemId=").append(rechargeItemId);
        sb.append(", gold=").append(gold);
        sb.append(", sendGold=").append(sendGold);
        sb.append(", price=").append(price);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}