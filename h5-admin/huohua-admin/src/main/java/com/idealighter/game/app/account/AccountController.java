package com.idealighter.game.app.account;

import com.idealighter.game.app.account.convert.AccountDtoConvert;
import com.idealighter.game.app.account.dto.AccountDto;
import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.common.Result;
import com.idealighter.game.service.account.IAccountService;
import com.idealighter.game.service.admin.bo.AdminBo;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AccountController extends BaseController {

  @Autowired
  private IAccountService accountService;

  @Autowired
  private AccountDtoConvert accountDtoConvert;

  // /**
  // * login.
  // * @Description 登录.
  // * @author cjb
  // * @date 2017年11月14日 下午9:58:43
  // * @param username 用户名.
  // * @param password 密码.
  // * @param session session.
  // * @return 登录结果.
  // */
  // @PostMapping("/login")
  // public Result login(@NotEmpty @RequestParam("username") String username,
  // @NotEmpty @RequestParam("password") String password, HttpSession session) {
  //
  // AdminBo adminBo = accountService.loginByUserPwd(username, password);
  //
  //
  // session.setAttribute(WebConstant.ADMIN_ID, adminBo.getId());
  // session.setAttribute(WebConstant.ADMIN_NAME, adminBo.getNickname());
  // session.setAttribute(WebConstant.ADMIN_ROLE, adminBo.getRole());
  //
  //
  // List<String> permission = new ArrayList<>();
  //
  // if (EmptyUtil.stringIsNotEmpty(adminBo.getPermission())) {
  // permission = JsonUtil.fromJsonToList(adminBo.getPermission(), String.class);
  // }
  //
  // session.setAttribute(WebConstant.ADMIN_PERMISSION, permission);
  //
  // AccountDto dto = accountDtoConvert.toAccountDto(adminBo);
  //
  // Result result = new Result(true);
  // result.successMg();
  // result.getMap().put("admin", dto);
  // return result;
  // }
  //
  // /**
  // * logout.
  // * @Description 登出.
  // * @author cjb
  // * @date 2017年11月14日 下午9:59:32
  // * @param session session.
  // * @return 登出结果.
  // */
  // @PostMapping("/logout")
  // public Result logout(HttpSession session) {
  // if (session != null) {
  // session.invalidate();
  // }
  //
  // return new Result(true);
  // }

  /**
   * changePassword.
   * 
   * @Description 修改密码.
   * @author cjb
   * @date 2017年11月14日 下午9:59:35
   * @param oldPassword 旧密码.
   * @param newPassword 新密码.
   * @param session session.
   * @return 修改结果.
   */
  @PostMapping("/account/changePwd")
  public Result changePassword(@NotEmpty @RequestParam("oldPassword") String oldPassword,
      @NotEmpty @RequestParam("newPassword") String newPassword, HttpSession session) {
    accountService.changePwd(getAdminId(), newPassword, oldPassword);

    if (session != null) {
      session.invalidate();
    }

    return new Result(true);
  }

  /**
   * 获取信息.
   * 
   * @param session session.
   * @return 返回管理员信息.
   */
  @GetMapping("/account/info")
  public Result userInfo(HttpSession session) {
    Integer adminId = getAdminId();

    AdminBo adminBo = accountService.findById(adminId);

    // session.setAttribute(WebConstant.ADMIN_ID, adminBo.getId());
    // session.setAttribute(WebConstant.ADMIN_NAME, adminBo.getNickname());
    // session.setAttribute(WebConstant.ADMIN_ROLE, adminBo.getRole());
    //
    //
    // List<String> permission = JsonUtil.fromJsonToList(adminBo.getPermission(), String.class);
    //
    // session.setAttribute(WebConstant.ADMIN_PERMISSION, permission);

    AccountDto dto = accountDtoConvert.toAccountDto(adminBo);

    Result result = new Result(true);
    result.successMg();
    result.getMap().put("admin", dto);
    return result;
  }

}
