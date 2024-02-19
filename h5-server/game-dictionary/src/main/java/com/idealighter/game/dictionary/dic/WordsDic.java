
package com.idealighter.game.dictionary.dic;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.WordsDomain;
import com.idealighter.game.dictionary.mapper.WordsMapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton
public class WordsDic implements DictionaryTable<WordsDomain> {

  private WordsMapper mapper;
  private List<WordsDomain> list;
  private Map<Integer, WordsDomain> map;
  // 敏感词集合
  private Set<String> words;

  @Inject
  public WordsDic(WordsMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<WordsDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, WordsDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, WordsDomain> map = new HashMap<>();
    Set<String> words = new HashSet<>();

    list.forEach(val -> {
      map.put(val.getId(), val);
      words.add(val.getWord());
    });

    this.map = map;
    this.words = words;
  }

  @Override
  public WordsDomain get(int id) {

    return map.get(id);
  }

  /**
   * 敏感词.
   * 
   * @return .
   */
  public Set<String> words() {

    return words;
  }
}
