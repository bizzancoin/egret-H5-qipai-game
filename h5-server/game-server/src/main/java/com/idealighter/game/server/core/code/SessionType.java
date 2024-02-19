package com.idealighter.game.server.core.code;

import io.netty.util.AttributeKey;

/**
 * 会话存储相关数据类型Key .
 * 
 * @date 2015年7月24日 下午2:52:47
 *
 */
public interface SessionType {
  // 玩家ID
  AttributeKey<Long> PLAYER_ID_KEY = AttributeKey.valueOf("playerId");
  // 密码失败次数
  // AttributeKey<Integer> PWD_FAILURE_NUM = AttributeKey.valueOf("pwdFailureNum");
  // 登录检验码
  // AttributeKey<String> LOGIN_KEY = AttributeKey.valueOf("loginKey");
  // 注册检验码
  // AttributeKey<String> REGISTER_KEY = AttributeKey.valueOf("registerKey");
  // 手机检验码
  // AttributeKey<String> PHONE_KEY = AttributeKey.valueOf("phoneKey");
  // 客户端请求消息的序号
  AttributeKey<Integer> REQ_MSG_ORDER = AttributeKey.valueOf("reqMsgOrder");

  // 退出类型
  AttributeKey<Byte> EXIT_TYPE = AttributeKey.valueOf("exitType");
}
