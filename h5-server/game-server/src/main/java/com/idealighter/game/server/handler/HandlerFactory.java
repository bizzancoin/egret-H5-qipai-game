package com.idealighter.game.server.handler;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.core.annotation.ReqMsgHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HandlerFactory .
 * 
 * @date 2015年7月30日 下午1:58:33
 *
 */
public class HandlerFactory {

  private static final Logger LOG = LoggerFactory.getLogger(HandlerFactory.class);

  // 扫描handler的包名
  private static final String HANDLER_PACKAGE_NAME = "com.idealighter.game";
  // HANDLER_PACKAGE_NAME下所有的reqMsgHanler实例
  private static final Map<Integer, ReqMessageHandler> reqMsgHanlers = new HashMap<>();

  private HandlerFactory() {}

  /*
   * 扫描package查找ReqMessageHandler注册到guice中 .
   */
  static {
    final ApplicationContext applicationContext = ApplicationContext.getInstance();
    ResolverUtil<ReqMessageHandler> hamdlerResolver = new ResolverUtil<ReqMessageHandler>()
        .find(new AnnotatedWith(ReqMsgHandler.class), HANDLER_PACKAGE_NAME);

    try {
      Set<Class<? extends ReqMessageHandler>> handlerClasses = hamdlerResolver.getClasses();
      for (Class<? extends ReqMessageHandler> handlerClass : handlerClasses) {
        ReqMsgHandler reqMsgHandler = handlerClass.getAnnotation(ReqMsgHandler.class);

        if (reqMsgHanlers.containsKey(reqMsgHandler.value())) {
          LOG.error("消息处理[{}]的id和消息处理[{}]重复,请检查配置", handlerClass.getName(),
              reqMsgHanlers.get(reqMsgHandler.value()).getClass().getName());
          System.exit(1);
        }

        reqMsgHanlers.put(reqMsgHandler.value(), handlerClass.newInstance());
      }
    } catch (Exception e) {
      LOG.error("扫描" + HANDLER_PACKAGE_NAME + "下的ReqMessageHandler异常", e);
      System.exit(1);
    }

    // 手动注入guic依赖
    reqMsgHanlers.values().forEach((handler) -> applicationContext.injectMembers(handler));
  }

  /**
   * 获取handler实例.
   * 
   * @param id . id.
   * @return 请求消息处理.
   */
  public static final ReqMessageHandler getHandler(int id) {

    return reqMsgHanlers.get(id);
  }

}
