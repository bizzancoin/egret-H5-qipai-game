package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * guice给Jersey提供的Controller注解.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface Controller {

}
