
package com.idealighter.game.core.util;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络工具类.
 *
 */
public class NetUtil {

  private static final String LOCAL_IP = "127.0.0.1";

  private NetUtil() {}

  /**
   * 远程端口是否可用.
   * 
   * @param ip .
   * @param port .
   * @return .
   */
  public static boolean remotePortAbled(String ip, int port) {
    try (Socket s = new Socket()) {
      SocketAddress add = new InetSocketAddress(ip, port);
      s.connect(add, 3000);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 本机端口是否可用.
   * 
   * @param port .
   * @return .
   */
  public static boolean localPortAbled(int port) {
    try (Socket s = new Socket()) {
      s.bind(new InetSocketAddress(LOCAL_IP, port));
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * 获取请求参数 .
   *
   * @author abin
   * @date 2018年6月14日 下午9:40:01
   * @param query 请求参数.
   * @return map.
   */
  public static Map<String, String> getQueryMap(String query) {
    Map<String, String> map = new HashMap<String, String>();
    if (query != null && !query.isEmpty()) {
      String[] params = query.split("&");
      for (String param : params) {
        String[] subParams = param.split("=");
        String name = "";
        if (subParams.length > 0) {
          name = param.split("=")[0];
        }
        String value = "";
        if (subParams.length > 1) {
          value = param.split("=")[1];
        }

        map.put(name, value);
      }
    }
    return map;
  }



}
