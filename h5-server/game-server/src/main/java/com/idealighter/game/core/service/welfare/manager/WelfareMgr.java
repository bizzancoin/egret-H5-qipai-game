
package com.idealighter.game.core.service.welfare.manager;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.constant.Operator;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.CommonMsgMgr;
import com.idealighter.game.core.service.event.manager.EventMgr;
import com.idealighter.game.core.service.event.struct.PlayerLoginEvent;
import com.idealighter.game.core.service.log.core.LogReason;
import com.idealighter.game.core.service.log.struct.welfare.ReceiveBenefitsLog;
import com.idealighter.game.core.service.log.struct.welfare.RegisterGiftLog;
import com.idealighter.game.core.service.log.struct.welfare.ShareLog;
import com.idealighter.game.core.service.log.struct.welfare.SignInLog;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.service.welfare.struct.WelfareData;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.dictionary.dic.WelfareDic;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.game.schedule.job.WelfareJob;
import com.idealighter.game.schedule.manager.JobRunTimeMgr;
import com.idealighter.game.schedule.struct.JobRunTime;
import com.idealighter.utils.time.TimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 福利管理 .
 * 
 * @date 2015年12月9日 上午10:52:23
 *
 */
@Singleton
public class WelfareMgr {

  private static final Logger LOG = LoggerFactory.getLogger(WelfareMgr.class);

  @Inject
  private JobRunTimeMgr jobRunTimeMgr;
  @Inject
  private WelfareMsgMgr msgMgr;
  @Inject
  private PlayerMgr playerMgr;
  @Inject
  private WelfareDataMgr dataMgr;
  @Inject
  private CommonMsgMgr commonMsgMgr;
  @Inject
  private WelfareDic welfareDic;

  @Inject
  public WelfareMgr(EventMgr eventMgr) {
    eventMgr.register(this);
  }

  /**
   * 登录事件处理 .
   * 
   * @param event .
   */
  @Subscribe
  public void onLogin(PlayerLoginEvent event) {
    Player player = event.player;
    WelfareData welfareData = player.welfareData();
    if (welfareData.getReceiveRegisterRewardTime() == 0) {
      long beforeGold = player.getGold();
      playerMgr.addGold(player, welfareDic.getRegisterReward(), LogReason.RECEIVE_REGISTER_REWARD);
      welfareData.setReceiveRegisterRewardTime(System.currentTimeMillis());
      DbLogService.log(new RegisterGiftLog(player, beforeGold));
    }

    refreshPlayerWelfareData(player, false);
    msgMgr.noticeWelfareInfoMsg(player);
  }

  /**
   * 刷新玩家福利数据 .
   * 
   * @param player 玩家.
   * @param notice . 刷新数据时是否发送刷新通知
   */
  public void refreshPlayerWelfareData(Player player, boolean notice) {
    JobRunTime jobRunTime = jobRunTimeMgr.getJobRunTime(WelfareJob.class);
    WelfareData welfareData = player.welfareData();

    if (welfareData.getSignInTime() < jobRunTime.getPreRunTime()) {
      // 刷新签到数据
      welfareData.setSignInNum(0);
    }

    if (welfareData.getReceiveBenefitsTime() < jobRunTime.getPreRunTime()) {
      // 刷新福利数据
      welfareData.setReceiveBenefitsNum(0);
    }

    if (welfareData.getShareWechatTime() < jobRunTime.getPreRunTime()) {
      // 刷新微信分享数据
      welfareData.setShareWechatNum(0);
    }

    if (notice) {
      msgMgr.sendRefreshWelfareMsg(player);
    }
  }

  /**
   * 签到 .
   * 
   * @param player 玩家.
   */
  public void signIn(Player player) {
    WelfareData welfareData = player.welfareData();
    int signInNum = welfareData.getSignInNum();
    if (signInNum > 0) {
      LOG.error("玩家[{}][{}]已经签到不能重复签到", player.getPlayerName(), player.getId());
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.TASK_SIGNIN_ALREAY, Operator.SYSTEM);
      return;
    }

    int ipSignInNum = dataMgr.getIpSignInNum(player.ip);
    if (ipSignInNum >= welfareDic.getSignInConfig().getSameIpNum()) {
      LOG.error("玩家[{}][{}]由于达到ip签到次数限制不能签到", player.getPlayerName(), player.getId());
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.TASK_SIGNIN_IP_MAX, Operator.SYSTEM);
      return;
    }

    // int macSignInNum = dataMgr.getMacSignInNum(player.getLoginMac());
    // if (macSignInNum >= welfareDic.getSignInConfig().getSameMacNum()) {
    // LOG.error("玩家[{}][{}]由于达到机器码签到次数限制不能签到", player.getPlayerName(), player.getId());
    // commonMsgMgr.sendErrorDlgMsg(player, 708, 0);
    // return;
    // }

    final long beforeGold = player.getGold();
    dataMgr.addIpSignInNum(player.ip);
    welfareData.setSignInNum(signInNum + 1);
    welfareData.setSignInTime(System.currentTimeMillis());

    int signInGold = welfareDic.getSignInConfig().getGold();
    msgMgr.sendRefreshWelfareMsg(player);
    playerMgr.addGold(player, signInGold, LogReason.SIGN_IN);

    // 领取奖励后给提示
    commonMsgMgr.sendTipDlgMsg(player, ErrorCode.TASK_SIGNIN_SUCCESS, Operator.SYSTEM,
        "" + signInGold);
    LOG.info("玩家[{}][{}]签到成功", player.getPlayerName(), player.getId());
    DbLogService.log(new SignInLog(player, beforeGold));
  }

  /**
   * 领取福利 .
   * 
   * @param player 玩家.
   */
  public void receiveBenefits(Player player) {
    WelfareData welfareData = player.welfareData();
    int receiveBenefitsNum = welfareData.getReceiveBenefitsNum();
    if (receiveBenefitsNum >= welfareDic.getBenefitsConfig().getNum()) {
      LOG.error("玩家[{}][{}]已经领取福利超过上限,领取事变", player.getPlayerName(), player.getId());
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.TASK_SIGNIN_GET_MAX, Operator.SYSTEM);
      return;
    }

    int ipReceiveBenefitsNum = dataMgr.getIpReceiveBenefitsNum(player.ip);
    if (ipReceiveBenefitsNum >= welfareDic.getBenefitsConfig().getSameIpNum()) {
      LOG.error("玩家[{}][{}]由于达到ip领取福利次数限制不能领取", player.getPlayerName(), player.getId());
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.TASK_SIGNIN_IP_GET_MAX, Operator.SYSTEM);
      return;
    }

    // int macReceiveBenefitsNum = dataMgr.getMacReceiveBenefitsNum(player.getLoginMac());
    // if (macReceiveBenefitsNum >= welfareDic.getBenefitsConfig().getSameMacNum()) {
    // LOG.error("玩家[{}][{}]由于达到机器码领取福利次数限制不能领取", player.getPlayerName(), player.getId());
    // commonMsgMgr.sendErrorDlgMsg(player, 711, 0);
    // return;
    // }

    if (player.getGold() + player.getSafeGold() > welfareDic.getBenefitsConfig().getLimit()) {
      LOG.error("玩家[{}][{}]超过领取福利金币限制不能领取", player.getPlayerName(), player.getId());
      commonMsgMgr.sendErrorDlgMsg(player, ErrorCode.TASK_SIGNIN_NOT_MATCH_REQUIRE,
          Operator.SYSTEM);
      return;
    }

    final long beforeGold = player.getGold();
    dataMgr.addIpReceiveBenefitsNum(player.ip);
    welfareData.setReceiveBenefitsNum(receiveBenefitsNum + 1);
    welfareData.setReceiveBenefitsTime(System.currentTimeMillis());
    int benefitsGold = welfareDic.getBenefitsConfig().getGold();
    playerMgr.addGold(player, benefitsGold, LogReason.RECEIVE_BENEFITS);

    msgMgr.sendRefreshWelfareMsg(player);
    // 领取奖励后给提示
    commonMsgMgr.sendTipDlgMsg(player, ErrorCode.TASK_SIGNIN_SUCCESS2, Operator.SYSTEM,
        "" + benefitsGold);
    LOG.info("玩家[{}][{}]领取福利[{}]成功", player.getPlayerName(), player.getId(), "" + benefitsGold);
    DbLogService.log(new ReceiveBenefitsLog(player, beforeGold));
  }

  /**
   * 领取微信分享奖励.
   * 
   * @param player 玩家.
   * @return 结果.
   */
  public ResMessage resShareWechat(Player player) {
    WelfareData welfareData = player.welfareData();
    int signInNum = welfareData.getShareWechatNum();
    if (signInNum < welfareDic.getShareWeChatConfig().getNum()) {

      welfareData.setShareWechatNum(signInNum + 1);
      welfareData.setShareWechatTime(TimeUtil.getTimeMillis());

      long shareGold = welfareDic.getShareWeChatConfig().getGold();

      final long beforeGold = player.getGold();

      playerMgr.addGold(player, shareGold, LogReason.SHARE_WECHAT);

      // msgMgr.sendRefreshWelfareMsg(player);

      // 领取奖励后给提示
      commonMsgMgr.sendTipDlgMsg(player, ErrorCode.TASK_SHARE_REWARD, Operator.SYSTEM,
          "" + shareGold);

      if (LOG.isDebugEnabled()) {
        LOG.debug("玩家[{}][{}]分享成功", player.getPlayerName(), player.getId());
      }

      DbLogService.log(new ShareLog(player, beforeGold));
    }

    return ResMessage.DEFAULT;
  }

}
