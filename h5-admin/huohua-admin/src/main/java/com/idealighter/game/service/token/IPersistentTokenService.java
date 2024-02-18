package com.idealighter.game.service.token;

import com.idealighter.game.service.token.bo.AdminPersistentLoginsBo;

import java.util.Date;

public interface IPersistentTokenService {
  AdminPersistentLoginsBo getBySeries(String series);

  void insert(AdminPersistentLoginsBo bo);

  boolean updateBySeriesAndPlatform(String token, Date lastUsed, String series, String platform);

  boolean deledeByUserNameAndPlatform(String username, String platform);
}
