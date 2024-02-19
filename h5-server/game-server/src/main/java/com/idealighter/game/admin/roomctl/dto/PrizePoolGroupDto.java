package com.idealighter.game.admin.roomctl.dto;

import java.util.List;

import lombok.Data;

@Data
public class PrizePoolGroupDto {
  private DefaultPrizePoolDto defaultPrizePool;
  private List<GoldRangePrizePoolDto> goldPrizePoolGroups;
}
