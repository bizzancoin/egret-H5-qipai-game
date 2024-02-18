package com.idealighter.game.request;

import com.idealighter.game.configuration.common.CommonProperties;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class RobotServerRequest {
  private static OkHttpClient client = new OkHttpClient.Builder().readTimeout(2, TimeUnit.SECONDS)
      .connectTimeout(2, TimeUnit.SECONDS).writeTimeout(2, TimeUnit.SECONDS).build();

  private static final String PRE_URL = CommonProperties.ROBOT_SERVER_URL;

  private static final Headers HEADERS =
      new Headers.Builder().add("Authorization", CommonProperties.TOKEN).build();


  /**
   * get，请求.
   * 
   * @param url 地址.
   * @param requestParamBuild 请求参数.
   * @return 结果string.
   */
  public static String get(RobotServerUrl url, RequestParamBuild requestParamBuild) {
    String result = null;

    Map<String, String> requestParams = requestParamBuild.getMap();
    okhttp3.HttpUrl.Builder urlBuild = okhttp3.HttpUrl.parse(PRE_URL + url).newBuilder();
    if (requestParams != null && !requestParams.isEmpty()) {
      for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
        String key = iterator.next();
        urlBuild.addEncodedQueryParameter(key, requestParams.get(key));
      }
    }

    HttpUrl httpUrl = urlBuild.build();


    Request request = new Request.Builder().url(httpUrl).build();


    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        result = response.body().string();
      }
    } catch (IOException exception) {
      log.error("请求{}失败", request.url(), exception);
    }

    return result;
  }



  /**
   * post，请求.
   * 
   * @param url 地址.
   * @param requestParamBuild 请求参数.
   * @return 结果string.
   */
  public static String post(RobotServerUrl url, RequestParamBuild requestParamBuild) {
    String result = null;

    Map<String, String> requestParams = requestParamBuild.getMap();
    Builder urlBuild = HttpUrl.parse(PRE_URL + url.getPath()).newBuilder();
    okhttp3.FormBody.Builder formBody = new FormBody.Builder();
    if (requestParams != null && !requestParams.isEmpty()) {
      for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
        String key = iterator.next();
        formBody.addEncoded(key, requestParams.get(key));
      }
    }

    HttpUrl httpUrl = urlBuild.build();

    RequestBody requestBody = formBody.build();

    Request request = new Request.Builder().url(httpUrl).headers(HEADERS).post(requestBody).build();


    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        result = response.body().string();
      }
    } catch (IOException exception) {
      log.error("请求{}失败", request.url(), exception);
    }

    return result;
  }

}
