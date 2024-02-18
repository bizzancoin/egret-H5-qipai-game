package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class ShuihuEffect implements Serializable {
  /**
   * 关键字
   */
  private String key;

  /**
   * 备注
   */
  private String remarks;

  /**
   * 单帧图片名
   */
  private String picname;

  /**
   * 播放数度
   */
  private Integer frametime;

  /**
   * 起始帧
   */
  private Integer beginframe;

  /**
   * 帧数
   */
  private Integer length;

  /**
   * 是否循环（1是0否）
   */
  private Integer isloop;

  /**
   * 生命周期
   */
  private Integer lifetime;

  /**
   * 是否移动
   */
  private Integer ismove;

  /**
   * 移动时间
   */
  private Integer movetime;

  /**
   * 起始坐标
   */
  private String beginpos;

  /**
   * 结束坐标
   */
  private String endpos;

  /**
   * 音效id
   */
  private Integer musicid;

  private static final long serialVersionUID = 1L;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getPicname() {
    return picname;
  }

  public void setPicname(String picname) {
    this.picname = picname;
  }

  public Integer getFrametime() {
    return frametime;
  }

  public void setFrametime(Integer frametime) {
    this.frametime = frametime;
  }

  public Integer getBeginframe() {
    return beginframe;
  }

  public void setBeginframe(Integer beginframe) {
    this.beginframe = beginframe;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getIsloop() {
    return isloop;
  }

  public void setIsloop(Integer isloop) {
    this.isloop = isloop;
  }

  public Integer getLifetime() {
    return lifetime;
  }

  public void setLifetime(Integer lifetime) {
    this.lifetime = lifetime;
  }

  public Integer getIsmove() {
    return ismove;
  }

  public void setIsmove(Integer ismove) {
    this.ismove = ismove;
  }

  public Integer getMovetime() {
    return movetime;
  }

  public void setMovetime(Integer movetime) {
    this.movetime = movetime;
  }

  public String getBeginpos() {
    return beginpos;
  }

  public void setBeginpos(String beginpos) {
    this.beginpos = beginpos;
  }

  public String getEndpos() {
    return endpos;
  }

  public void setEndpos(String endpos) {
    this.endpos = endpos;
  }

  public Integer getMusicid() {
    return musicid;
  }

  public void setMusicid(Integer musicid) {
    this.musicid = musicid;
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
    ShuihuEffect other = (ShuihuEffect) that;
    return (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey()))
        && (this.getRemarks() == null ? other.getRemarks() == null
            : this.getRemarks().equals(other.getRemarks()))
        && (this.getPicname() == null ? other.getPicname() == null
            : this.getPicname().equals(other.getPicname()))
        && (this.getFrametime() == null ? other.getFrametime() == null
            : this.getFrametime().equals(other.getFrametime()))
        && (this.getBeginframe() == null ? other.getBeginframe() == null
            : this.getBeginframe().equals(other.getBeginframe()))
        && (this.getLength() == null ? other.getLength() == null
            : this.getLength().equals(other.getLength()))
        && (this.getIsloop() == null ? other.getIsloop() == null
            : this.getIsloop().equals(other.getIsloop()))
        && (this.getLifetime() == null ? other.getLifetime() == null
            : this.getLifetime().equals(other.getLifetime()))
        && (this.getIsmove() == null ? other.getIsmove() == null
            : this.getIsmove().equals(other.getIsmove()))
        && (this.getMovetime() == null ? other.getMovetime() == null
            : this.getMovetime().equals(other.getMovetime()))
        && (this.getBeginpos() == null ? other.getBeginpos() == null
            : this.getBeginpos().equals(other.getBeginpos()))
        && (this.getEndpos() == null ? other.getEndpos() == null
            : this.getEndpos().equals(other.getEndpos()))
        && (this.getMusicid() == null ? other.getMusicid() == null
            : this.getMusicid().equals(other.getMusicid()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
    result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
    result = prime * result + ((getPicname() == null) ? 0 : getPicname().hashCode());
    result = prime * result + ((getFrametime() == null) ? 0 : getFrametime().hashCode());
    result = prime * result + ((getBeginframe() == null) ? 0 : getBeginframe().hashCode());
    result = prime * result + ((getLength() == null) ? 0 : getLength().hashCode());
    result = prime * result + ((getIsloop() == null) ? 0 : getIsloop().hashCode());
    result = prime * result + ((getLifetime() == null) ? 0 : getLifetime().hashCode());
    result = prime * result + ((getIsmove() == null) ? 0 : getIsmove().hashCode());
    result = prime * result + ((getMovetime() == null) ? 0 : getMovetime().hashCode());
    result = prime * result + ((getBeginpos() == null) ? 0 : getBeginpos().hashCode());
    result = prime * result + ((getEndpos() == null) ? 0 : getEndpos().hashCode());
    result = prime * result + ((getMusicid() == null) ? 0 : getMusicid().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", key=").append(key);
    sb.append(", remarks=").append(remarks);
    sb.append(", picname=").append(picname);
    sb.append(", frametime=").append(frametime);
    sb.append(", beginframe=").append(beginframe);
    sb.append(", length=").append(length);
    sb.append(", isloop=").append(isloop);
    sb.append(", lifetime=").append(lifetime);
    sb.append(", ismove=").append(ismove);
    sb.append(", movetime=").append(movetime);
    sb.append(", beginpos=").append(beginpos);
    sb.append(", endpos=").append(endpos);
    sb.append(", musicid=").append(musicid);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
