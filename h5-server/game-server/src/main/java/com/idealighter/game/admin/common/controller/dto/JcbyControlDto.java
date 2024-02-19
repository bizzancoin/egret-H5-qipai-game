package com.idealighter.game.admin.common.controller.dto;

import lombok.Data;

@Data
public class JcbyControlDto {
  private int id;
  private String name;

  public JcbyControlDto(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
