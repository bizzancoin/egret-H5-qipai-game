package com.idealighter.game.common.util;

import com.idealighter.game.request.IpLocationRequest;
import com.idealighter.game.request.model.IpLocationModel;
import com.idealighter.utils.check.EmptyUtil;
import com.idealighter.utils.json.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class IpUtils {

  public static final String[] LOCAL_IP_PREFIX;

  static {
    LOCAL_IP_PREFIX = new String[] { "192.168", "127.0.0.1" };

  }

  /**
   * ip 转 地址 .
   *
   * @author abin
   * @date 2018年5月28日 上午11:56:02
   * @param ip ip.
   * @return 地址.
   */
  public static String ipToLocation(String ip) {
    String location = "";
    boolean over = false;
    for (String localIp : LOCAL_IP_PREFIX) {
      if (ip.startsWith(localIp)) {
        location = "本地ip";
        over = true;
        break;
      }
    }
    if (!over) {
      String result = IpLocationRequest.getIp(ip);

      if (EmptyUtil.stringIsNotEmpty(result)) {
        JSONObject json;
        try {
          json = new JSONObject(result);

          int ret = json.getInt("ret");
          if (ret == 1) {
            IpLocationModel model = JsonUtil.fromJson(result, IpLocationModel.class);
            location = model.getCountry() + model.getProvince() + model.getCity();
          }
        } catch (JSONException jsonException) {
          // 不处理
        }
      }
    }
    return location;
  }
}
