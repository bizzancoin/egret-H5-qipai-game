package com.idealighter.game.server.net.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * GameNettyWebSocketServerService.
 * 
 * @Description Netty WebSocket 服务器
 * @author houdongsheng
 * @date 2018年4月14日 下午6:01:53 .
 */
public class GameNettyWebSocketServerService
    extends AbstractNettyWebSocketServerService<SocketChannel> {

  public GameNettyWebSocketServerService(String serviceId, int serverPort, String bossTreadName,
      String workThreadName, ChannelInitializer<SocketChannel> channelInitializer) {
    super(serviceId, serverPort, bossTreadName, workThreadName, channelInitializer);
  }
}
