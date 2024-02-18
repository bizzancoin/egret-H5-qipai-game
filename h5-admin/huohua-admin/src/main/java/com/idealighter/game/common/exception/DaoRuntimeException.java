package com.idealighter.game.common.exception;

public class DaoRuntimeException extends RuntimeException {

  private static final long serialVersionUID = -655208206057551395L;

  public DaoRuntimeException() {
    super();
  }

  public DaoRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public DaoRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoRuntimeException(String message) {
    super(message);
  }

  public DaoRuntimeException(Throwable cause) {
    super(cause);
  }



}
