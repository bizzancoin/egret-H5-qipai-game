package com.idealighter.game.request;

public enum GameServerUrl {
  PLAYER_ONLINE_USER("/player/onlinePlayers"),

  PLAYER_MODIFY_INFO("/player/modify"),

  PLAYER_ADD_GOLD("/player/addGold"),

  PLAYER_LOCK_PLAYER("/player/lock"),

  PLAYER_UNLOCK_PLAYER("/player/unlock"),

  PLAYER_CHANGE_USERTYPE_SUPERID("/player/changeUserTypeAndSuperId"),

  NEW_EMAIL("/notice/newemail"),

  NOTICE_MARQUEE("/notice/marquee"),

  // =============================
  PERSONCTL_PERSON_LIST("/personctl/playerLists"),

  PERSONCTL_PERSON_DETAIL("/personctl/playerDetail"),

  PERSONCTL_ADD("/personctl/addPlayerCtl"),

  PERSONCTL_UPDATE_GOLD("/personctl/updatePrizePoolGold"),

  PERSONCTL_UPDATE_CTL("/personctl/updateCtl"),
  // =============================
  // COMMON_GAME_LIST("/common/findGameList"),

  // COMMON_GAME_ROOM_LIST("/common/findRoomListByGameId"),

  // COMMON_ALL_ROOM_LIST("/common/findAllRoomList"),

  COMMON_GAME_CTL_CFG_INFO("/common/findCtlCfgInfo"),
  // =============================
  ROOMCTL_DETAIL("/roomCtl/findCtlInfo"),

  ROOMCTL_DEFAULT_DETAIL("/roomCtl/findDefaultPrizePool"),

  ROOMCTL_GOLD_DETAIL("/roomCtl/findGoldPrizePool"),

  ROOMCTL_ADD_DEFAULT_PRIZE_POOL("/roomCtl/addDefaultPrizePool"),

  ROOMCTL_ADD_DEFAULT_PRIZE_POOL_GOLD("/roomCtl/addDefaultPrizePoolGold"),

  ROOMCTL_MODIFY_DEFAULT_PRIZE_POOL_GOLD("/roomCtl/modifyDefaultPrizePoolGold"),

  ROOMCTL_ADD_DEFAULT_PRIZE_POOL_STRATEGY("/roomCtl/addDefaultPrizePoolStrategy"),

  ROOMCTL_MODIFY_DEFAULT_PRIZE_POOL_STRATEGY("/roomCtl/modifyDefaultPrizePoolStrategy"),

  ROOMCTL_DEL_DEFAULT_PRIZE_POOL_STRATEGY("/roomCtl/delDefaultPrizePoolStrategy"),

  ROOMCTL_DEL_DEFAULT_PRIZE_POOL("/roomCtl/delDefaultPrizePool"),


  ROOMCTL_ADD_GOLD_PRIZE_POOL("/roomCtl/addGoldPrizePool"),

  ROOMCTL_DEL_GOLD_PRIZE_POOL("/roomCtl/delGoldPrizePool"),

  ROOMCTL_ADD_GOLD_PRIZE_POOL_GOLD("/roomCtl/addGoldPrizePoolGold"),

  ROOMCTL_MODIFY_GOLD_PRIZE_POOL_GOLD("/roomCtl/modifyGoldPrizePoolGold"),

  ROOMCTL_MODIFY_GOLD_PRIZE_POOL_RANGE("/roomCtl/modifyGoldPrizePoolRange"),

  ROOMCTL_ADD_GOLD_PRIZE_POOL_STRATEGY("/roomCtl/addGoldPrizePoolStrategy"),

  ROOMCTL_MODIFY_GOLD_PRIZE_POOL_STRATEGY("/roomCtl/modifyGoldPrizePoolStrategy"),

  ROOMCTL_DEL_GOLD_PRIZE_POOL_STRATEGY("/roomCtl/delGoldPrizePoolStrategy"),
  // =============================
  GAME_CLEARDATA("/game/clearPlayerData"),

  GAME_SHUTDOWNSERVER("/game/shutdownServer"),

  GAME_START_ROOM("/game/startRoom"),

  GAME_SHUTDOWN_ROOM("/game/shutdownRoom"),

  GAME_START("/game/startGame"),

  GAME_SHUTDOWN("/game/shutdownGame"),

  CHANNEL_RELOAD("/channel/reload");

  private String path;



  public String getPath() {
    return path;
  }



  private GameServerUrl(String path) {
    this.path = path;
  }
}
