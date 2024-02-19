package com.idealighter.game.core.service.plaza.manager;
//
// package com.idealighter.game.plaza.manager;
//
// import com.google.inject.Inject;
// import com.google.inject.Singleton;
//
// import com.idealighter.game.dictionary.dic.GameTypeDic;
// import com.idealighter.game.dictionary.domwrapper.GameTypeDomainWrapper;
// import com.idealighter.game.player.manager.PlayerMsgWriter;
// import com.idealighter.game.player.struct.Player;
// import com.idealighter.game.plaza.dto.GameType;
// import com.idealighter.game.plaza.message.ResGameTypesMsg;
//
// import java.util.ArrayList;
// import java.util.Collections;
//
/// **
// . * 游戏广场消息管理.
//
// * @date 2015年11月14日 上午11:56:13
// *
// . */
// @Singleton
// public class PlazaMsgMgr {
//
// @Inject
// private PlayerMsgWriter msgWriter;
// @Inject
// private GameTypeDic gameTypeDic;
//
// /**
// . * 发送游戏类型消息 .
// * @param player 玩家.
// */
// public void sendGameTypesMsg(Player player) {
// ResGameTypesMsg msg = new ResGameTypesMsg();
// for (GameTypeDomainWrapper gameTypeDomain : gameTypeDic.list()) {
// GameType gameTypeDto = new GameType();
// if (gameTypeDomain.getId() == 0) { // 我的游戏
// ArrayList<Integer> games = new ArrayList<>(player.games());
// Collections.reverse(games);
// gameTypeDto.setGames(games);
// } else {
// gameTypeDto.setGames(gameTypeDomain.games());
// }
//
// gameTypeDto.setIndex(gameTypeDomain.getIndex());
// gameTypeDto.setType(gameTypeDomain.getId());
// msg.getTypes().add(gameTypeDto);
// }
//
// msgWriter.writeMsg(player, msg);
// }
// }
