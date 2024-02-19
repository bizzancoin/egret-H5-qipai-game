package com.idealighter.game.dblog.annotation;

import com.idealighter.game.dblog.core.IndexType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志索引生成策略(索引与唯一索引).
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(LogIndexKeys.class)
@Inherited
public @interface LogIndexKey {
  /**
   * 索引类型.
   * 
   * @return 索引类型.
   */
  public IndexType type() default IndexType.NORMAY;

  /**
   * 索引类型.
   * 
   * @return 索引名称
   */
  public String keyName();

  /**
   * 索引字段列表.
   * 
   * @return 索引字段列表.
   */
  public LogIndexKeyField[] fields();
}
