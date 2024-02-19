package com.idealighter.game.server.net;

import com.idealighter.game.server.service.AbstractServerService;

import java.net.InetSocketAddress;

public abstract class AbstractNettyServerService extends AbstractServerService {
  protected int serverPort;
  protected InetSocketAddress serverAddress;

  /**
   * 构造函数 .
   * 
   * @param serviceId .
   * @param serverPort .
   */
  public AbstractNettyServerService(String serviceId, int serverPort) {
    super(serviceId);
    this.serverPort = serverPort;
    this.serverAddress = new InetSocketAddress(serverPort);
  }
}
