package com.idealighter.game.configuration.security;

import com.idealighter.game.configuration.security.handler.DlAccessDeniedHandler;
import com.idealighter.game.configuration.security.handler.DlLoginFailureHandler;
import com.idealighter.game.configuration.security.handler.DlLoginSuccessHandler;
import com.idealighter.game.configuration.security.handler.DlLogoutSuccessHandler;
import com.idealighter.game.configuration.security.provider.DlAuthenticationProvider;
import com.idealighter.game.configuration.security.repository.DlPersistentTokenRepository;
import com.idealighter.game.configuration.security.service.DlUserDetailService;
import com.idealighter.game.configuration.security.strategy.AjaxExpiredSessionStrategy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

  // @Autowired
  // private AuthenticationProvider authenticationProvider;
  //
  // @Autowired
  // private void configureGloabl(AuthenticationManagerBuilder auth) throws Exception {
  // auth.authenticationProvider(authenticationProvider);
  // }

  /**
   * AuthenticationProvider定义.
   * 
   * @author houdongsheng
   * @date 2016年5月31日 上午10:48:00
   * @param userDetailsService UserDetailsService对象
   * @return AuthenticationProvider
   */
  @Bean
  @Autowired
  public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DlAuthenticationProvider bean = new DlAuthenticationProvider(userDetailsService);
    return bean;
  }

  /**
   * 配置跨域 .
   *
   * @return 跨域配置.
   */

  @Profile("local")
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:9527"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
    configuration.setAllowCredentials(true);
    configuration.applyPermitDefaultValues();
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  /**
   * 记住密码token仓库.
   * 
   * @author houdongsheng
   * @date 2016年6月8日 下午12:05:45
   * @return PersistentTokenRepository
   */
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    return new DlPersistentTokenRepository();
  }

  /**
   * 记住密码service.
   * 
   * @author houdongsheng
   * @date 2016年6月12日 下午2:57:46
   * @param repository PersistentTokenRepository
   * @return RememberMeServices
   */
  @Bean
  @Autowired
  public RememberMeServices rememberMeServices(PersistentTokenRepository repository) {
    PersistentTokenBasedRememberMeServices bean =
        new PersistentTokenBasedRememberMeServices("dlwriter", userDetailsService(), repository);

    bean.setAlwaysRemember(true); // 一直记住密码、
    bean.setTokenValiditySeconds(60 * 60 * 24 * 7);
    bean.setParameter("remeber");
    return bean;
  }

  /**
   * 登录成功后处理.
   * 
   * @author houdongsheng
   * @date 2016年6月8日 下午12:06:52
   * @return AuthenticationSuccessHandler
   */
  @Bean
  @Autowired
  public AuthenticationSuccessHandler loginSuccessHandler() {
    DlLoginSuccessHandler bean = new DlLoginSuccessHandler();
    return bean;
  }

  /**
   * 登录失败后处理.
   * 
   * @author houdongsheng
   * @date 2016年6月8日 下午12:07:19
   * @return AuthenticationFailureHandler
   */
  @Bean
  public AuthenticationFailureHandler loginFailureHandler() {
    DlLoginFailureHandler bean = new DlLoginFailureHandler();
    return bean;
  }

  /**
   * 退出成功后处理.
   * 
   * @author houdongsheng
   * @date 2016年6月8日 下午12:07:35
   * @return LogoutSuccessHandler
   */
  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    DlLogoutSuccessHandler bean = new DlLogoutSuccessHandler();
    return bean;
  }

  /**
   * 获取用户信息类.
   * 
   * @author houdongsheng
   * @date 2016年5月31日 上午10:48:25
   * @return UserDetailsService
   */
  @Bean
  public UserDetailsService userDetailsService() {
    DlUserDetailService bean = new DlUserDetailService();

    return bean;
  }

  /**
   * 访问拒绝处理.
   * 
   * @author houdongsheng
   * @date 2016年6月14日 下午12:01:47
   * @return AccessDeniedHandler
   */
  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    DlAccessDeniedHandler bean = new DlAccessDeniedHandler();
    return bean;
  }

  @Bean
  public SessionRegistry getSessionRegistry() {
    SessionRegistry sessionRegistry = new SessionRegistryImpl();
    return sessionRegistry;
  }

  @Bean
  public AjaxExpiredSessionStrategy ajaxExpiredSessionStrategy() {
    AjaxExpiredSessionStrategy ajaxExpiredSessionStrategy = new AjaxExpiredSessionStrategy();
    return ajaxExpiredSessionStrategy;
  }

}
