
package com.idealighter.game.robot.http;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 机器人http服务器,用于接收配置变化和机器人变化消息.
 * 
 * @date 2015年11月6日 上午10:44:44
 *
 */
// @Singleton
public class RobotHttpServer {

  private static final Logger LOG = LoggerFactory.getLogger(RobotHttpServer.class);

  @Inject
  @Named("robot.httpPort")
  private int httpPort;

  /**
   * 启动.
   */
  public void startUp() {
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup workerGroup = new NioEventLoopGroup(1);

    try {
      ServerBootstrap b = new ServerBootstrap().option(ChannelOption.SO_BACKLOG, 1024);
      b.group(bossGroup, workerGroup).childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
          ch.pipeline().addLast(new HttpServerCodec());
          ch.pipeline().addLast(new HttpObjectAggregator(65536));
          ch.pipeline().addLast(new HttpServerInboundHandler());
        }
      }).channel(NioServerSocketChannel.class);


      Channel ch = b.bind(httpPort).sync().channel();
      LOG.info("监听机器人服务器Http端口[{}]成功", httpPort);

      ch.closeFuture().sync();
    } catch (Exception e) {
      LOG.error("监听机器人服务器Http端口[" + httpPort + "]异常", e);
      System.exit(1);
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

}
