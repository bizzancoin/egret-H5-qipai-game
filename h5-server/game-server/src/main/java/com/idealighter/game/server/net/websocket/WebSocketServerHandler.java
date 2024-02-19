package com.idealighter.game.server.net.websocket;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.server.core.code.ReqMsgDecoder;
import com.idealighter.game.server.core.code.SessionType;
import com.idealighter.game.server.core.handler.InboundBusinessHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
  // 日志
  private static final Logger LOG = LoggerFactory.getLogger(WebSocketServerHandler.class);
  private static final Logger heartbeat = LoggerFactory.getLogger("heartbeat");
  private static final String NET_TYPE = "WEB-SOCKET";
  // 处理握手
  private WebSocketServerHandshaker handshaker;

  private static final String WEBSOCKET_PATH = "/websocket";
  private final InboundBusinessHandler inboundBusinessHandler;

  // 是否停服，停服后不再接受消息处理
  private boolean shutdown = false;

  @Inject
  public WebSocketServerHandler(InboundBusinessHandler inboundBusinessHandler) {
    this.inboundBusinessHandler = inboundBusinessHandler;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    // super.channelRead(ctx, msgWapper);
    if (shutdown) {
      return;
    }

    if (msg instanceof HttpRequest) { // 处理握手请求
      handleHttpRequest(ctx, (HttpRequest) msg);
    } else if (msg instanceof WebSocketFrame) { // 处理其他请求
      handleWebSocketFrame(ctx, (WebSocketFrame) msg);
    }
  }

  public void shutdown() {
    shutdown = true;
  }
  
  public void closing() {
    inboundBusinessHandler.closeing();
  }

  private void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
    // Handle a bad request.
    if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
      LOG.info("[{}]握手失败,[{}][{}]", ctx.channel(), req.decoderResult().isSuccess(),
          req.headers().get("Upgrade"));
      sendHttpResponse(ctx, req,
          new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
      return;
    }

    // Handshake
    WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
        getWebSocketLocation(req), null, true, 5 * 1024 * 1024);
    handshaker = wsFactory.newHandshaker(req);
    if (handshaker == null) {
      LOG.info("[{}]握手失败,不支持的websocket版本", ctx.channel());
      WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
    } else {
      LOG.info("[{}]处理握手", ctx.channel());
      handshaker.handshake(ctx.channel(), req);
    }
  }

  private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
    // Check for closing frame
    if (frame instanceof CloseWebSocketFrame) {
      handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
    }
    // 心跳信息
    if (frame instanceof PingWebSocketFrame) {
      Channel channel = ctx.channel();
      heartbeat.debug("channel[{}], 用户[{}],websocket心跳请求", channel,
          channel.attr(SessionType.PLAYER_ID_KEY).get());
      ctx.write(new PongWebSocketFrame(frame.content().retain()));
      return;
    }
    // 不支持文本协议
    if (frame instanceof TextWebSocketFrame) {
      // Echo the frame
      ctx.write(frame.retain());
      return;
    }
    if (frame instanceof BinaryWebSocketFrame) {
      BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
      ByteBuf buf = binaryWebSocketFrame.content();
      // 解析消息
      MessageWapper msgWapper = ReqMsgDecoder.decodeMsg(ctx, buf);
      inboundBusinessHandler.handler(ctx, msgWapper);
    }
  }

  private static String getWebSocketLocation(HttpRequest req) {
    String location = req.headers().get(HttpHeaderNames.HOST) + WEBSOCKET_PATH;
    return "ws://" + location;
  }

  private static void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req,
      FullHttpResponse res) {
    // Generate an error page if response getStatus code is not OK (200).
    if (res.status().code() != 200) {
      ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
      res.content().writeBytes(buf);
      buf.release();
      HttpUtil.setContentLength(res, res.content().readableBytes());
    }

    // Send the response and close the connection if necessary.
    ChannelFuture f = ctx.channel().writeAndFlush(res);
    if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    inboundBusinessHandler.active(ctx, NET_TYPE);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    inboundBusinessHandler.inactive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    inboundBusinessHandler.exception(ctx, cause);
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
    inboundBusinessHandler.eventTriggered(ctx, evt);
  }
}
