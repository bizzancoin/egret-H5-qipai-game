package com.idealighter.game.app.notice.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class NoticeDto {
  private Integer id;

  @NotNull
  private Date startTime;

  @NotNull
  private Date endTime;

  @NotNull
  private Boolean isMarquee;

  @NotNull
  private Boolean isNotice;

  private Integer intervalTime;

  @NotEmpty
  @Length(min = 1, max = 255)
  private String content;

  @NotNull
  private Integer times;

  @NotEmpty
  @Length(min = 1, max = 8)
  private String color;
}
