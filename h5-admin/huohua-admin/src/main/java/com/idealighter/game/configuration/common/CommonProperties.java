package com.idealighter.game.configuration.common;

import javax.annotation.PostConstruct;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "common")
public class CommonProperties {

  private String gameServerUrl = "";

  private String robotServerUrl = "";

  private String token = "";


  public static String GAME_SERVER_URL;

  public static String ROBOT_SERVER_URL;

  public static String TOKEN;



  /**
   * initConstants.
   * @Description 初始化静态变量.
   * @author cjb
   * @date 2016年7月14日 上午10:54:55
   */
  @PostConstruct
  public void initConstants() {

    TOKEN = token;

    GAME_SERVER_URL = gameServerUrl;

    ROBOT_SERVER_URL = robotServerUrl;
  }

}
