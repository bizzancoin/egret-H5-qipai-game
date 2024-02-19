package com.idealighter.game.core.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface GameCtl {
  /**
   * 游戏控制.
   * 
   * @Title value. *
   * @author houdongsheng
   * @date 2018年1月27日 下午3:15:03
   * @return
   */
  int value();
}
