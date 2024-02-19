
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.SysconfigDomain;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SysconfigMapper {

  @Select("select * from sysconfig")
  List<SysconfigDomain> selectAll();
}
