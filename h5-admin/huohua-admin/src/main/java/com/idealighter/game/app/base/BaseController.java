/**
 * Project Name:temple-app <br>
 * File Name:BaseController.java <br>
 * Package Name:com.idealighter.temple.app.base <br>
 * Date:2016年12月15日下午9:12:58 <br>
 * Copyright (c) 2016, www.idealighter.com All Rights Reserved.
 *
 */

package com.idealighter.game.app.base;

import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.configuration.security.model.DlUserDetail;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ClassName: BaseController <br>
 * Description: base Controller. <br>
 * Date: 2016年12月15日 下午9:12:58 <br>
 *
 * @author cjb
 * @version
 */
public class BaseController {

  protected Integer getAdminId() {
    DlUserDetail detail =
        (DlUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    AdminDto adminDto = detail.getAccount();

    return adminDto.getId();
  }

  protected String getAdminName() {
    DlUserDetail detail =
        (DlUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    AdminDto adminDto = detail.getAccount();

    return adminDto.getNickname();
  }

  protected AdminSessionInfo getAdminSessionInfo() {
    DlUserDetail detail =
        (DlUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    AdminDto adminDto = detail.getAccount();
    AdminSessionInfo adminSessionInfo = new AdminSessionInfo();
    adminSessionInfo.setAdminId(adminDto.getId());
    adminSessionInfo.setNickname(adminDto.getNickname());
    return adminSessionInfo;
  }

}
