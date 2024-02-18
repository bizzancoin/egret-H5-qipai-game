package com.idealighter.game.service.common.convert;

import com.idealighter.game.service.common.bo.GameListItemBo;
import com.idealighter.game.service.common.sto.GameListItemSto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameListItemBoConvert {
  @Mapping(target = "id", source = "type")
  @Mapping(target = "name", source = "desc")
  @Mapping(target = "tag", source = "tag")
  public GameListItemBo sto2bo(GameListItemSto sto);

  public List<GameListItemBo> sto2bo(List<GameListItemSto> sto);
}
