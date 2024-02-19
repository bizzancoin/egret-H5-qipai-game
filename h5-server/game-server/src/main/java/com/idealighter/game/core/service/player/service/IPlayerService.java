package com.idealighter.game.core.service.player.service;

import com.google.inject.ImplementedBy;

import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.service.bo.PlayerRankBo;

import java.util.Date;
import java.util.List;

@ImplementedBy(PlayerService.class)
public interface IPlayerService {

  /**
   * 根据玩家id获取.
   * 
   * @param id .
   * @return
   */
  PlayerBo selectById(long id);

  /**
   * 通过靓号获取玩家.
   * 
   * @param superId 靓号 .
   * @return 玩家.
   */
  PlayerBo selectBySuperId(long superId);

  /**
   * 查询登录或者登出时间大于activeTime的玩家 .
   * 
   * @param activeTime .
   * @return
   */
  List<PlayerBo> selectActivePlayers(Date activeTime);

  /**
   * 根据昵称查玩家id .
   * 
   * @param playerName 玩家昵称.
   * @return 玩家id.
   */
  Long selectPlayerIdByPlayerName(String playerName);

  /**
   * 根据用户名查玩家 .
   * 
   * @param userName 用户名.
   * @return 玩家信息.
   */
  PlayerBo selectPlayerByUserName(String userName);

  /**
   * 根据手机号码，查找用户.
   * 
   * @Title selectPlayerByPhone.
   * @author houdongsheng
   * @date 2017年11月24日 下午6:57:41
   * @param phone 手机号码
   * @return PlayerDomain
   */
  PlayerBo selectPlayerByPhone(String phone);

  /**
   * 新增玩家数据 .
   * 
   * @param playerDomain .
   * @return
   */
  int insert(PlayerBo playerDomain);

  /**
   * 更新玩家游戏信息.
   * 
   * @Title updatePlayerGameInfoDomain.
   * @Description
   * @author houdongsheng
   * @date 2018年1月29日 上午4:13:25
   * @param playerBo 游戏domain
   * @return 更新数量
   */
  public int updatePlayerGameInfoDomain(PlayerBo playerBo);

  /**
   * 根据玩家id更新数据 .
   * 
   * @param playerDomain .
   * @return
   */
  int updateById(PlayerBo playerDomain);

  /**
   * 更新玩家是否在线 .
   * 
   * @param id .
   * @param online .
   * @return
   */
  int updateOnlineByPlayerId(long id, boolean online);

  /**
   * 修改玩家的靓号.
   * 
   * @Title updateSuperIdByPlayerId.
   * @author houdongsheng
   * @date 2017年11月25日 下午4:51:56
   * @param id 用户编号
   * @param superId 靓号
   * @return 更改结果.
   */
  int updateSuperIdByPlayerId(long id, long superId);

  /**
   * 重置在线表示 .
   * 
   * @return
   */
  int resetOnline();

  /**
   * 根据微信union编号查找用户.
   * 
   * @Title selectPlayerByUnionId.
   * @Description
   * @author houdongsheng
   * @date 2018年1月11日 下午5:55:07
   */
  public PlayerBo selectPlayerByUnionId(String unionId);

  /**
   * 获取排行榜超过金币的列表.
   * 
   * @param gold 金币.
   * @param limit 限制.
   * @return 排行榜.
   */
  List<PlayerRankBo> selectBeyoudRankList(long gold, int limit);

  /**
   * 获取排行榜小于金币的列表.
   * 
   * @param gold 金币.
   * @param limit 限制.
   * @return 排行榜.
   */
  List<PlayerRankBo> selectBelowRankList(long gold, int limit);

  /**
   * 获取赢分榜列表.
   * 
   * @param limit 上限条数.
   * @return 排行榜.
   */
  List<PlayerRankBo> selectTopWinGold(int limit);

  /**
   * 是否存在靓号.
   * 
   * @param superId 靓号.
   * @return 结果.
   */
  boolean existsSuperId(Long superId);
}
