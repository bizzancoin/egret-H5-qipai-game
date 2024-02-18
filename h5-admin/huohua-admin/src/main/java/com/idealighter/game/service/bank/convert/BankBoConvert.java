package com.idealighter.game.service.bank.convert;

import com.idealighter.game.dao.data.po.TransferGoldRecord;
import com.idealighter.game.service.bank.bo.TransferGoldRecordBo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface BankBoConvert {
  TransferGoldRecordBo toTransferGoldRecordBo(TransferGoldRecord record);

  List<TransferGoldRecordBo> toTransferGoldRecordBos(List<TransferGoldRecord> records);
}
