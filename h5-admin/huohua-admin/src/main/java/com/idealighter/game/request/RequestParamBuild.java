package com.idealighter.game.request;

import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.json.JsonUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestParamBuild {

  private Map<String, String> map;

  public RequestParamBuild() {
    map = new HashMap<>();
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, String value) {
    if (value != null) {
      this.map.put(key, value);
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, Byte value) {
    if (value != null) {
      this.map.put(key, value.toString());
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, Short value) {
    if (value != null) {
      this.map.put(key, value.toString());
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, Integer value) {
    if (value != null) {
      this.map.put(key, value.toString());
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, Long value) {
    if (value != null) {
      this.map.put(key, value.toString());
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param values 数值.
   */
  public void add(String key, List<Long> values) {
    if (EmptyUtil.listIsNotEmpty(values)) {
      this.map.put(key, JsonUtil.toJson(values));
    }
  }

  /**
   * 添加.
   * 
   * @param key key.
   * @param value value.
   */
  public void add(String key, Date value) {
    if (value != null) {
      this.map.put(key, String.valueOf(value.getTime()));
    }
  }
}
