package com.idealighter.game.admin.common.controller.dto;

import lombok.Data;

@Data
public class LkbyControlDto {
  private int id;
  private String name;

  public LkbyControlDto(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
