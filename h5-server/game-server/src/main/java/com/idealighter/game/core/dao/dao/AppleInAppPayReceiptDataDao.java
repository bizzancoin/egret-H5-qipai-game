package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.AppleInAppPayReceiptDataDomain;
import com.idealighter.game.core.dao.generate.domain.AppleInAppPayReceiptDataDomainExample;
import com.idealighter.game.core.dao.mapper.AppleInAppPayReceiptDataMapper;

import java.util.List;

@Singleton
public class AppleInAppPayReceiptDataDao {
  @Inject
  private AppleInAppPayReceiptDataMapper mapper;

  /**
   * 查找内购数据.
   * 
   * @Title findReceiptDateByMd5.
   * @author houdongsheng
   * @date 2018年1月20日 下午11:14:25
   * @param md5 md5校验值
   * @return AppleInAppPayReceiptDataDomain列表
   */
  public List<AppleInAppPayReceiptDataDomain> findReceiptDateByMd5(String md5) {
    AppleInAppPayReceiptDataDomainExample example = new AppleInAppPayReceiptDataDomainExample();
    example.createCriteria().andMd5CheckEqualTo(md5);
    example.setOrderByClause("id desc");

    return mapper.selectByExampleWithBLOBs(example);
  }

  public Integer insert(AppleInAppPayReceiptDataDomain domain) {
    return mapper.insertSelective(domain);
  }
}
