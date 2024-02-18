package com.idealighter.game.app.robotcfg.dto;

import lombok.Data;

@Data
public class RoomConfigDto {
  private Integer id;
  private String name;
  private boolean hasRobot;
  private Long lower;
  private Long upper;
}
