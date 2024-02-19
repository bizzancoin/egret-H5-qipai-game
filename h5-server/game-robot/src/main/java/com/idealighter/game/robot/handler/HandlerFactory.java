
package com.idealighter.game.robot.handler;

import com.idealighter.game.core.annotation.ResMsgHandler;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.robot.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.ResolverUtil.AnnotatedWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler工厂.
 *
 */
public class HandlerFactory {

  private static final Logger LOG = LoggerFactory.getLogger(HandlerFactory.class);

  // 扫描handler的包名
  private static final String HANDLER_PACKAGE_NAME = "com.idealighter.game";
  // HANDLER_PACKAGE_NAME下所有的reqMsgHanler实例
  private static final Map<Integer, ResMessageHandler> resMsgHanlers = new HashMap<>();

  private HandlerFactory() {}

  /*
   * 扫描package查找ReqMessageHandler注册到guice中
   */
  static {
    ResolverUtil<ResMessageHandler> hamdlerResolver = new ResolverUtil<ResMessageHandler>()
        .find(new AnnotatedWith(ResMsgHandler.class), HANDLER_PACKAGE_NAME);

    final ApplicationContext applicationContext = ApplicationContext.getInstance();
    try {
      Set<Class<? extends ResMessageHandler>> handlerClasses = hamdlerResolver.getClasses();
      for (Class<? extends ResMessageHandler> handlerClass : handlerClasses) {
        ResMsgHandler resMsgHandler = handlerClass.getAnnotation(ResMsgHandler.class);

        if (resMsgHanlers.containsKey(resMsgHandler.value())) {
          LOG.error("消息处理[{}]的id和消息处理[{}]重复,请检查配置", handlerClass.getName(),
              resMsgHanlers.get(resMsgHandler.value()).getClass().getName());
          System.exit(1);
        }

        resMsgHanlers.put(resMsgHandler.value(), handlerClass.newInstance());
      }
    } catch (Exception e) {
      LOG.error("扫描" + HANDLER_PACKAGE_NAME + "下的ReqMessageHandler异常", e);
      System.exit(1);
    }

    // 手动注入guic依赖
    resMsgHanlers.values().forEach((handler) -> applicationContext.injectMembers(handler));
  }

  /**
   * 获取handler实例.
   * 
   * @param id .
   * @return .
   */
  public static ResMessageHandler getHandler(int id) {
    if (!resMsgHanlers.containsKey(id)) {
      return resMsgHanlers.get(ModuleMsgIdConstant.DEFAULT_RES);
    } else {
      return resMsgHanlers.get(id);
    }

  }
}
