//package com.idealighter.game.configuration.web;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//@Profile("local")
//public class DevWebConfig extends WebMvcConfigurerAdapter {
//
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**").allowCredentials(true).allowedHeaders("*").allowedMethods("*")
//        .allowedOrigins("http://localhost:9527");
//    super.addCorsMappings(registry);
//  }
//
//  /**
//   * web拦截器.
//   * 
//   * @return web拦截器.
//   */
//  @Bean
//  public FilterRegistrationBean getSeesionFilter() {
//    SessionFilter sessionFilter = new SessionFilter();
//    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//    registrationBean.setFilter(sessionFilter);
//    List<String> urlPatterns = new ArrayList<String>();
//    urlPatterns.add("/logout");
//    urlPatterns.add("/account/*");
//    urlPatterns.add("/withdraw/*");
//    urlPatterns.add("/user/*");
//    urlPatterns.add("/notice/*");    
//    urlPatterns.add("/mail/*");
//    urlPatterns.add("/recharge/*");
//    urlPatterns.add("/common/*");
//    urlPatterns.add("/games/*");
//    urlPatterns.add("/stat/*");
//    urlPatterns.add("/agent/*");
//    urlPatterns.add("/gold/*");
//    urlPatterns.add("/bank/*");
//    urlPatterns.add("/robot/*");
//    urlPatterns.add("/roomCtl/*");  
//    urlPatterns.add("/sysconfig/*");
//    urlPatterns.add("/playerCtl/*");
//    urlPatterns.add("/channel/*");
//    registrationBean.setUrlPatterns(urlPatterns);
//    registrationBean.setOrder(1);
//    return registrationBean;
//  }
//}
