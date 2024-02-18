package com.idealighter.game.configuration.security.event;

import com.idealighter.game.app.admin.dto.AdminDto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListeners {

  /**
   * 授权成功 .
   *
   */
  @EventListener
  public void onInteractiveAuthenticationSuccess(
      final InteractiveAuthenticationSuccessEvent event) {

    // HttpServletRequest request =
    // ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    //
    // DlUserDetail user = (DlUserDetail) event.getAuthentication().getPrincipal();
    // addSession(user.getAccount(), request);
  }

  /**
   * 成功后，设置session .
   *
   */
  public static void addSession(AdminDto adminDto, HttpServletRequest request) {
    // request.getSession().setAttribute(WebConstant.ADMIN_ID, adminDto.getId());
    // request.getSession().setAttribute(WebConstant.ADMIN_NAME, adminDto.getNickname());
    // request.getSession().setAttribute(WebConstant.ADMIN_ROLE, adminDto.getRole());
    //
    // List<String> permission = adminDto.getPermission();
    //
    // request.getSession().setAttribute(WebConstant.ADMIN_PERMISSION, permission);
  }
}
