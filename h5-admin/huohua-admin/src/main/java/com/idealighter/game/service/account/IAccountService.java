package com.idealighter.game.service.account;

import com.idealighter.game.service.admin.bo.AdminBo;

public interface IAccountService {

  AdminBo findByUserName(String username);

  AdminBo loginByUserPwd(String username, String password);

  AdminBo findById(Integer id);

  void changePwd(Integer adminId, String newPassword, String oldPassword);

}
