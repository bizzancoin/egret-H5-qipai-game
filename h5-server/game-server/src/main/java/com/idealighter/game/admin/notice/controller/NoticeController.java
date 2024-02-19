package com.idealighter.game.admin.notice.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.constant.notice.Notice;
import com.idealighter.game.core.constant.notice.NoticeType;
import com.idealighter.game.core.result.Result;
import com.idealighter.game.core.service.notice.manager.NoticeMsgMgr;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

@Path("/notice")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class NoticeController {

  @Inject
  private NoticeMsgMgr noticeMsgMgr;


  /**
   * 发送跑马灯公告.
   * 
   * @param type 1:跑马灯公告,2:聊天框系统公告,3:跑马灯和聊天框系统公告.
   * @param content 内容.
   * @return 处理结果.
   */
  @Path("/marquee")
  @POST
  public Result marqueeNotice(@NotNull @FormParam("type") Integer type,
      @NotEmpty @FormParam("content") String content, @NotEmpty @FormParam("color") String color,
      @NotNull @Min(1) @FormParam("times") Integer times, @FormParam("interval") Integer interval) {
    if (interval == null) {
      interval = Notice.DEFAULT_INTERVAL;
    }
    if (type == 1) {
      noticeMsgMgr.sendMarqueeNoticeMsg(content, NoticeType.OFFICIAL, interval,
          times, color);
    }

    return new Result(true);
  }
}
