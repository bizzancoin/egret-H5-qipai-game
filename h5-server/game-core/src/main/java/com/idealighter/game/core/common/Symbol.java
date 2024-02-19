package com.idealighter.game.core.common;

/**
 * 标点符号常量.
 *
 */
public enum Symbol {
  LBRACE("{"), //
  RBRACE("}"), //
  LBRACKET("["), //
  RBRACKET("]"), //
  COMMA(","), //
  COLON(":");

  public final String val;

  Symbol(String val) {
    this.val = val;
  }
}
