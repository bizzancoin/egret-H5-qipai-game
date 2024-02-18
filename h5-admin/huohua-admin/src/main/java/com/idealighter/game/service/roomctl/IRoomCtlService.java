package com.idealighter.game.service.roomctl;

import com.idealighter.game.service.roomctl.bo.StrategyBo;

public interface IRoomCtlService {
  /**
   * findCtlInfo.
   * @Description 获取控制信息
   * @author houdongsheng
   * @date 2018年1月27日 下午9:57:12
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return String
   */
  public String findCtlInfo(Integer gameId, Integer roomId);

  /**
   * addDefaultPrizePool.
   * @Description 增加默认奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGold 奖池奖金
   * @return String
   */
  public String addDefaultPrizePool(Integer gameId, Integer roomId, Long prizePoolGold);

  /**
   * addDefaultPrizePoolGold.
   * @Description 增加默认奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param gold 新增金币
   * @return String
   */
  public String addDefaultPrizePoolGold(Integer gameId, Integer roomId, Long gold);

  /**
   * modifyDefaultPrizePoolGold.
   * @Description 增加默认奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param gold 新增金币
   * @return String
   */
  public String modifyDefaultPrizePoolGold(Integer gameId, Integer roomId, Long gold);

  /**
   * addDefaultPrizePoolStrategy.
   * @Description 增加默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:52:12
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategy 策略编号
   * @return String
   */
  public String addDefaultPrizePoolStrategy(Integer gameId, Integer roomId, StrategyBo strategy);

  /**
   * modifyDefaultPrizePoolStrategy.
   * @Description 修改默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:52:39
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategy 策略
   * @return String
   */
  public String modifyDefaultPrizePoolStrategy(Integer gameId, Integer roomId, StrategyBo strategy);

  /**
   * delDefaultPrizePoolStrategy.
   * @Description 删除默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:28:00
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param strategyId 策略编号
   * @return String
   */
  public String delDefaultPrizePoolStrategy(Integer gameId, Integer roomId, Long strategyId);

  /**
   * delDefaultPrizePool.
   * @Description 删除默认奖池
   * @author houdongsheng
   * @date 2018年1月28日 上午8:28:00
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return String
   */
  public String delDefaultPrizePool(Integer gameId, Integer roomId);


  // ================

  /**
   * addGoldPrizePool.
   * @Description 增加金币奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolGold 奖池奖金
   * @param lower 金币下限
   * @param upper 金币上限
   * @return String
   */
  public String addGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolGold, Long lower,
      Long upper);

  /**
   * delGoldPrizePool.
   * @Description 删除金币奖池
   * @author houdongsheng
   * @date 2018年1月28日 上午8:28:00
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @return String
   */
  public String delGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolId);

  /**
   * addGoldPrizePoolGold.
   * @Description 增加金币奖池金币
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param gold 新增金币
   * @return String
   */
  public String addGoldPrizePoolGold(Integer gameId, Integer roomId, Long prizePoolId, Long gold);

  /**
   * modifyGoldPrizePoolGold.
   * @Description 修改金币奖池金币
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param gold 新增金币
   * @return String
   */
  public String modifyGoldPrizePoolGold(Integer gameId, Integer roomId, Long prizePoolId,
      Long gold);

  /**
   * modifyGoldPrizePoolGold.
   * @Description 修改金币奖池金币
   * @author houdongsheng
   * @date 2018年1月27日 下午10:01:55
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param lower 金币下限
   * @param upper 金币上限
   * @return String
   */
  public String modifyGoldPrizePoolRange(Integer gameId, Integer roomId, Long prizePoolId,
      Long lower, Long upper);

  /**
   * addGoldPrizePoolStrategy.
   * @Description 增加金币奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:52:12
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param strategy 策略编号
   * @return String
   */
  public String addGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      StrategyBo strategy);

  /**
   * modifyGoldPrizePoolStrategy.
   * @Description 修改金币奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:52:39
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param strategy 策略
   * @return String
   */
  public String modifyGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      StrategyBo strategy);

  /**
   * delDefaultPrizePoolStrategy.
   * @Description 删除默认奖池策略
   * @author houdongsheng
   * @date 2018年1月28日 上午8:28:00
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @param strategyId 策略编号
   * @return String
   */
  public String delGoldPrizePoolStrategy(Integer gameId, Integer roomId, Long prizePoolId,
      Long strategyId);

  /**
   * findDefaultPrizePool.
   * @Description 获取默认奖池
   * @author houdongsheng
   * @date 2018年1月28日 上午8:46:17
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @return String
   */
  public String findDefaultPrizePool(Integer gameId, Integer roomId);

  /**
   * findGoldPrizePool.
   * @Description 获取金币奖池信息
   * @author houdongsheng
   * @date 2018年1月28日 上午8:47:40
   * @param gameId 游戏编号
   * @param roomId 房间编号
   * @param prizePoolId 奖池编号
   * @return String
   */
  public String findGoldPrizePool(Integer gameId, Integer roomId, Long prizePoolId);
}
