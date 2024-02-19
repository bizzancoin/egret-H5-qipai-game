
package com.idealighter.game.core.service.personalcenter.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.service.common.SmsMgr;
import com.idealighter.game.core.service.log.struct.personalcenter.ModifyLoginPwdLog;
import com.idealighter.game.core.service.player.manager.PlayerMgr;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.struct.Player;
import com.idealighter.game.core.util.PwdEncode;
import com.idealighter.game.core.util.RandomKeyGenerator;
import com.idealighter.game.dblog.service.DbLogService;
import com.idealighter.game.message.core.ResMessage;
import com.idealighter.utils.check.CheckUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 个人中心管理 .
 * 
 * @date 2015年11月11日 下午7:52:22
 *
 */
@Singleton
public class PersonalCenterMgr {

  private static final Logger LOG = LoggerFactory.getLogger(PersonalCenterMgr.class);

  public static final String EMAIL_REGEX =
      "^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$";
  public static final String NAME_PATTERN = "^[\\u4E00-\\u9FA5]{2,15}$";
  public static final String IDNO_PATTERN = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";

  // 签名最大长度
  // private static final int SIGNATURE_MAX_LENGTH = 50;
  // 刷新手机登录验证码间隔时间(毫秒)
  private static final int REFRESH_BIND_PHONE_KEY_INTERVAL = 60000;

  @Inject
  private PersonalCenterMsgMgr msgMgr;
  @Inject
  private SmsMgr smsMgr;
  @Inject
  private PlayerMgr playerMgr;



  /**
   * 玩家修改登录密码 .
   * 
   * @param player 玩家.
   * @param oldPwd .
   * @param newPwd .
   */
  public ResMessage modifyLoginPwd(Player player, String oldPwd, String newPwd) {
    if (!PwdEncode.encodeWithTail(oldPwd).equals(player.getPassword())) {
      LOG.warn("玩家[{}][{}]输入的旧的登录密码不正确", player.getPlayerName(), player.getId());
      return msgMgr.sendModifyLoginPwdMsg(1);
    }

    if (!newPwd.matches(Player.PASSWORD_PATTERN)) {
      LOG.warn("玩家[{}][{}]输入的新的登录密码格式不合法", player.getPlayerName(), player.getId());
      return msgMgr.sendModifyLoginPwdMsg(2);
    }

    player.setPassword(PwdEncode.encodeWithTail(newPwd));

    playerMgr.asyncUpdatePlayer(player);
    LOG.info("玩家[{}][{}]修改登录密码成功", player.getUserName(), player.getId());
    DbLogService.log(new ModifyLoginPwdLog(player));

    return msgMgr.sendModifyLoginPwdMsg(0);
  }



  /**
   * 获取绑定手机验证码 .
   * 
   * @param player 玩家.
   * @param phone .
   */
  public ResMessage getBindPhoneKey(Player player, String phone) {

    HuohuaAssert.isTrue(CheckUtil.checkTelephone(phone), ErrorCode.BAD_REQUEST);


    // 校验用户存在否
    PlayerBo playerBo = playerMgr.selectPlayerByPhone(phone);

    HuohuaAssert.isTrue(playerBo == null, ErrorCode.USER_ALREADY_BIND_PHONE);


    long now = System.currentTimeMillis();
    // 给客户端延时2秒

    HuohuaAssert.isTrue(now - player.getBindPhoneKeyTime + 2000 >= REFRESH_BIND_PHONE_KEY_INTERVAL,
        ErrorCode.SMS_FREQUENT);

    player.tmpBindPhone = phone;
    player.bindPhoneKey = RandomKeyGenerator.generateNum(6);
    player.getBindPhoneKeyTime = now;

    playerMgr.asyncUpdatePlayer(player);
    smsMgr.sendSmsKey(player.tmpBindPhone, player.bindPhoneKey);
    LOG.info("玩家[{}][{}]获取绑定手机验证码[{}]", player.getUserName(), player.getId(), player.bindPhoneKey);

    return ResMessage.DEFAULT;
  }


}
