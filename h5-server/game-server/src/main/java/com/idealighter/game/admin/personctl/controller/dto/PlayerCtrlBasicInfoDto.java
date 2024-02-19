package com.idealighter.game.admin.personctl.controller.dto;

import com.idealighter.game.core.service.games.common.AbstractRoom;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;

import java.util.Map;

import lombok.Data;

@Data
public class PlayerCtrlBasicInfoDto {
  private Long playerId;
  private Long superId;
  private String nickName;
  private Integer gameId;
  private Integer roomId;
  private Long gold;
  private Long safeGold;
  private Long prizeTotal;

  /**
   * 构造函数 .
   * 
   * @param domain domain.
   */
  public PlayerCtrlBasicInfoDto(PlayerBo domain) {
    this.playerId = domain.getId();
    this.superId = domain.getSuperId();
    this.nickName = domain.getPlayerName();
    this.gold = domain.getGold().get();
    this.safeGold = domain.getSafeGold().get();

    Map<Integer, PlayerPrizePool> poolMap = domain.prizePools();
    if (poolMap != null && poolMap.size() > 0) {
      Long sum = 0L;
      for (PlayerPrizePool prizePool : poolMap.values()) {
        sum += prizePool.getPrize().get();
      }
      this.prizeTotal = sum;
    } else {
      this.prizeTotal = 0L;
    }

  }

  /**
   * 构造函数 .
   * 
   * @param player player.
   */
  public PlayerCtrlBasicInfoDto(Player player) {
    this(player.playerBo());
    AbstractRoom room = player.curRoom;
    if (room != null) {
      this.gameId = room.game().getType();
      this.roomId = room.getId();
    }
  }
}
