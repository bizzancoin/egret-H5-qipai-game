package com.idealighter.game.robot.common.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/10/29.
 */
public class MapList<K, V> implements Iterable<List<V>> {
  private final Map<K, List<V>> mapList = new HashMap<K, List<V>>();

  public Iterator<List<V>> iterator() {
    return mapList.values().iterator();
  }

  /**
   * 添加.
   * 
   * @param key .
   * @param value .
   * @return .
   */
  public V add(K key, V value) {
    List<V> values = mapList.get(key);

    if (values == null) {
      values = new ArrayList<V>();
      mapList.put(key, values);
    }

    values.add(value);
    return value;
  }

  public Set<K> keySet() {
    return mapList.keySet();
  }

  public Set<Map.Entry<K, List<V>>> entrySet() {
    return mapList.entrySet();
  }

  public List<V> get(K key) {
    return mapList.get(key);
  }
}
