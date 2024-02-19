package com.idealighter.game.message;


public interface ModuleMsgIdConstant {

  // 心跳请求-请求(使用)
  public static final int HEART_BEAT = 1;

  public static final int DEFAULT_RES = 2;

  // 错误信息-通知
  public static final int NOTICE_ERROR = 3;

  // 通用左下角展示-通知
  public static final int NOTICE_SHOW = 4;
  // 通用提示消息-通知
  public static final int NOTICE_TIP = 5;
  // 通用警告-通知
  public static final int NOTICE_WARN = 6;



  /**
   * 登录模块.
   * 
   * @ClassName Login.
   * @author houdongsheng
   * @date 2017年11月25日 上午10:24:53 .
   */
  public static interface Account {

    // 游客登录(使用)
    public static final int TOURIST_LOGIN = 101;

    // 手机端-手机短信登录-获取短信(使用)
    public static final int PHONE_LOGIN_OR_REGISTER_GET_SMS = 102;


    // 手机端，手机号码登录/注册（使用）
    public static final int PHONE_LOGIN_OR_REGISTER = 103;

    // 机器人登录(使用)
    public static final int ROBOT_LOGIN = 104;

    // 退出系统
    public static final int LOGOUT = 105;
    // 断线重连登录(使用)
    public static final int RE_LOGIN = 106;

    // 游客账号升级-请求(使用)
    public static final int TOURIST_UPDATE = 107;


    // 通知重复登录退出
    public static final int NOTICE_REPETITION_LOGOUT = 108;
    // 通知锁定退出
    public static final int NOTICE_LOCKED_LOGOUT = 109;
    // 游戏关闭通知退出
    public static final int NOTICE_GAME_EXIT_LOGOUT = 110;
    // channel关闭退出（网络层面问题）
    public static final int NOTICE_CHANNEL_CLOSE = 111;
    // 停服通知
    public static final int NOTICE_APPLICATION_CLOSE = 112;
  }



  /**
   * PLAYER. 玩家模块：ModuleMsgIdConstant.PLAYER
   */
  public static interface Player {
    // 玩家信息-通知（使用）
    public static final int NOTICE_PLAYER_INFO = 201;

    // 请求修改登录密码-请求
    public static final int MODIFY_LOGIN_PWD = 211;

    // 玩家属性变更
    public static final int NOTICE_ATTR_CHANGE = 214;
  }


  /**
   * 通知模块.
   * 
   * @ClassName Notice. *
   * @author houdongsheng
   * @date 2017年11月25日 下午3:27:04 .
   */
  public static interface Notice {
    // 跑马灯公告（使用）
    public static int NOTICE_MARQUEE_NOTICE = 501;
  }


  /**
   * 福利模块：ModuleMsgIdConstant.Welfare
   * 
   * @ClassName GameHall.
   * @Description
   * @author houdongsheng
   * @date 2017年11月25日 上午10:25:06 .
   */
  public static interface Welfare {
    // 领取救济金-请求
    public static final int RECEIVE_BENEFITS = 701;
    // 签到-请求
    public static final int SIGN_IN = 702;
    // 福利刷新-通知
    public static final int NOTICE_REFRESH_WELFARE = 703;
    // 福利信息-通知(使用)
    public static final int NOTICE_WELFARE_INFO = 704;
  }


  /**
   * 游戏. 游戏公用：ModuleMsgIdConstant.ShuiHu.
   *
   */
  public static interface Game {

    // 房间座位变更信息-通知（使用）
    public static final int NOTICE_SEAT_INFO_UPDATE = 5004;
    // 房间成员变更信息-通知（使用）
    public static final int NOTICE_MEM_INFO_UPDATE = 5005;
    // 删除房间成员信息-通知（使用）
    public static final int NOTICE_REMOVE_MEM_INFO = 5006;


    // 通知房间关闭
    public static final int NOTICE_ROOM_CLOSE_KICKOUT = 5009;

    public static final int NOTICE_GAME_START = 5097;
    public static final int NOTICE_GAME_CLOSE = 5098;
    public static final int GAME_LIST = 5099;
  }


  /**
   * 金蟾捕鱼模块：ModuleMsgIdConstant.Jcby.
   * 
   * @ClassName Jcby.
   * @author houdongsheng
   * @date 2018年2月9日 下午2:00:47 .
   */
  public static interface Jcby {
    public static final int ENTER_GAME_HALL = 5401;
    public static final int ENTER_ROOM = 5402;
    public static final int FAST_ENTER_TABLE = 5403;
    public static final int FIRE = 5406;
    public static final int SWITCH_BATTERY = 5407;
    public static final int HIT = 5408;
    public static final int EXIT_ROOM = 5409;
    public static final int EXIT_TABLE = 5410;
    public static final int SCREEN = 5411;
    public static final int LOCK = 5412;
    public static final int CANCEL_LOCK = 5413;
    public static final int GAME_INFO = 5414;

    public static final int EXCHANGE_GOLDS = 5415;

    public static final int EXCHAGE_CHIPS = 5416;

    public static final int FAST_ENTER = 5417;

    public static final int RES_OTHER_ENTER_TABLE = 5451;
    public static final int RES_CHIPS_CHANGE = 5452;
    public static final int RES_FIRE = 5453;
    public static final int RES_BATTERY_CHANGE = 5454;
    public static final int RES_HIT = 5455;
    public static final int RES_EXIT_TABLE = 5456;
    public static final int RES_LOCK = 5457;
    public static final int RES_CANCEL_LOCK = 5458;
    public static final int RES_INSTEAD_PLAYER_UPGRADE = 5459;
    public static final int RES_LIKUI_UPGRADE = 5460;
    public static final int RES_PRODUCE_FISH = 5461;
    public static final int RES_SWITCH_SCENCE = 5462;
  }



  /**
   * 百家乐.
   *
   * @author abin
   * @date 2018年4月19日 下午1:39:15 .
   */
  public static interface Baccarat {
    // 进入大厅-请求(使用)
    public static final int ENTER_GAME_HALL = 5901;

    // 进入房间-请求
    public static final int ENTER_ROOM = 5902;

    // 进入桌子-请求
    public static final int ENTER_TABLE = 5903;

    // 快速进入房间-请求(使用)
    public static final int FASTER_ENTER_TABLE = 5904;

    // 退出房间-请求（使用）
    public static final int EXIT_ROOM = 5905;

    // 退出桌子-请求（使用）
    public static final int EXIT_TABLE = 5906;

    // 开始下注-请求（使用）
    public static final int BET = 5907;

    // 开始下注-请求（使用）
    public static final int CLEAR_BET = 5908;

    public static final int EXCHANGE_GOLDS = 5909;

    public static final int EXCHAGE_CHIPS = 5910;

    public static final int FASTER_ENTER = 5911;


    public static final int NOTICE_OTHER_ENTER_TABLE = 5920;
    // 兑换金币-请求（机器人）（使用）
    public static final int NOTICE_CHIPS_CHANGE = 5921;

    // public static final int NOTICE_PLAYER_BET = 5922;
    //
    // public static final int NOTICE_PLAYER_CLEAR_BET = 5923;

    public static final int NOTICE_HISTORY = 5922;

    public static final int NOTICE_MULTIPLE = 5923;

    public static final int NOTICE_STAGE_CUT_CARD = 5924;

    public static final int NOTICE_STAGE_DEAL_CARD = 5925;

    // 通知结算-请求（使用）
    public static final int NOTICE_STAGE_BILL = 5926;

    // 结果统计信息
    public static final int NOTICE_STATISTICS_INFO = 5927;

    public static final int NOTICE_TABLE_BET = 5928;

    public static final int NOTICE_TIME = 5929;

    public static final int NOTICE_RESULT = 5930;

    public static final int NOTICE_PLAYER_EXIT_TABLE = 5931;



    // 获取游戏信息
    public static final int GAME_INFO = 5999;
  }
  /**
   * 百人牛牛模块：ModuleMsgIdConstant.BaiRenNiuNiu.
   * 
   * @ClassName BaiRenNiuNiu.
   * 
   * @author houdongsheng
   * 
   * @date 2018年4月20日 下午4:29:47 .
   */
  public static interface BaiRenNiuNiu {
    public static final int ENTER_GAME_HALL = 6001;
    public static final int ENTER_ROOM = 6002;
    public static final int FAST_ENTER_TABLE = 6003;
    public static final int EXIT_TABLE = 6004;
    public static final int EXIT_ROOM = 6005;
    public static final int APPLY_BANKER = 6006;
    public static final int CANCEL_APPLY_BANKER = 6007;
    public static final int OFF_BANKER = 6008;
    public static final int BET = 6009;
    public static final int CLEAR_BET = 6010;
    public static final int GAME_INFO = 6011;

    public static final int EXCHANGE_GOLDS = 6012;

    public static final int EXCHAGE_CHIPS = 6013;

    public static final int FAST_ENTER = 6014;

    public static final int RES_EXIT_ROOM = 6051;
    public static final int RES_CHIPS_CHANGE = 6052;
    public static final int RES_ENTER_TABLE = 6053;
    public static final int RES_OTHER_ENTER_TABLE = 6054;
    public static final int RES_EXIT_TABLE = 6055;
    public static final int RES_APPLY_BANKER = 6057;
    public static final int RES_DEAL_CARDS = 6058;
    public static final int RES_TABLE_BET = 6059;
    public static final int RES_BANKER_INFO = 6060;
    public static final int RES_BALANCE = 6061;
    public static final int RES_HISTORY = 6062;
    public static final int RES_TIME = 6063;
  }

  /**
   * @ClassName DDZ.
   * @Description 斗地主模块
   * @author houdongsheng
   * @date 2018年7月20日 下午2:17:14 .
   */
  public static interface DDZ {
    // 进入大厅
    public static final int ENTER_GAME_HALL = 6201;
    // 进入房间
    public static final int ENTER_ROOM = 6202;
    // 快速进入桌子
    public static final int FAST_ENTER_TABLE = 6203;
    // 换桌
    public static final int EXCHANGE_TABLE = 6204;
    // 准备
    public static final int READY = 6205;
    // 玩家是否叫/抢地主
    public static final int CALL_CARD = 6206;
    // 玩家是否加倍
    public static final int DOUBLE = 6207;
    // 请求提示
    public static final int PROMPT = 6208;
    // 玩家出牌
    public static final int PLAY_CARD = 6209;
    // 放弃出牌
    public static final int ABANDON_PLAY_CARD = 6210;
    // 玩家喊话
    public static final int PLAYER_SHOUT = 6211;
    // 退出桌子
    public static final int EXIT_TABLE = 6212;
    // 退出房间
    public static final int EXIT_ROOM = 6213;
    // 获取游戏最新消息
    public static final int GAME_LAST_INFO = 6214;
    // 托管/取消托管
    public static final int TRUSTEE = 6215;
    // 兑换金币
    public static final int EXCHANGE_GOLDS = 6216;
    // 兑换筹码
    public static final int EXCHAGE_CHIPS = 6217;
    // 明牌开始
    public static final int SHOW_CARD_PLAY = 6218;
    // 加载完成
    public static final int LOAD_OK = 6219;
    // 快速进入桌子
    public static final int FAST_ENTER = 6220;

    // 玩家自己进入桌子
    public static final int RES_ENTER_TABLE = 6251;
    // 其他玩家进入桌子
    public static final int RES_OTHER_ENTER_TABLE = 6252;
    // 进入准备阶段
    public static final int RES_READY_STAGE = 6253;
    // 准备-通知
    public static final int RES_READY = 6254;
    // 游戏开始通知
    public static final int RES_GAME_START = 6255;
    // 发牌通知
    public static final int RES_DEAL_CARDS = 6256;
    // 叫牌通知
    public static final int RES_CALL_CARD = 6257;
    // 发送底牌
    public static final int RES_HIDDEN_CARDS = 6258;
    // 加倍通知
    public static final int RES_DOUBLE = 6259;
    // 加倍结束通知
    public static final int RES_DOUBLE_OVER = 6260;
    // 请求提示
    public static final int RES_PROMPT = 6261;
    // 出牌-通知
    public static final int RES_PLAY_CARD = 6262;
    // 要不起-通知
    public static final int RES_ABANDON_PLAY_CARD = 6263;
    // 玩家喊话
    public static final int RES_PLAYER_SHOUT = 6264;
    // 筹码变化-通知
    public static final int RES_CHIPS_CHANGE = 6265;
    // 游戏结束-通知
    public static final int RES_GAME_OVER = 6266;
    // 退出桌子-通知
    public static final int RES_EXIT_TABLE = 6267;
    // 退出房间-通知
    public static final int RES_EXIT_ROOM = 6268;
    // 托管/取消托管-通知
    public static final int RES_TRESTEE = 6269;
    // 倍数通知
    public static final int RES_MULTIPLE = 6270;
    // 明牌开始
    public static final int RES_SHOW_CARD_PLAY = 6271;
  }
}
