
package com.idealighter.game.robot.core;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.gamehall.dto.SeatInfo;
import com.idealighter.game.gamehall.dto.TableInfo;
import com.idealighter.game.login.dto.PlayerInfoDto;
import com.idealighter.game.login.message.ReqLogoutMsg;
import com.idealighter.game.login.message.ReqRobotLoginMsg;
import com.idealighter.game.message.core.Message;
import com.idealighter.game.robot.common.PlayerPosition;
import com.idealighter.game.robot.context.ApplicationContext;
import com.idealighter.game.robot.core.code.SessionType;
import com.idealighter.game.robot.scheduler.PlayerScheduler;
import com.idealighter.utils.code.RandCodeUtil;
import com.idealighter.utils.json.JsonUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 机器人玩家.
 *
 */
public abstract class Player {
  public ScheduledFuture<?> removeSchedule = null;

  /**
   * 机器人玩家客户端.
   *
   */
  // private class Client {
  public class Client {

    private volatile boolean connected = false;
    private volatile Channel channel = null;

    /**
     * 连接服务器.
     * 
     * @param ip .
     * @param port .
     * @param callBack . 成功连接服务器后的回调函数
     */
    public void connect(String ip, int port, Runnable callBack) {
      if (connected) {
        LOG.warn("该客户端已经连接服务器,请勿重复连接");
        return;
      }

      Bootstrap b = new Bootstrap().channel(NioSocketChannel.class).group(eventloopGroup)
          .option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true)
          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000).handler(new ClientInitializer());
      try {
        ChannelFuture f = b.connect(ip, port);
        f.addListener((ChannelFuture future) -> {
          if (future.isSuccess()) {
            this.channel = future.channel();
            this.channel.attr(Player.PLAYER_KEY).set(Player.this);
            // 绑定玩家编号到channel中
            this.channel.attr(SessionType.PLAYER_ID_KEY).set(playerId);
            callBack.run();
          } else {
            LOG.error(userName + "与服务器建立连接失败", future.cause());
            ApplicationContext.getBean(PlayerScheduler.class).removePlayer(Player.this);
          }
        });

        f.sync();
        connected = true;
      } catch (InterruptedException e) {
        LOG.error(userName + "与服务器建立连接失败", e);
        ApplicationContext.getBean(PlayerScheduler.class).removePlayer(Player.this);
      }
    }

    /**
     * 发送消息.
     * 
     * @param msg .
     */
    public void sendMsg(Message msg) {
      if (channel != null && channel.isActive()) {
        channel.writeAndFlush(msg);
      } else {
        LOG.error("[{}]玩家[{}]发送消息[{}]失败", channel, playerId, JsonUtil.toJson(msg));
      }
    }

    public Channel getChannel() {
      return channel;
    }
  }

  protected static final Logger LOG = LoggerFactory.getLogger(Player.class);
  public static final AttributeKey<Player> PLAYER_KEY = AttributeKey.valueOf("player");
  // 机器人玩家网络线程数量
  public static final int EVENT_LOOP_THREADS = Runtime.getRuntime().availableProcessors() * 2;

  // 机器人网络线程组
  public static final EventLoopGroup eventloopGroup = new NioEventLoopGroup(EVENT_LOOP_THREADS);
  public static final long PRECISION = 1000;
  // 玩家id
  public final long playerId;
  // 玩家用户名
  public final String userName;
  // 机器人应该进入的房间
  public final int roomId;
  // 初始金币
  public final long initGold;
  // 合计剩余游戏次数
  public int totalGameNum;
  // 每桌剩余游戏次数
  public int tableGameNum;

  // 客户端
  // public final Client client = new Client();
  public Client client = new Client();
  // 玩家位置
  public PlayerPosition position = null;

  // 玩家信息
  public PlayerInfoDto playerInfo;
  // 进入大厅后获取的房间id集合(如果从服务器获得的房间没有自己的房间id则不进入房间，比如自己的房间被禁用了)
  public final Set<Integer> rooms = new HashSet<>();
  // 房间中的桌子
  public final Map<Integer, TableInfo> tables = new HashMap<>();
  // 房间中的成员
  public final Map<Long, MemInfo> members = new HashMap<>();

  // 申请庄家玩家(定长10队列)
  public final List<Long> applyBankers = new ArrayList<Long>();
  // 当前庄家
  public Long bankerId = null;

  public final RobotConfigDomain configDomain;
 
  /**
   * 玩家.
   * 
   * @param playerId .
   * @param userName .
   * @param roomId .
   * @param initGold .
   * @param totalGameNum .
   * @param tableGameNum .
   */
  public Player(long playerId, String userName, int roomId, long initGold, int totalGameNum,
      int tableGameNum, RobotConfigDomain configDomain) {
    this.playerId = playerId;
    this.userName = userName;
    this.roomId = roomId;
    this.initGold = initGold;
    this.totalGameNum = totalGameNum;
    this.tableGameNum = tableGameNum;
    this.configDomain = configDomain;
  }

  /**
   * 清空数据.
   */
  public void clear() {

  }

  /**
   * 关闭客户端.
   */
  public void close() {
    this.position = null;
    Channel channel = client.channel;
    if (channel != null && channel.isOpen()) {
      channel.close();
    }
  }

  /**
   * 判断channel是否可用.
   */
  public boolean isChannelActive() {
    boolean result = false;
    Channel channel = client.channel;
    if (channel != null && channel.isOpen() && channel.isActive()) {
      result = true;
    }
    return result;
  }

  /**
   * 延时登出.
   */
  public void delayLogout() {
    schedule(this::logout, RandCodeUtil.random(1, 10), TimeUnit.SECONDS);
  }

  /**
   * 进入游戏.
   */
  public void enterGame() {
    Channel channel = client.channel;
    if (channel == null || !channel.isActive()) {
      return;
    }
    if (position == null) {
      LOG.error("游戏尚未登录");
      return;
    }
    switch (position) {
      case PLAZA: // 在游戏广场中(即玩家登录成功)，需要进入游戏大厅
        enterGameHall();
        break;
      case HALL: // 在游戏大厅中，需要进入游戏房间
        enterRoom();
        break;
      case ROOM: // 在游戏房间中，需要进入游戏桌子
        enterTable();
        break;
      case TABLE:
      default:
        break;
    }
  }

  /**
   * 进入游戏大厅.
   */
  public abstract void enterGameHall();

  /**
   * 是否可以进入有密码的桌子.
   * 
   * @return .
   */
  public abstract boolean enterPwdTableAble();

  /**
   * 进入游戏房间.
   */
  public abstract void enterRoom();

  /**
   * 进入游戏桌子.
   */
  public abstract void enterTable();

  /**
   * 查找空位置,优先找有玩家的再找有机器人的最后是空桌子.
   * 
   * @return .
   */
  public SeatInfo findEmptySeat() {
    return findEmptySeat(0);
  }


  /**
   * 查找空位置,优先找有玩家的再找有机器人的最后是空桌子.
   * 
   * @param type . 0:普通空位置类型,1:没有在游戏中的空位置
   * @return .
   */
  private SeatInfo findEmptySeat(int type) {
    PlayerScheduler playerScheduler = ApplicationContext.getBean(PlayerScheduler.class);
    List<TableInfo> tablesList = new ArrayList<>(tables.values());
    Collections.shuffle(tablesList);

    // 有机器人玩家的游戏桌空位子
    SeatInfo robotTableEmptySeat = null;
    // 空桌子位置
    SeatInfo emptyTableSeat = null;
    for (TableInfo table : tablesList) {
      if (!enterPwdTableAble() && table.getHasPwd() != 0) {
        continue;
      }

      List<SeatInfo> seats = table.getSeats();
      Collections.shuffle(seats);

      boolean hasPlayer = false;
      boolean hasRobot = false;

      SeatInfo emptySeat = null;
      for (SeatInfo s : seats) {
        long playerId = s.getPlayerId();
        if (playerId == 0) {
          emptySeat = s;
        } else {
          MemInfo mem = members.get(playerId);
          if (type == 1 && mem != null && mem.getState() == 3) { // 该桌子游戏中玩家不能进入
            emptySeat = null;
            break;
          } else {
            if (playerScheduler.isRobot(playerId)) {
              hasRobot = true;
            } else {
              hasPlayer = true;
            }
          }
        }
      }

      if (emptySeat != null) {
        if (hasPlayer) {
          return emptySeat;
        } else if (hasRobot) {
          robotTableEmptySeat = emptySeat;
        } else {
          emptyTableSeat = emptySeat;
        }
      }
    }

    if (robotTableEmptySeat != null) {
      return robotTableEmptySeat;
    } else if (emptyTableSeat != null) {
      return emptyTableSeat;
    }

    return null;
  }

  /**
   * 查找桌子没有在游戏的空位置,优先找有玩家的再找有机器人的最后是空桌子.
   * 
   * @return .
   */
  public SeatInfo findNoGamingEmptySeat() {
    return findEmptySeat(1);
  }

  /**
   * 机器人游戏类型.
   */
  public abstract Game game();

  /**
   * 玩家进入房间的金币检测.
   * 
   * @return .
   */
  public abstract boolean goldCheck();

  /**
   * 登陆,玩家和服务器成功建立连接同时发送了登录消息logined才变为true.
   */
  public void login(String ip, int port) {
    client.connect(ip, port, () -> {
      sendLoginMsg();
    });
  }

  private void sendLoginMsg() {
    ReqRobotLoginMsg loginMsg = new ReqRobotLoginMsg();
    loginMsg.setName(userName);
    loginMsg.setPassword(userName);
    loginMsg.setGold(initGold);

    sendMsg(loginMsg);
  }

  /**
   * 登出.
   */
  public void logout() {
    sendMsg(new ReqLogoutMsg());
    close();
  }

  /**
   * 获取成员.
   * 
   * @return .
   */
  public MemInfo member(long playerId) {

    return members.get(playerId);
  }

  /**
   * 房间成员信息更新.
   * 
   * @param member .
   */
  public void memInfoUpdate(MemInfo member) {
    members.put(member.getPlayerId(), member);
  }

  /**
   * 移除房间成员.
   * 
   * @param playerId .
   */
  public void removeMemInfo(long playerId) {
    members.remove(playerId);
  }

  // /**
  // * 获取机器人配置.
  // *
  // * @return .
  // */
  // public RobotConfigDomWrapper robotCfg() {
  // return ApplicationContext.getBean(RobotConfigDic.class).getRobotCfg(game(), roomId);
  // }

  /**
   * 定时.
   * 
   * @param command .
   * @param delay .
   * @param unit .
   * @return .
   */
  public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
    return client.channel.eventLoop().schedule(command, delay, unit);
  }

  /**
   * 定时.
   * 
   * @param command .
   * @param initialDelay .
   * @param delay .
   * @param unit .
   * @return .
   */
  protected ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay,
      long delay, TimeUnit unit) {
    return client.channel.eventLoop().scheduleWithFixedDelay(command, initialDelay, delay, unit);
  }

  /**
   * 获取座位信息.
   * 
   * @param playerId .
   * @return .
   */
  public SeatInfo seat(long playerId) {
    TableInfo table = table(playerId);
    List<SeatInfo> seats = table.getSeats();
    for (int i = 0; i < seats.size(); i++) {
      SeatInfo seat = seats.get(i);
      if (seat.getPlayerId() == playerId) {
        return seat;
      }
    }

    return null;
  }

  /**
   * 座位更新处理.
   * 
   * @param seat .
   */
  public void seatInfoUpdate(SeatInfo seat) {
    TableInfo table = tables.get(seat.getTableId());
    List<SeatInfo> seats = table.getSeats();

    if (seats != null) {
      for (int i = 0; i < seats.size(); i++) {
        SeatInfo s = seats.get(i);
        if (s.getOrder() == seat.getOrder()) {
          table.getSeats().set(i, seat);
        }
      }
    }
  }

  /**
   * 发送消息.
   * 
   * @param msg .
   */
  public void sendMsg(Message msg) {
    client.sendMsg(msg);
  }

  /**
   * 获取玩家所在的桌子.
   * 
   * @param playerId .
   * @return .
   */
  public TableInfo table(long playerId) {

    return tables.get(member(playerId).getTableId());
  }



  /**
   * 房间是否有密码变更.
   * 
   * @param tableId .
   * @param hasPwd .
   */
  public void tablePwdChanged(int tableId, int hasPwd) {
    tables.get(tableId).setHasPwd(hasPwd);
  }

}
