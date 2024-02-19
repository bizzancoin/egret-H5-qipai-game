package com.idealighter.game.core.dao.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.generate.domain.WhiteListDomain;
import com.idealighter.game.core.dao.generate.domain.WhiteListDomainExample;
import com.idealighter.game.core.dao.mapper.WhiteListMapper;

import java.util.List;

@Singleton
public class WhiteListDao {
  private WhiteListMapper mapper;

  @Inject
  public WhiteListDao(WhiteListMapper mapper) {
    this.mapper = mapper;
  }

  public List<WhiteListDomain> selectAll() {
    WhiteListDomainExample example = new WhiteListDomainExample();
    return mapper.selectByExample(example);
  }
}
