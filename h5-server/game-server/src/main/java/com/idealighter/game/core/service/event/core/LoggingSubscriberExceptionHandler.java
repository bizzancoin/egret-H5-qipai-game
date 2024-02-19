
package com.idealighter.game.core.service.event.core;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * guava . EventBus的默认发布事件处理异常处理器LoggingSubscriberExceptionHandler没有记录异常堆栈，不能很好的定位问题
 * 
 * @date 2015年10月22日 上午10:26:06
 *
 */
public class LoggingSubscriberExceptionHandler implements SubscriberExceptionHandler {

  private static final Logger LOG =
      LoggerFactory.getLogger(LoggingSubscriberExceptionHandler.class);

  @Override
  public void handleException(Throwable exception, SubscriberExceptionContext context) {
    LOG.error("Could not dispatch event: " + context.getSubscriber() + " to "
        + context.getSubscriberMethod(), exception);
  }

}
