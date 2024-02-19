
package com.idealighter.game.dictionary.domain;

import lombok.Data;

@Data
public class GamesDomain {

  // id
  private int id;
  // 游戏名称
  private String name;
  // 房间表
  private String roomTable;
  // 描述
  private String remark;

  private Boolean active;

}
