package com.idealighter.game.service.notice.bo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeBo {
  private Integer id;

  private Date startTime;

  private Date endTime;

  private Boolean isMarquee;

  private Boolean isNotice;

  private Integer intervalTime;

  private String content;

  private Integer times;

  private String color;
}
