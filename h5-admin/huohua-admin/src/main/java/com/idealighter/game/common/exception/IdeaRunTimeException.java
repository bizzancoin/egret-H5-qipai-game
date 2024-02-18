package com.idealighter.game.common.exception;

import com.idealighter.game.common.ErrorCode;

public class IdeaRunTimeException extends RuntimeException {

  private ErrorCode errorCode;

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  private static final long serialVersionUID = 1586629652868323070L;

  public IdeaRunTimeException() {
    super();
  }

  public IdeaRunTimeException(ErrorCode errorCode, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(errorCode.getMessage(), cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  public IdeaRunTimeException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }

  public IdeaRunTimeException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }


}
