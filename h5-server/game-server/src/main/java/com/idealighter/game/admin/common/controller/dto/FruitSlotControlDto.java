package com.idealighter.game.admin.common.controller.dto;

import lombok.Data;

@Data
public class FruitSlotControlDto {
  private int id;
  private String name;

  public FruitSlotControlDto(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
