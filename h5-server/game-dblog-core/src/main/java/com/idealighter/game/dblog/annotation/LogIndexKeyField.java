package com.idealighter.game.dblog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志索引生成策略(索引与唯一索引).
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface LogIndexKeyField {
  /**
   * 字段名称.
   * 
   * @return 字段名称.
   */
  public String field();

  /**
   * 顺序.
   * 
   * @return 顺序.
   */
  public int order() default -1;

  /**
   * 索引长度.
   * 
   * @return 索引长度.
   */
  public int len() default -1;
}
