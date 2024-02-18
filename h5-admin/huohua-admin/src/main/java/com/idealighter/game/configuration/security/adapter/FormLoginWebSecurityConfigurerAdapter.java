package com.idealighter.game.configuration.security.adapter;

import com.idealighter.game.configuration.security.handler.DlAuthenticationEntryPoint;
import com.idealighter.game.configuration.security.strategy.AjaxExpiredSessionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法注解
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;
  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;
  @Autowired
  private RememberMeServices rememberMeServices;
  @Autowired
  private LogoutSuccessHandler logoutSuccessHandler;
  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private DlAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private AjaxExpiredSessionStrategy ajaxExpiredSessionStrategy;

  @Autowired
  private SessionRegistry sessionRegistry;

  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository()) // csrf配置
  // //
  // .and() //
  // .authorizeRequests() //
  // .antMatchers("/static/**").permitAll() // 静态文件
  // .anyRequest().authenticated() //
  // // 配置退出
  // .and().logout() //
  // .logoutUrl("/admin/system/login/loginoutAjax") //
  // .logoutSuccessHandler(logoutSuccessHandler) //
  // // 配置登录
  // .and() //
  // .formLogin() // 登录页面
  // .usernameParameter("phone") // 用户名参数
  // .passwordParameter("password") // 密码参数
  // .loginProcessingUrl("/admin/system/login/loginAjax").permitAll() // 登录请求路径
  // .loginPage("/").permitAll() // 登录页面
  // .successHandler(authenticationSuccessHandler) // 授权成功处理
  // .failureHandler(authenticationFailureHandler) // 授权失败处理
  // // 配置记住密码
  // .and() //
  // .rememberMe() // rememberMe设置
  // .key("dlwriter") // 设置key
  // .rememberMeServices(rememberMeServices) // 记住密码
  // // 配置基础http保护
  // .and() //
  // .httpBasic()
  // // 配置异常处理
  // .and() //
  // .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
  // }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors() // 跨域
        .and().csrf().disable() // csrf配置
        .authorizeRequests() //
        .antMatchers("/static/**").permitAll() // 静态文件
        .anyRequest().authenticated() //
        .and().sessionManagement().sessionFixation().migrateSession().maximumSessions(5)
        .maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry)
        .expiredSessionStrategy(ajaxExpiredSessionStrategy).and()
        // 配置退出
        .and().logout() //
        .logoutUrl("/logout") //
        .logoutSuccessHandler(logoutSuccessHandler) //
        .invalidateHttpSession(true)
        // 配置登录
        .and() //
        .formLogin() // 登录页面
        .usernameParameter("username") // 用户名参数
        .passwordParameter("password") // 密码参数
        .loginProcessingUrl("/login").permitAll() // 登录请求路径
        // .loginPage("/").permitAll() // 登录页面
        .successHandler(authenticationSuccessHandler) // 授权成功处理
        .failureHandler(authenticationFailureHandler) // 授权失败处理
        // 配置记住密码
        .and() //
        .rememberMe() // rememberMe设置
        .key("dlwriter") // 设置key
        .rememberMeServices(rememberMeServices) // 记住密码
        // 配置基础http保护
        .and() //
        .httpBasic()
        // 配置异常处理
        .and() //
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler);
  }
}
