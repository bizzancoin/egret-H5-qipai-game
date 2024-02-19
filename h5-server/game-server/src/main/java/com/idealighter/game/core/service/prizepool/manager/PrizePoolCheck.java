package com.idealighter.game.core.service.prizepool.manager;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.games.jcby.struct.JcbyDifficulty;
import com.idealighter.game.core.service.prizepool.struct.control.BaccaratControl;
import com.idealighter.game.core.service.prizepool.struct.control.BaiRenNiuNiuControl;
import com.idealighter.game.core.service.prizepool.struct.control.FishDifficulty;
import com.idealighter.game.core.service.prizepool.struct.control.GameControl;
import com.idealighter.game.core.service.prizepool.struct.control.GameControlMap;
import com.idealighter.game.core.service.prizepool.struct.control.JcbyControl;
import com.idealighter.game.core.service.prizepool.struct.room.ControlStrategy;
import com.idealighter.utils.json.JsonUtil;

public class PrizePoolCheck {

  /**
   * 校验控制.
   * 
   * @Title checkControl.
   * @author houdongsheng
   * @date 2018年2月1日 下午3:25:24
   * @param gameControl 游戏控制对象
   */
  public static void checkControl(GameControl gameControl) {
    HuohuaAssert.isTrue(gameControl != null, ErrorCode.GAME_CONTROL_ERROR, "[控制不能为空]");
    if (gameControl instanceof JcbyControl) {
      JcbyControl tmpCtl = (JcbyControl) gameControl;
      if (tmpCtl.getDifficultys() != null) {
        for (FishDifficulty item : tmpCtl.getDifficultys()) {
          HuohuaAssert.isTrue(item.getMultipleLower() > 0, ErrorCode.GAME_CONTROL_ERROR,
              "[倍数下限必须大于0]");
          HuohuaAssert.isTrue(item.getMultipleUpper() > 0, ErrorCode.GAME_CONTROL_ERROR,
              "[倍数上限必须大于0]");
          HuohuaAssert.isTrue(item.getMultipleUpper() >= item.getMultipleLower(),
              ErrorCode.GAME_CONTROL_ERROR, "[倍数上限不能小于下限]");

          HuohuaAssert.isTrue(JcbyDifficulty.get(item.getDifficulty()) != null,
              ErrorCode.GAME_CONTROL_ERROR, "[难度不存在]");
        }
      }

    } else if (gameControl instanceof BaccaratControl) {
      BaccaratControl tmpCtl = (BaccaratControl) gameControl;
      HuohuaAssert.isTrue(
          tmpCtl.getWinRatio() >= 0 && tmpCtl.getWinRatio() <= 100
              && tmpCtl.getLoseRatio() + tmpCtl.getWinRatio() == 100 && tmpCtl.getTieRito() >= 0
              && tmpCtl.getTieRito() <= 100,
          ErrorCode.GAME_CONTROL_ERROR, "[输赢必须在[0-100],且之和为100]");

    } else if (gameControl instanceof BaiRenNiuNiuControl) {
      BaiRenNiuNiuControl tmpCtl = (BaiRenNiuNiuControl) gameControl;

      HuohuaAssert.isTrue(
          tmpCtl.getWinRatio() >= 0 && tmpCtl.getWinRatio() <= 100
              && tmpCtl.getLoseRatio() + tmpCtl.getWinRatio() == 100,
          ErrorCode.GAME_CONTROL_ERROR, "[输赢必须在[0-100],且之和为100]");
    } else {
      HuohuaAssert.isTrue(false, ErrorCode.GAME_CONTROL_ERROR);
    }
  }

  /**
   * 校验策略是否合法.
   * 
   * @Title checkStrategy.
   * @author houdongsheng
   * @date 2018年1月31日 下午11:34:56
   * @param game 游戏编号
   * @param strategy 策略
   */
  public static void checkStrategy(Game game, ControlStrategy strategy) {
    HuohuaAssert.isTrue(strategy != null, ErrorCode.GAME_STRATEGY_ERROR, "[策略不能为空]");
    HuohuaAssert.isTrue(strategy.getUpper() >= strategy.getLower(), ErrorCode.GAME_STRATEGY_ERROR,
        "[提取下限不能大于提取上限]");
    HuohuaAssert.isTrue(strategy.getTime() > 0, ErrorCode.GAME_STRATEGY_ERROR, "[提取时间需要大于零0]");
    HuohuaAssert.isTrue(strategy.getGold() >= 0L, ErrorCode.GAME_STRATEGY_ERROR, "[提取金额不能低于0]");
    HuohuaAssert.isTrue(strategy.getRatio() >= 0 && strategy.getRatio() <= 100,
        ErrorCode.GAME_STRATEGY_ERROR, "[提取比例为0到100]");

    Class<? extends GameControl> controlClz = GameControlMap.getGameControlGameId(game.getType());
    GameControl gameControl = JsonUtil.fromJson(strategy.getControl(), controlClz);
    // GameControl gameControl = JSON.parseObject(strategy.getControl(), controlClz);
    checkControl(gameControl);
  }
}
