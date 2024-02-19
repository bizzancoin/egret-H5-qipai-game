package com.idealighter.game.server.net;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.ShutdownAllGameEvent;
import com.idealighter.game.core.service.log.struct.server.ShutDownServerLog;
import com.idealighter.game.core.service.log.struct.server.StartServerLog;
import com.idealighter.game.core.util.NetUtil;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.server.event.ShutdownEvent;
import com.idealighter.game.server.net.tcp.GameNettyTcpServerChannelInitializer;
import com.idealighter.game.server.net.tcp.GameNettyTcpServerService;
import com.idealighter.game.server.net.websocket.GameNettyWebSocketServerChannelInitializer;
import com.idealighter.game.server.net.websocket.GameNettyWebSocketServerService;
import com.idealighter.game.server.service.IServer;
import com.idealighter.game.server.service.ServiceName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class LocalNetService implements IServer {
  private static Logger LOG = LoggerFactory.getLogger(LocalNetService.class);
  /**
   * tcp服务器.
   * 
   * @Fields tcpServerService tcp服务器.
   */
  private GameNettyTcpServerService tcpServerService = null;
  /**
   * webSocket服务器.
   * 
   * @Fields webSocketServerService webSocket服务器.
   */
  private GameNettyWebSocketServerService webSocketServerService = null;

  // private GameNettyHttpServerService httpServerService = null;

  private GameNettyTcpServerChannelInitializer tcpChannelInitialier = null;
  private GameNettyWebSocketServerChannelInitializer webSocketChannelInitialer = null;
  // private GameNettyHttpServerChannelInitializer httpChannelInitializer = null;

  private final EventMgr eventMgr;

  @Inject
  public LocalNetService(EventMgr eventMgr) {
    this.eventMgr = eventMgr;
  }

  @Override
  public String getId() {
    return ServiceName.LocalNetService;
  }

  @Override
  public void startup() throws Exception {
    // 初始化数据库日志服务配置
    DbLogService.checkTables();
    // 开启server日志
    DbLogService.log(new StartServerLog());
    MessageFactory.init();

    // tcp启动
    if (tcpServerService != null) {
      tcpServerService.startServer();
    }
    // websocket启动
    if (webSocketServerService != null) {
      webSocketServerService.startServer();
    }
    // http启动
    // if (httpServerService != null) {
    // httpServerService.startServer();
    // }
  }

  @Override
  public void shutdown() throws Exception {

    LOG.info("----关闭登录与注册接口----");
    // tcp正在关闭
    if (tcpServerService != null && tcpServerService.isRunning()) {
      tcpChannelInitialier.closing();
    }
    // websocket正在关闭
    if (webSocketServerService != null) {
      webSocketChannelInitialer.closing();
    }
    // http正在关闭
    // if (httpServerService != null) {
    // httpChannelInitializer.closing();
    // }

    LOG.info("----关闭游戏----");
    // 发送服务器关闭事件
    eventMgr.post(new ShutdownAllGameEvent());

    LOG.info("----持久化用户信息----");
    eventMgr.post(new ShutdownEvent());

    LOG.info("----关注所有接口----");
    // tcp关闭
    if (tcpServerService != null && tcpServerService.isRunning()) {
      tcpChannelInitialier.shutdown();
      tcpServerService.stopServer();
    }
    // websocket关闭
    if (webSocketServerService != null) {
      webSocketChannelInitialer.shutdown();
      webSocketServerService.stopServer();
    }
    // // http关闭
    // if (httpServerService != null) {
    // httpChannelInitializer.shutdown();
    // httpServerService.stopServer();
    // }

    LOG.info("----关闭日志系统----");

    DbLogService.log(new ShutDownServerLog());
    DbLogService.shutdown();
  }

  /**
   * 初始化tcp服务 .
   *
   * @param tcpChannelInitialier channel初始.
   * @param port 端口.
   */
  @Inject
  public void initTcpServer(GameNettyTcpServerChannelInitializer tcpChannelInitialier,
      @Named("server.tcpPort") Integer port) {
    if (port != null) {
      // 端口是否已经被占用
      if (!NetUtil.localPortAbled(port)) {
        LOG.warn("本地端口[{}]已被占用", port);
        System.exit(1);
      }

      this.tcpChannelInitialier = tcpChannelInitialier;
      tcpServerService = new GameNettyTcpServerService(ServiceName.tcpHandlerService, port,
          "tcpServer-boss", "tcpServer-worker", this.tcpChannelInitialier);
    }

  }

  /**
   * 初始化websocket 服务 .
   *
   * @param webSocketChannelInitialer 初始。
   * @param port 端口.
   */
  @Inject
  public void initWebSocketServer(
      GameNettyWebSocketServerChannelInitializer webSocketChannelInitialer,
      @Named("server.webSocketPort") Integer port) {
    if (port != null) {
      // 端口是否已经被占用
      if (!NetUtil.localPortAbled(port)) {
        LOG.warn("本地端口[{}]已被占用", port);
        System.exit(1);
      }

      this.webSocketChannelInitialer = webSocketChannelInitialer;
      webSocketServerService =
          new GameNettyWebSocketServerService(ServiceName.webSocketHandlerService, port,
              "webSocketServer-boss", "webSocketServer-worker", this.webSocketChannelInitialer);
    }
  }
}
