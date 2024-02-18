package com.idealighter.game.request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IpLocationRequest {

  private static final String IP_TOOL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php";

  /**
   * get，请求.
   * 
   * @param ip ip地址.
   * @return 结果string.
   */
  public static String getIp(String ip) {
    String result = null;

    okhttp3.HttpUrl.Builder urlBuild = okhttp3.HttpUrl.parse(IP_TOOL).newBuilder();

    urlBuild.addQueryParameter("ip", ip);
    urlBuild.addQueryParameter("format", "json");

    HttpUrl httpUrl = urlBuild.build();


    Request request = new Request.Builder().url(httpUrl).build();

    try {
      OkHttpClient client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS)
          .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).build();

      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        result = response.body().string();
      }

    } catch (IOException exception) {
      result = null;
    }
    return result;
  }
}
