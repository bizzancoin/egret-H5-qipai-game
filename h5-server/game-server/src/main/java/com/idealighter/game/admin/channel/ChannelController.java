package com.idealighter.game.admin.channel;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.idealighter.game.core.result.Result;
import com.idealighter.game.dictionary.dic.ThirdChannelDic;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/channel")
@Singleton
@Produces({ MediaType.APPLICATION_JSON })
public class ChannelController {

  private ThirdChannelDic thirdChannelDic;

  @Inject
  public ChannelController(ThirdChannelDic thirdChannelDic) {
    this.thirdChannelDic = thirdChannelDic;
  }

  /**
   * 重载渠道 .
   *
   * @author abin
   * @date 2018年8月10日 下午1:59:01
   * @return 重载结果.
   */
  @Path("/reload")
  @POST
  public Result reload() {
    thirdChannelDic.load();
    return new Result(true);
  }
}
