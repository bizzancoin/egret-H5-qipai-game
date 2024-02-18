package com.idealighter.game.app.mail;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.mail.convert.MailDtoConvert;
import com.idealighter.game.app.mail.dto.MailDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.mail.IMailService;
import com.idealighter.game.service.mail.bo.MailBo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Validated
public class MailController extends BaseController {

  @Autowired
  private IMailService mailService;

  @Autowired
  private MailDtoConvert mailDtoConvert;

  /**
   * 邮件列表.
   * 
   * @param page 页数.
   * @param pageSize 页码.
   * @return 邮件列表.
   */
  @GetMapping("/list")
  public Result selectMailList(@RequestParam(required = false, name = "playerId") Long playerId,
      @Min(1) @RequestParam("page") int page, @Min(1) @RequestParam("pageSize") int pageSize) {

    List<MailBo> bos = mailService.findByPage(playerId, page, pageSize);
    List<MailDto> dtos = mailDtoConvert.toMailDtos(bos);

    long total = mailService.count(playerId);

    Result result = new Result(true);
    result.getMap().put("list", dtos);
    result.getMap().put("total", total);

    return result;
  }

  /**
   * 添加邮件.
   * 
   * @param dto 邮件.
   * @return 结果.
   */
  @PostMapping("/add")
  public Result addMail(@NotNull @RequestBody MailDto dto) {

    MailBo bo = mailDtoConvert.toMailBo(dto);

    Result result = new Result(false);
    int out = mailService.add(bo, getAdminSessionInfo());
    if (out > 0) {
      result.successMg();

      RequestParamBuild paramBuild = new RequestParamBuild();
      paramBuild.add("playerId", dto.getPlayerId());
      GameServerRequest.post(GameServerUrl.NEW_EMAIL, paramBuild);
    }

    return result;
  }

  /**
   * 删除邮件.
   * 
   * @param ids id列表.
   * @return 结果.
   */
  @PostMapping("/delete")
  public Result delete(@NotEmpty @RequestBody List<Integer> ids) {
    Result result = new Result(false);

    int out = mailService.delete(ids);
    if (out > 0) {
      result.successMg();
    }
    return result;
  }


}
