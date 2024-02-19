package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.RoomPrizePoolGoupDomain;
import com.idealighter.game.core.dao.generate.domain.RoomPrizePoolGoupDomainExample;
import com.idealighter.game.core.dao.mapper.RoomPrizePoolGroupMapper;
import com.idealighter.utils.time.TimeUtil;

import java.util.Date;
import java.util.List;

@Singleton
public class RoomPrizePoolGroupDao {
  private RoomPrizePoolGroupMapper mapper;

  @Inject
  public RoomPrizePoolGroupDao(RoomPrizePoolGroupMapper mapper) {
    this.mapper = mapper;
  }

  public List<RoomPrizePoolGoupDomain> selectAll() {
    RoomPrizePoolGoupDomainExample example = new RoomPrizePoolGoupDomainExample();
    return mapper.selectByExampleWithBLOBs(example);
  }

  /**
   * 插入奖池组.
   * 
   * @Title insertPrizePoolGroup.
   * @author houdongsheng
   * @date 2018年1月12日 下午5:27:43
   * @param record 奖池组
   * @return 插入数量
   */
  public Integer insertPrizePoolGroup(RoomPrizePoolGoupDomain record) {
    Date now = TimeUtil.now();
    record.setCreateTime(now);
    record.setUpdateTime(now);
    return mapper.insertSelective(record);
  }

  public int updatePrizePoolGroup(RoomPrizePoolGoupDomain domain) {
    domain.setUpdateTime(new Date());
    return mapper.updateByPrimaryKeySelective(domain);
  }
}
