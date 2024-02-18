//package com.idealighter.game.configuration.web;
//
//import com.idealighter.game.app.base.WebConstant;
//import com.idealighter.game.common.ErrorCode;
//import com.idealighter.game.common.Result;
//import com.idealighter.utils.json.JsonUtil;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class SessionFilter implements Filter {
//
//  @Override
//  public void init(FilterConfig filterConfig) throws ServletException {
//
//
//  }
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//      throws IOException, ServletException {
//    HttpServletRequest req = (HttpServletRequest) request;
//    HttpSession session = req.getSession(false);
//    if (session != null && session.getAttribute(WebConstant.ADMIN_ID) != null) {
//      chain.doFilter(request, response);
//    } else {
//      HttpServletRequest httpRequest = (HttpServletRequest) request;
//      HttpServletResponse httpResponse = (HttpServletResponse) response;
//      Result result = new Result();
//      result.changeErrorCode(ErrorCode.UNAUTHORIZED);
//
//      if (httpRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
//        result.successMg();
//        httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:9527");
//        httpResponse.addHeader("Access-Control-Allow-Methods",
//            "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.addHeader("Access-Control-Allow-Headers",
//            "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//
//      }
//
//      response.setContentType("application/json;charset=utf-8");
//      try {
//
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
