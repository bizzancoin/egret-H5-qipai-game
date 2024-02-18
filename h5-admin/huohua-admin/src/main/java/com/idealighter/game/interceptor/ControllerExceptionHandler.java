package com.idealighter.game.interceptor;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.exception.IdeaRunTimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

  private static Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  /**
   * exception. 统一异常处理路口 .
   * 
   * @author LinYang
   * @date 2015年7月20日 下午2:12:30
   * @param exception Exception
   * @return String
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Result exception(Exception exception) {
    log.error("异常了", exception);
    Result result = new Result();
    result.changeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
    return result;
  }


  /**
   * 没有权限 .
   *
   * @author abin
   * @date 2018年8月18日 下午5:51:58
   * @param accessDeniedException 没有权限.
   * @return 异常结果.
   */
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseBody
  public Result exception(AccessDeniedException accessDeniedException) {
    log.error("没有访问权限", accessDeniedException);
    Result result = new Result();
    result.changeErrorCode(ErrorCode.UNAUTHORIZED);
    return result;
  }

  /**
   * idea异常.
   * 
   * @param ideaRunTimeException .
   * @return .
   */
  @ExceptionHandler(IdeaRunTimeException.class)
  @ResponseBody
  public Result ideaException(IdeaRunTimeException ideaRunTimeException) {
    Result result = new Result();
    if (log.isDebugEnabled()) {
      log.debug("自定义异常[{}] {}", ideaRunTimeException.getErrorCode().getCode(),
          ideaRunTimeException.getErrorCode().getMessage(), ideaRunTimeException);
    }
    result.changeErrorCode(ideaRunTimeException.getErrorCode());
    return result;
  }


  /**
   * resteasy 校验错误.
   * 
   * @param exception 异常.
   * @return 异常拦截结果.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public Result ideaException(MethodArgumentNotValidException exception) {
    Result result = new Result();
    if (log.isDebugEnabled()) {
      log.debug("参数错误异常", exception);
    }
    result.changeErrorCode(ErrorCode.BAD_REQUEST);
    return result;
  }

}
