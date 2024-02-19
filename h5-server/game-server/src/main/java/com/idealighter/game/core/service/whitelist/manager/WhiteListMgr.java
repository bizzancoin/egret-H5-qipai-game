
package com.idealighter.game.core.service.whitelist.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.dao.dao.WhiteListDao;
import com.idealighter.game.core.dao.generate.domain.WhiteListDomain;
import com.idealighter.utils.check.EmptyUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 白名单管理 .
 * 
 * @date 2015年11月19日 下午6:12:19
 *
 */
@Singleton
public class WhiteListMgr {
  private WhiteListDao whiteListDao;
  private Set<String> ipWhiteLists;

  @Inject
  public WhiteListMgr(WhiteListDao whiteListDao) {
    this.whiteListDao = whiteListDao;
    loadData();
  }

  /**
   * 加载数据 .
   */
  public void loadData() {
    Set<String> ipWhiteListsTmp = new HashSet<>();
    List<WhiteListDomain> list = whiteListDao.selectAll();
    if (EmptyUtil.listIsNotEmpty(list)) {
      for (WhiteListDomain whiteListDomain : list) {
        ipWhiteListsTmp.add(whiteListDomain.getAccount());
      }
    }
    this.ipWhiteLists = ipWhiteListsTmp;
  }

  /**
   * 是否在白名单中 .
   * 
   * @param ip .
   * @return
   */
  public boolean isInWhiteList(String ip) {

    return ipWhiteLists.contains(ip);
  }
}
