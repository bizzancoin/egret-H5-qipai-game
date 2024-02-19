package com.idealighter.game.core.exception;

import com.idealighter.game.core.error.ErrorCode;

import java.util.List;

public class HuohuaRunTimeException extends RuntimeException {

  private ErrorCode errorCode;

  private List<String> args;

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<String> getArgs() {
    return args;
  }

  public void setArgs(List<String> args) {
    this.args = args;
  }

  private static final long serialVersionUID = 1586629652868323070L;

  public HuohuaRunTimeException() {
    super();
  }

  public HuohuaRunTimeException(ErrorCode errorCode, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(errorCode.getMessage(), cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  public HuohuaRunTimeException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }

  /**
   * 构造函数.
   * 
   * @param errorCode 错误码.
   * @param args 错误参数.
   */
  public HuohuaRunTimeException(ErrorCode errorCode, List<String> args) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.args = args;
  }

  public HuohuaRunTimeException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
