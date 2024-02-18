package com.idealighter.game.service.channel.convert;

import com.idealighter.game.dao.dic.po.ThirdChannel;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface ThirdChannelBoConvert {
  ThirdChannelBo toChannelBo(ThirdChannel thirdChannel);

  List<ThirdChannelBo> toChannelBos(List<ThirdChannel> thirdChannels);

  ThirdChannel toThirdChannel(ThirdChannelBo thirdChannelBo);
}
