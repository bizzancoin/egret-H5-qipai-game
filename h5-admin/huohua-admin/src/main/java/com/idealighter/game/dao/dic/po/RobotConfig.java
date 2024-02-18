package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * robot_config
 * @author 
 */
public class RobotConfig implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 游戏
     */
    private Integer game;

    /**
     * 房间
     */
    private Integer room;

    /**
     * 是否允许占桌
     */
    private Byte occupytable;

    /**
     * 是否允许和玩家对局
     */
    private Byte gamewithplayer;

    /**
     * 初始携带金币下限
     */
    private Long goldlower;

    /**
     * 初始携带金币上限
     */
    private Long goldupper;

    /**
     * 最大携带金币数
     */
    private Long maxgold;

    /**
     * 单桌游戏局数下限
     */
    private Integer tablegamelower;

    /**
     * 单桌游戏局数上限
     */
    private Integer tablegameupper;

    /**
     * 游戏总局数下限
     */
    private Integer totalgamelower;

    /**
     * 游戏总局数上限
     */
    private Integer totalgameupper;

    /**
     * 申请上庄最低筹码
     */
    private Long bebankerchips;

    /**
     * 最大申请上庄人数
     */
    private Byte bankerapplynums;

    /**
     * 最大机器人申请上庄人数
     */
    private Byte robotbankerapplynums;

    /**
     * 0:00~2:00人数
     */
    private Integer time1players;

    /**
     * 2:00~4:00人数
     */
    private Integer time2players;

    /**
     * 4:00~6:00人数
     */
    private Integer time3players;

    /**
     * 6:00~8:00人数
     */
    private Integer time4players;

    /**
     * 8:00~10:00人数
     */
    private Integer time5players;

    /**
     * 10:00~12:00人数
     */
    private Integer time6players;

    /**
     * 12:00~14:00人数
     */
    private Integer time7players;

    /**
     * 14:00~16:00人数
     */
    private Integer time8players;

    /**
     * 16:00~18:00人数
     */
    private Integer time9players;

    /**
     * 18:00~20:00人数
     */
    private Integer time10players;

    /**
     * 20:00~22:00人数
     */
    private Integer time11players;

    /**
     * 22:00~24:00人数
     */
    private Integer time12players;

    /**
     * 配置状态 0未开启 1开启
     */
    private Byte state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Byte getOccupytable() {
        return occupytable;
    }

    public void setOccupytable(Byte occupytable) {
        this.occupytable = occupytable;
    }

    public Byte getGamewithplayer() {
        return gamewithplayer;
    }

    public void setGamewithplayer(Byte gamewithplayer) {
        this.gamewithplayer = gamewithplayer;
    }

    public Long getGoldlower() {
        return goldlower;
    }

    public void setGoldlower(Long goldlower) {
        this.goldlower = goldlower;
    }

    public Long getGoldupper() {
        return goldupper;
    }

    public void setGoldupper(Long goldupper) {
        this.goldupper = goldupper;
    }

    public Long getMaxgold() {
        return maxgold;
    }

    public void setMaxgold(Long maxgold) {
        this.maxgold = maxgold;
    }

    public Integer getTablegamelower() {
        return tablegamelower;
    }

    public void setTablegamelower(Integer tablegamelower) {
        this.tablegamelower = tablegamelower;
    }

    public Integer getTablegameupper() {
        return tablegameupper;
    }

    public void setTablegameupper(Integer tablegameupper) {
        this.tablegameupper = tablegameupper;
    }

    public Integer getTotalgamelower() {
        return totalgamelower;
    }

    public void setTotalgamelower(Integer totalgamelower) {
        this.totalgamelower = totalgamelower;
    }

    public Integer getTotalgameupper() {
        return totalgameupper;
    }

    public void setTotalgameupper(Integer totalgameupper) {
        this.totalgameupper = totalgameupper;
    }

    public Long getBebankerchips() {
        return bebankerchips;
    }

    public void setBebankerchips(Long bebankerchips) {
        this.bebankerchips = bebankerchips;
    }

    public Byte getBankerapplynums() {
        return bankerapplynums;
    }

    public void setBankerapplynums(Byte bankerapplynums) {
        this.bankerapplynums = bankerapplynums;
    }

    public Byte getRobotbankerapplynums() {
        return robotbankerapplynums;
    }

    public void setRobotbankerapplynums(Byte robotbankerapplynums) {
        this.robotbankerapplynums = robotbankerapplynums;
    }

    public Integer getTime1players() {
        return time1players;
    }

    public void setTime1players(Integer time1players) {
        this.time1players = time1players;
    }

    public Integer getTime2players() {
        return time2players;
    }

    public void setTime2players(Integer time2players) {
        this.time2players = time2players;
    }

    public Integer getTime3players() {
        return time3players;
    }

    public void setTime3players(Integer time3players) {
        this.time3players = time3players;
    }

    public Integer getTime4players() {
        return time4players;
    }

    public void setTime4players(Integer time4players) {
        this.time4players = time4players;
    }

    public Integer getTime5players() {
        return time5players;
    }

    public void setTime5players(Integer time5players) {
        this.time5players = time5players;
    }

    public Integer getTime6players() {
        return time6players;
    }

    public void setTime6players(Integer time6players) {
        this.time6players = time6players;
    }

    public Integer getTime7players() {
        return time7players;
    }

    public void setTime7players(Integer time7players) {
        this.time7players = time7players;
    }

    public Integer getTime8players() {
        return time8players;
    }

    public void setTime8players(Integer time8players) {
        this.time8players = time8players;
    }

    public Integer getTime9players() {
        return time9players;
    }

    public void setTime9players(Integer time9players) {
        this.time9players = time9players;
    }

    public Integer getTime10players() {
        return time10players;
    }

    public void setTime10players(Integer time10players) {
        this.time10players = time10players;
    }

    public Integer getTime11players() {
        return time11players;
    }

    public void setTime11players(Integer time11players) {
        this.time11players = time11players;
    }

    public Integer getTime12players() {
        return time12players;
    }

    public void setTime12players(Integer time12players) {
        this.time12players = time12players;
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
        RobotConfig other = (RobotConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGame() == null ? other.getGame() == null : this.getGame().equals(other.getGame()))
            && (this.getRoom() == null ? other.getRoom() == null : this.getRoom().equals(other.getRoom()))
            && (this.getOccupytable() == null ? other.getOccupytable() == null : this.getOccupytable().equals(other.getOccupytable()))
            && (this.getGamewithplayer() == null ? other.getGamewithplayer() == null : this.getGamewithplayer().equals(other.getGamewithplayer()))
            && (this.getGoldlower() == null ? other.getGoldlower() == null : this.getGoldlower().equals(other.getGoldlower()))
            && (this.getGoldupper() == null ? other.getGoldupper() == null : this.getGoldupper().equals(other.getGoldupper()))
            && (this.getMaxgold() == null ? other.getMaxgold() == null : this.getMaxgold().equals(other.getMaxgold()))
            && (this.getTablegamelower() == null ? other.getTablegamelower() == null : this.getTablegamelower().equals(other.getTablegamelower()))
            && (this.getTablegameupper() == null ? other.getTablegameupper() == null : this.getTablegameupper().equals(other.getTablegameupper()))
            && (this.getTotalgamelower() == null ? other.getTotalgamelower() == null : this.getTotalgamelower().equals(other.getTotalgamelower()))
            && (this.getTotalgameupper() == null ? other.getTotalgameupper() == null : this.getTotalgameupper().equals(other.getTotalgameupper()))
            && (this.getBebankerchips() == null ? other.getBebankerchips() == null : this.getBebankerchips().equals(other.getBebankerchips()))
            && (this.getBankerapplynums() == null ? other.getBankerapplynums() == null : this.getBankerapplynums().equals(other.getBankerapplynums()))
            && (this.getRobotbankerapplynums() == null ? other.getRobotbankerapplynums() == null : this.getRobotbankerapplynums().equals(other.getRobotbankerapplynums()))
            && (this.getTime1players() == null ? other.getTime1players() == null : this.getTime1players().equals(other.getTime1players()))
            && (this.getTime2players() == null ? other.getTime2players() == null : this.getTime2players().equals(other.getTime2players()))
            && (this.getTime3players() == null ? other.getTime3players() == null : this.getTime3players().equals(other.getTime3players()))
            && (this.getTime4players() == null ? other.getTime4players() == null : this.getTime4players().equals(other.getTime4players()))
            && (this.getTime5players() == null ? other.getTime5players() == null : this.getTime5players().equals(other.getTime5players()))
            && (this.getTime6players() == null ? other.getTime6players() == null : this.getTime6players().equals(other.getTime6players()))
            && (this.getTime7players() == null ? other.getTime7players() == null : this.getTime7players().equals(other.getTime7players()))
            && (this.getTime8players() == null ? other.getTime8players() == null : this.getTime8players().equals(other.getTime8players()))
            && (this.getTime9players() == null ? other.getTime9players() == null : this.getTime9players().equals(other.getTime9players()))
            && (this.getTime10players() == null ? other.getTime10players() == null : this.getTime10players().equals(other.getTime10players()))
            && (this.getTime11players() == null ? other.getTime11players() == null : this.getTime11players().equals(other.getTime11players()))
            && (this.getTime12players() == null ? other.getTime12players() == null : this.getTime12players().equals(other.getTime12players()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGame() == null) ? 0 : getGame().hashCode());
        result = prime * result + ((getRoom() == null) ? 0 : getRoom().hashCode());
        result = prime * result + ((getOccupytable() == null) ? 0 : getOccupytable().hashCode());
        result = prime * result + ((getGamewithplayer() == null) ? 0 : getGamewithplayer().hashCode());
        result = prime * result + ((getGoldlower() == null) ? 0 : getGoldlower().hashCode());
        result = prime * result + ((getGoldupper() == null) ? 0 : getGoldupper().hashCode());
        result = prime * result + ((getMaxgold() == null) ? 0 : getMaxgold().hashCode());
        result = prime * result + ((getTablegamelower() == null) ? 0 : getTablegamelower().hashCode());
        result = prime * result + ((getTablegameupper() == null) ? 0 : getTablegameupper().hashCode());
        result = prime * result + ((getTotalgamelower() == null) ? 0 : getTotalgamelower().hashCode());
        result = prime * result + ((getTotalgameupper() == null) ? 0 : getTotalgameupper().hashCode());
        result = prime * result + ((getBebankerchips() == null) ? 0 : getBebankerchips().hashCode());
        result = prime * result + ((getBankerapplynums() == null) ? 0 : getBankerapplynums().hashCode());
        result = prime * result + ((getRobotbankerapplynums() == null) ? 0 : getRobotbankerapplynums().hashCode());
        result = prime * result + ((getTime1players() == null) ? 0 : getTime1players().hashCode());
        result = prime * result + ((getTime2players() == null) ? 0 : getTime2players().hashCode());
        result = prime * result + ((getTime3players() == null) ? 0 : getTime3players().hashCode());
        result = prime * result + ((getTime4players() == null) ? 0 : getTime4players().hashCode());
        result = prime * result + ((getTime5players() == null) ? 0 : getTime5players().hashCode());
        result = prime * result + ((getTime6players() == null) ? 0 : getTime6players().hashCode());
        result = prime * result + ((getTime7players() == null) ? 0 : getTime7players().hashCode());
        result = prime * result + ((getTime8players() == null) ? 0 : getTime8players().hashCode());
        result = prime * result + ((getTime9players() == null) ? 0 : getTime9players().hashCode());
        result = prime * result + ((getTime10players() == null) ? 0 : getTime10players().hashCode());
        result = prime * result + ((getTime11players() == null) ? 0 : getTime11players().hashCode());
        result = prime * result + ((getTime12players() == null) ? 0 : getTime12players().hashCode());
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
        sb.append(", game=").append(game);
        sb.append(", room=").append(room);
        sb.append(", occupytable=").append(occupytable);
        sb.append(", gamewithplayer=").append(gamewithplayer);
        sb.append(", goldlower=").append(goldlower);
        sb.append(", goldupper=").append(goldupper);
        sb.append(", maxgold=").append(maxgold);
        sb.append(", tablegamelower=").append(tablegamelower);
        sb.append(", tablegameupper=").append(tablegameupper);
        sb.append(", totalgamelower=").append(totalgamelower);
        sb.append(", totalgameupper=").append(totalgameupper);
        sb.append(", bebankerchips=").append(bebankerchips);
        sb.append(", bankerapplynums=").append(bankerapplynums);
        sb.append(", robotbankerapplynums=").append(robotbankerapplynums);
        sb.append(", time1players=").append(time1players);
        sb.append(", time2players=").append(time2players);
        sb.append(", time3players=").append(time3players);
        sb.append(", time4players=").append(time4players);
        sb.append(", time5players=").append(time5players);
        sb.append(", time6players=").append(time6players);
        sb.append(", time7players=").append(time7players);
        sb.append(", time8players=").append(time8players);
        sb.append(", time9players=").append(time9players);
        sb.append(", time10players=").append(time10players);
        sb.append(", time11players=").append(time11players);
        sb.append(", time12players=").append(time12players);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}