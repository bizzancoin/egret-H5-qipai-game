package com.idealighter.game;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAutoConfiguration(exclude = {CassandraDataAutoConfiguration.class})
@ComponentScan(value = {"com.idealighter.game", "com.abgame.common.redis"})
public class HuohuaAdminApplication extends SpringBootServletInitializer {


  /**
   * 主入口 .
   * 
   * @param args .
   * @throws Exception .
   */
  public static void main(String[] args) throws Exception {
    ApplicationContext ctx = SpringApplication.run(HuohuaAdminApplication.class, args);
    System.out.println("Let's inspect the beans provided by Spring Boot:");

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      System.out.println(beanName);
    }
  }


}
