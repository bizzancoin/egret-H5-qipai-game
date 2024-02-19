package com.idealighter.game.login.dto;

import com.idealighter.utils.code.RandCodeUtil;
import lombok.Data;

@Data
public class ReLoginCodeDto {
  private String account; // 账号([base64], 游客：游客唯一标识; 玩家:手机号码; 微信:userName)
  private String randCode; // 10位随机码
  private String ip; // 玩家ip账号(base64后md5加密)

  public ReLoginCodeDto() {};

  public ReLoginCodeDto(String account, String ip) {
    this.account = account;
    this.ip = ip;
    this.randCode = RandCodeUtil.createString(10);
  }
}
