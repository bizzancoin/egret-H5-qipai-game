package com.idealighter.game.admin.common.controller.dto;

import lombok.Data;

@Data
public class ShuiHuControlDto {
  private int id;
  private String name;

  public ShuiHuControlDto(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
