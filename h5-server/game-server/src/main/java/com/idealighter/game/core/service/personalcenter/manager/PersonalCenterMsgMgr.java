
package com.idealighter.game.core.service.personalcenter.manager;

import com.google.inject.Singleton;

import com.idealighter.game.personalcenter.message.ResModifyLoginPwdMsg;

/**
 * 个人中心消息管理 .
 * 
 * @date 2015年11月12日 下午2:40:43
 *
 */
@Singleton
public class PersonalCenterMsgMgr {

 
  /**
   * 发送修改登录密码结果 .
   * 
   * @param res 0:成功,1:旧密码输入不正确, 2:新密码格式不对
   */
  public ResModifyLoginPwdMsg sendModifyLoginPwdMsg(int res) {
    ResModifyLoginPwdMsg msg = new ResModifyLoginPwdMsg();
    msg.setRes(res);
    return msg;
  }
}
