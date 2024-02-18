package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * games
 * @author 
 */
public class Games implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 游戏名称
     */
    private String name;

    /**
     * 房间表
     */
    private String roomTable;

    /**
     * 描述
     */
    private String remark;

    /**
     * 0不启动  1启动
     */
    private Boolean active;

    /**
     * 是否需要机器人
     */
    private Boolean needRobot;

    /**
     * 是否为局数游戏
     */
    private Boolean roundGame;

    /**
     * 是否含有个人控制
     */
    private Boolean personControl;

    /**
     * 是否含有房间控制
     */
    private Boolean roomControl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomTable() {
        return roomTable;
    }

    public void setRoomTable(String roomTable) {
        this.roomTable = roomTable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getNeedRobot() {
        return needRobot;
    }

    public void setNeedRobot(Boolean needRobot) {
        this.needRobot = needRobot;
    }

    public Boolean getRoundGame() {
        return roundGame;
    }

    public void setRoundGame(Boolean roundGame) {
        this.roundGame = roundGame;
    }

    public Boolean getPersonControl() {
        return personControl;
    }

    public void setPersonControl(Boolean personControl) {
        this.personControl = personControl;
    }

    public Boolean getRoomControl() {
        return roomControl;
    }

    public void setRoomControl(Boolean roomControl) {
        this.roomControl = roomControl;
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
        Games other = (Games) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRoomTable() == null ? other.getRoomTable() == null : this.getRoomTable().equals(other.getRoomTable()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
            && (this.getNeedRobot() == null ? other.getNeedRobot() == null : this.getNeedRobot().equals(other.getNeedRobot()))
            && (this.getRoundGame() == null ? other.getRoundGame() == null : this.getRoundGame().equals(other.getRoundGame()))
            && (this.getPersonControl() == null ? other.getPersonControl() == null : this.getPersonControl().equals(other.getPersonControl()))
            && (this.getRoomControl() == null ? other.getRoomControl() == null : this.getRoomControl().equals(other.getRoomControl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRoomTable() == null) ? 0 : getRoomTable().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getNeedRobot() == null) ? 0 : getNeedRobot().hashCode());
        result = prime * result + ((getRoundGame() == null) ? 0 : getRoundGame().hashCode());
        result = prime * result + ((getPersonControl() == null) ? 0 : getPersonControl().hashCode());
        result = prime * result + ((getRoomControl() == null) ? 0 : getRoomControl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", roomTable=").append(roomTable);
        sb.append(", remark=").append(remark);
        sb.append(", active=").append(active);
        sb.append(", needRobot=").append(needRobot);
        sb.append(", roundGame=").append(roundGame);
        sb.append(", personControl=").append(personControl);
        sb.append(", roomControl=").append(roomControl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}