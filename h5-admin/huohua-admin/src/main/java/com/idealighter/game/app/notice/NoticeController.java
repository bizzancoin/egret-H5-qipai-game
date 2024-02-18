package com.idealighter.game.app.notice;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.notice.convert.NoticeDtoConvert;
import com.idealighter.game.app.notice.dto.NoticeDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.notice.INoticeCacheService;
import com.idealighter.game.service.notice.INoticeService;
import com.idealighter.game.service.notice.bo.NoticeBo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@Validated
public class NoticeController extends BaseController {

  @Autowired
  private INoticeService noticeService;

  @Autowired
  private INoticeCacheService noticeCacheService;

  @Autowired
  private NoticeDtoConvert noticeDtoConvert;


  /**
   * 获取通知列表.
   * 
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('A0301')")
  @GetMapping("/list")
  public Result list() {

    List<NoticeBo> bos = noticeService.findAllNotice();

    List<NoticeDto> dtos = noticeDtoConvert.toNoticeDtos(bos);

    Result result = new Result(true);

    result.getMap().put("list", dtos);

    return result;
  }

  /**
   * 保存通知.
   * 
   * @param dto 通知.
   * @return 保存结果.
   */
  @PreAuthorize("hasAuthority('A0303')")
  @PostMapping("/save")
  public Result save(@RequestBody @Valid NoticeDto dto) {
    NoticeBo bo = noticeDtoConvert.toNoticeBo(dto);
    noticeService.save(bo);
    noticeCacheService.loadCacheNotice(true);
    return new Result(true);
  }

  /**
   * 直接发送通知.
   * 
   * @param isMarquee 是否发送通告.
   * @param isNotice 是否发送聊天通知.
   * @param content 内容.
   * @param times 次数.
   * @param color 颜色.
   * @return 发送结果.
   */
  @PreAuthorize("hasAuthority('A0302')")
  @PostMapping("/send")
  public Result send(@NotNull @RequestParam("isMarquee") Boolean isMarquee,
      @NotNull @RequestParam("isNotice") Boolean isNotice,
      @NotNull @Length(min = 1, max = 255) @RequestParam("content") String content,
      @NotNull @Min(1) @RequestParam("times") Integer times,
      @NotEmpty @Length(min = 1, max = 8) @RequestParam("color") String color,
      @NotNull @Min(1) @RequestParam("intervalTime") Integer intervalTime) {

    int num = 0;
    if (isMarquee) {
      num = 1;
    }
    if (isNotice) {
      num = 2;
    }
    if (isNotice && isMarquee) {
      num = 3;
    }
    if (num > 0) {
      RequestParamBuild paramBuild = new RequestParamBuild();
      paramBuild.add("type", num);
      paramBuild.add("content", content);
      paramBuild.add("color", color);
      paramBuild.add("times", times);

      paramBuild.add("interval", intervalTime);
      GameServerRequest.post(GameServerUrl.NOTICE_MARQUEE, paramBuild);
    }
    return new Result(true);
  }

  /**
   * 删除通知.
   * 
   * @param id id.
   * @return 删除结果.
   */
  @PreAuthorize("hasAuthority('A0304')")
  @PostMapping("/delete")
  public Result delete(@RequestParam("id") Integer id) {
    noticeService.delete(id);
    noticeCacheService.loadCacheNotice(true);
    return new Result(true);
  }

}
