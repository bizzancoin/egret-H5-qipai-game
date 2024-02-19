
package com.idealighter.game.core.service.prizepool.struct.player;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.idealighter.game.core.service.prizepool.struct.PrizePool;
import com.idealighter.game.core.service.prizepool.struct.control.GameControl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 玩家个人奖池 .
 * 
 * @date 2015年10月14日 下午5:21:30
 *
 */
public class PlayerPrizePool extends PrizePool {

  // 奖池控制(json字符串,每个游戏自己解析)
  private String control;

  public <C extends GameControl> C control(TypeReference<C> controlType) {

    return JSON.parseObject(control, controlType);
  }

  public PlayerPrizePool() {}

  /**
   * 构造函数 .
   * 
   * @param game .
   * @param prize .
   * @param control .
   */
  public PlayerPrizePool(int game, long prize, String control) {
    this.game = game;
    this.control = control;
    this.prize = new AtomicLong(prize);
  }

  public String getControl() {
    return control;
  }

  public void setControl(String control) {
    this.control = control;
  }

}
