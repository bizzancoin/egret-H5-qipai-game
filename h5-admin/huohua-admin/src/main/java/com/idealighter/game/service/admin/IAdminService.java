package com.idealighter.game.service.admin;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.game.service.admin.bo.PermissionBo;

import java.util.List;

public interface IAdminService {
  ResultPage<AdminBo> findByPage(String username, Integer page, Integer pageSize);

  void resetPassword(Integer adminId, String password);

  void changeStatus(Integer adminId, Byte status);

  void add(String username, String password, String nickname);

  void update(Integer adminId, String nickname);

  List<PermissionBo> findPermission();

  void updatePermission(Integer adminId, String permission);

}
