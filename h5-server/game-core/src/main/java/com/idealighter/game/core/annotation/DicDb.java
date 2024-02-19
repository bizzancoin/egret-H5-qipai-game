package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用Dic字典数据库的sqlsession时用的注解.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, PARAMETER })
@BindingAnnotation
public @interface DicDb {

}
