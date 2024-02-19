package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.BlackListDomain;
import com.idealighter.game.core.dao.generate.domain.BlackListDomainExample;
import com.idealighter.game.core.dao.mapper.BlackListMapper;

import java.util.List;

@Singleton
public class BlackListDao {
  @Inject
  private BlackListMapper mapper;

  /**
   * 获取所有.
   * 
   * @Title selectAll.
   * @author houdongsheng
   * @date 2018年1月12日 下午4:55:48
   * @return List&lt;BlackListDomain&gt;
   */
  public List<BlackListDomain> selectAll() {
    BlackListDomainExample example = new BlackListDomainExample();

    return mapper.selectByExample(example);
  }
}
