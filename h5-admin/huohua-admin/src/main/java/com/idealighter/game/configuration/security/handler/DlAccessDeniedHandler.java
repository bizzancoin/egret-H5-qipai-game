package com.idealighter.game.configuration.security.handler;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.utils.json.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class DlAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    if (!response.isCommitted()) {
      HttpSession session = request.getSession(false);
      if (session != null) {
        session.invalidate();
      }
      response.setContentType("application/json;charset=utf-8");
      try {
        Result result = new Result();
        result.changeErrorCode(ErrorCode.UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.append(JsonUtil.toJson(result));
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
