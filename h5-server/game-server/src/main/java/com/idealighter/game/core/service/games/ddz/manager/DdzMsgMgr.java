package com.idealighter.game.core.service.games.ddz.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.games.common.AbstractTable;
import com.idealighter.game.core.service.games.ddz.struct.DdzAiSeat;
import com.idealighter.game.core.service.games.ddz.struct.DdzCard;
import com.idealighter.game.core.service.games.ddz.struct.DdzCardsType;
import com.idealighter.game.core.service.games.ddz.struct.DdzRoom;
import com.idealighter.game.core.service.games.ddz.struct.DdzSeat;
import com.idealighter.game.core.service.games.ddz.struct.DdzTable;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.DdzRoomDic;
import com.idealighter.game.dictionary.dic.DdzRoomTypeDic;
import com.idealighter.game.dictionary.dic.DdzTimeDic;
import com.idealighter.game.dictionary.domain.DdzRoomDomain;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.games.ddz.constant.DdzStage;
import com.idealighter.game.games.ddz.constant.DdzTime;
import com.idealighter.game.games.ddz.dto.BillInfo;
import com.idealighter.game.games.ddz.dto.GameInfo;
import com.idealighter.game.games.ddz.dto.GameInfo.CallDoubleItem;
import com.idealighter.game.games.ddz.dto.GameInfo.CallTypeItem;
import com.idealighter.game.games.ddz.dto.GameInfo.CardNumItem;
import com.idealighter.game.games.ddz.dto.GameInfo.ReadyItem;
import com.idealighter.game.games.ddz.dto.GameInfo.TrusteeItem;
import com.idealighter.game.games.ddz.dto.RoomInfo;
import com.idealighter.game.games.ddz.dto.RoomTypeDetailInfo;
import com.idealighter.game.games.ddz.message.ResAbandonMsg;
import com.idealighter.game.games.ddz.message.ResCallCardMsg;
import com.idealighter.game.games.ddz.message.ResChipsChangeMsg;
import com.idealighter.game.games.ddz.message.ResDealCardsMsg;
import com.idealighter.game.games.ddz.message.ResDoubleMsg;
import com.idealighter.game.games.ddz.message.ResDoubleOverMsg;
import com.idealighter.game.games.ddz.message.ResEnterGameHallMsg;
import com.idealighter.game.games.ddz.message.ResEnterRoomMsg;
import com.idealighter.game.games.ddz.message.ResEnterTableMsg;
import com.idealighter.game.games.ddz.message.ResExitRoomMsg;
import com.idealighter.game.games.ddz.message.ResExitTableMsg;
import com.idealighter.game.games.ddz.message.ResGameInfoMsg;
import com.idealighter.game.games.ddz.message.ResGameOverMsg;
import com.idealighter.game.games.ddz.message.ResGameStartMsg;
import com.idealighter.game.games.ddz.message.ResHiddenCardsMsg;
import com.idealighter.game.games.ddz.message.ResMultipleMsg;
import com.idealighter.game.games.ddz.message.ResOtherEnterTableMsg;
import com.idealighter.game.games.ddz.message.ResPlayCardsMsg;
import com.idealighter.game.games.ddz.message.ResPromptMsg;
import com.idealighter.game.games.ddz.message.ResReadyMsg;
import com.idealighter.game.games.ddz.message.ResReadyStageMsg;
import com.idealighter.game.games.ddz.message.ResShoutMsg;
import com.idealighter.game.games.ddz.message.ResTrusteeMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 斗地主消息管理工具类 .
 * 
 * @date 2015年8月8日 下午6:50:55
 *
 */
@Singleton
public class DdzMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;
  @Inject
  private DdzDataMgr dataMgr;
  @Inject
  private DdzRoomDic roomDic;
  @Inject
  private DdzRoomTypeDic roomTypeDic;
  @Inject
  private DdzTimeDic timeDic;

  /**
   * 发送进入游戏大厅消息 .
   */
  public ResMessage sendEnterGameHallMsg() {
    ResEnterGameHallMsg msg = new ResEnterGameHallMsg();

    Collection<DdzRoom> rooms = dataMgr.allRooms();
    for (DdzRoom room : rooms) {
      DdzRoomDomain roomDom = roomDic.get(room.getId());
      RoomTypeDetailInfo roomTypeDetailInfo = new RoomTypeDetailInfo();
      if (roomDom.getIsActive() != 1) {
        continue;
      }
      roomTypeDetailInfo.setType(roomDom.getType());
      roomTypeDetailInfo.setTypeName(roomTypeDic.map().get(roomDom.getType()).getName());

      RoomInfo roomInfo = toRoomInfo(room);
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

  private RoomInfo toRoomInfo(DdzRoom room) {
    RoomInfo roomInfo = new RoomInfo();
    DdzRoomDomain roomDom = room.getRoomDomain();
    roomInfo.setRoomId(room.getId());
    roomInfo.setName(roomDom.getName());
    roomInfo.setType(roomDom.getType());
    roomInfo.setMaxNum(roomDom.getMaxNum());
    roomInfo.setLower(roomDom.getLower());
    roomInfo.setUpper(roomDom.getUpper());
    roomInfo.setProportionGold(roomDom.getProportionGold());
    roomInfo.setProportionChips(roomDom.getProportionChips());
    roomInfo.setTable(roomDom.getTableNum());
    roomInfo.setBaseBet(roomDom.getBase());
    roomInfo.setAfee(roomDom.getAfee());
    roomInfo.setInType(roomDom.getInType());
    roomInfo.setPlayerNum(room.getPlayers().size());
    return roomInfo;
  }

  /**
   * @Title sendExitRoom.
   * @Description 退出房间通知
   * @author houdongsheng
   * @date 2018年8月22日 下午5:03:56
   * @param player .
   */
  public void sendExitRoom(Player player) {
    ResExitRoomMsg msg = new ResExitRoomMsg();
    msgWriter.writeMsg(player, msg);
  }

  /**
   * @Title sendReadyMsg.
   * @Description 发送准备消息
   * @author houdongsheng
   * @date 2018年8月22日 下午4:20:18
   * @param seat .
   */
  public void sendReadyMsg(DdzAiSeat seat) {
    ResReadyMsg msg = new ResReadyMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    sendTableMsg(seat.getTable(), msg);
  }

  /**
   * @Title sendReadyStageMsg.
   * @Description 发送准备阶段消息
   * @author houdongsheng
   * @date 2018年8月22日 上午9:38:28
   * @param table .
   * @param time 阶段时间.
   */
  public void sendReadyStageMsg(DdzTable table, int time) {
    ResReadyStageMsg msg = new ResReadyStageMsg();
    msg.setReadyTime(time);
    sendTableMsg(table, msg);
  }

  /**
   * @Title sendGameLastInfo.
   * @Description 发送游戏最新信息
   * @author houdongsheng
   * @date 2018年8月22日 上午9:39:05
   * @param seat .
   * @return .
   */
  public ResMessage sendGameLastInfo(DdzAiSeat seat) {
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

  private GameInfo toGameInfo(DdzAiSeat mySeat) {
    DdzTable table = mySeat.getTable();

    int stageTime = 0;
    ScheduledFuture<?> stepFuture = null;
    if (table.getStatus().equals(DdzStage.GAME_OVER)
        || table.getStatus().equals(DdzStage.DEAL_CARDS)) {
      stepFuture = table.stepFuture;
    } else {
      stepFuture = mySeat.stepFuture;
    }
    stageTime = stepFuture != null ? (int) stepFuture.getDelay(TimeUnit.SECONDS) : 0;

    // 准备列表
    List<ReadyItem> readyList = new ArrayList<ReadyItem>();
    // 叫牌列表
    List<CallTypeItem> callTypeList = new ArrayList<CallTypeItem>();
    // 下一个叫牌的玩家
    long nextCallPlayerId = 0L;
    // 加倍列表
    List<CallDoubleItem> callDoubleList = new ArrayList<CallDoubleItem>();
    // 地主编号
    Long landlord = 0L;
    List<Integer> hiddenCards = new ArrayList<Integer>();
    // 上一个出牌玩家编号
    long prePlayerId = 0;
    // 上一个出的牌类型
    long prePlayerCardTypeId = 0;
    // 上一个出的牌
    List<Integer> prePlayerCards = new ArrayList<Integer>();
    // 下一个出牌玩家编号
    long nextPlayerId = 0L;
    // 结算信息
    List<BillInfo> billInfos = new ArrayList<>();
    // 托管信息
    List<TrusteeItem> trusteeList = new ArrayList<TrusteeItem>();
    // 牌数量
    List<CardNumItem> cardNumList = new ArrayList<CardNumItem>();
    // 目前牌的倍数
    int multiple = 0;
    multiple = table.getMultiple();

    DdzAiSeat landloardSeat = table.landlord();
    if (landloardSeat != null) {
      landlord = landloardSeat.getPlayerId();
    }

    int landLoardMultiple = 0;
    for (DdzAiSeat seat : table.gamingSeats()) {
      if (seat.getPlayerId() > 0) {
        // 准备情况
        ReadyItem readyItem = new ReadyItem();
        readyItem.setOrder(seat.getOrder());
        readyItem.setPlayerId(seat.getPlayerId());
        readyItem.setReady(seat.readied() ? 1 : 0);
        readyList.add(readyItem);
        // 叫牌情况
        if (seat.getCallType() != null) {
          CallTypeItem callTypeItem = new CallTypeItem();
          callTypeItem.setOrder(seat.getOrder());
          callTypeItem.setPlayerId(seat.getPlayerId());
          callTypeItem.setCallType(seat.getCallType().type);
          callTypeList.add(callTypeItem);
        }
        // 非地主
        if (!seat.isLandlord()) {
          if (table.getSpring() != 0) {
            multiple *= 2;
          }
          Boolean tmpDouble = null;
          if (landloardSeat != null) {
            tmpDouble = landloardSeat.getDoubled();
            if (tmpDouble != null && tmpDouble) {
              multiple *= 2;
            }
          }
          tmpDouble = mySeat.getDoubled();
          if (tmpDouble != null && tmpDouble) {
            multiple *= 2;
          }
        }
        landLoardMultiple += multiple;
        // 加倍情况
        if (seat.getDoubled() != null) {
          CallDoubleItem doubleItem = new CallDoubleItem();
          doubleItem.setOrder(seat.getOrder());
          doubleItem.setPlayerId(seat.getPlayerId());
          doubleItem.setDoubleCount(seat.getDoubled() ? 1 : 0);
          callDoubleList.add(doubleItem);
        }
        if (table.getStatus().equals(DdzStage.GAME_OVER)) {
          // 结算信息
          BillInfo billInfo = new BillInfo();
          billInfo.setChips(seat.getBillChips());
          billInfo.setOrder(seat.getOrder());
          billInfo.setPlayerId(seat.getPlayerId());
          billInfo.setPlayerName(seat.getPlayerName());
          billInfos.add(billInfo);
        }
        TrusteeItem trustee = new TrusteeItem();
        trustee.setOrder(seat.getOrder());
        trustee.setPlayerId(seat.getPlayerId());
        trustee.setTrustee(seat.isSysHost() ? 1 : 0);
        trusteeList.add(trustee);

        CardNumItem cardNum = new CardNumItem();
        cardNum.setOrder(seat.getOrder());
        cardNum.setPlayerId(seat.getPlayerId());
        cardNum.setNum(seat.getCards().size());
        cardNumList.add(cardNum);
      }
    }

    // 如果我是地主,倍数为农名的总倍数
    if (mySeat.isLandlord()) {
      multiple = landLoardMultiple;
    }

    // 我自己的牌
    List<Integer> mySelfCards = new ArrayList<>();
    mySeat.getCards().forEach((card) -> {
      mySelfCards.add(card.ordinal());
    });

    // 下一个叫牌的玩家
    DdzAiSeat nextCallSeat = table.getNextCallSeat();
    if (nextCallSeat != null) {
      nextCallPlayerId = table.getNextCallSeat().getPlayerId();
    }

    // 上一个出牌玩家
    if (table.getPrePlaySeat() != null) {
      prePlayerId = table.getPrePlaySeat().getPlayerId();
    }
    // 上一个出牌牌型
    if (table.getPrePlayCardsType() != null) {
      prePlayerCardTypeId = table.getPrePlayCardsType().type;
    }
    // 上一个出的牌
    if (table.getPrePlayCards() != null) {
      table.getPrePlayCards().forEach((card) -> {
        prePlayerCards.add(card.ordinal());
      });
    }
    DdzAiSeat nextPlayerSeat = table.getNextPlaySeat();
    if (nextPlayerSeat != null) {
      nextPlayerId = nextPlayerSeat.getPlayerId();
    }

    // 如果庄家确定,才发送底下三张牌
    if (landlord != 0L) {
      // 底牌
      List<DdzCard> hiddens = table.hiddenCards();
      hiddens.forEach((card) -> {
        hiddenCards.add(card.ordinal());
      });
    }

    GameInfo result = new GameInfo();
    // 游戏阶段
    result.setStage(table.getStatus().val);
    // 阶段时间
    result.setStageTime(stageTime);
    result.setSpring(table.getSpring());
    result.setReadyList(readyList);
    result.setMyCards(mySelfCards);
    result.setCardNumList(cardNumList);
    result.setCallTypeList(callTypeList);
    result.setNextCallPlayerId(nextCallPlayerId);
    result.setLandlord(landlord);
    result.setCallDoubleList(callDoubleList);
    result.setHiddenCards(hiddenCards);
    result.setMultiple(multiple);
    result.setPrePlayerId(prePlayerId);
    result.setPrePlayCardTypeId(prePlayerCardTypeId);
    result.setPrePlayCards(prePlayerCards);
    result.setNextPlayerId(nextPlayerId);
    result.setBillInfos(billInfos);
    result.setTrusteeList(trusteeList);

    return result;
  }

  /**
   * 发送进入房间消息 .
   * 
   * @param room .
   */
  public ResMessage sendEnterRoomMsg(DdzRoom room) {
    ResEnterRoomMsg msg = new ResEnterRoomMsg();
    for (AbstractTable table : room.tables()) {
      msg.getTables().add(table.tableInfo());
    }
    msg.setMembers(room.memInfos());
    return msg;
  }

  /**
   * 发送进入桌子消息 .
   * 
   */
  public void sendEnterTableMsg(Player player, AbstractTable table, long totalChips, int readyTime) {
    ResEnterTableMsg msg = new ResEnterTableMsg();
    AbstractRoom room = table.room();
    List<MemInfo> mems = new ArrayList<>();
    for (Long playerId : table.players()) {
      mems.add(room.memInfo(playerId));
    }
    msg.setMems(mems);
    msg.setTotalChips(totalChips);
    msg.setReadyTime(readyTime);
    msgWriter.writeMsg(player, msg);
  }

  /**
   * @Title sendOtherEnterTableMsg.
   * @Description 其他人进入桌子
   * @author houdongsheng
   * @date 2018年8月22日 上午9:35:34
   * @param seat .
   */
  public void sendOtherEnterTableMsg(DdzAiSeat seat) {
    ResOtherEnterTableMsg msg = new ResOtherEnterTableMsg();
    msg.setMem(seat.getTable().getRoom().memInfo(seat.getPlayerId()));
    for (Long playerId : seat.getTable().players()) {
      if (playerId != seat.getPlayerId()) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }

  /**
   * @Title sendSysHostChangeMsg.
   * @Description 发送托管信息
   * @date 2018年8月22日 上午9:34:31
   * @param seat 玩家座位
   */
  public void sendSysHostChangeMsg(DdzSeat seat) {
    ResTrusteeMsg msg = new ResTrusteeMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    msg.setTrustee(seat.isSysHost() ? 1 : 0);
    sendTableMsg(seat.table(), msg);
  }

  /**
   * 向同桌的玩家发送叫牌结果消息 .
   * 
   * @param seat 座位信息. 叫牌的座位 .
   * @param callOver . 叫牌是否结束 .
   */
  public void sendCallCardMsg(DdzSeat seat, boolean callOver) {
    ResCallCardMsg msg = new ResCallCardMsg();
    msg.setPlayerId(seat.getPlayerId());

    DdzSeat landlord = seat.table().landlord();
    msg.setLandlord(callOver && landlord != null ? landlord.getPlayerId() : 0);
    msg.setLandlordOrder(callOver && landlord != null ? landlord.getOrder() : 0);
    msg.setOrder(seat.getOrder());
    msg.setType(seat.getCallType().type);

    NextOptionInfo nextOptionInfo = nextCallInfo(seat.getTable());

    msg.setNextCallOrder(nextOptionInfo.nextOrder);
    msg.setNextCallPlayer(nextOptionInfo.nextPlayerId);
    msg.setNextCallTimeLimit(nextOptionInfo.nextTimeLimit);

    sendTableMsg(seat.table(), msg);
  }

  private NextOptionInfo nextCallInfo(DdzTable table) {
    DdzAiSeat nextCallSeat = table.getNextCallSeat();
    int nextCallOrder = -1;
    long nextCallPlayerId = -1;
    int nextCallLimit = -1;
    if (nextCallSeat != null) {
      nextCallOrder = nextCallSeat.getOrder();
      nextCallPlayerId = nextCallSeat.getPlayerId();
      nextCallLimit = timeDic.get(DdzTime.CALL_DZ.val).getTime();
    }
    return new NextOptionInfo(nextCallOrder, nextCallPlayerId, nextCallLimit);
  }

  private NextOptionInfo nextPlayerInfo(DdzTable table) {
    DdzAiSeat nextCallSeat = table.getNextPlaySeat();
    int nextCallOrder = -1;
    long nextCallPlayerId = -1;
    int nextCallLimit = -1;
    if (nextCallSeat != null) {
      nextCallOrder = nextCallSeat.getOrder();
      nextCallPlayerId = nextCallSeat.getPlayerId();
      nextCallLimit = timeDic.get(DdzTime.PLAY_CARD.val).getTime();
    }
    return new NextOptionInfo(nextCallOrder, nextCallPlayerId, nextCallLimit);
  }

  @AllArgsConstructor
  public static class NextOptionInfo {
    public int nextOrder;
    public long nextPlayerId;
    public int nextTimeLimit;
  }

  /**
   * 向同桌的玩发送该牌桌的底牌消息 .
   * 
   * @param table .
   */
  public void sendHiddenCardsMsg(DdzTable table) {
    // 底牌
    List<DdzCard> hiddenCards = table.hiddenCards();
    List<Integer> hiddenCardIds = new ArrayList<>(hiddenCards.size());
    for (DdzCard card : hiddenCards) {
      hiddenCardIds.add(card.ordinal());
    }

    int multipleTime = timeDic.get(DdzTime.CALL_MULTIPLE.val).getTime();
    for (DdzSeat seat : table.gamingSeats()) {
      ResHiddenCardsMsg msg = new ResHiddenCardsMsg();
      msg.getCards().addAll(hiddenCardIds);
      msg.setMultipleTime(multipleTime);
      msgWriter.writeMsg(seat.getPlayerId(), msg);
    }
  }

  /**
   * 向同桌的每个玩家发送自己的牌消息 .
   * 
   * @param table .
   */
  public void sendDealCardsMsg(DdzTable table) {
    for (DdzSeat seat : table.gamingSeats()) {
      ResDealCardsMsg msg = new ResDealCardsMsg();
      for (DdzCard card : seat.getCards()) {
        msg.getCards().add(card.ordinal());
      }
      msgWriter.writeMsg(seat.getPlayerId(), msg);
    }
  }

  /**
   * 向同桌的玩家发送出的牌 .
   * 
   * @param table .
   * @param seat 座位信息. 出牌的玩家 .
   * @param cards .
   */
  public void sendPlayCardsMsg(DdzTable table, DdzSeat seat, List<DdzCard> cards,
      DdzCardsType cardsType) {
    // 下一个出牌玩家
    NextOptionInfo nextOptionInfo = nextPlayerInfo(table);

    ResPlayCardsMsg msg = new ResPlayCardsMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setCards(cards.stream().map((card) -> card.ordinal()).collect(Collectors.toList()));
    msg.setCardsType(cardsType.type);
    msg.setNextPlayOrder(nextOptionInfo.nextOrder);
    msg.setNextPlayPlayerId(nextOptionInfo.nextPlayerId);
    msg.setNextPlayTimeLimit(nextOptionInfo.nextTimeLimit);
    msg.setOrder(seat.getOrder());
    sendTableMsg(table, msg);
  }

  /**
   * 向同桌的玩家发送放弃出牌消息 .
   * 
   */
  public void sendAbandonMsg(DdzAiSeat seat) {
    // 下一个出牌玩家
    DdzTable table = seat.getTable();
    NextOptionInfo nextOptionInfo = nextPlayerInfo(table);

    ResAbandonMsg msg = new ResAbandonMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    msg.setNextPlayOrder(nextOptionInfo.nextOrder);
    msg.setNextPlayPlayerId(nextOptionInfo.nextPlayerId);
    msg.setNextPlayTimeLimit(nextOptionInfo.nextTimeLimit);
    sendTableMsg(table, msg);
  }

  /**
   * 向同桌的玩家发送游戏结束消息 .
   * 
   * @param table .
   * @param landlord .
   * @param landlordCedits .
   * @param farmer1 .
   * @param farmer1Cedits .
   * @param farmer2 .
   * @param farmer2Cedits .
   */
  public void sendGameOverMsg(DdzTable table, int stageTime, DdzSeat landlord, long landlordCedits,
      DdzSeat farmer1, long farmer1Cedits, DdzSeat farmer2, long farmer2Cedits) {

    BillInfo landlordBill = new BillInfo();
    landlordBill.setPlayerId(landlord.getPlayerId());
    landlordBill.setPlayerName(landlord.getPlayerName());
    landlordBill.setOrder(landlord.getOrder());
    landlordBill.setChips(landlordCedits);

    BillInfo farmer1BIll = new BillInfo();
    farmer1BIll.setPlayerId(farmer1.getPlayerId());
    farmer1BIll.setPlayerName(farmer1.getPlayerName());
    farmer1BIll.setOrder(farmer1.getOrder());
    farmer1BIll.setChips(farmer1Cedits);

    BillInfo farmer2BIll = new BillInfo();
    farmer2BIll.setPlayerId(farmer2.getPlayerId());
    farmer2BIll.setPlayerName(farmer2.getPlayerName());
    farmer2BIll.setOrder(farmer2.getOrder());
    farmer2BIll.setChips(farmer2Cedits);

    ResGameOverMsg msg = new ResGameOverMsg();
    msg.getBills().add(landlordBill);
    msg.getBills().add(farmer1BIll);
    msg.getBills().add(farmer2BIll);
    msg.setSpring(table.getSpring());
    msg.setStageTime(stageTime);
    sendTableMsg(table, msg);
  }

  /**
   * @Title sendMultipleMsg.
   * @Description 发送总倍数
   * @author houdongsheng
   * @date 2018年8月29日 上午11:03:31
   * @param table .
   */
  public void sendMultipleMsg(DdzTable table) {
    DdzAiSeat landLord = table.landlord();
    int totalMultiple = 0; // 地主总倍数
    for (DdzAiSeat seat : table.gamingSeats()) {
      if (!seat.isLandlord()) {

        int multiple = table.getMultiple();
        // 春天倍数
        if (table.getSpring() != 0) {
          multiple *= 2;
        }
        Boolean tmpDouble = null;
        if (landLord != null) {
          tmpDouble = landLord.getDoubled();
          if (tmpDouble != null && tmpDouble) {
            multiple *= 2;
          }
        }
        tmpDouble = seat.getDoubled();
        if (tmpDouble != null && tmpDouble) {
          multiple *= 2;
        }
        totalMultiple += multiple;

        ResMultipleMsg msg = new ResMultipleMsg();
        msg.setMultiple(multiple);
        msgWriter.writeMsg(seat.getPlayerId(), msg);
      }
    }
    if (landLord != null) {
      ResMultipleMsg msg = new ResMultipleMsg();
      msg.setMultiple(totalMultiple);
      msgWriter.writeMsg(landLord.getPlayerId(), msg);
    }
  }

  /**
   * 发送筹码变化.
   * 
   * @author houdongsheng
   * @date 2018年8月22日 上午9:42:26
   * @param seat .
   */
  public void sendChipsChangeMsg(DdzSeat seat) {
    ResChipsChangeMsg msg = new ResChipsChangeMsg();
    msg.setChips(seat.getTotalChips());
    msg.setPlayerId(seat.getPlayerId());
    msg.setOrder(seat.getOrder());
    sendTableMsg(seat.getTable(), msg);
  }

  /**
   * 发送提示的牌信息 .
   * 
   * @param player 玩家.
   * @param cards .
   */
  public void sendPromptMsg(Player player, List<DdzCard> cards) {
    ResPromptMsg msg = new ResPromptMsg();
    if (cards != null) {
      msg.setCards(cards.stream().map((card) -> card.ordinal()).collect(Collectors.toList()));
    }

    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送玩家加倍消息 .
   * 
   * @param seat 座位信息.
   */
  public void sendDoubleMsg(DdzSeat seat, boolean doubled) {
    ResDoubleMsg msg = new ResDoubleMsg();
    msg.setPlayerId(seat.getPlayerId());
    msg.setDoubled((byte) (doubled ? 1 : 0));
    msg.setOrder(seat.getOrder());
    sendTableMsg(seat.getTable(), msg);
  }

  /**
   * @Title sendDoubleOverMsg.
   * @Description 加倍完成
   * @author houdongsheng
   * @date 2018年8月22日 上午9:42:44
   * @param table .
   */
  public void sendDoubleOverMsg(DdzTable table) {
    // 下一个出牌玩家
    NextOptionInfo nextOptionInfo = nextPlayerInfo(table);
    ResDoubleOverMsg msg = new ResDoubleOverMsg();

    msg.setNextPlayOrder(nextOptionInfo.nextOrder);
    msg.setNextPlayPlayerId(nextOptionInfo.nextPlayerId);
    msg.setNextPlayTimeLimit(nextOptionInfo.nextTimeLimit);
    sendTableMsg(table, msg);
  }

  /**
   * 发送游戏开始消息 .
   * 
   * @param table .
   */
  public void sendGameStartMsg(DdzTable table) {
    ResGameStartMsg msg = new ResGameStartMsg();
    // 下一个叫牌玩家信息
    NextOptionInfo nextOptionInfo = nextCallInfo(table);

    msg.setFirstCallOrder(nextOptionInfo.nextOrder);
    msg.setFirstCallPlayerId(nextOptionInfo.nextPlayerId);
    msg.setFirstCallTimeLimit(nextOptionInfo.nextTimeLimit);
    sendTableMsg(table, msg);
  }

  /**
   * 发送喊话消息 .
   * 
   * @param seat 座位信息.
   * @param type . 1:嘲讽,2:催牌,3:赞扬 .
   */
  public void sendShoutMs(DdzSeat seat, int type) {
    ResShoutMsg msg = new ResShoutMsg();
    msg.setOrder(seat.getOrder());
    msg.setType(type);
    msg.setPlayerId(seat.getPlayerId());
    sendTableMsg(seat.getTable(), msg);
  }

  /**
   * @Title sendExitTableMsg.
   * @Description 退出桌子
   * @author houdongsheng
   * @date 2018年8月22日 上午9:42:57
   * @param seat .
   */
  public void sendExitTableMsg(DdzAiSeat seat) {
    ResExitTableMsg msg = new ResExitTableMsg();
    msg.setOrder(seat.getOrder());
    msg.setPlayerId(seat.getPlayerId());
    sendTableMsg(seat.getTable(), msg);
  }

  /**
   * 向同桌的玩家(包括自己)发送消息 .
   * 
   * @param table .
   * @param msg .
   */
  public void sendTableMsg(DdzTable table, ResMessage msg) {
    List<? extends AbstractSeat> seats = table.seats();
    for (int i = 0; i < seats.size(); i++) {
      AbstractSeat seat = seats.get(i);
      long playerId = seat.getPlayerId();
      if (playerId > 0) {
        msgWriter.writeMsg(playerId, msg);
      }
    }
  }
}
