package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface ReqMsg {
  /**
   * 请求消息.
   * 
   * @Title value.
   * @author cjb .
   * @date 2017年11月15日 上午10:28:55
   * @return .
   */
  int value();
}
