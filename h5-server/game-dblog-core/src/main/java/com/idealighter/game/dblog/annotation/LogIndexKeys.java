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
@Target(ElementType.TYPE)
@Inherited
public @interface LogIndexKeys {
  /**
   * 索引列表.
   * 
   * @return 索引列表.
   */
  public LogIndexKey[] value() default {};
}
