package com.idealighter.game.core.service.games.ddz.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.TreeMultimap;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.CancelPlayerExitEvent;
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
import com.idealighter.game.core.service.games.bairenniuniu.manager.BaiRenNiuNiuDataMgr;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuRoom;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.core.service.games.ddz.struct.DdzAiSeat;
import com.idealighter.game.core.service.games.ddz.struct.DdzCallType;
import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import com.idealighter.game.core.service.games.ddz.struct.DdzCardsType;
import com.idealighter.game.core.service.games.ddz.struct.DdzRoom;
import com.idealighter.game.core.service.games.ddz.struct.DdzTable;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeComparator;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeFinder;
import com.idealighter.game.core.service.games.ddz.util.CardsTypeGetter;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.game.Game25WinLoseLog;
import com.idealighter.game.core.service.log.struct.game.GameAfeeLog;
import com.idealighter.game.core.service.log.struct.resource.GameGoldLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.DdzTimeDic;
import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.games.ddz.constant.DdzStage;
import com.idealighter.game.games.ddz.constant.DdzTime;
import com.idealighter.game.games.ddz.message.ResGameInfoMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.game.third.constant.ThirdGameType;
import com.idealighter.game.third.constant.ThirdPlayerType;
import com.idealighter.game.third.utils.ThirdOrderUtils;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;
import com.idealighter.utils.json.JsonUtil;

/**
 * 斗地主。 .
 * 
 * @date 2016年2月25日 上午9:45:24
 *
 */
@Singleton
public class DdzMgr {
  private static final Logger LOG = LoggerFactory.getLogger(DdzMgr.class);
  // 进入后超时准备
  public static final int READY_LIMIT_WHEN_ENTER_TABLE = 15;
  // 玩家动作动作延时(有网络延时等原因，要比客户端的定时长一点)
  public static final int PLAYER_ACTION_DELAY = 2;
  // 系统托管动作时间(秒)
  public static final int HOST_ACTION_SECONDS = 3;
  // 机器人动作最大时间(秒)
  public static final int ROBOT_ACTION_MAX_SECONDS = 5;
  // 农民的牌数量
  public static final int FARMER_CARDS = 17;
  // 底牌数量
  public static final int HIDDEN_CARDS = 3;
  // 通知比赛时间(秒)
  public static final int NOTICE_MATCH_TIME = 60;
  // 客户端最长加载时间
  public static final int GAME_LOAD_LIMIT = 30;

  private final DdzDataMgr dataMgr;
  private final DdzMsgMgr msgMgr;
  private final PlayerMgr playerMgr;
  private final CommonMsgMgr commonMsgMgr;
  private DdzTimeDic timeDic;
  private final EventMgr eventMgr;
  private final ScheduleMgr scheduleMgr;
  // 游戏线程executor,可以执行普通任务和定时任务
  public final DisruptorExecutor gameExecutor;
  private final Game game = Game.DDZ;
  // 离线玩家
  private Vector<Long> logoutPlayers = new Vector<Long>();

  @Inject
  private LoginMgr loginMgr;

  /**
   * 构造函数.
   * 
   * @param dataMgr .
   * @param msgMgr .
   * @param playerMgr .
   * @param commonMsgMgr .
   * @param timeDic .
   * @param eventMgr .
   * @param scheduleMgr .
   * @param executorMgr .
   */
  @Inject
  public DdzMgr(DdzDataMgr dataMgr, DdzMsgMgr msgMgr, PlayerMgr playerMgr,
      CommonMsgMgr commonMsgMgr, DdzTimeDic timeDic, EventMgr eventMgr,
      ScheduleMgr scheduleMgr, ExecutorMgr executorMgr) {
    this.dataMgr = dataMgr;
    this.msgMgr = msgMgr;
    this.playerMgr = playerMgr;
    this.commonMsgMgr = commonMsgMgr;
    this.timeDic = timeDic;
    this.eventMgr = eventMgr;
    this.scheduleMgr = scheduleMgr;
    this.gameExecutor = executorMgr.getGameExecutor(Game.DDZ.getModuleId());

    eventMgr.register(this);
  }

  /**
   * T玩家离开事件监听.
   * 
   * @param event 玩家离开事件.
   */
  @Subscribe
  public void onPlayerExitGame(PlayerExitEvent event) {
    Player player = event.player;
    if (player != null) {
      long playerId = player.getId();
      if (player.curRoom instanceof DdzRoom) {
        gameExecutor.execute(() -> {
          // 玩家是否在牛牛房间中
          if (player.curRoom instanceof DdzRoom) {
            logoutPlayers.add(playerId);
            LOG.info("[{}]玩家预约退出 {}", game.getDesc(), playerId);
            // 离线后托管
            DdzAiSeat seat = dataMgr.getPlayerSeat(playerId);
            if (!seat.isSysHost()) {
              doTrustee(seat);
            }
          }
        });
      }
    }
  }

  /**
   * 取消玩家退出游戏.
   * 
   * @param event 退出游戏事件.
   */
  @Subscribe
  public void onCancelPlayerExitEvent(CancelPlayerExitEvent event) {
    gameExecutor.execute(() -> {

      Player player = event.player;
      long playerId = player.getId();
      if (logoutPlayers.contains(playerId)) {
        logoutPlayers.remove(playerId);
        LOG.info("[{}]玩家取消退出 {}", game.getDesc(), playerId);
      }
    });
  }

  private void clearLogoutPlayer(DdzTable table) {
    if (logoutPlayers != null && !logoutPlayers.isEmpty()) {
      List<Long> clearIds = new ArrayList<Long>();
      logoutPlayers.forEach(playerId -> {
        if (table.players().contains(playerId.longValue())) {
          clearLogoutPlayer(playerId);
          clearIds.add(playerId);
        }
      });
      if (EmptyUtil.listIsNotEmpty(clearIds)) {
        logoutPlayers.removeAll(clearIds);
        clearIds.clear();
      }
    }
  }

  private void clearLogoutPlayer(long playerId) {
    LOG.info("[{}]清空玩家 {}", this.game.getDesc(), playerId);

    Player player = playerMgr.getPlayer(playerId);
    if (player != null) {
      DdzAiSeat seat = dataMgr.getPlayerSeat(playerId);
      if (seat != null) {
        // 玩家在房间中但是没有准备，退出桌子，退出房间
        doExitTable(seat);
      }

      // 玩家是否在牛牛房间中
      if (player.curRoom instanceof DdzRoom) {
        // 退出房间
        doExitRoom(player);

        // 触发退出
        GameClearExitEvent gameClearExitEvent =
            new GameClearExitEvent(player, this.game.getDesc());
        eventMgr.post(gameClearExitEvent);
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
  private boolean checkClose(DdzTable table) {
    boolean remove = false;
    if (table.getRoom().getInstanceState() == InstanceState.TO_REMOVE) {
      boolean noExit = false;
      if (table.isGaming()) {
        noExit = true;
      }
      if (!noExit) {
        for (Long playerId : table.players()) {
          Player player = playerMgr.getPlayer(playerId);
          if (player != null) {
            kickoutForRoomClose(player);
          }
        }
        DdzRoom room = table.getRoom();
        room.removeTable(table.getId());

        table = null;
        remove = true;
      }
    }
    return remove;
  }

  private void deleteRooms(List<DdzRoom> deleteRooms) {
    if (EmptyUtil.listIsNotEmpty(deleteRooms)) {
      for (Iterator<DdzRoom> iterator = deleteRooms.iterator(); iterator.hasNext();) {
        DdzRoom room = iterator.next();
        List<DdzTable> tables = new ArrayList<>(room.tables());
        for (Iterator<DdzTable> tableIterator = tables.iterator(); tableIterator.hasNext();) {
          DdzTable table = tableIterator.next();
          checkClose(table);
        }
        dataMgr.checkRemoveInstance(room.getId(), room.getInstanceId());
      }
    }
  }

  private void kickoutForRoomClose(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      doExitTable(seat);
    }
    if (player.curRoom != null) {
      doExitRoom(player);
      commonMsgMgr.noticeRoomCloseKickout(player);
    }
  }

  /**
   * 开启房间事件监听.
   * 
   * @param event 开启房间.
   */
  @Subscribe
  public void onStartRoom(StartRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.DDZ) {
        List<DdzRoom> deleteRooms = dataMgr.startRoom(event.getRoomId(), true);
        deleteRooms(deleteRooms);
      }
    });
  }

  /**
   * 关闭房间监听事件.
   * 
   * @param event 关闭房间事件.
   */
  @Subscribe
  public void onShutDownRoom(ShutdownRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.DDZ) {
        List<DdzRoom> deleteRooms = dataMgr.deleteRoom(event.getRoomId());
        deleteRooms(deleteRooms);
      }
    });
  }

  /**
   * 开启游戏 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:24:51
   * @param event 开启游戏事件.
   */
  @Subscribe
  public void onStartGame(StartGameEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.DDZ) {
        List<Integer> ids = dataMgr.reloadRoom();
        if (EmptyUtil.listIsNotEmpty(ids)) {
          for (Integer id : ids) {
            List<DdzRoom> deleteRooms = dataMgr.startRoom(id, false);
            deleteRooms(deleteRooms);
          }
        }
      }
    });
  }

  /**
   * 关闭游戏事件.
   * 
   * @param event 事件.
   */
  @Subscribe
  public void onShutdownGame(ShutdownGameEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.DDZ) {
        Collection<DdzRoom> rooms = dataMgr.allRooms();
        if (!EmptyUtil.isEmpty(rooms)) {
          for (DdzRoom room : rooms) {
            List<DdzRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
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
    LOG.info("斗地主开始退出.....");
    gameExecutor.execute(() -> {
      Collection<DdzRoom> rooms = dataMgr.allRooms();
      if (!EmptyUtil.isEmpty(rooms)) {
        for (DdzRoom room : rooms) {
          List<DdzRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
          deleteRooms(deleteRooms);
        }
      }
    });

    int times = 600;
    while (!DdzDataMgr.roomEmpty && times > 0) {
      try {
        Thread.sleep(500L);
        times--;
      } catch (InterruptedException exception) {
        LOG.error("斗地主退出异常", exception);
      }
    }

    LOG.info("斗地主退出");
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
      LOG.warn("[{}]玩家[{}][{}]在[{}]游戏中，暂时无法进入游戏", game.getDesc(), player.getId(),
          player.getPlayerName(), game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    LOG.info("[{}]玩家[{}][{}]进入大厅成功", game.getDesc(), player.getPlayerName(), player.getId());
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
      LOG.warn("[{}]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入房间", game.getDesc(), player.getId(),
          player.getPlayerName(), curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    DdzRoom room = dataMgr.getNewestRoom(roomId);
    if (room == null) {
      LOG.error("[{}]玩家[{}][{}]进入的房间[{}]不存在", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_NOT_EXIST);
    }

    DdzRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != 1) {
      LOG.error("[{}]玩家[{}][{}]进入的房间[{}]已经关闭", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_CLOSED);
    }

    if (player.getSafeGold() < (roomDom.getLower()+roomDom.getAfee())) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币+台服[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getLower(), roomDom.getAfee());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    if (room.getPlayers().size() >= roomDom.getMaxNum()) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    // 发送进入房间消息
    LOG.info("[{}]玩家[{}][{}]进入房间[{}]成功", this.game.getDesc(), player.getPlayerName(),
        player.getId(), roomId);
    return msgMgr.sendEnterRoomMsg(room);
  }
  
  public ResMessage fastEnter(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;
    if (curRoom != null) {
      Game game = curRoom.game();
      LOG.warn("[{}]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入房间", game.getDesc(), player.getId(),
          player.getPlayerName(), curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    DdzRoom room = dataMgr.getNewestRoom(roomId);
    if (room == null) {
      LOG.error("[{}]玩家[{}][{}]进入的房间[{}]不存在", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_NOT_EXIST);
    }

    DdzRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != 1) {
      LOG.error("[{}]玩家[{}][{}]进入的房间[{}]已经关闭", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_CLOSED);
    }

    if (player.getSafeGold() < (roomDom.getLower() + roomDom.getAfee())) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币+台费[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getLower(), roomDom.getAfee());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    if (room.getPlayers().size() >= roomDom.getMaxNum()) {
      LOG.warn("[{}]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    // 发送进入房间消息
    LOG.info("[{}]玩家[{}][{}]进入房间[{}]成功", this.game.getDesc(), player.getPlayerName(),
        player.getId(), roomId);
    
    DdzAiSeat emptySeat = findEmptySeat(player, room, 0);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);
    
    doEnterTable(player, emptySeat, null);
    return ResMessage.DEFAULT;
  }

  /**
   * @Title gameLastInfo.
   * @Description 获取游戏最新消息
   * @author houdongsheng
   * @date 2018年8月22日 上午9:46:23
   * @param player .
   * @return .
   */
  public ResMessage gameLastInfo(Player player) {
    AbstractRoom curRoom = player.curRoom;
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());

    ResMessage result = null;
    if (curRoom != null && seat != null) {
      DdzRoom room = (DdzRoom) curRoom;
      seat.setTotalChips(convertChip(room.getRoomDomain(), player.getGold()));
      result = msgMgr.sendGameLastInfo(seat);

      if (logoutPlayers.contains(player.getId())) {
        logoutPlayers.remove(player.getId());
        LOG.info("[{}]玩家取消退出 {}", game.getDesc(), player.getId());
      }
    } else {
      result = new ResGameInfoMsg();
    }
    LOG.info("gameLastInfo: [{}]", JsonUtil.toJson(result));
    return result;
  }

  /**
   * 快速进入.
   * 
   * @param player .
   * @param roomId .
   */
  public ResMessage fastEnterTable(Player player, int roomId) {
    if (!(player.curRoom instanceof DdzRoom)) {
      LOG.error("[{}]玩家[{}][{}]当前不在房间中不能直接进入桌子", game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }

    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      LOG.error("[{}]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }

    DdzRoom room = (DdzRoom) player.curRoom;
    if (room == null) {
      LOG.error("[{}]玩家[{}][{}]快速进入的房间[{}]不存在", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId);
      HuohuaAssert.isTrue(false);
    }

    DdzAiSeat emptySeat = findEmptySeat(player, room, 0);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);

    DdzRoomDomain roomDom = room.getRoomDomain();
    if (player.getSafeGold() < roomDom.getLower() + roomDom.getAfee()) {
      LOG.warn("[{}]玩家[{}][{}]进入的桌子[{}][{}]低于下限[{}]金币+台费[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, emptySeat.getTable().getId(), roomDom.getLower(), roomDom.getAfee());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[{}]玩家[{}][{}]进入的桌子[{}]高于上限[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomId, emptySeat.getTable().getId(), roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (player.getSafeGold() < roomDom.getAfee()) {
      LOG.warn("[{}]玩家[{}][{}]进入桌子金币不足台费", game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_ENOUGH_FEE);
    }

    doEnterTable(player, emptySeat, null);
    return ResMessage.DEFAULT;
  }

  /**
   * 查找空位置,优先查找有人的桌子.
   * 
   * @param player .
   * @param room .
   * @return .
   */
  private DdzAiSeat findEmptySeat(Player player, DdzRoom room, long exceptTableId) {
    DdzAiSeat emptySeat = null;
    ArrayList<DdzTable> tables = new ArrayList<>(room.getTables().values());
    Collections.shuffle(tables);

    for (DdzTable table : tables) {
      if (table.getPassword() != null) {
        continue;
      }

      if (exceptTableId > 0 && table.getId() == exceptTableId) {
        continue;
      }

      if (table.getLimitIp() != null && table.getLimitIp().equals(player.ip)) {
        continue;
      }

      if (player.getSafeGold() < table.getLimitGold()) {
        continue;
      }

      if (table.isGaming()) {
        continue;
      }

      boolean hasPlayer = false;
      DdzAiSeat tableEmptySeat = null;

      List<DdzAiSeat> seats = new ArrayList<>(table.getSeats());
      Collections.shuffle(seats);
      for (DdzAiSeat seat : seats) {
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
   * 玩家进入座位.
   * 
   * @param player .
   * @param seat .
   */
  private void doEnterTable(Player player, DdzAiSeat seat, String tablePwd) {
    DdzTable table = seat.table();

    DdzRoomDomain roomDom = table.getRoom().getRoomDomain();
    if (roomDom.getAfee() > 0) {
      long beforeSafeGold = player.getSafeGold();
      HuohuaAssert.isTrue(beforeSafeGold >= roomDom.getAfee(), ErrorCode.GAME_NOT_ENOUGH_FEE);
      long afterSafeGold = beforeSafeGold - roomDom.getAfee();
      
      while (!playerMgr.addSafeGold(player, beforeSafeGold, afterSafeGold,
          ThirdOrderUtils.generateThirdOrderNo(),
          LogReason.DDZ_ENTER_TABLE_MINUS_AFEE)) {
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
    seat.setState(SeatState.SEATED); // 由于只有三个人，不允许旁观，所以进入后直接准备
    seat.setTotalChips(convertChip(roomDom, player.getGold()));

    // 更新玩家座位
    dataMgr.updatePlayerSeats(player.getId(), seat);
    // 触发进入桌子事件
    eventMgr.post(new EnterTableEvent(player, seat));

    player.curSeat = seat;

    // 准备时间
    int readyTime = timeDic.get(DdzTime.READY.val).getTime();
    // 向玩家发送进入牌桌结果信息
    msgMgr.sendEnterTableMsg(player, table, seat.getTotalChips(), readyTime);
    msgMgr.sendOtherEnterTableMsg(seat);

    LOG.info("[{}]玩家[{}][{}]进入桌子[{}][{}]座位[{}]成功", game.getDesc(), player.getPlayerName(),
        player.getId(), table.getRoom().getId(), table.getId(), seat.getOrder());

//    stepFutureDisable(seat.stepFuture);
//    seat.stepFuture = scheduleMgr.schedule(() -> {
//      // 超时退出
//      doExitTable(seat);
//      doExitRoom(player);
//    },  GAME_LOAD_LIMIT + PLAYER_ACTION_DELAY, TimeUnit.SECONDS, gameExecutor);
  }
  

  public ResMessage loadOk(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]还未进入牌桌不能loadOk", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }
    
    if (seat.getState().equals(SeatState.SEATED) && seat.isLoaded() == false) {
      seat.setLoaded(true);
      // 准备时间
      int readyTime = timeDic.get(DdzTime.READY.val).getTime();
      // 准备超时,退出
      stepFutureDisable(seat.stepFuture);
      seat.stepFuture = scheduleMgr.schedule(() -> {
        // 超时退出
        doExitTable(seat);
        doExitRoom(player);
      },  readyTime + PLAYER_ACTION_DELAY, TimeUnit.SECONDS, gameExecutor);
    } 

    return ResMessage.DEFAULT;
  }
  
  /**
   * 玩家金币兑换筹码.
   * 
   * @param player 玩家.
   * @param gold 兑换筹码的金币.
   */
  public ResMessage exchangeChips(Player player, long gold) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(player.getSafeGold() >= gold);

    long chips = convertChip(seat.getTable().getRoom().getRoomDomain(), gold);

    seat.setTotalChips(seat.getTotalChips() + chips);

    playerMgr.addSafeGold(player, -gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.DDZ_EXCHANGE_CHIP);

    playerMgr.addGold(player, gold, LogReason.DDZ_EXCHANGE_CHIP);
    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);

    LOG.info("[{}]玩家[{}][{}]用金币[{}]兑换筹码[{}]成功", this.game.getDesc(), player.getPlayerName(), player.getId(), gold,
        chips);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  private boolean stepFutureDisable(ScheduledFuture<?> stepFuture) {
    if (stepFuture != null) {
      if (stepFuture.isCancelled() || stepFuture.isDone()) {
        // stepFuture = null;
        return true;
      } else {
        boolean result = stepFuture.cancel(false);
        // stepFuture = null;
        return result;
      }
    }
    return true;
  }

  /**
   * 准备 .
   * 
   * @param seat 座位信息.
   */
  private void doReady(DdzAiSeat seat) {
    // 取消退出超时
    stepFutureDisable(seat.getStepFuture());
    seat.setState(SeatState.READY);
    DdzTable table = seat.getTable();
    
    // 桌子准备好且没有开始游戏
    if (table != null && table.readyOver() && (table.getStatus().equals(DdzStage.READY)
        || table.getStatus().equals(DdzStage.NO_START))) {
      // 发牌
      doDealCards(table);
    }
    
    LOG.info("[{}]房间[{}][{}]玩家[{}][{}]准备", game.getDesc(), table.room().getId(), table.getId(),
        seat.getPlayerId(), seat.getPlayerName());
    // 发送准备消息
    msgMgr.sendReadyMsg(seat);
  }

  /**
   * 发牌,每人先发17张 .
   * 
   * @param table .
   */
  private void doDealCards(DdzTable table) {
    table.setStatus(DdzStage.DEAL_CARDS);
    table.setGaming(true);
    LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
        table.getStatus().desc);

    // 发牌阶段时间
    int dealCardTime = timeDic.get(DdzTime.DEAL_CARD.val).getTime();

    Collections.shuffle(table.getCards());
    List<DdzCard> cards = table.getCards();
    int cardIndex = 0;

    List<DdzAiSeat> gamingSeats = table.gamingSeats();
    for (DdzAiSeat seat : gamingSeats) {
      seat.getCards().clear();
      seat.getCards().addAll(cards.subList(cardIndex, cardIndex += FARMER_CARDS));
      // ai需要计算牌型
      seat.calcCardsType();
      // 已经参加游戏,不退回台费
      seat.setPlayedGame(true);
    }

    // 桌子定时取消
    stepFutureDisable(table.stepFuture);
    table.stepFuture = scheduleMgr.schedule(() -> {
      table.setStatus(DdzStage.CALL_DZ);
      LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
          table.getStatus().desc);

      // 随机叫牌的位置
      DdzAiSeat nextSeat = RandCodeUtil.randomList(gamingSeats);
      table.setNextCallSeat(nextSeat);

      // schedule玩家叫牌
      scheduleCallCard(nextSeat, null);

      // 发送游戏开始通知
      msgMgr.sendGameStartMsg(table);
    }, dealCardTime, TimeUnit.SECONDS, gameExecutor);

    // 向同桌的玩家发送牌消息
    msgMgr.sendDealCardsMsg(table);
  }
  
  /**
   * 筹码换算金币,只会是多个金币等于一个筹码 .
   * 
   * @param roomDomain .
   * @param chips 筹码.
   */
  public long convertGold(DdzRoomDomain roomDomain, long chips) {
    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();

  }

  /**
   * 金币换算筹码 .
   * 
   * @param roomDomain .
   * @param gold 金币.
   * @return 筹码数.
   */
  public long convertChip(DdzRoomDomain roomDomain, long gold) {
    return gold * roomDomain.getProportionChips() / roomDomain.getProportionGold()
        / Player.PRECISION;
  }
  
  /**
   * 玩家筹码兑换金币.
   * 
   * @param player 玩家.
   * @param chips 筹码.
   */
  public ResMessage exchangeGold(Player player, long chips) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(chips > 0 && chips <= seat.getTotalChips());

    DdzTable table = seat.getTable();

    HuohuaAssert.isTrue(table.getStatus() == DdzStage.NO_START 
        || table.getStatus() == DdzStage.GAME_OVER || (table.getStatus() == DdzStage.READY && !seat.readied()), ErrorCode.GAME_NOT_ALLOW_EXCHANGE);

    seat.setTotalChips(seat.getTotalChips() - chips);
    long gold = convertGold(seat.getTable().getRoom().getRoomDomain(), chips);

    playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.DDZ_EXCHANGE_GOLD);
    playerMgr.addGold(player, -gold, LogReason.DDZ_EXCHANGE_GOLD);

    // 发送兑换筹码变更
    msgMgr.sendChipsChangeMsg(seat);

    LOG.info("[百人牛牛]玩家[{}][{}]用筹码[{}]兑换金币[{}]成功", player.getPlayerName(), player.getId(), chips,
        gold);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  /**
   * 准备 .
   * 
   * @param player 玩家.
   */
  public ResMessage ready(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]还未进入牌桌不能准备", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    if (!seat.getState().equals(SeatState.SEATED)) {
      LOG.error("[{}]玩家[{}][{}]还未进入牌桌不能准备", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }

    // 准备时，判断金币是否足够
    DdzRoomDomain roomDom = seat.table().room().getRoomDomain();
    if (player.getGold() < roomDom.getLower()) {
      LOG.error("[{}]玩家[{}][{}]兑换的筹码不够，不能准备", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_CHIP_NOT_ENOUGH);
    }

    doReady(seat);
    return ResMessage.DEFAULT;
  }

  /**
   * 玩家叫牌，“叫牌”分最高的玩家就是本局的“地主” .
   * 
   * @param player 玩家.
   * @param type . 0:不叫地主,1:叫地主,2:不抢地主,3:抢地主 .
   */
  public ResMessage callCard(Player player, int type) {
    // 玩家的叫牌类型
    DdzCallType callType = DdzCallType.getCallType(type);
    if (callType == null) {
      LOG.error("[{}]玩家[{}][{}]叫牌类型[{}]", this.game.getDesc(), player.getPlayerName(),
          player.getId(), type);
      HuohuaAssert.isTrue(false, ErrorCode.BAD_REQUEST);
    }

    // 座位
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]还未进入牌桌不能叫牌", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    // 牌桌
    DdzTable table = seat.table();
    if (table.getNextCallSeat() == null || !table.getNextCallSeat().equals(seat)) {
      LOG.error("[{}]当前叫牌顺序为[{}],玩家[{}][{}]顺序为[{}]不能叫牌", this.game.getDesc(),
          table.getNextCallSeat() == null ? -1 : table.getNextCallSeat().getOrder(),
          player.getPlayerName(), player.getId(), seat.getOrder());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_YOUR_TURN);
    }

    switch (callType) {
      case NOT_CALL_LANDLORD: // 不叫，不叫以后不能抢地主
        if (seat.getCallType() == null) {
          notCallLandlord(seat);
        }
        break;
      case CALL_LANDLORD: // 叫地主
        if (seat.getCallType() == null) {
          callLandlord(seat);
        }
        break;
      case NOT_GRAB_LANDLORD: // 不抢地主
        if (seat.getCallType() == null || (seat.getCallType() != DdzCallType.NOT_GRAB_LANDLORD
            && seat.getCallType() != DdzCallType.GRAB_LANDLORD)) {
          notGrabLandLord(seat);
        }
        break;
      case GRAB_LANDLORD: // 抢地主,不叫地主的玩家不能再抢地主
        if (seat.getCallType() == null || (seat.getCallType() != DdzCallType.NOT_GRAB_LANDLORD
            && seat.getCallType() != DdzCallType.GRAB_LANDLORD)) {
          grabLandlord(seat);
        }
        break;
      default:
        break;
    }
    return ResMessage.DEFAULT;
  }

  /**
   * schedule玩家叫牌 .
   * 
   * @param seat 座位信息.
   * @param preCallType . 上一个叫牌类型 .
   */
  private void scheduleCallCard(DdzAiSeat seat, DdzCallType preCallType) {
    int callTime = timeDic.get(DdzTime.CALL_DZ.val).getTime();

    DdzTable table = seat.table();
    DdzRoom room = table.room();
    LOG.info("[{}]房间[{}]桌子[{}]下一个叫牌玩家[{}][{}]", this.game.getDesc(), room.getId(), table.getId(),
        seat.getPlayerId(), seat.getPlayerName());

    // 取消定时
    stepFutureDisable(seat.stepFuture);

    // 开启新定时
    seat.stepFuture = scheduleMgr.schedule(() -> autoCallCard(seat, preCallType),
        seat.isSysHost() ? HOST_ACTION_SECONDS : callTime + PLAYER_ACTION_DELAY, TimeUnit.SECONDS,
        gameExecutor);
  }

  /**
   * 自动叫牌 .
   * 
   * @param seat 座位信息.
   * @param preCallType . 上一个叫牌类型 .
   */
  private void autoCallCard(DdzAiSeat seat, DdzCallType preCallType) {
    if (preCallType == null || preCallType == DdzCallType.NOT_CALL_LANDLORD) {
      if (seat.isRobot()) {
        if (seat.callLandlord()) {
          callLandlord(seat);
        } else {
          notCallLandlord(seat);
        }
      } else {
        notCallLandlord(seat);
      }
    } else {
      if (seat.isRobot()) {
        if (seat.grabLandlord()) {
          grabLandlord(seat);
        } else {
          notGrabLandLord(seat);
        }
      } else {
        notGrabLandLord(seat);
      }
    }
  }

  /**
   * 不叫地主 .
   * 
   * @param seat 座位信息.
   */
  private void notCallLandlord(DdzAiSeat seat) {
    stepFutureDisable(seat.stepFuture);
    if (seat.getCallType() == null) {
      seat.setCallType(DdzCallType.NOT_CALL_LANDLORD);
      seat.setLandlord(false);
    }
    // 叫牌后处理,主要是判断是否叫牌结束
    afterCallCard(seat);
  }

  /**
   * 叫地主 . 叫地主： 游戏开始后，系统随机选择一位玩家开始叫地主。 每位玩家最多只有1次叫地主机会，三位玩家都没叫地主，游戏自动结束，重新洗牌、发牌。积分只扣除平台使用费。
   * 叫地主之后，游戏倍数不变。 . 地主被叫之后，叫地主流程结束。进入抢地主流程。 .
   * 
   * @param seat 座位信息.
   */
  private void callLandlord(DdzAiSeat seat) {
    stepFutureDisable(seat.stepFuture);
    if (seat.getCallType() == null) {
      seat.setCallType(DdzCallType.CALL_LANDLORD);
      seat.setLandlord(true);
      DdzTable table = seat.getTable();
      // 倍数变更
      msgMgr.sendMultipleMsg(table);
    }
    // 叫牌后处理,主要是判断是否叫牌结束
    afterCallCard(seat);
  }

  /**
   * 不抢地主 .
   * 
   * @param seat 座位信息.
   */
  private void notGrabLandLord(DdzAiSeat seat) {
    stepFutureDisable(seat.stepFuture);
    if (seat.getCallType() == null || (seat.getCallType() != DdzCallType.NOT_GRAB_LANDLORD
        && seat.getCallType() != DdzCallType.GRAB_LANDLORD)) {
      seat.setCallType(DdzCallType.NOT_GRAB_LANDLORD);
      seat.setLandlord(false);
    }
    // 叫牌后处理,主要是判断是否叫牌结束
    afterCallCard(seat);
  }

  /**
   * 抢地主 . 抢地主： 有玩家叫地主之后，进入抢地主流程。每位玩家有且只有1次抢地主机会。最后一位选择抢地主的玩家成为地主。当有玩家抢地主后，倍数*2。
   * eg：游戏初始倍数为1，有人叫地主后，第一位玩家抢地主，倍数变为2倍，第二位玩家抢地主，倍数变为4倍，第三位玩家抢地主，倍数变为8倍。 .
   * 
   * @param seat 座位信息.
   */
  private void grabLandlord(DdzAiSeat seat) {
    DdzTable table = seat.table();
    stepFutureDisable(seat.stepFuture);
    if (seat.getCallType() == null || seat.getCallType() == DdzCallType.CALL_LANDLORD) {
      seat.setCallType(DdzCallType.GRAB_LANDLORD);
      DdzAiSeat landlordSeat = table.landlord();
      landlordSeat.setLandlord(false);
      seat.setLandlord(true);
      // 倍数 * 2 .
      table.setMultiple(table.getMultiple() * 2);
      // 倍数变更
      msgMgr.sendMultipleMsg(table);
    }
    // 叫牌后处理,主要是判断是否叫牌结束
    afterCallCard(seat);
  }

  /**
   * 叫牌后处理,主要是判断是否叫牌结束 .
   * 
   * @param seat 座位信息.
   */
  private void afterCallCard(DdzAiSeat seat) {
    DdzTable table = seat.table();

    // 下一个叫牌的seat
    DdzAiSeat nextCallSeat = table.nextCallCardSeat(seat);
    // 是否叫牌结束
    boolean callOver = nextCallSeat == null;

    if (callOver) { // 叫牌结束,地主确定后，如果地主是托管直接出牌
      // 叫牌发送叫牌结果
      msgMgr.sendCallCardMsg(seat, callOver);
      callCardOver(seat.table());
    } else {
      // 修改当前叫分的位置
      table.setNextCallSeat(nextCallSeat);
      // schedule玩家叫牌
      scheduleCallCard(nextCallSeat, seat.getCallType());
      // 叫牌发送叫牌结果
      msgMgr.sendCallCardMsg(seat, callOver);
    }
  }

  /**
   * 叫牌结束 .
   * 
   * @param table .
   */
  private void callCardOver(DdzTable table) {
    DdzAiSeat landlord = table.landlord();
    if (landlord == null) { // 没有一个人叫地主，重新发牌，再叫地主
      for (DdzAiSeat seat : table.gamingSeats()) {
        boolean sysHost = seat.isSysHost(); // 获取托管状态
        seat.reset(); // 重置
        seat.setState(SeatState.READY); // 游戏状态为准备
        seat.setSysHost(sysHost); // 恢复托管状态
      }
      // 发牌
      doDealCards(table);
    } else {
      table.setStatus(DdzStage.DOUBLE);
      LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
          table.getStatus().desc);

      table.setNextPlaySeat(landlord);
      table.setPrePlaySeat(landlord);
      // 将底牌放入自己的牌中
      landlord.getCards().addAll(table.hiddenCards());
      // 重新计算牌型
      landlord.calcCardsType();

      // seat如果是托管的话加倍？
      for (DdzAiSeat seat : table.gamingSeats()) {
        scheduleDouble(seat);
      }

      // 叫牌结束 发送底牌
      msgMgr.sendHiddenCardsMsg(table);

      LOG.info("[{}]牌桌[{}]叫牌结束,玩家[{}][{}]是地主", game.getDesc(), table.getId(),
          landlord.getPlayerId(), landlord.getPlayerName());
    }
  }

  /**
   * schedule玩家加倍 .
   * 
   * @param seat 座位信息.
   */
  private void scheduleDouble(DdzAiSeat seat) {
    stepFutureDisable(seat.stepFuture);
    if (seat.isSysHost()) { // 系统托管不加倍
      seat.stepFuture = scheduleMgr.schedule(() -> doDoubled(seat, false), HOST_ACTION_SECONDS,
          TimeUnit.SECONDS, gameExecutor);
    } else if (seat.isRobot()) { // 机器人加倍
      seat.stepFuture = scheduleMgr.schedule(() -> doDoubled(seat, seat.doDouble()),
          RandCodeUtil.random(1, ROBOT_ACTION_MAX_SECONDS), TimeUnit.SECONDS, gameExecutor);
    } else { // 普通玩家加倍
      int doubleTime = timeDic.get(DdzTime.CALL_MULTIPLE.val).getTime() + PLAYER_ACTION_DELAY;
      seat.stepFuture = scheduleMgr.schedule(() -> doDoubled(seat, false), doubleTime,
          TimeUnit.SECONDS, gameExecutor);
    }
  }

  /**
   * 玩家加倍 .
   * 
   * @param player 玩家.
   * @param doubled .
   */
  public ResMessage doubled(Player player, boolean doubled) {
    // 座位
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]还未进入牌桌不能加倍", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    doDoubled(seat, doubled);
    return ResMessage.DEFAULT;
  }
  
  public ResMessage showCardPlay(Player player, int type) {
    // 如果需要明牌功能，再实现
    return ResMessage.DEFAULT;
  }

  /**
   * 加倍 .
   * 
   * @param seat 座位信息.
   * @param doubled .
   */
  private void doDoubled(DdzAiSeat seat, boolean doubled) {
    stepFutureDisable(seat.stepFuture);
    seat.setDoubled(doubled);
    msgMgr.sendDoubleMsg(seat, doubled);

    if (doubled) {
      // 倍数变更
      DdzTable table = seat.getTable();
      // 倍数变更
      msgMgr.sendMultipleMsg(table);
    }
    DdzTable table = seat.table();
    if (table.doubledOver()) {
      table.setStatus(DdzStage.PLAY_CARD);
      LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
          table.getStatus().desc);

      schedulePlayCards(table.landlord(), true);

      // 发送叫庄结束通知
      msgMgr.sendDoubleOverMsg(table);
    }
  }
  
  /**
   * schedule玩家出牌 .
   * 
   * @param seat 座位信息.
   */
  private void schedulePlayCards(DdzAiSeat seat, boolean play) {
    stepFutureDisable(seat.stepFuture);
    DdzTable table = seat.table();
    if (!table.getNextPlaySeat().equals(seat)) {
      return;
    }
    // 真实玩家出牌时间限制
    int playTime = timeDic.get(DdzTime.PLAY_CARD.val).getTime() + PLAYER_ACTION_DELAY;

    LOG.info("[{}]房间[{}]桌子[{}]轮到玩家[{}][{}]出牌", this.game.getDesc(), table.room().getId(),
        table.getId(), seat.getPlayerId(), seat.getPlayerName());

    HuohuaAssert.isTrue(seat.getCards() != null && seat.getCards().size() > 0);
    seat.stepFuture = scheduleMgr.schedule(() -> autoPlayCards(seat, play),
        seat.isRobot() ? RandCodeUtil.random(1, ROBOT_ACTION_MAX_SECONDS)
            : (seat.isSysHost() ? HOST_ACTION_SECONDS : playTime),
        TimeUnit.SECONDS, gameExecutor);
  }

  /**
   * 自动出牌 .
   * 
   * @param seat 座位信息.
   * @param play 是否玩牌.
   */
  private void autoPlayCards(DdzAiSeat seat, boolean play) {
    DdzTable table = seat.table();
    // 上一次出牌也是我，即是第一手出牌或者出牌后其他人都不要
    if (table.getPrePlaySeat().equals(seat)) {
      if (seat.isRobot()) {
        List<DdzCard> playCards = seat.firstPlayCards();
        doPlayCards(seat, playCards, CardsTypeGetter.getCardsType(playCards));
      } else {
        List<DdzCard> playCards = CardsTypeFinder.findMinCards(seat.getCards());
        doPlayCards(seat, playCards, CardsTypeGetter.getCardsType(playCards));
      }
    } else {
      if (seat.isRobot()) { // 机器人出牌
        List<DdzCard> playCards = seat.playCards();
        // 找不到比上家更大的牌，放弃出牌
        if (playCards == null) {
          doAbandonPlayCards(seat);
        } else {
          doPlayCards(seat, playCards, CardsTypeGetter.getCardsType(playCards));
        }
      } else {
        // 托管时出牌，否则只有出牌的时候才出牌
        if (seat.isSysHost() || play) {
          List<DdzCard> playCards = seat.playCards();
          // 找不到比上家更大的牌，放弃出牌
          if (playCards == null) {
            doAbandonPlayCards(seat);
          } else {
            doPlayCards(seat, playCards, CardsTypeGetter.getCardsType(playCards));
          }
        } else {
          doAbandonPlayCards(seat);
        }
      }
    }
  }

  /**
   * @Title trustee.
   * @Description T托管切换
   * @author houdongsheng
   * @date 2018年8月22日 上午9:44:42
   * @param player .
   * @return .
   */
  public ResMessage trustee(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]未进入牌桌，不能托管", this.game.getDesc(), player.getId(),
          player.getPlayerName());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }
    DdzTable table = seat.getTable();
    if (table.getStatus().equals(DdzStage.NO_START) || table.getStatus().equals(DdzStage.READY)
        || table.getStatus().equals(DdzStage.GAME_OVER)) {
      LOG.error("[{}]玩家[{}][{}]游戏尚未开始，不能托管", this.game.getDesc(), seat.getPlayerId(),
          seat.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    if (!seat.getState().equals(SeatState.READY)) {
      LOG.error("[{}]玩家[{}][{}]未准备，不能托管", this.game.getDesc(), seat.getPlayerId(),
          seat.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    doTrustee(seat);
    return ResMessage.DEFAULT;
  }

  private void doTrustee(DdzAiSeat seat) {
    // 切换状态
    seat.setSysHost(seat.isSysHost() ? false : true);
    msgMgr.sendSysHostChangeMsg(seat);

    DdzTable table = seat.getTable();
    if (seat.isSysHost() && table.getStatus().equals(DdzStage.CALL_DZ)
        && table.getNextCallSeat().equals(seat)) {
      // 定时叫牌
      DdzAiSeat preSeat = table.preSeat(seat);
      scheduleCallCard(seat, preSeat.getCallType());
    } else if (seat.isSysHost() && table.getStatus().equals(DdzStage.DOUBLE)
        && seat.getDoubled() == null) {
      scheduleDouble(seat);
    } else if (seat.isSysHost() && table.getStatus().equals(DdzStage.PLAY_CARD)
        && table.getNextPlaySeat().equals(seat)) {
      schedulePlayCards(seat, true);
    }
  }

  /**
   * 玩家出牌 .
   * 
   * @param player 玩家.
   * @param cardIds .
   */
  public ResMessage playCards(Player player, List<Integer> cardIds) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]未进入牌桌，不能出牌", this.game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    DdzTable table = seat.table();
    if (!seat.equals(table.getNextPlaySeat())) {
      LOG.error("[{}]现在不该玩家[{}][{}]出牌", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_YOUR_TURN);
    }

    if (cardIds.size() == 0) {
      LOG.error("[{}]玩家[{}][{}]出的牌不能为空", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_YOUR_TURN);
    }

    if (cardIds.size() > 20) {
      LOG.error("[{}]玩家[{}][{}]出牌数量超过20张", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }

    // 将玩家出的牌按照数字排序
    TreeMultimap<Integer, DdzCard> sortedCards = TreeMultimap.create();
    for (Integer cardId : cardIds) {
      if (cardId < 0 || cardId >= DdzCard.values().length) {
        LOG.error("[{}]玩家[{}][{}]出的牌[{}]不存在", this.game.getDesc(), player.getPlayerName(),
            player.getId(), cardId);
        HuohuaAssert.isTrue(false);
      } else {
        DdzCard card = DdzCard.card(cardId);
        if (!seat.getCards().contains(card)) {
          LOG.error("[{}]玩家[{}][{}]手上没有改牌[{}]", this.game.getDesc(), player.getPlayerName(),
              player.getId(), cardId);
          HuohuaAssert.isTrue(false);
        }
        sortedCards.put(card.num, card);
      }
    }

    // 玩家所出的牌(已经按照牌的大小从小到大排列过)
    List<DdzCard> playSortedCards = new ArrayList<>(sortedCards.values());
    // 当前玩家的出牌类型
    DdzCardsType playCardsType = CardsTypeGetter.getCardsType(playSortedCards);
    if (playCardsType == null) {
      LOG.error("[{}]玩家[{}][{}]出的牌[{}]不符合规则", this.game.getDesc(), player.getPlayerName(),
          player.getId(), playSortedCards);
      HuohuaAssert.isTrue(false);
    }

    // 上一次玩家出的牌
    List<DdzCard> prePlayCards = table.getPrePlayCards();
    // 上一次玩家出的牌的类型
    DdzCardsType preCardsType = table.getPrePlayCardsType();

    // 玩家首个出牌
    if (prePlayCards == null) {
      doPlayCards(seat, playSortedCards, playCardsType);
    } else if (prePlayCards != null && table.getPrePlaySeat().equals(table.getNextPlaySeat())) {
      // 玩家出牌后别人不要
      doPlayCards(seat, playSortedCards, playCardsType);
    } else {
      // 牌的比较结果 (-2:出牌数量或牌型不匹配,-1：我小，0：相等，1：我大)
      int compareRes =
          CardsTypeComparator.compare(playSortedCards, playCardsType, prePlayCards, preCardsType);

      // 我的牌如果不是王炸或者炸弹那么数量必须和上一家出牌数量一致
      if (compareRes == -2) {
        LOG.error("[{}]玩家[{}][{}]出牌[{}]数量或牌型和上家[{}]不匹配", this.game.getDesc(),
            player.getPlayerName(), player.getId(), playSortedCards, prePlayCards);
        HuohuaAssert.isTrue(false);
      }

      // 我的牌没有上家大
      if (compareRes == -1 || compareRes == 0) {
        LOG.error("[{}]玩家[{}][{}]出牌[{}]没有上家[{}]大", this.game.getDesc(), player.getPlayerName(),
            player.getId(), playSortedCards, prePlayCards);
        HuohuaAssert.isTrue(false);
      }

      doPlayCards(seat, playSortedCards, playCardsType);
    }

    return ResMessage.DEFAULT;
  }

  /**
   * 玩家出牌 .
   * 
   * @param seat 座位信息.
   * @param playCards .
   * @param playCardsType .
   */
  private void doPlayCards(DdzAiSeat seat, List<DdzCard> playCards, DdzCardsType playCardsType) {
    stepFutureDisable(seat.stepFuture);
    DdzTable table = seat.table();
    table.setPrePlaySeat(seat);
    table.setPrePlayCards(playCards);
    table.setPrePlayCardsType(playCardsType);
    table.setNextPlaySeat(table.nextSeat(seat));
    seat.setPlayNum(seat.getPlayNum() + 1);
    seat.setPrePromptCards(null);
    seat.setPrePromptCardsType(null);
    seat.getCards().removeAll(playCards);

    // 重新计算牌型
    seat.calcCardsType();
    if (playCardsType == DdzCardsType.ZHA_DAN || playCardsType == DdzCardsType.WANG_ZHA) {
      // 倍数 * 2 .
      table.setMultiple(table.getMultiple() * 2);
      // 倍数变更
      msgMgr.sendMultipleMsg(table);
    }

    // 如果游戏已经结束,清空下一个出牌的玩家
    if (seat.getCards().size() == 0) {
      table.setNextPlaySeat(null);
    }

    if (seat.getCards() != null && seat.getCards().size() > 0) {
      schedulePlayCards(table.getNextPlaySeat(), false);

      msgMgr.sendPlayCardsMsg(table, seat, playCards, playCardsType);
      LOG.info("[{}]玩家[{}][{}]出牌[{}]", this.game.getDesc(), seat.getPlayerName(),
          seat.getPlayerId(), playCards.toString());
    } else {
      msgMgr.sendPlayCardsMsg(table, seat, playCards, playCardsType);
      LOG.info("[{}]玩家[{}][{}]出牌[{}]", this.game.getDesc(), seat.getPlayerName(),
          seat.getPlayerId(), playCards.toString());

      // 延时2秒游戏结束
      scheduleMgr.schedule(() -> {
        gameOver(table, seat);
      }, 1, TimeUnit.SECONDS, gameExecutor);
    }
  }

  /**
   * 玩家放弃出牌 .
   * 
   * @param player 玩家.
   */
  public ResMessage abandonPlayCards(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]未进入牌桌，不能出牌", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    DdzTable table = seat.table();
    if (!seat.equals(table.getNextPlaySeat())) {
      LOG.error("[{}]现在不该玩家[{}][{}]出牌不能放弃出牌", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_YOUR_TURN);
    }

    if (table.getPrePlaySeat().equals(table.getNextPlaySeat())) {
      LOG.error("[{}]玩家[{}][{}]头家出牌不能放弃", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }
    doAbandonPlayCards(seat);
    return ResMessage.DEFAULT;
  }

  /**
   * 放弃出牌 .
   * 
   * @param seat 座位信息.
   */
  private void doAbandonPlayCards(DdzAiSeat seat) {
    stepFutureDisable(seat.stepFuture);
    seat.setPrePromptCards(null);
    seat.setPrePromptCardsType(null);
    DdzTable table = seat.table();
    DdzAiSeat nextSeat = table.nextSeat(seat);
    table.setNextPlaySeat(nextSeat);

    // 下一个玩家操作
    schedulePlayCards(nextSeat, false);
    // 发送放弃出牌消息
    msgMgr.sendAbandonMsg(seat);
    LOG.info("[{}]玩家[{}][{}]放弃出牌", this.game.getDesc(), seat.getPlayerName(), seat.getPlayerId());

  }

  /**
   * 当前牌桌游戏结束,结算 .
   * 
   * @param table .
   * @param winner . 赢家 .
   */
  private void gameOver(DdzTable table, DdzAiSeat winner) {
    table.setStatus(DdzStage.GAME_OVER);
    table.setGaming(false);
    LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
        table.getStatus().desc);

    DdzAiSeat landlord = table.landlord();
    DdzAiSeat farmer1 = table.nextSeat(landlord);
    DdzAiSeat farmer2 = table.preSeat(landlord);
    boolean landlordWin = winner.isLandlord();

    /*
     * 农民积分变更=游戏底数*游戏倍数*农民倍数（选择加倍为2，不加倍为1）*地主倍数（选择加倍为2，不加倍为1）*输赢系数（输为-1，赢为1）。 .
     * 地主积分变更=（农民1积分变更+农民2积分变更）*-1。 . 游戏中出现炸弹、火箭、春天、反春天时，每出现一次，房间倍数*2。 春天：地主出牌后，在其他玩家未出牌时，地主将手牌出完。
     * 反春天：地主只出过一手牌，任一农民（允许相互配合）将手牌出光。 .
     */
    if (landlordWin && farmer1.getCards().size() == FARMER_CARDS
        && farmer2.getCards().size() == FARMER_CARDS) { // 春天
      // 倍数 * 2 .
      table.setMultiple(table.getMultiple() * 2);
      table.setSpring(1);
    } else if (!landlordWin && landlord.getPlayNum() == 1) { // 反春天
      // 倍数 * 2 .
      table.setMultiple(table.getMultiple() * 2);
      table.setSpring(-1);
    }

    long tableCedits = table.getBase() // 底分
        * table.getMultiple() // 倍数
        * (landlord.getDoubled() ? 2 : 1); // 是否加倍
    // 倍数变更
    msgMgr.sendMultipleMsg(table);

    long farmer1Cedits = tableCedits * (farmer1.getDoubled() ? 2 : 1) * (landlordWin ? -1 : 1);
    long farmer2Cedits = tableCedits * (farmer2.getDoubled() ? 2 : 1) * (landlordWin ? -1 : 1);
    long landlordCedits = -(farmer1Cedits + farmer2Cedits);

    // 如果地主赢，判断农名是否够输
    if (landlordWin) {
      if (farmer1.getTotalChips() + farmer1Cedits < 0) {
        farmer1Cedits = -farmer1.getTotalChips();
      }
      if (farmer2.getTotalChips() + farmer2Cedits < 0) {
        farmer2Cedits = -farmer2.getTotalChips();
      }
      landlordCedits = -(farmer1Cedits + farmer2Cedits);
    } else { // 如果地主输，判断地主是否够输
      double rate1 = (farmer1Cedits * 1.0) / (-landlordCedits);
      double rate2 = (farmer2Cedits * 1.0) / (-landlordCedits);

      if (landlord.getTotalChips() + landlordCedits < 0) {
        farmer1Cedits = (int) (rate1 * landlord.getTotalChips());
        farmer2Cedits = (int) (rate2 * landlord.getTotalChips());
        landlordCedits = -(farmer1Cedits + farmer2Cedits);
      }
    }

    // 结算筹码
    landlord.setBillChips(landlordCedits);
    farmer1.setBillChips(farmer1Cedits);
    farmer2.setBillChips(farmer2Cedits);

    doBill(farmer1);
    doBill(farmer2);
    doBill(landlord);

    LOG.info("[{}]玩家[{}][{}]出完所有牌,[{}]获胜,地主[{}][{}]积分[{}],农民[{}][{}]积分[{}],农民[{}][{}]积分[{}]",
        this.game.getDesc(), winner.getPlayerName(), winner.getPlayerId(),
        winner.isLandlord() ? "地主" : "农民", landlord.getPlayerName(), landlord.getPlayerId(),
        landlordCedits, farmer1.getPlayerName(), farmer1.getPlayerId(), farmer1Cedits,
        farmer2.getPlayerName(), farmer2.getPlayerId(), farmer2Cedits);

    int stageTime = timeDic.get(DdzTime.GAME_OVER.val).getTime();
    msgMgr.sendGameOverMsg(table, stageTime, landlord, landlordCedits, farmer1, farmer1Cedits,
        farmer2, farmer2Cedits);

    // 桌子定时取消
    stepFutureDisable(table.stepFuture);
    table.stepFuture = scheduleMgr.schedule(() -> {
      // 结束之后,判断房间是否关闭
      if (checkClose(table)) {
        dataMgr.checkRemoveInstance(table.getRoom().getId(), table.getRoom().getInstanceId());
        return;
      }
      
      DdzRoom room = table.getRoom();
      DdzRoomDomain roomDom = room.getRoomDomain();
      // 1: 离线玩家踢出
      clearLogoutPlayer(table);
      // 2: 金币不够踢出
      for (DdzAiSeat s : table.gamingSeats()) {
        Player player = playerMgr.getPlayer(s.getPlayerId());
        if ((player.getGold() + player.getSafeGold()) < roomDom.getLower()) {
          doExitTable(s);
          doExitRoom(player);
        }
      }
      // 进入准备阶段
      scheduleReady(table);
    }, stageTime, TimeUnit.SECONDS, gameExecutor);
  }

  /**
   * schedule桌子准备 .
   * 
   * @param table .
   */
  public void scheduleReady(DdzTable table) {
    // 重置数据,准备进入准备阶段
    table.reset();
    for (DdzAiSeat s : table.gamingSeats()) {
      s.reset();
    }

    table.setStatus(DdzStage.READY);
    LOG.info("[{}]房间[{}]桌子[{}]阶段[{}]", this.game.getDesc(), table.room().getId(), table.getId(),
        table.getStatus().desc);

    // 准备时间
    int readyTime = timeDic.get(DdzTime.READY.val).getTime();
    msgMgr.sendReadyStageMsg(table, readyTime);

    // 超时未准备，退出
    for (DdzAiSeat seat : table.gamingSeats()) {
      stepFutureDisable(seat.getStepFuture());
      ScheduledFuture<?> stepFuture = scheduleMgr.schedule(() -> {
        Player player = playerMgr.getPlayer(seat.getPlayerId());
        // 超时退出
        doExitTable(seat);
        doExitRoom(player);
      }, readyTime + PLAYER_ACTION_DELAY, TimeUnit.SECONDS, gameExecutor);
      seat.setStepFuture(stepFuture);
    }
  }

  private void doBill(DdzAiSeat seat) {
    long billChips = seat.getBillChips();
    long billGold = 0L;
    DdzRoomDomain roomDom = seat.getTable().getRoom().getRoomDomain();
    billGold = convertGold(roomDom, billChips);
    seat.setTotalChips(seat.getTotalChips() + seat.getBillChips());

    // 增加玩家金币
    Player player = playerMgr.getPlayer(seat.getPlayerId());
    PlayerBo playerDom = playerMgr.selectPlayer(seat.getPlayerId());
    if (player != null) {
      playerMgr.addGold(player, billGold, true, false, LogReason.DDZ_BILL);
    } else {
      playerMgr.addGold(playerDom, billGold, LogReason.DDZ_BILL);
    }

    DbLogService.log(new Game25WinLoseLog(playerDom, seat, seat.getBillChips(), billGold, LogReason.DDZ_BILL));
//    if (seat.isLandlord()) {
//      // 游戏金币记录
//      DbLogService.log(new GameGoldLog(playerDom, LogReason.DDZ_BILL, 0, billGold, ThirdGameType.DDZ, ThirdPlayerType.BANKER));
//    } else {
//      // 游戏金币记录
//      DbLogService.log(new GameGoldLog(playerDom, LogReason.DDZ_BILL, 0, billGold, ThirdGameType.DDZ, ThirdPlayerType.PLAYER));
//    }
    
    // 发送筹码变化
    msgMgr.sendChipsChangeMsg(seat);

    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));
  }

  /**
   * 玩家提示出牌 .
   * 
   * @param player 玩家.
   */
  public ResMessage prompt(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]未进入牌桌，不能提示", this.game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    DdzTable table = seat.table();
    if (!seat.equals(table.getNextPlaySeat())) {
      LOG.error("[{}]现在不该玩家[{}][{}]出牌，不能提示", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_YOUR_TURN);
    }
    if (table.getPrePlaySeat().equals(seat)) { // 玩家第一手出牌
      LOG.error("[{}]玩家[{}][{}]第一手出牌，不能提示", this.game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false);
    }
    // 本次提示应该出的牌
    List<DdzCard> promptCards = null;
    // 本次提示应该出的牌类
    DdzCardsType promptCardsType = null;

    if (seat.getPrePromptCards() == null) {
      promptCards = CardsTypeFinder.findBiggerCards(seat.getCards(), table.getPrePlayCards(),
          table.getPrePlayCardsType(), table.getPrePlayCards());
      if (promptCards == null) {
        doAbandonPlayCards(seat);
        return ResMessage.DEFAULT;
      }
    } else {
      promptCards = CardsTypeFinder.findBiggerCards(seat.getCards(), seat.getPrePromptCards(),
          seat.getPrePromptCardsType(), table.getPrePlayCards());
      if (promptCards == null) {
        promptCards = CardsTypeFinder.findBiggerCards(seat.getCards(), table.getPrePlayCards(),
            table.getPrePlayCardsType(), table.getPrePlayCards());
      }
    }

    if (promptCards != null) {
      promptCardsType = CardsTypeGetter.getCardsType(promptCards);
      msgMgr.sendPromptMsg(player, promptCards);
    }
    seat.setPrePromptCards(promptCards);
    seat.setPrePromptCardsType(promptCardsType);

    return ResMessage.DEFAULT;
  }
  
  /**
   * 玩家退出房间 .
   * 
   * @param player 玩家.
   */
  public void doExitRoom(Player player) {
    AbstractRoom room = player.curRoom;
    if (room instanceof DdzRoom) {
      player.curRoom = null;
      room.getPlayers().remove(player.getId());
      // 触发退出房间事件
      eventMgr.post(new ExitRoomEvent(player, room));
      msgMgr.sendExitRoom(player);
    } else {
      LOG.warn("[{}]玩家[{}][{}]不在斗地主房间中，无法退出房间", this.game.getDesc(), player.getPlayerName(),
          player.getId());
    }
  }

  public ResMessage resExitRoom(Player player) {
    doExitRoom(player);
    return ResMessage.DEFAULT;
  }

  /**
   * @Title exchangeTable.
   * @Description 换桌
   * @author houdongsheng
   * @date 2018年8月22日 下午4:13:58
   * @param player 玩家
   * @return .
   */
  public ResMessage exchangeTable(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]没在牌桌中不能换桌", this.game.getDesc(), player.getId(),
          player.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    DdzTable table = seat.getTable();
    if (seat.getState().equals(SeatState.GAMING) && !(table.getStatus().equals(DdzStage.NO_START)
        || table.getStatus().equals(DdzStage.READY)
        || table.getStatus().equals(DdzStage.GAME_OVER))) {
      LOG.error("[{}]玩家[{}][{}]游戏已经发牌，不能换桌", this.game.getDesc(), player.getId(),
          player.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    DdzRoom room = table.getRoom();
    DdzAiSeat emptySeat = findEmptySeat(player, room, table.getId());
    // 机器人换桌失败时,不抛出异常
    if (emptySeat == null) {
      if (player.isRobot()) {
        return ResMessage.DEFAULT;
      }
    }
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NO_EMPTY_SEAT, ", 不能换桌");

    DdzRoomDomain roomDom = table.getRoom().getRoomDomain();
    if ((player.getGold() + player.getSafeGold()) < roomDom.getLower()) {
      LOG.warn("[{}]玩家[{}][{}]进入的桌子[{}][{}]低于下限[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomDom.getId(), table.getId(), roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && (player.getGold() + player.getSafeGold()) > upper) {
      LOG.warn("[{}]玩家[{}][{}]进入的桌子[{}]高于上限[{}]金币", game.getDesc(), player.getPlayerName(),
          player.getId(), roomDom.getId(), table.getId(), roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (seat.isPlayedGame() && (player.getGold() + player.getSafeGold()) < roomDom.getAfee()) {
      LOG.warn("[{}]玩家[{}][{}]进入桌子金币不足台费", game.getDesc(), player.getPlayerName(),
          player.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_ENOUGH_FEE);
    }

    // 退出桌子
    doExitTable(seat);
    // 进入其他桌子
    doEnterTable(player, emptySeat, null);
    return ResMessage.DEFAULT;
  }

  /**
   * @Title exitTable.
   * @Description 退出桌子
   * @author houdongsheng
   * @date 2018年8月22日 上午9:45:51
   * @param player .
   * @return .
   */
  public ResMessage exitTable(Player player) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]没在牌桌中不能退出牌桌", this.game.getDesc(), player.getId(),
          player.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    DdzTable table = seat.getTable();
    if (seat.getState().equals(SeatState.GAMING) && !(table.getStatus().equals(DdzStage.NO_START)
        || table.getStatus().equals(DdzStage.READY)
        || table.getStatus().equals(DdzStage.GAME_OVER))) {
      LOG.error("[{}]玩家[{}][{}]游戏已经发牌，不能退出", this.game.getDesc(), player.getId(),
          player.getPlayerName());
      HuohuaAssert.isTrue(false);
    }
    // 退出桌子
    doExitTable(seat);
    return ResMessage.DEFAULT;
  }

  private void doExitTable(DdzAiSeat seat) {
    stepFutureDisable(seat.getStepFuture());
    long playerId = seat.getPlayerId();
    final Player player = playerMgr.getPlayer(playerId);
    DdzTable table = seat.getTable();
    
    // 如果尚未参加游戏，退还台费
    if (!seat.isPlayedGame()) {
      DdzRoomDomain roomDom = table.getRoom().getRoomDomain();
      if (roomDom.getAfee() > 0) {
        long beforeSafeGold = player.getSafeGold();
        long afterSafeGold = beforeSafeGold + roomDom.getAfee();
        
        while (!playerMgr.addSafeGold(player, beforeSafeGold, afterSafeGold,
            ThirdOrderUtils.generateThirdOrderNo(),
            LogReason.DDZ_RETURN_AFEE)) {
          beforeSafeGold = player.getSafeGold();
          afterSafeGold = beforeSafeGold + roomDom.getAfee();
        }

        boolean returnBack = true;
        DbLogService.log(new GameAfeeLog(player, table, returnBack, roomDom.getAfee()));
      }
    }
    
    if (seat.getTotalChips() > 0L) {
      long gold = player.getGold();
      
      playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
          LogReason.DDZ_EXIT_TABLE_EXCHANGE_GOLD);
      playerMgr.addGold(player, -gold, LogReason.DDZ_EXIT_TABLE_EXCHANGE_GOLD);
      
      seat.setTotalChips(0L);
    }

    // 发送退出房间消息
    msgMgr.sendExitTableMsg(seat);
    dataMgr.removePlayerSeat(seat.getPlayerId());
    LOG.info("[{}]玩家[{}][{}]退出桌子[{}][{}]", this.game.getDesc(), seat.getPlayerId(),
        seat.getPlayerName(), table.getRoom().getId(), seat.getTable().getId());

    seat.clear();
    player.curSeat = null;
    player.curTable = null;

    // 触发退出桌子事件
    eventMgr.post(new ExitTableEvent(player, seat));

    LOG.info("[{}]玩家[{}][{}]退出桌子[{}]", this.game.getDesc(), player.getId(), player.getUserName(),
        seat.table().getId());
  }

  /**
   * 玩家喊话 .
   * 
   * @param player 玩家.
   * @param type .
   */
  public ResMessage shout(Player player, int type) {
    DdzAiSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[{}]玩家[{}][{}]未进入牌桌，不能喊话", this.game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_SEAT_EMPTY);
    }

    msgMgr.sendShoutMs(seat, type);

    return ResMessage.DEFAULT;
  }

  /**
   * 清除玩家,服务器出现bug，玩家卡在游戏中，后台可以清除玩家.
   * 
   * @param playerId .
   */
  private void doClearGameData(long playerId) {
    try {

      dataMgr.removePlayerSeat(playerId);
      /*
       * 清除玩家房间数据
       */
      for (DdzRoom room : dataMgr.allRooms()) {
        room.getPlayers().remove(playerId);

        /*
         * 清除房间中玩家所在桌子数据
         */
        for (DdzTable table : room.getTables().values()) {

          // 是否在桌子中
          boolean inTable = false;
          for (DdzAiSeat s : table.getSeats()) {
            if (s.getPlayerId() > 0 && s.getState().equals(SeatState.GAMING)
                && s.getPlayerId() == playerId) {
              inTable = true;
              break;
            }
          }

          if (inTable) { // 在桌子中清除数据
            for (DdzAiSeat s : table.getSeats()) {
              if (s.getPlayerId() > 0 && s.getState().equals(SeatState.GAMING)) {
                dataMgr.removePlayerSeat(s.getPlayerId());
                room.getPlayers().remove(s.getPlayerId());

                /*
                 * 清除座位数据
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
      }

      Player player = playerMgr.getPlayer(playerId);
      if (player != null) {
        loginMgr.noticeLogoutForGameExit(player);
      }
    } catch (Exception e) {
      LOG.error("[炸金花]清除玩家[" + playerId + "]卡限失败", e);
    }
  }
}
