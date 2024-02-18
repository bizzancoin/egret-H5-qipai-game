package com.idealighter.game.service.channel;

import com.idealighter.game.common.ResultPage;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;

import java.util.List;

public interface IThirdChannelService {

  List<ThirdChannelBo> selectAllChannel();

  ResultPage<ThirdChannelBo> findByPage(String channelId, String channelName, int page,
      int pageSize);

  void save(ThirdChannelBo thirdChannelBo);

  void delete(String channelId);
}
