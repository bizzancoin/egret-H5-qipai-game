package com.idealighter.game.message.core;

import lombok.Data;

@Data
public class MessageWapper {
  private MessageHeader header;
  private Message msg;

  public MessageWapper() {

  }

  public MessageWapper(MessageHeader header, Message msg) {
    this.header = header;
    this.msg = msg;
  }
}
