
package com.idealighter.game.core.service.login.manager;

import com.google.inject.Inject;

import com.idealighter.game.core.service.player.manager.PlayerMsgWriter;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.dictionary.dic.LevelExpDic;
import com.idealighter.game.dictionary.domain.LevelExpDomain;
import com.idealighter.game.login.dto.GameInfoDto;
import com.idealighter.game.login.dto.PlayerInfoDto;
import com.idealighter.game.login.message.ResLogoutForApplicationCloseMsg;
import com.idealighter.game.login.message.ResLogoutForGameExitMsg;
import com.idealighter.game.login.message.ResLogoutForLockedMsg;
import com.idealighter.game.login.message.ResLogoutForRepetitionMsg;
import com.idealighter.game.personalcenter.message.ResPlayerInfoMsg;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录消息管理 .
 * 
 * @date 2015年9月11日 上午11:46:40
 *
 */
@Slf4j
public class LoginMsgMgr {

  @Inject
  private PlayerMsgWriter msgWriter;

  @Inject
  private LevelExpDic levelExpDic;

  /**
   * 发送玩家信息 .
   * 
   * @param player 玩家.
   */
  public void sendPlayerInfo(Player player) {
    PlayerInfoDto playerInfo = new PlayerInfoDto();
    playerInfo.setId(player.getId());
    playerInfo.setUserName(player.getUserName());
    playerInfo.setNickName(player.getPlayerName());
    playerInfo.setSex(player.getSex());
    playerInfo.setAge(player.getAge());
    playerInfo.setBirthMonth(player.getBirthMonth());
    playerInfo.setBirthDay(player.getBirthDay());
    playerInfo.setProvince(player.getProvince());
    playerInfo.setCity(player.getCity());
    playerInfo.setAddr(player.getAddr());
    playerInfo.setSignature(player.getSignature());
    playerInfo.setFullName(player.getName());
    playerInfo.setIcon(player.getIcon());
    playerInfo.setIdCardNo(player.getIdcard());
    playerInfo.setLevel(player.getLevel());
    playerInfo.setGold(player.getGold());
    playerInfo.setCedit(player.getCedit());
    playerInfo.setIngot(player.getIngot());
    playerInfo.setLottery(player.getLottery());
    playerInfo.setSafeGold(player.getSafeGold());
    playerInfo.setVipLevel(player.getVipLevel());
    playerInfo.setVipDuration(player.vipDuration());
    playerInfo.setPhone(player.getPhone());
    playerInfo.setEmail(player.getEmail());
    playerInfo.setLoginPhoneVerify(player.isLoginPhoneVerify() ? 1 : 0);
    playerInfo.setPlayItemEffect(player.isPlayItemEffect() ? 1 : 0);
    playerInfo.setHavePwdProtect(player.getFirstPwdProtectA() != null ? 1 : 0);
    playerInfo.setTourist(player.isTourist() ? 1 : 0);
    playerInfo.setReLoginCode(player.getReLoginCode());
    playerInfo.setType(player.getType());
    playerInfo.setTransferReward(player.getTransferReward());
    playerInfo.setSuperId(player.getSuperId());
    playerInfo.setExp(player.getExp());

    LevelExpDomain expDomain = levelExpDic.map().get(player.getLevel() + 1);
    if (expDomain != null) {
      playerInfo.setNextLevelExp(expDomain.getExp());
    } else {
      expDomain = levelExpDic.map().get(levelExpDic.getMaxLevel());
      playerInfo.setNextLevelExp(expDomain.getExp());
    }

    GameInfoDto gameInfoDto = null;
    if (player.curRoom != null) {

      if (player.curTable != null) {
        log.info("用户游戏{} 还在  房间[{}] 桌子[{}] ", player.curRoom.game(), player.curRoom.getId(),
            player.curTable.getId());
      }

      gameInfoDto = new GameInfoDto();
      gameInfoDto.setGameType(player.curRoom.game().getType());
    }
    ResPlayerInfoMsg msg = new ResPlayerInfoMsg();
    msg.setPlayerInfo(playerInfo);
    msg.setGameInfo(gameInfoDto);
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 同一个账号重复登录被T号退出.
   * 
   * @param player 玩家.
   */
  public void sendLogoutForRepetitionMsg(Player player) {
    ResLogoutForRepetitionMsg msg = new ResLogoutForRepetitionMsg();
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 账号锁定退出.
   * 
   * @param player 玩家.
   */
  public void sendLogoutForLockedMsg(Player player) {
    ResLogoutForLockedMsg msg = new ResLogoutForLockedMsg();
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 游戏退出，踢出玩家.
   * 
   * @param player 玩家.
   */
  public void sendLogoutForGameExitMsg(Player player) {
    ResLogoutForGameExitMsg msg = new ResLogoutForGameExitMsg();

    msgWriter.writeMsg(player, msg);
  }

  /**
   * 停服退出消息.
   * 
   * @param player 玩家.
   */
  public void sendLogoutForApplicationCloseMsg(Player player) {
    ResLogoutForApplicationCloseMsg msg = new ResLogoutForApplicationCloseMsg();
    msgWriter.writeMsg(player, msg);
  }

  /**
   * 发送游戏开始消息 .
   * 
   * @param player 玩家.
   */
  // public void sendGameStartMsg(Player player) {
  // ResGameStartMsg msg = new ResGameStartMsg();
  // msgWriter.writeMsg(player, msg);
  // }
}
