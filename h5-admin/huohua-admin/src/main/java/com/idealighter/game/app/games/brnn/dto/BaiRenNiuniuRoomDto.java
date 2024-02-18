package com.idealighter.game.app.games.brnn.dto;

import com.idealighter.game.app.game.dto.RoomBaseDto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaiRenNiuniuRoomDto extends RoomBaseDto {
  @NotNull
  @Min(0)
  private Long beBankerChips;

  @NotNull
  @Min(0)
  private Long offBankerChips;
  
  @NotEmpty
  private List<Long> betOptions;
}
