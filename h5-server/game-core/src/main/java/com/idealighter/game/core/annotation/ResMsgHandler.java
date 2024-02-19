

package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ResHandler注解，通过扫描ResHandler注入到guice容器中.
 * 
 * @date 2015年8月2日 下午4:22:07
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface ResMsgHandler {
  /**
   * 回复消息处理.
   * 
   * @return 回复消息处理 .
   */
  int value();
}
