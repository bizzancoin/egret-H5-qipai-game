package com.idealighter.game.app.games.jcby.dto;

import com.idealighter.game.app.game.dto.RoomBaseDto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class JcbyRoomDto extends RoomBaseDto {

  @NotEmpty
  private List<Integer> scences;

  private Integer liKui = 0;

 

  @NotNull
  @Min(1)
  private Integer powerBatteryMultiple;

  @NotNull
  @Min(1)
  private Integer powerBatteryPro;

  @NotNull
  @Min(1)
  private Integer powerBatteryTime;
}
