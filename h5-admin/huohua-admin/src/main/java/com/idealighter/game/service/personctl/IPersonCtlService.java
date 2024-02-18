package com.idealighter.game.service.personctl;

import com.idealighter.game.service.personctl.bo.PlayerCtrlDetailInfoBo;
import com.idealighter.game.service.personctl.bo.PlayerCtrlPlayerListBo;

public interface IPersonCtlService {
  /**
   * findPlayerCtrl.
   * @Description 分页获取玩家控制列表
   * @author houdongsheng
   * @date 2018年1月26日 下午3:11:06
   * @return PlayerCtrlItemBo列表
   */
  public PlayerCtrlPlayerListBo findPlayerCtrlList(Integer gameId, Integer roomId, Integer page,
      Integer pageSize);

  /**
   * findPlayerCtrlInfo.
   * @Description 获取个人控制详情
   * @author houdongsheng
   * @date 2018年1月26日 下午8:43:14
   * @return PlayerCtrlInfoBo
   */
  public PlayerCtrlDetailInfoBo findPlayerCtrlDetailInfo(Long playerId, String nickName);

  /**
   * addPlayerCtl.
   * @Description 增加玩家奖池
   * @author houdongsheng
   * @date 2018年1月27日 下午2:09:19
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param prizePool 奖金池奖金
   * @param control 控制信息
   * @return Result 返回信息
   */
  public String addPlayerCtl(Integer gameId, Long playerId, Long prizePool, String control);

  /**
   * addPlayerCtl.
   * @Description 修改玩家奖池金币
   * @author houdongsheng
   * @date 2018年1月27日 下午2:09:19
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param prizePoolGold 奖金池奖金
   * @return Result 返回信息
   */
  public String updatePrizePoolGold(Integer gameId, Long playerId, Long prizePoolGold);

  /**
  pdateCtl.
   * @Description 修改玩家奖池控制
   * @author houdongsheng
   * @date 2018年1月27日 下午2:09:19
   * @param gameId 游戏编号
   * @param playerId 玩家编号
   * @param control 奖池控制
   * @return Result 返回信息
   */
  public String updateCtl(Integer gameId, Long playerId, String control);
}
