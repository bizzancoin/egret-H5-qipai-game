package com.idealighter.game.dao.dic.po;

import java.io.Serializable;

/**
 * @author
 */
public class GameNotice implements Serializable {
  /**
   * id
   */
  private Integer id;

  /**
   * 游戏ID
   */
  private Integer game;

  /**
   * 公告需要满足的条件
   */
  private String condition;

  /**
   * 条件描述（辨识用）
   */
  private String desc;

  /**
   * 是否跑马灯显示（否：0；是：1）
   */
  private Integer marqueeshow;

  /**
   * 聊天区是否显示（否：0；是：1）
   */
  private Integer chatshow;

  /**
   * 公告内容
   */
  private String content;

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

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Integer getMarqueeshow() {
    return marqueeshow;
  }

  public void setMarqueeshow(Integer marqueeshow) {
    this.marqueeshow = marqueeshow;
  }

  public Integer getChatshow() {
    return chatshow;
  }

  public void setChatshow(Integer chatshow) {
    this.chatshow = chatshow;
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
    GameNotice other = (GameNotice) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getGame() == null ? other.getGame() == null
            : this.getGame().equals(other.getGame()))
        && (this.getCondition() == null ? other.getCondition() == null
            : this.getCondition().equals(other.getCondition()))
        && (this.getDesc() == null ? other.getDesc() == null
            : this.getDesc().equals(other.getDesc()))
        && (this.getMarqueeshow() == null ? other.getMarqueeshow() == null
            : this.getMarqueeshow().equals(other.getMarqueeshow()))
        && (this.getChatshow() == null ? other.getChatshow() == null
            : this.getChatshow().equals(other.getChatshow()))
        && (this.getContent() == null ? other.getContent() == null
            : this.getContent().equals(other.getContent()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getGame() == null) ? 0 : getGame().hashCode());
    result = prime * result + ((getCondition() == null) ? 0 : getCondition().hashCode());
    result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
    result = prime * result + ((getMarqueeshow() == null) ? 0 : getMarqueeshow().hashCode());
    result = prime * result + ((getChatshow() == null) ? 0 : getChatshow().hashCode());
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
    sb.append(", game=").append(game);
    sb.append(", condition=").append(condition);
    sb.append(", desc=").append(desc);
    sb.append(", marqueeshow=").append(marqueeshow);
    sb.append(", chatshow=").append(chatshow);
    sb.append(", content=").append(content);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
