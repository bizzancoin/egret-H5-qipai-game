package com.idealighter.game.configuration.security.utils;

import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

public class SecurityUtil {
  private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME =
      HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");


  public static String csrfSessionAttrbuteName() {
    return DEFAULT_CSRF_TOKEN_ATTR_NAME;
  }
}
