package com.idealighter.game.web.provider;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.idealighter.utils.check.EmptyUtil;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Provider
public class SecurityInterceptor implements ContainerRequestFilter {
  private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);


  private String token;

  private boolean backendAuth;

  private static final Response ACCESS_DENIED =
      new ServerResponse("Access denied", 401, new Headers<Object>());


  /**
   * 构造函数.
   * 
   * @param token token.
   * @param backendAuth 授权.
   */
  @Inject
  public SecurityInterceptor(@Named("backend.token") String token,
      @Named("backend.auth") boolean backendAuth) {
    this.token = token;
    this.backendAuth = backendAuth;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

    if (!backendAuth || EmptyUtil.stringIsEmpty(authorizationHeader)
        || !authorizationHeader.equals(token)) {
      LOG.error("ip没有权限,不能发送后台指令");
      requestContext.abortWith(ACCESS_DENIED);
    } else {
      if (LOG.isDebugEnabled()) {
        LOG.info("执行 {} {}", requestContext.getMethod(), requestContext.getUriInfo().getPath());
      }
    }


  }

}
