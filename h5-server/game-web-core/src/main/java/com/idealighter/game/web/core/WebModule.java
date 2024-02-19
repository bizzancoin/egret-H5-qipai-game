package com.idealighter.game.web.core;

import com.google.inject.PrivateModule;

import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.ibatis.io.ResolverUtil;

public class WebModule extends PrivateModule {

  private static final String PACKAGE_PATH = "com.idealighter.game.web.core";

  @Override
  protected void configure() {
    Set<Class<? extends Object>> providerTypes = new ResolverUtil<Object>()
        .find(new ResolverUtil.AnnotatedWith(Provider.class), PACKAGE_PATH).getClasses();
    for (Class<? extends Object> provider : providerTypes) {
      bind(provider);
      expose(provider);
    }


    Set<Class<? extends Object>> pathTypes = new ResolverUtil<Object>()
        .find(new ResolverUtil.AnnotatedWith(Path.class), PACKAGE_PATH).getClasses();
    for (Class<? extends Object> path : pathTypes) {
      bind(path);
      expose(path);
    }
  }

}
