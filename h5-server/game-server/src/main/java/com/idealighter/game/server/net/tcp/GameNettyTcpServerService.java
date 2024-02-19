package com.idealighter.game.server.net.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * GameNettyWebSocketServerService.
 * 
 * @Description Netty WebSocket 服务器
 * @author houdongsheng
 * @date 2018年4月14日 下午6:01:53 .
 */
public class GameNettyTcpServerService extends AbstractNettyTcpServerService<SocketChannel> {

  public GameNettyTcpServerService(String serviceId, int serverPort, String bossThreadName,
      String workThreadName, ChannelInitializer<SocketChannel> channelInitializer) {
    super(serviceId, serverPort, bossThreadName, workThreadName, channelInitializer);
  }
}
