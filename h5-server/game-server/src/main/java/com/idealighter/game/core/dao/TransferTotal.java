package com.idealighter.game.core.dao;

import lombok.Data;

@Data
public class TransferTotal {
  private long total;
  private byte type;
  private byte oppositeType;
}
