
package com.idealighter.game.core.service.games.ddz.struct;

/**
 * 叫牌类型.
 * 
 * @date 2015年9月4日 下午1:25:06
 *
 */
public enum DdzCallType {
  NOT_CALL_LANDLORD(0, "不叫地主"), CALL_LANDLORD(1, "叫地主"), NOT_GRAB_LANDLORD(2,
      "不抢地主"), GRAB_LANDLORD(3, "抢地主");

  public final int type;
  public final String desc;

  /**
   * 叫牌类型.
   * 
   * @param type .
   * @param desc .
   */
  private DdzCallType(int type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  /**
   * 获取叫牌类型 .
   * 
   * @param type .
   * @return .
   */
  public static DdzCallType getCallType(int type) {
    for (DdzCallType callType : DdzCallType.values()) {
      if (callType.type == type) {
        return callType;
      }
    }

    return null;
  }

  @Override
  public String toString() {

    return name() + "(" + desc + ")";
  }
}
