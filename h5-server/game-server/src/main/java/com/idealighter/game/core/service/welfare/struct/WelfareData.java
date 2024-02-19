
package com.idealighter.game.core.service.welfare.struct;

import com.google.common.collect.Lists;

import com.idealighter.game.ApplicationContext;
import com.idealighter.game.dictionary.dic.WelfareDic;
import com.idealighter.game.welfare.dto.WelfareInfo;

import java.util.List;

import lombok.Data;

/**
 * 福利数据 .
 * 
 * @date 2015年12月9日 上午10:52:54
 *
 */
@Data
public class WelfareData {

  // 签到时间
  private long signInTime;
  // 签到次数
  private int signInNum;
  // 领取救济金时间
  private long receiveBenefitsTime;
  // 领取救济金次数
  private int receiveBenefitsNum;
  // 领取注册奖励金币时间
  private long receiveRegisterRewardTime;

  // 分享次数
  private int shareWechatNum;
  // 分享时间
  private long shareWechatTime;

  /**
   * 福利信息 .
   * 
   * @return
   */
  public List<WelfareInfo> welfares() {
    WelfareDic welfareDic = ApplicationContext.getBean(WelfareDic.class);

    WelfareInfo signInWelfare = new WelfareInfo();
    signInWelfare.setType(1);
    signInWelfare.setNum(1 - signInNum);// 剩余次数
    signInWelfare.getParams().add("" + welfareDic.getSignInConfig().getGold());

    WelfareInfo benefitsWelfare = new WelfareInfo();
    benefitsWelfare.setType(2);
    benefitsWelfare.setNum(welfareDic.getBenefitsConfig().getNum() - receiveBenefitsNum);// 剩余次数
    benefitsWelfare.getParams().add("" + welfareDic.getBenefitsConfig().getGold());
    benefitsWelfare.getParams().add("" + welfareDic.getBenefitsConfig().getLimit());

    WelfareInfo shareWechatWelfare = new WelfareInfo();
    shareWechatWelfare.setNum(1 - shareWechatNum);
    shareWechatWelfare.setType(3);
    shareWechatWelfare.getParams().add("" + welfareDic.getShareWeChatConfig().getGold());

    return Lists.newArrayList(signInWelfare, benefitsWelfare, shareWechatWelfare);
  }
}
