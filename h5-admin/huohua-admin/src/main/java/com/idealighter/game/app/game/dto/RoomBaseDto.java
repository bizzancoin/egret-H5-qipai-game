package com.idealighter.game.app.game.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class RoomBaseDto {
  private Integer id;

  @NotEmpty
  @Length(min = 1, max = 255)
  private String name;

  @NotNull
  @Min(0)
  private Integer type;

  @NotNull
  @Min(1)
  private Long lower;

  @NotNull
  @Min(0)
  private Long upper;

  @NotNull
  @Min(0)
  private Integer ordinarPeople;

  @NotNull
  @Min(0)
  private Integer robotRatio;

  @NotNull
  @Min(0)
  private Integer proportionGold;

  @NotNull
  @Min(0)
  private Integer proportionChips;

  @NotNull
  @Min(0)
  private Integer tableNum;

  @NotNull
  @Min(1)
  private Integer chair;

  @NotNull
  @Min(0)
  private Integer afee;

  @NotNull
  @Min(0)
  @Max(1)
  private Integer inType;


  @NotNull
  @Min(0)
  @Max(1)
  private Byte isActive;


  private Date timeCreate;


  private Date timeOpen;

}
