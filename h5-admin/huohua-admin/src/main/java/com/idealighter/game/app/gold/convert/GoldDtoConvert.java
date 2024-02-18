package com.idealighter.game.app.gold.convert;

import com.idealighter.game.app.gold.dto.DailyDto;
import com.idealighter.game.app.gold.dto.GameGoldLogDto;
import com.idealighter.game.app.gold.dto.GoldLogDto;
import com.idealighter.game.app.gold.dto.SafeGoldLogDto;
import com.idealighter.game.app.gold.dto.SendGoldRecordDto;
import com.idealighter.game.app.gold.dto.TransferMoneyGoldLogDto;
import com.idealighter.game.service.gold.bo.DailyBo;
import com.idealighter.game.service.gold.bo.GameGoldLogBo;
import com.idealighter.game.service.gold.bo.GoldLogBo;
import com.idealighter.game.service.gold.bo.SafeGoldLogBo;
import com.idealighter.game.service.gold.bo.SendGoldRecordBo;
import com.idealighter.game.service.gold.bo.TransferMoneyGoldLogBo;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface GoldDtoConvert {
  SendGoldRecordDto toSendGoldRecordDto(SendGoldRecordBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<SendGoldRecordDto> toSendGoldRecordDtos(List<SendGoldRecordBo> bos);


  GoldLogDto toGoldLogDto(GoldLogBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<GoldLogDto> toGoldLogDtos(List<GoldLogBo> bos);

  SafeGoldLogDto toSafeGoldLogDto(SafeGoldLogBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<SafeGoldLogDto> toSafeGoldLogDtos(List<SafeGoldLogBo> list);

  TransferMoneyGoldLogDto toTransferMoneyGoldLogDto(TransferMoneyGoldLogBo bo);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<TransferMoneyGoldLogDto> toTransferMoneyGoldLogDtos(List<TransferMoneyGoldLogBo> list);
  
  GameGoldLogDto toGameGoldLogDto(GameGoldLogBo bo);
  
  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<GameGoldLogDto> toGameGoldLogDtos(List<GameGoldLogBo> bos);
  
  DailyDto toDailyDto(DailyBo bo);
  
  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<DailyDto> toDailyDtos(List<DailyBo> bos);
}
