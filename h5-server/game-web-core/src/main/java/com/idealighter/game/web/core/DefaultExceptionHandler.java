package com.idealighter.game.web.core;

import com.google.inject.Singleton;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.exception.HuohuaRunTimeException;
import com.idealighter.game.core.exception.IdeaRunTimeException;
import com.idealighter.game.core.result.Result;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {
  private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

  @Override
  public Response toResponse(Exception exception) {

    Status status = Status.BAD_REQUEST;
    Result result = new Result(false);
    if (exception instanceof IllegalArgumentException
        || exception instanceof NumberFormatException) {
      status = Status.OK;
      result.changeErrorCode(ErrorCode.BAD_REQUEST);
      log.error("参数错误", exception);
    } else if (exception instanceof ConstraintViolationException) {
      status = Status.OK;
      result.changeErrorCode(ErrorCode.BAD_REQUEST);
      log.error("参数错误", exception);
    } else if (exception instanceof NullPointerException) {
      status = Status.OK;
      result.changeErrorCode(ErrorCode.BAD_REQUEST);
      log.error("空指针异常", exception);
    } else if (exception instanceof NotAuthorizedException) {
      status = Status.UNAUTHORIZED;
      result.changeErrorCode(ErrorCode.UNAUTHORIZED);
      log.error("没有权限", exception);
    } else if (exception instanceof IdeaRunTimeException) {
      status = Status.OK;
      IdeaRunTimeException runTimeException = (IdeaRunTimeException) exception;
      result.changeErrorCode(runTimeException.getErrorCode());
      log.error("业务异常", exception);
    } else if (exception instanceof HuohuaRunTimeException) {
      status = Status.OK;
      HuohuaRunTimeException runTimeException = (HuohuaRunTimeException) exception;
      result.changeErrorCode(runTimeException.getErrorCode(), runTimeException.getArgs());
      log.error("业务异常", exception);
    } else {
      log.error("异常", exception);
    }

    return Response.status(status).entity(result)
        .type(MediaType.APPLICATION_JSON + "; charset=utf-8").build();
  }

}
