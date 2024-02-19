package com.idealighter.game.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.server.net.LocalNetService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏服务器 .
 * 
 * @date 2015年7月25日 下午5:14:49
 *
 */
@Singleton
public class GameServer extends Thread {
  private static final Logger LOG = LoggerFactory.getLogger(GameServer.class);
  private LocalNetService localNetService;

  /**
   * 构造函数 .
   * 
   * @param localNetService 本地网络服务.
   */
  @Inject
  public GameServer(LocalNetService localNetService) {
    super("gameServer");
    this.localNetService = localNetService;
  }

  @Override
  public void run() {
    try {
      this.localNetService.startup();
    } catch (Exception e) {
      LOG.error("服务器启动失败: {}", e);
    }
  }

  /**
   * 关闭 . WebServer.
   */
  public void shutdown() {
    try {
      LOG.info("----关闭game server 开始----");
      this.localNetService.shutdown();
      LOG.info("----关闭game server 完成----");
    } catch (Exception e) {
      LOG.error("服务器关闭失败: {}", e);
    }
  }
}
