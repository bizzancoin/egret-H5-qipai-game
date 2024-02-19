package com.idealighter.game.core.service.games.baccarat.manager;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
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
import com.idealighter.game.core.service.gamehall.manager.GameHallMgr;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratRoom;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratSeat;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratTable;
import com.idealighter.game.core.service.games.baccarat.struct.BettingDecision;
import com.idealighter.game.core.service.games.baccarat.struct.GameStatus;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.game.Game18WinLoseLog;
import com.idealighter.game.core.service.log.struct.game.GameAfeeLog;
import com.idealighter.game.core.service.log.struct.resource.GameGoldLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.BaccaratTimeDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.baccarat.message.ResGameInfoMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.schedule.manager.ScheduleMgr;
import com.idealighter.game.server.core.executor.DisruptorExecutor;
import com.idealighter.game.server.event.ExecutorMgr;
import com.idealighter.game.third.constant.ThirdGameType;
import com.idealighter.game.third.constant.ThirdPlayerType;
import com.idealighter.game.third.utils.ThirdOrderUtils;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.idealighter.game.dblog.service.DbLogService;

/**
 * 百家乐逻辑处理 .
 */
@Singleton
public class BaccaratMgr {

  private static final Logger LOG = LoggerFactory.getLogger(BaccaratMgr.class);

  // 玩家手上的牌数
  public static final int PLAYER_CARDS = 2;
  private final EventMgr eventMgr;
  // 游戏线程executor,可以执行普通任务和定时任务
  private final DisruptorExecutor gameExecutor;
  private final BaccaratTimeDic timeDic;
  private final PlayerMgr playerMgr;
  private final ScheduleMgr scheduleMgr;
  private final BaccaratMsgMgr msgMgr;
  private final BaccaratDataMgr dataMgr;
  // private final BaccaratRoomDic roomDic;
  private final LoginMgr loginMgr;
  private final BaccaratControlScript controlScript;

  @Inject
  private CommonMsgMgr commonMsgMgr;

  // 定时服务器增加的延时，因为与网络延时等原因
  private static final int ADDED_DELAY = 1;

  private Vector<Long> logoutPlayers = new Vector<>();

  /**
   * 构造函数.
   * 
   * @param eventMgr 事件mgr.
   * @param executorMgr 执行mgr.
   * @param timeDic 时间dic.
   * @param playerMgr 玩家管理.
   * @param scheduleMgr 定时管理.
   * @param commonMsgMgr 普通消息.
   * @param msgMgr 消息管理.
   * @param dataMgr 数据管理.
   * @param gameHallMgr 游戏大厅管理.
   * @param loginMgr 登录管理.
   * @param controlScript 控制脚本.
   */
  @Inject
  public BaccaratMgr(EventMgr eventMgr, ExecutorMgr executorMgr, BaccaratTimeDic timeDic,
      PlayerMgr playerMgr, ScheduleMgr scheduleMgr, CommonMsgMgr commonMsgMgr,
      BaccaratMsgMgr msgMgr, BaccaratDataMgr dataMgr, GameHallMgr gameHallMgr, LoginMgr loginMgr,
      BaccaratControlScript controlScript) {
    this.playerMgr = playerMgr;
    this.scheduleMgr = scheduleMgr;
    this.msgMgr = msgMgr;
    this.dataMgr = dataMgr;
    this.loginMgr = loginMgr;
    this.eventMgr = eventMgr;
    this.timeDic = timeDic;
    this.controlScript = controlScript;
    eventMgr.register(this);
    this.gameExecutor = executorMgr.getGameExecutor(Game.BACCARAT.getModuleId());

    // 开始加载游戏

    gameStart();

  }

  /**
   * 清空离线玩家.
   */
  private void clearLogoutPlayer(BaccaratTable table) {
    if (logoutPlayers != null && !logoutPlayers.isEmpty()) {
      List<Long> clearIds = new ArrayList<Long>();
      logoutPlayers.forEach(playerId -> {
        LOG.info("[百家乐]清空离线玩家 {}", playerId);
        if (table.players().contains(playerId.longValue())) {

          Player player = playerMgr.getPlayer(playerId);

          BaccaratSeat seat = dataMgr.getPlayerSeat(playerId);
          if (seat != null && player != null) {
            doExitTable(seat, player);
          }

          // 玩家是否在房间中
          if (player != null && player.curRoom instanceof BaccaratRoom) {
            // 退出房间
            exitRoom(player);

            GameClearExitEvent gameClearExitEvent =
                new GameClearExitEvent(player, Game.BACCARAT.getDesc());
            eventMgr.post(gameClearExitEvent);
          }
          clearIds.add(playerId);
        }

      });
      if (EmptyUtil.listIsNotEmpty(clearIds)) {
        logoutPlayers.removeAll(clearIds);
        clearIds.clear();
      }
    }

  }

  /**
   * 订阅玩家退出游戏事件, 在gameExecutor中执行, 避免线程安全问题 .
   * 
   * @param event .
   */
  @Subscribe
  public void onPlayerExitGame(PlayerExitEvent event) {
    gameExecutor.execute(() -> {
      Player player = event.player;
      if (player.curRoom instanceof BaccaratRoom) {
        long playerId = player.getId();
        if (!logoutPlayers.contains(playerId)) {
          // 添加到退出列表
          logoutPlayers.add(playerId);
          LOG.info("[百家乐]玩家预约退出 {}", playerId);
        }
      }
    });
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
        // 清空用户
        logoutPlayers.remove(playerId);
        LOG.info("[百家乐]玩家取消退出 {}", playerId);
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
    LOG.info("百家乐开始退出.....");
    gameExecutor.execute(() -> {
      Collection<BaccaratRoom> rooms = dataMgr.allRooms();
      if (!EmptyUtil.isEmpty(rooms)) {
        for (BaccaratRoom room : rooms) {
          List<BaccaratRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
          deleteRooms(deleteRooms);
        }
      }
    });

    int times = 600;
    while (!BaccaratDataMgr.roomEmpty && times > 0) {
      try {
        Thread.sleep(500L);
        times--;
      } catch (InterruptedException exception) {
        LOG.error("关闭百家乐异常", exception);
      }
    }

    LOG.info("百家乐退出");
  }

  /**
   * 检验是否房间关闭，如果桌子不在游戏中或者游戏正在休息，顺便把桌子关闭 .
   *
   * @author abin
   * @date 2018年4月28日 下午3:41:20
   * @param table 桌子.
   */
  private boolean checkClose(BaccaratTable table) {
    boolean remove = false;
    if (table.getRoom().getInstanceState() == InstanceState.TO_REMOVE) {
      if (table.getStatus().equals(GameStatus.BILL)) {
        for (Long playerId : table.players()) {
          Player player = playerMgr.getPlayer(playerId);
          if (player != null) {
            kickoutForRoomClose(player);
          }
        }
        LOG.info("[百家乐]房间[{}] 实例[{}] 桌子[{}]阶段[{}] 删除  第{}局", table.getRoom().getId(),
            table.getRoom().getInstanceId(), table.getId(), table.getStatus().desc,
            table.getScore());
        BaccaratRoom room = table.getRoom();
        room.removeTable(table.getId());
        if (table != null && table.stepFuture != null) {
          table.stepFuture.cancel(false);
        }
        table = null;
        remove = true;
      }
    }
    return remove;
  }

  private void deleteRooms(List<BaccaratRoom> deleteRooms) {
    if (EmptyUtil.listIsNotEmpty(deleteRooms)) {
      for (Iterator<BaccaratRoom> iterator = deleteRooms.iterator(); iterator.hasNext();) {
        BaccaratRoom baccaratRoom = iterator.next();
        List<BaccaratTable> tables = new ArrayList<>(baccaratRoom.tables());
        for (Iterator<BaccaratTable> tableIterator = tables.iterator(); tableIterator.hasNext();) {
          BaccaratTable baccaratTable = tableIterator.next();
          checkClose(baccaratTable);
        }
        dataMgr.checkRemoveInstance(baccaratRoom.getId(), baccaratRoom.getInstanceId());
      }
    }
  }

  /**
   * 启动房间.
   * 
   * @param event .
   */
  @Subscribe
  public void onStartRoom(StartRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.BACCARAT) {
        List<BaccaratRoom> deleteRooms = dataMgr.startRoom(event.getRoomId(), true);
        deleteRooms(deleteRooms);

        // 启动table
        BaccaratRoom room = dataMgr.getNewestRoom(event.getRoomId());
        if (room != null) {
          for (BaccaratTable table : room.tables()) {
            doBet(table);
          }
        }
      }
    });
  }

  /**
   * 关闭房间.
   * 
   * @param event .
   */
  @Subscribe
  public void onShutDownRoom(ShutdownRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.BACCARAT) {
        List<BaccaratRoom> deleteRooms = dataMgr.deleteRoom(event.getRoomId());
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
      if (event.getGame() == Game.BACCARAT) {
        List<Integer> ids = dataMgr.reloadRoom();
        if (EmptyUtil.listIsNotEmpty(ids)) {
          for (Integer id : ids) {
            List<BaccaratRoom> deleteRooms = dataMgr.startRoom(id, false);
            deleteRooms(deleteRooms);

            // 启动table
            BaccaratRoom room = dataMgr.getNewestRoom(id);
            if (room != null) {
              for (BaccaratTable table : room.tables()) {
                doBet(table);
              }
            }
          }
        }
      }
    });
  }

  /**
   * 关闭游戏 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:24:51
   * @param event 关闭游戏事件.
   */
  @Subscribe
  public void onShutdownGame(ShutdownGameEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.BACCARAT) {
        Collection<BaccaratRoom> rooms = dataMgr.allRooms();
        if (!EmptyUtil.isEmpty(rooms)) {
          for (BaccaratRoom room : rooms) {
            List<BaccaratRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
            deleteRooms(deleteRooms);
          }
        }

      }
    });
  }

  /**
   * 清空游戏数据 .
   *
   * @author abin
   * @date 2018年4月19日 下午8:20:51
   * @param event 清空数据事件.
   */
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
      LOG.warn("[百家乐]玩家[{}][{}]在[{}]游戏中，暂时无法进入游戏", player.getId(), player.getPlayerName(),
          game.getDesc());

      HuohuaAssert.isTrue(player.curRoom == null, ErrorCode.GAME_ALREADY_IN,
          player.curRoom.game().getDesc());
    }

    LOG.info("[百家乐]玩家[{}][{}]进入大厅成功", player.getPlayerName(), player.getId());

    return msgMgr.resEnterGameHallMsg(player);
  }

  /**
   * 玩家进入房间 .
   * 
   * @param player 玩家.
   * @param roomId .
   */
  public ResMessage enterRoom(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;

    HuohuaAssert.isTrue(curRoom == null, ErrorCode.GAME_ALREADY_IN);

    BaccaratRoom room = dataMgr.getNewestRoom(roomId);

    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    BaccaratRoomDomain roomDom = room.getRoomDomain();

    HuohuaAssert.isTrue(roomDom.getIsActive() == RoomActiveConstant.ACTIVE);

    HuohuaAssert.isTrue(player.getSafeGold() >= roomDom.getLower(), ErrorCode.GAME_BELOW_MIN_GOLD);

    HuohuaAssert.isTrue(player.getSafeGold() <= roomDom.getUpper(), ErrorCode.GAME_BEYOND_MAX_GOLD);

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    HuohuaAssert.isTrue(room.getPlayers().size() < roomDom.getMaxNum(), ErrorCode.GAME_ROOM_FULL);

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    LOG.info("[百家乐]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    // 发送进入房间消息
    return msgMgr.resEnterRoomMsg(player);
  }



  /**
   * 快速进入.
   *
   * @author abin
   * @date 2018年9月10日 下午5:33:30
   * @param player 玩家.
   * @param roomId 房间id.
   * @return 返回message.
   */
  public ResMessage fastEnter(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;

    HuohuaAssert.isTrue(curRoom == null, ErrorCode.GAME_ALREADY_IN);

    BaccaratRoom room = dataMgr.getNewestRoom(roomId);

    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    BaccaratRoomDomain roomDom = room.getRoomDomain();

    HuohuaAssert.isTrue(roomDom.getIsActive() == RoomActiveConstant.ACTIVE);

    HuohuaAssert.isTrue(player.getSafeGold() >= roomDom.getLower(), ErrorCode.GAME_BELOW_MIN_GOLD);

    HuohuaAssert.isTrue(player.getSafeGold() <= roomDom.getUpper(), ErrorCode.GAME_BEYOND_MAX_GOLD);

    if (!player.vip()
        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }

    HuohuaAssert.isTrue(room.getPlayers().size() < roomDom.getMaxNum(), ErrorCode.GAME_ROOM_FULL);

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());

    LOG.info("[百家乐]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));


    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat == null);

    seat = findEmptySeat(player, room);

    HuohuaAssert.isTrue(seat != null && seat.getPlayerId() <= 0, ErrorCode.GAME_NOT_SIT);


    doEnterTable(player, seat);

    List<MemInfo> memInfos = getTableMemInfos(seat.getTable());

    // 发送快速进入桌子结果
    return msgMgr.resFastEnterMsg(memInfos);
  }

  /**
   * 玩家金币兑换筹码 .
   * 
   * @param player 玩家.
   * @param gold 兑换筹码的金币 .
   */
  public ResMessage exchangeChips(Player player, long gold) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    // 用户金币大于兑换金币就可以
    HuohuaAssert.isTrue(player.getSafeGold() >= gold);

    long chips = convertChip(seat.getTable().getRoom().getRoomDomain(), gold);

    seat.setTotalChips(seat.getTotalChips() + chips);

    playerMgr.addSafeGold(player, -gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.BACCARAT_EXCHANGE_CHIP);

    playerMgr.addGold(player, gold, LogReason.BACCARAT_EXCHANGE_CHIP);

    // playerMgr.addAllGold(player, -gold, gold, LogReason.BACCARAT_EXCHANGE_CHIP);

    // 发送筹码变化消息
    msgMgr.noticeChipsChangeMsg(seat);

    LOG.info("[百家乐]玩家[{}][{}]用金币[{}]兑换筹码[{}]成功", player.getPlayerName(), player.getId(), gold,
        chips);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  /**
   * 玩家筹码兑换金币.
   * 
   * @param player 玩家.
   * @param chips 筹码.
   */
  public ResMessage exchangeGold(Player player, long chips) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(chips <= seat.getTotalChips());

    BaccaratTable table = seat.getTable();

    HuohuaAssert.isTrue(table.getStatus() == GameStatus.BET, ErrorCode.GAME_NOT_ALLOW_EXCHANGE);

    seat.setTotalChips(seat.getTotalChips() - chips);
    long gold = convertGold(seat.getTable().getRoom().getRoomDomain(), chips);

    playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.BACCARAT_EXCHANGE_GOLD);
    playerMgr.addGold(player, -gold, LogReason.BACCARAT_EXCHANGE_GOLD);

    // playerMgr.addAllGold(player, gold, -gold, LogReason.BACCARAT_EXCHANGE_GOLD);
    // 发送兑换筹码变更
    msgMgr.noticeChipsChangeMsg(seat);

    LOG.info("[百家乐]玩家[{}][{}]用筹码[{}]兑换金币[{}]成功", player.getPlayerName(), player.getId(), chips,
        gold);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  private void doExitRoom(Player player) {
    AbstractRoom curRoom = player.curRoom;
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(curRoom instanceof BaccaratRoom);

    HuohuaAssert.isTrue(seat == null, ErrorCode.GAME_EXIT_ROOM_EXIT_SIT_FIRST);

    player.curRoom = null;
    BaccaratRoom room = (BaccaratRoom) curRoom;
    room.getPlayers().remove(player.getId());


    LOG.info("[百家乐]玩家[{}][{}]退出房间[{}]", player.getPlayerName(), player.getId(), room.getId());
    // 触发退出房间事件
    eventMgr.post(new ExitRoomEvent(player, curRoom));

  }

  /**
   * 房间关闭踢出-通知 .
   *
   * @author abin
   * @date 2018年4月28日 下午5:08:21
   * @param player 玩家.
   */
  private void kickoutForRoomClose(Player player) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      doExitTable(seat, player);
    }
    if (player.curRoom != null) {
      doExitRoom(player);
      commonMsgMgr.noticeRoomCloseKickout(player);
    }
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

  /**
   * 筹码换算金币,只会是多个金币等于一个筹码 .
   * 
   * @param roomDomain .
   * @param chips 筹码.
   */
  public long convertGold(BaccaratRoomDomain roomDomain, long chips) {
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
  public long convertChip(BaccaratRoomDomain roomDomain, long gold) {
    return gold * roomDomain.getProportionChips() / roomDomain.getProportionGold()
        / Player.PRECISION;
  }

  /**
   * 获取房间成员信息 .
   *
   * @author abin
   * @date 2018年4月8日 下午4:29:07
   * @param table 桌子信息.
   * @return 成员信息.
   */
  private List<MemInfo> getTableMemInfos(BaccaratTable table) {
    // 桌子玩家列表
    AbstractRoom room = table.room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : table.players()) {
      mems.add(room.memInfo(playerId));
    }

    return mems;
  }

  /**
   * 玩家进入牌桌 .
   * 
   * @param player 玩家.
   * @param tableId 房间id.
   */
  public ResMessage enterTable(Player player, int tableId) {
    HuohuaAssert.isTrue(player.curRoom instanceof BaccaratRoom);

    BaccaratTable table = dataMgr.getTable(tableId);

    HuohuaAssert.isTrue(table != null, ErrorCode.GAME_ROOM_CLOSED);

    HuohuaAssert.isTrue(dataMgr.getPlayerSeat(player.getId()) == null);

    BaccaratSeat seat = findEmptySeat(player, table);

    HuohuaAssert.isTrue(seat != null && seat.getPlayerId() <= 0, ErrorCode.GAME_NOT_SIT);


    doEnterTable(player, seat);

    List<MemInfo> memInfos = getTableMemInfos(seat.getTable());

    // 进入桌子结果
    return msgMgr.resEnterTableMsg(memInfos);

  }

  /**
   * 快速进入四人牛牛 .
   * 
   * @param player 玩家.
   */
  public ResMessage fastEnterTable(Player player) {
    HuohuaAssert.isTrue(player.curRoom instanceof BaccaratRoom);

    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat == null);

    BaccaratRoom room = (BaccaratRoom) player.curRoom;
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    seat = findEmptySeat(player, room);

    HuohuaAssert.isTrue(seat != null && seat.getPlayerId() <= 0, ErrorCode.GAME_NOT_SIT);


    doEnterTable(player, seat);

    List<MemInfo> memInfos = getTableMemInfos(seat.getTable());

    // 发送快速进入桌子结果
    return msgMgr.resFastEnterTableMsg(memInfos);
  }

  /**
   * 玩家进入座位 .
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   */
  private void doEnterTable(Player player, BaccaratSeat seat) {

    BaccaratTable table = seat.getTable();
    BaccaratRoomDomain roomDom = table.getRoom().getRoomDomain();

    HuohuaAssert.isTrue(player.getSafeGold() >= roomDom.getLower(),
        ErrorCode.PLAYER_GOLD_NOT_ENOUGH);

    HuohuaAssert.isTrue(player.getSafeGold() <= roomDom.getUpper(),
        ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);

    // 台费(金币)
    int afee = roomDom.getAfee();

    HuohuaAssert.isTrue(player.getSafeGold() >= afee, ErrorCode.GAME_NOT_ENOUGH_FEE);

    if (afee > 0) {
      long beforeSafeGold = player.getSafeGold();
      HuohuaAssert.isTrue(beforeSafeGold >= roomDom.getAfee(), ErrorCode.GAME_NOT_ENOUGH_FEE);
      long afterSafeGold = beforeSafeGold - roomDom.getAfee();

      while (!playerMgr.addSafeGold(player, beforeSafeGold, afterSafeGold,
          ThirdOrderUtils.generateThirdOrderNo(), LogReason.BACCARAT_ENTER_TABLE_MINUS_AFEE)) {
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

    // 改成补充筹码 初始化筹码为金币数

    // seat.setTotalChips(convertChip(roomDom, player.getGold()));

    // 更新玩家座位
    dataMgr.updatePlayerSeats(player.getId(), seat);
    // 触发进入桌子事件
    eventMgr.post(new EnterTableEvent(player, seat));

    // 给其他人发送进入桌子信息.
    msgMgr.noticeOtherEnterTableMsg(seat);

    LOG.info("[百家乐]玩家[{}][{}]进入桌子[{}]座位[{}]成功", player.getPlayerName(), player.getId(),
        table.getId(), seat.getOrder());

    player.curSeat = seat;


  }

  /**
   * 获取当前游戏信息.
   *
   * @author abin
   * @date 2018年4月19日 下午4:53:14
   * @param player 玩家.
   * @return 游戏信息.
   */
  public ResMessage getGameInfo(Player player) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());
    // 校验参数

    ResMessage msg = null;
    if (player.curRoom instanceof BaccaratRoom && player.curSeat != null && seat != null) {
      // 同步筹码 需要同步
      // seat.setTotalChips(convertChip(seat.getTable().getRoom().getRoomDomain(),
      // player.getGold()));

      GameStatus gameStatus = seat.getTable().getStatus();
      msgMgr.noticeHistory(seat);
      // 已经结算了，就不发
      if (!gameStatus.equals(GameStatus.BILL)) {
        msgMgr.noticeTableBetInfosMsg(seat);
      }

      int time = (int) seat.getTable().stepFuture.getDelay(TimeUnit.SECONDS);
      // if (time < 0) {
      // LOG.info("猜测没作用的代码----------");
      // doBet(seat.table);
      // time = timeDic.get(GameStatus.BET.val).getTime();
      // }
      // msgMgr.sendApplyBankersMsg(seat);
      // 通知统计信息
      msgMgr.noticeStatisticsInfoMsg(seat);
      // 通知游戏倒计时
      if (time - ADDED_DELAY > 0) {
        time = time - ADDED_DELAY;
        LOG.info("[百家乐]房间[{}] 实例[{}] 桌子[{}] 阶段[{}] 还剩下{} 秒  第{}局 ",
            seat.getTable().getRoom().getId(), seat.getTable().getRoom().getInstanceId(),
            seat.getTable().getId(), seat.getTable().getStatus().desc, time,
            seat.getTable().getScore());
        if (!player.isRobot()) {
          msgMgr.noticeGameStage(seat, time);
          if (gameStatus.equals(GameStatus.REWARD)) {
            msgMgr.noticeDealCardsMsgToPlayer(player, seat.getTable());
            msgMgr.noticeRewardResultToPlayer(player, seat.getTable());
            // 我的结算信息
            msgMgr.noticeBill(seat);
            // 通知筹码变化
            msgMgr.noticeChipsChangeMsg(seat);
          }
        }
      }

      // 通知赔率
      msgMgr.noticeIconMulti(seat);

      // 进入座位
      if (!player.isRobot()) {
        List<MemInfo> memInfos = getTableMemInfos(seat.getTable());
        msg = msgMgr.resGameInfo(seat.getTable().getRoom().getInstanceId(), memInfos,
            seat.getBets(), seat.getTable().getCardIndex());
      }
      LOG.info("玩家{}还在游戏中", player.getId());
    } else {
      msg = new ResGameInfoMsg();
      LOG.info("玩家{}不在游戏中", player.getId());
      player.curRoom = null;
      player.curTable = null;
      player.curSeat = null;
    }

    return msg;
  }

  /**
   * 玩家退出牌桌 .
   * 
   * @param player 玩家.
   */
  public ResMessage exitTable(Player player) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    // 退出桌子
    doExitTable(seat, player);

    // 发送退出房间消息
    return ResMessage.DEFAULT;
  }

  /**
   * 退出桌子 .
   * <p/>
   * 1.玩家正常退出牌桌, . 能在在线玩家中拿到player, 正常退出桌子
   * <p/>
   * 2.玩家非正常退出(如掉线)后,从活跃玩家或数据库中拿到PlayerDomain，结算后马上更新缓冲和数据库 .
   * 
   * @param seat 座位信息.
   * @param player 玩家.
   */
  private void doExitTable(BaccaratSeat seat, Player player) {
    // long playerId = seat.playerId;
    // Player player = playerMgr.getPlayer(playerId);
    BaccaratTable table = seat.getTable();

    // 发送退出房间消息
    if (table.getStatus() == GameStatus.REWARD) { // 开奖过程中退出，立刻更新玩家金币
      playerMgr.noticeGold(playerMgr.getPlayer(seat.getPlayerId()));
    } else if (table.getStatus() == GameStatus.BET) { // 下注退,退还下注金币
      playerMgr.addGold(player, convertGold(table.getRoom().getRoomDomain(), seat.totalBets()),
          LogReason.BACCARAT_RETURN_BET);
    }

    if (seat.getTotalChips() > 0) {
      long gold = player.getGold();

      playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
          LogReason.BACCARAT_EXIT_TABLE_EXCHANGE_GOLD);
      playerMgr.addGold(player, -gold, LogReason.BACCARAT_EXIT_TABLE_EXCHANGE_GOLD);

      // playerMgr.addAllGold(player, gold, -gold, LogReason.BACCARAT_EXIT_TABLE_EXCHANGE_GOLD);
      seat.setTotalChips(0L);
    }

    msgMgr.noticePlayerExitTableMsg(seat);

    dataMgr.removePlayerSeat(seat.getPlayerId());

    player.curSeat = null;

    player.curTable = null;

    LOG.info("[百家乐]玩家[{}][{}]退出桌子[{}]", seat.getPlayerId(), seat.getPlayerName(),
        seat.getTable().getId());
    seat.clear();
    if (table.getStatus() == GameStatus.BET) { // 下注阶段退同步一次筹码
      msgMgr.noticeTableBetInfosMsg(table);
    }

    // 触发退出桌子事件
    eventMgr.post(new ExitTableEvent(player, seat));
  }

  /**
   * 开始游戏 .
   */

  private void gameStart() {
    for (BaccaratRoom room : dataMgr.newestRooms()) {
      for (BaccaratTable table : room.tables()) {
        doBet(table);
      }
    }
  }

  /**
   * 休息阶段 .
   * 
   * @param table .
   */

  private void doRest(BaccaratTable table) {
    // table.status = GameStatus.REST;
    // int time = timeDic.get(table.status.val).getTime();
    // bankerNotice(table);
    for (BaccaratSeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0) {
        playerMgr.noticeGold(playerMgr.getPlayer(seat.getPlayerId()));
      }
      seat.resetGameData();
    }
    // msgMgr.sendGameStage(table, time);
    table.reset();
    // table.stepFuture = scheduleMgr.schedule(() -> doBet(table), time,
    // TimeUnit.SECONDS, gameExecutor);
    // LOG.info("[百家乐]房间[{}]桌子[{}]阶段[{}]", table.room.id, table.id,
    // table.status.desc);


  }

  /**
   * 庄家通吃通赔公告 .
   * 
   * 
   * @param table .
   */
  @SuppressWarnings("unused")
  private void bankerNotice(BaccaratTable table) {
    // BaccaratSeat bankerSeat = table.banker();
    // int count = 0;
    // for (BaccaratSeat sysSeat : table.sysSeats) {
    // if (sysSeat.order == -4) {
    // continue;
    // }
    // if (sysSeat.win)
    // count++;
    // }
    // if (count == 0 || count == 4) {
    // msgMgr.sendBankerNotice(bankerSeat.playerName, table.room.name,
    // count, bankerSeat.billChips);
    // }
    // bankerSeat.resetGameData();
  }

  /**
   * 下注阶段 .
   * 
   * @param table .
   */
  private void doBet(BaccaratTable table) {
    if (checkClose(table)) {
      dataMgr.checkRemoveInstance(table.getRoom().getId(), table.getRoom().getInstanceId());
      return;
    }
    doRest(table);
    table.setStatus(GameStatus.BET);
    int time = timeDic.get(table.getStatus().val).getTime();
    msgMgr.noticeGameStage(table, time);
    msgMgr.noticeIconMulti(table);
    table.stepFuture = scheduleMgr.schedule(() -> doReward(table), time + ADDED_DELAY,
        TimeUnit.SECONDS, gameExecutor);
    LOG.info("[百家乐] 房间[{}] 实例[{}] 桌子[{}] 阶段[{}] 下注 第{}局", table.getRoom().getId(),
        table.getRoom().getInstanceId(), table.getId(), table.getStatus().desc, table.getScore());
  }

  /**
   * 开奖阶段 .
   * 
   * @param table .
   */
  private void doReward(BaccaratTable table) {
    table.setStatus(GameStatus.REWARD);
    int time = timeDic.get(table.getStatus().val).getTime();
    msgMgr.noticeGameStage(table, time);
    // controlScript.dealCards(table);
    controlScript.controlDealCards(table);
    table.deduceWinner();
    table.balance();
    table.balanceTable();
    table.addHistory();
    controlScript.balanceControl(table);
    msgMgr.noticeDealCardsMsg(table);
    msgMgr.noticeRewardResult(table);
    // table.addHistory();
    table.setLeftNum(table.countLeftNum());
    msgMgr.noticeHistory(table);
    for (BaccaratSeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0) {
        doPlayerBill(seat);
      }
    }
    // dobalance(table, time);
    table.stepFuture = scheduleMgr.schedule(() -> doBill(table), time + ADDED_DELAY,
        TimeUnit.SECONDS, gameExecutor);
    LOG.info("[百家乐] 房间[{}] 实例[{}] 桌子[{}] 阶段[{}] 发牌  第{}局", table.getRoom().getId(),
        table.getRoom().getInstanceId(), table.getId(), table.getStatus().desc, table.getScore());
  }

  /**
   * 结算阶段 .
   * 
   * @param table .
   */
  private void doBill(BaccaratTable table) {
    table.setStatus(GameStatus.BILL);
    int time = timeDic.get(table.getStatus().val).getTime();
    msgMgr.noticeGameStage(table, time);

    msgMgr.noticeStatisticsInfoMsg(table);

    // 清空预约退出玩家.
    clearLogoutPlayer(table);

    if (table.isWashCard()) {
      table.stepFuture =
          scheduleMgr.schedule(() -> doWashCard(table), time, TimeUnit.SECONDS, gameExecutor);
    } else {
      table.stepFuture =
          scheduleMgr.schedule(() -> doBet(table), time, TimeUnit.SECONDS, gameExecutor);
    }

    LOG.info("[百家乐]房间[{}] 实例[{}] 桌子[{}] 阶段[{}] 结算 第{}局", table.getRoom().getId(),
        table.getRoom().getInstanceId(), table.getId(), table.getStatus().desc, table.getScore());

  }

  /**
   * 洗牌阶段 .
   * 
   * @param table .
   */
  private void doWashCard(BaccaratTable table) {
    table.setStatus(GameStatus.WASHCARD);
    int time = timeDic.get(table.getStatus().val).getTime();
    table.washCardRest();
    msgMgr.noticeStatisticsInfoMsg(table);
    msgMgr.noticeGameStage(table, time);
    msgMgr.noticeWashCard(table);
    table.stepFuture =
        scheduleMgr.schedule(() -> doBet(table), time, TimeUnit.SECONDS, gameExecutor);

    LOG.info("[百家乐] 房间[{}] 实例[{}] 桌子[{}] 阶段[{}]", table.getRoom().getId(),
        table.getRoom().getInstanceId(), table.getId(), table.getStatus().desc);
  }

  /**
   * 查找空位置, 优先查找有人的桌子 .
   * 
   * @param player 玩家.
   * @param room .
   * @return
   */
  private BaccaratSeat findEmptySeat(Player player, BaccaratRoom room) {
    BaccaratSeat emptySeat = null;
    ArrayList<BaccaratTable> tables = new ArrayList<>(room.getTables().values());
    Collections.shuffle(tables);

    boolean robot = player.isRobot();
    for (BaccaratTable table : tables) {
      if (table.getPassword() != null) {
        continue;
      }
      if (table.getLimitIp() != null && table.getLimitIp().equals(player.ip)) {
        continue;
      }

      if (player.getSafeGold() < table.getLimitGold()) {
        continue;
      }
      if (robot && table.robotFull()) {
        continue;
      }

      boolean hasPlayer = false;
      BaccaratSeat tableEmptySeat = null;

      for (BaccaratSeat seat : table.getSeats()) {
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

  private BaccaratSeat findEmptySeat(Player player, BaccaratTable table) {
    BaccaratSeat emptySeat = null;

    boolean robot = player.isRobot();
    if (robot && table.robotFull()) {
      return emptySeat;
    }

    for (BaccaratSeat seat : table.getSeats()) {
      if (seat.getPlayerId() == 0) {
        emptySeat = seat;
        break;
      }
    }

    return emptySeat;
  }

  /**
   * 玩家下注 .
   * 
   * @param player 玩家.
   * @param area .
   * @param chips .
   */
  public ResMessage playerBet(Player player, int area, long chips) {
    HuohuaAssert.isTrue(area >= 0 && area <= 11 && chips > 0);

    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(seat.getTable().getStatus() == GameStatus.BET);

    HuohuaAssert.isTrue(chips <= seat.getTotalChips());

    // 在31局以后不能压注大小
    HuohuaAssert.isTrue(seat.getTable().getScore() < 30
        || area != BettingDecision.BIG.type && area != BettingDecision.SMALL.type);

    long areaBet = seat.getBets().getOrDefault(area, 0L) + chips;
    seat.getBets().put(area, areaBet);
    seat.setTotalChips(seat.getTotalChips() - chips);

    msgMgr.noticeChipsChangeMsg(seat);

    playerMgr.addGold(seat.getPlayerId(),
        convertGold(seat.getTable().getRoom().getRoomDomain(), -chips), LogReason.BACCARAT_BET);

    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    // 同步桌子下注筹码消息
    msgMgr.noticeTableBetInfosMsgExcludeSelf(seat);

    LOG.info("[百家乐]玩家[{}][{}]下注区域[{}][{}]成功", player.getPlayerName(), player.getId(), area, chips);

    // 发送下注成功
    return msgMgr.resBetMsg(area, chips);
  }

  /**
   * 清除下注 .
   * 
   * @param player 玩家.
   */
  public ResMessage clearBet(Player player) {
    BaccaratSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(seat != null);

    BaccaratTable table = seat.getTable();

    HuohuaAssert.isTrue(table.getStatus() == GameStatus.BET);

    long chips = seat.totalBets();
    seat.getBets().clear();
    seat.setTotalChips(seat.getTotalChips() + chips);

    msgMgr.noticeChipsChangeMsg(seat);

    playerMgr.addGold(player.getId(), convertGold(table.getRoom().getRoomDomain(), chips),
        LogReason.BACCARAT_CLEAR);

    msgMgr.noticeTableBetInfosMsg(seat.getTable());

    LOG.info("[百家乐]玩家[{}][{}]清除下注成功", player.getPlayerName(), player.getId());

    return ResMessage.DEFAULT;
  }

  /**
   * 座位结算 .
   * 
   * @param seat 座位信息.
   */
  private void doPlayerBill(BaccaratSeat seat) {
    long playerId = seat.getPlayerId();
    BaccaratRoomDomain roomDomain = seat.getTable().getRoom().getRoomDomain();
    // 扣除输赢
    seat.setTotalChips(seat.getTotalChips() + seat.getBillChips());
    long billGold = convertGold(roomDomain, seat.getBillChips());

    /*
     * 游戏结算金币 .
     */
    Player player = playerMgr.getPlayer(playerId);
    PlayerBo playerDom = playerMgr.selectPlayer(playerId);
    if (player != null) {
      // playerMgr.addGold(player, billGold, false,
      // LogReason.BACCARAT_BILL);
      playerMgr.addGold(player, billGold, true, false, LogReason.BACCARAT_BILL);
    } else {
      playerMgr.addGold(playerDom, billGold, LogReason.BACCARAT_BILL);
    }

    long betting = convertGold(roomDomain, seat.totalBets());

    long bonus = convertGold(roomDomain, seat.getBillChips());

    if (betting != 0 || bonus != 0) {
      DbLogService.log(new GameGoldLog(playerDom, LogReason.BACCARAT_BILL, betting, bonus,
          ThirdGameType.BAI_JIA_LE, ThirdPlayerType.PLAYER));
    }



    // 最终结果输赢筹码
    long resChips = seat.getBillChips() - seat.totalBets();
    long resGold = convertGold(roomDomain, resChips);

    LOG.info("[百家乐]玩家[{}][{}] 总下注 {} 总赢分 {} 最终输赢[{}]",
        player != null ? player.getPlayerName() : playerDom.getPlayerName(),
        player != null ? player.getId() : playerDom.getId(), seat.totalBets(), seat.getBillChips(),
        resChips);

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService
          .log(new Game18WinLoseLog(playerDom, seat, resChips, resGold, LogReason.BACCARAT_BILL));

    }

    msgMgr.noticeBill(seat);
    // seat.resetGameData();
    // 发送筹码变化消息
    msgMgr.noticeChipsChangeMsg(seat);
    // eventMgr.post(new
    // RoomMemInfoUpdateEvent(playerMgr.getPlayer(playerId)));
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
      for (BaccaratRoom room : dataMgr.allRooms()) {
        room.getPlayers().remove(playerId);
        /*
         * 清除房间中玩家所在桌子数据 .
         */
        for (BaccaratTable table : room.getTables().values()) {
          for (BaccaratSeat seat : table.getSeats()) {
            if (seat.getPlayerId() == playerId) {
              seat.clear();
              Player player = playerMgr.getPlayer(playerId);
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
      LOG.error("[百家乐]清除玩家[" + playerId + "]卡限失败", e);
    }
  }
}
