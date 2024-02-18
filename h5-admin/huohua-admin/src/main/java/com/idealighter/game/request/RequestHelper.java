package com.idealighter.game.request;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.utils.check.EmptyUtil;

public class RequestHelper {

  /**
   * 请求开启房间 .
   *
   * @author abin
   * @date 2018年4月29日 下午1:28:23
   * @param gameId 游戏id.
   * @param roomId 房间id.
   * @return 结果 string.
   */
  public static String noticeStartRoom(int gameId, int roomId) {
    RequestParamBuild paramBuild = new RequestParamBuild();

    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);

    String result = null;
    try {
      result = GameServerRequest.post(GameServerUrl.GAME_START_ROOM, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);


      result = RobotServerRequest.post(RobotServerUrl.ROBOT_RELOAD_ROOM, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

    } catch (Exception exception) {
      if (exception instanceof java.net.ConnectException) {
        // 连接错误，不处理
      }
    }
    return result;
  }

  /**
   * 请求关闭房间 .
   *
   * @author abin
   * @date 2018年4月29日 下午1:28:53
   * @param gameId 游戏id
   * @param roomId 房间id.
   * @return 结果string.
   */
  public static String noticeShutdownRoom(int gameId, int roomId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);
    paramBuild.add("roomId", roomId);

    String result = null;
    try {

      result = GameServerRequest.post(GameServerUrl.GAME_SHUTDOWN_ROOM, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

      result = RobotServerRequest.post(RobotServerUrl.ROBOT_RELOAD_ROOM, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

    } catch (Exception exception) {
      if (exception instanceof java.net.ConnectException) {
        // 连接错误，不处理
      }
    }

    return result;
  }


  /**
   * 通知游戏开启 .
   *
   * @author abin
   * @date 2018年5月16日 下午1:59:39
   * @param gameId 游戏id.
   * @return 通知游戏开启.
   */
  public static String noticeGameStart(int gameId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);

    String result = null;
    try {

      result = GameServerRequest.post(GameServerUrl.GAME_START, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

      result = RobotServerRequest.post(RobotServerUrl.ROBOT_RELOAD_GAME, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

    } catch (Exception exception) {
      if (exception instanceof java.net.ConnectException) {
        // 连接错误，不处理
      }
    }

    return result;
  }

  /**
   * 通知游戏关闭.
   *
   * @author abin
   * @date 2018年5月16日 下午1:59:59
   * @param gameId 游戏id.
   * @return 通知结果.
   */
  public static String noticeGameShutdown(int gameId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);

    String result = null;
    try {

      result = GameServerRequest.post(GameServerUrl.GAME_SHUTDOWN, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

      result = RobotServerRequest.post(RobotServerUrl.ROBOT_RELOAD_GAME, paramBuild);

      IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);

    } catch (Exception exception) {
      if (exception instanceof java.net.ConnectException) {
        // 连接错误，不处理
      }
    }

    return result;
  }
}
