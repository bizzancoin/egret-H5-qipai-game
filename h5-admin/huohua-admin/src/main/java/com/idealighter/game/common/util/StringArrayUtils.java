package com.idealighter.game.common.util;

import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

public class StringArrayUtils {

  /**
   * string 转 list.
   * 
   * @author abin
   * @date 2018年3月16日 下午4:01:48
   * @param str 字符串.
   * @param split 分隔符.
   * @return 结果.
   */
  public static List<Integer> toIntegerArray(String str, String split) {
    List<Integer> toList = null;
    if (EmptyUtil.stringIsNotEmpty(str)) {
      toList = new ArrayList<>();
      String[] arrays = str.split(split);
      try {
        if (arrays != null && arrays.length > 0) {
          for (String value : arrays) {

            toList.add(Integer.parseInt(value));
          }
        }
      } catch (Exception ex) {
        toList = null;
      }
    }
    return toList;
  }

  /**
   * fromIntegerArray int数组转string.
   * 
   * @author abin
   * @date 2018年3月16日 下午4:02:27
   * @param values 数组列表.
   * @param split 分隔符.
   * @return 结果字符串.
   */
  public static String fromIntegerArray(List<Integer> values, String split) {
    String result = null;
    if (EmptyUtil.listIsNotEmpty(values)) {
      result = ""; 
      for (Integer value : values) {
        result += value + split;
      }
      if (result.endsWith(split)) {
        result = result.substring(0, result.length() - 1);
      }
    }
    return result;
  }



}
