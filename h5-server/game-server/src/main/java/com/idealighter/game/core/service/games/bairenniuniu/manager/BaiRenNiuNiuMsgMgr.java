package com.idealighter.game.core.service.games.bairenniuniu.manager;

import com.alibaba.fastjson.JSONObject;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.BoolInteger;
import com.idealighter.game.core.constant.notice.NoticeType;
import com.idealighter.game.core.constant.room.RoomActiveConstant;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuRoom;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuSeat;
import com.idealighter.game.core.service.games.bairenniuniu.struct.BaiRenNiuNiuTable;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCard;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsType;
import com.idealighter.game.core.service.games.common.niuniu.NiuNiuCardsTypeGetter;
import com.idealighter.game.core.service.notice.manager.NoticeMsgMgr;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.BairenniuniuRoomTypeDic;
import com.idealighter.game.dictionary.dic.GameNoticeDic;
import com.idealighter.game.dictionary.domain.BairenniuniuRoomDomain;
import com.idealighter.game.dictionary.domwrapper.GameNoticeDomainWrapper;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.bairenniuniu.dto.BetInfo;
import com.idealighter.game.games.bairenniuniu.dto.CardsInfo;
import com.idealighter.game.games.bairenniuniu.dto.GameInfo;
import com.idealighter.game.games.bairenniuniu.dto.GameInfo.BalanceInfo;
import com.idealighter.game.games.bairenniuniu.dto.GameInfo.BankerInfo;
import com.idealighter.game.games.bairenniuniu.dto.RoomInfo;
import com.idealighter.game.games.bairenniuniu.dto.RoomTypeDetailInfo;
import com.idealighter.game.games.bairenniuniu.message.ResApplyBankersMsg;
import com.idealighter.game.games.bairenniuniu.message.ResBalanceMsg;
import com.idealighter.game.games.bairenniuniu.message.ResBankerInfoMsg;
import com.idealighter.game.games.bairenniuniu.message.ResBetMsg;
import com.idealighter.game.games.bairenniuniu.message.ResChipsChangeMsg;
import com.idealighter.game.games.bairenniuniu.message.ResDealCardsMsg;
import com.idealighter.game.games.bairenniuniu.message.ResEnterGameHallMsg;
import com.idealighter.game.games.bairenniuniu.message.ResEnterRoomMsg;
import com.idealighter.game.games.bairenniuniu.message.ResEnterTableMsg;
import com.idealighter.game.games.bairenniuniu.message.ResExitRoomMsg;
import com.idealighter.game.games.bairenniuniu.message.ResExitTableMsg;
import com.idealighter.game.games.bairenniuniu.message.ResGameInfoMsg;
import com.idealighter.game.games.bairenniuniu.message.ResHistoryMsg;
import com.idealighter.game.games.bairenniuniu.message.ResOtherEnterTableMsg;
import com.idealighter.game.games.bairenniuniu.message.ResTableBetMsg;
import com.idealighter.game.games.bairenniuniu.message.ResTimeMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


/**
 * 百人牛牛消息管理 .
 */
@Singleton
public class BaiRenNiuNiuMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;
  @Inject
  private BaiRenNiuNiuDataMgr dataMgr;

  @Inject
  private BairenniuniuRoomTypeDic roomTypeDic;
  @Inject
  private NoticeMsgMgr msgMgr;
  @Inject
  private GameNoticeDic gameNoticeDic;

  /**
   * 发送进入游戏大厅消息 .
   * 
   * @param player 玩家.
   */
  public ResMessage sendEnterGameHallMsg(Player player) {
    ResEnterGameHallMsg msg = new ResEnterGameHallMsg();

    Collection<BaiRenNiuNiuRoom> rooms = dataMgr.newestRooms();
    for (BaiRenNiuNiuRoom room : rooms) {
      BairenniuniuRoomDomain roomDom = room.getRoomDomain();
      if (roomDom.getIsActive() != RoomActiveConstant.ACTIVE) {
        continue;
      }
      RoomTypeDetailInfo roomTypeDetailInfo = new RoomTypeDetailInfo();

      roomTypeDetailInfo.setType(roomDom.getType());
      roomTypeDetailInfo.setTypeName(roomTypeDic.map().get(roomDom.getType()).getName());

      roomTypeDetailInfo.getRooms().add(toRoomInfo(room));


      msg.getRoomTypes().add(roomTypeDetailInfo);
    }
    
    if (EmptyUtil.listIsNotEmpty(msg.getRoomTypes())) {
      msg.getRoomTypes().sort(new Comparator<RoomTypeDetailInfo>() {

        @Override
        public int compare(RoomTypeDetailInfo o1, RoomTypeDetailInfo o2) {
          return o1.getType() - o2.getType();
        }
      });
    }

    return msg;
  }

  /**
   * 获取游戏最近信息 .
   *
   * @author abin
   * @date 2018年5月18日 上午11:30:30
   * @param seat 位置.
   * @return 消息.
   */
  public ResMessage sendGameLastInfo(BaiRenNiuNiuSeat seat) {
    ResGameInfoMsg msg = new ResGameInfoMsg();
    AbstractRoom room = seat.getTable().room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : seat.getTable().players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setRoom(toRoomInfo(seat.getTable().getRoom()));
    msg.setGameInfo(toGameInfo(seat));
    return msg;
  }

  private RoomInfo toRoomInfo(BaiRenNiuNiuRoom room) {
    RoomInfo roomInfo = new RoomInfo();
    BairenniuniuRoomDomain roomDom = room.getRoomDomain();
    roomInfo.setRoomId(room.getId());
    roomInfo.setName(roomDom.getName());
    roomInfo.setType(roomDom.getType());
    roomInfo.setMaxNum(roomDom.getMaxNum());
    roomInfo.setLower(roomDom.getLower());
    roomInfo.setUpper(roomDom.getUpper());
    roomInfo.setBeBankerChips(roomDom.getBeBankerChips());
    roomInfo.setOffBankerChips(roomDom.getOffBankerChips());
    roomInfo.setProportionGold(roomDom.getProportionGold());
    roomInfo.setProportionChips(roomDom.getProportionChips());
    roomInfo.setTabble(roomDom.getTableNum());
    roomInfo.setBetOptions(roomDom.getBetOptions());
    roomInfo.setAfee(roomDom.getAfee());
    roomInfo.setInType(roomDom.getInType());
    roomInfo.setPlayerNum(room.getPlayers().size());

    return roomInfo;
  }

  private GameInfo toGameInfo(BaiRenNiuNiuSeat mySeat) {
    BaiRenNiuNiuTable table = mySeat.getTable();
    GameInfo result = new GameInfo();
    result.setStage(gameStage(table));
    result.setStageTime(table.stepFuture.getDelay(TimeUnit.SECONDS));
    // 闲家对庄家输赢记录
    ArrayList<Integer> histroy = new ArrayList<>(table.getHistory());
    Collections.reverse(histroy);
    result.setHistoryList(histroy);

    // 桌面下注信息
    List<BetInfo> tableBetInfos = new ArrayList<BetInfo>();
    for (Entry<Integer, Long> tableBet : table.tableBets().entrySet()) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(tableBet.getKey());
      betInfo.setChips(tableBet.getValue());
      tableBetInfos.add(betInfo);
    }
    result.setTableBetInfos(tableBetInfos);

    // 我的下注信息
    List<BetInfo> myBetInfos = new ArrayList<BetInfo>();
    for (Integer area : mySeat.getBets().keySet()) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(area);
      betInfo.setChips(mySeat.getBets().getOrDefault(area, 0L));
      myBetInfos.add(betInfo);
    }
    result.setMyBetInfos(myBetInfos);

    // 申请坐庄列表
    result.setBankerApplicants(new ArrayList<>(table.getApplyBankers()));

    // 庄家信息
    BaiRenNiuNiuSeat banker = table.banker();
    BankerInfo bankerInfo = new BankerInfo();
    bankerInfo.setPlayerId(banker.getPlayerId());
    bankerInfo.setPlayerId(banker.getPlayerId());
    if (banker.getPlayerId() == 0) {
      bankerInfo.setSex(3);
    } else {
      bankerInfo.setSex(banker.getSex());
    }
    bankerInfo.setChips(banker.getTotalChips());
    bankerInfo.setName(banker.getPlayerName());
    bankerInfo.setNum(banker.getBankerNum());
    bankerInfo.setScore(banker.getBankerScore());
    result.setBankerInfo(bankerInfo);

    // 发牌信息
    List<CardsInfo> cardsInfos = new ArrayList<>();
    for (BaiRenNiuNiuSeat seat : table.getSysSeats()) {
      CardsInfo cardsInfo = new CardsInfo();
      NiuNiuCardsType cardType =
          NiuNiuCardsTypeGetter.getCardsType(seat.getCards(), seat.getBestCards());
      for (NiuNiuCard card : seat.getBestCards()) {
        cardsInfo.getCards().add(card.ordinal());
      }
      cardsInfo.setCardsType(cardType.niu);
      cardsInfo.setId(-seat.getOrder());
      cardsInfos.add(cardsInfo);
    }
    result.setCardsInfo(cardsInfos);

    // 结算信息
    BalanceInfo balanceInfo = new BalanceInfo();
    balanceInfo.setBankerChips(banker.getBillChips());
    balanceInfo.setPlayerChips(mySeat.getBillChips());
    result.setBalanceInfo(balanceInfo);
    return result;
  }

  private int gameStage(BaiRenNiuNiuTable table) {
    return table.getStatus().getVal();
  }

  /**
   * 发送兑换筹码消息.
   * 
   * @param seat 座位信息.
   */
  public void sendChipsChangeMsg(BaiRenNiuNiuSeat seat) {
    ResChipsChangeMsg msg = new ResChipsChangeMsg();
    msg.setChips(seat.getTotalChips());
    msg.setPlayerId(seat.getPlayerId());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送进入桌子消息 .
   * 
   * @param player 玩家.
   */
  public ResMessage sendEnterRoomMsg(Player player) {
    ResEnterRoomMsg msg = new ResEnterRoomMsg();
    for (AbstractTable table : player.curRoom.tables()) {
      msg.getTables().add(table.tableInfo());
    }
    msg.setMembers(player.curRoom.memInfos());
    return msg;
  }

  /**
   * 发送进入桌子消息 .
   * 
   * @param player 玩家.
   * @param table .
   */
  public void sendEnterTableMsg(Player player, AbstractTable table, long totalChips) {
    ResEnterTableMsg msg = new ResEnterTableMsg();
    AbstractRoom room = table.room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : table.players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setTotalChips(totalChips);
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送退出房间消息 .
   * 
   * @param player 玩家.
   */
  public void sendExitRoomMsg(Player player) {
    ResExitRoomMsg msg = new ResExitRoomMsg();
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发牌 .
   * 
   * @param table .
   */
  public void sendDealCardsMsg(BaiRenNiuNiuTable table) {
    ResDealCardsMsg msg = new ResDealCardsMsg();
    for (BaiRenNiuNiuSeat seat : table.getSysSeats()) {
      CardsInfo cardsInfo = new CardsInfo();
      NiuNiuCardsType cardType =
          NiuNiuCardsTypeGetter.getCardsType(seat.getCards(), seat.getBestCards());
      for (NiuNiuCard card : seat.getBestCards()) {
        cardsInfo.getCards().add(card.ordinal());
      }
      cardsInfo.setCardsType(cardType.niu);
      cardsInfo.setId(-seat.getOrder());
      msg.getCardsInfo().add(cardsInfo);
    }

    for (BaiRenNiuNiuSeat seat1 : table.getSeats()) {
      if (seat1.getPlayerId() == 0) {
        continue;
      }
      msgWriter.writeMsg(seat1.getPlayerId(), msg);
    }
  }

  /**
   * 发送退出桌子消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendExitTableMsg(BaiRenNiuNiuSeat seat) {
    ResExitTableMsg msg = new ResExitTableMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送其他人进入桌子消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendOtherEnterTableMsg(BaiRenNiuNiuSeat seat) {
    ResOtherEnterTableMsg msg = new ResOtherEnterTableMsg();
    msg.setMem(seat.getTable().getRoom().memInfo(seat.getPlayerId()));
    for (Long playerId : seat.getTable().players()) {
      if (playerId != seat.getPlayerId()) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }

  /**
   * 向同桌的玩家(包括自己)发送消息 .
   * 
   * @param table .
   * @param msg .
   */
  public void sendTablePlayersMsg(BaiRenNiuNiuTable table, ResMessage msg) {
    List<? extends AbstractSeat> seats = table.seats();
    for (int i = 0; i < seats.size(); i++) {
      AbstractSeat seat = seats.get(i);
      long playerId = seat.getPlayerId();
      if (playerId > 0) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }

  /**
   * 图标合计押注 .
   * 
   * 
   * @param player 玩家.
   * @param area .
   * @param chips .
   * 
   */
  public ResMessage sendBetMsg(Player player, int area, long chips, long bankerTotalBets) {
    ResBetMsg msg = new ResBetMsg();
    msg.setArea(area);
    msg.setChips(chips);
    msg.setBankerTotalBets(bankerTotalBets);
    return msg;
  }

  /**
   * 同步桌上筹码 . 发送下注信息 .
   * 
   * @param table .
   */
  public void sendTableBetInfosMsg(BaiRenNiuNiuTable table) {
    ResTableBetMsg msg = new ResTableBetMsg();
    // for (Entry<Integer, Long> tableBet : table.tableBets().entrySet()) {
    // BetInfo betInfo = new BetInfo();
    // betInfo.setArea(tableBet.getKey());
    // betInfo.setChips(tableBet.getValue());
    // msg.getBetInfo().add(betInfo);
    // }
    for (int i = 1; i <= 4; i++) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(i);
      betInfo.setChips(table.tableBets().getOrDefault(i, 0L));
      msg.getBetInfo().add(betInfo);
    }

    sendTablePlayersMsg(table, msg);
  }

  /**
   * 同步桌上筹码 . 发送下注信息 .
   * 
   * @param seat 座位信息.
   */
  public void sendTableBetInfosMsg(BaiRenNiuNiuSeat seat) {
    ResTableBetMsg msg = new ResTableBetMsg();
    for (Entry<Integer, Long> tableBet : seat.getTable().tableBets().entrySet()) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(tableBet.getKey());
      betInfo.setChips(tableBet.getValue());
      msg.getBetInfo().add(betInfo);
    }
    msgWriter.writeMsg(seat.getPlayerId(), msg);
  }

  /**
   * 发送庄家信息 .
   * 
   * @param table .
   */
  public void sendBankerInfoMsg(BaiRenNiuNiuTable table) {
    BaiRenNiuNiuSeat banker = table.banker();
    ResBankerInfoMsg msg = new ResBankerInfoMsg();
    msg.setPlayerId(banker.getPlayerId());
    if (banker.getPlayerId() == 0) {
      msg.setSex(3);
    } else {
      msg.setSex(banker.getSex());
    }
    msg.setChips(banker.getTotalChips());
    msg.setName(banker.getPlayerName());
    msg.setNum(banker.getBankerNum());
    msg.setScore(banker.getBankerScore());
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送庄家信息 .
   * 
   * @param seat 座位信息.
   */
  public void sendBankerInfoMsg(BaiRenNiuNiuSeat seat) {
    BaiRenNiuNiuSeat banker = seat.getTable().banker();
    ResBankerInfoMsg msg = new ResBankerInfoMsg();
    msg.setPlayerId(banker.getPlayerId());
    msg.setPlayerId(banker.getPlayerId());
    if (banker.getPlayerId() == 0) {
      msg.setSex(3);
    } else {
      msg.setSex(banker.getSex());
    }
    msg.setChips(banker.getTotalChips());
    msg.setName(banker.getPlayerName());
    msg.setNum(banker.getBankerNum());
    msg.setScore(banker.getBankerScore());
    msgWriter.writeMsg(seat.getPlayerId(), msg);
  }

  /**
   * 发送申请庄家列表(所有玩家) .
   * 
   * @param table .
   */
  public void sendApplyBankersMsg(BaiRenNiuNiuTable table) {
    ResApplyBankersMsg msg = new ResApplyBankersMsg();
    msg.setApplicants(new ArrayList<>(table.getApplyBankers()));
    sendTablePlayersMsg(table, msg);
  }

  /**
   * 发送申请庄家列表(单个玩家) .
   * 
   * @param seat 座位信息.
   */
  public void sendApplyBankersMsg(BaiRenNiuNiuSeat seat) {
    ResApplyBankersMsg msg = new ResApplyBankersMsg();
    msg.setApplicants(new ArrayList<>(seat.getTable().getApplyBankers()));
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 发送给客户端当前游戏阶段 .
   * 
   * @param table .
   * @param time .
   */
  public void sendGameStage(BaiRenNiuNiuTable table, int time) {
    ResTimeMsg msg = new ResTimeMsg();
    msg.setTime(time);
    // System.err.println(time);
    msg.setState(table.getStatus().getVal());
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送给客户端当前游戏阶段 .
   * 
   * @param seat 座位信息.
   * @param time .
   */
  public void sendGameStage(BaiRenNiuNiuSeat seat, int time) {
    ResTimeMsg msg = new ResTimeMsg();
    msg.setTime(time);
    // System.err.println(time);
    msg.setState(seat.getTable().getStatus().getVal());
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 发送历史记录 .
   * 
   * @param table .
   */
  public void sendHistory(BaiRenNiuNiuTable table) {
    ResHistoryMsg msg = new ResHistoryMsg();
    ArrayList<Integer> histroy = new ArrayList<>(table.getHistory());
    Collections.reverse(histroy);
    msg.setResult(histroy);
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送历史记录 .
   * 
   * @param seat 座位信息.
   */
  public void sendHistory(BaiRenNiuNiuSeat seat) {
    ResHistoryMsg msg = new ResHistoryMsg();
    ArrayList<Integer> histroy = new ArrayList<>(seat.getTable().getHistory());
    Collections.reverse(histroy);
    msg.setResult(histroy);
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 结算消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendBanlance(BaiRenNiuNiuSeat seat) {
    ResBalanceMsg msg = new ResBalanceMsg();
    BaiRenNiuNiuSeat bankerSeat = seat.getTable().banker();
    msg.setBankerChips(bankerSeat.getBillChips());
    msg.setPlayerChips(seat.getBillChips());
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 向同桌其他玩家发送消息 .
   * 
   * @param seat .
   * @param msg .
   */
  public void sendTablePlayersButSelfMsg(BaiRenNiuNiuSeat seat, ResMessage msg) {
    BaiRenNiuNiuTable table = seat.getTable();
    List<? extends AbstractSeat> seats = table.seats();
    for (AbstractSeat s : seats) {
      if (s.getPlayerId() > 0 && s.getPlayerId() != seat.getPlayerId()) {
        msgWriter.writeMsg(s.getPlayerId(), msg);
      }
    }
  }

  /**
   * 给通知其他人发送桌子上的下注信息 .
   * 
   * @param seat .
   */
  public void sendTableBetInfosMsgExcludeSelf(BaiRenNiuNiuSeat seat) {
    BaiRenNiuNiuTable table = seat.getTable();
    ResTableBetMsg msg = new ResTableBetMsg();
    for (int i = 1; i <= 4; i++) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(i);
      betInfo.setChips(table.tableBets().getOrDefault(i, 0L));
      msg.getBetInfo().add(betInfo);
    }

    sendTablePlayersButSelfMsg(seat, msg);
  }

  /**
   * 如果满足条件发送庄家通吃通赔公告 .
   * 
   * @param playerName . 牌型 .
   * @param chips . 赢得筹码 .
   */
  public void sendBankerNotice(String playerName, String roomName, int count, long chips) {
    for (GameNoticeDomainWrapper dom : gameNoticeDic.getGameDoms()
        .get(Game.BAIREN_NIUNIU.getType())) {
      JSONObject conditionData = dom.conditionData();
      // 公告条件牌型
      Integer conditionCardType = conditionData.getInteger("cardsType");

      if (-conditionCardType == count) {
        String sendContent = MessageFormat.format(dom.getContent(), playerName, chips, roomName);
        if (dom.getMarqueeShow() == BoolInteger.TRUE) { // 跑马灯公告
          msgMgr.sendMarqueeNoticeMsg(sendContent, NoticeType.GAME, dom.getInterval(),
              dom.getTimes(), dom.getColor());
        }
      }
    }
  }
}
