package com.idealighter.game.app.search.dto;

import lombok.Data;

@Data
public class SearchPlayerDto {
  private Long playerId;
  private Long playerSuperId;
  private String playerName;
  private String phone;
}
