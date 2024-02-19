package com.idealighter.game.dictionary.core;

import java.util.List;
import java.util.Map;

/**
 * 字典表.
 * 
 * @date 2015年7月26日 上午1:35:25
 *
 */
public interface DictionaryTable<E> {

  /**
   * list方式返回字典数据.
   * 
   * @return .
   */
  List<E> list();

  /**
   * map方式返回字典数据,key为主键.
   * 
   * @return .
   */
  Map<Integer, E> map();

  /**
   * 加载数据 .
   */
  void load();

  /**
   * 加载数据 .
   */
  E get(int id);
}
