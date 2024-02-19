package com.idealighter.game.robot.core;

import com.idealighter.game.robot.core.code.ReqMsgDecoder;
import com.idealighter.game.robot.core.code.ResMsgEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * 机器人客户端管道处理器初始化.
 *
 */
public class RobotClientInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    ChannelPipeline pipeline = channel.pipeline();
    // 响应消息解码器
    pipeline.addLast(new ReqMsgDecoder());
    // 请求消息编码器
    pipeline.addLast(new ResMsgEncoder());
    // 消息逻辑处理器
    pipeline.addLast(new ClientHandler());
  }


}
