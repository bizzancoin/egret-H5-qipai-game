package com.idealighter.game.server.core.handler;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.blacklist.manager.BlackListMgr;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.ChannelCloseEvent;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.ExitType;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.login.message.ResHeartbeatMsg;
import com.idealighter.game.message.ModuleMsgIdConstant;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.message.core.MessageHeader;
import com.idealighter.game.message.core.MessageWapper;
import com.idealighter.game.message.core.ReqMessage;
import com.idealighter.game.server.core.code.SessionType;
import com.idealighter.game.server.core.task.ReqMsgTask;
import com.idealighter.game.server.event.ExecutorMgr;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class InboundBusinessHandler {
  private static final Logger LOG = LoggerFactory.getLogger(InboundBusinessHandler.class);
  private static final Logger SESSION_LOG = LoggerFactory.getLogger("SessionLog");
  private static final Logger heartbeat = LoggerFactory.getLogger("heartbeat");
  // actionId种子，默认为当前的一个毫微秒，1秒等于10亿毫微秒
  public static final AtomicLong ACTIONID_SEED = new AtomicLong(System.nanoTime());
  private static final ResHeartbeatMsg heartbeatMsg = new ResHeartbeatMsg();

  private final EventMgr eventMgr;
  private final ExecutorMgr executorMgr;
  private final BlackListMgr blackListMgr;
  private final CommonMsgMgr commonMsgMgr;
  private final PlayerMsgWriter playerMsgWriter;

  private final Timer timer;

  public static final AttributeKey<String> netTypeAttr = AttributeKey.valueOf("netTypeAttr");

  private boolean isClosing = false;

  /**
   * 构造函数.
   * 
   * @param eventMgr .
   * @param executorMgr .
   * @param blackListMgr .
   * @param commonMsgMgr .
   * @param playerMsgWriter .
   */
  @Inject
  public InboundBusinessHandler(EventMgr eventMgr, ExecutorMgr executorMgr,
      BlackListMgr blackListMgr, CommonMsgMgr commonMsgMgr, PlayerMsgWriter playerMsgWriter) {
    this.eventMgr = eventMgr;
    this.executorMgr = executorMgr;
    this.blackListMgr = blackListMgr;
    this.commonMsgMgr = commonMsgMgr;
    this.playerMsgWriter = playerMsgWriter;

    // 初始化定时器
    timer = new HashedWheelTimer();
  }

  /**
   * 进入拦截处理 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:34:51
   * @param ctx 上下文.
   * @param msgWapper 消息.
   */
  public void handler(ChannelHandlerContext ctx, MessageWapper msgWapper) {
    if (msgWapper != null) {
      String netType = ctx.channel().attr(netTypeAttr).get();
      // 消息
      ReqMessage reqMsg = null;
      MessageHeader header = null;
      if (msgWapper instanceof MessageWapper) {
        Message msg = msgWapper.getMsg();
        header = msgWapper.getHeader();
        if (msg instanceof ReqMessage) {
          reqMsg = (ReqMessage) msg;
        } else {
          LOG.error("[{}]消息[{}]不是请求消息", netType, JSON.toJSONString(msg));
          return;
        }
      } else {
        LOG.error("[{}]消息[{}]不是请求消息", netType, JSON.toJSONString(msgWapper));
        return;
      }

      Channel channel = ctx.channel();
      Player player = channel.attr(Player.PLAYER_KEY).get();

      // 心跳信息直接处理
      if (reqMsg.getId() == ModuleMsgIdConstant.HEART_BEAT) {
        handlerHeartBeat(channel, header);
        return;
      }

      if (player == null) {
        String ip = ((InetSocketAddress) channel.remoteAddress()).getHostString();
        player = new Player(channel, ip);
        channel.attr(Player.PLAYER_KEY).set(player);
      }

      // 玩家的每次msg请求都有一个唯一的actionId,用于记录日志
      player.actionId = ACTIONID_SEED.getAndIncrement();
      // 消息id
      int msgId = reqMsg.getId();
      // 消息模块id
      int moduleId = msgId / 100;

      if (moduleId <= 1) {
        /*
         * 登录模块在loginExecutorGroup中执行 .
         */
        if (isClosing) {
          return;
        }
        executorMgr.getLoginExecutor(channel)
            .execute(new ReqMsgTask(player, reqMsg, header, playerMsgWriter));
      } else if (moduleId <= 50) {

        /*
         * 102-500为：单个玩家任务处理，如：商城等。必须先登录或注册后才可以 .
         */
        long playerId = 0;
        if (player.playerBo() != null && (playerId = player.getId()) > 0) {
          executorMgr.getPlayerExecutor(playerId)
              .execute(new ReqMsgTask(player, reqMsg, header, playerMsgWriter));;
        } else {
          LOG.error("[{}][{}]会话的玩家还未登录", netType, channel);
        }

      } else if (moduleId <= 100) {
        if (player.playerBo() != null && !blackListMgr.playAble(player)) {
          LOG.error("[{}]玩家[{}][{}]在黑名单中不能玩游戏", netType, player.getId(), player.getPlayerName());
          commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.GAME_IN_BLACK_LIST, Operator.SYSTEM);
          return;
        }

        /*
         * 具体游戏的Executor，如：斗地主等。 .
         */
        if (player.playerBo() != null) {
          executorMgr.getGameExecutor(moduleId)
              .execute(new ReqMsgTask(player, reqMsg, header, playerMsgWriter));
        } else {
          LOG.error("[{}][{}]会话的玩家还未登录", netType, channel);
        }
      } else {
        LOG.error("[{}]消息id[{}]无法找到对应的executor,请按规则定义", netType, reqMsg.getId());
        return;
      }
    }
  }

  public void active(ChannelHandlerContext ctx, String netType) {
    ctx.channel().attr(netTypeAttr).set(netType);
    LOG.info("[{}]建立连接成功:[{}]", netType, ctx.channel());
  }

  /**
   * Channel 关闭响应 .
   * 
   * @param ctx .
   * @throws Exception .
   */
  public void inactive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    String netType = ctx.channel().attr(netTypeAttr).get();
    LOG.info("[{}][{}]玩家[{}]断开连接", netType, channel, channel.attr(SessionType.PLAYER_ID_KEY));
    int channelHashCode = ctx.channel().hashCode();

    Player player = channel.attr(Player.PLAYER_KEY).get();
    // 断开连接后，Player和channel双解绑
    if (player != null) {
      if (channel != null) { // 执行退出后，Player和Channel双解绑
        channel.attr(SessionType.PLAYER_ID_KEY).set(null); // 清空channel中的玩家编号
        channel.attr(Player.PLAYER_KEY).set(null); // 清空channle中绑定的player信息
        player.clearChannel(); // 玩家的channel设为null,防止退出后再收到消息
      }
    }

    // Byte exitType = channel.attr(SessionType.EXIT_TYPE).get();
    // 不是定义好的退出
    if (player != null && player.playerBo() != null) {
      // 非主动关闭 定时退出
      Timeout timeout = timer.newTimeout(new TimerTask() {
        @Override
        public void run(Timeout timeout) throws Exception {
          eventMgr.post(new ChannelCloseEvent(player, channelHashCode));
        }
      }, 60, TimeUnit.SECONDS);
      player.logoutTimeout = timeout;
      LOG.info("[{}][{}]玩家[{}]断开连接后定时退出开启", netType, channel,
          channel.attr(SessionType.PLAYER_ID_KEY));
      SESSION_LOG.debug("[{}][{}]玩家[{}]断开连接后定时退出开启", netType, channel,
          channel.attr(SessionType.PLAYER_ID_KEY));
    } else {
      eventMgr.post(new ChannelCloseEvent(player, channelHashCode));
    }
  }

  public void exception(ChannelHandlerContext ctx, Throwable cause) {
    ctx.channel().attr(SessionType.EXIT_TYPE).set(ExitType.EXCEPTION);
    ctx.close();
  }

  /**
   * 时间出发响应 .
   *
   * @param ctx .
   * @param evt .
   * @throws Exception .
   */
  public void eventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    /* 心跳处理 . */
    if (evt instanceof IdleStateEvent) {
      String netType = ctx.channel().attr(netTypeAttr).get();
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.READER_IDLE) { // 读超时
        if (LOG.isDebugEnabled()) {
          LOG.warn("[{}][{}]空闲超时关闭连接", netType, ctx.channel());
        }
        ctx.channel().attr(SessionType.EXIT_TYPE).set(ExitType.TIMEOUT);
        ctx.close();
      }
    }
  }

  // 处理心跳请求
  private void handlerHeartBeat(Channel channel, MessageHeader header) {
    Long playerId = channel.attr(SessionType.PLAYER_ID_KEY).get();
    if (channel != null && channel.isActive()) {
      String netType = channel.attr(netTypeAttr).get();
      heartbeat.debug("[{}]channel[{}], 用户[{}],心跳请求", netType, channel, playerId);
      playerMsgWriter.writeMsg(channel, heartbeatMsg, header);
    }
  }

  public void closeing() {
    this.isClosing = true;
  }
}
