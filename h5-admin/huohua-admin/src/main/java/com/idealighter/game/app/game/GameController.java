package com.idealighter.game.app.game;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.game.convert.GameDtoConvert;
import com.idealighter.game.app.game.dto.GameDto;
import com.idealighter.game.app.game.dto.IdName;
import com.idealighter.game.common.Result;
import com.idealighter.game.service.game.IGameService;
import com.idealighter.game.service.game.bo.GameBo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@Validated
public class GameController extends BaseController {

  @Autowired
  private GameDtoConvert gameDtoConvert;

  @Autowired
  private IGameService gameService;

  /**
   * 获取游戏列表.
   * 
   * @return 游戏列表.
   */
  @GetMapping("/list")
  public Result gameList(@RequestParam(required = false, name = "active") Boolean active) {
    List<GameBo> bos = gameService.findGame(active);
    List<GameDto> dtos = gameDtoConvert.toGameDtos(bos);

    Result result = new Result(true);
    result.getMap().put("list", dtos);
    return result;
  }

  /**
   * 修改游戏状态 .
   *
   * @author abin
   * @date 2018年5月15日 下午4:54:09
   * @param gameId 游戏id.
   * @param active 是否启动.
   * @return 结果.
   */
  @PreAuthorize("hasAuthority('A0102')")
  @PostMapping("/changeState")
  public Result changeState(@RequestParam("id") int gameId,
      @RequestParam("active") Boolean active) {

    gameService.changeState(gameId, active);

    Result result = new Result(true);

    return result;
  }

  /**
   * 房间列表 .
   *
   * @author abin
   * @date 2018年5月17日 上午11:25:18
   * @param gameId 游戏id.
   * @param active 激活状态.
   * @return 结果.
   */
  @GetMapping("/roomlist")
  public Result roomList(@RequestParam("gameId") Integer gameId,
      @RequestParam(required = false, name = "active") Byte active) {

    List<IdName> idNames = gameService.findRoom(gameId, active);

    Result result = new Result(true);

    result.getMap().put("list", idNames);

    return result;
  }


}
