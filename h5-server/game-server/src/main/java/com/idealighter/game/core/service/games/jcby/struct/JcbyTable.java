
package com.idealighter.game.core.service.games.jcby.struct;

import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 金蝉捕鱼桌子 .
 * 
 * @date 2016年3月7日 下午9:07:50
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JcbyTable extends AbstractTable {
  // 所属房间
  private final JcbyRoom room;
  // 座位(数目固定)
  private final List<JcbySeat> seats;
  // 当前场景Index
  private int scenceIndex = 0;
  // 桌子中的场景id
  private final List<JcbyScence> scences;
  // 牌桌leader，用来代发机器人的碰撞
  private long leader = 0;
  // 暂停发炮
  private boolean pauseFire;
  // 场景ScheduledFuture
  public ScheduledFuture<?> scenceFuture = null;

  /**
   * 构造函数 .
   * 
   * @param id .
   * @param room .
   * @param chairs .
   */
  public JcbyTable(int id, JcbyRoom room, int chairs, List<Integer> scences) {
    super(id);
    this.room = room;
    this.seats = Collections.unmodifiableList(createSeats(chairs));
    this.scences = Collections.unmodifiableList(createScences(scences));
  }

  /**
   * 创建座位 .
   * 
   * @param chairs .
   * @return
   */
  private List<JcbySeat> createSeats(int chairs) {
    List<JcbySeat> seats = new ArrayList<>(chairs);
    for (int i = 0; i < chairs; i++) {
      seats.add(new JcbySeat(i, this));
    }

    return seats;
  }

  /**
   * 创建场景 .
   * 
   * @param scenceDoms .
   * @return
   */
  private List<JcbyScence> createScences(List<Integer> scenceIds) {
    List<JcbyScence> scences = new ArrayList<>();
    for (Integer scenceId : scenceIds) {
      scences.add(new JcbyScence(scenceId, this));
    }

    return scences;
  }

  /**
   * 获取seat .
   * 
   * @param order .
   * @return
   */
  public JcbySeat getSeat(int order) {

    return seats.get(order);
  }

  /**
   * 有玩家座位的数量 .
   * 
   * @return
   */
  public int playersNum() {
    int num = 0;
    for (JcbySeat s : seats) {
      if (s.getPlayerId() > 0) {
        num++;
      }
    }

    return num;
  }

  /**
   * 上一个场景 .
   * 
   * @return
   */
  public JcbyScence scence() {
    return scences.get(scenceIndex % scences.size());
  }

  /**
   * 上一个场景 .
   * 
   * @return
   */
  public JcbyScence preScence() {

    return scences.get((scenceIndex - 1) % scences.size());
  }

  /**
   * 下一个场景 .
   * 
   * @return
   */
  public JcbyScence nextScence() {

    return scences.get((scenceIndex + 1) % scences.size());
  }

  /**
   * 重置数据 .
   */
  public void reset() {
    this.scenceIndex = 0;
    this.leader = 0;
    this.pauseFire = false;
    if (scenceFuture != null) {
      scenceFuture.cancel(false);
    }
  }

  @Override
  public JcbyRoom room() {

    return room;
  }

  @Override
  public List<JcbySeat> seats() {

    return seats;
  }
  
  @Override
  public boolean robotFull() {
    boolean full = false;

    // 现有机器人数量
    int robotNum = 0;
    for (AbstractSeat seat : seats()) {
      if (seat.getPlayerId() > 0 && seat.isRobot()) {
        robotNum++;
      }
    }

    // 计算最大机器人数量
    int maxRobotNum =
        (int) (this.getRoom().getRoomDomain().getRobotRatio() / 100.0 * seats().size());
    
    if (robotNum >= maxRobotNum) {
      full = true;
    }

    return full;
  }
}
