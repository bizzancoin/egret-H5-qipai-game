package com.idealighter.game.configuration.security.handler;

import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.configuration.security.model.DlUserDetail;
import com.idealighter.utils.json.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 登录成功后处理.
 */
@Slf4j
public class DlLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  public DlLoginSuccessHandler() {

  }


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {
    // 添加session
    DlUserDetail user = (DlUserDetail) authentication.getPrincipal();

    AdminDto adminDto = user.getAccount();

    log.info("登录成功  {}", adminDto.getUsername());

    response.setContentType("application/json;charset=utf-8");
    Result result = new Result(true);
    result.getMap().put("admin", adminDto);
    PrintWriter writer = response.getWriter();
    writer.append(JsonUtil.toJson(result));
    writer.flush();
    writer.close();
  }
}
