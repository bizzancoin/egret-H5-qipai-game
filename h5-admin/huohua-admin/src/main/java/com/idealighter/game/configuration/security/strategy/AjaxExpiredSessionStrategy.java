package com.idealighter.game.configuration.security.strategy;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.utils.json.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Slf4j
public class AjaxExpiredSessionStrategy implements SessionInformationExpiredStrategy {

  @Override
  public void onExpiredSessionDetected(SessionInformationExpiredEvent event)
      throws IOException, ServletException {
    log.info("触发 过期策略");
    event.getResponse().setContentType("application/json;charset=utf-8");

    try {
      Result result = new Result();
      result.changeErrorCode(ErrorCode.UNAUTHORIZED);
      PrintWriter writer = event.getResponse().getWriter();
      writer.append(JsonUtil.toJson(result));
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
