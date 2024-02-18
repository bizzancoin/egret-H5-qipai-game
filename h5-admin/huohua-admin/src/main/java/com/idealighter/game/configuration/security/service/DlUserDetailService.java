package com.idealighter.game.configuration.security.service;

import com.idealighter.game.app.admin.convert.AdminDtoConvert;
import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.configuration.security.model.DlUserDetail;
import com.idealighter.game.service.account.IAccountService;
import com.idealighter.game.service.admin.bo.AdminBo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DlUserDetailService implements UserDetailsService {
  @Autowired
  private IAccountService accountService;

  @Autowired
  private AdminDtoConvert adminDtoConvert;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AdminBo adminBo = accountService.findByUserName(username);

    AdminDto adminDto = adminDtoConvert.toAdminDto(adminBo);

    List<String> authoritys = null;
    DlUserDetail result = null;
    if (adminDto != null) {
      authoritys = adminDto.getPermission();
      result = new DlUserDetail(adminDto, authoritys);
      return result;
    }
    return result;
  }
}
