
package com.idealighter.game.core.service.games.jcby.manager;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.notice.Notice;
import com.idealighter.game.core.constant.notice.NoticeType;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.ClearGameDataEvent;
import com.idealighter.game.core.service.event.struct.GameClearExitEvent;
import com.idealighter.game.core.service.event.struct.PlayerExitEvent;
import com.idealighter.game.core.service.event.struct.ShutdownAllGameEvent;
import com.idealighter.game.core.service.event.struct.ShutdownGameEvent;
import com.idealighter.game.core.service.event.struct.ShutdownRoomEvent;
import com.idealighter.game.core.service.event.struct.StartGameEvent;
import com.idealighter.game.core.service.event.struct.StartRoomEvent;
import com.idealighter.game.core.service.gamehall.event.EnterRoomEvent;
import com.idealighter.game.core.service.gamehall.event.EnterTableEvent;
import com.idealighter.game.core.service.gamehall.event.ExitRoomEvent;
import com.idealighter.game.core.service.gamehall.event.ExitTableEvent;
import com.idealighter.game.core.service.gamehall.event.RoomMemInfoUpdateEvent;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.core.service.games.common.buyu.Bullet;
import com.idealighter.game.core.service.games.common.buyu.strategy.Strategy;
import com.idealighter.game.core.service.games.jcby.struct.JcbyFish;
import com.idealighter.game.core.service.games.jcby.struct.JcbyRoom;
import com.idealighter.game.core.service.games.jcby.struct.JcbyScence;
import com.idealighter.game.core.service.games.jcby.struct.JcbySeat;
import com.idealighter.game.core.service.games.jcby.struct.JcbyTable;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.game.Game9WinLoseLog;
import com.idealighter.game.core.service.log.struct.game.GameAfeeLog;
import com.idealighter.game.core.service.log.struct.resource.GameGoldLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.notice.manager.NoticeMsgMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.tuple.Triplet;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.JcbyBatteryDic;
import com.idealighter.game.dictionary.dic.JcbyFishDic;
import com.idealighter.game.dictionary.dic.JcbyScenceDic;
import com.idealighter.game.dictionary.domain.JcbyRoomDomain;
import com.idealighter.game.dictionary.domwrapper.JcbyFishDomWrapper;
import com.idealighter.game.dictionary.domwrapper.JcbyScenceDomWrapper;
import com.idealighter.game.games.jcby.message.ResGameInfoMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.game.third.constant.ThirdGameType;
import com.idealighter.game.third.constant.ThirdPlayerType;
import com.idealighter.game.third.utils.ThirdOrderUtils;
import com.idealighter.utils.check.CheckUtil;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 金蝉捕鱼 .
 * 
 * @date 2016年1月26日 下午8:36:26
 *
 */
@Singleton
public class JcbyMgr {

  private static final Logger LOG = LoggerFactory.getLogger(JcbyMgr.class);
  // 普通鱼
  public static final int GENERAL_FISH = 1;
  // 同类炸弹鱼
  public static final int KINDS_BOMB_FISH = 2;
  // 全屏炸弹鱼
  public static final int FULL_SCREEN_FISH = 3;
  // 场景延时5秒,每次刷场景的延时(客户端有个场景切换动画)
  public static final int SCENCE_DELAY = 5;
  // 吃子弹时间（断线重连）
  public static final int EAT_BULLET_DELAY = 30;

  private final EventMgr eventMgr;
  // 游戏线程executor,可以执行普通任务和定时任务
  public final DisruptorExecutor gameExecutor;

  @Inject
  private CommonMsgMgr commonMsgMgr;

  @Inject
  private JcbyDataMgr dataMgr;
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private JcbyMsgMgr msgMgr;
  @Inject
  private NoticeMsgMgr noticeMsgMgr;
  @Inject
  private JcbyScenceDic scenceDic;
  @Inject
  private ScheduleMgr scheduleMgr;
  @Inject
  private JcbyBatteryDic batteryDic;
  @Inject
  private JcbyScheduleFishMgr scheduleFishMgr;
  @Inject
  private JcbyFishDic fishDic;
  @Inject
  private LoginMgr loginMgr;
  @Inject
  private JcbyControlScript controlScript;

  /**
   * 构造函数 .
   * 
   * @param eventMgr 事件mgr.
   * @param executorMgr 执行器mgr.
   */
  @Inject
  public JcbyMgr(EventMgr eventMgr, ExecutorMgr executorMgr) {
    this.eventMgr = eventMgr;
    eventMgr.register(this);
    this.gameExecutor = executorMgr.getGameExecutor(Game.JCBY.getModuleId());
  }

  /**
   * 玩家退出事件.
   * 
   * @param event 玩家退出事件.
   */
  @Subscribe
  public void onPlayerExitGame(PlayerExitEvent event) {
    Player player = event.player;
    if (player != null) {
      long playerId = player.getId();
      if (player.curRoom instanceof JcbyRoom) {
        gameExecutor.execute(() -> {
          JcbySeat seat = dataMgr.getPlayerSeat(playerId);
          if (seat != null) {
            doExitTabl(seat);
          }

          // 玩家是否在房间中
          if (player.curRoom instanceof JcbyRoom) {
            // 退出房间
            exitRoom(player);
            // 触发退出
            GameClearExitEvent gameClearExitEvent =
                new GameClearExitEvent(player, Game.JCBY.getDesc());
            eventMgr.post(gameClearExitEvent);
          }
        });
      }
    }
  }

  /**
   * 检验是否房间关闭，如果桌子不在游戏中或者游戏正在休息，顺便把桌子关闭 .
   *
   * @author abin
   * @date 2018年4月28日 下午3:41:20
   * @param table 桌子.
   */
  private boolean checkClose(JcbyTable table) {
    boolean remove = false;
    if (table.getRoom().getInstanceState() == InstanceState.TO_REMOVE) {
      for (Long playerId : table.players()) {
        Player player = playerMgr.getPlayer(playerId);
        if (player != null) {
          kickoutForRoomClose(player);
        }
      }
      JcbyRoom room = table.getRoom();
      room.removeTable(table.getId());

      if (table != null) {
        List<JcbyScence> scences = table.getScences();
        if (EmptyUtil.listIsNotEmpty(scences)) {
          for (JcbyScence lkbyScence : scences) {
            List<ScheduledFuture<?>> produceFishFutures = lkbyScence.getProduceFishFutures();
            if (EmptyUtil.listIsNotEmpty(produceFishFutures)) {
              for (ScheduledFuture<?> scheduledFuture : produceFishFutures) {
                scheduledFuture.cancel(false);
              }
            }
          }
        }
        if (table.scenceFuture != null) {
          table.scenceFuture.cancel(false);
        }
      }
      table = null;
      remove = true;
    }
    return remove;
  }

  private void deleteRooms(List<JcbyRoom> deleteRooms) {
    if (EmptyUtil.listIsNotEmpty(deleteRooms)) {
      for (Iterator<JcbyRoom> iterator = deleteRooms.iterator(); iterator.hasNext();) {
        JcbyRoom room = iterator.next();
        List<JcbyTable> tables = new ArrayList<>(room.tables());
        for (Iterator<JcbyTable> tableIterator = tables.iterator(); tableIterator.hasNext();) {
          JcbyTable table = tableIterator.next();
          checkClose(table);
        }
        dataMgr.checkRemoveInstance(room.getId(), room.getInstanceId());
      }
    }
  }

  /**
   * 开启房间 .
   * 
   * @param event 开启房间事件.
   */
  @Subscribe
  public void onStartRoom(StartRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.JCBY) {
        List<JcbyRoom> deleteRooms = dataMgr.startRoom(event.getRoomId(), true);
        deleteRooms(deleteRooms);
      }
    });
  }

  /**
   * 关闭房间.
   * 
   * @param event 关闭房间事件.
   */
  @Subscribe
  public void onShutDownRoom(ShutdownRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.JCBY) {
        List<JcbyRoom> deleteRooms = dataMgr.deleteRoom(event.getRoomId());
        deleteRooms(deleteRooms);
      }

    });
  }

  /**
   * 开启游戏 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:11:37
   * @param event 开启事件.
   */
  @Subscribe
  public void onStartGame(StartGameEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.JCBY) {
        List<Integer> ids = dataMgr.reloadRoom();
        if (EmptyUtil.listIsNotEmpty(ids)) {
          for (Integer id : ids) {
            List<JcbyRoom> deleteRooms = dataMgr.startRoom(id, false);
            deleteRooms(deleteRooms);
          }
        }
      }
    });
  }

  /**
   * 关闭游戏 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:11:54
   * @param event 事件.
   */
  @Subscribe
  public void onShutdownGame(ShutdownGameEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.JCBY) {
        Collection<JcbyRoom> rooms = dataMgr.allRooms();
        if (!EmptyUtil.isEmpty(rooms)) {
          for (JcbyRoom room : rooms) {
            List<JcbyRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
            deleteRooms(deleteRooms);
          }
        }

      }
    });
  }

  /**
   * 监听应用关闭 .
   *
   * @author abin
   * @date 2018年6月20日 下午3:24:35
   * @param event 关闭事件.
   */
  @Subscribe
  public void onShutdownAllGameEvent(ShutdownAllGameEvent event) {
    LOG.info("金蟾捕鱼开始退出.....");
    gameExecutor.execute(() -> {
      Collection<JcbyRoom> rooms = dataMgr.allRooms();
      if (!EmptyUtil.isEmpty(rooms)) {
        for (JcbyRoom room : rooms) {
          List<JcbyRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
          deleteRooms(deleteRooms);
        }
      }
    });

    while (!JcbyDataMgr.roomEmpty) {
      try {
        Thread.sleep(500L);
      } catch (InterruptedException exception) {
        LOG.error("金蟾捕鱼退出异常", exception);
      }
    }

    LOG.info("金蟾捕鱼退出");
  }

  @Subscribe
  public void onClearGameData(ClearGameDataEvent event) {
    gameExecutor.execute(() -> doClearGameData(event.playerId));
  }

  /**
   * 进入游戏大厅 .
   * 
   * @param player 玩家.
   */
  public ResMessage enterGameHall(Player player) {
    if (player.curRoom != null) {
      Game game = player.curRoom.game();
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]在[{}]游戏中，暂时无法进入游戏", player.getId(), player.getPlayerName(),
          game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }
    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入大厅成功", player.getPlayerName(), player.getId());
    return msgMgr.sendEnterGameHallMsg();
  }

  /**
   * 玩家进入房间 .
   * 
   * @param player 玩家.
   * @param roomId .
   */
  public ResMessage enterRoom(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;
    if (curRoom != null) {
      Game game = curRoom.game();
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入斗地主房间", player.getId(), player.getPlayerName(),
          curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    JcbyRoom room = dataMgr.getNewestRoom(roomId);
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    JcbyRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != RoomActiveConstant.ACTIVE) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]已经关闭", player.getPlayerName(), player.getId(), roomId);
      HuohuaAssert.isTrue(false);
    }
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0 && player.getSafeGold() > upper) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    if (room.getPlayers().size() >= roomDom.getMaxNum()) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    // 发送进入房间消息
    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);
    return msgMgr.sendEnterRoomMsg(room);
  }

  /**
   * 快速进入 .
   *
   * @author abin
   * @date 2018年9月10日 下午5:58:55
   * @param player 玩家.
   * @param roomId 房间Id.
   * @return 快速进入.
   */
  public ResMessage fastEnter(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;
    if (curRoom != null) {
      Game game = curRoom.game();
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入斗地主房间", player.getId(), player.getPlayerName(),
          curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    JcbyRoom room = dataMgr.getNewestRoom(roomId);
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    JcbyRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != RoomActiveConstant.ACTIVE) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]已经关闭", player.getPlayerName(), player.getId(), roomId);
      HuohuaAssert.isTrue(false);
    }
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0 && player.getSafeGold() > upper) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    if (room.getPlayers().size() >= roomDom.getMaxNum()) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    // 发送进入房间消息
    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);


    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    JcbySeat emptySeat = findEmptySeat(player, room);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);

    doEnterTable(player, emptySeat);

    return msgMgr.sendFastEnterMsg(emptySeat.getTable(), emptySeat.getTotalChips());
  }

  /**
   * 快速进入二人牛牛 .
   * 
   * @param player 玩家.
   * @param roomId .
   */
  public ResMessage fastEnterTable(Player player, int roomId) {
    if (!(player.curRoom instanceof JcbyRoom)) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]当前不在金蝉捕鱼中不能直接进入桌子", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    JcbyRoom room = (JcbyRoom) player.curRoom;
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    JcbySeat emptySeat = findEmptySeat(player, room);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);

    doEnterTable(player, emptySeat);

    // 向玩家发送进入牌桌结果信息
    return msgMgr.sendEnterTableMsg(emptySeat.getTable(), emptySeat.getTotalChips());
  }

  /**
   * 查找空位置,优先查找有人的桌子 .
   * 
   * @param player 玩家.
   * @param room .
   * @return
   */
  private JcbySeat findEmptySeat(Player player, JcbyRoom room) {
    JcbySeat emptySeat = null;
    ArrayList<JcbyTable> tables = new ArrayList<>(room.getTables().values());
    Collections.shuffle(tables);

    boolean robot = player.isRobot();
    for (JcbyTable table : tables) {
      boolean hasPlayer = false;
      JcbySeat tableEmptySeat = null;

      if (robot && table.robotFull()) {
        continue;
      }

      for (JcbySeat seat : table.getSeats()) {
        if (seat.getPlayerId() == 0) {
          emptySeat = seat;
          tableEmptySeat = seat;
        } else {
          hasPlayer = true;
        }
      }

      if (hasPlayer && tableEmptySeat != null) {
        return tableEmptySeat;
      }
    }

    return emptySeat;
  }

  /**
   * 获取游戏最新信息.
   * 
   * @Title gameLastInfo.
   * @author houdongsheng
   * @date 2018年3月12日 下午5:51:59
   * @param player 玩家
   * @return ResMessage
   */
  public ResMessage gameLastInfo(Player player) {
    AbstractRoom curRoom = player.curRoom;
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    ResMessage result = null;
    if (curRoom != null && seat != null) {
      result = msgMgr.sendGameLastInfo(seat);
    } else {
      result = new ResGameInfoMsg();
      player.curRoom = null;
      player.curTable = null;
      player.curSeat = null;
    }
    return result;
  }

  /**
   * 玩家进入座位 .
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   */
  private void doEnterTable(Player player, JcbySeat seat) {
    int roomId = seat.getTable().getRoom().getId();
    JcbyTable table = seat.getTable();

    JcbyRoomDomain roomDom = table.getRoom().getRoomDomain();
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }


    if (roomDom.getAfee() > 0) {
      long beforeSafeGold = player.getSafeGold();
      HuohuaAssert.isTrue(beforeSafeGold >= roomDom.getAfee(), ErrorCode.GAME_NOT_ENOUGH_FEE);
      long afterSafeGold = beforeSafeGold - roomDom.getAfee();

      while (!playerMgr.addSafeGold(player, beforeSafeGold, afterSafeGold,
          ThirdOrderUtils.generateThirdOrderNo(), LogReason.SHARK_ENTER_TABLE_MINUS_AFEE)) {
        beforeSafeGold = player.getSafeGold();
        HuohuaAssert.isTrue(beforeSafeGold >= roomDom.getAfee(), ErrorCode.GAME_NOT_ENOUGH_FEE);
        afterSafeGold = beforeSafeGold - roomDom.getAfee();
      }
      DbLogService.log(new GameAfeeLog(player, table, roomDom.getAfee()));
    }

    seat.setPlayerId(player.getId());
    seat.setPlayerName(player.getPlayerName());
    seat.setSex(player.getSex().equals("男") ? (byte) 0 : (byte) 1);
    seat.setRobot(player.robot());
    seat.setState(SeatState.GAMING);

    if (!seat.isRobot()) {
      if (table.getLeader() == 0) {
        table.setLeader(seat.getPlayerId());
        msgMgr.sendInsteadPlayersUpgradeMsg(table);
      }
    } else {
      if (table.getLeader() != 0) {
        msgMgr.sendInsteadPlayersUpgradeMsg(table);
      }
    }

    // 更新玩家座位
    dataMgr.updatePlayerSeats(player.getId(), seat);
    // 触发进入桌子事件
    eventMgr.post(new EnterTableEvent(player, seat));

    LOG.info("[金蝉捕鱼]玩家[{}][{}]进入桌子[{}]座位[{}]成功", player.getPlayerName(), player.getId(),
        table.getId(), seat.getOrder());
    if (table.playersNum() == 1) {
      scheduleStartNextScence(table, 0);
    }

    player.curSeat = seat;

    msgMgr.sendOtherEnterTableMsg(seat);

  }

  /**
   * 发送场景数据 .
   * 
   * @param player 玩家.
   */
  public ResMessage sendScenceData(Player player) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.warn("[金蝉捕鱼]玩家[{}][{}]还未进入桌子不能获取场景数据", player.getId(), player.getPlayerName());
      HuohuaAssert.isTrue(false);
    }

    JcbyRoom room = seat.getTable().getRoom();
    if (room.getLikui() > 0) {
      msgMgr.sendLikuiUpgradeMsg(seat);
    }
    LOG.info("[金蝉捕鱼]玩家[{}][{}]获取场景数据", player.getId(), player.getPlayerName());
    return msgMgr.sendScenceMsg(player, seat.getTable());
  }

  /**
   * schedule开始下一个场景 .
   * 
   * @param table .
   * @param nextScenceIndex .
   */
  public void scheduleStartNextScence(JcbyTable table, int nextScenceIndex) {
    // 清空上一个场景
    table.scence().reset();
    // 先发送切换场景消息，客户端播放切换场景动画
    msgMgr.sendSwitchSceneMsg(table, nextScenceIndex);
    table.setPauseFire(true);
    table.scenceFuture = scheduleMgr.schedule(() -> {
      doStartScence(table, nextScenceIndex);
    }, SCENCE_DELAY, TimeUnit.SECONDS, gameExecutor);
  }

  /**
   * 开始场景 .
   * 
   * @param table .
   * @param scenceIndex .
   */
  private void doStartScence(JcbyTable table, int scenceIndex) {
    table.setScenceIndex(scenceIndex);
    JcbyScence scence = table.scence();
    table.setPauseFire(false);
    /*
     * schedule刷鱼策略 .
     */
    JcbyScenceDomWrapper scenceDom = scenceDic.get(scence.getId());
    for (Integer strategyId : scenceDom.getStrategysData()) {
      Strategy strategy = dataMgr.getStrategy(strategyId);
      scheduleFishMgr.scheduleProduceFishs(scence, strategy, null);
    }

    int nextScenceIndex = (scenceIndex + 1) % table.getScences().size();
    // schedule下一场比赛
    table.scenceFuture = scheduleMgr.schedule(() -> {
      scheduleStartNextScence(table, nextScenceIndex);
    }, scenceDom.getLifeTime(), TimeUnit.SECONDS, gameExecutor);
  }

  /**
   * 玩家金币兑换筹码 .
   * 
   * @param player 玩家.
   * @param gold 金币.
   */
  public ResMessage exchangeChips(Player player, long gold) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(player.getSafeGold() >= gold);

    JcbyRoomDomain roomDom = seat.getTable().getRoom().getRoomDomain();


    long chips = convertChip(roomDom, gold);
    seat.setTotalChips(seat.getTotalChips() + chips);
    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);

    playerMgr.addSafeGold(player, -gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.JCBY_EXCHANGE_CHIP);

    playerMgr.addGold(player, gold, LogReason.JCBY_EXCHANGE_CHIP);

    // playerMgr.addAllGold(player, -gold, gold, LogReason.JCBY_EXCHANGE_CHIP);
    // playerMgr.minusGold(player, exchangeGold, LogReason.JCBY_EXCHANGE_CHIPS);

    LOG.info("[金蝉捕鱼]玩家[{}][{}]用金币[{}]兑换筹码[{}]成功", player.getPlayerName(), player.getId(), gold,
        chips);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  /**
   * 玩家筹码兑换金币,捕鱼是全部下 .
   * 
   * @param player 玩家.
   * @param chips 筹码.
   */
  public ResMessage exchangeGold(Player player, long chips) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(chips > 0 && chips <= seat.getTotalChips());

    seat.setTotalChips(seat.getTotalChips() - chips);
    long gold = convertGold(seat.getTable().getRoom().getRoomDomain(), chips);

    playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.JCBY_EXCHANGE_GOLD);
    playerMgr.addGold(player, -gold, LogReason.JCBY_EXCHANGE_GOLD);

    // playerMgr.addAllGold(player, gold, -gold, LogReason.JCBY_EXCHANGE_GOLD);

    // 发送兑换筹码变更
    msgMgr.sendChipsChangeMsg(seat);
    // playerMgr.addGold(seat.playerId, gold, LogReason.JCBY_EXCHANGE_GOLD);

    LOG.info("[金蝉捕鱼]玩家[{}][{}]兑换金币[{}]成功", seat.getPlayerName(), seat.getPlayerId(), gold);

    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }


  /**
   * 筹码换算金币,只会是多个金币等于一个筹码 .
   * 
   * @param chips .
   */
  public long convertGold(JcbyRoomDomain roomDom, long chips) {
    return chips * roomDom.getProportionGold() * Player.PRECISION / roomDom.getProportionChips();
  }

  /**
   * 金币换算筹码 .
   * 
   * @param gold .
   * @return
   */
  public long convertChip(JcbyRoomDomain roomDomain, long gold) {
    return gold * roomDomain.getProportionChips() / roomDomain.getProportionGold()
        / Player.PRECISION;
  }

  /**
   * 切换炮台 .
   * 
   * @param player 玩家.
   * @param type . 类型(1:加炮,非1:减炮)
   */
  public ResMessage switchBattery(Player player, int type) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]不在牌桌中不能切换炮台", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    if (seat.isPower()) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]当前是能量炮不能切换炮台", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    int nextBatteryId = 0;
    // 最大炮管id
    int maxBatteryId = batteryDic.list().size();
    if (type == 1) { // 加炮
      nextBatteryId = seat.getBatteryId() + 1;
      if (nextBatteryId > maxBatteryId) {
        nextBatteryId = 1;
      }
    } else { // 减炮
      nextBatteryId = seat.getBatteryId() - 1;
      if (nextBatteryId < 1) {
        nextBatteryId = maxBatteryId;
      }
    }
    seat.setBatteryId(nextBatteryId);
    msgMgr.sendBatteryChangeMsg(seat);

    return ResMessage.DEFAULT;
  }

  /**
   * 开火 .
   * 
   * @param player 玩家.
   * @param angle .
   */
  public ResMessage fire(Player player, int angle) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      HuohuaAssert.isTrue(false);
    }

    int score = batteryDic.get(seat.getBatteryId()).getScore();
    if (seat.getTotalChips() < score || seat.getTable().isPauseFire()) {
      HuohuaAssert.isTrue(false);
    }

    seat.setTotalChips(seat.getTotalChips() - score);
    seat.setWinChips(seat.getWinChips() - score);
    msgMgr.sendChipsChangeMsg(seat);


    long bulletId = seat.getBulletSeed();
    seat.setBulletSeed(seat.getBulletSeed() + 1);

    seat.getBullets().put(bulletId, new Bullet(bulletId, score, seat.isPower()));
    msgMgr.sendFireMsg(seat, bulletId, angle);

    long betGold = -convertGold(seat.getTable().getRoom().getRoomDomain(), score);
    playerMgr.addGold(player, betGold, false, false, LogReason.JCBY_FIRE);

    seat.setBetting(seat.getBetting() + -betGold);

    // 定时吃子弹(防止短线后字段不清除)
    ScheduledFuture<?> schedule = scheduleMgr.schedule(() -> {
      Bullet bullet = seat.getBullets().remove(bulletId);
      if (bullet != null) {
        controlScript.eatBullet(seat, bullet);
      }
    }, EAT_BULLET_DELAY, TimeUnit.SECONDS, gameExecutor);

    seat.getBulletScheduleMap().put(bulletId, schedule);

    return ResMessage.DEFAULT;
  }

  /**
   * 子弹打中鱼,某个客户端会代发机器人打中 .
   * 
   * @param hitPlayerId .
   * @param bulletId .
   * @param fishId . 鱼的唯一标识
   */
  public ResMessage hit(long hitPlayerId, long bulletId, int fishId) {
    JcbySeat seat = dataMgr.getPlayerSeat(hitPlayerId);
    HuohuaAssert.isTrue(seat != null);
    Map<Long, Bullet> bullets = seat.getBullets();
    JcbyTable table = seat.getTable();
    JcbyScence scence = table.scence();
    Bullet bullet = bullets.remove(bulletId);
    if (bullet == null) {
      return ResMessage.DEFAULT;
    }
    // 取消定时
    seat.getBulletScheduleMap().remove(bulletId).cancel(false);
    // 被打中的鱼
    JcbyFish hitedFish = scence.getFishs().get(fishId);
    if (hitedFish == null || !hitedFish.alive()) { // 鱼不存在或鱼已经死了，奖池吃子弹
      controlScript.eatBullet(seat, bullet);
      return ResMessage.DEFAULT;
    }

    JcbyRoom room = table.getRoom();
    JcbyFishDomWrapper fishDom = fishDic.get(hitedFish.getFishId());
    JcbyRoomDomain roomDom = room.getRoomDomain();
    int deathStrategy = fishDom.getDeathStrategy();
    Triplet<LinkedHashSet<JcbyFish>, Long, Long> hitDiedData =
        controlScript.hitDied(seat, bullet, hitedFish);
    // 如果该鱼死亡的导致的鱼死亡集合
    LinkedHashSet<JcbyFish> dieFishs = hitDiedData.v1;
    // 合计鱼的倍数
    long totalFishMultiple = hitDiedData.v2;
    // 合计鱼死亡获得的筹码
    long totalFishChips = hitDiedData.v3;
    // 打中鱼的子弹分数(能量炮子弹需要加倍)
    int bulletScore = bullet.power ? bullet.score * 2 : bullet.score;

    if (dieFishs != null) {
      // 被打死的鱼分数(key：鱼id,val:鱼的分数)
      Map<Integer, Integer> fishScores = new LinkedHashMap<>();
      for (JcbyFish dieFish : dieFishs) {
        dieFish.setDied(true);
        int fishScore = bulletScore * dieFish.getMultiple();
        if (dieFish.getFishId() == room.getLikui()) { // 李逵死后子弹清0
          // 子弹清0前倍率
          int preLikuiMultiple = room.likuiMultiple();
          room.setLikuiEatBullets(0);
          // 子弹清0后倍率
          int likuiMultiple = room.likuiMultiple();
          if (likuiMultiple != preLikuiMultiple) { // 李逵等级变化了
            updateLikuiMultiple(room, likuiMultiple);
          }
        }

        fishScores.put(dieFish.getId(), fishScore);
        if (deathStrategy > 0) {
          scheduleFishMgr.scheduleProduceFishs(scence, dataMgr.getStrategy(deathStrategy),
              hitedFish.position());
        }

        // 鱼死后是否发送公告
        sendedNotice(seat, dieFish, fishScore);
      }

      // 能量炮处理
      if (!seat.isPower()) {
        int powerMultiple = roomDom.getPowerBatteryMultiple();
        int powerPro = roomDom.getPowerBatteryPro();
        int powerTime = roomDom.getPowerBatteryTime();
        if (totalFishMultiple >= powerMultiple && RandCodeUtil.probable(powerPro, 10000)) {
          seat.setPower(true);
          msgMgr.sendBatteryChangeMsg(seat);
          scheduleMgr.schedule(() -> {
            seat.setPower(false);
            msgMgr.sendBatteryChangeMsg(seat);
          }, powerTime, TimeUnit.SECONDS, gameExecutor);
        }
      }

      seat.setTotalChips(seat.getTotalChips() + totalFishChips);
      seat.setWinChips(seat.getWinChips() + totalFishChips);

      long winGold = convertGold(roomDom, totalFishChips);
      playerMgr.addGold(playerMgr.selectPlayer(seat.getPlayerId()), winGold, false,
          LogReason.JCBY_HIT);

      seat.setBonus(seat.getBonus() + winGold);

      msgMgr.sendHitsMsg(seat, fishScores);
      msgMgr.sendChipsChangeMsg(seat);
    } else if (hitedFish.getFishId() == room.getLikui()) { // 李逵没死，吃子弹
      // 吃子弹前倍率
      int preLikuiMultiple = room.likuiMultiple();
      room.setLikuiEatBullets(room.getLikuiEatBullets() + bullet.score);
      // 吃子弹后倍率
      int likuiMultiple = room.likuiMultiple();
      if (likuiMultiple > preLikuiMultiple) { // 李逵升级了
        updateLikuiMultiple(room, likuiMultiple);
      }
    }

    return ResMessage.DEFAULT;
  }

  /**
   * 如果满足条件发送公告 .
   * 
   * @param seat 座位信息.
   * @param dieFish .
   * @param chips .
   */
  private void sendedNotice(JcbySeat seat, JcbyFish dieFish, long chips) {
    JcbyFishDomWrapper fishDom = fishDic.get(dieFish.getFishId());
    // 0:无公告,1:全服公告,2:本房间公告,只有 跑马灯公告
    int noticeType = fishDom.getNoticeType();

    if (noticeType > 0) {
      // 桌子号
      int tableIndex = seat.getTable().getId() % AbstractTable.MAX_TABLE;
      String sendContent =
          MessageFormat.format(fishDom.getNoticeContent(), tableIndex, seat.getPlayerName(), chips);
      if (noticeType == 1) {
        noticeMsgMgr.sendMarqueeNoticeMsg(sendContent, NoticeType.GAME, Notice.DEFAULT_INTERVAL,
            Notice.DEFAULT_TIMES, Notice.DEFAULT_COLOR, seat.getTable().getRoom());
      } else if (noticeType == 2) {
        noticeMsgMgr.sendMarqueeNoticeMsg(sendContent, NoticeType.GAME, Notice.DEFAULT_INTERVAL,
            Notice.DEFAULT_TIMES, Notice.DEFAULT_COLOR);
      }
    }
  }

  /**
   * 更新李逵倍数 .
   * 
   * @param room .
   * @param multiple .
   */
  private void updateLikuiMultiple(JcbyRoom room, int multiple) {
    for (JcbyTable t : room.getTables().values()) {
      for (JcbyFish f : t.scence().getFishs().values()) {
        if (f.getFishId() == room.getLikui()) {
          f.setMultiple(multiple);
        }
      }
    }

    // 发送李逵升级消息
    msgMgr.sendLikuiUpgradeMsg(room, multiple);
  }

  /**
   * 锁定鱼 .
   * 
   * @param player 玩家.
   * @param fishId .
   */
  public ResMessage lock(Player player, int fishId, int angle) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      HuohuaAssert.isTrue(false);
    }
    if (fishId > 0) {
      seat.setLockAngle(-1);
      seat.setLockFish(fishId);
    } else if (CheckUtil.checkMinValue(angle, 0)) {
      seat.setLockAngle(angle);
      seat.setLockFish(0);
    }

    msgMgr.sendLockMsg(seat, fishId, angle);
    return ResMessage.DEFAULT;
  }

  /**
   * 取消锁定 .
   * 
   * @param player 玩家.
   */
  public ResMessage cancelLock(Player player) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      HuohuaAssert.isTrue(false);
    }
    // 锁定的角度
    seat.setLockAngle(-1);
    // 取消锁定的鱼
    seat.setLockFish(0);

    LOG.info("[金蝉捕鱼]玩家[{}][{}]取消锁定", player.getPlayerName(), player.getId());
    msgMgr.sendCancelLockMsg(seat);
    return ResMessage.DEFAULT;
  }

  /**
   * 玩家退出牌桌 .
   * 
   * @param player 玩家.
   */
  public ResMessage exitTable(Player player) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[金蝉捕鱼]玩家[{}][{}]没在牌桌中不能退出牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    playerMgr.noticeGold(player);
    // 退出桌子
    doExitTabl(seat);
    return ResMessage.DEFAULT;
  }

  /**
   * 退出桌子,返还筹码,桌子leader退出处理 .
   * 
   * @param seat 座位信息.
   */
  private void doExitTabl(JcbySeat seat) {
    final long playerId = seat.getPlayerId();
    final JcbyTable table = seat.getTable();

    // 返还子弹筹码
    long returnBulletChips = 0;
    for (Bullet bullet : seat.getBullets().values()) {
      returnBulletChips += bullet.score;
    }


    seat.setTotalChips(seat.getTotalChips() + returnBulletChips);
    seat.setWinChips(seat.getWinChips() + returnBulletChips);
    // 返还子弹的金币
    // playerMgr.addGold(playerId, convertGold(roomDomain, returnBulletChips),
    // LogReason.JCBY_EXIT_TABL);

    Player player = playerMgr.getPlayer(seat.getPlayerId());
    PlayerBo playerBo = playerMgr.selectPlayer(seat.getPlayerId());
    if (seat.getTotalChips() > 0) {
      if (player != null) {
        long gold = player.getGold();
        playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
            LogReason.JCBY_EXIT_TABLE_EXCHANGE_GOLD);
        playerMgr.addGold(player, -gold, LogReason.JCBY_EXIT_TABLE_EXCHANGE_GOLD);
      } else {
        long gold = playerBo.getGold().get();

        playerMgr.addSafeGold(playerBo, gold, ThirdOrderUtils.generateThirdOrderNo(),
            LogReason.JCBY_EXIT_TABLE_EXCHANGE_GOLD);
        playerMgr.addGold(playerBo, -gold, LogReason.JCBY_EXIT_TABLE_EXCHANGE_GOLD);
      }

      seat.setTotalChips(0L);
    }
    if (seat.getBetting() != 0 || seat.getBonus() != 0) {
      DbLogService.log(new GameGoldLog(playerBo, LogReason.JCBY_EXIT_BILL, seat.getBetting(),
          seat.getBonus(), ThirdGameType.JIN_CHAN_BU_YU, ThirdPlayerType.PLAYER));
    }


    if (player != null) {
      player.curSeat = null;
      player.curTable = null;
    }

    // 兑换所有筹码
    // doExchangeAllGold(seat);
    // 发送退出房间消息
    msgMgr.sendExitTableMsg(seat);
    dataMgr.removePlayerSeat(playerId);
    JcbyRoomDomain roomDomain = table.getRoom().getRoomDomain();

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new Game9WinLoseLog(playerBo, seat, seat.getWinChips(),
          convertGold(roomDomain, seat.getWinChips()), LogReason.JCBY_EXIT_TABL));
    }

    LOG.info("[金蝉捕鱼]玩家[{}][{}]退出桌子[{}]", playerId, seat.getPlayerName(), seat.getTable().getId());
    seat.clear();

    if (table.getLeader() == playerId) { // 桌子leader退出
      table.setLeader(0);
      for (JcbySeat s : table.getSeats()) {
        if (!s.isRobot() && s.getPlayerId() > 0) { // 找下一个leader代发碰撞
          table.setLeader(s.getPlayerId());
          msgMgr.sendInsteadPlayersUpgradeMsg(table);
          break;
        }
      }
    }
    if (table.playersNum() == 0) {
      table.scence().reset();
      table.reset();
    }

    // 触发退出桌子事件
    eventMgr.post(new ExitTableEvent(playerMgr.getPlayer(playerId), seat));
  }

  /**
   * 玩家退出房间 .
   * 
   * @param player 玩家.
   */
  public void doExitRoom(Player player) {
    AbstractRoom curRoom = player.curRoom;
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(curRoom instanceof JcbyRoom);

    HuohuaAssert.isTrue(seat == null, ErrorCode.GAME_EXIT_ROOM_EXIT_SIT_FIRST);

    JcbyRoom room = (JcbyRoom) curRoom;
    room.getPlayers().remove(player.getId());

    LOG.info("[金蝉捕鱼]玩家[{}][{}]退出房间[{}]", player.getPlayerName(), player.getId(), room.getId());
    // 触发退出房间事件
    eventMgr.post(new ExitRoomEvent(player, curRoom));

    player.curRoom = null;

  }

  /**
   * 玩家退出房间 .
   * 
   * @param player 玩家.
   */
  public ResMessage exitRoom(Player player) {
    doExitRoom(player);
    return ResMessage.DEFAULT;
  }

  private void kickoutForRoomClose(Player player) {
    JcbySeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      doExitTabl(seat);
    }
    if (player.curRoom != null) {
      doExitRoom(player);
      commonMsgMgr.noticeRoomCloseKickout(player);
    }
  }

  /**
   * 清除玩家,服务器出现bug，玩家卡在游戏中，后台可以清除玩家 .
   * 
   * @param playerId 玩家id.
   */
  private void doClearGameData(long playerId) {
    try {

      dataMgr.removePlayerSeat(playerId);
      /*
       * 清除玩家房间数据 .
       */
      for (JcbyRoom room : dataMgr.allRooms()) {
        room.getPlayers().remove(playerId);

        /*
         * 清除房间中玩家所在桌子数据 .
         */
        for (JcbyTable table : room.getTables().values()) {
          for (JcbySeat s : table.getSeats()) {
            if (s.getPlayerId() > 0 && s.getPlayerId() == playerId) {
              dataMgr.removePlayerSeat(s.getPlayerId());
              room.getPlayers().remove(s.getPlayerId());

              /*
               * 清除座位数据 .
               */
              s.clear();
              Player player = playerMgr.getPlayer(s.getPlayerId());
              if (player != null) {
                loginMgr.noticeLogoutForGameExit(player);
              }
            }
          }
        }
      }

      Player player = playerMgr.getPlayer(playerId);
      if (player != null) {
        loginMgr.noticeLogoutForGameExit(player);
      }
    } catch (Exception e) {
      LOG.error("[金蝉捕鱼]清除玩家[" + playerId + "]卡限失败", e);
    }
  }
}
