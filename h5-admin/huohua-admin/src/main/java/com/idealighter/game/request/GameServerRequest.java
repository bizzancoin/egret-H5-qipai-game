package com.idealighter.game.request;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.Result;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.configuration.common.CommonProperties;
import com.idealighter.utils.json.JsonUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class GameServerRequest {
  private static OkHttpClient client = new OkHttpClient.Builder().readTimeout(2, TimeUnit.SECONDS)
      .connectTimeout(2, TimeUnit.SECONDS).writeTimeout(2, TimeUnit.SECONDS).build();

  private static final String PRE_URL = CommonProperties.GAME_SERVER_URL;

  private static final Headers HEADERS =
      new Headers.Builder().add("Authorization", CommonProperties.TOKEN).build();

  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  /**
   * get，请求.
   * 
   * @param url 地址.
   * @param requestParamBuild 请求参数.
   * @return 结果string.
   */
  public static String get(GameServerUrl url, RequestParamBuild requestParamBuild) {
    String result = null;

    Map<String, String> requestParams = requestParamBuild.getMap();
    okhttp3.HttpUrl.Builder urlBuild = okhttp3.HttpUrl.parse(PRE_URL + url.getPath()).newBuilder();
    if (requestParams != null && !requestParams.isEmpty()) {
      for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
        String key = iterator.next();
        if (requestParams.get(key) != null) {
          urlBuild.addEncodedQueryParameter(key, requestParams.get(key));
        }
      }
    }

    HttpUrl httpUrl = urlBuild.build();


    Request request = new Request.Builder().url(httpUrl).headers(HEADERS).build();


    try {
      Response response = client.newCall(request).execute();

      IdeaAssert.isTrue(response.isSuccessful(), ErrorCode.INTERNAL_SERVER_ERROR);

      result = response.body().string();

    } catch (IOException exception) {
      log.error("请求{}失败", request.url(), exception);
      result = JsonUtil.toJson(new Result(ErrorCode.INTERNAL_SERVER_ERROR));
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
  public static String post(GameServerUrl url, RequestParamBuild requestParamBuild) {
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
      result = JsonUtil.toJson(new Result(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    return result;
  }


  /**
   * 发送json请求.
   * 
   * @param url 请求.
   * @param json json文本.
   * @return 结果.
   */
  public static String postJson(GameServerUrl url, String json) {
    String result = null;

    Builder urlBuild = HttpUrl.parse(PRE_URL + url.getPath()).newBuilder();

    HttpUrl httpUrl = urlBuild.build();

    RequestBody requestBody = RequestBody.create(JSON, json);

    Request request = new Request.Builder().url(httpUrl).headers(HEADERS).post(requestBody).build();


    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        result = response.body().string();
      }
    } catch (IOException exception) {
      log.error("请求{}失败", request.url(), exception);
      result = JsonUtil.toJson(new Result(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    return result;
  }

}
