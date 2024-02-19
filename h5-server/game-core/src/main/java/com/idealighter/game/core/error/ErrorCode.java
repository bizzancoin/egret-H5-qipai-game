package com.idealighter.game.core.error;

/**
 * 错误分类：.
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
 * <li>用户（00）(系统用户，用户，VIP，代理)</li>
 * <li>通告（01）</li>
 * <li>邮件（02）</li>
 * <li>银行（03）</li>
 * <li>排行榜（04）</li>
 * <li>任务系统（05）</li>
 * <li>商店系统（06）</li>
 * <li>充值系统（07）</li>
 * <li>游戏（50）</li>
 * <li>水浒传（51）</li>
 * <li>水果拉霸（52）</li>
 * <li>飞禽走兽（53）</li>
 * <li>金蟾捕鱼（54）</li>
 * </ul>
 * 
 */
public enum ErrorCode {

  /**
   * OK : 操作成功.
   */
  OK(200, "成功"),

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
   * EXCEPTION : 远程服务出错.
   */
  INTERNAL_SERVER_ERROR(500, "远程服务出错"),

  /**
   * SERVICE_UNAVAILABLE.
   */
  SERVICE_UNAVAILABLE(503, "对不起，服务器负载过重，请稍后登录！"),

  /**
   * SMS_FAIL : 短信校验码输入有误.
   */
  SMS_FAIL(600, "短信校验码输入有误"),

  /**
   * SMS_LIMIT_FAIL : 短信次数到达.
   */
  SMS_LIMIT_FAIL(601, "短信次数到达"),
  /**
   * SMS_SEND_FAIL : 短信发送失败.
   */
  SMS_SEND_FAIL(602, "短信发送失败"),

  /**
   * 短信验证码过期.
   */
  SMS_EXPIRE(603, "短信验证码过期"),

  /**
   * 短信验证码太过频繁，请稍后再试.
   */
  SMS_FREQUENT(604, "短信验证码太过频繁，请稍后再试"),

  /**
   * IMGCODE_FAIL : 图片校验码出错.
   */
  IMGCODE_FAIL(605, "图片校验码出错"),

  /**
   * ALREADY_UP_TO_DATE : 已是最新版本.
   */
  ALREADY_UP_TO_DATE(606, "已是最新版本"),



  /**
   * 系统正在维护 .
   */
  SYSTEM_MAINTAINING(9999, "系统正在维护"),

  THIRD_SIGN_ERROR(10001, "签名错误"),

  THIRD_USER_NOT_EXIST(10002, "用户不存在"),

  THIRD_USER_PWD_NOT_MATCH(10003, "账号密码错误"),

  THIRD_REMAIN_NO_ENOUGH(10004, "游戏余额不足"),

  THIRD_USER_NAME_EXIST(10005, "用户名已经注册"),

  THIRD_USER_LOCKED(10006, "帐号被锁定"),


  // -------用户（00） start-------//
  /**
   * SER_LOCK : 用户被锁定.
   */
  USER_LOCK(20001, "用户被锁定"),

  USER_NOT_EXIST(20002, "对不起，该用户名不存在，登陆失败！ "),

  USER_LOGIN_FAIL(20003, "对不起，您登陆失败！"),

  USER_NAME_CODE_NOT_MATCH(20004, "对不起，您登录的用户名和获取验证码的用户名不匹配！"),

  USER_NAME_ERROR(20005, "对不起，您注册的名字必须为2至12个中文字符！"),

  USER_ACCOUNT_INCLUDE_SENSITIVE_WORD(20006, "对不起，您注册的账号有敏感词汇！"),

  USER_IDCARD_ERROR(20008, "对不起，您注册的填写的身份证格式不正确！"),

  USER_REFERRER_ERROR(20009, "对不起，您注册的填写的推荐人必须为数字！"),

  USER_NOT_BIND_PHONE(20010, "对不起，您还没有绑定手机，不能手机登录！"),


  NOT_TOURIST_COULD_NOT_UPDATE(20011, "对不起，您的账号非游客,无法升级！"),

  PWD_FORMATE_INVALID(20012, "对不起，密码必须是6~30个字母、数字或特殊符号！"),

  PHONE_HAD_BIND(20013, "对不起, 手机号码已绑定, 请绑定其他号码"),

  SMS_CODE_ERROR(20014, "短信验证法错误"),

  USER_NICKNAME_INCLUDE_SENSITIVE_WORD(20015, "对不起，您注册的昵称有敏感词汇！"),

  USER_NICKNAME_EXIST(20016, "对不起，用户昵称已存在！"),

  USER_ALREADY_BIND_PHONE(20017, "对不起，已经绑定手机"),

  USER_SUPERID_EXIST(20018, "对不起，该靓号已经存在"),

  PLAYER_GOLD_NOT_ENOUGH(20050, "对不起，您的金币不足！"),

  PLAYER_GOLD_BEYOND_MAX_GOLD(20051, "对不起，您的金币超出房间限制，请前往高级房间"),

  ROBOT_HAD_LOGED(20052, "机器人不能重复登录"),

  PLAYER_CHIP_NOT_ENOUGH(20053, "对不起，您的筹码不足！"),

  // -------用户（00） end---------//

  // -------通告（01） start-------//

  // -------通告（01） end---------//



  // -------邮件（02） start------//

  // -------邮件（02） end--------//

  // -------银行(03) start------//

  BANK_TRANSFER_MIN(20301, "对不起，银行转账金额不能低于{1}！"),

  BANK_PWD_ERROR(20302, "对不起，您的银行密码输入错误"),

  BANK_NOT_ENOUGH_GOLD_TRANSFER(20303, "真遗憾，您的银行余额不足，转账失败！"),

  BANK_NOT_FIND_USER(20304, "对不起，您输入的ID不存在，请核对您要转账的玩家ID后重新输入！"),

  BANK_CAN_TRANSFER(20305, "对不起，您没有转账权限！"),

  BANK_NOT_ENOUGH_GOLD_TOSAVE(20306, "真遗憾，您没有那么多钱可存！"),

  BANK_NOT_ENOUGH_GOLD_WITHDRAW(20307, "真遗憾，您没有那么多钱可取！"),

  BANK_TRANSFER_MSG(20308, "玩家{1}向您转入{2}金币，请前往银行查收！"),

  BANK_TRANSFER_REWARD_NOT_ENOUGH(20309, "礼品盒金币不足"),

  // -------银行(03) end--------//

  // -------排行榜（04） start------//

  // -------排行榜（04） end--------//

  // -------任务系统（05） start------//

  TASK_SIGNIN_ALREAY(20501, "对不起，今天已经不能重复签到！"),

  TASK_SIGNIN_IP_MAX(20502, "对不起，该ip已经达到签到上限！"),

  TASK_SIGNIN_SUCCESS(20503, "签到成功，奖励{1}金币！ "),

  TASK_SIGNIN_GET_MAX(20504, "对不起，已经超过领取福利超过上限！"),

  TASK_SIGNIN_IP_GET_MAX(20505, "对不起，已经超过ip领取福利次数限制不能领取！"),

  TASK_SIGNIN_NOT_MATCH_REQUIRE(20506, "您还那么有钱，还是不要来抢这点救济金了吧！"),

  TASK_SIGNIN_SUCCESS2(20507, "领取成功，奖励{1}金币。快去翻本吧！"),

  TASK_ONLINE_REWARD(20508, "恭喜您获得{1}金币的在线奖励，已帮您存入银行，请前往银行查看！"),

  TASK_GAME_REWARD(20509, "恭喜您获得{1}金币的游戏奖励，已帮您存入银行，请前往银行查看！"),

  TASK_SHARE_REWARD(20510, "分享成功，奖励{1}金币！"),



  // -------任务系统（05） end--------//

  // -------商店系统（06） start------//



  // -------商店系统（06） end--------//


  // -------充值系统（07） start------//
  RECHARGE_ITEM_NOT_EXIST(20701, "充值选项不存在"),
  // -------充值系统（07） end--------//


  // -------游戏（50） start------//


  GAME_ALREADY_IN(25001, "对不起，您的账号在{1}游戏中，暂时无法进入游戏"),

  GAME_ROOM_FULL(25002, "对不起，当前房间玩家已满，试试其他房间吧！"),

  GAME_SET_NOT_EMPTY(25003, "对不起，该座位已被{1}捷足先登了，请重新寻找座位！"),

  GAME_BELOW_MIN_GOLD(25004, "对不起，您的金币低于进入的房间下限{1}金币，进入房间失败！"),

  GAME_BEYOND_MAX_GOLD(25005, "对不起，您的金币高于进入的房间上限{1}金币，进入房间失败！"),

  GAME_ONLY_FOR_VIP(25007, "对不起，房间设置功能仅对会员开放，请到商城购买会员卡成为会员后进行设置！"),

  GAME_ROOM_PWD_ERROR(25008, "对不起，进桌失败，您的密码不对，请核对后重新输入！"),

  GAME_NOT_PLAY_WITH_IP(25009, "对不起，房主设置了不和同ip的玩家一起游戏！"),

  GAME_NOT_PLAY_WITH_MIN_GOLD(25010, "对不起，房主设置了不和金币少于{1}多少的玩家一起游戏！"),

  GAME_NOT_VIP_ROOM_FULL(25012, "对不起，该房间人数满，去其他房间看看吧！"),

  GAME_NOT_ENOUGH_FEE(25013, "对不起，金币不足扣除台费，不能进入桌子！"),

  GAME_NOT_SIT(25014, "对不起，已经没有空位置了，去其他房间看看吧！"),

  GAME_ALREADY_IN_MATCH(25015, "对不起，您当前正在其他比赛中，请等待您的比赛结束后再进入其他比赛房间！"),

  GAME_NOT_MATCH_REQUIREMENT(25016, "对不起，您未满足报名需求，报名失败！"),

  GAME_START_MSG(25017, "您报名的比赛还有{1}秒开始 ，是否立即前往（否则按弃权处理）？"),

  GAME_BANKER_NOT_ALLOW_EXCHANGE(25018, "对不起，庄家不允许上下分。请您先下庄后再上下分。"),

  GAME_CHIP_NOT_ENOUGH(25019, "对不起，您的筹码不足{1}"),

  GAME_NOT_ALLOW_EXCHANGE(25020, "对不起，游戏阶段不允许上下分，请您在休息阶段进行上下分操作！"),

  GAME_BANKER_QUEUE_FULL(25021, "对不起，当前上庄列队已满，请您稍后再试。"),

  // [飞禽走兽]
  GAME_ICON_BET_MAX(25022, "对不起，每个图标最多允许下注{1}W！"),

  GAME_BANKER_CHIP_NOT_ENOUGH(25023, "对不起，下注失败，当前庄家筹码不足！"),

  GAME_PROPS_NOT_ENOUGH(25024, "对不起，您的道具数量不足，使用失败！"),

  GAME_PROPS_NOT_EFFECT_OFFLINE_PLAYER(25025, "对不起，您不能对没在线的玩家使用道具！"),

  GAME_NOT_ALLOW_CHAT(25026, "对不起，该房间禁止聊天！"),

  GAME_PLAYING_NOT_ALLOW_CHAT(25027, "对不起，游戏中禁止聊天！"),

  GAME_IN_BLACK_LIST(25028, "对不起，您无法进入此游戏！"),

  GAME_GOLD_NOT_ENOUGH(25029, "对不起，您的金币不足{1}"),

  GAME_EXIT_ROOM_EXIT_SIT_FIRST(25030, "在座位中不能退出房间,请先退出桌子!"),

  GAME_BET_TIME_OVER(25031, "下注超时"),

  GAME_STRATEGY_ERROR(25032, "游戏策略信息有误{1}"),

  GAME_CONTROL_ERROR(25033, "游戏控制信息有误{1}"),

  GAME_ROOM_NOT_EXIST(25034, "对不起，当前房间不存在，试试其他房间吧！"),

  GAME_ROOM_CLOSED(25035, "对不起，当前房间被关闭，试试其他房间吧！"),


  GAME_PLAYING_NOT_LEAVE(25036, "对不起，游戏中不能退出"),

  GAME_NOT_YOUR_TURN(25037, "对不起，请等待其他玩家操作"),



  GAME_LIMIT_BET(25038, "对不起，下注不能超过限额"),

  GAME_SEAT_EMPTY(25039, "对不起，未就坐"),

  GAME_NO_EMPTY_SEAT(25039, "对不起，未含有空闲座位{1}"),

  // -------游戏（50） end--------//


  // -------水浒传（51） start------//

  // -------水浒传（51） end--------//


  // -------水果拉霸（52） start------//

  // -------水果拉霸（52） end--------//

  // -------飞禽走兽（53） start------//

  // -------飞禽走兽（53） end--------//

  // -------金蟾捕鱼（54） start------//
  JCBY_BULLET_EMPTY(25401, "没有子弹"),
  // -------金蟾捕鱼（54） end--------//

  // -------四人牛牛（55） end--------//
  SIREN_NIUNIU_COULD_NOT_EXCHANGE_TABLE_GAME_READY(25501, "对不起, 已经准备, 不能换桌"),

  SIREN_NIUNIU_COULD_NOT_EXCHANGE_HAVE_NO_OTHER_TABLE(25502, "对不起, 没有其他桌可换"),

  SIREN_NIUNIU_BET_CHIP_ILLEGAL(25503, "对不起，下注筹码不合法"),

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
