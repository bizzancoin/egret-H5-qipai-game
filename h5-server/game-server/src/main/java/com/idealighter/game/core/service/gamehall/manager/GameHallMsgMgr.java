
package com.idealighter.game.core.service.gamehall.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.common.Game;
import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.games.common.AbstractSeat;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.domain.GamesDomain;
import com.idealighter.game.gamehall.dto.GameDto;
import com.idealighter.game.gamehall.dto.MemInfo;
import com.idealighter.game.gamehall.message.NoticeGameCloseMsg;
import com.idealighter.game.gamehall.message.NoticeGameStartMsg;
import com.idealighter.game.gamehall.message.ResGameMsg;
import com.idealighter.game.gamehall.message.ResMemInfoUpdateMsg;
import com.idealighter.game.gamehall.message.ResRemoveMemInfoMsg;
import com.idealighter.game.gamehall.message.ResSeatInfoUpdateMsg;
import com.idealighter.game.login.message.ResTouristUpdateMsg;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏大厅消息管理 .
 * 
 * @date 2015年11月14日 上午11:58:51
 *
 */
@Singleton
public class GameHallMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;
  @Inject
  private PlayerMgr playerMgr;

  /**
   * 发送座位信息变化消息,pc用户才发送座位信息变化 .
   * 
   * @param seat 座位信息.
   */
  void noticeSeatInfoUpdateMsg(AbstractSeat seat) {
    ResSeatInfoUpdateMsg msg = new ResSeatInfoUpdateMsg();
    msg.setSeat(seat.seatInfo());

    for (Long romPlayerId : seat.table().room().getPlayers()) {
      Player player = playerMgr.getPlayer(romPlayerId);
      if (player != null && !player.fromPhone) { // pc用户才发送座位信息变化
        msgWriter.writeMsg(player, msg);
      }

    }
  }

  /**
   * 发送房间成员信息变化消息,pc用户才发送成员信息变化 .
   * 
   * @param player 玩家.
   */
  void noticeMemInfoUpdateMsg(AbstractRoom room, long playerId) {
    MemInfo memInfo = null;
    if (room != null && (memInfo = room.memInfo(playerId)) != null) {
      ResMemInfoUpdateMsg msg = new ResMemInfoUpdateMsg();
      msg.setMember(memInfo);

      for (Long romPlayerId : room.getPlayers()) {
        Player player = playerMgr.getPlayer(romPlayerId);
        // if (player != null && !player.fromPhone) { // pc用户才发送成员信息变化
        // msgWriter.writeMsg(player, msg);
        // }
        if (player != null && player.isRobot()) { // 机器人才收到成员信息变化
          msgWriter.writeMsg(player, msg);
        }
      }

      if (memInfo.getTableId() > 0) {
        noticeSeatInfoUpdateMsg(room.seat(memInfo.getPlayerId()));
      }
    }
  }

  /**
   * 发送移除房间成员消息 .
   * 
   * @param playerId 玩家id.
   * @param room .
   */
  public void noticeRemoveMemInfo(long playerId, AbstractRoom room) {
    ResRemoveMemInfoMsg msg = new ResRemoveMemInfoMsg();
    msg.setPlayerId(playerId);
    sendRoomMsg(room, msg);
  }


  /**
   * 向房间中成员发送消息 .
   * 
   * @param room .
   * @param msg .
   */
  private void sendRoomMsg(AbstractRoom room, ResMessage msg) {
    for (Long playerId : room.getPlayers()) {
      msgWriter.writeMsg(playerId, msg);
    }
  }

  /**
   * 发送游客账号升级结果消息 .
   * 
   * @param player 玩家.
   */
  public ResTouristUpdateMsg sendTouristUpdateMsg(Player player) {
    ResTouristUpdateMsg msg = new ResTouristUpdateMsg();
    msg.setPlayerName(player.getPlayerName());
    msg.setPhone(player.getPhone());
    return msg;
  }

  /**
   * 返回激活的游戏列表 .
   *
   * @author abin
   * @date 2018年5月18日 上午9:58:15
   * @param gamesDomains 游戏列表.
   * @return 游戏结果.
   */
  public ResMessage resGameList(List<GamesDomain> gamesDomains) {
    ResGameMsg resGameMsg = new ResGameMsg();

    List<GameDto> games = new ArrayList<>();
    if (EmptyUtil.listIsNotEmpty(gamesDomains)) {
      for (GamesDomain domain : gamesDomains) {
        if (domain.getActive()) {
          GameDto dto = new GameDto();
          dto.setGameId(domain.getId());
          dto.setName(domain.getName());
          games.add(dto);
        }
      }
    }
    resGameMsg.setGames(games);
    return resGameMsg;
  }

  /**
   * 游戏开启.
   *
   * @author abin
   * @date 2018年5月18日 上午10:04:50
   * @param game 游戏.
   */
  public void noticeGameStart(Game game) {
    NoticeGameStartMsg msg = new NoticeGameStartMsg();
    msg.setGameId(game.getType());
    msgWriter.writeWorld(msg);
  }

  /**
   * 游戏关闭 .
   *
   * @author abin
   * @date 2018年5月18日 上午10:05:03
   * @param game 游戏.
   */
  public void noticeGameClose(Game game) {
    NoticeGameCloseMsg msg = new NoticeGameCloseMsg();
    msg.setGameId(game.getType());
    msgWriter.writeWorld(msg);
  }
}
