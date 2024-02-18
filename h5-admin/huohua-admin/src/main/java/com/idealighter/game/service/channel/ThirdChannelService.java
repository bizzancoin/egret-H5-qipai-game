package com.idealighter.game.service.channel;

import com.idealighter.game.common.ErrorCode;
import com.idealighter.game.common.ResultPage;
import com.idealighter.game.common.assertions.IdeaAssert;
import com.idealighter.game.dao.dic.mapper.ThirdChannelMapper;
import com.idealighter.game.dao.dic.po.ThirdChannel;
import com.idealighter.game.dao.dic.po.ThirdChannelExample;
import com.idealighter.game.request.GameServerRequest;
import com.idealighter.game.request.GameServerUrl;
import com.idealighter.game.request.RequestParamBuild;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;
import com.idealighter.game.service.channel.convert.ThirdChannelBoConvert;
import com.idealighter.game.service.player.IPlayerService;
import com.idealighter.utils.check.EmptyUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ThirdChannelService implements IThirdChannelService {

  @Autowired
  private ThirdChannelMapper mapper;

  @Autowired
  private IPlayerService playerService;

  @Autowired
  private ThirdChannelBoConvert convert;

  @Autowired
  @Value("default.channel.id")
  private String defaultChannel;

  @Override
  public ResultPage<ThirdChannelBo> findByPage(String channelId, String channelName, int page,
      int pageSize) {

    ThirdChannelExample example = new ThirdChannelExample();
    example.setLimit(pageSize);
    example.setOffset((page - 1) * pageSize);

    example.setOrderByClause("channel_id desc");

    ThirdChannelExample.Criteria criteria = example.createCriteria();

    if (EmptyUtil.stringIsNotEmpty(channelId)) {
      criteria.andChannelIdEqualTo(channelId);
    }
    if (EmptyUtil.stringIsNotEmpty(channelName)) {
      criteria.andChannelNameEqualTo(channelName);
    }

    List<ThirdChannel> thirdChannels = mapper.selectByExample(example);

    long total = mapper.countByExample(example);

    List<ThirdChannelBo> thirdChannelBos = convert.toChannelBos(thirdChannels);

    ResultPage<ThirdChannelBo> resultPage = new ResultPage<>();

    resultPage.setList(thirdChannelBos);
    resultPage.setTotal(total);

    return resultPage;
  }

  @Override
  public void save(ThirdChannelBo thirdChannelBo) {

    ThirdChannel thirdChannel = mapper.selectByPrimaryKey(thirdChannelBo.getChannelId());

    IdeaAssert.isTrue(!thirdChannelBo.getChannelId().equals(defaultChannel),ErrorCode.ACCOUNT_FAIL);

    ThirdChannel updateChannel = convert.toThirdChannel(thirdChannelBo);
    int out = 0;
    if (thirdChannel != null) {
      out = mapper.updateByPrimaryKeySelective(updateChannel);
    } else {
      out = mapper.insertSelective(updateChannel);
    }

    if (out > 0) {
      RequestParamBuild paramBuild = new RequestParamBuild();

      try {
        String result = GameServerRequest.post(GameServerUrl.CHANNEL_RELOAD, paramBuild);

        IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);
      } catch (Exception exception) {
        if (exception instanceof java.net.ConnectException) {
          // 连接错误，不处理
        }
      }
    }
  }

  @Override
  public void delete(String channelId) {

    boolean hasPlayer = playerService.countChannel(channelId) > 0;

    IdeaAssert.isTrue(!hasPlayer, ErrorCode.CHANNEL_HAS_PEOPLE);

    int out = mapper.deleteByPrimaryKey(channelId);

    if (out > 0) {
      RequestParamBuild paramBuild = new RequestParamBuild();

      try {
        String result = GameServerRequest.post(GameServerUrl.CHANNEL_RELOAD, paramBuild);

        IdeaAssert.isTrue(EmptyUtil.stringIsNotEmpty(result), ErrorCode.INTERNAL_SERVER_ERROR);
      } catch (Exception exception) {
        if (exception instanceof java.net.ConnectException) {
          // 连接错误，不处理
        }
      }
    }
  }

  @Override
  public List<ThirdChannelBo> selectAllChannel() {
    List<ThirdChannel> thirdChannels = mapper.selectByExample(new ThirdChannelExample());
    return convert.toChannelBos(thirdChannels);
  }

}
