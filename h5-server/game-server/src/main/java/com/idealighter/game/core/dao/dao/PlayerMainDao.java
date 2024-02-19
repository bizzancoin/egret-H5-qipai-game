package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.dao.generate.domain.PlayerMainDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerMainDomainExample;
import com.idealighter.game.core.dao.mapper.PlayerMainMapper;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

@Singleton
public class PlayerMainDao {
  private PlayerMainMapper mapper;

  @Inject
  public PlayerMainDao(PlayerMainMapper mapper) {
    this.mapper = mapper;
  }

  public PlayerMainDomain findPlayerMainDomainById(Long id) {
    return mapper.selectByPrimaryKey(id);
  }

  /**
   * 获取玩家主要信息.
   * 
   * @Title findPlayerMainDomainBySuperId.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:01:58
   * @param superId 用户靓号
   * @return PlayerMainDomain
   */
  public PlayerMainDomain findPlayerMainDomainBySuperId(Long superId) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andSuperIdEqualTo(superId);
    List<PlayerMainDomain> playerMainDomains = mapper.selectByExample(select);
    PlayerMainDomain playerMainDomain = null;
    if (EmptyUtil.listIsNotEmpty(playerMainDomains)) {
      playerMainDomain = playerMainDomains.get(0);
    }
    return playerMainDomain;
  }

  /**
   * 根据玩家昵称查询用户主要信息.
   * 
   * @Title findPlayerMainDomainByPlayerName.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:05:04
   * @param playerName 玩家昵称
   * @return PlayerMainDomain
   */
  public PlayerMainDomain findPlayerMainDomainByPlayerName(String playerName) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andPlayerNameEqualTo(playerName);

    List<PlayerMainDomain> playerMainDomains = mapper.selectByExample(select);
    PlayerMainDomain playerMainDomain = null;
    if (EmptyUtil.listIsNotEmpty(playerMainDomains)) {
      playerMainDomain = playerMainDomains.get(0);
    }
    return playerMainDomain;
  }

  /**
   * 根据账号查询玩家信息.
   * 
   * @Title findPlayerMainDomainByUserName.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:06:03
   * @param userName 用户昵称
   * @return PlayerMainDomain
   */
  public PlayerMainDomain findPlayerMainDomainByUserName(String userName) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andUserNameEqualTo(userName);

    List<PlayerMainDomain> playerMainDomains = mapper.selectByExample(select);
    PlayerMainDomain playerMainDomain = null;
    if (EmptyUtil.listIsNotEmpty(playerMainDomains)) {
      playerMainDomain = playerMainDomains.get(0);
    }
    return playerMainDomain;
  }

  /**
   * 通过微信unionId查询用户.
   * 
   * @Title findPlayerMainDomainByUnionId.
   * @Description
   * @author houdongsheng
   * @date 2018年1月12日 下午5:10:24
   * @param unionId 微信UnionId
   * @return PlayerMainDomain
   */
  public PlayerMainDomain findPlayerMainDomainByUnionId(String unionId) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andUnionIdEqualTo(unionId);
    List<PlayerMainDomain> playerMainDomains = mapper.selectByExample(select);
    PlayerMainDomain playerMainDomain = null;
    if (EmptyUtil.listIsNotEmpty(playerMainDomains)) {
      playerMainDomain = playerMainDomains.get(0);
    }
    return playerMainDomain;
  }

  /**
   * 根据电话号码查询用户.
   * 
   * @Title findPlayerMainDomainByPhone.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:10:51
   * @param phone 手机号码
   * @return PlayerMainDomain
   */
  public PlayerMainDomain findPlayerMainDomainByPhone(String phone) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andPhoneEqualTo(phone);
    List<PlayerMainDomain> playerMainDomains = mapper.selectByExample(select);
    PlayerMainDomain playerMainDomain = null;
    if (EmptyUtil.listIsNotEmpty(playerMainDomains)) {
      playerMainDomain = playerMainDomains.get(0);
    }
    return playerMainDomain;
  }

  public Integer insertSelective(PlayerMainDomain record) {
    return mapper.insertSelective(record);
  }

  public Integer updateByPrimaryKeySelective(PlayerMainDomain record) {
    HuohuaAssert.isTrue(record.getId() != null);
    return mapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 重置在线情况.
   * 
   * @Title resetOnline.
   * @Description
   * @author houdongsheng
   * @date 2018年1月12日 下午5:12:49
   * @return 更新数量
   */
  public int resetOnline() {
    PlayerMainDomainExample example = new PlayerMainDomainExample();
    PlayerMainDomain record = new PlayerMainDomain();
    record.setOnline(false);
    return mapper.updateByExampleSelective(record, example);
  }

  /**
   * 获取排行榜.
   * 
   * @Title selectBeyoudRankList.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:15:45
   * @param gold 最小金币数量
   * @param limit 数量
   * @return List&lt;PlayerMainDomain&gt;
   */
  public List<PlayerMainDomain> selectBeyoudRankList(long gold, int limit) {
    PlayerMainDomainExample example = new PlayerMainDomainExample();

    example.createCriteria().andRegisterTypeNotEqualTo((byte) 0).andGoldGreaterThanOrEqualTo(gold)
        .andLockedEqualTo(false);
    example.setOrderByClause("gold desc, id desc");
    example.setLimit(limit);

    return mapper.selectByExample(example);
  }

  /**
   * 获取排行榜.
   * 
   * @Title selectBelowRankList.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:15:45
   * @param gold 最大金币数量
   * @param limit 数量
   * @return 排行榜列表.
   */
  public List<PlayerMainDomain> selectBelowRankList(long gold, int limit) {
    PlayerMainDomainExample example = new PlayerMainDomainExample();
    example.createCriteria().andRegisterTypeNotEqualTo((byte) 0).andGoldLessThan(gold)
        .andLockedEqualTo(false);
    example.setOrderByClause("gold desc, id desc");
    example.setLimit(limit);

    return mapper.selectByExample(example);
  }

  /**
   * 获取玩家赢分榜.
   * 
   * @param limit 限制.
   * @return 排行榜列表.
   */
  public List<PlayerMainDomain> selectTopWinGoldRankList(int limit) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();

    select.createCriteria().andRegisterTypeNotEqualTo((byte) 0).andLockedEqualTo(false);

    select.setLimit(limit);
    select.setOrderByClause("win_gold desc, id desc");
    return mapper.selectByExample(select);
  }

  /**
   * 查询靓号是否唯一.
   * 
   * @param superId 靓号.
   * @return 结果.
   */
  public boolean existsSuperId(long superId) {
    PlayerMainDomainExample select = new PlayerMainDomainExample();
    select.createCriteria().andSuperIdEqualTo(superId);
    return mapper.countByExample(select) > 0;
  }
}
