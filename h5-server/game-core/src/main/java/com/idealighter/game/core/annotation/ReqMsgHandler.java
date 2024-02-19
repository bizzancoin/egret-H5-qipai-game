
package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ReqHandler注解，通过扫描ReqHandler注入到guice容器中.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface ReqMsgHandler {
  /**
   * handler id.
   * 
   * @return handler id .
   */
  int value();
}
