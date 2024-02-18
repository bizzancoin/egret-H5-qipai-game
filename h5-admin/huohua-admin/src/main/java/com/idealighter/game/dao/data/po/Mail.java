package com.idealighter.game.dao.data.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Mail implements Serializable {
    /**
     * 表id
     */
    private Integer id;

    /**
     * 收件人uid 所有人=0 单人=uid
     */
    private Long playerId;

    private Long playerSuperId;

    /**
     * 收件人昵称
     */
    private String playerName;

    /**
     * 发件人uid
     */
    private Long fromPlayerId;

    private Long fromPlayerSuperId;

    /**
     * 发件人昵称
     */
    private String fromPlayerName;

    /**
     * 附件金币
     */
    private Long gold;

    /**
     * 标题
     */
    private String title;

    /**
     * 0=未读,1=已读
     */
    private Boolean isRead;

    /**
     * 邮件领取附件 0=未领取 1=已领取
     */
    private Boolean isRecGold;

    /**
     * 时间
     */
    private Date createTime;

    /**
     * 内容
     */
    private String content;

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

    public Long getFromPlayerId() {
        return fromPlayerId;
    }

    public void setFromPlayerId(Long fromPlayerId) {
        this.fromPlayerId = fromPlayerId;
    }

    public Long getFromPlayerSuperId() {
        return fromPlayerSuperId;
    }

    public void setFromPlayerSuperId(Long fromPlayerSuperId) {
        this.fromPlayerSuperId = fromPlayerSuperId;
    }

    public String getFromPlayerName() {
        return fromPlayerName;
    }

    public void setFromPlayerName(String fromPlayerName) {
        this.fromPlayerName = fromPlayerName;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsRecGold() {
        return isRecGold;
    }

    public void setIsRecGold(Boolean isRecGold) {
        this.isRecGold = isRecGold;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        Mail other = (Mail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getPlayerSuperId() == null ? other.getPlayerSuperId() == null : this.getPlayerSuperId().equals(other.getPlayerSuperId()))
            && (this.getPlayerName() == null ? other.getPlayerName() == null : this.getPlayerName().equals(other.getPlayerName()))
            && (this.getFromPlayerId() == null ? other.getFromPlayerId() == null : this.getFromPlayerId().equals(other.getFromPlayerId()))
            && (this.getFromPlayerSuperId() == null ? other.getFromPlayerSuperId() == null : this.getFromPlayerSuperId().equals(other.getFromPlayerSuperId()))
            && (this.getFromPlayerName() == null ? other.getFromPlayerName() == null : this.getFromPlayerName().equals(other.getFromPlayerName()))
            && (this.getGold() == null ? other.getGold() == null : this.getGold().equals(other.getGold()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getIsRead() == null ? other.getIsRead() == null : this.getIsRead().equals(other.getIsRead()))
            && (this.getIsRecGold() == null ? other.getIsRecGold() == null : this.getIsRecGold().equals(other.getIsRecGold()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getPlayerSuperId() == null) ? 0 : getPlayerSuperId().hashCode());
        result = prime * result + ((getPlayerName() == null) ? 0 : getPlayerName().hashCode());
        result = prime * result + ((getFromPlayerId() == null) ? 0 : getFromPlayerId().hashCode());
        result = prime * result + ((getFromPlayerSuperId() == null) ? 0 : getFromPlayerSuperId().hashCode());
        result = prime * result + ((getFromPlayerName() == null) ? 0 : getFromPlayerName().hashCode());
        result = prime * result + ((getGold() == null) ? 0 : getGold().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIsRead() == null) ? 0 : getIsRead().hashCode());
        result = prime * result + ((getIsRecGold() == null) ? 0 : getIsRecGold().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
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
        sb.append(", playerSuperId=").append(playerSuperId);
        sb.append(", playerName=").append(playerName);
        sb.append(", fromPlayerId=").append(fromPlayerId);
        sb.append(", fromPlayerSuperId=").append(fromPlayerSuperId);
        sb.append(", fromPlayerName=").append(fromPlayerName);
        sb.append(", gold=").append(gold);
        sb.append(", title=").append(title);
        sb.append(", isRead=").append(isRead);
        sb.append(", isRecGold=").append(isRecGold);
        sb.append(", createTime=").append(createTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}