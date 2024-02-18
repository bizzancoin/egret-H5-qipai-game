package com.idealighter.game.common;

import com.idealighter.utils.check.EmptyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterMap {
  private static Map<String, String> map;

  static {
    map = new HashMap<>();
    map.put("/player", "M01");
  }

  /**
   * 是否有权限.
   * 
   * @param permissions 权限列表.
   * @param routerPrefix 路由前缀.
   * @return 结果.
   */
  public static boolean hasPermission(List<String> permissions, String routerPrefix) {
    String modelCode = map.get(routerPrefix);
    boolean result = false;
    if (EmptyUtil.stringIsNotEmpty(modelCode)) {
      result = permissions.contains(modelCode);
    }

    return result;
  }

}
