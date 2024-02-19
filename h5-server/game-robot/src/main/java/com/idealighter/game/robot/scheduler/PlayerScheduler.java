
package com.idealighter.game.robot.scheduler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.util.NetUtil;
import com.idealighter.game.dictionary.dic.BaccaratRoomDic;
import com.idealighter.game.dictionary.dic.BairenniuniuRoomDic;
import com.idealighter.game.dictionary.dic.GamesDic;
import com.idealighter.game.dictionary.dic.JcbyRoomDic;
import com.idealighter.game.dictionary.dic.RobotConfigDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.domain.RobotConfigDomain;
import com.idealighter.game.dictionary.domwrapper.RobotConfigDomWrapper;
import com.idealighter.game.games.baccarat.BaccaratPlayer;
import com.idealighter.game.games.bairenniuniu.BaiRenNiuNiuPlayer;
import com.idealighter.game.games.jcby.JcbyPlayer;
import com.idealighter.game.message.MessageFactory;
import com.idealighter.game.robot.core.Player;
import com.idealighter.game.robot.datadb.domain.RobotDomain;
import com.idealighter.game.robot.datadb.mapper.RobotMapper;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 机器人玩家Scheduler.
 *
 */
@Singleton
public class PlayerScheduler {
  private static final Logger LOG = LoggerFactory.getLogger(PlayerScheduler.class);

  // 服务器ip
  private final String serverIp;
  // 服务器端口
  private final int serverPort;
  private final RobotMapper robotMapper;
  private final RobotConfigDic robotConfigDic;
  // 机器人玩家定时调度器(单线程)
  private final ScheduledExecutorService scheduler =
      Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "robotPlayer-scheduler"));

  // 所有可用机器人(不包含已冻结的)Dom
  private static final ConcurrentHashMap<Long, RobotDomain> robotDoms = new ConcurrentHashMap<>();
  // 已经创建的机器人
  private final ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<>();

  // 房间中最大机器人数量
  private Table<Integer, Integer, Integer> maxRoomRobotNumTable = HashBasedTable.create();

  private BaccaratRoomDic baccaratRoomDic;

  private BairenniuniuRoomDic bairenniuniuRoomDic;



  private JcbyRoomDic jcbyRoomDic;



  private GamesDic gamesDic;

  /**
   * 构造函数.
   * 
   * @param mapper .
   * @param dic .
   * @param serverIp .
   * @param serverPort .
   */
  @Inject
  public PlayerScheduler(RobotMapper mapper, RobotConfigDic dic,
      @Named("server.ip") String serverIp, @Named("server.tcpPort") int serverPort,
      BaccaratRoomDic baccaratRoomDic, BairenniuniuRoomDic bairenniuniuRoomDic,
      JcbyRoomDic jcbyRoomDic, GamesDic gamesDic) {
    this.robotMapper = mapper;
    this.robotConfigDic = dic;
    this.serverIp = serverIp;
    this.serverPort = serverPort;
    this.baccaratRoomDic = baccaratRoomDic;
    this.bairenniuniuRoomDic = bairenniuniuRoomDic;
    this.jcbyRoomDic = jcbyRoomDic;
    this.gamesDic = gamesDic;


    // 加载机器人
    loadRobots();
    // 加载房间中实际需要的机器人数量
    loadRoomRobotMaxNum();
  }

  private void loadRoomRobotMaxNum() {
    if (EmptyUtil.listIsNotEmpty(bairenniuniuRoomDic.list())) {
      for (BairenniuniuRoomDomain roomDom : bairenniuniuRoomDic.list()) {
        int maxRobot =
            (int) (roomDom.getRobotRatio() / 100.0 * roomDom.getChair() * roomDom.getTableNum());
        maxRoomRobotNumTable.put(Game.BAIREN_NIUNIU.getType(), roomDom.getId(), maxRobot);
      }
    }


    if (EmptyUtil.listIsNotEmpty(jcbyRoomDic.list())) {
      for (JcbyRoomDomain roomDom : jcbyRoomDic.list()) {
        int maxRobot =
            (int) (roomDom.getRobotRatio() / 100.0 * roomDom.getChair() * roomDom.getTableNum());
        maxRoomRobotNumTable.put(Game.JCBY.getType(), roomDom.getId(), maxRobot);
      }
    }



    if (EmptyUtil.listIsNotEmpty(baccaratRoomDic.list())) {
      for (BaccaratRoomDomain roomDom : baccaratRoomDic.list()) {
        int maxRobot =
            (int) (roomDom.getRobotRatio() / 100.0 * roomDom.getChair() * roomDom.getTableNum());
        maxRoomRobotNumTable.put(Game.BACCARAT.getType(), roomDom.getId(), maxRobot);
      }
    }
  }

  /**
   * 加载机器人数据 .
   */
  private void loadRobots() {
    List<RobotDomain> robotsList = robotMapper.selectAll();
    Collections.shuffle(robotsList);

    for (RobotDomain robotDom : robotsList) {
      if (robotDom.isLocked()) {
        continue;
      }
      robotDoms.put(robotDom.getId(), robotDom);
    }
  }

  /**
   * 重新加载服务器机器人 .
   */
  public void reloadRobots() {
    List<RobotDomain> robotsList = robotMapper.selectAll();
    Collections.shuffle(robotsList);

    for (RobotDomain robotDom : robotsList) {
      long playerId = robotDom.getId();

      if (robotDom.isLocked()) {
        Player player = players.get(playerId);
        if (player != null) {
          LOG.info("玩家[{}][{}]被冻结退出游戏", robotDom.getPlayerName(), playerId);
          player.logout();
        }
      } else {
        robotDoms.putIfAbsent(playerId, robotDom);
      }
    }

    LOG.info("重新加载机器人成功");
  }

  /**
   * 启动，每隔一分钟schedule一次机器人玩家 .
   */
  public void startUp() {
    MessageFactory.init();
    scheduler.scheduleWithFixedDelay(this::schedulePlayers, 0, 1, TimeUnit.MINUTES);
    // scheduler.scheduleWithFixedDelay(this::schedulePlayers, 0, 3, TimeUnit.SECONDS);
    LOG.info("机器人服务器启动成功");
  }


  /**
   * 重新加载游戏 .
   *
   * @author abin
   * @date 2018年5月16日 上午10:20:50
   */
  public void reloadGame() {
    LOG.info("重新加载游戏列表");
    gamesDic.load();
  }

  /**
   * 重新加载房间 .
   *
   * @author abin
   * @date 2018年5月2日 下午7:25:46
   * @param gameId 游戏id;
   */
  public void reloadRoom(Integer gameId) {
    switch (Game.getGame(gameId)) {
      case BAIREN_NIUNIU:
        bairenniuniuRoomDic.load();
        break;    
      case JCBY:
        jcbyRoomDic.load();
        break;     
      case BACCARAT:
        baccaratRoomDic.load();
        break;

      default:
        break;
    }

    loadRoomRobotMaxNum();
  }

  /**
   * schedule机器人玩家.
   * 
   * @param players .
   */
  private void schedulePlayers() {
    try {
      // 服务器端口开放才启动玩家
      if (!NetUtil.remotePortAbled(serverIp, serverPort)) {
        LOG.warn("远程IP[{}]端口[{}]不可用不能schedule机器人", serverIp, serverPort);
        return;
      }

      for (RobotConfigDomWrapper robotCfg : robotConfigDic.list()) {
        schedulePlayers(robotCfg);
      }
    } catch (Exception e) {
      LOG.error("schedulePlayers异常", e);
    }
  }

  /**
   * schedule游戏房间玩家,当机器人高于或低于配置时，及时剔除或者补充机器人.
   * 
   * @param robotCfg .
   */
  private void schedulePlayers(RobotConfigDomWrapper robotCfg) {
    // 没有游戏和房间的玩家可以被使用的玩家
    List<RobotDomain> usableRobotDoms = new ArrayList<>();
    // 该游戏房间的玩家
    List<Player> roomPlayers = new ArrayList<>();
    Game game = Game.getGame(robotCfg.getGame());
    int roomId = robotCfg.getRoom();
    // 当前时刻应该在线的机器人数
    int robotNum = robotCfg.getRobotNum(LocalTime.now().getHour());
    Integer maxRobotNum = maxRoomRobotNumTable.get(robotCfg.getGame(), robotCfg.getRoom());
    // 如果配置的机器人数量大于最大需要的机器人数量
    if (maxRobotNum != null && robotNum > maxRobotNum) {
      robotNum = maxRobotNum;
    }

    for (RobotDomain robotDom : robotDoms.values()) {
      long playerId = robotDom.getId();
      Player player = players.get(playerId);

      if (player == null) {
        usableRobotDoms.add(robotDom);
      } else if (player.game() == game && player.roomId == roomId && player.isChannelActive()
          && player.position != null) {
        roomPlayers.add(player);
      } else if (player.game() == game && player.roomId == roomId && player.isChannelActive()
          && player.position == null) {
        LOG.info("[{}][{}]中未登录机器人[{}]", game.getDesc(), roomId, player.playerId);
        // 取消移除schedule
        if (player.removeSchedule != null) {
          player.removeSchedule.cancel(false);
        }
        // 触发新的登录任务
        player.removeSchedule = scheduler.schedule(() -> {
          // 如果30后的状态还是尚未登录，说明用户应该是登录不上(重复登陆，关闭连接),移除玩家,等待下次登陆重试
          Player p = players.get(playerId);
          if (p != null && p.isChannelActive() && p.position == null) {
            removePlayer(p);
          }
        }, 30, TimeUnit.SECONDS);
      } else if (player.game() == game && player.roomId == roomId && !player.isChannelActive()) {
        players.remove(player.playerId);
        player = null;
      }
    }

    if (robotNum > roomPlayers.size()) { // 人数不足，补充人数
      for (int i = 0; i < usableRobotDoms.size() && i < robotNum - roomPlayers.size(); i++) {
        RobotDomain robotDom = usableRobotDoms.get(i);
        Player player = createPlayer(robotCfg, robotDom);
        if (player != null) {
          players.put(player.playerId, player);
          scheduler.schedule(() -> {
            player.login(serverIp, serverPort);
          }, RandCodeUtil.random(1, 30), TimeUnit.SECONDS);
        }
      }
    } else if (robotNum < roomPlayers.size()) { // 人数过多,机器人退出登录
      for (int i = 0; i < roomPlayers.size() && i < roomPlayers.size() - robotNum; i++) {
        roomPlayers.get(i).delayLogout();
      }
    }

    // schedule游戏房间玩家进入游戏
    for (Player p : roomPlayers) {
      scheduler.schedule(() -> p.enterGame(), RandCodeUtil.random(5, 30), TimeUnit.SECONDS);
    }
  }
  
  public void logOutRobotOfGameAndRoom(int gameId, int roomId) {
    scheduler.execute(() -> {
      LOG.info("房间[{}][{}]刷新机器人,该房间机器人将延时退出系统", Game.getGame(gameId).getDesc(), roomId);
      for (Player player : players.values()) {
        if (player.game().getType() == gameId && player.roomId == roomId) {
          // 已经登录
          if (player.isChannelActive() && player.position != null) {
            player.delayLogout(); // 退出
          } else if (player.isChannelActive()) { // 已经连接，尚未登录(也包含发送了登录请求，尚未返回消息机器人)
            player.delayLogout(); // 先退出(如果尚未登陆不影响),再断开链接
          } else { // 离线
          }
          // 移除机器人,下次登陆时重新生成,直到机器人从进入的游戏中退出后才能够登陆成功
          players.remove(player.playerId);
        }
      }
    });
  }

  /**
   * 从RobotDomain创建玩家.
   * 
   * @param robotCfg .
   * @param robotDom .
   * @return .
   */
  public Player createPlayer(RobotConfigDomain robotCfg, RobotDomain robotDom) {
    long playerId = robotDom.getId();
    String userName = robotDom.getUserName();
    int roomId = robotCfg.getRoom();
    // 初始金币
    long initGold = RandCodeUtil.random(robotCfg.getGoldLower(), robotCfg.getGoldUpper());
    // 牌桌游戏局数
    int tableGameNum =
        RandCodeUtil.random(robotCfg.getTableGameLower(), robotCfg.getTableGameUpper());
    // 总游戏局数
    int totalGameNum =
        RandCodeUtil.random(robotCfg.getTotalGameLower(), robotCfg.getTotalGameUpper());
    Player player = null;
    GamesDomain gamesDomain = gamesDic.get(robotCfg.getGame());
    if (gamesDomain != null && gamesDomain.getActive()) {
      switch (Game.getGame(robotCfg.getGame())) {
        case BAIREN_NIUNIU:
          BairenniuniuRoomDomain bairenniuniuRoomDomain = bairenniuniuRoomDic.get(roomId);
          if (bairenniuniuRoomDomain != null
              && bairenniuniuRoomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
            player = new BaiRenNiuNiuPlayer(playerId, userName, roomId, initGold, totalGameNum,
                tableGameNum, robotCfg, bairenniuniuRoomDomain);
          }
          break;

       
       
        case JCBY:
          JcbyRoomDomain jcbyRoomDomain = jcbyRoomDic.get(roomId);
          if (jcbyRoomDomain != null && jcbyRoomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
            player = new JcbyPlayer(playerId, userName, roomId, initGold, totalGameNum,
                tableGameNum, robotCfg, jcbyRoomDomain);
          }
          break;
        
       
        case BACCARAT:
          BaccaratRoomDomain baccaratRoomDomain = baccaratRoomDic.get(roomId);
          if (baccaratRoomDomain != null
              && baccaratRoomDomain.getIsActive() == RoomActiveConstant.ACTIVE) {
            player = new BaccaratPlayer(playerId, userName, roomId, initGold, totalGameNum,
                tableGameNum, robotCfg, baccaratRoomDomain);
          }
          break;       
        
        default:
          break;
      }
    }

    return player;
  }

  /**
   * 是否是机器人.
   * 
   * @param playerId .
   * @return .
   */
  public boolean isRobot(long playerId) {

    return robotDoms.containsKey(playerId);
  }

  /**
   * 移除玩家.
   * 
   * @param player .
   */
  public void removePlayer(Player player) {
    long playerId = player.playerId;
    if (players.remove(playerId) != null) {
      LOG.info("[{}]机器人玩家[{}][{}]退出游戏 ", player.game().getDesc(), player.userName, playerId);
      player.close();
      player.clear();
      player = null;
    }
  }

  /**
   * 获取玩家.
   * 
   * @param playerId .
   */
  public Player getPlayer(long playerId) {

    return players.get(playerId);
  }
}
