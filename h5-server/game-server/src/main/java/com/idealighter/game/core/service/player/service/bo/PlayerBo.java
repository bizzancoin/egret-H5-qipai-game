package com.idealighter.game.core.service.player.service.bo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.idealighter.game.core.service.prizepool.struct.player.PlayerPrizePool;
import com.idealighter.game.core.service.welfare.struct.WelfareData;
import com.idealighter.game.core.util.PwdEncode;
import com.idealighter.game.gamehall.dto.MemInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 玩家域对象 .
 * 
 * @date 2015年8月25日 下午3:13:23
 *
 */
public class PlayerBo {
  // 玩家id
  private long id;
  // 靓号
  private long superId;

  // 渠道Id
  public String channelId;

  // 用户名
  private String userName;
  // 微信unionId
  private String unionId;
  // 密码
  private String password;
  // 玩家昵称
  private String playerName;
  // 用户图标
  private int icon;
  // 性别
  private String sex;
  // 年龄
  private int age = 18;
  // 出生月
  private int birthMonth = 1;
  // 出生日
  private int birthDay = 1;
  // 省
  private String province;
  // 市
  private String city;
  // 地址
  private String addr;
  // 断线重连
  private String reLoginCode;
  // 签名
  private String signature;
  // 玩家姓名
  private String name;
  // 玩家身份证
  private String idcard;
  // 第一个密保问题
  private String firstPwdProtectQ;
  // 第一个密保答案
  private String firstPwdProtectA;
  // 第二个密保问题
  private String secondPwdProtectQ;
  // 第二个密保答案
  private String secondPwdProtectA;
  // 玩家微信
  private String weixin;
  // 电话
  private String phone;
  // 邮件
  private String email;
  // 电话是否检验
  private boolean validatePhone;
  // 邮件是否校验
  private boolean validateEmail;
  // 经验
  private long exp;
  // 等级
  private int level;
  // 元宝
  private AtomicLong ingot = new AtomicLong(0L);
  // 玩家身上的金币
  private AtomicLong gold = new AtomicLong(0L);
  // 保险箱里的金币
  private AtomicLong safeGold = new AtomicLong(0L);

  // 赢分
  private AtomicLong winGold = new AtomicLong(0L);
  // 银行密码，默认888888
  private String bankPwd = PwdEncode.encodeWithTail("888888");
  // 积分
  private AtomicLong cedit = new AtomicLong(0L);
  // 奖券
  private AtomicLong lottery = new AtomicLong(0L);
  // 玩家个人奖池(被prizePoolsMap代替)
  // private String prizePools;
  // 玩家道具(被itemsMap代替)
  // private String items;
  // vip等级
  private int vipLevel;
  // vip结束时间
  private long vipEndTime = 0L;
  // 推荐人id
  private long recommendId;
  // 注册类型(0:机器人,1:客户端,2:网页,3:手机)
  private byte registerType;
  // 注册ip
  private String registerIp;
  // 注册时间
  private Date registerTime;
  // 登录ip
  private String loginIp;
  // 登录时间
  private Date loginTime;
  // 登录次数
  private int loginCount;
  // 退出登录时间
  private Date logoutTime;
  // 是否在线
  private boolean online;
  // 是否锁定
  private boolean locked;
  // 是否登录手机验证
  private boolean loginPhoneVerify = true;
  // 是否播放道具特效
  private boolean playItemEffect = true;
  // 数据更新时间
  private Date updateTime;
  // 游戏时间
  private long gametime;
  // 在线时间
  private long onlinetime;

  // 0:普通用户 1:代理.
  private byte type;

  // 代理转账获取金币.
  private long transferReward;
  // 福利（被welfareData代替）
  // private String welfare;

  // 玩家进入过的游戏，被gamesSet取代
  // private String games;
  /***********************************************
   * vip房间桌子设置.
   ***********************************************************************/
  // 牌桌其他玩家金币限制
  private long tableLimitGold = 0;
  // 牌桌其他玩家金币限制是否有效
  private boolean tableLimitGoldAbled = false;
  // 牌桌是否不和同ip的玩家玩
  private boolean tableLimitIp = false;
  // 桌子密码
  private String tablePwd = null;
  // 桌子密码是否生效
  private boolean tablePwdAbled = true;
  // 当前房间设置是否生效
  private boolean roomSettingAbled = true;
  /***********************************************
   * vip房间桌子设置.
   ***********************************************************************/

  /**********************************************
   * 自定义持久化字段.
   **************************************************************/
  // 玩家身上的物品
  private Map<Integer, Integer> itemsMap = new HashMap<>();
  // 玩家个人奖池奖池
  private Map<Integer, PlayerPrizePool> prizePoolsMap = new ConcurrentHashMap<>();
  // 玩家进入过的游戏
  private LinkedHashSet<Integer> gamesSet = new LinkedHashSet<>();
  // 福利数据
  private WelfareData welfareData = new WelfareData();
  /**********************************************
   * 自定义持久化字段.
   **************************************************************/
  // 是否游客
  private boolean tourist = false;

  /**
   * 是否机器人.
   * 
   * @return 是否机器人.
   */
  public boolean isRobot() {

    return registerType == 0;
  }

  /**
   * 是否是vip.
   * 
   * @return 是否.
   */
  public boolean vip() {

    return vipEndTime > System.currentTimeMillis();
  }

  /**
   * vip剩余时长(秒).
   * 
   * @return 剩余时间.
   */
  public int vipDuration() {
    long duration = vipEndTime - System.currentTimeMillis();
    return (int) (duration > 0 ? duration / 1000 : 0);
  }

  /**
   * 玩家房间成员基本信息,不包含房间和桌子信息.
   * 
   * @return 成员基本信息.
   */
  public MemInfo baseMemInfo() {
    MemInfo memInfo = new MemInfo();
    memInfo.setPlayerId(id);
    memInfo.setPlayerName(playerName);
    memInfo.setSex(sex.equals("男") ? 0 : 1);
    memInfo.setIcon(icon);
    memInfo.setGold(safeGold.get());
    memInfo.setCedit(cedit.get());
    memInfo.setState(0);
    memInfo.setVip(vip() ? 1 : 0);
    memInfo.setTableId(0);
    memInfo.setOrder(0);
    memInfo.setLevel(level);
    memInfo.setSignature(signature);

    return memInfo;
  }

  public Map<Integer, Integer> items() {

    return itemsMap;
  }

  public Map<Integer, PlayerPrizePool> prizePools() {

    return prizePoolsMap;
  }

  public WelfareData welfareData() {
    return welfareData;
  }

  public LinkedHashSet<Integer> games() {

    return gamesSet;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getSuperId() {
    return superId;
  }

  public void setSuperId(long superId) {
    this.superId = superId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUnionId() {
    return unionId;
  }

  public void setUnionId(String unionId) {
    this.unionId = unionId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  public String getWeixin() {
    return weixin;
  }

  public void setWeixin(String weixin) {
    this.weixin = weixin;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isValidatePhone() {
    return validatePhone;
  }

  public void setValidatePhone(boolean validatePhone) {
    this.validatePhone = validatePhone;
  }

  public boolean isValidateEmail() {
    return validateEmail;
  }

  public void setValidateEmail(boolean validateEmail) {
    this.validateEmail = validateEmail;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }


  public int getVipLevel() {
    return vipLevel;
  }

  public void setVipLevel(int vipLevel) {
    this.vipLevel = vipLevel;
  }

  public long getVipEndTime() {
    return vipEndTime;
  }

  public void setVipEndTime(long vipEndTime) {
    this.vipEndTime = vipEndTime;
  }

  public long getRecommendId() {
    return recommendId;
  }

  public void setRecommendId(long recommendId) {
    this.recommendId = recommendId;
  }

  public byte getRegisterType() {
    return registerType;
  }

  public void setRegisterType(byte registerType) {
    this.registerType = registerType;
  }

  public String getRegisterIp() {
    return registerIp;
  }

  public void setRegisterIp(String registerIp) {
    this.registerIp = registerIp;
  }

  public Date getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(Date registerTime) {
    this.registerTime = registerTime;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(Date logoutTime) {
    this.logoutTime = logoutTime;
  }

  public int getLoginCount() {
    return loginCount;
  }

  public void setLoginCount(int loginCount) {
    this.loginCount = loginCount;
  }

  public String getPrizePools() {
    // return prizePools;
    return JSON.toJSONString(prizePoolsMap);
  }

  /**
   * 设置奖池.
   * 
   * @param prizePools . 奖池.
   */
  public void setPrizePools(String prizePools) {
    // this.prizePools = prizePools;
    if (prizePools == null) {
      this.prizePoolsMap = new HashMap<>();
    } else {
      this.prizePoolsMap =
          JSON.parseObject(prizePools, new TypeReference<Map<Integer, PlayerPrizePool>>() {});
    }
  }

  public String getItems() {
    // return items;
    return JSON.toJSONString(itemsMap);
  }

  /**
   * 设置item.
   * 
   * @param items . item.
   */
  public void setItems(String items) {
    // this.items = items;
    if (items == null) {
      this.itemsMap = new HashMap<>();
    } else {
      this.itemsMap = JSON.parseObject(items, new TypeReference<Map<Integer, Integer>>() {});
    }
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public long getExp() {
    return exp;
  }

  public void setExp(long exp) {
    this.exp = exp;
  }

  public String getBankPwd() {
    return bankPwd;
  }

  public void setBankPwd(String bankPwd) {
    this.bankPwd = bankPwd;
  }

  public int getBirthMonth() {
    return birthMonth;
  }

  public void setBirthMonth(int birthMonth) {
    this.birthMonth = birthMonth;
  }

  public int getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(int birthDay) {
    this.birthDay = birthDay;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getReLoginCode() {
    return reLoginCode;
  }

  public void setReLoginCode(String reLoginCode) {
    this.reLoginCode = reLoginCode;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstPwdProtectQ() {
    return firstPwdProtectQ;
  }

  public void setFirstPwdProtectQ(String firstPwdProtectQ) {
    this.firstPwdProtectQ = firstPwdProtectQ;
  }

  public String getFirstPwdProtectA() {
    return firstPwdProtectA;
  }

  public void setFirstPwdProtectA(String firstPwdProtectA) {
    this.firstPwdProtectA = firstPwdProtectA;
  }

  public String getSecondPwdProtectQ() {
    return secondPwdProtectQ;
  }

  public void setSecondPwdProtectQ(String secondPwdProtectQ) {
    this.secondPwdProtectQ = secondPwdProtectQ;
  }

  public String getSecondPwdProtectA() {
    return secondPwdProtectA;
  }

  public void setSecondPwdProtectA(String secondPwdProtectA) {
    this.secondPwdProtectA = secondPwdProtectA;
  }

  public boolean isLoginPhoneVerify() {
    return loginPhoneVerify;
  }

  public void setLoginPhoneVerify(boolean loginPhoneVerify) {
    this.loginPhoneVerify = loginPhoneVerify;
  }

  public boolean isPlayItemEffect() {
    return playItemEffect;
  }

  public void setPlayItemEffect(boolean playItemEffect) {
    this.playItemEffect = playItemEffect;
  }

  public boolean isRoomSettingAbled() {
    return roomSettingAbled;
  }

  public void setRoomSettingAbled(boolean roomSettingAbled) {
    this.roomSettingAbled = roomSettingAbled;
  }

  public boolean isTableLimitIp() {
    return tableLimitIp;
  }

  public void setTableLimitIp(boolean tableLimitIp) {
    this.tableLimitIp = tableLimitIp;
  }

  public String getTablePwd() {
    return tablePwd;
  }

  public void setTablePwd(String tablePwd) {
    this.tablePwd = tablePwd;
  }

  public boolean isTablePwdAbled() {
    return tablePwdAbled;
  }

  public void setTablePwdAbled(boolean tablePwdAbled) {
    this.tablePwdAbled = tablePwdAbled;
  }

  public long getTableLimitGold() {
    return tableLimitGold;
  }

  public void setTableLimitGold(long tableLimitGold) {
    this.tableLimitGold = tableLimitGold;
  }

  public boolean isTableLimitGoldAbled() {
    return tableLimitGoldAbled;
  }

  public void setTableLimitGoldAbled(boolean tableLimitGoldAbled) {
    this.tableLimitGoldAbled = tableLimitGoldAbled;
  }

  public long getGametime() {
    return gametime;
  }

  public void setGametime(long gametime) {
    this.gametime = gametime;
  }

  public long getOnlinetime() {
    return onlinetime;
  }

  public void setOnlinetime(long onlinetime) {
    this.onlinetime = onlinetime;
  }

  public String getGames() {
    return JSON.toJSONString(gamesSet);
  }

  /**
   * 设置games.
   * 
   * @param games . games 数据.
   */
  public void setGames(String games) {
    if (games == null) {
      this.gamesSet = new LinkedHashSet<>();
    } else {
      this.gamesSet = JSON.parseObject(games, new TypeReference<LinkedHashSet<Integer>>() {});
    }
  }

  public String getWelfare() {
    return JSON.toJSONString(welfareData);
  }

  /**
   * 设置福利数据.
   * 
   * @param welfare . 福利数据数据.
   */
  public void setWelfare(String welfare) {
    if (welfare == null) {
      this.welfareData = new WelfareData();
    } else {
      this.welfareData = JSON.parseObject(welfare, WelfareData.class);
    }
  }

  public boolean isTourist() {
    return tourist;
  }

  public void setTourist(boolean tourist) {
    this.tourist = tourist;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public long getTransferReward() {
    return transferReward;
  }

  public void setTransferReward(long transferReward) {
    this.transferReward = transferReward;
  }

  public AtomicLong getIngot() {
    return ingot;
  }


  public AtomicLong getGold() {
    return gold;
  }


  public AtomicLong getSafeGold() {
    return safeGold;
  }



  public AtomicLong getWinGold() {
    return winGold;
  }



  public AtomicLong getCedit() {
    return cedit;
  }



  public AtomicLong getLottery() {
    return lottery;
  }

  public void setIngot(AtomicLong ingot) {
    this.ingot = ingot;
  }

  public void setGold(AtomicLong gold) {
    this.gold = gold;
  }

  public void setSafeGold(AtomicLong safeGold) {
    this.safeGold = safeGold;
  }

  public void setWinGold(AtomicLong winGold) {
    this.winGold = winGold;
  }

  public void setCedit(AtomicLong cedit) {
    this.cedit = cedit;
  }

  public void setLottery(AtomicLong lottery) {
    this.lottery = lottery;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

}
