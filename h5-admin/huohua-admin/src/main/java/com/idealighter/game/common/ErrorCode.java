package com.idealighter.game.common;

/**
 * 错误分类： .
 * 
 * <h2>1.公共错误</h2>
 * <ul>
 * <li>1.1 可见通用错误 【0-9999】</li>
 * <li>1.2 不可见通用错误【10000-19999】</li>
 * </ul>
 * <h2>2.业务错误（一个业务有2和3开头的编码，用来区分客户端是否提醒）</h2>
 * <ul>
 * <li>2.1 用户可见【2 ** **】 【20000-29999】 **业务编号 **错误编号</li>
 * <li>2.2 用户不可见【3 ** **】 【30000-39999】 **业务编号 **错误编号</li>
 * </ul>
 * 
 * <h2>2.系统模块</h2>
 * <ul>
 * <li>M00系统用户</li>
 * <li>M01用户管理</li>
 * <li>M02代理管理</li>
 * <li>M03通告管理</li>
 * <li>M04邮件管理</li>
 * <li>M05商店配置</li>
 * <li>M06充值管理</li>
 * <li>M07任务系统配置</li>
 * <li>M08活动管理</li>
 * <li>M09统计系统</li>
 * <li>M94智能系统</li>
 * <li>M95游戏管理</li>
 * <li>M96操作日志</li>
 * <li>M97管理员管理</li>
 * <li>M98系统更新</li>
 * <li>M99系统设置</li>
 * </ul>
 * 
 */
public enum ErrorCode {

  /**
   * OK : 操作成功.
   */
  OK(200, "操作成功"),

  /**
   * BAD_REQUEST : 参数出错.
   */
  BAD_REQUEST(400, "参数出错"),

  /**
   * AUTH_FAIL : 用户不合法.
   */
  UNAUTHORIZED(401, "用户不合法"),

  /**
   * 拒绝访问.
   */
  FORBIDDEN(403, "拒绝访问"),

  /**
   * 404.
   */
  NOT_FOUND(404, "404"),

  /**
   * 发送了太多请求.
   */
  TOO_MANY_REQUESTS(429, "发送了太多请求"),

  /**
   * 远程服务出错.
   */
  INTERNAL_SERVER_ERROR(500, "远程服务出错"),



  /**
   * 系统正在维护 .
   */
  SYSTEM_MAINTAINING(9999, "系统正在维护"),


  ACCOUNT_FAIL(10001, "账号密码错误"),

  ACCOUNT_LOCK(10002, "账号被锁定"),

  ACCOUNT_OLD_PWD_FAIL(10003, "用户旧密码错误"),

  PLAYER_NOT_EXIST(10004, "玩家不存在"),

  ROOM_NOT_EXIST(10005, "房间不存在"),

  ROBOT_CFG_EXIST_CFG(10006, "该房间的配置已存在"),

  ROBOT_CFG_CFG_NOT_EXIST(10007, "该房间的配置不存在"),

  ROBOT_CFG_CAN_NOT_UPDATE_GAMEID_ROOMID(10008, "不能修改游戏或者房间"),


  CHANNEL_HAS_PEOPLE(10009, "该渠道已经有注册用户，不能删除"),

  CHANNEL_NO_DEFAULT(10010, "该渠道号是默认渠道号，请更换"),

  ACCOUNT_CHANGE(10011, "帐号被修改")

  ;
  private final int code;
  private final String message;

  private ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String toString() {
    return code + ": " + message;
  }
}
