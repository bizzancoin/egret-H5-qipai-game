package com.idealighter.game.server.net.tcp;

import com.idealighter.game.server.net.AbstractNettyServerService;
import com.idealighter.game.server.net.ThreadNameFactory;

import io.netty.bootstrap.ServerBootstrap;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractNettyTcpServerService<T extends Channel>
    extends AbstractNettyServerService {
  private static Logger LOG = LoggerFactory.getLogger(AbstractNettyTcpServerService.class);

  private EventLoopGroup bossGroup;
  private EventLoopGroup workerGroup;

  private ThreadNameFactory bossThreadNameFactory;
  private ThreadNameFactory workerThreadNameFactory;
  private ChannelInitializer<T> channelInitializer;

  private ChannelFuture serverChannelFuture;

  /**
   * 构造函数.
   * @param serviceId .
   * @param serverPort .
   * @param bossThreadName .
   * @param workThreadName .
   * @param channelInitializer .
   */
  public AbstractNettyTcpServerService(String serviceId, int serverPort, String bossThreadName,
      String workThreadName, ChannelInitializer<T> channelInitializer) {
    super(serviceId, serverPort);
    this.bossThreadNameFactory = new ThreadNameFactory(bossThreadName);
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
          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
          .childOption(ChannelOption.TCP_NODELAY, true)
          .childOption(ChannelOption.SO_KEEPALIVE, true).handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(channelInitializer);

      serverChannelFuture = serverBootstrap.bind(serverPort).sync();
      LOG.info("监听游戏端口[{}]成功", serverPort);
      // TODO:
      // serverChannelFuture.channel().closeFuture().sync();
      serverChannelFuture.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
    } catch (Exception e) {
      LOG.error("GameTcpServer异常", e);
      serviceFlag = false;
    }
    return serviceFlag;
  }

  @Override
  public boolean stopServer() throws Exception {
    boolean flag = super.stopServer();
    if (bossGroup != null) {
      bossGroup.shutdownGracefully();
    }
    if (workerGroup != null) {
      workerGroup.shutdownGracefully();
    }
    return flag;
  }
}
