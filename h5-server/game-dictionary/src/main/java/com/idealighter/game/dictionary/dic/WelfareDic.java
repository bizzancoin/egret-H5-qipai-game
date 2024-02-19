
package com.idealighter.game.dictionary.dic;

import com.alibaba.fastjson.JSON;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.dictionary.core.DictionaryTable;
import com.idealighter.game.dictionary.domain.WelfareDomain;
import com.idealighter.game.dictionary.mapper.WelfareMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class WelfareDic implements DictionaryTable<WelfareDomain> {

  private WelfareMapper mapper;
  private List<WelfareDomain> list;
  private Map<Integer, WelfareDomain> map;

  // 签到配置
  private SignInConfig signInConfig;
  // 救济金配置
  private BenefitsConfig benefitsConfig;
  // 注册奖励金币
  private int registerReward;

  private ShareWeChatConfig shareWeChatConfig;

  @Inject
  public WelfareDic(WelfareMapper mapper) {
    this.mapper = mapper;
    load();
  }

  @Override
  public List<WelfareDomain> list() {

    return list;
  }

  @Override
  public Map<Integer, WelfareDomain> map() {

    return map;
  }

  @Override
  public void load() {
    this.list = mapper.selectAll();
    Map<Integer, WelfareDomain> map = new HashMap<>();
    list.forEach(val -> map.put(val.getId(), val));
    this.map = map;

    this.signInConfig = JSON.parseObject(get(1).getConfig(), SignInConfig.class);
    this.benefitsConfig = JSON.parseObject(get(2).getConfig(), BenefitsConfig.class);
    this.registerReward = JSON.parseObject(get(3).getConfig()).getIntValue("gold");
    this.shareWeChatConfig = JSON.parseObject(get(4).getConfig(), ShareWeChatConfig.class);
  }

  @Override
  public WelfareDomain get(int id) {

    return map.get(id);
  }

  public SignInConfig getSignInConfig() {
    return signInConfig;
  }

  public BenefitsConfig getBenefitsConfig() {
    return benefitsConfig;
  }

  public int getRegisterReward() {
    return registerReward;
  }

  public ShareWeChatConfig getShareWeChatConfig() {
    return shareWeChatConfig;
  }

  /**
   * 签到配置.
   *
   */
  public static class SignInConfig {
    // 签到奖励金币
    private int gold;
    // 同IP签到次数
    private int sameIpNum;
    // 同机器码签到次数
    private int sameMacNum;

    public int getGold() {
      return gold;
    }

    public void setGold(int gold) {
      this.gold = gold;
    }

    public int getSameIpNum() {
      return sameIpNum;
    }

    public void setSameIpNum(int sameIpNum) {
      this.sameIpNum = sameIpNum;
    }

    public int getSameMacNum() {
      return sameMacNum;
    }

    public void setSameMacNum(int sameMacNum) {
      this.sameMacNum = sameMacNum;
    }
  }

  /**
   * 救济金配置.
   *
   */
  public static class BenefitsConfig extends SignInConfig {
    // 领取救济金的金币限制(玩家身上和保险箱金币)
    private long limit;
    // 每日领取救济金次数
    private int num;

    public long getLimit() {
      return limit;
    }

    public void setLimit(long limit) {
      this.limit = limit;
    }

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }
  }

  public static class ShareWeChatConfig {
    // 微信分享奖励金币
    private long gold;
    // 微信分享每日次数
    private int num;

    public long getGold() {
      return gold;
    }

    public void setGold(long gold) {
      this.gold = gold;
    }

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }
  }

}
