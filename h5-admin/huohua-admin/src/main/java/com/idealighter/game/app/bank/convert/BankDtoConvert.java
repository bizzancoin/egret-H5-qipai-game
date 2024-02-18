package com.idealighter.game.app.bank.convert;

import com.idealighter.game.app.bank.dto.TransferGoldRecordDto;
import com.idealighter.game.service.bank.bo.TransferGoldRecordBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface BankDtoConvert {
  TransferGoldRecordDto toTransferGoldRecordDto(TransferGoldRecordBo bo);
  
  List<TransferGoldRecordDto> toTransferGoldRecordDtos(List<TransferGoldRecordBo> bos);
}
