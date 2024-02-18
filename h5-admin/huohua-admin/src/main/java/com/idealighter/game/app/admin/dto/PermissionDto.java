package com.idealighter.game.app.admin.dto;

import java.util.List;

import lombok.Data;

@Data
public class PermissionDto {
  private String id;
  private String label;
  private List<PermissionDto> children;
}
