package com.idealighter.game.service.token.bo;

public class AdminPersistentLoginsBo {
  private String username;
  private String series;
  private String token;
  private Long lastUsed;
  private String platform;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getLastUsed() {
    return lastUsed;
  }

  public void setLastUsed(Long lastUsed) {
    this.lastUsed = lastUsed;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }


}
