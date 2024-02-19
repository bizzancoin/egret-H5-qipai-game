
package com.idealighter.game.dictionary.mapper;

import com.idealighter.game.dictionary.domain.ThirdChannelDomain;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ThirdChannelMapper {

  @Select("select * from third_channel")
  @Results({ @Result(property = "channelId", column = "channel_id"),
      @Result(property = "channelName", column = "channel_name"),
      @Result(property = "channelRemark", column = "channel_remark"),
      @Result(property = "channelDesKey", column = "channel_des_key"),
      @Result(property = "channelMd5Key", column = "channel_md5_key") })
  List<ThirdChannelDomain> selectAll();
}
