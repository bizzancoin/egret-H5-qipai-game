package com.idealighter.game.app.admin;

import com.idealighter.game.app.admin.convert.AdminDtoConvert;
import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.app.admin.dto.PermissionDto;
import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.service.admin.IAdminService;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.game.service.admin.bo.PermissionBo;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController extends BaseController {

  @Autowired
  private IAdminService adminService;

  @Autowired
  private AdminDtoConvert convert;

  /**
   * 管理员列表.
   *
   * @author abin
   * @date 2018年8月18日 上午11:32:02
   * @param username 用户名.
   * @param page 页数.
   * @param pageSize 页大小.
   * @return 管理员列表.
   */
  @PreAuthorize("hasAuthority('H0101')")
  @RequestMapping("/list")
  public Result list(@RequestParam(required = false, value = "username") String username,
      @RequestParam(name = "page") Integer page,
      @RequestParam(name = "pageSize") Integer pageSize) {

    ResultPage<AdminBo> resultPage = adminService.findByPage(username, page, pageSize);

    List<AdminDto> adminDtos = convert.toAdminDtos(resultPage.getList());

    Result result = new Result(true);
    result.getMap().put("list", adminDtos);
    result.getMap().put("total", resultPage.getTotal());

    return result;
  }

  private void addSubPermission(List<PermissionDto> dtos, PermissionBo bo) {
    for (PermissionDto permissionDto : dtos) {
      if (permissionDto.getId().equals(bo.getPid())) {
        PermissionDto dto = new PermissionDto();
        dto.setChildren(new ArrayList<>());
        dto.setId(bo.getId());
        dto.setLabel(bo.getLabel());
        permissionDto.getChildren().add(dto);
      } else if (bo.getPid().startsWith(permissionDto.getId())) {
        addSubPermission(permissionDto.getChildren(), bo);
      }
    }
  }

  /**
   * 所有权限列表 .
   *
   * @author abin
   * @date 2018年8月21日 下午3:52:59
   * @return 获取权限列表.6
   */
  @RequestMapping("/permissions")
  public Result permissions() {
    List<PermissionBo> permissionBos = adminService.findPermission();

    List<PermissionDto> dtos = new ArrayList<>();
    if (EmptyUtil.listIsNotEmpty(permissionBos)) {
      for (int grade = 1; grade < 4; grade++) {
        for (PermissionBo permissionBo : permissionBos) {
          if (permissionBo.getGrade().intValue() == grade) {
            if (grade == 1) {
              PermissionDto dto = new PermissionDto();
              dto.setChildren(new ArrayList<>());
              dto.setId(permissionBo.getId());
              dto.setLabel(permissionBo.getLabel());
              dtos.add(dto);
            } else {
              addSubPermission(dtos, permissionBo);
            }
          }
        }
      }
    }

    Result result = new Result(true);
    result.getMap().put("list", dtos);
    return result;
  }

  /**
   * 添加管理员 .
   *
   * @author abin
   * @date 2018年8月18日 上午11:32:30
   * @param username 用户名.
   * @param password 密码.
   * @param nickname 昵称.
   * @return 添加结果.
   */
  @PreAuthorize("hasAuthority('H0102')")
  @PostMapping("/add")
  public Result add(@NotEmpty @Length(min = 4, max = 20) @RequestParam("username") String username,
      @NotEmpty @Length(min = 6, max = 20) @RequestParam("password") String password,
      @NotEmpty @Length(min = 2, max = 20) @RequestParam("nickname") String nickname) {

    adminService.add(username, password, nickname);

    return new Result(true);
  }

  /**
   * 更新管理员信息 .
   *
   * @author abin
   * @date 2018年8月18日 上午11:33:00
   * @param adminId 管理员id.
   * @param nickname 管理员昵称.
   * @return 更新结果.
   */
  @PreAuthorize("hasAuthority('H0102')")
  @PostMapping("/update")
  public Result update(@NonNull @RequestParam("adminId") Integer adminId,
      @NotEmpty @Length(min = 2, max = 20) @RequestParam("nickname") String nickname) {

    adminService.update(adminId, nickname);

    return new Result(true);
  }

  /**
   * 重置密码 .
   *
   * @author abin
   * @date 2018年8月18日 上午11:33:21
   * @param adminId 管理员id.
   * @param password 密码.
   * @return 重置结果.
   */
  @PreAuthorize("hasAuthority('H0104')")
  @PostMapping("/resetPassword")
  public Result resetPassword(@NonNull @RequestParam("adminId") Integer adminId,
      @NotEmpty @Length(min = 6, max = 20) @RequestParam("password") String password) {
    adminService.resetPassword(adminId, password);
    return new Result(true);
  }


  /**
   * 修改管理员 状态.
   *
   * @author abin
   * @date 2018年8月18日 上午11:33:45
   * @param adminId 管理员id.
   * @param status 状态.
   * @return 修改结果.
   */
  @PreAuthorize("hasAuthority('H0103')")
  @PostMapping("/changeStatus")
  public Result changeStatus(@NonNull @RequestParam("adminId") Integer adminId,
      @NonNull @RequestParam("status") Byte status) {
    adminService.changeStatus(adminId, status);
    return new Result(true);
  }

  /**
   * 更新权限列表 .
   *
   * @author abin
   * @date 2018年8月21日 下午5:05:27
   * @param adminId 管理员id.
   * @param permission 权限.
   * @return 更新结果.
   */
  @PreAuthorize("hasAuthority('H0105')")
  @PostMapping("/updatePermission")
  public Result updatePermission(@NonNull @RequestParam("adminId") Integer adminId,
      @NotEmpty @RequestParam("userPermission") String permission) {
    List<String> pers = JsonUtil.fromJsonToList(permission, String.class);

    IdeaAssert.notEmpty(pers);
    adminService.updatePermission(adminId, permission);

    return new Result(true);
  }

}
