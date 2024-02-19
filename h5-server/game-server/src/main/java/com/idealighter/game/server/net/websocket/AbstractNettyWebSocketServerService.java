package com.idealighter.game.server.net.websocket;

import com.idealighter.game.server.net.AbstractNettyServerService;
import com.idealighter.game.server.net.ThreadNameFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractNettyWebSocketServerService<T extends Channel>
    extends AbstractNettyServerService {
  private static Logger LOG = LoggerFactory.getLogger(AbstractNettyWebSocketServerService.class);

  private EventLoopGroup bossGroup;
  private EventLoopGroup workerGroup;

  private ThreadNameFactory bossThreadNameFactory;
  private ThreadNameFactory workerThreadNameFactory;
  private ChannelInitializer<T> channelInitializer;

  private ChannelFuture serverChannelFuture;

  /**
   * 构造函数.
   * 
   * @param serviceId .
   * @param serverPort .
   * @param bossTreadName .
   * @param workThreadName .
   * @param channelInitializer .
   */
  public AbstractNettyWebSocketServerService(String serviceId, int serverPort, String bossTreadName,
      String workThreadName, ChannelInitializer<T> channelInitializer) {
    super(serviceId, serverPort);
    this.bossThreadNameFactory = new ThreadNameFactory(bossTreadName);
    this.workerThreadNameFactory = new ThreadNameFactory(workThreadName);
    this.channelInitializer = channelInitializer;
  }

  @Override
  public boolean startServer() throws Exception {
    boolean serviceFlag = super.startServer();
    bossGroup = new NioEventLoopGroup(1, bossThreadNameFactory);
    workerGroup = new NioEventLoopGroup(8, workerThreadNameFactory);
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap = serverBootstrap.group(bossGroup, workerGroup);
      serverBootstrap.channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
          .childOption(ChannelOption.SO_REUSEADDR, true) // 重用地址
          .childOption(ChannelOption.SO_RCVBUF, 65536).childOption(ChannelOption.SO_SNDBUF, 65536)
          .childOption(ChannelOption.TCP_NODELAY, true)
          .childOption(ChannelOption.SO_KEEPALIVE, true)
          // heap buf 's
          // better
          .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))
          .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(1000))
          .handler(new LoggingHandler(LogLevel.INFO)).childHandler(channelInitializer);

      serverChannelFuture = serverBootstrap.bind(serverPort).sync();
      LOG.info("监听游戏端口[{}]成功", serverPort);

      serverChannelFuture.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
    } catch (Exception e) {
      LOG.error(e.toString(), e);
      serviceFlag = false;
    }
    return serviceFlag;
  }

  @Override
  public boolean stopServer() throws Exception {
    boolean flag = super.stopServer();
    if (bossGroup != null) {
      Future<?> bossReturn = bossGroup.shutdownGracefully();
      // 只是为了阻塞
      bossReturn.get();
    }
    if (workerGroup != null) {
      Future<?> workerReturn = workerGroup.shutdownGracefully();
      // 只是为了阻塞
      workerReturn.get();
    }
    return flag;
  }
}
