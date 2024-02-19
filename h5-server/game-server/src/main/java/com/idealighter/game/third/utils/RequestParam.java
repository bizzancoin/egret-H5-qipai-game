package com.idealighter.game.third.utils;

import java.util.Map;

import lombok.Data;

@Data
public class RequestParam {

  private Map<String, String> map;

  public RequestParam(Map<String, String> map) {
    this.map = map;
  }

  public String get(String key) {
    return map.get(key);
  }

  public Integer getInteger(String key) {
    String temp = map.get(key);
    return temp == null || temp.isEmpty() ? null : Integer.valueOf(temp);
  }

  public Boolean getBoolean(String key) {
    String temp = map.get(key);
    return temp == null || temp.isEmpty() ? null : Boolean.valueOf(temp);
  }


  public Long getLong(String key) {
    String temp = map.get(key);
    return temp == null || temp.isEmpty() ? null : Long.valueOf(temp);
  }

  public Byte getByte(String key) {
    String temp = map.get(key);
    return temp == null || temp.isEmpty() ? null : Byte.valueOf(temp);
  }

  public Short getShort(String key) {
    String temp = map.get(key);
    return temp == null || temp.isEmpty() ? null : Short.valueOf(temp);
  }


}
