package com.idealighter.game.dao.data.po;

import java.io.Serializable;

/**
 * @author 
 */
public class PlayerGameInfo implements Serializable {
    /**
     * 玩家id
     */
    private Integer id;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 玩家个人奖池组
     */
    private String prizePools;

    /**
     * 道具
     */
    private String items;

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

    public String getPrizePools() {
        return prizePools;
    }

    public void setPrizePools(String prizePools) {
        this.prizePools = prizePools;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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
        PlayerGameInfo other = (PlayerGameInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getPrizePools() == null ? other.getPrizePools() == null : this.getPrizePools().equals(other.getPrizePools()))
            && (this.getItems() == null ? other.getItems() == null : this.getItems().equals(other.getItems()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getPrizePools() == null) ? 0 : getPrizePools().hashCode());
        result = prime * result + ((getItems() == null) ? 0 : getItems().hashCode());
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
        sb.append(", prizePools=").append(prizePools);
        sb.append(", items=").append(items);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}