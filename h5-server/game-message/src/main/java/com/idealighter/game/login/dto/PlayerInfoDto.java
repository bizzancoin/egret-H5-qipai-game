package com.idealighter.game.login.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import lombok.Data;

/**
 * 玩家信息.
 * 
 * @author messageGenerator
 */
@Data
public class PlayerInfoDto {
  // 玩家id
  @Protobuf(order = 1)
  private long id;
  // 玩家账号
  @Protobuf(order = 2)
  private String userName;
  // 玩家昵称
  @Protobuf(order = 3)
  private String nickName;
  // 玩家性别
  @Protobuf(order = 4)
  private String sex;
  // 玩家姓名
  @Protobuf(order = 5)
  private String fullName;
  // 玩家身份证号
  @Protobuf(order = 6)
  private String idCardNo;
  // 头像
  @Protobuf(order = 7)
  private int icon;
  // 玩家等级
  @Protobuf(order = 8)
  private int level;
  // vip等级
  @Protobuf(order = 9)
  private int vipLevel;
  // vip时长(秒)
  @Protobuf(order = 10)
  private int vipDuration;
  // 金币
  @Protobuf(order = 11)
  private long gold;
  // 保险箱金币
  @Protobuf(order = 12)
  private long safeGold;
  // 元宝
  @Protobuf(order = 13)
  private long ingot;
  // 积分
  @Protobuf(order = 14)
  private long cedit;
  // 奖券
  @Protobuf(order = 15)
  private long lottery;
  // 电话
  @Protobuf(order = 16)
  private String phone;
  // 邮箱
  @Protobuf(order = 17)
  private String email;
  // 年龄
  @Protobuf(order = 18)
  private int age;
  // 出生月
  @Protobuf(order = 19)
  private int birthMonth;
  // 出生日
  @Protobuf(order = 20)
  private int birthDay;
  // 省
  @Protobuf(order = 21)
  private String province;
  // 市
  @Protobuf(order = 22)
  private String city;
  // 地址
  @Protobuf(order = 23)
  private String addr;
  // 签名
  @Protobuf(order = 24)
  private String signature;
  // 绑定机器码
  @Protobuf(order = 25)
  private String bindingMac;
  // 是否有密保(0:没有,非0:有)
  @Protobuf(order = 26)
  private int havePwdProtect;
  // 登录是否需要手机验证(0:不需要，非0:需要)
  @Protobuf(order = 27)
  private int loginPhoneVerify;
  // 是否需要播放道具使用特效(0:不需要，非0:需要)
  @Protobuf(order = 28)
  private int playItemEffect;
  // 0:不是游客,1:游客
  @Protobuf(order = 29)
  private int tourist;
  // 断线重连码
  @Protobuf(order = 30)
  private String reLoginCode;
  // 0:普通用户 1:代理.
  @Protobuf(order = 31)
  private int type;
  // 代理转账获取金币.
  @Protobuf(order = 32)
  private long transferReward;
  // 靓号
  @Protobuf(order = 33)
  private long superId;
  // 下一个等级升级经验
  @Protobuf(order = 34)
  private long nextLevelExp;
  // 经验
  @Protobuf(order = 35)
  private long exp;

}
