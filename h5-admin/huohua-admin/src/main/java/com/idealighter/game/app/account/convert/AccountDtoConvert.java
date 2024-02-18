package com.idealighter.game.app.account.convert;

import com.idealighter.game.app.account.dto.AccountDto;
import com.idealighter.game.service.admin.bo.AdminBo;

import org.mapstruct.Mapper;

@Mapper
public interface AccountDtoConvert {
  AccountDto toAccountDto(AdminBo bo);
}
