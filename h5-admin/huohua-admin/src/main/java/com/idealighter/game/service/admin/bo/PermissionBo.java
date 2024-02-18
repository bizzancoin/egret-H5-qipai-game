package com.idealighter.game.service.admin.bo;

import lombok.Data;

@Data
public class PermissionBo {
  private String id;

  private String pid;

  private String label;
  
  private Integer grade;
}
