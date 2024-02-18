package com.idealighter.game.service.player;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.player.bo.PlayerBo;
import com.idealighter.game.service.player.bo.PlayerListBo;

import java.util.List;

public interface IPlayerService {

  List<PlayerBo> selectByQuery(String query, int limit);

  ResultPage<PlayerListBo> selectByPage(Long playerId, Long superId, Byte playerType,
      String nickname, String userName, String phone, String channelId, Boolean locked,
      Integer page, Integer pageSize);

  PlayerBo findById(Long id);

  long countChannel(String channelId);

}
