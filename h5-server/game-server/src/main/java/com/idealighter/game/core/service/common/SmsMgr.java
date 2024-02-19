
package com.idealighter.game.core.service.common;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.game.core.assertions.HuohuaAssert;
import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.utils.sms.alidayu.SmsAlidayuUtil;
import com.idealighter.utils.sms.alidayu.bo.SmsSendResult;

/**
 * 短信管理.
 * 
 * @date 2015年12月15日 下午3:47:07
 *
 */
@Singleton
public class SmsMgr {
  private String signName; // 短信签名
  private String templateCode; // 短信模板码
  private String accessKeyId; //
  private String accessKeySecret; //

  /**
   * 构造函数 .
   * 
   * @param signName 短信签名
   * @param templateCode 短信模板
   * @param accessKeyId 应用AccessKeyId
   * @param accessKeySecret 引用AccessKeySecret.
   */
  @Inject
  public SmsMgr(@Named("sms.signName") String signName,
      @Named("sms.templateCode") String templateCode, @Named("sms.accessKeyId") String accessKeyId,
      @Named("sms.accessKeySecret") String accessKeySecret) {
    this.signName = signName;
    this.templateCode = templateCode;
    this.accessKeySecret = accessKeySecret;
    this.accessKeyId = accessKeyId;
  }

  /**
   * 发送短信数字验证码,异步发送 .
   * 
   * @param phone 手机号码.
   * @param smsCode 短信码
   */
  public void sendSmsKey(String phone, String smsCode) {
    SmsSendResult result = SmsAlidayuUtil.sendSmsCode(phone, smsCode, signName, templateCode,
        accessKeyId, accessKeySecret);

    if (result.equals(SmsSendResult.LIMIT)) {
      HuohuaAssert.isTrue(false, ErrorCode.SMS_LIMIT_FAIL);
    }

    HuohuaAssert.isTrue(result.equals(SmsSendResult.OK), ErrorCode.SMS_SEND_FAIL);
  }

}
