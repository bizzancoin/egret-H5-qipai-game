package com.idealighter.game.core.service.event.manager;

import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;

import com.idealighter.game.core.service.event.core.LoggingSubscriberExceptionHandler;

/**
 * 基于google . EventBus的事件管理器
 * 
 * @date 2015年8月1日 下午10:42:58
 *
 */
@Singleton
public class EventMgr extends EventBus {

  public EventMgr() {
    // guava EventBus的默认发布事件处理异常处理器LoggingSubscriberExceptionHandler没有记录异常堆栈，不能很好的定位问题
    super(new LoggingSubscriberExceptionHandler());
  }

}

