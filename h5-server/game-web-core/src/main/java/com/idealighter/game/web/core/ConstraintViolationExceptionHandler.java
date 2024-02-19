package com.idealighter.game.web.core;

import com.google.inject.Singleton;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.result.Result;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class ConstraintViolationExceptionHandler
    implements ExceptionMapper<ConstraintViolationException> {
  private static final Logger log =
      LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    Status status = Status.OK;
    Result result = new Result(false);
    result.changeErrorCode(ErrorCode.BAD_REQUEST);
    log.error("参数错误", exception);

    return Response.status(status).entity(result)
        .type(MediaType.APPLICATION_JSON + "; charset=utf-8").build();
  }
}
