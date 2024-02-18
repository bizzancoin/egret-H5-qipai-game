package com.idealighter.game.app.channel;

import com.idealighter.game.app.channel.convert.ThirdChannelDtoConvert;
import com.idealighter.game.app.channel.dto.ThirdChannelDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.channel.IThirdChannelService;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
@RequestMapping("/channel")
@Validated
public class ThirdChannelController {

  @Autowired
  private IThirdChannelService thirdChannelService;

  @Autowired
  private ThirdChannelDtoConvert convert;

  /**
   * 渠道 列表 .
   *
   * @author abin
   * @date 2018年8月10日 下午3:42:04
   * @param channelId 渠道id.
   * @param channelName 渠道名称.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 渠道列表.
   */
  @PreAuthorize("hasAuthority('G0101')")
  @GetMapping("/list")
  public Result list(@RequestParam(required = false, value = "channelId") String channelId,
      @RequestParam(required = false, value = "channelName") String channelName,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {

    ResultPage<ThirdChannelBo> resultPage =
        thirdChannelService.findByPage(channelId, channelName, page, pageSize);

    Result result = new Result(true);
    result.getMap().put("list", resultPage.getList());
    result.getMap().put("total", resultPage.getTotal());

    return result;

  }

  /**
   * 保存渠道 .
   *
   * @author abin
   * @date 2018年8月10日 下午3:42:45
   * @param dto 渠道.
   * @return 保存结果.
   */
  @PreAuthorize("hasAuthority('G0102')")
  @PostMapping("/save")
  public Result save(@NotNull @Valid @RequestBody ThirdChannelDto dto) {

    ThirdChannelBo thirdChannelBo = convert.toThirdChannelBo(dto);
    thirdChannelService.save(thirdChannelBo);

    return new Result(true);
  }

  /**
   * 删除渠道 .
   *
   * @author abin
   * @date 2018年8月10日 下午5:29:49
   * @param channelId 渠道id.
   * @return 删除结果.
   */
  @PreAuthorize("hasAuthority('G0103')")
  @PostMapping("/delete")
  public Result delete(@NotNull @RequestParam("channelId") String channelId) {
    thirdChannelService.delete(channelId);
    return new Result(true);
  }
}
