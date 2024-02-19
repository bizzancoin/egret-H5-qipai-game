package com.idealighter.game.web.core;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

//@Provider
//@Singleton
public class CorsFeature implements Feature {

  @Override
  public boolean configure(FeatureContext context) {

    CorsFilter corsFilter = new CorsFilter();
    corsFilter.getAllowedOrigins().add("*");
    context.register(corsFilter);
    return true;
  }

}
