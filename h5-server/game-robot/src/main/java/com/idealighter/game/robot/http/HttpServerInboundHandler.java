
package com.idealighter.game.robot.http;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import com.alibaba.fastjson.JSON;

import com.idealighter.game.dictionary.dic.RobotConfigDic;
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.http.struct.BackendOrder;
import com.idealighter.game.robot.http.struct.ResBackendOrder;
import com.idealighter.game.robot.scheduler.PlayerScheduler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.jboss.resteasy.util.HttpHeaderNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http交互处理.
 *
 */
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

  private static Logger LOG = LoggerFactory.getLogger(HttpServerInboundHandler.class);

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    if (msg instanceof HttpRequest) {
      HttpRequest request = (HttpRequest) msg;
      if (!request.method().equals(HttpMethod.POST)) {
        LOG.error("请使用post方法提交");
        return;
      }
    }

    /*
     * post提交内容{param:{}}
     */
    if (msg instanceof HttpContent) {
      HttpContent content = (HttpContent) msg;
      ByteBuf buf = content.content();

      // 后台命令字符串
      String backendOrderStr = buf.toString(io.netty.util.CharsetUtil.UTF_8);
      LOG.info("收到hhtp后台gm命令请求[{}]", backendOrderStr);
      BackendOrder backendOrder =
          JSON.parseObject(buf.toString(StandardCharsets.UTF_8), BackendOrder.class);
      buf.release();

      String ip = ((InetSocketAddress) ctx.channel().remoteAddress()).getHostString();
      // 后台gm执行结果
      ResBackendOrder resBackendOrder = action(backendOrder, ip);
      // http返回结果字符串
      String res = JSON.toJSONString(resBackendOrder);

      FullHttpResponse response =
          new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
      ctx.write(response);
      ctx.flush();
    }
  }

  /**
   * 后台命令执行.
   * 
   * @param backendOrder .
   * @param ip .
   * @return .
   */
  private ResBackendOrder action(BackendOrder backendOrder, String ip) {
    try {
      switch (backendOrder.getCmd()) {
        case "reloadRobotConfig": // 重新加载机器人配置
          ApplicationContext.getBean(RobotConfigDic.class).load();
          break;
        case "reloadRobotPlayers": // 重新加载机器人玩家
          ApplicationContext.getBean(PlayerScheduler.class).reloadRobots();
          break;
        default:
          break;
      }
    } catch (Exception e) {
      LOG.error("IP[" + ip + "]命令[" + JSON.toJSONString(backendOrder) + "]执行异常", e);
      return new ResBackendOrder(0, e.getMessage(), "");
    }

    return new ResBackendOrder(1, "", "");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    LOG.error(cause.getMessage(), cause);
    // ctx.close();
  }

}
