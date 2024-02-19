package com.idealighter.game.robot.core;

import com.idealighter.game.robot.core.code.ReqMsgDecoder;
import com.idealighter.game.robot.core.code.ResMsgEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * 客户端管道处理器初始化.
 * 
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    ChannelPipeline pipeline = channel.pipeline();
    // 响应消息编码器
    pipeline.addLast(new ResMsgEncoder());
    // 请求消息解码器
    pipeline.addLast(new ReqMsgDecoder());
    pipeline.addLast(new IdleStateHandler(0, 25, 0));
    // 消息逻辑处理器
    pipeline.addLast(new ClientHandler());
  }

}
