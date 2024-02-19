package com.idealighter.game.admin.common.controller.convert;

import com.idealighter.game.admin.common.controller.dto.GameListDto;
import com.idealighter.game.core.common.Game;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class GameListDtoConvert {
  public static GameListDtoConvert INSTANCE = Mappers.getMapper(GameListDtoConvert.class);

  /**
   * Game 转 GameListDto.
   * 
   * @Title toGameListDto.
   * @author houdongsheng
   * @date 2018年1月28日 下午3:04:19
   * @param game Game对象
   * @return GameListDto
   */
  public GameListDto toGameListDto(Game game) {
    GameListDto result = null;
    if (game != null) {
      result = new GameListDto();
      result.setType(game.getType());
      result.setDesc(game.getDesc());
      result.setModuleId(game.getModuleId());
      result.setTag(game.getTag());
    }
    return result;
  }

  public abstract List<GameListDto> toGameListDto(List<Game> game);
}
