package com.idealighter.game.dblog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志表列注解.
 * 
 * @date 2015年8月27日 上午11:41:24
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Column {
  /**
   * 列类型.
   */
  public String type();

  /**
   * 列大小.
   * 
   * @return 列大小.
   */
  public int size() default 0;

  /**
   * 列备注.
   * 
   * @return 列备注.
   */
  public String remark();
}
