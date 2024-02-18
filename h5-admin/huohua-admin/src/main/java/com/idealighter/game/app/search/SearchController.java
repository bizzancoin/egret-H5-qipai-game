package com.idealighter.game.app.search;

import com.idealighter.game.app.base.BaseController;
import com.idealighter.game.app.search.convert.SearchDtoConvert;
import com.idealighter.game.app.search.dto.SearchPlayerDto;
import com.idealighter.game.common.Result;
import com.idealighter.game.service.player.IPlayerService;
import com.idealighter.game.service.player.bo.PlayerBo;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Validated
public class SearchController extends BaseController {

  private static final int SEARCH_LIMIT = 20;

  @Autowired
  private IPlayerService playerService;

  @Autowired
  private SearchDtoConvert searchDtoConvert;

  /**
   * 搜索玩家.
   * 
   * @param query 玩家playerId,靓号Id,playerName.
   * @return 结果.
   */
  @GetMapping("/player")
  public Result searchPlayer(@NotEmpty @RequestParam("query") String query) {

    List<PlayerBo> bos = playerService.selectByQuery(query, SEARCH_LIMIT);

    List<SearchPlayerDto> dtos = searchDtoConvert.toSearchPlayerDtos(bos);

    Result result = new Result(true);
    result.getMap().put("list", dtos);

    return result;
  }

}
