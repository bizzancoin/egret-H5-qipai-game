package com.idealighter.game.app.common;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.common.convert.CommonDtoConvert;
import com.idealighter.game.app.common.dto.ChannelSimpleDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.service.channel.IThirdChannelService;
import com.idealighter.game.service.channel.bo.ThirdChannelBo;
import com.idealighter.game.service.common.ICommonService;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {
  @Autowired
  private ICommonService commonService;

  @Autowired
  private IThirdChannelService thirdChannelService;

  @Autowired
  private CommonDtoConvert commonDtoConvert;

  // @Autowired
  // private GameListItemDtoConvert gameListItemDtoConvert;

  // /**
  // * findUserCtrlListInRoom.
  // *
  // * @Description 获取游戏列表
  // * @author houdongsheng
  // * @date 2018年1月26日 下午2:53:53
  // * @return Result
  // */
  // @PostMapping("/findGameList")
  // public Result findGameList() {
  // Result result = new Result(true);
  // List<GameListItemBo> listBo = commonService.findGameList();
  // result.getMap().put("list", gameListItemDtoConvert.bo2dto(listBo));
  // return result;
  // }
  //
  // /**
  // * findRoomListByGameId.
  // *
  // * @Description 获取游戏的房间列表
  // * @author houdongsheng
  // * @date 2018年1月27日 下午7:30:08
  // * @param gameId 游戏编号
  // * @return String 结果（json）
  // */
  // @PostMapping("/findRoomListByGameId")
  // public String findRoomListByGameId(@Min(0) @RequestParam(value = "gameId") Integer gameId) {
  // return commonService.findRoomListByGameId(gameId);
  // }
  //
  // /**
  // * 获取游戏中的所有房间.
  // *
  // * @author houdongsheng
  // * @date 2018年3月21日 下午3:46:29
  // * @return String
  // */
  // @PostMapping("/findAllRoomList")
  // public String findAllRoomList() {
  // return commonService.findAllRoomList();
  // }

  /**
   * findCtlCfgInfo.
   * 
   * @Description 获取游戏控制配置信息
   * @author houdongsheng
   * @date 2018年1月27日 下午7:33:24
   * @param gameId 游戏编号
   * @return String 结果(json)
   */
  @PostMapping("/findCtlInfo")
  public String findCtlCfgInfo(@Min(0) @RequestParam(value = "gameId") Integer gameId) {
    return commonService.findCtlCfgInfo(gameId);
  }

  /**
   * channel 列表 .
   *
   * @author abin
   * @date 2018年8月10日 下午7:35:13
   * @return 渠道列表.
   */
  @GetMapping("/channel")
  public Result channelList() {
    List<ThirdChannelBo> bos = thirdChannelService.selectAllChannel();

    List<ChannelSimpleDto> dtos = commonDtoConvert.toChannelSimpleDtos(bos);

    Result result = new Result(true);
    result.getMap().put("list", dtos);

    return result;
  }
}
