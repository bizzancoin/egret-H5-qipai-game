package com.idealighter.game.service.common;

import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;

import org.springframework.stereotype.Service;

@Service
public class CommonService implements ICommonService {
  // @Autowired
  // private GameListItemBoConvert gameListItemBoConvert;

  // @Override
  // public List<GameListItemBo> findGameList() {
  // RequestParamBuild paramBuild = new RequestParamBuild();
  //
  // String serviceResponse = GameServerRequest.post(GameServerUrl.COMMON_GAME_LIST, paramBuild);
  // Result responseResult = JsonUtil.fromJson(serviceResponse, Result.class);
  // List<GameListItemBo> result = null;
  // if (responseResult.getCode() == ErrorCode.OK.getCode()) {
  // List<GameListItemSto> list = JsonUtil.fromJsonToList(
  // JsonUtil.toJson(responseResult.getMap().get("list")), GameListItemSto.class);
  // result = gameListItemBoConvert.sto2bo(list);
  // }
  // return result;
  // }
  //
  // @Override
  // public String findRoomListByGameId(Integer gameId) {
  // RequestParamBuild paramBuild = new RequestParamBuild();
  // paramBuild.add("gameId", gameId);
  //
  // return GameServerRequest.post(GameServerUrl.COMMON_GAME_ROOM_LIST, paramBuild);
  // }
  //
  // @Override
  // public String findAllRoomList() {
  // RequestParamBuild paramBuild = new RequestParamBuild();
  //
  // return GameServerRequest.post(GameServerUrl.COMMON_ALL_ROOM_LIST, paramBuild);
  // }

  @Override
  public String findCtlCfgInfo(Integer gameId) {
    RequestParamBuild paramBuild = new RequestParamBuild();
    paramBuild.add("gameId", gameId);

    return GameServerRequest.post(GameServerUrl.COMMON_GAME_CTL_CFG_INFO, paramBuild);
  }
}
