
package com.idealighter.game.core.service.games.jcby.struct;

import com.idealighter.game.core.service.games.common.buyu.Position;
import com.idealighter.game.core.service.games.common.buyu.Road;
import com.idealighter.game.games.jcby.dto.FishInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 鱼 .
 * 
 * @date 2016年3月7日 下午8:56:23
 *
 */
@Data
@EqualsAndHashCode(exclude = "scence")
public class JcbyFish {
  // 鱼的标识id
  private final int id;
  // 鱼的id
  private final int fishId;
  // 鱼的倍数
  private int multiple;
  // 鱼的出生时间
  private final long birthTime;
  // 鱼所在的场景
  private final JcbyScence scence;
  // 起点
  private final Position start;
  // 路径
  private final Road road;
  // 夹角(负数不处理)
  private final int angle;
  // 是否死亡
  private boolean died = false;

  private JcbyFish(int id, int fishId, int multiple, long birthTime, JcbyScence scence,
      Position start, Road road, int angle) {
    this.id = id;
    this.fishId = fishId;
    this.multiple = multiple;
    this.birthTime = birthTime;
    this.scence = scence;
    this.start = start;
    this.road = road;
    this.angle = angle;
  }

  /**
   * 创建鱼 .
   * 
   * @return
   */
  public static JcbyFish create(int fishId, int multiple, long birthTime, JcbyScence scence,
      Position start, Road road) {
    return create(fishId, multiple, birthTime, scence, start, road, -1);
  }

  /**
   * 工厂方法创建鱼，why？李逵特殊处理 .
   * 
   * @return
   */
  public static JcbyFish create(int fishId, int multiple, long birthTime, JcbyScence scence,
      Position start, Road road, int angle) {
    JcbyRoom room = scence.getTable().getRoom();
    if (fishId == room.getLikui()) {
      multiple = room.likuiMultiple();
    }
    scence.setFishIdSeed(scence.getFishIdSeed() + 1);
    return new JcbyFish(scence.getFishIdSeed(), fishId, multiple, birthTime, scence, start, road,
        angle);
  }

  /**
   * 获取鱼的位置 .
   * 
   * @return
   */
  public Position position() {

    return road.position(start, liveTime(), angle);
  }

  /**
   * 获取用于传输的dto .
   * 
   * @return
   */
  public FishInfo finshInfo() {
    FishInfo finshInfo = new FishInfo();
    finshInfo.setId(id);
    finshInfo.setRoad(road.id);
    finshInfo.setType(fishId);
    finshInfo.setXcoord((int) start.xcoord);
    finshInfo.setYcoord((int) start.ycoord);
    finshInfo.setTime(liveTime());
    finshInfo.setAngle(angle);

    return finshInfo;
  }

  /**
   * 鱼出生后活了多少时间(秒) .
   * 
   * @return
   */
  public long liveTime() {

    return System.currentTimeMillis() - birthTime;
  }

  /**
   * 鱼是否活着的 .
   * 
   * @return 被打死或时间超过鱼的生命时间返回false
   */
  public boolean alive() {

    return !died && liveTime() < road.lifeTime;
  }

}
