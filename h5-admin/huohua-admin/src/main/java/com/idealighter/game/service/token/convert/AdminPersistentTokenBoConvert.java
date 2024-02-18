package com.idealighter.game.service.token.convert;

import com.idealighter.game.common.convert.DateLongConvert;
import com.idealighter.game.dao.manage.po.AdminPersistentLogins;
import com.idealighter.game.service.token.bo.AdminPersistentLoginsBo;

import org.mapstruct.Mapper;

@Mapper(uses = DateLongConvert.class)
public interface AdminPersistentTokenBoConvert {

  AdminPersistentLogins toAdminPersistentLogins(AdminPersistentLoginsBo bo);

  AdminPersistentLoginsBo toAdminPersistentLoginsBo(AdminPersistentLogins pojo);
}
