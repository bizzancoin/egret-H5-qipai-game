package com.idealighter.game.configuration.security.handler;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.configuration.security.utils.SecurityUtil;
import com.idealighter.utils.json.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class DlLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  public DlLoginFailureHandler() {
    super();
  }

  public DlLoginFailureHandler(String defaultFailureUrl) {
    super(defaultFailureUrl);
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      String sessionAttributeName = SecurityUtil.csrfSessionAttrbuteName();
      Object csrfToken = session.getAttribute(sessionAttributeName);
      session.invalidate();

      session = request.getSession();
      session.setAttribute(sessionAttributeName, csrfToken);
    }


    response.setContentType("application/json;charset=utf-8");
    Result result = new Result(true);
    result.changeErrorCode(ErrorCode.ACCOUNT_FAIL);
    PrintWriter writer = response.getWriter();
    writer.append(JsonUtil.toJson(result));
    writer.flush();
    writer.close();
  }

}
