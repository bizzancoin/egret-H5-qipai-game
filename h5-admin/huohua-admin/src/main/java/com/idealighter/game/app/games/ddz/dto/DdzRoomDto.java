package com.idealighter.game.app.games.ddz.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.idealighter.game.app.game.dto.RoomBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DdzRoomDto extends RoomBaseDto {
  @NotNull
  @Min(1)
  private Long base;
}
