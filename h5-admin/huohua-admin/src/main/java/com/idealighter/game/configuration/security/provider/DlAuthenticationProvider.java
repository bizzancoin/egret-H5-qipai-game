package com.idealighter.game.configuration.security.provider;

import com.idealighter.game.app.admin.dto.AdminDto;
import com.idealighter.game.configuration.security.model.DlUserDetail;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.crypto.MD5Utils;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DlAuthenticationProvider implements AuthenticationProvider {
  private UserDetailsService userDetailsService;

  public DlAuthenticationProvider(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final String username = authentication.getName();
    final String password = authentication.getCredentials().toString();

    String decodePwd = "";
    if (EmptyUtil.stringIsNotEmpty(password)) {
      // decodePwd = DesUtil.decrypt(password, CommonProperties.PWD_ENCRYPT_KEY);
      decodePwd = password;
    }

    DlUserDetail userInfo = (DlUserDetail) userDetailsService.loadUserByUsername(username);
    if (userInfo != null) {
      AdminDto adminDto = userInfo.getAccount();
      String pwd = MD5Utils.getPassword(decodePwd, adminDto.getSalt());
      if (pwd.equals(userInfo.getPassword())) {
        return new UsernamePasswordAuthenticationToken(userInfo, password,
            userInfo.getAuthorities());
      }
    }
    throw new BadCredentialsException("User '" + username + "' authenticate fail");
  }

  /**
   * 重新加载权限 .
   *
   */
  // public void reloadUserAuthority(HttpSession session) {
  // Integer userId = Integer.parseInt(session.getAttribute(WebConstant.ADMIN_ID).toString());
  // IdeaAssert.isTrue(userId != null);
  // AdminBo adminBo = accountService.findById(userId);
  // IdeaAssert.isTrue(adminBo != null);
  // String userName = adminBo.getUsername();
  // DlUserDetail userInfo = (DlUserDetail) userDetailsService.loadUserByUsername(userName);
  //
  // SecurityContext securityContext = (SecurityContext) session
  // .getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
  // Authentication authentication = securityContext.getAuthentication();
  //
  // // 重新new一个token，因为Authentication中的权限是不可变的.
  // UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userInfo,
  // authentication.getCredentials(), userInfo.getAuthorities());
  // result.setDetails(authentication.getDetails());
  // securityContext.setAuthentication(result);
  // }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
