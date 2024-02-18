package com.idealighter.game.configuration.security.repository;

import com.idealighter.game.service.token.IPersistentTokenService;
import com.idealighter.game.service.token.bo.AdminPersistentLoginsBo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class DlPersistentTokenRepository implements PersistentTokenRepository {

  private String platform;

  public DlPersistentTokenRepository() {
    platform = "admin";
  }

  @Autowired
  private IPersistentTokenService persistentTokenService;

  @Override
  public void createNewToken(PersistentRememberMeToken token) {
    // 删除其他账号，再添加新的账号
    removeUserTokens(token.getUsername());

    AdminPersistentLoginsBo bo = new AdminPersistentLoginsBo();
    bo.setLastUsed(token.getDate().getTime());
    bo.setPlatform(platform);
    bo.setSeries(token.getSeries());
    bo.setToken(token.getTokenValue());
    bo.setUsername(token.getUsername());
    persistentTokenService.insert(bo);
  }

  @Override
  public void updateToken(String series, String tokenValue, Date lastUsed) {
    persistentTokenService.updateBySeriesAndPlatform(tokenValue, new Timestamp(lastUsed.getTime()),
        series, platform);

  }

  @Override
  public PersistentRememberMeToken getTokenForSeries(String seriesId) {
    PersistentRememberMeToken token = null;
    AdminPersistentLoginsBo bo = persistentTokenService.getBySeries(seriesId);
    if (bo != null) {
      token = new PersistentRememberMeToken(bo.getUsername(), bo.getSeries(), bo.getToken(),
          new Date(bo.getLastUsed()));
    }
    return token;
  }

  @Override
  public void removeUserTokens(String username) {
    persistentTokenService.deledeByUserNameAndPlatform(username, platform);
  }


}
