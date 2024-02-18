package com.idealighter.game.app.account.dto;

import lombok.Data;

@Data
public class AccountDto {

  private String nickname;

  private String avatar;

  private String permission;
  
  private Byte role;

}
