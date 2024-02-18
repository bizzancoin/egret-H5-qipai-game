package com.idealighter.game.service.robotcfg.bo;

import lombok.Data;

@Data
public class RoomConfigBo {
  private Integer id;
  private String name;
  private boolean hasRobot;
  private Long lower;
  private Long upper;
}
