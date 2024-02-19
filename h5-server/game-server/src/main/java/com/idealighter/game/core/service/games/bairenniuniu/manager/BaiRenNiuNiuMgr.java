
package com.idealighter.game.core.service.games.bairenniuniu.manager;

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
import com.idealighter.game.core.service.gamehall.manager.GameHallMgr;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuRoom;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuSeat;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuTable;
import com.idealighter.game.core.service.games.bairenniuniu.struct.GameStatus;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.InstanceState;
import com.idealighter.game.core.service.games.common.SeatState;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.game.Game7WinLoseLog;
import com.idealighter.game.core.service.log.struct.game.GameAfeeLog;
import com.idealighter.game.core.service.log.struct.resource.GameGoldLog;
import com.idealighter.game.core.service.login.manager.LoginMgr;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.BairenniuniuTimeDic;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.games.bairenniuniu.message.ResGameInfoMsg;
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

/**
 * 百人牛牛逻辑处理.
 */
@Singleton
public class BaiRenNiuNiuMgr {

  private static final Logger LOG = LoggerFactory.getLogger(BaiRenNiuNiuMgr.class);

  // 玩家手上的牌数
  public static final int PLAYER_CARDS = 5;
  public static final int MAX_BE_BANKER_NUM = 10; // 最大坐庄把数

  // 百人牛牛定时服务器增加的延时，因为与网络延时等原因
  // private static final int ADDED_DELAY = 5;

  // private static final long BANKER_NEED_CHIPS = 100000000;
  private final EventMgr eventMgr;
  // 游戏线程executor,可以执行普通任务和定时任务
  private final DisruptorExecutor gameExecutor;
  private final BairenniuniuTimeDic timeDic;
  private final PlayerMgr playerMgr;
  private final ScheduleMgr scheduleMgr;
  private final BaiRenNiuNiuMsgMgr msgMgr;
  private final BaiRenNiuNiuDataMgr dataMgr;

  private final LoginMgr loginMgr;
  private final BaiRenNiuNiuControlScript controlScript;
  private CommonMsgMgr commonMsgMgr;

  private Vector<Long> logoutPlayers = new Vector<Long>();

  /**
   * 构造函数.
   * 
   * @param eventMgr .
   * @param executorMgr .
   * @param timeDic .
   * @param playerMgr .
   * @param scheduleMgr .
   * @param msgMgr .
   * @param dataMgr .
   * @param gameHallMgr .
   * @param loginMgr .
   * @param controlScript .
   * @param commonMsgMgr .
   */
  @Inject
  public BaiRenNiuNiuMgr(EventMgr eventMgr, ExecutorMgr executorMgr, BairenniuniuTimeDic timeDic,
      PlayerMgr playerMgr, ScheduleMgr scheduleMgr, BaiRenNiuNiuMsgMgr msgMgr,
      BaiRenNiuNiuDataMgr dataMgr, GameHallMgr gameHallMgr, LoginMgr loginMgr,
      BaiRenNiuNiuControlScript controlScript, CommonMsgMgr commonMsgMgr) {
    this.playerMgr = playerMgr;
    this.scheduleMgr = scheduleMgr;
    this.msgMgr = msgMgr;
    this.dataMgr = dataMgr;
    this.loginMgr = loginMgr;
    this.eventMgr = eventMgr;
    this.timeDic = timeDic;
    this.controlScript = controlScript;
    this.commonMsgMgr = commonMsgMgr;
    eventMgr.register(this);
    this.gameExecutor = executorMgr.getGameExecutor(Game.BAIREN_NIUNIU.getModuleId());

    gameStart();

  }

  /**
   * 订阅玩家退出游戏事件, . 在gameExecutor中执行, 避免线程安全问题 .
   * 
   * @param event .
   */
  @Subscribe
  public void onPlayerExitGame(PlayerExitEvent event) {
    Player player = event.player;
    if (player != null) {
      long playerId = player.getId();
      gameExecutor.execute(() -> {
        if (player.curRoom instanceof BaiRenNiuNiuRoom) {
          if (!logoutPlayers.contains(playerId)) {
            logoutPlayers.add(playerId);
            LOG.info("[{}]玩家预约退出 {}", Game.BAIREN_NIUNIU.getDesc(), playerId);
          }
        }
      });
    }
  }

  /**
   * 清空离线玩家.
   */
  private void clearLogoutPlayer(BaiRenNiuNiuTable table) {
    if (logoutPlayers != null && !logoutPlayers.isEmpty()) {
      List<Long> clearIds = new ArrayList<Long>();
      logoutPlayers.forEach(playerId -> {
        if (table.players().contains(playerId.longValue())) {
          LOG.info("[百人牛牛]清空玩家 {}", playerId);

          Player player = playerMgr.getPlayer(playerId);
          if (player != null) {
            BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(playerId);
            if (seat != null) {
              // 玩家在房间中但是没有准备，退出桌子，退出房间
              doExitTable(seat, player);
            }

            // 玩家是否在牛牛房间中
            if (player.curRoom instanceof BaiRenNiuNiuRoom) {
              // 退出房间
              exitRoom(player);

              // 触发退出
              GameClearExitEvent gameClearExitEvent =
                  new GameClearExitEvent(player, Game.BAIREN_NIUNIU.getDesc());
              eventMgr.post(gameClearExitEvent);
            }
            clearIds.add(playerId);
          }
        }
      });

      if (EmptyUtil.listIsNotEmpty(clearIds)) {
        logoutPlayers.removeAll(clearIds);
        clearIds.clear();
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
        LOG.info("[{}]玩家取消退出 {}", Game.BAIREN_NIUNIU.getDesc(), playerId);
      }
    });
  }

  /**
   * 检验是否房间关闭，如果桌子不在游戏中或者游戏正在休息，顺便把桌子关闭 .
   *
   * @author abin
   * @date 2018年4月28日 下午3:41:20
   * @param table 桌子.
   */
  private boolean checkClose(BaiRenNiuNiuTable table) {
    boolean remove = false;
    if (table.getRoom().getInstanceState() == InstanceState.TO_REMOVE) {
      if (table.getStatus().equals(GameStatus.REST)) {
        for (Long playerId : table.players()) {
          Player player = playerMgr.getPlayer(playerId);
          if (player != null) {
            kickoutForRoomClose(player);
          }
        }
        BaiRenNiuNiuRoom room = table.getRoom();
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

  private void deleteRooms(List<BaiRenNiuNiuRoom> deleteRooms) {
    if (EmptyUtil.listIsNotEmpty(deleteRooms)) {
      for (Iterator<BaiRenNiuNiuRoom> iterator = deleteRooms.iterator(); iterator.hasNext();) {
        BaiRenNiuNiuRoom room = iterator.next();
        List<BaiRenNiuNiuTable> tables = new ArrayList<>(room.tables());
        for (Iterator<BaiRenNiuNiuTable> tableIterator = tables.iterator(); tableIterator
            .hasNext();) {
          BaiRenNiuNiuTable blackjackTable = tableIterator.next();
          checkClose(blackjackTable);
        }
        dataMgr.checkRemoveInstance(room.getId(), room.getInstanceId());
      }
    }
  }

  /**
   * .
   * 
   * @param event .
   */
  @Subscribe
  public void onStartRoom(StartRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.BAIREN_NIUNIU) {
        List<BaiRenNiuNiuRoom> deleteRooms = dataMgr.startRoom(event.getRoomId(), true);
        deleteRooms(deleteRooms);

        // 启动table
        BaiRenNiuNiuRoom room = dataMgr.getNewestRoom(event.getRoomId());
        if (room != null) {
          for (BaiRenNiuNiuTable table : room.tables()) {
            doRest(table);
          }
        }
      }
    });
  }

  /**
   * .
   * 
   * @param event .
   */
  @Subscribe
  public void onShutDownRoom(ShutdownRoomEvent event) {
    gameExecutor.execute(() -> {
      if (event.getGame() == Game.BAIREN_NIUNIU) {
        List<BaiRenNiuNiuRoom> deleteRooms = dataMgr.deleteRoom(event.getRoomId());
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
      if (event.getGame() == Game.BAIREN_NIUNIU) {
        List<Integer> ids = dataMgr.reloadRoom();
        if (EmptyUtil.listIsNotEmpty(ids)) {
          for (Integer id : ids) {
            List<BaiRenNiuNiuRoom> deleteRooms = dataMgr.startRoom(id, false);
            deleteRooms(deleteRooms);

            // 启动table
            BaiRenNiuNiuRoom room = dataMgr.getNewestRoom(id);
            if (room != null) {
              for (BaiRenNiuNiuTable table : room.tables()) {
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
      if (event.getGame() == Game.BAIREN_NIUNIU) {
        Collection<BaiRenNiuNiuRoom> rooms = dataMgr.allRooms();
        if (!EmptyUtil.isEmpty(rooms)) {
          for (BaiRenNiuNiuRoom room : rooms) {
            List<BaiRenNiuNiuRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
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
    LOG.info("百人牛牛开始退出.....");
    gameExecutor.execute(() -> {
      Collection<BaiRenNiuNiuRoom> rooms = dataMgr.allRooms();
      if (!EmptyUtil.isEmpty(rooms)) {
        for (BaiRenNiuNiuRoom room : rooms) {
          List<BaiRenNiuNiuRoom> deleteRooms = dataMgr.deleteRoom(room.getId());
          deleteRooms(deleteRooms);
        }
      }
    });

    int times = 600;
    while (!BaiRenNiuNiuDataMgr.roomEmpty && times > 0) {
      try {
        Thread.sleep(500L);
        times--;
      } catch (InterruptedException exception) {
        LOG.error("百人牛牛退出异常", exception);
      }
    }

    LOG.info("百人牛牛退出");
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
      LOG.warn("[百人牛牛]玩家[{}][{}]在[{}]游戏中，暂时无法进入游戏", player.getId(), player.getPlayerName(),
          game.getDesc());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());
    }

    LOG.info("[百人牛牛]玩家[{}][{}]进入大厅成功", player.getPlayerName(), player.getId());
    return msgMgr.sendEnterGameHallMsg(player);
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
      LOG.warn("[百人牛牛]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入房间", player.getId(), player.getPlayerName(),
          game.getDesc(), curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());

    }

    BaiRenNiuNiuRoom room = dataMgr.getNewestRoom(roomId);
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    BairenniuniuRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != 1) {
      LOG.error("[百人牛牛]玩家[{}][{}]进入的房间[{}]已经关闭", player.getPlayerName(), player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_CLOSED);
    }
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }
    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()

        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {

      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }


    if (room.getPlayers().size() >= roomDom.getMaxNum()) {

      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    LOG.info("[百人牛牛]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());
    // 发送进入房间消息
    return msgMgr.sendEnterRoomMsg(player);
  }


  /**
   * 快速进入 .
   *
   * @author abin
   * @date 2018年9月10日 下午5:42:38
   * @param player 玩家.
   * @param roomId 房间Id.
   * @return 消息.
   */
  public ResMessage fastEnter(Player player, int roomId) {
    AbstractRoom curRoom = player.curRoom;
    if (curRoom != null) {
      Game game = curRoom.game();
      LOG.warn("[百人牛牛]玩家[{}][{}]当前在游戏[{}]房间[{}]中不能进入房间", player.getId(), player.getPlayerName(),
          game.getDesc(), curRoom.getId());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ALREADY_IN, game.getDesc());

    }

    BaiRenNiuNiuRoom room = dataMgr.getNewestRoom(roomId);
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    BairenniuniuRoomDomain roomDom = room.getRoomDomain();
    if (roomDom.getIsActive() != 1) {
      LOG.error("[百人牛牛]玩家[{}][{}]进入的房间[{}]已经关闭", player.getPlayerName(), player.getId(), roomId);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_CLOSED);
    }
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }
    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (!player.vip()

        && room.getPlayers().size() >= roomDom.getMaxNum() * roomDom.getOrdinarPeople() / 100) {

      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]普通玩家人数已超过上限", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_NOT_VIP_ROOM_FULL);
    }


    if (room.getPlayers().size() >= roomDom.getMaxNum()) {

      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]已超过最大人数[{}]", player.getPlayerName(), player.getId(),
          roomId, roomDom.getMaxNum());
      HuohuaAssert.isTrue(false, ErrorCode.GAME_ROOM_FULL);
    }

    // 触发进入房间事件
    eventMgr.post(new EnterRoomEvent(player, room));

    LOG.info("[百人牛牛]玩家[{}][{}]进入房间[{}]成功", player.getPlayerName(), player.getId(), roomId);

    // 修改玩家当前房间
    player.curRoom = room;
    room.getPlayers().add(player.getId());


    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      LOG.error("[百人牛牛]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuSeat emptySeat = findEmptySeat(player, room);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);

    doEnterTable(player, emptySeat, null);
    return ResMessage.DEFAULT;
  }

  /**
   * 获取最近游戏信息 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:29:34
   * @param player 玩家.
   * @return 信息.
   */
  public ResMessage gameLastInfo(Player player) {
    AbstractRoom curRoom = player.curRoom;
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());

    ResMessage result = null;
    if (curRoom != null && seat != null) {
      // BairenniuniuRoomDomain roomDomain = seat.getTable().getRoom().getRoomDomain();
      // seat.setTotalChips(convertChip(roomDomain, player.getGold()));
      result = msgMgr.sendGameLastInfo(seat);


      if (logoutPlayers.contains(player.getId())) {
        logoutPlayers.remove(player.getId());
        LOG.info("[{}]玩家取消退出 {}", Game.BAIREN_NIUNIU.getDesc(), player.getId());

      }
    } else {
      result = new ResGameInfoMsg();
      player.curRoom = null;
      player.curTable = null;
      player.curSeat = null;
    }
    return result;
  }


  /**
   * 请求退出房间 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:29:58
   * @param player 玩家.
   * @return 退出房间消息.
   */
  public ResMessage resExitRoom(Player player) {
    exitRoom(player);
    return ResMessage.DEFAULT;

  }

  private void kickoutForRoomClose(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      doExitTable(seat, player);
    }
    if (player.curRoom != null) {
      doExitRoom(player);
      commonMsgMgr.noticeRoomCloseKickout(player);
    }
  }

  private void doExitRoom(Player player) {
    AbstractRoom curRoom = player.curRoom;
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());

    HuohuaAssert.isTrue(curRoom instanceof BaiRenNiuNiuRoom);
    HuohuaAssert.isTrue(seat == null, ErrorCode.GAME_EXIT_ROOM_EXIT_SIT_FIRST);

    player.curRoom = null;
    BaiRenNiuNiuRoom room = (BaiRenNiuNiuRoom) curRoom;
    room.getPlayers().remove(player.getId());

    LOG.info("[百人牛牛]玩家[{}][{}]退出房间[{}]", player.getPlayerName(), player.getId(), room.getId());
    // 触发退出房间事件
    eventMgr.post(new ExitRoomEvent(player, curRoom));
  }

  /**
   * 玩家退出房间 .
   * 
   * @param player 玩家.
   */
  public void exitRoom(Player player) {
    doExitRoom(player);
    msgMgr.sendExitRoomMsg(player);
  }

  /**
   * 筹码换算金币,只会是多个金币等于一个筹码 .
   * 
   * @param roomDomain .
   * @param chips .
   */
  public long convertGold(BairenniuniuRoomDomain roomDomain, long chips) {
    return chips * roomDomain.getProportionGold() * Player.PRECISION
        / roomDomain.getProportionChips();
  }

  /**
   * 金币换算筹码 .
   * 
   * @param roomDomain .
   * @param gold .
   * @return
   */
  public long convertChip(BairenniuniuRoomDomain roomDomain, long gold) {
    return gold * roomDomain.getProportionChips() / roomDomain.getProportionGold()
        / Player.PRECISION;
  }

  /**
   * 玩家金币兑换筹码.
   * 
   * @param player 玩家.
   * @param gold 兑换筹码的金币.
   */
  public ResMessage exchangeChips(Player player, long gold) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(player.getSafeGold() >= gold);

    BaiRenNiuNiuTable table = seat.getTable();

    HuohuaAssert.isTrue(!table.banker().equals(seat), ErrorCode.GAME_BANKER_NOT_ALLOW_EXCHANGE);

    long chips = convertChip(seat.getTable().getRoom().getRoomDomain(), gold);

    seat.setTotalChips(seat.getTotalChips() + chips);

    playerMgr.addSafeGold(player, -gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.BAIREN_NIUNIU_EXCHANGE_CHIP);

    playerMgr.addGold(player, gold, LogReason.BAIREN_NIUNIU_EXCHANGE_CHIP);

    // playerMgr.addAllGold(player, -gold, gold, LogReason.BAIREN_NIUNIU_EXCHANGE_CHIP);

    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);

    LOG.info("[百人牛牛]玩家[{}][{}]用金币[{}]兑换筹码[{}]成功", player.getPlayerName(), player.getId(), gold,
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
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    HuohuaAssert.isTrue(seat != null);

    HuohuaAssert.isTrue(chips > 0 && chips <= seat.getTotalChips());

    BaiRenNiuNiuTable table = seat.getTable();

    HuohuaAssert.isTrue(!table.banker().equals(seat), ErrorCode.GAME_BANKER_NOT_ALLOW_EXCHANGE);

    HuohuaAssert.isTrue(table.getStatus() == GameStatus.REST, ErrorCode.GAME_NOT_ALLOW_EXCHANGE);

    seat.setTotalChips(seat.getTotalChips() - chips);
    long gold = convertGold(seat.getTable().getRoom().getRoomDomain(), chips);

    playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
        LogReason.BAIREN_NIUNIU_EXCHANGE_GOLD);
    playerMgr.addGold(player, -gold, LogReason.BAIREN_NIUNIU_EXCHANGE_GOLD);

    // playerMgr.addAllGold(player, gold, -gold, LogReason.BAIREN_NIUNIU_EXCHANGE_GOLD);

    // 发送兑换筹码变更
    msgMgr.sendChipsChangeMsg(seat);

    LOG.info("[百人牛牛]玩家[{}][{}]用筹码[{}]兑换金币[{}]成功", player.getPlayerName(), player.getId(), chips,
        gold);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    return ResMessage.DEFAULT;
  }

  /**
   * 玩家进入牌桌 .
   * 
   * @param player 玩家.
   * @param tableId .
   * @param order .
   */
  public void enterTable(Player player, int tableId, byte order, String tablePwd) {
    if (!(player.curRoom instanceof BaiRenNiuNiuRoom)) {
      LOG.error("[百人牛牛]玩家[{}][{}]当前不在百人牛牛房间中不能进入百人牛牛桌子", player.getPlayerName(), player.getId());
      return;
    }

    BaiRenNiuNiuTable table = dataMgr.getTable(tableId);

    if (table == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]进入的牌桌[{}]不存在", player.getPlayerName(), player.getId(), tableId);
      return;
    }

    if (dataMgr.getPlayerSeat(player.getId()) != null) {
      LOG.error("[百人牛牛]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", player.getPlayerName(), player.getId());
      return;
    }

    fastEnterTable(player, table.getRoom().getId());

  }

  /**
   * 快速进入四人牛牛 .
   * 
   * @param player 玩家.
   * @param roomId .
   */
  public ResMessage fastEnterTable(Player player, int roomId) {
    if (!(player.curRoom instanceof BaiRenNiuNiuRoom)) {
      LOG.error("[百人牛牛]玩家[{}][{}]当前不在房间中不能直接进入桌子", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat != null) {
      LOG.error("[百人牛牛]玩家[{}][{}]已经在牌桌中不能重复进入牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuRoom room = dataMgr.getNewestRoom(roomId);
    HuohuaAssert.isTrue(room != null && room.getInstanceState().equals(InstanceState.NORMAL),
        ErrorCode.GAME_ROOM_CLOSED);

    BaiRenNiuNiuSeat emptySeat = findEmptySeat(player, room);
    HuohuaAssert.isTrue(emptySeat != null, ErrorCode.GAME_NOT_SIT);

    doEnterTable(player, emptySeat, null);
    return ResMessage.DEFAULT;
  }

  /**
   * 玩家进入座位 .
   * 
   * @param player 玩家.
   * @param seat 座位信息.
   */
  private void doEnterTable(Player player, BaiRenNiuNiuSeat seat, String tablePwd) {
    int roomId = seat.getTable().getRoom().getId();
    BaiRenNiuNiuTable table = seat.getTable();

    if (table.isReadied()) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的桌子[{}]已经开始游戏", player.getPlayerName(), player.getId(),
          table.getId());
      HuohuaAssert.isTrue(false);
    }

    BairenniuniuRoomDomain roomDom = table.getRoom().getRoomDomain();
    if (player.getSafeGold() < roomDom.getLower()) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]低于下限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getLower());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_NOT_ENOUGH);
    }

    long upper = roomDom.getUpper();
    if (upper > 0L && player.getSafeGold() > upper) {
      LOG.warn("[百人牛牛]玩家[{}][{}]进入的房间[{}]高于上限[{}]金币", player.getPlayerName(), player.getId(),
          roomId, roomDom.getUpper());
      HuohuaAssert.isTrue(false, ErrorCode.PLAYER_GOLD_BEYOND_MAX_GOLD);
    }

    if (roomDom.getAfee() > 0) {
      long beforeSafeGold = player.getSafeGold();
      HuohuaAssert.isTrue(beforeSafeGold >= roomDom.getAfee(), ErrorCode.GAME_NOT_ENOUGH_FEE);
      long afterSafeGold = beforeSafeGold - roomDom.getAfee();

      while (!playerMgr.addSafeGold(player, beforeSafeGold, afterSafeGold,
          ThirdOrderUtils.generateThirdOrderNo(),
          LogReason.BAIREN_NIUNIU_ENTER_TABLE_MINUS_AFEE)) {
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
    // seat.setTotalChips(convertChip(roomDom, player.getGold()));

    // 更新玩家座位
    dataMgr.updatePlayerSeats(player.getId(), seat);
    // 触发进入桌子事件
    eventMgr.post(new EnterTableEvent(player, seat));

    player.curSeat = seat;

    // 向玩家发送进入牌桌结果信息
    // int time = (int) table.stepFuture.getDelay(TimeUnit.SECONDS);
    // if (time < 0) {
    // doRest(seat.table);
    // time = timeDic.get(GameStatus.REST.val).getTime();
    // }

    int time = (int) table.stepFuture.getDelay(TimeUnit.SECONDS);
    msgMgr.sendEnterTableMsg(player, table, seat.getTotalChips());
    msgMgr.sendOtherEnterTableMsg(seat);
    msgMgr.sendHistory(seat);
    msgMgr.sendTableBetInfosMsg(seat);
    msgMgr.sendApplyBankersMsg(seat);
    msgMgr.sendBankerInfoMsg(seat);
    msgMgr.sendGameStage(seat, time < 0 ? 5 : time);
    LOG.info("[百人牛牛]玩家[{}][{}]进入桌子[{}]座位[{}]成功", player.getPlayerName(), player.getId(),
        table.getId(), seat.getOrder());
  }

  /**
   * 玩家退出牌桌 .
   * 
   * @param player 玩家.
   */
  public ResMessage exitTable(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]没在牌桌中不能退出牌桌", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    // 退出桌子
    doExitTable(seat, player);
    return ResMessage.DEFAULT;
  }

  /**
   * 退出桌子.
   * <p/>
   * . 1.玩家正常退出牌桌, . 能在在线玩家中拿到player, 正常退出桌子
   * <p/>
   * . 2.玩家非正常退出(如掉线)后,从活跃玩家或数据库中拿到PlayerDomain，结算后马上更新缓冲和数据库 .
   * 
   * @param seat 座位信息.
   * @param player 玩家.
   */
  private void doExitTable(BaiRenNiuNiuSeat seat, Player player) {
    // long playerId = seat.playerId;
    // Player player = playerMgr.getPlayer(playerId);
    BaiRenNiuNiuTable table = seat.getTable();

    // 发送退出房间消息
    if (table.getStatus() == GameStatus.REWARD) { // 开奖过程中退出，立刻更新玩家金币
      playerMgr.noticeGold(playerMgr.getPlayer(seat.getPlayerId()));
    } else if (table.getStatus() == GameStatus.BET) { // 下注退,退还下注金币
      // playerMgr.addGold(player, convertGold(table.room.id,
      // seat.totalBets()), LogReason.BAIREN_NIUNIU_BILL);
    }

    if (seat == table.banker()) { // 庄家退
      if (table.getStatus() == GameStatus.REST // 休息阶段，玩家主动退出
          || //
          table.getStatus() == GameStatus.REWARD // 结算阶段，剔除玩家
      ) {
        table.setBankerId(0);
        doChangeBanker(table);
      }
    } else {
      if (table.getApplyBankers().contains(seat.getPlayerId())) {
        table.getApplyBankers().remove(seat.getPlayerId());
        msgMgr.sendApplyBankersMsg(table);
      }
    }

    if (seat.getTotalChips() > 0) {
      long gold = player.getGold();

      playerMgr.addSafeGold(player, gold, ThirdOrderUtils.generateThirdOrderNo(),
          LogReason.BAIREN_NIUNIU_EXIT_TABLE_EXCHANGE_GOLD);
      playerMgr.addGold(player, -gold, LogReason.BAIREN_NIUNIU_EXIT_TABLE_EXCHANGE_GOLD);

      // playerMgr.addAllGold(player, gold, -gold,
      // LogReason.BAIREN_NIUNIU_EXIT_TABLE_EXCHANGE_GOLD);

      seat.setTotalChips(0L);
    }

    msgMgr.sendExitTableMsg(seat);
    dataMgr.removePlayerSeat(seat.getPlayerId());
    LOG.info("[百人牛牛]玩家[{}][{}]退出桌子[{}]", seat.getPlayerId(), seat.getPlayerName(),
        seat.getTable().getId());
    seat.clear();
    if (table.getStatus() == GameStatus.BET) { // 下注阶段退同步一次筹码
      msgMgr.sendTableBetInfosMsg(table);
    }

    seat.clear();
    player.curSeat = null;
    player.curTable = null;

    // 触发退出桌子事件
    eventMgr.post(new ExitTableEvent(player, seat));
  }

  /**
   * 开始游戏 .
   */
  private void gameStart() {
    for (BaiRenNiuNiuRoom room : dataMgr.newestRooms()) {
      for (BaiRenNiuNiuTable table : room.tables()) {
        doRest(table);
      }
    }
  }

  /**
   * 休息阶段 .
   * 
   * @param table .
   */

  private void doRest(BaiRenNiuNiuTable table) {
    table.setStatus(GameStatus.REST);
    if (checkClose(table)) {
      dataMgr.checkRemoveInstance(table.getRoom().getId(), table.getRoom().getInstanceId());
      return;
    }
    int time = timeDic.get(table.getStatus().getVal()).getTime();
    bankerNotice(table);
    for (BaiRenNiuNiuSeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0) {
        playerMgr.noticeGold(playerMgr.getPlayer(seat.getPlayerId()));
      }
      seat.getAreaBill().clear();
      seat.resetGameData();
    }
    msgMgr.sendGameStage(table, time);
    // 改变庄家
    doChangeBanker(table);
    table.reset();
    table.stepFuture =
        scheduleMgr.schedule(() -> doBet(table), time, TimeUnit.SECONDS, gameExecutor);
    LOG.info("[百人牛牛]房间[{}]桌子[{}]阶段[{}]", table.getRoom().getId(), table.getId(),
        table.getStatus().getDesc());
  }

  /**
   * 庄家通吃通赔公告 .
   * 
   * 
   * @param table .
   */
  private void bankerNotice(BaiRenNiuNiuTable table) {
    BaiRenNiuNiuSeat bankerSeat = table.banker();
    int count = 0;
    for (BaiRenNiuNiuSeat sysSeat : table.getSysSeats()) {
      if (sysSeat.getOrder() == BaiRenNiuNiuTable.BANKER_SEAT) {
        continue;
      }
      if (sysSeat.isWin()) {
        count++;
      }
    }
    if (count == 0 || count == 4) {
      msgMgr.sendBankerNotice(bankerSeat.getPlayerName(), table.getRoom().getName(), count,
          bankerSeat.getBillChips());
    }
    bankerSeat.resetGameData();
  }

  /**
   * 下注阶段 .
   * 
   * @param table .
   */
  private void doBet(BaiRenNiuNiuTable table) {
    table.setStatus(GameStatus.BET);
    int time = timeDic.get(table.getStatus().getVal()).getTime();
    msgMgr.sendGameStage(table, time);
    table.stepFuture =
        scheduleMgr.schedule(() -> doReward(table), time, TimeUnit.SECONDS, gameExecutor);
    LOG.info("[百人牛牛]房间[{}]桌子[{}]阶段[{}]", table.getRoom().getId(), table.getId(),
        table.getStatus().getDesc());
  }

  /**
   * 开奖阶段 .
   * 
   * @param table .
   */
  private void doReward(BaiRenNiuNiuTable table) {
    table.setStatus(GameStatus.REWARD);
    int time = timeDic.get(table.getStatus().getVal()).getTime();
    msgMgr.sendGameStage(table, time);
    // msgMgr.sendDealCardsMsg(table);
    // controlScript.controlDealCards(table);
    long start = System.currentTimeMillis();
    controlScript.controlDealCards2(table);
    long end = System.currentTimeMillis();
    LOG.info("百人牛牛发牌总耗时: " + (end - start) + "ms");
    balanceTable(table);
    controlScript.balanceControl(table);
    table.addHistory();
    msgMgr.sendHistory(table);
    dobalance(table);
    table.stepFuture =
        scheduleMgr.schedule(() -> doRest(table), time, TimeUnit.SECONDS, gameExecutor);
    LOG.info("[百人牛牛]房间[{}]桌子[{}]阶段[{}]", table.getRoom().getId(), table.getId(),
        table.getStatus().getDesc());
  }

  /**
   * 结算阶段 .
   * 
   * @param table .
   */
  private void dobalance(BaiRenNiuNiuTable table) {
    BaiRenNiuNiuSeat banker = table.banker();
    // 坐庄把数
    banker.setBankerNum(banker.getBankerNum() + 1);
    // 坐庄
    banker.setBankerScore(banker.getBankerScore() + banker.getBillChips());
    for (BaiRenNiuNiuSeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0) {
        doBill(seat);
      }
    }

    // 结算完之后，离线玩家退出
    clearLogoutPlayer(table);

    // table.banker().resetGameData();
    // 结算后，如果庄家是玩家，校验钱够不够
    if (banker.getPlayerId() > 0
        && banker.getTotalChips() < table.getRoom().getRoomDomain().getOffBankerChips()) {
      table.setBankerId(0);
      doChangeBanker(table);
    }

    // 有其他人申请坐庄时，如果玩家坐庄的数量满10时，切换庄家
    if (banker.getPlayerId() > 0 && banker.getBankerNum() >= MAX_BE_BANKER_NUM) {
      checkApplayBanker(table);
      if (table.getApplyBankers().size() > 1) {
        table.setBankerId(0);
        doChangeBanker(table);
      }
    }

    // msgMgr.sendGameStage(table, time);
    // 同步庄家信息
    msgMgr.sendBankerInfoMsg(table);
    // 结算消息

    //
    // table.stepFuture = scheduleMgr.schedule(() -> doRest(table), time,
    // TimeUnit.SECONDS, gameExecutor);
    // LOG.info("百人牛牛房间[{}]桌子[{}]阶段[{}]", table.room.id, table.id,
    // table.status.desc);

  }

  /**
   * 结算(只是结算筹码，并未告知客户端) .
   * 
   * @param table .
   */
  private void balanceTable(BaiRenNiuNiuTable table) {
    BaiRenNiuNiuSeat banker = table.banker();
    BaiRenNiuNiuSeat dealer = table.dealer();
    for (BaiRenNiuNiuSeat seat : table.getSeats()) {
      if (seat.getPlayerId() > 0 && seat.getPlayerId() != banker.getPlayerId()) {
        for (BaiRenNiuNiuSeat sysSeat : table.getSysSeats()) {
          if (sysSeat.getOrder() == BaiRenNiuNiuTable.BANKER_SEAT) {
            continue;
          }
          long areBet = seat.getBets().getOrDefault(-sysSeat.getOrder(), 0L);
          if (areBet == 0) {
            continue;
          }
          if (sysSeat.isWin()) {
            long playerWin = sysSeat.getCardsType().multiple * areBet;
            seat.setBillChips(seat.getBillChips() + playerWin);
            banker.setBillChips(banker.getBillChips() - playerWin);
            seat.getAreaBill().put(-sysSeat.getOrder(), playerWin);


            // 玩家 betting
            seat.setBetting(seat.getBetting() + areBet);
            seat.setBonus(seat.getBonus() + playerWin + areBet);

            banker.setBetting(banker.getBetting() + playerWin);

          } else {
            long playerLose = dealer.getCardsType().multiple * areBet;
            banker.setBillChips(banker.getBillChips() + playerLose);
            seat.setBillChips(seat.getBillChips() - playerLose);
            seat.getAreaBill().put(-sysSeat.getOrder(), -playerLose);

            // 玩家 betting
            seat.setBetting(seat.getBetting() + playerLose);

            banker.setBonus(banker.getBonus() + playerLose);
          }
        }
      }
    }
    table.setEarn(banker.getPlayerId() > 0 ? table.robotBill() : -table.playerBill());
  }

  /**
   * 查找空位置, . 优先查找有人的桌子 .
   * 
   * @param player 玩家.
   * @param room .
   * @return
   */
  private BaiRenNiuNiuSeat findEmptySeat(Player player, BaiRenNiuNiuRoom room) {
    BaiRenNiuNiuSeat emptySeat = null;
    ArrayList<BaiRenNiuNiuTable> tables = new ArrayList<>(room.getTables().values());
    Collections.shuffle(tables);

    boolean robot = player.isRobot();
    for (BaiRenNiuNiuTable table : tables) {
      if (robot && table.robotFull()) {
        continue;
      }
      for (BaiRenNiuNiuSeat seat : table.getSeats()) {
        if (seat.getPlayerId() == 0) {
          emptySeat = seat;
        }
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
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (area < 1 || area > 4) {
      LOG.error("[百人牛牛]玩家[{}][{}]下注区域[{}]不合法", player.getUserName(), player.getId(), area);
      HuohuaAssert.isTrue(false);
    }
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]不在牌桌中不能下注", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    if (seat.getTable().getStatus() != GameStatus.BET) {
      LOG.error("[百人牛牛]非下注阶段玩家[{}][{}]不能下注", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    long areaBet = seat.getBets().getOrDefault(area, 0L) + chips;
    long limitBet = seat.totalBets() + chips;

    long maxMultipleChips = limitBet * NiuNiuCardsType.getMaxMultiple();
    if (maxMultipleChips > seat.getTotalChips()) {
      LOG.error("[百人牛牛]玩家[{}][{}]下注总和[{}]不能超过自己的筹码[{}]的1/8", player.getPlayerName(),
          player.getId(), limitBet, seat.getTotalChips() / 8);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_CHIP_NOT_ENOUGH, "" + maxMultipleChips);
    }
    BaiRenNiuNiuSeat banker = seat.getTable().banker();

    if (banker.getPlayerId() != 0) {
      maxMultipleChips =
          (seat.getTable().tableTotalBets() + chips) * NiuNiuCardsType.getMaxMultiple();
      if (maxMultipleChips > banker.getTotalChips()) {
        HuohuaAssert.isTrue(false, ErrorCode.GAME_BANKER_CHIP_NOT_ENOUGH);
      }
    }
    seat.getBets().put(area, areaBet);

    /*
     * 扣除筹码和金币 .
     */
    // seat.totalChips -= chips;
    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);
    // playerMgr.minusGold(player, convertGold(seat.table.room.id, chips),
    // LogReason.BEIRENNIUNIU_BET);
    // 触发房间大厅成员信息变更事件
    eventMgr.post(new RoomMemInfoUpdateEvent(player));

    // 同步桌子下注筹码消息
    msgMgr.sendTableBetInfosMsgExcludeSelf(seat);

    LOG.info("[百人牛牛]玩家[{}][{}]下注区域[{}][{}]成功", player.getPlayerName(), player.getId(), area,
        chips);
    // 发送下注成功
    return msgMgr.sendBetMsg(player, area, chips, seat.getTable().tableTotalBets());
  }

  /**
   * 清除下注 .
   * 
   * @param player 玩家.
   */
  public ResMessage clearBet(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]没有在桌子中不能清除下注筹码", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuTable table = seat.getTable();
    if (table.getStatus() != GameStatus.BET) {
      LOG.error("[百人牛牛]玩家[{}][{}]不是下注阶段不能清除下注", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    // 合计下注
    // long totalBets = seat.totalBets();
    seat.getBets().clear();

    // seat.totalChips += totalBets;
    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);
    // playerMgr.addGold(player, convertGold(seat.table.room.id, totalBets),
    // LogReason.SHARK_BET);
    // // 触发房间大厅成员信息变更事件
    // eventMgr.post(new RoomMemInfoUpdateEvent(player));

    msgMgr.sendTableBetInfosMsg(seat.getTable());
    LOG.info("[百人牛牛]玩家[{}][{}]清除下注成功", player.getPlayerName(), player.getId());

    return ResMessage.DEFAULT;
  }

  /**
   * 申请庄家 .
   * 
   * @param player 玩家.
   */
  public ResMessage applyBanker(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]没有在桌子中不能申请庄家", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    BaiRenNiuNiuTable table = seat.getTable();
    if (seat.getPlayerId() == table.getBankerId()) {
      LOG.info("[百人牛牛]玩家[{}][{}]已经是庄家申请失败", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    Long beBankerChips = table.getRoom().getRoomDomain().getBeBankerChips();
    if (seat.getTotalChips() < beBankerChips) {
      LOG.error("[百人牛牛]玩家[{}][{}]不够庄家最小筹码数[{}]申请失败", player.getPlayerName(), player.getId(),
          beBankerChips);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_CHIP_NOT_ENOUGH, beBankerChips + "");
    }

    if (table.getApplyBankers().size() >= BaiRenNiuNiuTable.MAX_BANKER_SIZE) {
      LOG.warn("[百人牛牛]玩家[{}][{}]申请庄家时人数超过上限[{}]", player.getPlayerName(), player.getId(),
          BaiRenNiuNiuTable.MAX_BANKER_SIZE);
      HuohuaAssert.isTrue(false, ErrorCode.GAME_BANKER_QUEUE_FULL);
    }

    if (table.getApplyBankers().contains(player.getId())) {
      LOG.error("[百人牛牛]玩家[{}][{}]已经在庄家列表中!,申请失败！");
      HuohuaAssert.isTrue(false);
    }
    table.getApplyBankers().add(player.getId());
    msgMgr.sendApplyBankersMsg(table);
    if (table.getStatus() == GameStatus.REST) {
      doChangeBanker(table);
    }
    LOG.info("[百人牛牛]玩家[{}][{}]申请庄家成功", player.getPlayerName(), player.getId());
    return ResMessage.DEFAULT;
  }

  /**
   * 玩家下庄 .
   * 
   * @param player 玩家.
   */
  public ResMessage offBanker(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]没有在桌子中不能下庄", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }
    BaiRenNiuNiuSeat banker = seat.getTable().banker();
    if (banker != seat) {
      LOG.error("[百人牛牛]玩家[{}][{}]不是庄家不能下庄", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuTable table = seat.getTable();
    if (table.getStatus() != GameStatus.REST) {
      LOG.error("[百人牛牛]玩家[{}][{}]只有在休息阶段才能下庄", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    banker.setBankerNum(0);
    banker.setBankerScore(0);
    table.setBankerId(0);
    msgMgr.sendBankerInfoMsg(table);
    doChangeBanker(table);

    LOG.info("[百人牛牛]玩家[{}][{}]下庄成功", player.getPlayerName(), player.getId());
    return ResMessage.DEFAULT;
  }

  /**
   * 取消申请庄家 .
   * 
   * @param player 玩家.
   */
  public ResMessage cancelApplyBanker(Player player) {
    BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(player.getId());
    if (seat == null) {
      LOG.error("[百人牛牛]玩家[{}][{}]没有在桌子中不能申请庄家", player.getPlayerName(), player.getId());
      HuohuaAssert.isTrue(false);
    }

    BaiRenNiuNiuTable table = seat.getTable();
    if (table.getApplyBankers().contains(player.getId())) {
      table.getApplyBankers().remove(player.getId());
      msgMgr.sendApplyBankersMsg(table);
    }

    LOG.info("[百人牛牛]玩家[{}][{}]取消申请庄家成功", player.getPlayerName(), player.getId());
    return ResMessage.DEFAULT;
  }

  /**
   * 换庄家 .
   * 
   * @param table .
   */
  private void doChangeBanker(BaiRenNiuNiuTable table) {
    // boolean flag = false;
    checkApplayBanker(table);
    if (table.getBankerId() == 0) {
      Long beBankerChips = table.getRoom().getRoomDomain().getBeBankerChips();
      Long applicant = null;
      while ((applicant = table.getApplyBankers().poll()) != null) {
        BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(applicant);
        if (seat.getTotalChips() >= beBankerChips) {
          table.setBankerId(applicant);
          // 换庄后重置系统位置数据
          // table.sysSeat.reset();
          table.dealer().reset();
          msgMgr.sendApplyBankersMsg(table);
          msgMgr.sendBankerInfoMsg(table);
          // flag = true;
          return;
        }
      }
    }
    msgMgr.sendApplyBankersMsg(table);
    msgMgr.sendBankerInfoMsg(table);

  }

  private void checkApplayBanker(BaiRenNiuNiuTable table) {
    List<Long> list = new ArrayList<>();
    list.addAll(table.getApplyBankers());
    Long beBankerChips = table.getRoom().getRoomDomain().getBeBankerChips();
    for (Long playerId : list) {
      BaiRenNiuNiuSeat seat = dataMgr.getPlayerSeat(playerId);
      if (seat == null) {
        table.getApplyBankers().remove(playerId);
        continue;
      }
      if (seat.getTotalChips() < beBankerChips) {
        table.getApplyBankers().remove(playerId);
        msgMgr.sendApplyBankersMsg(table);
      }
    }
  }

  /**
   * 座位结算 .
   * 
   * @param seat 座位信息.
   */
  private void doBill(BaiRenNiuNiuSeat seat) {
    long playerId = seat.getPlayerId();
    // 扣除输赢
    seat.setTotalChips(seat.getTotalChips() + seat.getBillChips());

    PlayerBo playerDom = playerMgr.selectPlayer(playerId);

    BairenniuniuRoomDomain roomDomain = seat.getTable().getRoom().getRoomDomain();

    long billGold = convertGold(roomDomain, seat.getBillChips());

    Player player = playerMgr.getPlayer(playerId);
    /*
     * 游戏结算金币 .
     */
    if (player != null) {
      playerMgr.addGold(player, billGold, LogReason.BAIREN_NIUNIU_BILL);
      // playerMgr.addAllGold(player, 0L, billGold, LogReason.BAIREN_NIUNIU_BILL);
    } else {

      playerMgr.addGold(playerDom, billGold, LogReason.BAIREN_NIUNIU_BILL);
    }
    LOG.info("[百人牛牛]]玩家[{}][{}]结算筹码[{}]",
        player != null ? player.getPlayerName() : playerDom.getPlayerName(),
        player != null ? player.getId() : playerDom.getId(), seat.getBillChips());

    // 机器人不加log
    if (!player.isRobot()) {
      DbLogService.log(new Game7WinLoseLog(playerDom, seat, seat.getBillChips(), billGold,
          LogReason.BAIREN_NIUNIU_BILL));

    }

    if (seat.getBetting() != 0 || seat.getBonus() != 0) {
      if (seat.getPlayerId() == seat.getTable().getBankerId()) {
        DbLogService
            .log(new GameGoldLog(playerDom, LogReason.BAIREN_NIUNIU_BILL, seat.getBetting(),
                seat.getBonus(), ThirdGameType.BAI_REN_NIU_NIU, ThirdPlayerType.BANKER));
      } else {
        DbLogService
            .log(new GameGoldLog(playerDom, LogReason.BAIREN_NIUNIU_BILL, seat.getBetting(),
                seat.getBonus(), ThirdGameType.BAI_REN_NIU_NIU, ThirdPlayerType.PLAYER));
      }

    }

    msgMgr.sendBanlance(seat);
    // seat.resetGameData();
    // 发送筹码变化消息
    msgMgr.sendChipsChangeMsg(seat);


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
      for (BaiRenNiuNiuRoom room : dataMgr.allRooms()) {
        room.getPlayers().remove(playerId);
        /*
         * 清除房间中玩家所在桌子数据 .
         */
        for (BaiRenNiuNiuTable table : room.getTables().values()) {
          table.getApplyBankers().remove(playerId);
          if (table.getBankerId() == playerId) {
            table.setBankerId(0);
          }
          for (BaiRenNiuNiuSeat seat : table.getSeats()) {
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
      LOG.error("[百人牛牛]清除玩家[" + playerId + "]卡限失败", e);
    }
  }
}
