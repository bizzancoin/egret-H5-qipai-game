package com.idealighter.game.core.service.games.baccarat.manager;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.constant.BoolInteger;
import com.idealighter.game.core.constant.notice.NoticeType;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratCard;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratCardsType;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratRoom;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratSeat;
import com.idealighter.game.core.service.games.baccarat.struct.BaccaratTable;
import com.idealighter.game.core.service.games.baccarat.struct.BettingDecision;
import com.idealighter.game.core.service.games.baccarat.util.CardsTypeGetter;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.notice.manager.NoticeMsgMgr;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.BaccaratRoomTypeDic;
import com.idealighter.game.dictionary.dic.GameNoticeDic;
import com.idealighter.game.dictionary.domain.BaccaratRoomDomain;
import com.idealighter.game.dictionary.domwrapper.GameNoticeDomainWrapper;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.baccarat.dto.BetInfo;
import com.idealighter.game.games.baccarat.dto.CardsInfo;
import com.idealighter.game.games.baccarat.dto.IconMultiple;
import com.idealighter.game.games.baccarat.dto.RoomInfo;
import com.idealighter.game.games.baccarat.dto.RoomTypeDetailInfo;
import com.idealighter.game.games.baccarat.message.NoticeBillMsg;
import com.idealighter.game.games.baccarat.message.NoticeChipsChangeMsg;
import com.idealighter.game.games.baccarat.message.NoticeCutCardsMsg;
import com.idealighter.game.games.baccarat.message.NoticeDealCardsMsg;
import com.idealighter.game.games.baccarat.message.NoticeExitTableMsg;
import com.idealighter.game.games.baccarat.message.NoticeHistoryMsg;
import com.idealighter.game.games.baccarat.message.NoticeMultipleMsg;
import com.idealighter.game.games.baccarat.message.NoticeOtherEnterTableMsg;
import com.idealighter.game.games.baccarat.message.NoticeResultMsg;
import com.idealighter.game.games.baccarat.message.NoticeStatisticsInfoMsg;
import com.idealighter.game.games.baccarat.message.NoticeTableBetMsg;
import com.idealighter.game.games.baccarat.message.NoticeTimeMsg;
import com.idealighter.game.games.baccarat.message.ResBetMsg;
import com.idealighter.game.games.baccarat.message.ResEnterGameHallMsg;
import com.idealighter.game.games.baccarat.message.ResEnterRoomMsg;
import com.idealighter.game.games.baccarat.message.ResEnterTableMsg;
import com.idealighter.game.games.baccarat.message.ResFastEnterMsg;
import com.idealighter.game.games.baccarat.message.ResFastEnterTableMsg;
import com.idealighter.game.games.baccarat.message.ResGameInfoMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.code.RandCodeUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 百家乐消息管理 .
 */
@Singleton
public class BaccaratMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;
  @Inject
  private BaccaratDataMgr dataMgr;

  @Inject
  private BaccaratRoomTypeDic roomTypeDic;
  @Inject
  private NoticeMsgMgr msgMgr;
  @Inject
  private GameNoticeDic gameNoticeDic;


  /**
   * 获取百家乐房间.
   *
   * @author abin
   * @date 2018年4月23日 上午10:59:46
   * @param room 房间.
   * @return 房间信息.
   */
  private RoomInfo getRoomInfoByRoom(BaccaratRoom room) {
    BaccaratRoomDomain roomDom = room.getRoomDomain();
    RoomInfo roomInfo = new RoomInfo();
    roomInfo.setRoomId(room.getId());
    roomInfo.setName(roomDom.getName());
    roomInfo.setType(roomDom.getType());
    roomInfo.setMaxNum(roomDom.getMaxNum());
    roomInfo.setLower(roomDom.getLower());
    roomInfo.setUpper(roomDom.getUpper());
    roomInfo.setProportionGold(roomDom.getProportionGold());
    roomInfo.setProportionChips(roomDom.getProportionChips());
    roomInfo.setTable(roomDom.getTableNum());
    roomInfo.setBetOptions(roomDom.getBetOptions());
    roomInfo.setAfee(roomDom.getAfee());
    roomInfo.setInType(roomDom.getInType());
    roomInfo.setPlayerNum(room.getPlayers().size());

    return roomInfo;

  }

  private RoomInfo getRoomInfoByInstanceId(int instanceId) {
    BaccaratRoom room = dataMgr.getRoomByIntanceId(instanceId);
    return getRoomInfoByRoom(room);
  }

  /**
   * 发送进入游戏大厅消息 .
   * 
   * @param player 玩家.
   */
  public ResMessage resEnterGameHallMsg(Player player) {
    ResEnterGameHallMsg msg = new ResEnterGameHallMsg();

    Collection<BaccaratRoom> rooms = dataMgr.newestRooms();
    for (BaccaratRoom room : rooms) {
      BaccaratRoomDomain roomDom = room.getRoomDomain();
      if (roomDom.getIsActive() != 1) {
        continue;
      }

      RoomInfo roomInfo = getRoomInfoByRoom(room);

      RoomTypeDetailInfo roomTypeDetailInfo = new RoomTypeDetailInfo();

      roomTypeDetailInfo.setType(roomDom.getType());
      roomTypeDetailInfo.setTypeName(roomTypeDic.map().get(roomDom.getType()).getName());

      roomTypeDetailInfo.getRooms().add(roomInfo);

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
   * 发送兑换筹码消息 .
   * 
   * @param seat 座位信息.
   */
  public void noticeChipsChangeMsg(BaccaratSeat seat) {
    NoticeChipsChangeMsg msg = new NoticeChipsChangeMsg();
    msg.setChips(seat.getTotalChips());
    msg.setPlayerId(seat.getPlayerId());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送进入桌子消息 .
   * 
   * @param player 玩家.
   */
  public ResMessage resEnterRoomMsg(Player player) {
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
   * @param members 成员列表 .
   */
  public ResMessage resEnterTableMsg(List<MemInfo> members) {
    ResEnterTableMsg msg = new ResEnterTableMsg();
    msg.setMems(members);
    return msg;
  }


  /**
   * 快速进入 .
   *
   * @author abin
   * @date 2018年9月10日 下午8:10:01
   * @param members 成员列表.
   * @return res.
   */
  public ResMessage resFastEnterMsg(List<MemInfo> members) {
    ResFastEnterMsg msg = new ResFastEnterMsg();
    msg.setMems(members);
    return msg;
  }


  /**
   * 发送快速进入结果消息 .
   * 
   * @param members 成员列表 .
   */
  public ResMessage resFastEnterTableMsg(List<MemInfo> members) {
    ResFastEnterTableMsg msg = new ResFastEnterTableMsg();
    msg.setMems(members);
    return msg;
  }


  /**
   * 发牌阶段 发个断线重连用户 .
   *
   * @author abin
   * @date 2018年8月22日 下午7:25:32
   * @param player 玩家.
   * @param table 桌子.
   */
  public void noticeDealCardsMsgToPlayer(Player player, BaccaratTable table) {
    NoticeDealCardsMsg msg = new NoticeDealCardsMsg();
    msg.setCardIndex(table.getCardIndex());
    msg.setLeftNum(table.getLeftNum());
    msg.setYellowCard(table.getCardIndex() != table.getYelloCardIndex() ? 0
        : RandCodeUtil.random(1, 6 - table.getLeftNum()));
    CardsInfo cardsInfoBanker = new CardsInfo();
    // 庄家牌
    BaccaratCardsType cardType = CardsTypeGetter.getCardsType(table.getBanker().subList(0, 2));

    int bankerPoint = CardsTypeGetter.getCardPoint(table.getBanker());
    cardsInfoBanker.setPoint(bankerPoint);
    cardsInfoBanker.setCardsType(cardType.type);
    cardsInfoBanker.setId(0);
    for (BaccaratCard card : table.getBanker()) {
      cardsInfoBanker.getCards().add(card.ordinal());
    }
    msg.getCardsInfo().add(cardsInfoBanker);

    // 闲家牌
    CardsInfo cardsInfoPlayer = new CardsInfo();
    int playerPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer());
    BaccaratCardsType cardTypePlayer =
        CardsTypeGetter.getCardsType(table.getNormalPlayer().subList(0, 2));
    cardsInfoPlayer.setPoint(playerPoint);
    cardsInfoPlayer.setCardsType(cardTypePlayer.type);
    cardsInfoPlayer.setId(1);
    for (BaccaratCard card : table.getNormalPlayer()) {
      cardsInfoPlayer.getCards().add(card.ordinal());
    }
    msg.getCardsInfo().add(cardsInfoPlayer);

    msgWriter.writeMsg(player, msg);

  }

  /**
   * 发牌 .
   * 
   * @param table .
   */
  public void noticeDealCardsMsg(BaccaratTable table) {
    NoticeDealCardsMsg msg = new NoticeDealCardsMsg();
    msg.setCardIndex(table.getCardIndex());
    msg.setLeftNum(table.getLeftNum());
    msg.setYellowCard(table.getCardIndex() != table.getYelloCardIndex() ? 0
        : RandCodeUtil.random(1, 6 - table.getLeftNum()));
    CardsInfo cardsInfoBanker = new CardsInfo();
    // 庄家牌
    BaccaratCardsType cardType = CardsTypeGetter.getCardsType(table.getBanker().subList(0, 2));

    int bankerPoint = CardsTypeGetter.getCardPoint(table.getBanker());
    cardsInfoBanker.setPoint(bankerPoint);
    cardsInfoBanker.setCardsType(cardType.type);
    cardsInfoBanker.setId(0);
    for (BaccaratCard card : table.getBanker()) {
      cardsInfoBanker.getCards().add(card.ordinal());
    }
    msg.getCardsInfo().add(cardsInfoBanker);

    // 闲家牌
    CardsInfo cardsInfoPlayer = new CardsInfo();
    int playerPoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer());
    BaccaratCardsType cardTypePlayer =
        CardsTypeGetter.getCardsType(table.getNormalPlayer().subList(0, 2));
    cardsInfoPlayer.setPoint(playerPoint);
    cardsInfoPlayer.setCardsType(cardTypePlayer.type);
    cardsInfoPlayer.setId(1);
    for (BaccaratCard card : table.getNormalPlayer()) {
      cardsInfoPlayer.getCards().add(card.ordinal());
    }
    msg.getCardsInfo().add(cardsInfoPlayer);
    for (BaccaratSeat seat1 : table.getSeats()) {
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
  public void noticePlayerExitTableMsg(BaccaratSeat seat) {
    NoticeExitTableMsg msg = new NoticeExitTableMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    sendTablePlayersMsg(seat.getTable(), msg);
  }

  /**
   * 发送其他人进入桌子消息 .
   * 
   * @param seat 座位信息.
   */
  public void noticeOtherEnterTableMsg(BaccaratSeat seat) {
    NoticeOtherEnterTableMsg msg = new NoticeOtherEnterTableMsg();
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
  public void sendTablePlayersMsg(BaccaratTable table, ResMessage msg) {
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
   * 向同桌其他玩家发送消息 .
   * 
   * @param seat .
   * @param msg .
   */
  public void sendTablePlayersButSelfMsg(BaccaratSeat seat, ResMessage msg) {
    BaccaratTable table = seat.getTable();
    List<? extends AbstractSeat> seats = table.seats();
    for (AbstractSeat s : seats) {
      if (s.getPlayerId() > 0 && s.getPlayerId() != seat.getPlayerId()) {
        msgWriter.writeMsg(s.getPlayerId(), msg);
      }
    }
  }


  /**
   * 图标合计押注 .
   * 
   * @param area 区域.
   * @param chips 筹码.
   * 
   */
  public ResMessage resBetMsg(int area, long chips) {
    ResBetMsg msg = new ResBetMsg();
    msg.setArea(area);
    msg.setChips(chips);
    return msg;
  }


  /**
   * 同步桌上筹码 . 发送下注信息 .
   * 
   * @param seat .
   */
  public void noticeTableBetInfosMsgExcludeSelf(BaccaratSeat seat) {
    BaccaratTable table = seat.getTable();
    NoticeTableBetMsg msg = new NoticeTableBetMsg();
    for (Entry<Integer, Long> tableBet : table.tableBets().entrySet()) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(tableBet.getKey());
      betInfo.setChips(tableBet.getValue());
      msg.getBetInfo().add(betInfo);
    }
    sendTablePlayersButSelfMsg(seat, msg);
  }

  /**
   * 同步桌子信息.
   *
   * @author abin
   * @date 2018年9月6日 下午3:55:03
   * @param table 桌子.
   */
  public void noticeTableBetInfosMsg(BaccaratTable table) {
    NoticeTableBetMsg msg = new NoticeTableBetMsg();
    for (Entry<Integer, Long> tableBet : table.tableBets().entrySet()) {
      BetInfo betInfo = new BetInfo();
      betInfo.setArea(tableBet.getKey());
      betInfo.setChips(tableBet.getValue());
      msg.getBetInfo().add(betInfo);
    }
    sendTablePlayersMsg(table, msg);
  }

  /**
   * 同步桌上筹码 . 发送下注信息 .
   * 
   * @param seat 座位.
   */
  public void noticeTableBetInfosMsg(BaccaratSeat seat) {
    NoticeTableBetMsg msg = new NoticeTableBetMsg();
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
  public void noticeStatisticsInfoMsg(BaccaratTable table) {
    NoticeStatisticsInfoMsg msg = new NoticeStatisticsInfoMsg();
    msg.setBankerWin(table.getBankerWin());
    msg.setPlayerWin(table.getPlayerWin());
    msg.setTie(table.getTie());
    msg.setScore(table.getScore());
    msg.setBankerPaire(table.getBankerPaire());
    msg.setPlayerPaire(table.getPlayerPaire());
    msg.setDragon(table.getDragon());
    msg.setTiger(table.getTiger());
    msg.setDragonTigerTie(table.getDragonTigerTie());
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送庄家信息 .
   * 
   * @param seat 座位信息.
   */
  public void noticeStatisticsInfoMsg(BaccaratSeat seat) {
    NoticeStatisticsInfoMsg msg = new NoticeStatisticsInfoMsg();
    msg.setBankerWin(seat.getTable().getBankerWin());
    msg.setPlayerWin(seat.getTable().getPlayerWin());
    msg.setTie(seat.getTable().getTie());
    msg.setScore(seat.getTable().getScore());
    msg.setBankerPaire(seat.getTable().getBankerPaire());
    msg.setPlayerPaire(seat.getTable().getPlayerPaire());
    msg.setDragon(seat.getTable().getDragon());
    msg.setTiger(seat.getTable().getTiger());
    msg.setDragonTigerTie(seat.getTable().getDragonTigerTie());
    msgWriter.writeMsg(seat.getPlayerId(), msg);
  }

  /**
   * 发送申请庄家列表(所有玩家) .
   * 
   * @param table . //
   */
  // public void sendApplyBankersMsg(BaccaratTable table) {
  // ResApplyBankersMsg msg = new ResApplyBankersMsg();
  // // msg.setApplicants(new ArrayList<>(table.applyBankers));
  // sendTablePlayersMsg(table, msg);
  //
  // }
  //
  // /**
  // * 发送申请庄家列表(单个玩家) .
  // *
  // * @param seat 座位信息.
  // */
  // public void sendApplyBankersMsg(BaccaratSeat seat) {
  // ResApplyBankersMsg msg = new ResApplyBankersMsg();
  // // msg.setApplicants(new ArrayList<>(seat.table.applyBankers));
  // msgWriter.writeMsg(seat.playerId, msg);
  //
  // }

  /**
   * 发送给客户端当前游戏阶段 .
   * 
   * @param table .
   * @param time .
   */
  public void noticeGameStage(BaccaratTable table, int time) {
    NoticeTimeMsg msg = new NoticeTimeMsg();
    msg.setTime(time);
    msg.setState(table.getStatus().val);
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送给客户端当前游戏阶段 .
   * 
   * @param seat 座位信息.
   * @param time .
   */
  public void noticeGameStage(BaccaratSeat seat, int time) {
    NoticeTimeMsg msg = new NoticeTimeMsg();
    msg.setTime(time);
    msg.setState(seat.getTable().getStatus().val);
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 发送历史记录 .
   * 
   * @param table .
   */
  public void noticeHistory(BaccaratTable table) {
    NoticeHistoryMsg msg = new NoticeHistoryMsg();
    ArrayList<Integer> histroyWayBill = new ArrayList<>(table.getHistory());
    // Collections.reverse(histroyWayBill);
    ArrayList<Integer> histroyDragon = new ArrayList<>(table.getHistoryDragonAndTiger());
    // Collections.reverse(histroyDragon);
    ArrayList<Integer> hisbigSmall = new ArrayList<>(table.getHisBigSmall());
    // Collections.reverse(hisbigSmall);
    msg.setHisWaybill(histroyWayBill);
    msg.setHisDragon(histroyDragon);
    msg.setHisBigSmall(hisbigSmall);
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送历史记录 .
   * 
   * @param seat 座位信息.
   */
  public void noticeHistory(BaccaratSeat seat) {
    NoticeHistoryMsg msg = new NoticeHistoryMsg();
    ArrayList<Integer> histroyWayBill = new ArrayList<>(seat.getTable().getHistory());
    // Collections.reverse(histroyWayBill);
    ArrayList<Integer> histroyDragon = new ArrayList<>(seat.getTable().getHistoryDragonAndTiger());
    // Collections.reverse(histroyDragon);
    ArrayList<Integer> hisbigSmall = new ArrayList<>(seat.getTable().getHisBigSmall());
    // Collections.reverse(hisbigSmall);
    msg.setHisWaybill(histroyWayBill);
    msg.setHisDragon(histroyDragon);
    msg.setHisBigSmall(hisbigSmall);
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 结算消息 .
   * 
   * @param seat 座位信息.
   */
  public void noticeBill(BaccaratSeat seat) {
    NoticeBillMsg msg = new NoticeBillMsg();
    // BaccaratSeat bankerSeat = seat.table.banker();
    msg.setBankerChips(seat.getTable().getEarn());
    msg.setPlayerChips(seat.getBillChips() - seat.totalBets());
    msgWriter.writeMsg(seat.getPlayerId(), msg);

  }

  /**
   * 如果满足条件发送庄家通吃通赔公告 .
   * 
   * @param playerName . 牌型 .
   * @param chips . 赢得筹码 .
   */
  public void noticeBankerNotice(String playerName, String roomName, int count, long chips) {
    for (GameNoticeDomainWrapper dom : gameNoticeDic.getGameDoms().get(Game.BACCARAT.getType())) {
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

  /**
   * 图标区域倍率 .
   * 
   * 
   * @param table .
   */
  public void noticeIconMulti(BaccaratTable table) {
    NoticeMultipleMsg msg = new NoticeMultipleMsg();
    for (int i = 0; i < table.getCbMultiple().length; i++) {
      IconMultiple icon = new IconMultiple();
      icon.setAreaId(i);
      icon.setRate((int) table.getCbMultiple()[i] * 100);
      msg.getIconMulti().add(icon);
    }
    sendTablePlayersMsg(table, msg);
  }

  /**
   * 图标区域倍率 .
   * 
   * 
   * @param seat 座位信息.
   */
  public void noticeIconMulti(BaccaratSeat seat) {
    NoticeMultipleMsg msg = new NoticeMultipleMsg();
    for (int i = 0; i < seat.getTable().getCbMultiple().length; i++) {
      IconMultiple icon = new IconMultiple();
      icon.setAreaId(i);
      icon.setRate((int) seat.getTable().getCbMultiple()[i] * 100);
      msg.getIconMulti().add(icon);
    }
    msgWriter.writeMsg(seat.getPlayerId(), msg);
  }

  /**
   * 开奖结果 .
   * 
   * 
   * @param table .
   */
  public void noticeRewardResult(BaccaratTable table) {
    NoticeResultMsg msg = new NoticeResultMsg();
    int ppoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer());
    int bpoint = CardsTypeGetter.getCardPoint(table.getBanker());
    int diff = Math.abs(ppoint - bpoint);
    List<Integer> li = Lists.newArrayList();
    for (BettingDecision val : table.getResult()) {
      if ((diff <= 3)
          && (val == BettingDecision.BANKER_DRAGON || val == BettingDecision.PLAYER_DRAGON)) {
        if (ppoint >= 8 || bpoint >= 8) {
          li.add(val.type);
        }
      } else {
        li.add(val.type);
      }
    }
    msg.getResult().addAll(li);
    sendTablePlayersMsg(table, msg);

  }

  /**
   * 发送玩家结果 .
   *
   * @author abin
   * @date 2018年8月22日 下午7:26:09
   * @param player 玩家.
   * @param table 桌子.
   */
  public void noticeRewardResultToPlayer(Player player, BaccaratTable table) {
    NoticeResultMsg msg = new NoticeResultMsg();
    int ppoint = CardsTypeGetter.getCardPoint(table.getNormalPlayer());
    int bpoint = CardsTypeGetter.getCardPoint(table.getBanker());
    int diff = Math.abs(ppoint - bpoint);
    List<Integer> li = Lists.newArrayList();
    for (BettingDecision val : table.getResult()) {
      if ((diff <= 3)
          && (val == BettingDecision.BANKER_DRAGON || val == BettingDecision.PLAYER_DRAGON)) {
        if (ppoint >= 8 || bpoint >= 8) {
          li.add(val.type);
        }
      } else {
        li.add(val.type);
      }
    }
    msg.getResult().addAll(li);
    msgWriter.writeMsg(player, msg);

  }

  /**
   * 切牌 .
   * 
   * @param table .
   */
  public void noticeWashCard(BaccaratTable table) {
    NoticeCutCardsMsg msg = new NoticeCutCardsMsg();
    List<BaccaratCard> cards = Lists.newArrayList();
    cards.addAll(table.getCards().subList(table.getCardIndex(),
        table.getCardIndex() + table.getCards().get(table.getCardIndex()).power + 1));
    table.removeCard(cards);
    for (BaccaratCard card : cards) {
      msg.getCardsInfo().add(card.ordinal());
    }
    sendTablePlayersMsg(table, msg);
  }

  /**
   * 返回游戏信息 .
   *
   * @author abin
   * @date 2018年4月23日 上午11:06:52
   * @param instanceId 实例id.
   * @param mems 房间列表.
   * @param bets 下注信息.
   * @param cardIndex 下注序号.
   * @return 游戏信息.
   */
  public ResMessage resGameInfo(int instanceId, List<MemInfo> mems, Map<Integer, Long> bets,
      int cardIndex) {
    ResGameInfoMsg gameInfoMsg = new ResGameInfoMsg();

    gameInfoMsg.setMems(mems);
    gameInfoMsg.setRoomInfo(getRoomInfoByInstanceId(instanceId));

    List<Long> betList = new ArrayList<>(12);

    for (BettingDecision decision : BettingDecision.values()) {
      betList.add(bets.getOrDefault(decision.type, 0L));
    }

    gameInfoMsg.setBetInfos(betList);

    gameInfoMsg.setCardIndex(cardIndex);

    return gameInfoMsg;
  }
}
