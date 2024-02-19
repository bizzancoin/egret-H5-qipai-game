
package com.idealighter.game.web.core;

import com.google.inject.Binding;

import com.idealighter.game.core.util.NetUtil;

import java.util.Collection;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供web服务的服务器.
 *
 */
public class WebServer extends Thread {
  private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

  // 是否停服，停服后不再接受消息处理
  // private boolean shutdown = false;
  private NettyJaxrsServer server = null;
  private Dispatcher dispatcher = null;
  private Collection<Binding<?>> allMembers = null;

  private int port;

  public WebServer() {}

  /**
   * 初始设置.
   * 
   * @Title init.
   * @author houdongsheng
   * @date 2018年1月31日 下午6:03:38
   * @param threadName 线程名称
   * @param port 端口
   * @param allMembers guice中的所有bean
   */
  public void init(String threadName, int port, Collection<Binding<?>> allMembers) {
    super.setName(threadName);
    this.port = port;
    this.allMembers = allMembers;
  }

  @Override
  public void run() {
    if (server != null) {
      server.stop();
      server = null;
      dispatcher = null;
    }

    // 端口是否已经被占用
    if (!NetUtil.localPortAbled(port)) {
      LOG.warn("本地端口[{}]已被占用", port);
      System.exit(1);
    }

    server = new NettyJaxrsServer();
    server.setPort(port);
    server.setRootResourcePath("/");
    server.start();
    dispatcher = server.getDeployment().getDispatcher();
    LOG.info("监听web端口[{}]成功", port);

    ModuleProcessor processor =
        new ModuleProcessor(dispatcher.getRegistry(), dispatcher.getProviderFactory());
    processor.registerMembers(allMembers);
  }

  /**
   * 关闭 WebServer .
   */
  public void shutdown() {
    LOG.info("----关闭 {} 开始----", getName());
    if (server != null) {
      server.stop();
      server = null;
      dispatcher = null;
    }

    LOG.info("----关闭 {}  完成----", getName());
  }
}
