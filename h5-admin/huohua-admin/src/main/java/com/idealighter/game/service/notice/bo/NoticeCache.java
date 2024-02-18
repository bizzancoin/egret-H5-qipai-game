package com.idealighter.game.service.notice.bo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeCache extends NoticeBo {
  private Date sendTime;
}
