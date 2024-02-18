package com.idealighter.game.configuration.security.model;

import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.common.constant.AdminStatusConstant;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DlUserDetail implements UserDetails {
  /**
   * serialVersionUID : 序列码.
   */
  private static final long serialVersionUID = 1L;

  private AdminDto adminDto; // 玩家信息
  private List<String> authoritys; // 权限列表
  private boolean blocked; //

  /**
   * account UserAccount对象.
   */
  public DlUserDetail(AdminDto adminDto, List<String> authoritys) {
    super();
    this.adminDto = adminDto;
    this.authoritys = authoritys;
    if (adminDto == null || adminDto.getStatus().equals(AdminStatusConstant.LOCK)) {
      blocked = true;
    } else {
      blocked = false;
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    final Set<GrantedAuthority> authorities = new HashSet<>();
    for (String role : authoritys) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return adminDto.getPassword();
  }

  @Override
  public String getUsername() {
    return adminDto.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    // 账号是否过期
    return !blocked;
  }

  @Override
  public boolean isAccountNonLocked() {
    // 账号是否锁定
    return !blocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // 密码是否过期
    return !blocked;
  }

  @Override
  public boolean isEnabled() {
    // 用户是否可用
    return !blocked;
  }

  public AdminDto getAccount() {
    return adminDto;
  }
}
