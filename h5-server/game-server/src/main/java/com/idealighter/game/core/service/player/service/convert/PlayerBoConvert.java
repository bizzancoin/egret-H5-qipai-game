package com.idealighter.game.core.service.player.service.convert;

import com.idealighter.game.core.convert.DateLongValueConvert;
import com.idealighter.game.core.convert.LongAtomicLongConvert;
import com.idealighter.game.core.dao.generate.domain.PlayerGameInfoDomainWithBLOBs;
import com.idealighter.game.core.dao.generate.domain.PlayerInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerMainDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerSafeInfoDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerStatusRecordDomain;
import com.idealighter.game.core.dao.generate.domain.PlayerVipDomain;
import com.idealighter.game.core.service.player.service.bo.PlayerBo;
import com.idealighter.game.core.service.player.service.bo.PlayerRankBo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DateLongValueConvert.class, LongAtomicLongConvert.class })
public interface PlayerBoConvert {

  PlayerBoConvert INSTANCE = Mappers.getMapper(PlayerBoConvert.class);

  @Mapping(target = "id", source = "mainDomain.id")
  PlayerBo toPlayerBo(PlayerMainDomain mainDomain, PlayerInfoDomain playerInfoDomain,
      PlayerGameInfoDomainWithBLOBs playerGameInfoDomain, PlayerSafeInfoDomain playerSafeInfoDomain,
      PlayerVipDomain playerVipDomain, PlayerStatusRecordDomain playerStatusRecordDomain);


  PlayerMainDomain toPlayerMainDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "id", ignore = true)
  PlayerInfoDomain toPlayerInfoDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "id", ignore = true)
  PlayerGameInfoDomainWithBLOBs toPlayerGameInfoDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "id", ignore = true)
  PlayerSafeInfoDomain toPlayerSafeInfoDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "id", ignore = true)
  PlayerVipDomain toPlayerVipDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  @Mapping(target = "id", ignore = true)
  PlayerStatusRecordDomain toPlayerStatusRecordDomain(PlayerBo bo);

  @Mapping(target = "playerId", source = "id")
  PlayerRankBo toPlayerRankBo(PlayerMainDomain playerMainDomain);

  List<PlayerRankBo> toPlayerRankBos(List<PlayerMainDomain> playerMainDomains);
}
