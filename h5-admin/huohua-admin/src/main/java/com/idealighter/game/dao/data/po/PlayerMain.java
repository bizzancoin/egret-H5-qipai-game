package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * player_main
 * @author 
 */
public class PlayerMain implements Serializable {
    /**
     * 玩家id
     */
    private Long id;

    /**
     * 靓号id
     */
    private Long superId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信unionId
     */
    private String unionId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    private String playerName;

    /**
     * 密码
     */
    private String password;

    /**
     * 断线重连码
     */
    private String reLoginCode;

    /**
     * 推荐人id
     */
    private Long recommendId;

    /**
     * 0:机器人,1:客户端,2:网页
     */
    private Byte registerType;

    /**
     * 是否为游客
     */
    private Boolean tourist;

    /**
     * 0普通用户 1代理
     */
    private Byte type;

    /**
     * 是否在线
     */
    private Boolean online;

    /**
     * 用户图标id
     */
    private Integer icon;

    /**
     * 经验
     */
    private Long exp;

    /**
     * 玩家等级
     */
    private Integer level;

    /**
     * 元宝
     */
    private Long ingot;

    /**
     * 金币
     */
    private Long gold;

    /**
     * 保险箱金币
     */
    private Long safeGold;

    /**
     * 赢分
     */
    private Long winGold;

    /**
     * 积分
     */
    private Long cedit;

    /**
     * 奖券
     */
    private Long lottery;

    private Long transferReward;

    /**
     * 银行密码
     */
    private String bankPwd;

    private Integer vipLevel;

    private Date vipEndTime;

    /**
     * 是否锁住
     */
    private Boolean locked;

    private String channelId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReLoginCode() {
        return reLoginCode;
    }

    public void setReLoginCode(String reLoginCode) {
        this.reLoginCode = reLoginCode;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public Byte getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Byte registerType) {
        this.registerType = registerType;
    }

    public Boolean getTourist() {
        return tourist;
    }

    public void setTourist(Boolean tourist) {
        this.tourist = tourist;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getIngot() {
        return ingot;
    }

    public void setIngot(Long ingot) {
        this.ingot = ingot;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSafeGold() {
        return safeGold;
    }

    public void setSafeGold(Long safeGold) {
        this.safeGold = safeGold;
    }

    public Long getWinGold() {
        return winGold;
    }

    public void setWinGold(Long winGold) {
        this.winGold = winGold;
    }

    public Long getCedit() {
        return cedit;
    }

    public void setCedit(Long cedit) {
        this.cedit = cedit;
    }

    public Long getLottery() {
        return lottery;
    }

    public void setLottery(Long lottery) {
        this.lottery = lottery;
    }

    public Long getTransferReward() {
        return transferReward;
    }

    public void setTransferReward(Long transferReward) {
        this.transferReward = transferReward;
    }

    public String getBankPwd() {
        return bankPwd;
    }

    public void setBankPwd(String bankPwd) {
        this.bankPwd = bankPwd;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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
        PlayerMain other = (PlayerMain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSuperId() == null ? other.getSuperId() == null : this.getSuperId().equals(other.getSuperId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getUnionId() == null ? other.getUnionId() == null : this.getUnionId().equals(other.getUnionId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPlayerName() == null ? other.getPlayerName() == null : this.getPlayerName().equals(other.getPlayerName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getReLoginCode() == null ? other.getReLoginCode() == null : this.getReLoginCode().equals(other.getReLoginCode()))
            && (this.getRecommendId() == null ? other.getRecommendId() == null : this.getRecommendId().equals(other.getRecommendId()))
            && (this.getRegisterType() == null ? other.getRegisterType() == null : this.getRegisterType().equals(other.getRegisterType()))
            && (this.getTourist() == null ? other.getTourist() == null : this.getTourist().equals(other.getTourist()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getOnline() == null ? other.getOnline() == null : this.getOnline().equals(other.getOnline()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getExp() == null ? other.getExp() == null : this.getExp().equals(other.getExp()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getIngot() == null ? other.getIngot() == null : this.getIngot().equals(other.getIngot()))
            && (this.getGold() == null ? other.getGold() == null : this.getGold().equals(other.getGold()))
            && (this.getSafeGold() == null ? other.getSafeGold() == null : this.getSafeGold().equals(other.getSafeGold()))
            && (this.getWinGold() == null ? other.getWinGold() == null : this.getWinGold().equals(other.getWinGold()))
            && (this.getCedit() == null ? other.getCedit() == null : this.getCedit().equals(other.getCedit()))
            && (this.getLottery() == null ? other.getLottery() == null : this.getLottery().equals(other.getLottery()))
            && (this.getTransferReward() == null ? other.getTransferReward() == null : this.getTransferReward().equals(other.getTransferReward()))
            && (this.getBankPwd() == null ? other.getBankPwd() == null : this.getBankPwd().equals(other.getBankPwd()))
            && (this.getVipLevel() == null ? other.getVipLevel() == null : this.getVipLevel().equals(other.getVipLevel()))
            && (this.getVipEndTime() == null ? other.getVipEndTime() == null : this.getVipEndTime().equals(other.getVipEndTime()))
            && (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSuperId() == null) ? 0 : getSuperId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getUnionId() == null) ? 0 : getUnionId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPlayerName() == null) ? 0 : getPlayerName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getReLoginCode() == null) ? 0 : getReLoginCode().hashCode());
        result = prime * result + ((getRecommendId() == null) ? 0 : getRecommendId().hashCode());
        result = prime * result + ((getRegisterType() == null) ? 0 : getRegisterType().hashCode());
        result = prime * result + ((getTourist() == null) ? 0 : getTourist().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOnline() == null) ? 0 : getOnline().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getExp() == null) ? 0 : getExp().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getIngot() == null) ? 0 : getIngot().hashCode());
        result = prime * result + ((getGold() == null) ? 0 : getGold().hashCode());
        result = prime * result + ((getSafeGold() == null) ? 0 : getSafeGold().hashCode());
        result = prime * result + ((getWinGold() == null) ? 0 : getWinGold().hashCode());
        result = prime * result + ((getCedit() == null) ? 0 : getCedit().hashCode());
        result = prime * result + ((getLottery() == null) ? 0 : getLottery().hashCode());
        result = prime * result + ((getTransferReward() == null) ? 0 : getTransferReward().hashCode());
        result = prime * result + ((getBankPwd() == null) ? 0 : getBankPwd().hashCode());
        result = prime * result + ((getVipLevel() == null) ? 0 : getVipLevel().hashCode());
        result = prime * result + ((getVipEndTime() == null) ? 0 : getVipEndTime().hashCode());
        result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
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
        sb.append(", superId=").append(superId);
        sb.append(", phone=").append(phone);
        sb.append(", unionId=").append(unionId);
        sb.append(", userName=").append(userName);
        sb.append(", email=").append(email);
        sb.append(", playerName=").append(playerName);
        sb.append(", password=").append(password);
        sb.append(", reLoginCode=").append(reLoginCode);
        sb.append(", recommendId=").append(recommendId);
        sb.append(", registerType=").append(registerType);
        sb.append(", tourist=").append(tourist);
        sb.append(", type=").append(type);
        sb.append(", online=").append(online);
        sb.append(", icon=").append(icon);
        sb.append(", exp=").append(exp);
        sb.append(", level=").append(level);
        sb.append(", ingot=").append(ingot);
        sb.append(", gold=").append(gold);
        sb.append(", safeGold=").append(safeGold);
        sb.append(", winGold=").append(winGold);
        sb.append(", cedit=").append(cedit);
        sb.append(", lottery=").append(lottery);
        sb.append(", transferReward=").append(transferReward);
        sb.append(", bankPwd=").append(bankPwd);
        sb.append(", vipLevel=").append(vipLevel);
        sb.append(", vipEndTime=").append(vipEndTime);
        sb.append(", locked=").append(locked);
        sb.append(", channelId=").append(channelId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}