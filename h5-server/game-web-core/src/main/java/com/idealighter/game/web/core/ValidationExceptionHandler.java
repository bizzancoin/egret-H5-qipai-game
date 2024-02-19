package com.idealighter.game.web.core;

import com.google.inject.Singleton;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.result.Result;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
  private static final Logger log = LoggerFactory.getLogger(ValidationExceptionHandler.class);

  @Override
  public Response toResponse(ValidationException exception) {

    if (exception instanceof ResteasyViolationException) {
      if (log.isInfoEnabled()) {
        log.error("数据不正确", exception);
        exception.printStackTrace();
      } else {
        log.error("数据不正确");
      }
    }

    return Response.ok().entity(new Result(ErrorCode.BAD_REQUEST))
        .type(MediaType.APPLICATION_JSON + "; charset=utf-8").build();
  }

}
