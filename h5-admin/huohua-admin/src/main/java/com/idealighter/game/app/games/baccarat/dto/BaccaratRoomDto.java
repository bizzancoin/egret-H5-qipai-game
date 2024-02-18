package com.idealighter.game.app.games.baccarat.dto;

import com.idealighter.game.app.game.dto.RoomBaseDto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaccaratRoomDto extends RoomBaseDto {

  @NotEmpty
  private List<Long> betOptions;

}
