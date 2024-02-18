package com.idealighter.game.service.admin.bo;

import lombok.Data;

@Data
public class AdminBo {
  /**
   * 表id .
   */
  private Integer id;

  /**
   * 用户名 .
   */
  private String username;

  /**
   * 密码 .
   */
  private String password;

  /**
   * 加密串 .
   */
  private String salt;

  /**
   * 昵称 .
   */
  private String nickname;

  /**
   * 头像url .
   */
  private String avatar;

  /**
   *  0=锁定 1=正常 .
   */
  private Byte status;

  /**
   * 权限列表 .
   */
  private String permission;

  /**
   * 1普通管理员 2 超级管理员 .
   */
  private Byte role;
}
