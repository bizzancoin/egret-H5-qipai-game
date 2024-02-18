package com.idealighter.game.service.admin.convert;

import com.idealighter.game.dao.manage.po.Admin;
import com.idealighter.game.dao.manage.po.Permission;
import com.idealighter.game.service.admin.bo.AdminBo;
import com.idealighter.game.service.admin.bo.PermissionBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface AdminBoConvert {
  AdminBo toAdminBo(Admin admin);

  List<AdminBo> toAdminBos(List<Admin> admins);

  Admin toAdmin(AdminBo adminBo);

  PermissionBo toPermissionBo(Permission permission);

  List<PermissionBo> toPermissionBos(List<Permission> permissions);
}
