//package com.idealighter.game.configuration.web;
//
//import com.idealighter.game.app.base.WebConstant;
//import com.idealighter.game.common.ErrorCode;
//import com.idealighter.game.common.Result;
//import com.idealighter.game.common.RouterMap;
//import com.idealighter.utils.check.EmptyUtil;
//import com.idealighter.utils.json.JsonUtil;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.core.annotation.Order;
//
//
//@Order(2)
//@WebFilter(filterName = "permissionFilter", urlPatterns = { "/player/*" })
//public class PermissionFilter implements Filter {
//
//  @Override
//  public void init(FilterConfig filterConfig) throws ServletException {
//    
//  }
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//      throws IOException, ServletException {
//    HttpServletRequest req = (HttpServletRequest) request;
//    HttpSession session = req.getSession(false);
//    boolean hasPermission = false;
//    if (session != null) {
//
//      @SuppressWarnings("unchecked")
//      List<String> permissions =
//          (List<String>) session.getAttribute(WebConstant.ADMIN_PERMISSION);
//
//      String routerPrefix = req.getServletPath();
//
//      if (EmptyUtil.stringIsNotEmpty(routerPrefix)) {
//        int beginIndex = routerPrefix.indexOf('/');
//        int endIndex = routerPrefix.indexOf('/', 1);
//        routerPrefix = routerPrefix.substring(beginIndex, endIndex);
//
//        hasPermission = RouterMap.hasPermission(permissions, routerPrefix);
//
//        if (hasPermission) {
//          chain.doFilter(request, response);
//        }
//      }
//    }
//
//    if (!hasPermission) {
//      response.setContentType("application/json;charset=utf-8");
//      try {
//        Result result = new Result();
//        result.changeErrorCode(ErrorCode.UNAUTHORIZED);
//        PrintWriter writer = response.getWriter();
//        writer.append(JsonUtil.toJson(result));
//        writer.flush();
//        writer.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  @Override
//  public void destroy() {
//   
//  }
//
//}
